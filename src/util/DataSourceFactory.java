package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {
	private final static Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

	public static DataSource getMySQLDataSource() {
		MysqlDataSource msDS=null;
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("server.conf");
			props.load(in);
			in.close();

			String driver = props.getProperty("jdbc.driver");
			if (driver != null) {
				Class.forName(driver);
			}
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			
			msDS=new MysqlDataSource();
			msDS.setUrl(url);
			msDS.setUser(username);
			msDS.setPassword(password);
		
			return msDS;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING,"MySQLDataSource exception",e);
		}
		return msDS;
	}

}