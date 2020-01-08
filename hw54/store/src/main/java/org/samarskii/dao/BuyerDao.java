package org.samarskii.dao;

import org.samarskii.domain.Buyer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BuyerDao extends GenericDao<Buyer> {

    private static final String INSERT = "INSERT INTO buyers(login, password, firstName, lastName) VALUES(?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT id, login, password, firstName, lastName FROM buyers";
    private static final String SELECT = "SELECT id, login, password, firstName, lastName FROM buyers WHERE login = ? AND password = ?";
    private static final String SELECT_BY_ID = "SELECT id, login, password, firstName, lastName FROM buyers WHERE id = ?";
    private static final String DELETE = "DELETE FROM buyers WHERE id = ?";
    private static final String UPDATE = "UPDATE buyers SET login = ?, password = ?, firstName = ?, lastName = ? WHERE id = ?";

    public BuyerDao(String connectionString, String user, String password) throws SQLException {
        super(connectionString, user, password);
    }

    @Override
    public boolean save(Buyer data) {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, data.getLogin());
            preparedStatement.setString(2, data.getPassword());
            preparedStatement.setString(3, data.getFirstName());
            preparedStatement.setString(4, data.getLastName());
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
    public List<Buyer> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Buyer> users = new ArrayList<Buyer>();
        while(resultSet.next()){
            Buyer user = new Buyer();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            users.add(user);
        }
        return  users;
    }

    public Buyer findByLoginAndPassword(String login, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Buyer user = new Buyer();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
        return null;
    }

    public Buyer findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Buyer user = new Buyer();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
        return null;
    }

    @Override
    public void update(Buyer data) {
        PreparedStatement preparedStatement = null;
        try {
            startTransaction();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, data.getLogin());
            preparedStatement.setString(2, data.getPassword());
            preparedStatement.setString(3, data.getFirstName());
            preparedStatement.setString(4, data.getLastName());
            preparedStatement.setInt(5, data.getId());
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
    public void delete(Buyer data)  {
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
