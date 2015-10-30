package modelo;

public class Conta {
	private int Cod_Conta;
	private String descricao;
	private String tipo;
	
	public int getCod_Conta() {
		return Cod_Conta;
	}
	public void setCod_Conta(int cod_Conta) {
		Cod_Conta = cod_Conta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
