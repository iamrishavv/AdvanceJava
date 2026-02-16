package AdvanceJava;
//Types:
//1) TYPE_FORWARD_ONLY (by default)
//for frwd backwd and skipp also.
//2) TYPE_SCROLL_INSENSITIVE(👉 Does NOT reflect database changes after ResultSet is created.Most used)
//3) TYPE_SCROLL_SENSITIVE(👉 Reflects database changes made after ResultSet creation.)
//it works on create statement
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class resultSetType {
    public static void main(String[] args) throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advjdb", "root", "Rishav123@");

            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                             //  ResultSet.CONCUR_READ_ONLY);
                                               ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("SELECT * FROM BOOKS");

            System.out.println("---- Forward Direction (INSENSITIVE) ----");
//
//            rs.absolute(2);
//            rs.updateDouble(3,5500);
//            rs.updateRow();
//            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
//            rs.previous();
//            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
//            con.close();
            System.out.println("---- FIRST READ ----");
            while (rs.next()) {
                System.in.read();
                rs.refreshRow();
                System.out.println(
                        rs.getInt("BOOK_ID") + " " +
                                rs.getString("BOOK_NAME") + " " +
                                rs.getInt("BOOK_PRICE")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
