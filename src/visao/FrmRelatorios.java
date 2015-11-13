package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ProdutoController;
import controle.UsuarioController;

@SuppressWarnings("serial")
public class FrmRelatorios extends JFrame {

	private JPanel contentPane;
	private JButton btnUsuarios;
	private JButton btnProdutos;
	private JButton btnFinanceiro;
	private JButton btnPedidos;
	private JButton btnProducao;
	private JButton btnClientes;

	public FrmRelatorios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ProdutoController controle = new ProdutoController();
				try {
					controle.gerarRelatorio();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnUsuarios = new JButton("Usuarios");
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
		btnUsuarios.setBounds(63, 35, 89, 57);
		contentPane.add(btnUsuarios);

		btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClientes.setBounds(162, 35, 89, 57);
		contentPane.add(btnClientes);
		btnProdutos.setBounds(261, 35, 88, 57);
		contentPane.add(btnProdutos);

		btnFinanceiro = new JButton("Financeiro");
		btnFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		btnPedidos = new JButton("Pedidos");
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPedidos.setBounds(359, 35, 89, 57);
		contentPane.add(btnPedidos);
		btnFinanceiro.setBounds(458, 35, 88, 57);
		contentPane.add(btnFinanceiro);

		btnProducao = new JButton("Producao");
		btnProducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProducao.setBounds(556, 35, 89, 57);
		contentPane.add(btnProducao);

		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
