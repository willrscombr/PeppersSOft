package interfaces;

public interface Cliente {
	
	public String getTipoPessoa();
	public long getNumCadNacional();
	public long getNumCadEstadual();
	public void setNumCadNacional(long num);
	public void setNumCadEstadual(long num);
	public int getCodigo();
	public String getNome();
	public String getEndereco();
	public long getTelefone();
	public void setNome(String nome);
	public void setEndereco(String endereco);
	public void setTelefone(long telefone);
	public void setCodigo(int i);
	

}
