package controle;

import java.sql.ResultSet;

import modelo.Conta;
import modelo.Financeiro;
import dao.ContaDAO;

public class ContaController {

	ContaDAO dao;

	public ContaController() {
		dao = new ContaDAO();
	}
	public boolean cadastrar(Financeiro financeiro) throws Exception {
		return dao.cadastrar(financeiro);
	}

	public ResultSet consultar() throws Exception {
		return dao.consultar();
	}
	
	public Conta consultar(int id) throws Exception{
		return dao.consultar(id);
	}
	
}
