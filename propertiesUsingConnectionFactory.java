package AdvanceJava;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
//❓ Why does it NOT have a main() method?
//Because ConnectionFactory is NOT a runnable program.
//It is a utility / helper class whose only job is to provide database connections.
//🧠 Simple Explanation
//main() → entry point of an application
//ConnectionFactory → support class
//It is used by other classes, not executed directly
//👉 That’s why no main() method is needed.
public class propertiesUsingConnectionFactory {

        private static DataSource datasource = null;

        static {

            try {

                File f = new File("db.properties");
                FileInputStream fis = new FileInputStream(f);

                Properties p = new Properties();
                p.load(fis);

                String url = p.getProperty("db.url");
                String uname = p.getProperty("db.username");
                String pwd = p.getProperty("db.password");
                String poolSize = p.getProperty("db.poolSize");

                HikariConfig config = new HikariConfig();

                config.setJdbcUrl(url);
                config.setUsername(uname);
                config.setPassword(pwd);
                config.setMaximumPoolSize(Integer.parseInt(poolSize));

                datasource = new HikariDataSource(config);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static Connection getDBConnection () throws Exception {
            return datasource.getConnection();
        }
    }
