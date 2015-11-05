package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controle.ProdutoRel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class FrmRelatorios extends JFrame{

	private JPanel contentPane;

	public FrmRelatorios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoRel imprime = new ProdutoRel();
				JasperPrint relat;
				try {
					relat=imprime.gerar();
					JasperViewer jv = new JasperViewer(relat, false);
					jv.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "deu treta");
					e.printStackTrace();
				}
			}
		});
		btnProdutos.setBounds(87, 71, 89, 23);
		contentPane.add(btnProdutos);
		
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
