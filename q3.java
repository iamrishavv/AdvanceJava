package AdvanceJava;

import java.sql.*;
import java.util.Scanner;

//=================================================================================
//Assignment : Develop JDBC application to retrieve books which are having price less than given price.
//
//=> Ask user to enter the price in keyboard, if user entered the price then we have to fetch books which are having price less than user given price and display to console
//
//=> If user don't enter price then fetch all books and display to console
//
//=================================================================================
public class q3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advjdb","root","Rishav123@");
            System.out.print("Enter price (press Enter to fetch all books): ");
            String input = sc.nextLine();
            PreparedStatement ps;
            ResultSet rs;
            // 3️⃣ If user entered price
            if (!input.isEmpty()) {
                int price = Integer.parseInt(input);
                ps = con.prepareStatement(
                        "SELECT * FROM BOOKS WHERE BOOK_PRICE <= ?"
                );
                ps.setInt(1, price);
                rs = ps.executeQuery();
            }
            // 4️⃣ If user did NOT enter price
            else {
                ps = con.prepareStatement(
                        "SELECT * FROM BOOKS"
                );
                rs = ps.executeQuery();
            }
            // 5️⃣ Display Results
            System.out.println("\nBOOK_ID | BOOK_NAME | BOOK_PRICE");
            System.out.println("--------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("BOOK_ID") + " | " +
                                rs.getString("BOOK_NAME") + " | " +
                                rs.getInt("BOOK_PRICE")
                );
            }
            if (!found) {
                System.out.println("No books found.");
            }
            con.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
