package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Produto;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ProdutoDAO {

	private String path; // Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão
										// armazenados os relatorios Jarper

	// Recupera os caminhos para que a classe possa encontrar os relatórios
	public ProdutoDAO() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jr/";
	}

	public boolean cadastrar(Produto produto) {

		String sql = "INSERT INTO produto (id_produto,descricao,estoque,pr_custo,pr_venda,margem_lucro) values(null,?,?,?,?,?)";

		boolean retorno = false;

		try {
			Connection connection;

			PreparedStatement stmt;
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, produto.getDescricao());
			stmt.setFloat(2, produto.getEstoque());
			stmt.setFloat(3, produto.getPr_custo());
			stmt.setFloat(4, produto.getPr_venda());
			stmt.setFloat(5, produto.getMargem_lucro());

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

	public boolean alterar(Produto produto) throws Exception {
		String sqlSelect = "SELECT * FROM produto WHERE id_produto = ?";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect);
		stmtSelect.setInt(1, produto.getId_produto());

		ResultSet rs = stmtSelect.executeQuery();

		boolean encontrou = rs.next();

		if (encontrou) {

			String sqlUpdate = "UPDATE produto SET descricao = ?, estoque = ?, pr_custo = ?, pr_venda = ?, margem_lucro = ? where id_produto = ?";
			PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate);

			stmtUpdate.setString(1, produto.getDescricao());
			stmtUpdate.setFloat(2, produto.getEstoque());
			stmtUpdate.setFloat(3, produto.getPr_custo());
			stmtUpdate.setFloat(4, produto.getPr_venda());
			stmtUpdate.setFloat(5, produto.getMargem_lucro());
			stmtUpdate.setInt(6, produto.getId_produto());

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
		try {
			produto = new Produto();
			String sql = "SELECT * FROM cliente WHERE codigo = ?";
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			ConnectionFactory.closeConnection(connection);
			while (rs.next()) {
				JOptionPane.showMessageDialog(null, rs.getString("nome"));
				produto.setId_produto(rs.getInt("id_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setEstoque(rs.getFloat("estoque"));
				produto.setGrupo(rs.getString("grupo"));
				produto.setMargem_lucro(rs.getFloat("margem_lucro"));
				produto.setPr_custo(rs.getFloat("pr_custo"));
				produto.setPr_venda(rs.getFloat("pr_venda"));
			}
		} catch (Exception e) {

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

	// Imprime/gera uma lista de Produtos
	public void gerarRelatorio() throws Exception {

		List<Produto> produtos = new ArrayList<>();

		String sql = "SELECT * FROM produto";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			produtos.add(new Produto(rs.getInt("id_produto"), rs.getString("descricao"), rs.getFloat("pr_custo"),
					rs.getFloat("pr_venda"), rs.getFloat("margem_lucro"), rs.getFloat("estoque")));
		}

		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "ProdutosRel.jrxml");
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(produtos));
		JasperExportManager.exportReportToPdfFile(print, "relatorios/Relatorio_de_Produtos.pdf");

		rs.close();
		stmt.close();
		ConnectionFactory.closeConnection(connection);

	}

	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}

}
