package kz.zhabassov.webapp.dao;

import kz.zhabassov.webapp.entity.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TestDAO {
    Test selectWhere(String value);

    Test selectWhere(String value1, String value2);

    Test selectWhere(String value1, int value2);

    boolean insert(Test test) throws SQLException;

    Test update(Test test);

    boolean delete(Test test);

    ArrayList<Test> select() throws SQLException;
}
