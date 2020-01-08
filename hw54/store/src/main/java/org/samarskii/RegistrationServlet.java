package org.samarskii;

import org.samarskii.dao.BuyerDao;
import org.samarskii.domain.Buyer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private BuyerDao buyerDao;

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
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Buyer user = new Buyer(login, password);
        if(buyerDao != null) {
            PrintWriter writer = resp.getWriter();
            if(buyerDao.save(user)){
                //writer.println("You are SUCCESSFULLY registered!");
                req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
            }
            else {
                writer.println("Login is busy by another user. Reload the page and enter another login...");
            }
        }
    }
}
