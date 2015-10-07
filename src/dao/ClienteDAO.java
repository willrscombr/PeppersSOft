package dao;

import interfaces.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class ClienteDAO {
	
	public boolean cadastrar(Cliente cliente) throws Exception {

		// Primeiro faz uma consulta pra ver se j� est� cadastrado
		
			// A ? � o campo que vai ser preenchido
			String sql = "INSERT INTO cliente(?, ?, ?, ?)";

			// Abre a conex�o
			Connection connection = ConnectionFactory.getConnection();

			// Executa comando SQL
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, cliente.getCodigo());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getEndereco());
			stmt.setString(4, cliente.getTelefone());
			

			stmt.executeUpdate();
			stmt.close();

			// Fecha a conex�o
			ConnectionFactory.closeConnection(connection);

			return true;
		
	}

	public boolean alterar(Usuario usuario) throws Exception {

		String sqlSelect = "SELECT * FROM usuarios WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
		stmtSelect.setInt(1, usuario.getCodigo());

		// Armazena o resultado da query
		ResultSet rs = stmtSelect.executeQuery();

		boolean encontrou = rs.next();

		if (encontrou) {

			String sqlUpdate = "UPDATE usuarios SET nome = ?, email = ?, login = ?, senha = ?";
			PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate);

			stmtUpdate.setString(1, usuario.getNome());
			stmtUpdate.setString(2, usuario.getEmail());
			stmtUpdate.setString(3, usuario.getLogin());
			stmtUpdate.setString(4, usuario.getSenha());

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
		
		String sql = "SELECT * FROM cliente";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		return rs;
		
	}

	public ResultSet consultar(Cliente cliente) throws Exception {

		String sql = "SELECT * FROM cliente WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, cliente.getCodigo());

		ResultSet rs = stmt.executeQuery();

		return rs;

	}

	public boolean excluir(Usuario usuario) throws Exception {

		String sql = "DELETE FROM usuarios WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, usuario.getCodigo());

		int linhasAfetadas = stmt.executeUpdate();

		stmt.close();
		ConnectionFactory.closeConnection(connection);

		if (linhasAfetadas > 0) {
			return true;
		} else {
			return false;
		}

	}

	public List<Usuario> listar() throws Exception {

		List<Usuario> usuarios = new ArrayList<>();

		String sql = "SELECT * FROM usuarios";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			usuarios.add(new Usuario(rs.getInt("codigo"), rs.getString("nome")));
		}

		rs.close();
		stmt.close();
		ConnectionFactory.closeConnection(connection);

		return usuarios;

	}

}

