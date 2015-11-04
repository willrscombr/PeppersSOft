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
import util.UtilMenssage;
import modelo.Usuario;

public class ClienteDAO {
	
	public boolean cadastrar(Cliente cliente) {

		// Primeiro faz uma consulta pra ver se j� est� cadastrado
		
			// A ? � o campo que vai ser preenchido
			String sql = "INSERT INTO cliente (codigo,numcadnac,numcadest,nome,endereco,telefone) values(null, ?, ?, ?,?,?)";

			// Abre a conex�o
			boolean retorno = false;
		

			try {
				Connection connection;
				
				PreparedStatement stmt;	
				connection = ConnectionFactory.getConnection();
				stmt = connection.prepareStatement(sql);
				stmt.setLong(1, cliente.getNumCadNacional());
				stmt.setLong(2,cliente.getNumCadEstadual());
				stmt.setString(3, cliente.getNome());
				stmt.setString(4, cliente.getEndereco());
				stmt.setLong(5, cliente.getTelefone());
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

	public boolean alterar(Usuario usuario) throws Exception {

		String sqlSelect = "SELECT * FROM usuarios WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
		stmtSelect.setInt(1, usuario.getId_codigo());

		// Armazena o resultado da query
		ResultSet rs = stmtSelect.executeQuery();

		boolean encontrou = rs.next();

		if (encontrou) {

			String sqlUpdate = "UPDATE usuarios SET nome = ?, email = ?, login = ?, senha = ?";
			PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate);

			stmtUpdate.setString(1, usuario.getNome());
			//stmtUpdate.setString(2, usuario.getEmail());
			//stmtUpdate.setString(3, usuario.getLogin());
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

	@SuppressWarnings({ "static-access", "unused" })
	public Cliente consultar(int id) throws Exception {
		Cliente cliente = null;
		Connection connection = null;
		try{
		cliente = new ClienteFactory().clienteFisica();
		String sql = "SELECT * FROM cliente WHERE codigo = ?";
		connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		//
		boolean encontrou = rs.next();
		JOptionPane.showMessageDialog(null, rs.getString("nome"));
		int cont = 0;
		if (encontrou) {
			
				
			cliente.setCodigo(rs.getInt("codigo"));
			cliente.setEndereco(rs.getString("endereco"));
			cliente.setNome(rs.getString("nome"));
			cliente.setNumCadEstadual(rs.getLong("numcadest"));
			cliente.setNumCadNacional(rs.getLong("numcadnac"));
			cliente.setTelefone(rs.getLong("telefone"));
			cont++;
		
		}else{
			cliente = null;
			UtilMenssage.msgError();
		}
		}
		catch(Exception e ){
			e.getStackTrace();
		}finally{
			ConnectionFactory.closeConnection(connection);
		}
		
		
		return cliente;
	}

	public boolean excluir(Usuario usuario) throws Exception {

		String sql = "DELETE FROM usuarios WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		//stmt.setInt(1, usuario.getCodigo());

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

