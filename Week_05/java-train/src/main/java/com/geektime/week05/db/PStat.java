package com.geektime.week05.db;

import java.sql.*;

public class PStat {
    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/demo";
        String user = "root";
        String password = "root";
        String sql = "insert into user(name,age) values(?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //注册数据库驱动
            Class.forName(driver);
            //取得数据库连接
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            //进行预编译，这里进行参数设置
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "p用户");
            pstmt.setInt(2, 32);
            pstmt.addBatch();

            pstmt.setString(1, "v用户");
            pstmt.setInt(2, 22);
            pstmt.addBatch();

            pstmt.executeBatch();

            sql = "select * from user where age<?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 30);
            //进行编译
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println(id + ":" + name + ":" + age);
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (rs != null) {//轻量级，创建和销毁rs所需要的时间和资源较小
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {//轻量级，创建和销毁rs所需要的时间和资源较小
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {//重量级，创建和销毁rs所需要的时间和资源较小
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
