package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class UpdateServlet extends HttpServlet {

    private String url;
    private String user;
    private String password;

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

        url = getServletContext().getInitParameter("db:url2");
        user = getServletContext().getInitParameter("db:user");
        password = getServletContext().getInitParameter("db:pass");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false); // SET autcommit=0

            Statement statement = conn.createStatement();
            try {
                int amount = Integer.parseInt(req.getParameter("amount"));
                statement.execute("update finances set amount = amount + " + amount + " where id=1;");
                // throw
                statement.execute("update finances set amount = amount - " + amount + " where id=2;");
                conn.commit();
                writer.println("Ok");
            } catch (Throwable ex) {
                conn.rollback();
                writer.println("Rollback: " + ex.getLocalizedMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
