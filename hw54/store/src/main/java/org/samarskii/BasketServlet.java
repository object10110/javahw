package org.samarskii;

import org.samarskii.dao.*;
import org.samarskii.domain.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
    private BuyerDao buyerDao;
    private BasketDao basketDao;
    private ProductDao productDao;
    private TokenDao tokenDao;
    private OrderDao orderDao;
    private final static String cookieName = "token";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String user = getServletContext().getInitParameter("db.username");
        String pass = getServletContext().getInitParameter("db.password");
        String url = getServletContext().getInitParameter("db.url");
        try {
            buyerDao = new BuyerDao(url, user, pass);
            tokenDao = new TokenDao(url, user, pass);
            basketDao = new BasketDao(url, user, pass);
            productDao = new ProductDao(url, user, pass);
            orderDao = new OrderDao(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getRequestDispatcher("/WEB-INF/jsp/products.html").forward(req, resp);
        String page = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">" +
                "    <title>Login</title>\n" +
                "    <link href=\"{pathToCss}\" rel=\"stylesheet\"/>\n" +
                "</head>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Basket</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"panel\">\n" +
                "        <div class=\"products\">\n" +
                "            <h1>BASKET</h1>\n" +
                "            {productsList}" +
                "            \n" +
                "            <form action=\"{actionPath}\" method=\"post\">\n" +
                "                <input class=\"buy-all\" type=\"submit\" value=\"BUY ALL\">\n" +
                "            </form>" +
                "        </div>" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        Buyer buyer = getLoggedBuyerByToken(req);
        page = page.replace("{pathToCss}", req.getContextPath() + "/resources/css/style.css");

        if (basketDao != null && buyer != null) {
            try {
                List<BasketItem> byBuyerId = basketDao.findByBuyerId(buyer.getId());
                int count = byBuyerId.stream().mapToInt(BasketItem::getAmount).sum();
                page = page.replace("{amountInBasket}", count + "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (productDao != null) {
            try {
                List<BasketItem> all = basketDao.findByBuyerId(buyer.getId());
                StringBuilder htmlProducts = new StringBuilder();
                for (BasketItem p : all) {
                    Product product = productDao.findById(p.getId());
                    String htmlProduct = product.toHtmlForBasket();
                    htmlProduct = htmlProduct.replace("{amountInBasket}", p.getAmount() + "");
                    htmlProducts.append(htmlProduct);
                }
                page = page.replace("{productsList}", htmlProducts);
                page = page.replace("{actionPath}", req.getContextPath() + "/basket");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.getWriter().print(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Buyer buyer = getLoggedBuyerByToken(req);
        if (buyer != null) {
            if (basketDao != null && productDao != null) {
                try {
                    List<BasketItem> listBasketItems = basketDao.findByBuyerId(buyer.getId());
                    if(listBasketItems!= null && listBasketItems.size()>0) {
                        BasketItem basketItem = listBasketItems.get(0);
                        Order order = new Order(0, basketItem.getBuyerId(), 0, basketItem.getProductId(), basketItem.getAmount());
                        orderDao.save(order, true, false);
                        for (int i = 1; i < listBasketItems.size(); i++) {
                            basketItem = listBasketItems.get(i);
                            order = new Order(0, basketItem.getBuyerId(), 0, basketItem.getProductId(), basketItem.getAmount());
                            orderDao.save(order, false, false);
                            basketDao.delete(basketItem, false, false);
                        }
                        basketDao.delete(listBasketItems.get(0), false, true);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String path = req.getContextPath() + "/products";
                resp.sendRedirect(path);
            }
        }
    }

    private Buyer getLoggedBuyerByToken(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Cookie cookie = null;
        Buyer user = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        if (cookie != null) {
            if (tokenDao != null) {
                try {
                    Token selectedToken = tokenDao.findByToken(cookie.getValue());
                    if (selectedToken != null) {
                        if (buyerDao != null) {
                            try {
                                user = buyerDao.findById(selectedToken.getBuyerId());
                                return user;
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
