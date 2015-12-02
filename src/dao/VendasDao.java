package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import modelo.Venda;

public class VendasDao {
	
	private Connection conn = null ;
	private PreparedStatement stmt = null;
	
	
	public VendasDao() {

	}
	
	public long cadastrar(Venda venda) throws Exception{
		conn = ConnectionFactory.getConnection();
		String sql = "insert into venda(cliente) values(?);";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, venda.getCliente().getCodigo());
		Long cod = Long.valueOf(stmt.executeUpdate());
		stmt.close();
		return cod;
		
	}public Long buscarUltimaVenda() throws Exception{
		conn = ConnectionFactory.getConnection();
		String sql = "select max(codigo) from venda";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Long cod = null;
		if(rs.next()) {
			cod = rs.getLong(1);
		}
		stmt.close();
		return cod;
	}
	public boolean inserirItemVenda(Venda venda) throws Exception{
		
		conn = ConnectionFactory.getConnection();
		String sql = "insert into itemvenda(codigo,venda,quantidade,produto) values(?,?,?,?);";
		Iterator itevenda = venda.getListaitempedido().iterator(); 
		while(itevenda.hasNext()){
			sql += "(?,?,?,?),";
		}
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, venda.getCliente().getCodigo());
		stmt.executeUpdate();
		
		
		return true;
	}
}
