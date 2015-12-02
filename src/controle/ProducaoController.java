package controle;

import java.sql.ResultSet;
import java.util.List;

import modelo.Producao;
import modelo.Produto;
import dao.ProducaoDAO;

public class ProducaoController {
	ProducaoDAO dao;
	public ProducaoController() {
		dao = new ProducaoDAO();
	}

	public boolean cadastrar(Producao producao) throws Exception {
		return dao.cadastrar(producao);
	}

	public ResultSet consultar() throws Exception {
		return dao.consultar();
	}

	public boolean excluir(int id) throws Exception {
		return dao.excluir(id);
	}
	public void gerarRelatorio() throws Exception {
	//	dao.gerarRelatorio();
	}

	
}
