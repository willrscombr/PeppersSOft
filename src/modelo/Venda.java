package modelo;


import java.util.Date;
import java.util.List;
import modelo.Cliente;

public class Venda {
	private Long codvenda;
	private Date data_criacao;
	private Cliente cliente = null;
	private List<ItemVenda> listaitempedido;
	
	
	
	public Long getCodVenda() {
		return codvenda;
	}
	public void setCodVenda(Long codvenda) {
		this.codvenda = codvenda;
	}
	public Date getData_criacao() {
		return data_criacao;
	}
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemVenda> getListaitempedido() {
		return listaitempedido;
	}
	public void setListaitempedido(List<ItemVenda> listaitempedido) {
		this.listaitempedido = listaitempedido;
	}
}
