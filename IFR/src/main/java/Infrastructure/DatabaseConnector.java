package Infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	private static final String URL = "jdbc:mysql://192.168.1.3:3306/messages";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private Connection connection;

	public DatabaseConnector() {
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to the database.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("Connection closed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
