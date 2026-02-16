package AdvanceJava;

import java.sql.*;
import java.util.Scanner;

//=> Develop User Registration and User Login Functionality.
//Note: For Registration and Login read the data from keyboard.
//Note: We should not insert user record with duplicate email. If any user trying to register with duplicate email application should show error message.
public class q2 {

    static final String URL = "jdbc:mysql://localhost:3306/advjdb";
    static final String USER = "root";
    static final String PASS = "Rishav123@";
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            while (true) {

                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice:");
                int choice = sc.nextInt();
                sc.nextLine();

                // ================= REGISTER =================
                if (choice == 1) {

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    PreparedStatement check =
                            con.prepareStatement("SELECT * FROM USERS WHERE EMAIL=?");
                    check.setString(1, email);

                    ResultSet rs = check.executeQuery();

                    if (rs.next()) {
                        System.out.println("Email already registered");
                    } else {

                        PreparedStatement ps = con.prepareStatement(
                                "INSERT INTO USERS(NAME,EMAIL,PASSWORD) VALUES (?,?,?)"
                        );

                        ps.setString(1, name);
                        ps.setString(2, email);
                        ps.setString(3, password);

                        ps.executeUpdate();
                        System.out.println("✅ Registration Successful!");
                    }
                }

                // ================= LOGIN =================
                else if (choice == 2) {

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    PreparedStatement ps = con.prepareStatement(
                            "SELECT * FROM USERS WHERE EMAIL=? AND PASSWORD=?"
                    );

                    ps.setString(1, email);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("✅ Login Successful! Welcome " +
                                rs.getString("NAME"));
                    } else {
                        System.out.println(" Invalid credentials");
                    }
                }

                else if (choice == 3) {
                    System.out.println("Exiting...");
                    break;
                }
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
