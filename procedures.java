package AdvanceJava;
//A stored procedure without parameters can be executed from Java using CallableStatement, and if it contains a SELECT statement, the result is retrieved using ResultSet.
//1️⃣ Performance (MOST IMPORTANT)
//SQL inside a procedure is compiled once
//Executed many times
//Less parsing & planning every time
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class procedures {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/advjdb";
    private static final String DB_UNAME = "root";
    private static final String DB_PWD = "Rishav123@";
    private static final String PROCEDURE = "call getBooksData()";
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
        //with procedure using prepare call.
        CallableStatement cstmt = con.prepareCall(PROCEDURE);

        ResultSet rs = cstmt.executeQuery();

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + "-" +
                            rs.getString(2) + "-" +
                            rs.getDouble(3)
            );
        }

        con.close();
    }
}
