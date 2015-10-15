package modelo;

import interfaces.Cliente;

public class Juridica extends Pessoa implements Cliente {
	
	private long ie;
	private long cnpj;
	
	
	@Override
	public String getTipoPessoa() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}
	@Override
	public long getNumCadNacional() {
		// TODO Auto-generated method stub
		return this.cnpj;
	}
	@Override
	public long getNumCadEstadual() {
		// TODO Auto-generated method stub
		return this.ie;
	}
	@Override
	public void setNumCadNacional(long num) {
		this.cnpj = num;
		
	}
	@Override
	public void setNumCadEstadual(long num) {
		this.ie = num;
		
	}
	
	
	

}
