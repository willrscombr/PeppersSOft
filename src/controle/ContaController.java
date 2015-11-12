package controle;

import java.sql.ResultSet;
import modelo.Conta;
import dao.ContaDAO;

public class ContaController {

	ContaDAO dao;

	public ContaController() {
		dao = new ContaDAO();
	}
	public boolean cadastrar(Conta conta) throws Exception {
		return dao.cadastrar(conta);
	}

	public ResultSet consultar() throws Exception {
		return dao.consultar();
	}
	
	public Conta consultar(int id) throws Exception{
		return dao.consultar(id);
	}
	
}
