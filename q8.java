package AdvanceJava;
import java.sql.*;

public class q8 {
    public static void main(String[] args)throws Exception {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/advjdb",
                "root",
                "Rishav123@"
        );

        String sql = "SELECT * FROM employee WHERE joining_date BETWEEN ? AND ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setDate(1, Date.valueOf("2025-01-01"));
        ps.setDate(2, Date.valueOf("2025-12-31"));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " " +
                            rs.getString(2) + " " +
                            rs.getDate("joining_date")
            );
        }

        con.close();
    }
}
