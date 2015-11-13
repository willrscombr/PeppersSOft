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
import controle.UsuarioController;

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
		btnProdutos.setBounds(142, 11, 88, 57);
		contentPane.add(btnProdutos);
		
		JButton btnRelFinanceiro = new JButton("Financeiro");
		btnRelFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnRelFinanceiro.setBounds(240, 11, 88, 57);
		contentPane.add(btnRelFinanceiro);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UsuarioController controle = new UsuarioController();
				try {
					controle.gerarRelatorio();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUsuarios.setBounds(45, 11, 89, 57);
		contentPane.add(btnUsuarios);
		
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
