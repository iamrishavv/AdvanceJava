package AdvanceJava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;
public class propertiesFileUse {
    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("db.properties");
        props.load(fis);

        Connection con = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password")
        );

        System.out.println("Connected successfully");
        con.close();
    }
}
