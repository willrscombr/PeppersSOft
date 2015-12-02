package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import modelo.ItemProducao;
import modelo.Producao;

public class ProducaoDAO {
	
	private Connection conn = null ;
	private PreparedStatement stmt = null;
	
	
	public ProducaoDAO() {

	}
	
	public boolean cadastrar(Producao producao) throws Exception{
		Date data = new Date(System.currentTimeMillis());
		
		String sql = "INSERT INTO producao (id_codigo,data_producao,data_lanc,responsavel) values(null,?,?,?)";

		try {
			Connection connection;

			PreparedStatement stmt;
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, producao.getData());
			stmt.setDate(2, data);
			stmt.setString(3, producao.getResponsavel());

			stmt.executeUpdate();
			stmt.close();
			ConnectionFactory.closeConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		producao.setId_producao(buscarIdProducao());
		
		return inserirItemProducao(producao);
		
	}public int buscarIdProducao() throws Exception{
		conn = ConnectionFactory.getConnection();
		String sql = "select max(id_codigo) from producao";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int cod = 0;
		if(rs.next()) {
			cod = rs.getInt(1);
		}
		stmt.close();
		return cod;
	}
	public boolean inserirItemProducao(Producao producao) throws Exception{
		
		conn = ConnectionFactory.getConnection();
		String sql = "insert into producao_item(id_producao,id_produto,descricao,quantidade,unidade) values";
		Iterator itemproducao = producao.getListaitens().iterator(); 
		while(itemproducao.hasNext()){
			ItemProducao item = (ItemProducao) itemproducao.next();
				
				sql	+= "("+producao.getId_producao()+",";
				sql += ""+item.getProduto().getId_produto() +",";
				sql+= ""+item.getItemdesc()+",";
				sql+= ""+item.getQtdprod()+",";
				sql+= ""+item.getItemund()+"),";
		}
		int tamSql = sql.length();
		sql = sql.substring(0,tamSql -1);
		System.out.println("sql = "+sql);
		stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		
		
		return true;
	}

	public ResultSet consultar() throws Exception {
		String sql = "SELECT * FROM producao";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		return rs;

		
	}

	public boolean excluir(int i) throws Exception {

		String sql = "DELETE FROM producao WHERE id_codigo = ?";
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
	
}
