package org.samarskii.dao;

import org.samarskii.domain.Token;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TokenDao extends GenericDao<Token> {

    private static final String INSERT = "INSERT INTO tokens(buyer_id, token) VALUES(?, ?)";
    private static final String SELECT_ALL = "SELECT id, token FROM tokens";
    private static final String SELECT = "SELECT id, token FROM tokens WHERE buyer_id = ?";
    private static final String SELECT_BY_TOKEN = "SELECT id, buyer_id FROM tokens WHERE token = ?";
    private static final String DELETE = "DELETE FROM tokens WHERE token = ?";

    public TokenDao(String connectionString, String user, String password) throws SQLException {
        super(connectionString, user, password);
    }

    @Override
    public boolean save(Token data) {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, data.getBuyerId());
            preparedStatement.setString(2, data.getToken());
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
    public List<Token> findAll() throws SQLException {
        return null;
    }

    @Override
    public void update(Token data) throws SQLException {

    }

    @Override
    public void delete(Token data) throws SQLException {
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

    public Token findByBuyerId(int buyerId) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT);
            statement.setInt(1, buyerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String tokenStr = resultSet.getString("token");
                int id = resultSet.getInt("id");
                Token token = new Token(id, buyerId, tokenStr);
                return token;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Token findByToken(String tokenStr) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_TOKEN);
            statement.setString(1, tokenStr);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int buyerId = resultSet.getInt("buyer_id");
                int id = resultSet.getInt("id");
                Token token = new Token(id, buyerId, tokenStr);
                return token;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
