package controle;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Conection {

	private static String sqlString = "jdbc:mysql://localhost:3306/peepe";
	private static String usuario = "root";
	private static String senha = ""; // no laborat�rio da Unifieo n�o tem
											// senha, deixar em branco � �

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