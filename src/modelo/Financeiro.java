package modelo;

import java.sql.Date;

public class Financeiro {
	private Conta conta;
	private int id_codigo;
	private String discriminacao;
	private float valor;
	private Date data;

	public Financeiro() {
	}
	
	public Financeiro(int id_codigo, String discriminacao,  float valor) {
		this.setId_codigo(id_codigo);
		this.setDiscriminacao(discriminacao);
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
}
