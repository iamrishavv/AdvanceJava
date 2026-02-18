package AdvanceJava;
//Read Employee & Address data from Keyboard and insert into two tables
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class q10 {
    
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        // Read EMP data
        System.out.print("Enter Emp ID: ");
        int empId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Emp Name: ");
        String empName = sc.nextLine();

        System.out.print("Enter Emp Salary: ");
        int empSalary = sc.nextInt();
        sc.nextLine();

        // Read ADDRESS data
        System.out.print("Enter City: ");
        String city = sc.nextLine();

        System.out.print("Enter State: ");
        String state = sc.nextLine();

        System.out.print("Enter Country: ");
        String country = sc.nextLine();

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/advjdb",
                "root",
                "Rishav123@"
        );

        // Insert into EMP table
        String empSql = "INSERT INTO EMP VALUES (?, ?, ?)";
        PreparedStatement empPs = con.prepareStatement(empSql);
        empPs.setInt(1, empId);
        empPs.setString(2, empName);
        empPs.setInt(3, empSalary);
        empPs.executeUpdate();

        // Insert into EMP_ADDRESS table
        String addrSql = "INSERT INTO EMP_ADDRESS VALUES (?, ?, ?, ?)";
        PreparedStatement addrPs = con.prepareStatement(addrSql);
        addrPs.setString(1, city);
        addrPs.setString(2, state);
        addrPs.setString(3, country);
        addrPs.setInt(4, empId);
        addrPs.executeUpdate();

        System.out.println("Employee and Address data inserted successfully");

        con.close();
    }
}
