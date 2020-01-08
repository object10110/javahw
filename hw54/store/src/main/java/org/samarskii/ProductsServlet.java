package org.samarskii;

import org.samarskii.dao.BasketDao;
import org.samarskii.dao.BuyerDao;
import org.samarskii.dao.ProductDao;
import org.samarskii.dao.TokenDao;
import org.samarskii.domain.BasketItem;
import org.samarskii.domain.Buyer;
import org.samarskii.domain.Product;
import org.samarskii.domain.Token;

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

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private BuyerDao buyerDao;
    private BasketDao basketDao;
    private ProductDao productDao;
    private TokenDao tokenDao;
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
                "    <title>Products</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"panel\">\n" +
                "        <div class=\"products\">\n" +
                "            <h1>PRODUCTS</h1>\n" +
                "            <a class=\"basket\" href=\"{pathToBasket}\">BASKET</a><label class=\"amount-in-basket\">({amountInBasket})</label>" +
                "            {productsList}" +
                "        </div>\n" +
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
                page = page.replace("{amountInBasket}", count+"");
                page = page.replace("{pathToBasket}", req.getContextPath() + "/basket");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (productDao != null) {
            try {
                List<Product> all = productDao.findAll();
                StringBuilder htmlProducts = new StringBuilder();
                for (Product p : all) {
                    htmlProducts.append(p.toHtml());
                }
                page = page.replace("{productsList}", htmlProducts);
                page = page.replace("{actionPath}", req.getContextPath() + "/products");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.getWriter().print(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId;
        productId = Integer.parseInt(req.getParameter("product-id"));
        Buyer buyer = getLoggedBuyerByToken(req);
        if (buyer != null) {
            if (basketDao != null && productDao != null) {
                try {
                    Product product = productDao.findById(productId);
                    if (product != null) {
                        BasketItem productInBasket = basketDao.findByBuyerIdAndProductId(buyer.getId(), product.getId());
                        //если такой товар уже есть в корзине
                        if (productInBasket != null && product.getAmount() > 0) {
                            productInBasket.setAmount(productInBasket.getAmount() + 1);
                            basketDao.update(productInBasket);
                            product.setAmount(product.getAmount() - 1);
                            productDao.update(product);
                        } else if (productInBasket == null && product.getAmount() > 0) {
                            BasketItem newItem = new BasketItem(0, buyer.getId(), product.getId(), 1);
                            basketDao.save(newItem);
                            product.setAmount(product.getAmount() - 1);
                            productDao.update(product);
                        }
                        String path = req.getContextPath() + "/products";
                        resp.sendRedirect(path);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            String path = req.getContextPath() + "/";
            resp.sendRedirect(path);
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
