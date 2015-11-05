package controle;

import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import dao.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ProdutoRel {

	public JasperPrint gerar() throws Exception{
		JasperPrint rel = null;
		try {
			Connection connection;
			connection = ConnectionFactory.getConnection();
			HashMap map = new HashMap();
			String arquivoJasper = "RelProdutos.jasper";
			rel = JasperFillManager.fillReport(arquivoJasper, map, connection);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		return rel;

	}
}
