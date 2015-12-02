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
		String sql = "insert into venda(cliente,totalvenda) values(?,?);";
		stmt = conn.prepareStatement(sql);
		Iterator  itotalvenda = venda.getListaitempedido().iterator();
		Float totalvenda = (float) 0;
		while (itotalvenda.hasNext()) {
			ItemVenda itemvenda = (ItemVenda) itotalvenda.next();
			totalvenda = totalvenda + itemvenda.getSubtotal();
			
		}
		stmt.setInt(1, venda.getCliente().getCodigo());
		stmt.setFloat(2,totalvenda);
		totalvenda = (float) 0;
		Long cod = Long.valueOf(stmt.executeUpdate());
		stmt.close();
		venda.setCodVenda(buscarUltimaVenda());
		
		 inserirItemVenda(venda);
		 return alterarEstoque(venda);
		
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
	public boolean alterarEstoque(Venda venda) throws Exception{
		
		conn = ConnectionFactory.getConnection();
		String sql = "update produto set estoque = ? where id_produto = ?";
		stmt = conn.prepareStatement(sql);
		Iterator itevenda = venda.getListaitempedido().iterator(); 
		while(itevenda.hasNext()){
			ItemVenda item = (ItemVenda) itevenda.next();
			stmt.setFloat(1, item.getProduto().getEstoque());
			stmt.setInt(2,item.getProduto().getId_produto());
			stmt.executeUpdate();
		}
		
		
		
		
		return true;
	}
}
