package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import controle.FinanceiroController;
import controle.ProdutoController;
import controle.UsuarioController;
import dao.ClienteDAO;

@SuppressWarnings("serial")
public class FrmRelatorios extends JFrame {

	private JPanel contentPane;
	private JButton btnUsuarios;
	private JButton btnProdutos;
	private JButton btnFinanceiro;
	private JButton btnPedidos;
	private JButton btnProducao;
	private JButton btnClientes;
	private JRadioButton rdbtnGeralProduto;
	private JRadioButton rdbtnGeralCliente;
	private JRadioButton rdbtnEstoquePositivo;
	private JRadioButton rdbtnEstoqueZerado;
	private JRadioButton rdbtnPessoaFisica;
	private JRadioButton rdbtnPessoaJuridica;
	private ButtonGroup grupoProduto;
	private ButtonGroup grupoCliente;

	public FrmRelatorios() {
		setTitle("PepperSoft - Relat\u00F3rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 586, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int op = 0;

				if (rdbtnEstoquePositivo.isSelected()) {
					op = 1;
				} else if (rdbtnEstoqueZerado.isSelected()) {
					op = 2;
				}

				ProdutoController controle = new ProdutoController();
				try {
					controle.gerarRelatorio(op);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
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
					JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
					e1.printStackTrace();
				}
			}
		});
		btnUsuarios.setBounds(442, 376, 89, 57);
		contentPane.add(btnUsuarios);

		btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int op = 0;

				if (rdbtnPessoaFisica.isSelected()) {
					op = 1;
				} else if (rdbtnPessoaJuridica.isSelected()) {
					op = 2;
				}

				ClienteDAO controle = new ClienteDAO();
				try {
					controle.gerarRelatorio(op);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
					e1.printStackTrace();
				}
			}
		});
		btnClientes.setBounds(442, 308, 89, 57);
		contentPane.add(btnClientes);
		btnProdutos.setBounds(442, 240, 88, 57);
		contentPane.add(btnProdutos);

		btnFinanceiro = new JButton("Financeiro");
		btnFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FinanceiroController controle = new FinanceiroController();
				try {
					controle.gerarRelatorio();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
					e.printStackTrace();
				}
			}
		});

		btnPedidos = new JButton("Pedidos");
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPedidos.setBounds(441, 171, 89, 57);
		contentPane.add(btnPedidos);
		btnFinanceiro.setBounds(441, 103, 89, 57);
		contentPane.add(btnFinanceiro);

		btnProducao = new JButton("Producao");
		btnProducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProducao.setBounds(442, 35, 89, 57);
		contentPane.add(btnProducao);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 28, 550, 64);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 96, 550, 71);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 164, 550, 71);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 233, 550, 17);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 301, 550, 17);
		contentPane.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 372, 550, 17);
		contentPane.add(separator_5);

		rdbtnEstoquePositivo = new JRadioButton("Estoque Positivo");
		rdbtnEstoquePositivo.setBounds(297, 240, 139, 23);
		contentPane.add(rdbtnEstoquePositivo);

		rdbtnEstoqueZerado = new JRadioButton("Estoque Zerado");
		rdbtnEstoqueZerado.setBounds(297, 266, 139, 23);
		contentPane.add(rdbtnEstoqueZerado);

		rdbtnPessoaFisica = new JRadioButton("Pessoa F\u00EDsica");
		rdbtnPessoaFisica.setBounds(297, 308, 109, 23);
		contentPane.add(rdbtnPessoaFisica);

		rdbtnPessoaJuridica = new JRadioButton("Pessoa Jur\u00EDdica");
		rdbtnPessoaJuridica.setBounds(297, 334, 109, 23);
		contentPane.add(rdbtnPessoaJuridica);

		rdbtnGeralProduto = new JRadioButton("Geral");
		rdbtnGeralProduto.setBounds(186, 266, 109, 23);
		rdbtnGeralProduto.setSelected(true);
		contentPane.add(rdbtnGeralProduto);

		rdbtnGeralCliente = new JRadioButton("Geral");
		rdbtnGeralCliente.setBounds(186, 334, 109, 23);
		rdbtnGeralCliente.setSelected(true);
		contentPane.add(rdbtnGeralCliente);
		
		grupoProduto = new ButtonGroup();
		grupoProduto.add(rdbtnGeralProduto);
		grupoProduto.add(rdbtnEstoquePositivo);
		grupoProduto.add(rdbtnEstoqueZerado);

		grupoCliente = new ButtonGroup();
		grupoCliente.add(rdbtnGeralCliente);
		grupoCliente.add(rdbtnPessoaFisica);
		grupoCliente.add(rdbtnPessoaJuridica);

		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
