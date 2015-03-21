package dao;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBConnection {

	private static DataSource dataSource;
	private static ThreadLocal<Connection> connecionThread = new ThreadLocal<Connection>();

	static {
		try {
			Properties props = new Properties();
			props.load(DBConnection.class.getClassLoader().getResourceAsStream("dbcp.properties"));
			dataSource = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static Connection open() {
		Connection connection = connecionThread.get();
		try {
			if (connection == null || connection.isClosed()) {
				connection = dataSource.getConnection();
				connecionThread.set(connection);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return connection;
	}

	public static void close() {
		Connection connection = connecionThread.get();
		connecionThread.set(null);
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}
