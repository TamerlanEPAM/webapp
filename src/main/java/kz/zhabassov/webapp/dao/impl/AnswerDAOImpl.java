package kz.zhabassov.webapp.dao.impl;

import kz.zhabassov.webapp.dao.AnswerDAO;
import kz.zhabassov.webapp.entity.Answer;
import kz.zhabassov.webapp.pool.ConnectionPool;
import kz.zhabassov.webapp.pool.PooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerDAOImpl implements AnswerDAO {
    private static final String SQL_INSERT_ANSWER = "INSERT INTO \"answer\" (answer_text,question_text, test_name, teacher_name, is_right) VALUES (?,?,?,?,?);";
    private static final String SQL_SELECT_ANSWER = "SELECT * FROM  \"answer\" WHERE test_name = (?) AND teacher_name = (?) AND question_text=(?) AND  is_right=(?);";
    private static final String SQL_SELECT_IS_RIGHT = "SELECT * FROM  \"answer\" WHERE test_name = (?) AND teacher_name = (?) AND question_text=(?) AND answer_text=(?);";
    private ConnectionPool conn = ConnectionPool.getInstance();

    @Override
    public void insert(Answer answer) throws SQLException {
        PooledConnection connection = null;
        PreparedStatement addAnswer = null;
        try {
            connection = conn.getConnection();
            connection.setAutoCommit(true);
            addAnswer = connection.prepareStatement(SQL_INSERT_ANSWER);
            addAnswer.setString(1,answer.getAnswerText());
            addAnswer.setString(2, answer.getQuestionText());
            addAnswer.setString(3, answer.getTestName());
            addAnswer.setString(4, answer.teacherName());
            addAnswer.setBoolean(5, answer.isRight());
            addAnswer.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (addAnswer != null) {
                addAnswer.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Answer[] select(String testName, String teacherName, String questionText, boolean isRight) throws SQLException {
        PooledConnection connection = null;
        PreparedStatement getAnswers = null;
        int answerNumber = 0;
        Answer[] answers = new Answer[4];
        try {
            connection = conn.getConnection();
            getAnswers = connection.prepareStatement(SQL_SELECT_ANSWER);
            getAnswers.setString(1, testName);
            getAnswers.setString(2, teacherName);
            getAnswers.setString(3, questionText);
            getAnswers.setBoolean(4, isRight);
            try (ResultSet rs = getAnswers.executeQuery()) {
                while (rs.next()){
                    Answer answer = new Answer();
                    answer.setTestName(testName);
                    answer.setTeacherNmae(teacherName);
                    answer.setQuestionText(questionText);
                    answer.setAnswerText(rs.getString("answer_text"));
                    answer.setRight(isRight);

                    answers[answerNumber++] = answer;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getAnswers != null) {
                getAnswers.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return answers;
    }

    @Override
    public void update(Answer answer) {

    }

    @Override
    public void delete(Answer answer) {

    }

    @Override
    public boolean select(String testName,String teacherName, String questionText, String selectedAnswer) throws SQLException {
        PooledConnection connection = null;
        PreparedStatement getIsRight = null;
        boolean isRight = false;
        try {
            connection = conn.getConnection();
            getIsRight = connection.prepareStatement(SQL_SELECT_IS_RIGHT);
            getIsRight.setString(1, testName);
            getIsRight.setString(2, teacherName);
            getIsRight.setString(3, questionText);
            getIsRight.setString(4, selectedAnswer);
            try (ResultSet rs = getIsRight.executeQuery()) {
                while (rs.next()){
                    isRight = (rs.getBoolean("is_right"));
                }
            }catch (Exception e){
                //log there is no answers like this error
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getIsRight != null) {
                getIsRight.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isRight;
    }
}
