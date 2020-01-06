package org.itstep;

import org.itstep.dao.UserDao;
import org.itstep.domain.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String user = getServletContext().getInitParameter("db.username");
        String pass = getServletContext().getInitParameter("db.password");
        String url = getServletContext().getInitParameter("db.url");
        try {
            userDao = new UserDao(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(userDao!= null){
            User user = null;
            PrintWriter writer = resp.getWriter();
            try {
                user = userDao.findByLoginAndPassword(login, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(user!=null){
                writer.println("Resource Access Gained");
            }else {
                writer.println("Incorrect login or password. Try again...");
            }
        }
    }
}
