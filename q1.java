package AdvanceJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Requirement : Write a java program to retrieve all the records from the database table and display on the console.
//ResultSet is used to store and process data returned by SELECT queries, while executeUpdate is used for non-select operations and returns the number of affected rows instead of data
public class q1 {
    public static void main(String[] args) {
        try {
            // 1️⃣ Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2️⃣ Get Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/advjdb",
                    "root",
                    "Rishav123@"
            );

            // 3️⃣ Create Statement
            Statement st = con.createStatement();

            // 4️⃣ Execute SELECT Query
            ResultSet rs = st.executeQuery("SELECT * FROM BOOKS");

            // 5️⃣ Display Records
            //
            while (rs.next()) {
                int id = rs.getInt("BOOK_ID");
                String name = rs.getString("BOOK_NAME");
                int price = rs.getInt("BOOK_PRICE");

                System.out.println(id + " | " + name + " | " + price);
            }

            // 6️⃣ Close Connection
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
