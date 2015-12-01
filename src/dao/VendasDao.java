package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import modelo.ItemVenda;
import modelo.Venda;

public class VendasDao {

	public VendasDao() {

	}
	
	public boolean cadastrar(Venda venda) {
		boolean retorno = false;
		String sql = "INSERT INTO `pepper`.`venda` (`cliente`) VALUES (?);";
		try {
			Connection connection;
			ResultSet rs;
			PreparedStatement stmt;
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, venda.getCliente().getCodigo());
			stmt.executeUpdate();
			
			 sql = "SELECT LAST_INSERT_ID();";
			 stmt = connection.prepareStatement(sql);
			 rs = stmt.executeQuery();
			 Long codvenda = rs.getLong(0);

			sql = "INSERT INTO itemcarrinho (venda, quantidade, produto) VALUES(?,?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
			
			
				
			
			
			stmt.close();
			ConnectionFactory.closeConnection(connection);
			Long codigovenda = ultimoRegistroAdicionado();
			venda.setCodVenda(codigovenda);
			
			
		} catch (Exception e) {
			e.getStackTrace();	
		}
		return retorno;
	}
	
	public Long ultimoRegistroAdicionado() {
	
		String sql = "SELECT LAST_INSERT_ID();";
		Long retorno = Long.valueOf(0);
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			retorno = rs.getLong(0);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return 	retorno;
		

	}

}
