package modelo;



public class FORNECEDOR extends Pessoa{
	
	private long ie;
	private long cnpj;
	
	

	public String getTipoPessoa() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}
	
	public long getNumCadNacional() {
		// TODO Auto-generated method stub
		return this.cnpj;
	}
	
	public long getNumCadEstadual() {
		// TODO Auto-generated method stub
		return this.ie;
	}
	
	public void setNumCadNacional(long num) {
		this.cnpj = num;
		
	}
	
	public void setNumCadEstadual(long num) {
		this.ie = num;
		
	}
	
	
	

}
