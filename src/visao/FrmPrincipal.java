package visao;

import java.awt.EventQueue;
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

@SuppressWarnings("serial")
public class FrmPrincipal extends JFrame {

	private JPanel contentPane;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	} */
	public FrmPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/imagens/new icon/16x16/db_unregister_16_h.bmp")));
		setTitle("PeeperSoft");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 591);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 867, 553);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrmUsuarios().setVisible(true);
			}
		});

		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/imagens/couple64.png")));
		btnNewButton.setBounds(34, 25, 105, 100);
		panel.add(btnNewButton);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmClientes().setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/imagens/Edit-Male-User64.png")));
		button.setBounds(149, 25, 105, 100);
		panel.add(button);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmProdutos().setVisible(true);
			}
		});

		button_1.setIcon(new ImageIcon(getClass().getResource("/imagens/pimenta.png")));
		button_1.setBounds(264, 25, 105, 100);
		panel.add(button_1);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmPedido().setVisible(true);
			}
		});
		button_2.setIcon(new ImageIcon(getClass().getResource("/imagens/money.png")));
		button_2.setBounds(379, 25, 105, 100);
		panel.add(button_2);

		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmFinanceiro().setVisible(true);
			}
		});
		button_3.setIcon(new ImageIcon(getClass().getResource("/imagens/safety_box.png")));
		button_3.setBounds(494, 25, 105, 100);
		panel.add(button_3);

		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmProducao().setVisible(true);
			}
		});
		button_4.setIcon(new ImageIcon(getClass().getResource("/imagens/company.png")));
		button_4.setBounds(609, 25, 105, 100);
		panel.add(button_4);

		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmRelatorios().setVisible(true);
			}
		});
		button_5.setIcon(new ImageIcon(getClass().getResource("/imagens/Rank-History64.png")));
		button_5.setBounds(724, 25, 105, 100);
		panel.add(button_5);

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
