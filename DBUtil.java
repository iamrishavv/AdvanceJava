package com.example.util;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
public class DBUtil {
    private static final HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();

            // ✅ CORRECT JDBC URL (MATCH YOUR DB)
            config.setJdbcUrl("jdbc:mysql://localhost:3306/advjdb");

            config.setUsername("root");
            config.setPassword("Rishav123@"); // change if needed

            // ✅ EXPLICIT DRIVER (IMPORTANT)
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setConnectionTimeout(20000);

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize HikariCP", e);
        }
    }

    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
}
