package com.geektime.week05.db;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HikariTest {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/demo");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);

        Connection conn = ds.getConnection();;
        Statement stat = null;

        // 执行查询
        stat = conn.createStatement();

        String sqlInsert = "INSERT INTO `user` (`name`, `age`) VALUES ('h用户', '87');";
        stat.execute(sqlInsert);

        conn.close();
    }
}
