package kz.zhabassov.webapp.dao;

import kz.zhabassov.webapp.entity.Answer;

import java.sql.SQLException;

public interface AnswerDAO {
    void insert(Answer answer) throws SQLException;
    Answer[] select(String testName, String teacherName, String questionText, boolean isRight) throws SQLException;
    void update(Answer answer);
    void delete(Answer answer);

    boolean select(String testName, String teacherName, String questionText, String selectedAnswer) throws SQLException;
}
