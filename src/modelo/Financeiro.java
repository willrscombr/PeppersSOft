package modelo;

import java.sql.Date;

public class Financeiro {
	private Conta conta;
	private int id_codigo;
	private String discriminacao;
	private float valor;
	private String tipo_lanc;
	private Date data;

	public Financeiro() {
		// TODO Auto-generated constructor stub
	}
	
	public Financeiro(int id_codigo, String discriminacao, String tipo_lanc, float valor) {
		this.setId_codigo(id_codigo);
		this.setDiscriminacao(discriminacao);
		this.setTipo_lanc(tipo_lanc);
		this.setValor(valor);
	}

	public Date getData() {
		data = new Date(System.currentTimeMillis());
		return data;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public int getId_codigo() {
		return id_codigo;
	}

	public void setId_codigo(int id_codigo) {
		this.id_codigo = id_codigo;
	}

	public String getDiscriminacao() {
		return discriminacao;
	}

	public void setDiscriminacao(String discriminacao) {
		this.discriminacao = discriminacao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getTipo_lanc() {
		return tipo_lanc;
	}

	public void setTipo_lanc(String tipo_lanc) {
		this.tipo_lanc = tipo_lanc;
	}

}
