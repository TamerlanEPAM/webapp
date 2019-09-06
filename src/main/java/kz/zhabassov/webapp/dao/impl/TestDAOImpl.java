package kz.zhabassov.webapp.dao.impl;

import kz.zhabassov.webapp.dao.TestDAO;
import kz.zhabassov.webapp.entity.Test;
import kz.zhabassov.webapp.pool.ConnectionPool;
import kz.zhabassov.webapp.pool.PooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestDAOImpl implements TestDAO {
    private static final String SQL_SELECT_ALL_WHERE_TEST_NAME_AND_TEACHER_NAME = "SELECT * FROM \"test\" WHERE test_name = ? AND teacher_name = ?;";
    private static final String SQL_INSERT_TEST = "INSERT INTO \"test\" (test_name, question_quantity, language, teacher_name, is_test_published) VALUES (?,?,?,?,?);";
    private static final String SQL_SELECT_NAME_TEACHER_TESTS= "SELECT * FROM \"test\"";

    private ConnectionPool conn = ConnectionPool.getInstance();

    @Override
    public Test selectWhere(String value) {
        return null;
    }

    @Override
    public Test selectWhere(String testName, String teacherName) {
        PooledConnection connection = null;
        PreparedStatement selectTest = null;
        int id = 0;
        int questionQuantity = 0;
        String language = null;
        boolean isTestPublished = false;
        Test test = null;
        ResultSet resultSet = null;
        try {
            connection = conn.getConnection();
            selectTest = connection.prepareStatement(SQL_SELECT_ALL_WHERE_TEST_NAME_AND_TEACHER_NAME);
            selectTest.setString(1, testName);
            selectTest.setString(2, teacherName);
            resultSet = selectTest.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("test_id");
                questionQuantity = resultSet.getInt("question_quantity");
                language = resultSet.getString("language");
                isTestPublished = resultSet.getBoolean("is_test_published");
                test = new Test(id, testName, questionQuantity, language, teacherName, isTestPublished);
            } else {
                // throw new SQLException("Error creation user with such login doesn't exist");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    @Override
    public Test selectWhere(String value1, int value2) {
        return null;
    }

    @Override
    public boolean insert(Test test) throws SQLException {
        PooledConnection connection = null;
        PreparedStatement addTest = null;
        boolean isInserted=false;
        try {
            connection = conn.getConnection();
            connection.setAutoCommit(false);
            addTest = connection.prepareStatement(SQL_INSERT_TEST, Statement.RETURN_GENERATED_KEYS);
            addTest.setString(1, test.getTestName());
            addTest.setInt(2, test.getQuestionQuantity());
            addTest.setString(3, test.getLanguage());
            addTest.setString(4, test.getTeacherName());
            addTest.setBoolean(5, test.isTestPublished());


            addTest.executeUpdate();

            try (ResultSet generatedKeys = addTest.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    test.setId(generatedKeys.getInt(1));
                } else {
                    //log
                    throw new SQLException("Error creation user: didn't get ID");
                }
            }
            connection.commit();
            isInserted=true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (addTest != null) {
                addTest.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isInserted;
    }

    @Override
    public Test update(Test test) {
        return null;
    }

    @Override
    public boolean delete(Test test) {
        return false;
    }

    @Override
    public ArrayList<Test> select() throws SQLException {
        PooledConnection connection = null;
        PreparedStatement getTests = null;
        ArrayList<Test> tests = new ArrayList<>();
        try {
            connection = conn.getConnection();
            getTests = connection.prepareStatement(SQL_SELECT_NAME_TEACHER_TESTS);

            try (ResultSet rs = getTests.executeQuery()) {
               while (rs.next()){
                   Test test = new Test();
                   test.setId(rs.getInt("test_id"));
                   test.setTestName(rs.getString("test_name"));
                   test.setQuestionQuantity(rs.getInt("question_quantity"));
                   test.setLanguage(rs.getString("language"));
                   test.setTeacherName(rs.getString("teacher_name"));
                   test.setTestPublished(rs.getBoolean("is_test_published"));
                   tests.add(test);
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getTests != null) {
                getTests.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return tests;
    }
}
