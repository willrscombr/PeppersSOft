package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionFactory {

	private static String sqlString = "jdbc:mysql://localhost:3306/pepper";
	private static String usuario = "root";
	private static String senha = "";

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(sqlString, usuario, senha);
	}

	public static void closeConnection(Connection connection) throws Exception {
		connection.close();
	}
	
	public static void testarConexao() throws Exception {
		Connection con = getConnection();
		
		if (con != null) {
			JOptionPane.showMessageDialog(null, "OK");
		} else {
			JOptionPane.showMessageDialog(null, "Falhou");
		}
	}

}
