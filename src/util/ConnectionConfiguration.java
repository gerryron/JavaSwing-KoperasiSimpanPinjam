package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionConfiguration {
	
	public static Connection getConnectionDatabase() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("application.properties"));

		String user = props.getProperty("DATABASE_USERNAME");
		String password = props.getProperty("DATABASE_PASSWORD");
		String dbUrl = props.getProperty("DATABASE_URL");
		
		return DriverManager.getConnection(dbUrl, user, password);
	}

}
