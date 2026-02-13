package AdvanceJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//code for select query(DQL) queries.
//for retrieval only
public class myFirstJdbcProgram {
    public static void main(String[] args) throws ClassNotFoundException {
        //(1)load driver
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        //(2) get connection
        try {
            // (1) Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // (2) Get connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/advjdb",
                    "root",
                    "Rishav123@"
            );

            System.out.println("Connected Successfully!");

            // (3) create statement
            Statement st = con.createStatement();
            //(4) execute query
            ResultSet rs = st.executeQuery("SELECT * FROM BOOKS");

            // (5) Display data/Result
            while (rs.next()) {
                System.out.println(rs.getInt("BOOK_ID") + " | " + rs.getString("BOOK_NAME") + " | " + rs.getInt("BOOK_PRICE"));
            }
            //(6) close the connection
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
