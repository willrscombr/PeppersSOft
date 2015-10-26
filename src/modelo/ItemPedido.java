package modelo;

public class ItemPedido {
	private int coditempedido;
	private Pedido pedido; 
	private Produto produto;
	
	
	public int getCodItempedido() {
		return coditempedido;
	}
	public void setCodItempedido(int coditempedido) {
		this.coditempedido = coditempedido;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	

}
