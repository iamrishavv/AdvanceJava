package AdvanceJava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
//Requirement : Develop JDBC application to read EMP_ID from Keyboard and then retrieve emp data along with address based on given emp_id from Database table.
public class q11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter EMP ID: ");
        int empId = sc.nextInt();

        String sql =
                "SELECT e.EMP_ID, e.EMP_NAME, e.EMP_SALARY, " +
                        "a.CITY, a.STATE, a.COUNTRY " +
                        "FROM EMP e JOIN EMP_ADDRESS a " +
                        "ON e.EMP_ID = a.EMP_ID " +
                        "WHERE e.EMP_ID = ?";

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/advjdb",
                    "root",
                    "Rishav123@"
            );

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("EMP ID     : " + rs.getInt("EMP_ID"));
                System.out.println("EMP NAME   : " + rs.getString("EMP_NAME"));
                System.out.println("EMP SALARY : " + rs.getInt("EMP_SALARY"));
                System.out.println("CITY       : " + rs.getString("CITY"));
                System.out.println("STATE      : " + rs.getString("STATE"));
                System.out.println("COUNTRY    : " + rs.getString("COUNTRY"));
            } else {
                System.out.println("No employee found with EMP_ID " + empId);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
