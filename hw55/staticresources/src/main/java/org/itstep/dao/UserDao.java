package org.itstep.dao;

import org.itstep.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends GenericDao<User> {

    private static final String INSERT = "INSERT INTO users(login, password) VALUES(?, ?)";
    private static final String SELECT_ALL = "SELECT id, login, password FROM users";
    private static final String SELECT = "SELECT id, login, password FROM users WHERE login = ? AND password = ?";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE = "UPDATE users SET login = ?, password = ? WHERE id = ?";

    public UserDao(String connectionString, String user, String password) throws SQLException {
        super(connectionString, user, password);
    }

    @Override
    public boolean save(User data) {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, data.getLogin());
            preparedStatement.setString(2, data.getPassword());
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
    public List<User> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<User> users = new ArrayList<User>();
        while(resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);
        }
        return  users;
    }
    @Override
    public User findByLoginAndPassword(String login, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
        return null;
    }

    @Override
    public void update(User data) {
        PreparedStatement preparedStatement = null;
        try {
            startTransaction();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, data.getLogin());
            preparedStatement.setString(2, data.getPassword());
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
    public void delete(User data)  {
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
