package AdvanceJava;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
public class propertiesFile {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();

        FileInputStream fis = new FileInputStream("db.properties");

        props.load(fis);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String pwd = props.getProperty("db.password");

        System.out.println(url);
        System.out.println(user);
        System.out.println(pwd);
        fis.close();
    }
}
