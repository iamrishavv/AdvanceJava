package AdvanceJava;

import java.sql.*;
import java.util.Scanner;

public class q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/advjdb?useSSL=false&serverTimezone=UTC",
                    "root",
                    "Rishav123@"
            );
            // Read inputs
            System.out.print("Enter Work Location (press Enter to skip): ");
            String location = sc.nextLine().trim();

            System.out.print("Enter Department (press Enter to skip): ");
            String dept = sc.nextLine().trim();

            System.out.print("Enter Gender (press Enter to skip): ");
            String gender = sc.nextLine().trim();
            // normalize "null" input-> if we want to write as hyd-null-null as  input.
            if (location.equalsIgnoreCase("null")) location = "";
            if (dept.equalsIgnoreCase("null")) dept = "";
            if (gender.equalsIgnoreCase("null")) gender = "";
            // Base query
            StringBuilder sql = new StringBuilder(
                    "SELECT * FROM EMPLOYEE WHERE 1=1"
            );
            //Explanation
            //location.isEmpty() check:
            //Did the user press Enter without typing anything?
            //!location.isEmpty() means:
            //User entered a value
            // Add conditions dynamically
            if (!location.isEmpty()) {
                sql.append(" AND WORK_LOCATION = ?");
            }
            if (!dept.isEmpty()) {
                sql.append(" AND EMP_DEPT = ?");
            }
            if (!gender.isEmpty()) {
                sql.append(" AND EMP_GENDER = ?");
            }
            //Converts the dynamically built SQL into a PreparedStatement
            //sql.toString() converts StringBuilder to String
            PreparedStatement ps = con.prepareStatement(sql.toString());

            // Set values dynamically
            //if not typed then
            int index = 1;

            if (!location.isEmpty()) {
                ps.setString(index++, location);
            }
            if (!dept.isEmpty()) {
                ps.setString(index++, dept);
            }
            if (!gender.isEmpty()) {
                ps.setString(index++, gender);
            }
            //Sends the SQL + values to database
            //Returns matching rows
            //Stores them in ResultSet
            ResultSet rs = ps.executeQuery();

            System.out.println("\nEMP_ID | NAME | SALARY | DEPT | GENDER | LOCATION");
            System.out.println("--------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("EMP_ID") + " | " +
                                rs.getString("EMP_NAME") + " | " +
                                rs.getInt("EMP_SALARY") + " | " +
                                rs.getString("EMP_DEPT") + " | " +
                                rs.getString("EMP_GENDER") + " | " +
                                rs.getString("WORK_LOCATION")
                );
            }

            if (!found) {
                System.out.println("No employee found for given criteria.");
            }

            con.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
