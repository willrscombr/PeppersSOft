package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Financeiro;
import modelo.Produto;

public class FinanceiroDAO {
	public boolean cadastrar(Financeiro financeiro) {

			String sql = "INSERT INTO financeiro (id_codigo,discriminacao,valor,id_conta,tipo_lanc,data) values(null,?,?,?,?,?)";

			boolean retorno = false;
			try {
				Connection connection;	
				PreparedStatement stmt;	
				connection = ConnectionFactory.getConnection();
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, financeiro.getDisc());
				stmt.setFloat(2,financeiro.getValor());
				stmt.setInt(3, financeiro.getConta().getCod_Conta());
				stmt.setString(4, financeiro.getTipo());
				stmt.setDate(5, financeiro.getData());	
	
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
		Date data = new Date(System.currentTimeMillis());
		
		String sql = "SELECT * FROM financeiro where data = ? ";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setDate(1, data);
		
		ResultSet rs = stmt.executeQuery();
		return rs;	
	}

	public Float consultar(String sql) throws Exception {
		sql = "select sum(valor) from financeiro where tipo = 'C'";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);	
		ResultSet rs = stmt.executeQuery();
		Float total = Float.parseFloat(String.valueOf(rs));
		JOptionPane.showMessageDialog(null, total);
		return total;	
	}
	
	public Financeiro consultar(int id) throws Exception {
		Financeiro financeiro = null;
		try{
		financeiro = new Financeiro();
		String sql = "SELECT * FROM financeiro WHERE id_codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		ConnectionFactory.closeConnection(connection);
		while (rs.next()) {
		    financeiro.setDisc(rs.getString("discriminacao"));
		    financeiro.setTipo(rs.getString("tipo_lanc"));
		    financeiro.setValor(rs.getFloat("valor"));
		}
		}catch(Exception e ){
			
		}
		return financeiro;
	}
	
	public Financeiro consultar(Date data) throws Exception {
		Financeiro financeiro = null;
		
		try{
		financeiro = new Financeiro();
		String sql = "SELECT * FROM financeiro WHERE data = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setDate(1, data);

		ResultSet rs = stmt.executeQuery();
		ConnectionFactory.closeConnection(connection);
		while (rs.next()) {
		    financeiro.setDisc(rs.getString("discriminacao"));
		    financeiro.setTipo(rs.getString("tipo_lanc"));
		    financeiro.setValor(rs.getFloat("valor"));
		}
		}catch(Exception e ){
			
		}
		return financeiro;
	}

	public boolean excluir(int i) throws Exception {

		String sql = "DELETE FROM financeiro WHERE id_codigo = ?";
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

	public List<Produto> listar() throws Exception {
		List<Produto> produtos = new ArrayList<>();

		String sql = "SELECT * FROM financeiro";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		rs.close();
		stmt.close();
		ConnectionFactory.closeConnection(connection);
		return produtos;

	}

}

