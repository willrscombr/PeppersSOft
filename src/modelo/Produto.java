package modelo;

public class Produto {

	private int id_produto;
	private String descricao;
	private float pr_custo;
	private float pr_venda;
	private float estoque;
	private boolean ativo;
	private String grupo;
	private float margem_lucro;

	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(int id_codigo, String descricao, float preco_custo, float preco_venda, float margem_lucro,
			float estoque) {
		this.setId_produto(id_codigo);
		this.setDescricao(descricao);
		this.setPr_custo(preco_custo);
		this.setPr_venda(preco_venda);
		this.setMargem_lucro(margem_lucro);
		this.setEstoque(estoque);
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPr_custo() {
		return pr_custo;
	}

	public void setPr_custo(float pr_custo) {
		this.pr_custo = pr_custo;
	}

	public float getPr_venda() {
		return pr_venda;
	}

	public void setPr_venda(float pr_venda) {
		this.pr_venda = pr_venda;
	}

	public float getEstoque() {
		return estoque;
	}

	public void setEstoque(float estoque) {
		this.estoque = estoque;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public float getMargem_lucro() {
		return margem_lucro;
	}

	public void setMargem_lucro(float margem_lucro) {
		this.margem_lucro = margem_lucro;
	}

}
