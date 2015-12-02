package modelo;

public class ItemVenda {
	private int coditempedido;
	private Venda pedido; 
	private Produto produto;
	private Float quantidade;
	private Float subtotal;
	
	public ItemVenda(){
		
	}
	
	public ItemVenda(int codigo, Produto produto,Float quantidade){
		this.coditempedido = codigo;
		this.produto = produto;
		this.quantidade = quantidade;
		this.subtotal = this.quantidade * this.produto.getPr_venda();
		
	}
	
	public int getCodItempedido() {
		return coditempedido;
	}
	public Float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}
	public void setCodItempedido(int coditempedido) {
		this.coditempedido = coditempedido;
	}
	public Venda getPedido() {
		return pedido;
	}
	public void setPedido(Venda pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Float getSubtotal(){
	return	this.subtotal;
	}
	
	
	

}
