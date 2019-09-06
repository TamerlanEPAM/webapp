package kz.zhabassov.webapp.dao;

import kz.zhabassov.webapp.entity.Entity;
import kz.zhabassov.webapp.entity.User;

import java.sql.SQLException;

public interface UserDAO {
    User insert(User user) throws SQLException;
    boolean isLoginExist(String login) throws Exception;
    Entity select(int id);
    Entity select(String name);
    Entity selectWhere(String name, String value);
    void update(User updatedUser);
    boolean delete(User user);

    User update(String login, String oldPassword, String newPassword, String repeatNewPassword) throws SQLException;
}
