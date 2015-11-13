package controle;

import java.sql.ResultSet;

import dao.FinanceiroDAO;
import modelo.Financeiro;

public class FinanceiroController {

	FinanceiroDAO dao;

	public FinanceiroController() {
		dao = new FinanceiroDAO();
	}

	public ResultSet consultaCredito(String sql) throws Exception {
		return dao.consultaSQL(sql);
	}

	public ResultSet consultaDebito(String sql) throws Exception {
		return dao.consultaSQL(sql);
	}

	public boolean cadastrar(Financeiro financeiro) throws Exception {
		return dao.cadastrar(financeiro);
	}

	public ResultSet consulta() throws Exception {
		return dao.consultar();
	}

	public ResultSet consultaData(String data) throws Exception {
		return dao.consultaData(data);

	}

	public void gerarRelatorio() throws Exception {
		dao.gerarRelatorio();

	}
}
