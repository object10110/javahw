package org.samarskii;

import org.samarskii.dao.BuyerDao;
import org.samarskii.dao.TokenDao;
import org.samarskii.domain.Buyer;
import org.samarskii.domain.Token;
import org.samarskii.domain.TokenGenerator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class HomeServlet extends HttpServlet {

    private BuyerDao buyerDao;
    private TokenDao tokenDao;
    private final static String cookieName = "token";


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String user = getServletContext().getInitParameter("db.username");
        String pass = getServletContext().getInitParameter("db.password");
        String url = getServletContext().getInitParameter("db.url");
        try {
            buyerDao = new BuyerDao(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            tokenDao = new TokenDao(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //пытаемся получить учетку пользователя, если он уже залогинен
        Buyer user = getLoggedBuyerByToken(req);
        if(user == null) {
            req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        }
        else {
            //req.getRequestDispatcher("/WEB-INF/jsp/products.html").forward(req, resp);
            String path = req.getContextPath() + "/products";
            resp.sendRedirect(path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //пытаемся получить учетку пользователя, если он уже залогинен
        Buyer user = getLoggedBuyerByToken(req);

        PrintWriter writer = resp.getWriter();

        //если пользователь не имеет токена для входа
        if(user==null) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            if (buyerDao != null) {
                try {
                    user = buyerDao.findByLoginAndPassword(login, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (user != null && tokenDao != null) {
                    String generatedToken = TokenGenerator.generateToken();
                    Token token = new Token(0, user.getId(), generatedToken);
                    if (tokenDao.save(token)) {
                        Cookie cook = new Cookie(cookieName, generatedToken);
                        resp.addCookie(cook);
                        //переход на страницу с товарами
                        //req.getRequestDispatcher("/WEB-INF/jsp/products.jsp").forward(req, resp);
                        String path = req.getContextPath() + "/products";
                        resp.sendRedirect(path);
                        //writer.println("Resource Access Gained");
                    }
                } else {
                    writer.println("Incorrect login or password. Try again...");
                }
            }
        }
        else {
            //req.getRequestDispatcher("/WEB-INF/jsp/products.jsp").forward(req, resp);
            String path = req.getContextPath() + "/products";
            resp.sendRedirect(path);
        }
    }

    private Buyer getLoggedBuyerByToken(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        Cookie cookie = null;
        Buyer user = null;

        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        if(cookie != null){
            if(tokenDao!=null){
                try {
                    Token selectedToken = tokenDao.findByToken(cookie.getValue());
                    if(selectedToken!= null){
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
