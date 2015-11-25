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

	public ResultSet consulta(String datai, String dataf) throws Exception {
		return dao.consulta(datai,dataf);

	}

	public void gerarRelatorio() throws Exception {
		dao.gerarRelatorio();

	}
	public void gerarRelDetalhado(String datai,String dataf) throws Exception {
		dao.gerarRelDetalhado(datai,dataf);

	}

}
