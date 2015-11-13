package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Conta;

public class ContaDAO {
	public boolean cadastrar(Conta conta) {

			String sql = "INSERT INTO conta(id_codigo,descricao,tipo) values(null,?,?)";

			boolean retorno = false;
		
			try {
				Connection connection;
				
				PreparedStatement stmt;	
				connection = ConnectionFactory.getConnection();
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, conta.getDescricao());
				stmt.setString(2, conta.getTipo());


				stmt.executeUpdate();
				stmt.close();
				ConnectionFactory.closeConnection(connection);
				retorno =  true;
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return retorno;	
	}

	public ResultSet consultar() throws Exception {
		
		String sql = "SELECT * FROM conta";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		return rs;
		
	}

	public Conta consultar(int id) throws Exception {
		Conta conta = new Conta();
		
		try{
		String sql = "SELECT * FROM conta WHERE id_conta = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
		    conta.setCod_Conta(rs.getInt("id_conta"));
			conta.setDescricao(rs.getString("descricao"));
		}else{
			JOptionPane.showMessageDialog(null, "Deu treta de novo");
		}
		
		}catch(Exception e ){
			
		}
		return conta;
	}

	public boolean excluir(int i) throws Exception {

		String sql = "DELETE FROM conta WHERE id_conta = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, i);

		int linhasAfetadas = stmt.executeUpdate();

		stmt.close();
		ConnectionFactory.closeConnection(connection);

		if (linhasAfetadas > 0) {
			return true;
		} else {
			return false;
		}

	}

	public List<Conta> listar() throws Exception {

		List<Conta> conta = new ArrayList<>();

		String sql = "SELECT * FROM conta";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		rs.close();
		stmt.close();
		ConnectionFactory.closeConnection(connection);

		return conta;
	}
}

