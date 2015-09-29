package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class UsuarioDAO {

	public boolean cadastrar(Usuario usuario) throws Exception {

		// Primeiro faz uma consulta pra ver se já está cadastrado
		if (this.consultar(usuario) == true) {
			return false;
		} else {

			// A ? é o campo que vai ser preenchido
			String sql = "INSERT INTO usuarios(?, ?, ?, ?)";

			// Abre a conexão
			Connection connection = ConnectionFactory.getConnection();

			// Executa comando SQL
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, usuario.getCodigo());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getLogin());
			stmt.setString(5, usuario.getSenha());

			stmt.executeUpdate();
			stmt.close();

			// Fecha a conexão
			ConnectionFactory.closeConnection(connection);

			return true;
		}
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

	public boolean consultar(Usuario usuario) throws Exception {

		String sql = "SELECT * FROM usuarios WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, usuario.getCodigo());

		ResultSet rs = stmt.executeQuery();

		boolean encontrou = rs.next();

		if (encontrou) {
			String nome = rs.getString("nome");
			usuario.setNome(nome);
			String email = rs.getString("email");
			usuario.setEmail(email);
			String login = rs.getString("login");
			usuario.setLogin(login);
			String senha = rs.getString("senha");
			usuario.setSenha(senha);
		}

		rs.close();
		stmt.close();
		ConnectionFactory.closeConnection(connection);

		if (encontrou) {
			return true;
		} else {
			return false;
		}

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
