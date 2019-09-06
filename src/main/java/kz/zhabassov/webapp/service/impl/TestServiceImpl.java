package kz.zhabassov.webapp.service.impl;

import kz.zhabassov.webapp.dao.AnswerDAO;
import kz.zhabassov.webapp.dao.DAOFactory;
import kz.zhabassov.webapp.dao.QuestionDAO;
import kz.zhabassov.webapp.dao.TestDAO;
import kz.zhabassov.webapp.entity.Answer;
import kz.zhabassov.webapp.entity.Question;
import kz.zhabassov.webapp.entity.Test;
import kz.zhabassov.webapp.service.TestService;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestServiceImpl implements TestService {

    @Override
    public Test create(Test test) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        TestDAO testDAO = daoFactory.getTestDAO();
        if (testDAO.selectWhere(test.getTestName(), test.getTeacherName()) != null) {
            return null;
            //log test already exist
        } else {
            test.setTestPublished(true);
            testDAO.insert(test);
            return test;
        }
    }

    @Override
    public void publish(Test test) throws Exception {
        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDAO();
        AnswerDAO answerDAO = daoFactory.getAnswerDAO();
        String str = new String();
        for (Question q:test.getQuestions()) {
            str=q.getQuestionText();
            questionDAO.insert(q);
            answerDAO.insert(q.getCorrectAnswer());
            for (Answer a: q.getAnswers()) {
                answerDAO.insert(a);
            }
        }
    }

    @Override
    public ArrayList<Test> getTests() throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        TestDAO testDAO = daoFactory.getTestDAO();
        ArrayList<Test> tests = new ArrayList<>();
        tests.addAll(testDAO.select());
        return tests;
    }

    @Override
    public Test getTest(Test test) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDAO();
        AnswerDAO answerDAO = daoFactory.getAnswerDAO();
//        test.setQuestions(questionDAO.select(test));
        for (int i = 0; i < test.getQuestions().length ; i++) {
            test.getQuestions()[i]=questionDAO.select(test)[i];
            test.getQuestions()[i].setCorrectAnswer(answerDAO.select(test.getTestName(),
                    test.getTeacherName(),test.getQuestions()[i].getQuestionText(),true)[0]);
            for (int j = 0; j < 4; j++) {
                test.getQuestions()[i].getAnswers()[j] = answerDAO.select(test.getTestName(),
                        test.getTeacherName(),test.getQuestions()[i].getQuestionText(),false)[j];
            }
        }
        return test;
    }

    @Override
    public int getMark(String testName,String teacherName, String[] questionText, String[] selectedAnswer) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AnswerDAO answerDAO = daoFactory.getAnswerDAO();
        int markValue = 0;
        for (int i = 0; i < questionText.length; i++) {
           if(answerDAO.select(testName, teacherName, questionText[i],selectedAnswer[i]))
           {
               markValue++;
           }
        }
        return markValue;
    }
}
