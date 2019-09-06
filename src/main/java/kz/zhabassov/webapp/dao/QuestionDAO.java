package kz.zhabassov.webapp.dao;

import kz.zhabassov.webapp.entity.Question;
import kz.zhabassov.webapp.entity.Test;

import java.sql.SQLException;

public interface QuestionDAO {
    Question[] select(Test test) throws SQLException;
    void insert(Question question) throws SQLException;
    void update(Question question);
    void delete(Question question);
}
