package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
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
            connection.setAutoCommit(false);
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
        try {
            // 1. получить данные формы
            String supplier_id1 = req.getParameter("supplier_id");
            int supplier_id = Integer.parseInt(supplier_id1);
            String product_name = req.getParameter("product_name");
            String category_name = req.getParameter("category_name");
            BigDecimal net_retail_price = new BigDecimal(req.getParameter("net_retail_price"));
            int available_quantity = Integer.parseInt(req.getParameter("available_quantity"));
            BigDecimal wholesale_price = new BigDecimal(req.getParameter("wholesale_price"));
            BigDecimal unit_kg_weight = new BigDecimal(req.getParameter("unit_kg_weight"));

            if (product_name != null &&
                    category_name != null &&
                    net_retail_price != null &&
                    //available_quantity != null &&
                    wholesale_price != null &&
                    unit_kg_weight != null) {

                // 2. вызвать процедуру sp_new_product(), передать данные формы
                try {
                    connection.beginRequest();
                    CallableStatement callableStatement = connection.prepareCall("{call sp_new_product(?, ?, ?, ?, ?, ?, ?, ?)}");
                    callableStatement.setInt(1, supplier_id);
                    callableStatement.setString(2, product_name);
                    callableStatement.setString(3, category_name);
                    callableStatement.setBigDecimal(4, net_retail_price);
                    callableStatement.setInt(5, available_quantity);
                    callableStatement.setBigDecimal(6, wholesale_price);
                    callableStatement.setBigDecimal(7, unit_kg_weight);
                    callableStatement.registerOutParameter(8, Types.INTEGER);
                    callableStatement.execute();
                    connection.commit();
                    int id = callableStatement.getInt(8);

                    // 3. вернуть пользователю сообщение об успешном или не успешном добавлении продукта
                    resp.getWriter().println("Product added success (id = " + id + ")!");

                } catch (SQLException e) {
                    resp.getWriter().println("Product not added...");
                }
            }
        }catch (Exception ex){
            resp.getWriter().println("ERROR: "+ex.getMessage());
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
