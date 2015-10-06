package modelo;

import interfaces.Cliente;

public class Fisica extends Pessoa implements Cliente{
	
	private int cpf;
	private int rg;
	@Override
	public String getTipoPessoa() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}
	@Override
	public int getNumCadNacional() {
		// TODO Auto-generated method stub
		return this.cpf;
	}
	@Override
	public int getNumCadEstadual() {
		// TODO Auto-generated method stub
		return this.rg;
	}
	@Override
	public void setNumCadNacional(int num) {
		this.cpf = num;
		
	}
	@Override
	public void setNumCadEstadual(int num) {
		this.rg = num;
		
	}
	
	
	
	

}
