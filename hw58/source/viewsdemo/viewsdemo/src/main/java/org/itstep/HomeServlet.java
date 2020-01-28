package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class HomeServlet extends HttpServlet {

    static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext servletContext = config.getServletContext();
        var url = servletContext.getInitParameter("db:url");
        var user = servletContext.getInitParameter("db:user");
        var password = servletContext.getInitParameter("db:password");

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean exists = false;
        String id = "";
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT id from users where login = ? and password=?");
            stmt.setString(1, req.getParameter("login"));
            stmt.setString(2, req.getParameter("password"));
            ResultSet rs = stmt.executeQuery();
            exists = rs.next();
            id = rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(exists){
            Cookie cookie = new Cookie("user_id", id);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600); // 1 час
            resp.addCookie(cookie);
            resp.sendRedirect(getServletContext().getContextPath() + "/secret");
        } else {
            resp.sendRedirect(getServletContext().getContextPath() + "/");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
