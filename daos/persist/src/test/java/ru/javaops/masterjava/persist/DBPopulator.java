package ru.javaops.masterjava.persist;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;

import java.io.Reader;
import java.sql.Connection;

/**
 * Created by Restrictor on 14.11.2016.
 */
public abstract class DBPopulator {

    public static void populate() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        Reader reader;

        try {
            Connection conn = sqlSession.getConnection();
            reader = Resources.getResourceAsReader("populateDB.sql");

            ScriptRunner runner = new ScriptRunner(conn);
            runner.setLogWriter(null);
            runner.runScript(reader);
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            sqlSession.close();
        }
    }

}
