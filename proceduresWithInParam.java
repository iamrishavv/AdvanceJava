package AdvanceJava;
//A stored procedure with IN parameter accepts input values from the caller and executes database logic based on those values, and it is invoked from Java using CallableStatement.
//A stored procedure with an IN parameter accepts input values from the caller, uses those values to execute database logic, and does not return values through the parameter.
//here we are retrieving all the columns
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.Scanner;

public class proceduresWithInParam {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/advjdb";

    private static final String DB_UNAME = "root";

    private static final String DB_PWD = "Rishav123@";

    private static final String PROCEDURE = "call getBookById(?)";

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Book Id :: ");
        int bookId = s.nextInt();

        Connection con  = DriverManager.getConnection(DB_URL,DB_UNAME,DB_PWD);

        CallableStatement cstmt = con.prepareCall(PROCEDURE);
        //in procedure with param.
        cstmt.setInt(1, bookId);

        ResultSet rs = cstmt.executeQuery();

        while(rs.next()) {
            System.out.println(rs.getInt(1) + "-"+rs.getString(2)+"-"+rs.getDouble(3));
        }

    }
}
