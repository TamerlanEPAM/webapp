package kz.zhabassov.webapp.dao.impl;

import kz.zhabassov.webapp.dao.QuestionDAO;
import kz.zhabassov.webapp.entity.Question;
import kz.zhabassov.webapp.entity.Test;
import kz.zhabassov.webapp.pool.ConnectionPool;
import kz.zhabassov.webapp.pool.PooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDAOImpl implements QuestionDAO {
    private ConnectionPool conn = ConnectionPool.getInstance();
    private static final String SQL_INSERT_QUESTION = "INSERT INTO \"question\" (question_text, test_name, teacher_name) VALUES (?,?,?);";
    private static final String SQL_SELECT_QUESTION = "SELECT * FROM \"question\" WHERE test_name=(?) AND teacher_name=(?);";


    @Override
    public Question[] select(Test test) throws SQLException {
        PooledConnection connection = null;
        PreparedStatement getQuestions = null;
        Question[] questions = new Question[test.getQuestionQuantity()];
        int i = 0;
        try {
            connection = conn.getConnection();
            getQuestions = connection.prepareStatement(SQL_SELECT_QUESTION);
            getQuestions.setString(1, test.getTestName());
            getQuestions.setString(2, test.getTeacherName());
            try (ResultSet rs = getQuestions.executeQuery()) {
                while (rs.next()){
                    Question question = new Question(test);
                    question.setQuestionText(rs.getString("question_text"));
                    questions[i++] = question;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getQuestions != null) {
                getQuestions.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return questions;
    }

    @Override
    public void insert(Question question) throws SQLException {
        PooledConnection connection = null;
        PreparedStatement addQuestion = null;
        try {
            connection = conn.getConnection();
            connection.setAutoCommit(true);
            addQuestion = connection.prepareStatement(SQL_INSERT_QUESTION);
            addQuestion.setString(1, question.getQuestionText());
            addQuestion.setString(2, question.getTestName());
            addQuestion.setString(3, question.getTeacherName());
            addQuestion.executeUpdate();
//            addQuestion.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (addQuestion != null) {
                addQuestion.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public void update(Question question) {

    }

    @Override
    public void delete(Question question) {

    }
}
