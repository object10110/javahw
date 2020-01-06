package org.itstep.dao;

import org.itstep.domain.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends GenericDao<User> {

    private static final String INSERT = "INSERT INTO users(login, password) VALUES(?, ?)";
    private static final String SELECT = "SELECT id, login, password from users";

    public UserDao(String connectionString, String user, String password) throws SQLException {
        super(connectionString, user, password);
    }

    @Override
    public void save(User data) {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, data.getLogin());
            preparedStatement.setString(2, data.getPassword());
            preparedStatement.execute();
            commit();
        } catch (SQLException e) {
            try {
                rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void update(User data) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void delete(User data) {

    }
}
