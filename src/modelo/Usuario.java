package modelo;

public class Usuario {

	private int id_codigo;
	private String nome;
	private String usuario;
	private String senha;
	private String permissao;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(int codigo) {
		this.setId_codigo(codigo);
	}
	
	public Usuario(String login, String senha) {
		this.setUsuario(login);
		this.setSenha(senha);
	}
	
	public Usuario(int codigo, String nome) {
		this(codigo);
		this.setNome(nome);
	}

	public int getId_codigo() {
		return id_codigo;
	}

	public void setId_codigo(int id_codigo) {
		this.id_codigo = id_codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	

}
