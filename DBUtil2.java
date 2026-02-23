package com.example.util;
import java.sql.Connection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
public class DBUtil2 {
        private static final HikariDataSource dataSource;

        static {
            try {
                HikariConfig config = new HikariConfig();

                config.setJdbcUrl(
                        "jdbc:mysql://localhost:3306/userdb?useSSL=false&serverTimezone=UTC"
                );
                config.setUsername("root");
                config.setPassword("Rishav123@");

                // IMPORTANT
                config.setDriverClassName("com.mysql.cj.jdbc.Driver");

                // Pool settings
                config.setMaximumPoolSize(10);
                config.setMinimumIdle(2);
                config.setConnectionTimeout(30000);
                config.setIdleTimeout(600000);

                dataSource = new HikariDataSource(config);

            } catch (Exception e) {
                throw new RuntimeException("HikariCP initialization failed", e);
            }
        }

        public static Connection getConnection() throws Exception {
            return dataSource.getConnection();
        }

}
