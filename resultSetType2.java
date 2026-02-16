package AdvanceJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class resultSetType2 {
    public static void main(String[] args) throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advjdb", "root", "Rishav123@");
            //make sensitive.
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                               ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery("SELECT * FROM BOOKS");
//
//            rs.absolute(2);
//            rs.updateDouble(3,6500);
//            rs.updateRow();
//            System.out.println(rs.getInt("BOOK_ID") + " " + rs.getString("BOOK_NAME") + " " + rs.getInt("BOOK_PRICE"));
//            rs.previous();
//            System.out.println(rs.getInt("BOOK_ID") + " " + rs.getString("BOOK_NAME") + " " + rs.getInt("BOOK_PRICE"));
//
            System.out.println("---- FIRST READ ----");
            while (rs.next()) {
                System.in.read();
                rs.refreshRow();
                System.out.println(
                        rs.getInt(1) + " " +
                                rs.getString(2) + " " +
                                rs.getDouble(3)
                );
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
