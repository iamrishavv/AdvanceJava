package AdvanceJava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class batchOperationUsingPreparedStatement {
    public static void main(String[] args)throws Exception {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/advjdb",
                "root",
                "Rishav123@"
        );

        String sql = "INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_SALARY, EMP_DEPT, EMP_GENDER, WORK_LOCATION, JOINING_DATE) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

// 1st employee
        ps.setInt(1, 6);
        ps.setString(2, "Rahul");
        ps.setDouble(3, 25000);
        ps.setString(4, "HR");
        ps.setString(5, "Male");
        ps.setString(6, "Hyd");
        ps.setDate(7, java.sql.Date.valueOf("2022-05-10"));
        ps.addBatch();

// 2nd employee
        ps.setInt(1, 7);
        ps.setString(2, "Neha");
        ps.setDouble(3, 27000);
        ps.setString(4, "Admin");
        ps.setString(5, "FeMale");
        ps.setString(6, "Delhi");
        ps.setDate(7, java.sql.Date.valueOf("2023-01-15"));
        ps.addBatch();

        // execute batch
        int[] result = ps.executeBatch();

        System.out.println(result.length + " records inserted");

        con.close();
    }
}
