package controle;

import java.sql.ResultSet;
import java.util.List;

import dao.ProdutoDAO;
import modelo.Produto;

public class ProdutoController {
	ProdutoDAO dao;

	public ProdutoController() {
		dao = new ProdutoDAO();
	}

	public boolean cadastrar(Produto produto) throws Exception {
		return dao.cadastrar(produto);
	}

	public boolean alterar(Produto produto) throws Exception {
		return dao.alterar(produto);
	}

	public ResultSet consultar() throws Exception {
		return dao.consultar();
	}
	
	public boolean excluir(int id) throws Exception {
		return dao.excluir(id);
	}

	public List<Produto> listar() throws Exception {
		return dao.listar();
	}
}
