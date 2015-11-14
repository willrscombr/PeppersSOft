package visao;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnUsuarios;
	private JButton btnClientes;
	private JButton btnProdutos;
	private JButton btnPedidos;
	private JButton btnFinanceiro;
	private JButton btnProducao;
	private JButton btnRelatorios;

	public FrmPrincipal() {
		inicializar();
	}
	
	public FrmPrincipal(String tipo) {
		inicializar();
		
		if (tipo.equals("G")) {
		} else {
			btnUsuarios.setEnabled(false);
		}
	}

	public void inicializar() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String message = "Deseja realmente sair?";
				String title = "Confirmação";
				int opc = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					System.exit(DISPOSE_ON_CLOSE);
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/imagens/new icon/16x16/db_unregister_16_h.bmp")));
		setTitle("PeeperSoft");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 591);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmUsurios = new JMenuItem("Usu\u00E1rios");
		mnCadastro.add(mntmUsurios);

		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mntmProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmProdutos().setVisible(true);
			}
		});
		mnCadastro.add(mntmProdutos);

		JMenu mnFinanceiro = new JMenu("Financeiro");
		mnCadastro.add(mnFinanceiro);

		JMenuItem mntmLanamento = new JMenuItem("Lan\u00E7amento");
		mntmLanamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrmFinanceiro().setVisible(true);
			}
		});
		mnFinanceiro.add(mntmLanamento);

		JMenuItem mntmConta = new JMenuItem("Conta");
		mntmConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrmConta().setVisible(true);
			}
		});
		mnFinanceiro.add(mntmConta);

		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);

		JMenuItem mntmProdutos_1 = new JMenuItem("Produtos");
		mntmProdutos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		mnRelatrios.add(mntmProdutos_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 867, 553);
		contentPane.add(panel);
		panel.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrmUsuarios().setVisible(true);
			}
		});

		btnUsuarios.setIcon(new ImageIcon(getClass().getResource("/imagens/couple64.png")));
		btnUsuarios.setBounds(34, 25, 105, 100);
		panel.add(btnUsuarios);

		btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmClientes().setVisible(true);
			}
		});
		btnClientes.setIcon(new ImageIcon(getClass().getResource("/imagens/Edit-Male-User64.png")));
		btnClientes.setBounds(149, 25, 105, 100);
		panel.add(btnClientes);

		btnProdutos = new JButton("");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmProdutos().setVisible(true);
			}
		});

		btnProdutos.setIcon(new ImageIcon(getClass().getResource("/imagens/pimenta.png")));
		btnProdutos.setBounds(264, 25, 105, 100);
		panel.add(btnProdutos);

		btnPedidos = new JButton("");
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmPedido().setVisible(true);
			}
		});
		btnPedidos.setIcon(new ImageIcon(getClass().getResource("/imagens/money.png")));
		btnPedidos.setBounds(379, 25, 105, 100);
		panel.add(btnPedidos);

		btnFinanceiro = new JButton("");
		btnFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmFinanceiro().setVisible(true);
			}
		});
		btnFinanceiro.setIcon(new ImageIcon(getClass().getResource("/imagens/safety_box.png")));
		btnFinanceiro.setBounds(494, 25, 105, 100);
		panel.add(btnFinanceiro);

		btnProducao = new JButton("");
		btnProducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmProducao().setVisible(true);
			}
		});
		btnProducao.setIcon(new ImageIcon(getClass().getResource("/imagens/company.png")));
		btnProducao.setBounds(609, 25, 105, 100);
		panel.add(btnProducao);

		btnRelatorios = new JButton("");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmRelatorios().setVisible(true);
			}
		});
		btnRelatorios.setIcon(new ImageIcon(getClass().getResource("/imagens/Rank-History64.png")));
		btnRelatorios.setBounds(724, 25, 105, 100);
		panel.add(btnRelatorios);

		JLabel lblFinanceiro = new JLabel("Financeiro");
		lblFinanceiro.setFont(new Font("Calibri", Font.BOLD, 14));
		lblFinanceiro.setBounds(516, 136, 77, 14);
		panel.add(lblFinanceiro);

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Calibri", Font.BOLD, 14));
		lblClientes.setBounds(177, 136, 77, 14);
		panel.add(lblClientes);

		JLabel lblProduo = new JLabel("Produ\u00E7\u00E3o");
		lblProduo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblProduo.setBounds(637, 136, 77, 14);
		panel.add(lblProduo);

		JLabel lblRelatrios = new JLabel("Relat\u00F3rios");
		lblRelatrios.setFont(new Font("Calibri", Font.BOLD, 14));
		lblRelatrios.setBounds(752, 136, 77, 14);
		panel.add(lblRelatrios);

		JLabel lblVenda = new JLabel("Pedido");
		lblVenda.setFont(new Font("Calibri", Font.BOLD, 14));
		lblVenda.setBounds(407, 136, 77, 14);
		panel.add(lblVenda);

		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setFont(new Font("Calibri", Font.BOLD, 14));
		lblProdutos.setBounds(292, 136, 77, 14);
		panel.add(lblProdutos);

		JLabel lblUsurios = new JLabel("Usu\u00E1rios");
		lblUsurios.setFont(new Font("Calibri", Font.BOLD, 14));
		lblUsurios.setBounds(58, 136, 77, 14);
		panel.add(lblUsurios);
	}
}
