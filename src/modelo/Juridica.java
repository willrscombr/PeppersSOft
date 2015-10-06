package modelo;

import interfaces.Cliente;

public class Juridica extends Pessoa implements Cliente {
	
	private int ie;
	private int cnpj;
	
	
	@Override
	public String getTipoPessoa() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}
	@Override
	public int getNumCadNacional() {
		// TODO Auto-generated method stub
		return this.cnpj;
	}
	@Override
	public int getNumCadEstadual() {
		// TODO Auto-generated method stub
		return this.ie;
	}
	@Override
	public void setNumCadNacional(int num) {
		this.cnpj = num;
		
	}
	@Override
	public void setNumCadEstadual(int num) {
		this.ie = num;
		
	}
	
	

}
