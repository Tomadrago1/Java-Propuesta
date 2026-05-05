package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

	private static DbConnector instancia;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String host     = System.getenv("DB_HOST")     != null ? System.getenv("DB_HOST")     : "localhost";
	private String port     = System.getenv("DB_PORT")     != null ? System.getenv("DB_PORT")     : "3306";
	private String user     = System.getenv("DB_USER")     != null ? System.getenv("DB_USER")     : "java";
	private String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "java";
	private String db       = System.getenv("DB_NAME")     != null ? System.getenv("DB_NAME")     : "jartraining";
	private int conectados = 0;
	private Connection conn = null;

	private DbConnector() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DbConnector getInstancia() {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		return instancia;
	}

	public Connection getConn() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, password);
				conectados = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conectados++;
		return conn;
	}

	public void releaseConn() {
		conectados--;
		try {
			if (conectados <= 0 && conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
