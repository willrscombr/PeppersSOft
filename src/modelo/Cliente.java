package modelo;



public class Cliente extends Pessoa {
	
	private long cpf;
	private long rg;
	
	


	public long getNumCadNacional() {
		// TODO Auto-generated method stub
		return this.cpf;
	}
	
	public long getNumCadEstadual() {
		// TODO Auto-generated method stub
		return this.rg;
	}
	
	public void setNumCadNacional(long num) {
		this.cpf = num;
		
	}
	
	public void setNumCadEstadual(long num) {
		this.rg = num;
		
	}
	
	
	
	
	

}
