package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DataSourceFactory {
	
	public static DataSource getMySQLDataSource() {
		MysqlDataSource msDS=null;
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("server.conf");
			props.load(in);
			in.close();

			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			
			msDS=new MysqlDataSource();
			msDS.setUrl(url);
			msDS.setUser(username);
			msDS.setPassword(password);
		
			return msDS;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msDS;
	}

}