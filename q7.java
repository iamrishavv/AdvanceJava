package AdvanceJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//2) JDBC program to execute SQL query with IN clause (worklocation : 'Hyd' and 'Pune' )
//Ans) select * from employees where work_location IN ('hyd', 'pune');
public class q7 {
    public static void main(String[] args) throws Exception{
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/advjdb",
                "root",
                "Rishav123@"
        );

        String sql = "SELECT * FROM employee WHERE work_location IN (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, "Hyd");
        ps.setString(2, "Delhi");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " " +
                            rs.getString(2) + " " +
                            rs.getString("work_location")
            );
        }

        con.close();
    }
}
