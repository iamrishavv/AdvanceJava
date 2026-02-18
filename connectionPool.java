package AdvanceJava;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Connection pooling is a technique where a set of database connections is created once, reused multiple times, and managed by a pool, instead of creating a new connection for every request.
//hikari is 3rd party library in jdbc for client side server pool.
public class connectionPool {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/advjdb";
    private static final String DB_UNAME = "root";
    private static final String DB_PWD = "Rishav123@";
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_UNAME);
        config.setPassword(DB_PWD);

        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        //connection pool
        HikariDataSource datasource = new HikariDataSource(config);

        Connection con = datasource.getConnection();

        String sql = "INSERT INTO BOOKS VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, 103);
        ps.setString(2, "MicroServices");
        ps.setDouble(3, 5500.00);

        ps.executeUpdate();

        System.out.println("RECORD INSERTED.....");

        ps.close();
        con.close();
        datasource.close();
    }
}
