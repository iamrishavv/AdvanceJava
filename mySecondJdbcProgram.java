package AdvanceJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//no select query(DML) queries(Create,Insert,Update,Delete).
//it will change in sql directly.
public class mySecondJdbcProgram {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/advjdb",
                    "root",
                    "Rishav123@"
            );

            Statement st1 = con.createStatement();

            // Non-select query
            //create RUNS ONLY ONE TIME
//            String sql = "CREATE TABLE STUDENTS (" +
//                    "ID INT, " +
//                    "NAME VARCHAR(50), " +
//                    "MARKS INT)";
//
//            st1.executeUpdate(sql);
//
//            System.out.println("Table created successfully!");

            //insert
            int rows = st1.executeUpdate(
                    "INSERT INTO STUDENTS VALUES (1,'RISHAV',650)"
            );

            System.out.println(rows + " row inserted");
           // update
            int rows1 = st1.executeUpdate(
                    "UPDATE STUDENTS SET MARKS=750 WHERE ID=1"
            );
            System.out.println(rows1 + " row updated");
            //delete
            int rows3 = st1.executeUpdate(
                    "DELETE FROM STUDENTS WHERE ID=1"
            );
            System.out.println(rows3 + " row deleted");


            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
