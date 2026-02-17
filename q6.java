package AdvanceJava;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//1) Insert Image into database table
//Ans) 	create table user (
//			user_id    INT  ,
//			user_name 	VARCHAR(100),
//			user_image BLOB
//		)
//use longblob for long data jpg.
public class q6 {
    public static void main(String[] args)  throws Exception{
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/advjdb",
                "root",
                "Rishav123@"
        );

        String sql = "INSERT INTO user VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, 1);
        ps.setString(2, "Rishav");

        FileInputStream fis = new FileInputStream(
                "C:/Users/Rishav/Desktop/Virat-Kohli-16.jpg"
        );

        ps.setBinaryStream(3, fis);

        ps.executeUpdate();
        System.out.println("Image inserted successfully");

        con.close();
    }

}
