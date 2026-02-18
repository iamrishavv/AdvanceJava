package AdvanceJava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
//A transaction is a group of SQL operations that are executed as one single unit of work.
//✔ Either all operations succeed
//✔ Or all operations fail
//This follows ACID properties.
//🔹 Why Transactions are Needed?
//Consider this scenario:
//Insert employee data → SUCCESS
//Insert employee address → FAIL
//❌ Database becomes inconsistent
//👉 To avoid this, we use transactions.
public class transaction {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/advjdb",
                    "root",
                    "Rishav123@"
            );

            // 1️⃣ Disable auto-commit
            con.setAutoCommit(false);

            // EMP data
            System.out.print("Enter Emp ID: ");
            int empId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Emp Name: ");
            String empName = sc.nextLine();

            System.out.print("Enter Emp Salary: ");
            int empSalary = sc.nextInt();
            sc.nextLine();

            // ADDRESS data
            System.out.print("Enter City: ");
            String city = sc.nextLine();

            System.out.print("Enter State: ");
            String state = sc.nextLine();

            System.out.print("Enter Country: ");
            String country = sc.nextLine();

            // Insert into EMP
            PreparedStatement ps1 =
                    con.prepareStatement("INSERT INTO EMP VALUES (?, ?, ?)");
            ps1.setInt(1, empId);
            ps1.setString(2, empName);
            ps1.setInt(3, empSalary);
            ps1.executeUpdate();

            // Insert into EMP_ADDRESS
            PreparedStatement ps2 =
                    con.prepareStatement("INSERT INTO EMP_ADDRESS VALUES (?, ?, ?, ?)");
            ps2.setString(1, city);
            ps2.setString(2, state);
            ps2.setString(3, country);
            ps2.setInt(4, empId);
            ps2.executeUpdate();

            // 2️⃣ Commit transaction
            con.commit();
            System.out.println("Transaction committed successfully");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred. Rolling back transaction.");
            // rollback handled below
            con.rollback();
        }
    }
}
