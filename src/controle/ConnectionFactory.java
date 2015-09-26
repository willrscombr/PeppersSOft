package controle;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static String sql = "jdbc:mysql://localhost:3306/sispeepersoft";
	private static String user = "root";
	private static String password = "";
	
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(sql, user, password);
	}
	
	public static void testConnection() throws Exception {
		Connection conn = getConnection();		
	}
	
	public static void closeConnection(Connection connection) throws Exception {
		connection.close();
	}

}
