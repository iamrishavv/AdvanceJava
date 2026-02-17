package AdvanceJava;
//A stored procedure with IN and OUT parameters accepts input values from the caller and returns processed output values back to the caller throughOUT parameters.
//here we are retrieving only one column based on input(Book name).
import java.sql.*;
import java.util.Scanner;

public class ProceduresWithInAndOutParam {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/advjdb";

    private static final String DB_UNAME = "root";

    private static final String DB_PWD = "Rishav123@";

    private static final String PROCEDURE = "call getBookNameByPrice(?, ?)";
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book Price :: ");
        double bookPrice = sc.nextDouble();

        Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);

        CallableStatement cstmt =
                con.prepareCall("{CALL getBookNameByPrice(?, ?)}");

        // IN parameter
        cstmt.setDouble(1, bookPrice);

        // OUT parameter
        cstmt.registerOutParameter(2, Types.VARCHAR);//book name datatype.

        // execute procedure
//        ResultSet rs = cstmt.executeQuery();
//        while(rs.next()){
//            System.out.println(rs.getString(1));
//        }
        // read OUT parameter (✅ THIS IS THE FIX)
        String bookName = cstmt.getString(2);

        System.out.println("Book Name :: " + bookName);

        con.close();

    }
}
