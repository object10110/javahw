package org.samarskii.dao;

import org.samarskii.domain.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDao extends GenericDao<Order> {

    private static final String INSERT = "INSERT INTO orders(buyer_id, seller_id, product_id, amount) VALUES(?, ?, ?, ?)";

    public OrderDao(String connectionString, String user, String password) throws SQLException {
        super(connectionString, user, password);
    }

    @Override
    public boolean save(Order data) {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, data.getBuyerId());
            preparedStatement.setInt(2, data.getSellerId());
            preparedStatement.setInt(3, data.getProductId());
            preparedStatement.setInt(4, data.getAmount());
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

    public boolean save(Order data, boolean startTransaction, boolean commit) {
        try {
            if(startTransaction) {
                startTransaction();
            }
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, data.getBuyerId());
            preparedStatement.setInt(2, data.getSellerId());
            preparedStatement.setInt(3, data.getProductId());
            preparedStatement.setInt(4, data.getAmount());
            preparedStatement.execute();
            if(commit) {
                commit();
            }
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
    public List<Order> findAll() throws SQLException {
        return null;
    }

    @Override
    public void update(Order data) throws SQLException {

    }

    @Override
    public void delete(Order data) throws SQLException {

    }
}
