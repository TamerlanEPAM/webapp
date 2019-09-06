package kz.zhabassov.webapp.service;

import kz.zhabassov.webapp.entity.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TestService {
    Test create(Test test) throws Exception;
    void publish(Test test) throws Exception;
    ArrayList<Test> getTests() throws SQLException;
    Test getTest(Test test) throws SQLException;

    int getMark(String testName, String teacherName, String[] questionText, String[] selectedAnswer) throws SQLException;
}
