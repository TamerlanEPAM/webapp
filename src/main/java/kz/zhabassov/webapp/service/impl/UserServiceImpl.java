package kz.zhabassov.webapp.service.impl;

import kz.zhabassov.webapp.dao.DAOFactory;
import kz.zhabassov.webapp.dao.UserDAO;
import kz.zhabassov.webapp.entity.User;
import kz.zhabassov.webapp.service.UserService;

import static kz.zhabassov.webapp.util.StringUtil.isNullOrEmpty;

public class UserServiceImpl implements UserService {
    private static final String LOGIN = "^[a-zA-Z_0-9]{3,45}$";
    private static final String PASSWORD = "^[a-zA-Z_!@#%0-9]{8,16}$";

    private static boolean isValidLogin(String username){
        return !isNullOrEmpty(username)&&username.matches(LOGIN);
    }
    private static boolean isValidPassword(String password){
        return !isNullOrEmpty(password)&&password.matches(PASSWORD);
    }
    private static boolean isPaswordsEqual(String password, String passwordRepeat){
        return password.equals(passwordRepeat);
    }

    @Override
    public User register(String login, String password, String passwordRepeat, String role) throws Exception {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        if(!isValidLogin(login)){
            try {
                throw new Exception("Login is not valid");
            } catch (Exception e) {

            }
        }
        if(!isValidPassword(password)){
            try {
                throw new Exception("Password is not valid");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!isPaswordsEqual(password,passwordRepeat)){
            try {
                throw new Exception("Passwords are not equal");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(userDAO.isLoginExist(login)){

            //throw new Exception("Warning the login already exists");
        }
        User user = new User(login, password, role);
        try{
            user = userDAO.insert(user);
        }catch (Exception e){
            throw new Exception("is not registered");
        }finally {
            return user;
        }
    }

    @Override
    public User login(String login, String password) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = (User) userDAO.select(login);
        if(user.getPassword().equals(password)){
            return user;
        }else {
            return null;
        }

    }

    @Override
    public User updatePassword(String login, String oldPassword, String newPassword, String repeatNewPassword) throws Exception {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        if(!isValidPassword(newPassword)){
            try {
                throw new Exception("Password is not valid");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!isPaswordsEqual(newPassword,repeatNewPassword)){
            try {
                throw new Exception("Passwords are not equal");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(userDAO.isLoginExist(login)){

            //throw new Exception("Warning the login already exists");
        }
        User user = null;
        try{
            user = userDAO.update(login,oldPassword,newPassword,repeatNewPassword);
        }catch (Exception e){
            throw new Exception("is not registered");
        }finally {
            return user;
        }

    }

    @Override
    public boolean register(String login, String password, String passwordRepeat) {
        return false;
    }

    @Override
    public boolean deleteProfile(User user) throws Exception {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        boolean deleted = false;
        if(userDAO.isLoginExist(user.getUsername())){
            deleted= userDAO.delete(user) ;
        }
        return deleted;
    }
}
