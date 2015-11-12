package controle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import dao.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ProdutoRel {

	public void gerar() throws Exception{

		try {
			Connection connection;
			connection = ConnectionFactory.getConnection();
			ProdutoController consulta = new ProdutoController();
			ResultSet rs = consulta.consultar();
			JRResultSetDataSource rl = new JRResultSetDataSource(rs);
			
			JasperPrint jp = JasperFillManager.fillReport("/relatorios/RelProdutos.jasper",new HashMap(),rl);
			JasperViewer jv = new JasperViewer(jp, false);
			jv.setVisible(true);
			jv.toFront();
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		

	}
}
