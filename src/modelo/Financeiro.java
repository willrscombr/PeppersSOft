package modelo;;

public class Financeiro {
	Conta c = new Conta();
	
	private int cod_financeiro;
	private String disc;
	private float valor;
	private String tipo;
	
	public Conta getC() {
		return c;
	}
	public void setC(Conta c) {
		this.c = c;
	}
	public int getCod_financeiro() {
		return cod_financeiro;
	}
	public void setCod_financeiro(int cod_financeiro) {
		this.cod_financeiro = cod_financeiro;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
