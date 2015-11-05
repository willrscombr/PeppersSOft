package controle;

import java.sql.ResultSet;
import dao.FinanceiroDAO;
import modelo.Financeiro;

public class FinanceiroController {

	FinanceiroDAO dao;

	public FinanceiroController() {
		dao = new FinanceiroDAO();
	}

	public boolean cadastrar(Financeiro financeiro) throws Exception {
		return dao.cadastrar(financeiro);
	}

	public ResultSet consultar() throws Exception {
		return dao.consultar();
	}

}
