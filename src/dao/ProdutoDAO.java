package dao;

import fabrica.ClienteFactory;
import interfaces.Cliente;
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

		// Primeiro faz uma consulta pra ver se j� est� cadastrado
		
			// A ? � o campo que vai ser preenchido
			String sql = "INSERT INTO produto (id_produto,descricao,estoque,pr_custo,pr_venda,margem_lucro) values(null,?,?,?,?,?)";

			// Abre a conex�o
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
				// Fecha a conex�o
			

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

		String sqlSelect = "SELECT * FROM produto WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
		stmtSelect.setInt(1, produto.getCod_prod());

		// Armazena o resultado da query
		ResultSet rs = stmtSelect.executeQuery();

		boolean encontrou = rs.next();

		if (encontrou) {

			String sqlUpdate = "UPDATE usuarios SET nome = ?, email = ?, login = ?, senha = ?";
			PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate);

			stmtUpdate.setString(1, produto.getDescricao());
			stmtUpdate.setString(2, produto.getGrupo());
			stmtUpdate.setFloat(3, produto.getEstoque());
			stmtUpdate.setFloat(4, produto.getPreco_venda());

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

	public Cliente consultar(int id) throws Exception {
		Cliente cliente = null;
		try{
		cliente = new ClienteFactory().clienteFisica();
		String sql = "SELECT * FROM cliente WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		ConnectionFactory.closeConnection(connection);
		while (rs.next()) {JOptionPane.showMessageDialog(null, rs.getString("nome"));
			cliente.setCodigo(rs.getInt("codigo"));
			cliente.setEndereco(rs.getString("endereco"));
			cliente.setNome(rs.getString("nome"));
			cliente.setNumCadEstadual(rs.getLong("numcadest"));
			cliente.setNumCadNacional(rs.getLong("numcadnac"));
			cliente.setTelefone(rs.getLong("telefone"));
		}
		}catch(Exception e ){
			
		}
		return cliente;
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

