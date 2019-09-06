package kz.zhabassov.webapp.service;

import kz.zhabassov.webapp.entity.Entity;
import kz.zhabassov.webapp.entity.User;

public interface UserService extends BaseService {

    User register(String login, String password, String passwordRepeat, String role) throws Exception;

    Entity login(String login, String password);

    User updatePassword(String login, String oldPassword, String newPassword, String repeatNewPassword) throws Exception;

    boolean register(String login, String password, String passwordRepeat);

    boolean deleteProfile(User user) throws Exception;
}
