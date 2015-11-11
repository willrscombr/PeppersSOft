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
import javax.swing.JMenuBar;
import javax.swing.JMenu;

@SuppressWarnings("serial")
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

				try {
					imprime.gerar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "deu treta");
					e.printStackTrace();
				}
			}
		});
		btnProdutos.setBounds(10, 11, 88, 57);
		contentPane.add(btnProdutos);
		
		JButton btnRelFinanceiro = new JButton("Financeiro");
		btnRelFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnRelFinanceiro.setBounds(108, 11, 88, 57);
		contentPane.add(btnRelFinanceiro);
		
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
