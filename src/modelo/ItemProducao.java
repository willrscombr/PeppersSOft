package modelo;

public class ItemProducao {
	private int coditemprod;
	private float qtdprod;
	private Produto produto;
	private String itemdesc;
	private String itemund;
	
	public int getCoditemprod() {
		return coditemprod;
	}
	public void setCoditemprod(int coditemprod) {
		this.coditemprod = coditemprod;
	}
	public float getQtdprod() {
		return qtdprod;
	}
	public void setQtdprod(float qtdprod) {
		this.qtdprod = qtdprod;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getItemdesc() {
		return itemdesc;
	}
	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}
	public String getItemund() {
		return itemund;
	}
	public void setItemund(String itemund) {
		this.itemund = itemund;
	}
	
	
}
