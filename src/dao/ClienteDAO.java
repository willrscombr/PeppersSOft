package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import modelo.Cliente;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.UtilMenssage;

public class ClienteDAO {

	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde est�o
										// armazenados os relatorios Jarper

	// Recupera os caminhos para que a classe possa encontrar os relat�rios
	public ClienteDAO() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jr/";
	}

	public boolean cadastrar(Cliente cliente) {

		// Primeiro faz uma consulta pra ver se j� est� cadastrado

		// A ? � o campo que vai ser preenchido
		String sql = "INSERT INTO cliente (codigo,numcadnac,numcadest,nome,endereco,telefone,tipo) values(null, ?, ?, ?,?,?,?)";

		// Abre a conex�o
		boolean retorno = false;

		try {
			Connection connection;

			PreparedStatement stmt;
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, cliente.getNumCadNacional());
			stmt.setLong(2, cliente.getNumCadEstadual());
			stmt.setString(3, cliente.getNome());
			stmt.setString(4, cliente.getEndereco());
			stmt.setLong(5, cliente.getTelefone());
			stmt.setInt(6, cliente.getTipo());
			stmt.executeUpdate();
			stmt.close();
			ConnectionFactory.closeConnection(connection);
			// Fecha a conex�o

			retorno = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;

	}

	public boolean alterar(Cliente cliente) throws Exception {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmtUpdate = null;
		try {
			String sqlUpdate = "UPDATE cliente SET nome = ?, numcadnac = ? , numcadest = ?, telefone = ?, tipo = ?, endereco = ? where codigo = ? ";
			stmtUpdate = connection.prepareStatement(sqlUpdate);

			stmtUpdate.setString(1, cliente.getNome());
			stmtUpdate.setLong(2, cliente.getNumCadNacional());
			stmtUpdate.setLong(3, cliente.getNumCadEstadual());
			stmtUpdate.setLong(4, cliente.getTelefone());
			stmtUpdate.setInt(5, cliente.getTipo());
			stmtUpdate.setString(6, cliente.getEndereco());
			stmtUpdate.setInt(7, cliente.getCodigo());

			stmtUpdate.executeUpdate();
			return true;

		} catch (Exception e) {
			return false;
		} finally {

			stmtUpdate.close();
			ConnectionFactory.closeConnection(connection);

		}

	}

	public ResultSet consultar() throws Exception {

		String sql = "select codigo,nome,numcadnac,numcadest,telefone,tipo,endereco from cliente;";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		return rs;

	}

	public ResultSet Busca(String busca) throws Exception {

		String sql = "select codigo,nome,numcadnac,numcadest,telefone,tipo,endereco from cliente where nome like '%"
				+ busca + "%';";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		return rs;

	}

	@SuppressWarnings({ "static-access", "unused" })
	public Cliente consultar(int id) throws Exception {
		Cliente cliente = null;
		Connection connection = null;
		try {
			cliente = new Cliente();
			String sql = "SELECT * FROM cliente WHERE codigo = ?";
			connection = ConnectionFactory.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			//
			boolean encontrou = rs.next();
			// JOptionPane.showMessageDialog(null, rs.getString("nome"));
			int cont = 0;
			if (encontrou) {

				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setNome(rs.getString("nome"));
				cliente.setNumCadEstadual(rs.getLong("numcadest"));
				cliente.setNumCadNacional(rs.getLong("numcadnac"));
				cliente.setTelefone(rs.getLong("telefone"));
				cont++;

			} else {
				cliente = null;
				UtilMenssage.msgError();
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection);
		}

		return cliente;
	}

	public boolean excluir(int codigo) throws Exception {

		String sql = "DELETE FROM cliente WHERE codigo = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, codigo);

		int linhasAfetadas = stmt.executeUpdate();

		stmt.close();
		ConnectionFactory.closeConnection(connection);

		if (linhasAfetadas > 0) {
			return true;
		} else {
			return false;
		}

	}

	// Imprime/gera uma lista de Usuarios
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void gerarRelatorio(int op) throws Exception {

		String sql = "SELECT * FROM cliente";

		if (op == 1) {
			sql = "SELECT * FROM cliente WHERE tipo = 0";
		} else if (op == 2) {
			sql = "SELECT * FROM cliente WHERE tipo = 1";
		}

		// estabelece conex�o
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		// gerando o jasper design
		JasperDesign desenho = JRXmlLoader.load(this.getPathToReportPackage() + "ClientesRel.jrxml");

		// compila o relat�rio
		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// implementa��o da interface JRDataSource para DataSource ResultSet
		JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

		// executa o relat�rio
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
