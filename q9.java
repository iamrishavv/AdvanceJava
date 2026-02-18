package AdvanceJava;
//Insert 3 records using Batch Operation + PreparedStatement
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class q9 {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/advjdb",
                "root",
                "Rishav123@"
        );

        String sql = "INSERT INTO EMP (EMP_ID, EMP_NAME, EMP_SALARY) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        // 1st record
        ps.setInt(1, 1);
        ps.setString(2, "John");
        ps.setInt(3, 20000);
        ps.addBatch();

        // 2nd record
        ps.setInt(1, 2);
        ps.setString(2, "Smith");
        ps.setInt(3, 25000);
        ps.addBatch();

        // 3rd record
        ps.setInt(1, 3);
        ps.setString(2, "Anil");
        ps.setInt(3, 18000);
        ps.addBatch();

        ps.executeBatch();

        System.out.println("3 records inserted using batch operation");

        con.close();
    }
}
