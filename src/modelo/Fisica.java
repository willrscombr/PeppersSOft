package modelo;

import interfaces.Cliente;

public class Fisica extends Pessoa implements Cliente{
	
	private long cpf;
	private long rg;
	
	
	@Override
	public String getTipoPessoa() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}
	@Override
	public long getNumCadNacional() {
		// TODO Auto-generated method stub
		return this.cpf;
	}
	@Override
	public long getNumCadEstadual() {
		// TODO Auto-generated method stub
		return this.rg;
	}
	@Override
	public void setNumCadNacional(long num) {
		this.cpf = num;
		
	}
	@Override
	public void setNumCadEstadual(long num) {
		this.rg = num;
		
	}
	
	
	
	
	

}
