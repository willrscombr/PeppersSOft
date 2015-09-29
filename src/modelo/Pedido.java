package modelo;

import interfaces.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
	private int cod_pedido;
	private Date data_criacao;
	private Cliente cliente;
	private List<ItemPedido> listaitempedido;
	
	
	
	public int getCod_pedido() {
		return cod_pedido;
	}
	public void setCod_pedido(int cod_pedido) {
		this.cod_pedido = cod_pedido;
	}
	public Date getData_criacao() {
		return data_criacao;
	}
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemPedido> getListaitempedido() {
		return listaitempedido;
	}
	public void setListaitempedido(List<ItemPedido> listaitempedido) {
		this.listaitempedido = listaitempedido;
	}
	

	

}
