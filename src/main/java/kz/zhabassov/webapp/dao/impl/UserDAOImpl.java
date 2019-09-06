package kz.zhabassov.webapp.dao.impl;

import kz.zhabassov.webapp.dao.UserDAO;
import kz.zhabassov.webapp.entity.Entity;
import kz.zhabassov.webapp.entity.User;
import kz.zhabassov.webapp.pool.ConnectionPool;
import kz.zhabassov.webapp.pool.PooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOImpl implements UserDAO {
    private static final String SQL_ADD_NEW_USER = "INSERT INTO \"user\" (role, login, password) VALUES (?, ?, ?);";
    private static final String SQL_SELECT_ID_WHERE_LOGIN = "SELECT id FROM \"user\" WHERE login = (?) ;";
    private static final String SQL_SELECT_ALL_WHERE_LOGIN = "SELECT * FROM \"user\" WHERE login = ?;";
    private static final String SQL_UPDATE_USER = "UPDATE \"user\" SET password = (?) WHERE login = (?) AND password = (?);";
    private static final String SQL_DELETE_USER = "DELETE  FROM \"user\" WHERE login=(?)";


    private ConnectionPool conn = ConnectionPool.getInstance();


    @Override
    public User insert(User user) throws SQLException {
        PooledConnection connection = null;
        PreparedStatement addUser = null;
        try {
            connection = conn.getConnection();
            connection.setAutoCommit(false);
            addUser = connection.prepareStatement(SQL_ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS);
            addUser.setString(1, user.getRole());
            addUser.setString(2, user.getUsername());
            addUser.setString(3, user.getPassword());

            addUser.executeUpdate();

            try (ResultSet generatedKeys = addUser.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    //log
                    throw new SQLException("Error creation user: didn't get ID");
                }
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (addUser != null) {
                addUser.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return user;
    }

    public boolean isLoginExist(String login) throws Exception {
        boolean isLoginExist = false;
        PooledConnection connection = null;
        PreparedStatement serarchLogin = null;
        ResultSet resultSet = null;
        try {
            connection = conn.getConnection();
            serarchLogin = connection.prepareStatement(SQL_SELECT_ID_WHERE_LOGIN);
            serarchLogin.setString(1, login);
            resultSet = serarchLogin.executeQuery();
            isLoginExist = resultSet.next();

        } catch (Exception e) {
            throw new Exception("Error searching similar login", e);

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (serarchLogin != null) {
                serarchLogin.close();
            }
            if (connection != null) {
                connection.close();
            }
            return isLoginExist;
        }
    }


    @Override
    public Entity select(int id) {
        return null;
    }

    @Override
    public User select(String login) {
        PooledConnection connection;
        PreparedStatement selectPassword;
        int id;
        String password;
        String role;
        User user = null;
        ResultSet resultSet;
        try {
            connection = conn.getConnection();
            selectPassword = connection.prepareStatement(SQL_SELECT_ALL_WHERE_LOGIN);
            selectPassword.setString(1, login);
            resultSet = selectPassword.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
                password = resultSet.getString("password");
                role = resultSet.getString("role");
                user = new User(id, login, password, role);
            } else {
                throw new SQLException("Error creation user with such login doesn't exist");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Entity selectWhere(String name, String value) {
        return null;
    }

    @Override
    public void update(User updatedUser) {

    }

    @Override
    public boolean delete(User user) {
        PooledConnection connection;
        PreparedStatement deleteUser;
        boolean deleted = false;
        try {
            connection = conn.getConnection();
            deleteUser = connection.prepareStatement(SQL_DELETE_USER);
            deleteUser.setString(1, user.getUsername());

            deleteUser.executeUpdate();
            deleted = true;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public User update(String login, String oldPassword, String newPassword, String repeatNewPassword) throws SQLException {
        PooledConnection connection = null;
        PreparedStatement updateUser = null;
        User user = null;
        try {
            connection = conn.getConnection();
            updateUser = connection.prepareStatement(SQL_UPDATE_USER);
            updateUser.setString(1, newPassword);
            updateUser.setString(2, login);
            updateUser.setString(3, oldPassword);

            updateUser.executeUpdate();

            user = select(login);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (updateUser != null) {
                updateUser.close();
            }
            if (connection != null) {
                connection.close();
            }

            return user;
        }

    }
}
