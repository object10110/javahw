package org.samarskii.dao;

import org.samarskii.domain.Buyer;
import org.samarskii.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends GenericDao<Product> {

    private static final String INSERT = "INSERT INTO products(`name`, amount) VALUES(?, ?)";
    private static final String SELECT_ALL = "SELECT id, `name`, amount FROM products";
    private static final String SELECT = "SELECT id, `name`, amount FROM products WHERE id = ?";
    private static final String DELETE = "DELETE FROM products WHERE id = ?";
    private static final String UPDATE = "UPDATE products SET `name` = ?, amount = ? WHERE id = ?";

    public ProductDao(String connectionString, String user, String password) throws SQLException {
        super(connectionString, user, password);
    }

    @Override
    public boolean save(Product data) {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, data.getName());
            preparedStatement.setInt(2, data.getAmount());
            preparedStatement.execute();
            commit();
            return true;
        } catch (SQLException e) {
            try {
                rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setAmount(resultSet.getInt("amount"));
            products.add(product);
        }
        return products;
    }

    public Product findById(int productId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setAmount(resultSet.getInt("amount"));
            return product;
        }
        return null;
    }

    @Override
    public void update(Product data) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            startTransaction();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, data.getName());
            preparedStatement.setInt(2, data.getAmount());
            preparedStatement.setInt(3, data.getId());
            preparedStatement.execute();
            commit();
        } catch (SQLException e) {
            try {
                rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Product data) throws SQLException {
        PreparedStatement statement = null;
        try {
            startTransaction();
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, data.getId());
            statement.execute();
            commit();
        } catch (SQLException e) {
            try {
                rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
