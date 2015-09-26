package modelo;

public class Usuario {

	private int codigo;
	private String nome;
	private String email;
	private String login;
	private String senha;
	
	public Usuario(int codigo) {
		this.setCodigo(codigo);
	}
	
	public Usuario(String login, String senha) {
		this.setLogin(login);
		this.setSenha(senha);
	}
	
	public Usuario(int codigo, String nome) {
		this(codigo);
		this.setNome(nome);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
