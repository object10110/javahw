package org.samarskii.dao;

import org.samarskii.domain.BasketItem;
import org.samarskii.domain.Buyer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BasketDao extends GenericDao<BasketItem> {

    private static final String INSERT = "INSERT INTO basket(buyer_id, product_id, amount) VALUES(?, ?, ?)";
    private static final String SELECT_ALL = "SELECT id, buyer_id, product_id, amount FROM basket";
    private static final String SELECT_BY_BUYER_ID = "SELECT id, buyer_id, product_id, amount FROM basket WHERE buyer_id = ?";
    private static final String SELECT_BY_BUYER_ID_AND_PRODUCT_ID = "SELECT id, buyer_id, product_id, amount FROM basket WHERE buyer_id = ? AND product_id = ?";
    private static final String DELETE = "DELETE FROM basket WHERE id = ?";
    private static final String UPDATE = "UPDATE basket SET buyer_id = ?, product_id = ?, amount = ? WHERE id = ?";


    public BasketDao(String connectionString, String user, String password) throws SQLException {
        super(connectionString, user, password);
    }

    @Override
    public boolean save(BasketItem data) {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, data.getBuyerId());
            preparedStatement.setInt(2, data.getProductId());
            preparedStatement.setInt(3, data.getAmount());
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
    public List<BasketItem> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<BasketItem> items = new ArrayList<BasketItem>();
        while(resultSet.next()){
            BasketItem item = new BasketItem();
            item.setId(resultSet.getInt("id"));
            item.setBuyerId(resultSet.getInt("buyer_id"));
            item.setProductId(resultSet.getInt("product_id"));
            item.setAmount(resultSet.getInt("amount"));
            items.add(item);
        }
        return  items;
    }

    public List<BasketItem> findByBuyerId(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_BUYER_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<BasketItem> items = new ArrayList<BasketItem>();
        while(resultSet.next()){
            BasketItem item = new BasketItem();
            item.setId(resultSet.getInt("id"));
            item.setBuyerId(resultSet.getInt("buyer_id"));
            item.setProductId(resultSet.getInt("product_id"));
            item.setAmount(resultSet.getInt("amount"));
            items.add(item);
        }
        return  items;
    }

    public BasketItem findByBuyerIdAndProductId(int buyerId, int productId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_BUYER_ID_AND_PRODUCT_ID);
        statement.setInt(1, buyerId);
        statement.setInt(2, productId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            BasketItem item = new BasketItem();
            item.setId(resultSet.getInt("id"));
            item.setBuyerId(resultSet.getInt("buyer_id"));
            item.setProductId(resultSet.getInt("product_id"));
            item.setAmount(resultSet.getInt("amount"));
            return item;
        }
        return null;
    }

    @Override
    public void update(BasketItem data) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            startTransaction();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, data.getBuyerId());
            preparedStatement.setInt(2, data.getProductId());
            preparedStatement.setInt(3, data.getAmount());
            preparedStatement.setInt(4, data.getId());
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
    public void delete(BasketItem data) throws SQLException {
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
    public void delete(BasketItem data, boolean startTransaction, boolean commit) throws SQLException {
        PreparedStatement statement = null;
        try {
            if(startTransaction) {
                startTransaction();
            }
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, data.getId());
            statement.execute();
            if(commit) {
                commit();
            }
        } catch (SQLException e) {
            try {
                rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
