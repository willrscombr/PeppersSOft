package visao;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class FrmRelatorios extends JFrame {

	private JPanel contentPane;

	public FrmRelatorios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Map parametros = new HashMap();
			        String relatorio = "relatorios\\Black_A4.jasper";
			        JasperPrint jasperPrint;
					try {
						jasperPrint = JasperFillManager.fillReport(relatorio, parametros);
						JasperViewer view = new JasperViewer(jasperPrint, false);
				        view.setVisible(true);
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}            
			        
			}
		});
		btnProdutos.setBounds(39, 31, 127, 86);
		contentPane.add(btnProdutos);
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
