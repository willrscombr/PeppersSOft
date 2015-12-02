package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import modelo.ItemVenda;
import modelo.Pessoa;
import modelo.Venda;

public class VendasDao {
	
	private Connection conn = null ;
	private PreparedStatement stmt = null;
	
	
	public VendasDao() {

	}
	
	public boolean cadastrar(Venda venda) throws Exception{
		conn = ConnectionFactory.getConnection();
		String sql = "insert into venda(cliente) values(?);";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, venda.getCliente().getCodigo());
		Long cod = Long.valueOf(stmt.executeUpdate());
		stmt.close();
		venda.setCodVenda(buscarUltimaVenda());
		
		return inserirItemVenda(venda);
		
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
		String sql = "insert into itemvenda(codigo,venda,quantidade,produto,preco) values";
		Iterator itevenda = venda.getListaitempedido().iterator(); 
		while(itevenda.hasNext()){
			ItemVenda item = (ItemVenda) itevenda.next();
			sql += "("+item.getCodItempedido() +",";
				sql	+= ""+venda.getCodVenda()+",";
							sql+= ""+item.getQuantidade()+",";
							sql+= ""+item.getProduto().getId_produto()+",";
							sql+= ""+item.getProduto().getPr_venda()+"),";
		}
		int tamSql = sql.length();
		sql = sql.substring(0,tamSql -1);
		System.out.println("sql = "+sql);
		stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		
		
		return true;
	}
}
