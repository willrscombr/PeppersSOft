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
	public void setVazio(){
		this.setCodigo(0);
		this.setEndereco(null);
		this.setIdade(0);
		this.setNome(null);
		this.setNumCadEstadual(0);
		this.setNumCadNacional(0);
		this.setTelefone(0);
		this.setTipo(0);
		
	}
	
	
	
	
	

}
