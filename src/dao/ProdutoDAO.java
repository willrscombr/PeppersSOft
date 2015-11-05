package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Produto;

public class ProdutoDAO {
	public boolean cadastrar(Produto produto) {

			String sql = "INSERT INTO produto (id_produto,descricao,estoque,pr_custo,pr_venda,margem_lucro) values(null,?,?,?,?,?)";

			boolean retorno = false;
		
			try {
				Connection connection;
				
				PreparedStatement stmt;	
				connection = ConnectionFactory.getConnection();
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, produto.getDescricao());
				stmt.setFloat(2,produto.getEstoque());
				stmt.setFloat(3, produto.getPreco_custo());
				stmt.setFloat(4, produto.getPreco_venda());
				stmt.setFloat(5, produto.getMargemlucro());

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

	public boolean alterar(Produto produto) throws Exception {
		String sqlSelect = "SELECT * FROM produto WHERE id_produto = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
		stmtSelect.setInt(1, produto.getCod_prod());

		// Armazena o resultado da query
		ResultSet rs = stmtSelect.executeQuery();

		boolean encontrou = rs.next();

		if (encontrou) {

			String sqlUpdate = "UPDATE produto SET descricao = ?, estoque = ?, pr_custo = ?, pr_venda = ?, margem_lucro = ? where id_produto = ?";
			PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate);

			stmtUpdate.setString(1, produto.getDescricao());
			stmtUpdate.setFloat(2, produto.getEstoque());
			stmtUpdate.setFloat(3, produto.getPreco_custo());
			stmtUpdate.setFloat(4, produto.getPreco_venda());
			stmtUpdate.setFloat(5, produto.getMargemlucro());
			stmtUpdate.setInt(6, produto.getCod_prod());

			stmtUpdate.executeUpdate();
			stmtUpdate.close();
			ConnectionFactory.closeConnection(connection);

			return true;

		} else {

			rs.close();
			stmtSelect.close();
			ConnectionFactory.closeConnection(connection);

			return false;
		}

	}
	public ResultSet consultar() throws Exception {
		
		String sql = "SELECT * FROM produto";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		return rs;
		
	}

	public Produto consultar(int id) throws Exception {
		Produto produto = null;
		try{
		produto = new Produto();
		String sql = "SELECT * FROM cliente WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		ConnectionFactory.closeConnection(connection);
		while (rs.next()) {JOptionPane.showMessageDialog(null, rs.getString("nome"));
			produto.setCod_prod(rs.getInt("id_produto"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setEstoque(rs.getFloat("estoque"));
			produto.setGrupo(rs.getString("grupo"));
			produto.setMargemlucro(rs.getFloat("margem_lucro"));
			produto.setPreco_custo(rs.getFloat("pr_custo"));
			produto.setPreco_venda(rs.getFloat("pr_venda"));
		}
		}catch(Exception e ){
			
		}
		return produto;
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
		rs.close();
		stmt.close();
		ConnectionFactory.closeConnection(connection);

		return produtos;

	}

}

