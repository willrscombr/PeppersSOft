package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import modelo.Conta;
import modelo.Financeiro;
import modelo.Produto;

public class ContaDAO {
	public boolean cadastrar(Financeiro financeiro) {

			String sql = "INSERT INTO financeiro (id_codigo,discriminacao,valor,id_conta,tipo_lanc) values(null,?,?,?,?)";

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
		
		String sql = "SELECT * FROM financeiro";
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

		String sql = "DELETE FROM produto WHERE id_produto = ?";
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

		String sql = "SELECT * FROM produto";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
	//		produtos.add(new Produto(rs.getInt("codigo"), rs.getString("nome")));
		}

		rs.close();
		stmt.close();
		ConnectionFactory.closeConnection(connection);

		return produtos;

	}

}

