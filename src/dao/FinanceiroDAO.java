package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Financeiro;
import modelo.Produto;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.UtilFuncoes;

public class FinanceiroDAO {

	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão
										// armazenados os relatorios Jarper

	// Recupera os caminhos para que a classe possa encontrar os relatórios
	public FinanceiroDAO() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jr/";
	}

	public boolean cadastrar(Financeiro financeiro) {

		String sql = "INSERT INTO financeiro (id_codigo,discriminacao,valor,id_conta,tipo_lanc,data) values(null,?,?,?,?,?)";

		boolean retorno = false;
		try {
			Connection connection;
			PreparedStatement stmt;
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, financeiro.getDiscriminacao());
			stmt.setFloat(2, financeiro.getValor());
			stmt.setInt(3, financeiro.getConta().getId_conta());
			stmt.setString(4, financeiro.getConta().getTipo());
			stmt.setDate(5, financeiro.getData());

			stmt.executeUpdate();
			stmt.close();
			ConnectionFactory.closeConnection(connection);
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

	public ResultSet consultar() throws Exception {
		Date data = new Date(System.currentTimeMillis());

		String sql = "SELECT * FROM financeiro where data = ? ";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setDate(1, data);

		ResultSet rs = stmt.executeQuery();
		return rs;
	}

	public ResultSet consulta(String datai, String dataf) throws Exception {
		Date data = new Date(System.currentTimeMillis());

		UtilFuncoes u = new UtilFuncoes();
		String dinicial=u.formataData(datai);
		String dfinal=u.formataData(dataf);
		
		if(dinicial=="0"){
			dinicial =  data.toString();
		}
		if(dfinal=="0"){
			dfinal = data.toString();	
		}
		String sql = "SELECT financeiro.id_codigo,financeiro.discriminacao,financeiro.tipo_lanc,"
				+ "conta.descricao,financeiro.valor,financeiro.data FROM financeiro,conta where "
				+ "financeiro.id_conta = conta.id_conta and data between '"+dinicial+"' and '"+dfinal+"'";
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	
	public String consultaSql(String datai, String dataf) throws Exception {
		Date data = new Date(System.currentTimeMillis());
		
		UtilFuncoes u = new UtilFuncoes();
		String dinicial=u.formataData(datai);
		String dfinal=u.formataData(dataf);
		
		if(dinicial=="0"){
			dinicial =  data.toString();
		}
		if(dfinal=="0"){
			dfinal = data.toString();	
		}
		String sql = "SELECT financeiro.id_codigo,financeiro.discriminacao,financeiro.tipo_lanc,"
				+ "conta.descricao,financeiro.valor,financeiro.data FROM financeiro,conta where "
				+ "financeiro.id_conta = conta.id_conta and data between '"+dinicial+"' and '"+dfinal+"'";
		
		return sql;
	}

	public ResultSet consultaSQL(String sql) throws Exception {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public Financeiro consultar(int id) throws Exception {
		Financeiro financeiro = null;
		try {
			financeiro = new Financeiro();
			String sql = "SELECT * FROM financeiro WHERE id_codigo = ?";
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			ConnectionFactory.closeConnection(connection);
			while (rs.next()) {
				financeiro.setDiscriminacao(rs.getString("discriminacao"));
				financeiro.setValor(rs.getFloat("valor"));
			}
		} catch (Exception e) {

		}
		return financeiro;
	}

	public Financeiro consultar(Date data) throws Exception {
		Financeiro financeiro = null;

		try {
			financeiro = new Financeiro();
			String sql = "SELECT * FROM financeiro WHERE data = ?";
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setDate(1, data);

			ResultSet rs = stmt.executeQuery();
			ConnectionFactory.closeConnection(connection);
			while (rs.next()) {
				financeiro.setDiscriminacao(rs.getString("discriminacao"));
				//financeiro.setConta().;Tipo_lanc(rs.getString("tipo_lanc"));
				financeiro.setValor(rs.getFloat("valor"));
			}
		} catch (Exception e) {

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

	// Imprime/gera uma lista Financeiro
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void gerarRelatorio() throws Exception {

		// estabelece conexão
		String sql = "SELECT * FROM financeiro";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		// gerando o jasper design
		JasperDesign desenho = JRXmlLoader.load(this.getPathToReportPackage() + "FinanceiroRel.jrxml");

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

	// Imprime/gera uma lista Financeiro
		@SuppressWarnings({ "unchecked", "deprecation" })
		public void gerarRelDetalhado(String sql) throws Exception {

			// estabelece conexão
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			// gerando o jasper design
			JasperDesign desenho = JRXmlLoader.load(this.getPathToReportPackage() + "FinanceiroRelDet.jrxml");

			// compila o relatório
			JasperReport relatorio = JasperCompileManager.compileReport(desenho);

			// implementação da interface JRDataSource para DataSource ResultSet
			JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);

			// executa o relatório
			@SuppressWarnings("rawtypes")
			Map parametros = new HashMap();
			parametros.put("data", sql);
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
