package kz.zhabassov.webapp.dao;

import kz.zhabassov.webapp.dao.impl.AnswerDAOImpl;
import kz.zhabassov.webapp.dao.impl.QuestionDAOImpl;
import kz.zhabassov.webapp.dao.impl.TestDAOImpl;
import kz.zhabassov.webapp.dao.impl.UserDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private UserDAO userDAO = new UserDAOImpl();
    private TestDAO testDAO = new TestDAOImpl();
    private QuestionDAO questionDAO = new QuestionDAOImpl();
    private AnswerDAO answerDAO = new AnswerDAOImpl();


    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }


    public TestDAO getTestDAO() {
        return testDAO;
    }

    public QuestionDAO getQuestionDAO(){return questionDAO;}

    public AnswerDAO getAnswerDAO() {return answerDAO;}
}
