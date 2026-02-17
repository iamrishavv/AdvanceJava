package AdvanceJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;
//=============
//Requirement :
//=============
//Develop JDBC application to increase employees salary based on Department.
//Read Hike Percentage for Each Department from Keyboard and then update salary with given percentage.
//Formula :   New_Salary = existing_sal + (existing_Sal * hike_percentage) / 100;
public class q5 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        try {
            // 1️⃣ Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2️⃣ Create Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/advjdb?useSSL=false&serverTimezone=UTC",
                    "root",
                    "Rishav123@"
            );
            // 3️⃣ Read inputs
            System.out.print("Enter Department: ");
            String dept = sc.nextLine();
            System.out.print("Enter Hike Percentage: ");
            double hike = sc.nextDouble();
            // 4️⃣ Update salary using formula
            String sql =
                    "UPDATE EMPLOYEE " +
                            "SET EMP_SALARY = EMP_SALARY + (EMP_SALARY * ? / 100) " +
                            "WHERE EMP_DEPT = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, hike);
            ps.setString(2, dept);
            // 5️⃣ Execute update
            int rows = ps.executeUpdate();

            System.out.println(rows + " employee(s) salary updated successfully.");

            con.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
