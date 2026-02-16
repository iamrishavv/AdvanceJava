package AdvanceJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//PreparedStatement is a JDBC interface used to execute parameterized SQL queries.
//👉 The SQL query is compiled once and executed multiple times with different values.
public class preparedStatement {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/advjdb",
                    "root",
                    "Rishav123@"
            );
            // ================= INSERT =================
            PreparedStatement psInsert = con.prepareStatement("INSERT INTO STUDENTS (ID, NAME, MARKS) VALUES (?,?,?)");
            psInsert.setInt(1, 1);
            psInsert.setString(2, "RISHAV");
            psInsert.setInt(3, 650);

            int rows = psInsert.executeUpdate();
            System.out.println(rows + " row inserted");

            // ================= UPDATE =================
            PreparedStatement psUpdate = con.prepareStatement("UPDATE STUDENTS SET MARKS=? WHERE ID=?");
            psUpdate.setInt(1, 650);//marks
            psUpdate.setInt(2, 1);//index

            int rows1 = psUpdate.executeUpdate();
            System.out.println(rows1 + " row updated");

         //    ================= DELETE =================
//            PreparedStatement psDelete = con.prepareStatement(
//                    "DELETE FROM STUDENTS WHERE ID=?"
//            );
//            psDelete.setInt(1, 1);
//
//            int rows3 = psDelete.executeUpdate();
//            System.out.println(rows3 + " row deleted");

            con.close();


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
