package com.geektime.week05.db;

import java.sql.*;

public class JdbcOrigin {
    static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stat = null;

        // 注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

        // 执行查询
        stat = conn.createStatement();

        String sqlInsert = "INSERT INTO `user` (`name`, `age`) VALUES ('无名', '88');";
        stat.execute(sqlInsert);

        String sql = "SELECT * FROM user";

        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()){
            System.out.print(rs.getInt("id")+",");
            System.out.print(rs.getString("name")+",");
            System.out.print(rs.getInt("age"));
            System.out.print("\n");
        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
