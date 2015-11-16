package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controle.ContaController;
import controle.FinanceiroController;
import modelo.Conta;
import modelo.Financeiro;

@SuppressWarnings("serial")
public class FrmLancFinanceiro extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtConta;
	private JTextField txtDiscrim;
	private JTextField txtValor;
	private JLabel lblTipo;

	public FrmLancFinanceiro() {
		setTitle("PepperSoft - Lan\u00E7amento Financeiro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtDiscrim.requestFocus();
				}
			}
		});
		txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				ContaController consulta = new ContaController();

				try {
					Conta conta = consulta.consultar(Integer.parseInt(txtCodigo.getText()));
					txtConta.setText(String.valueOf(conta.getDescricao()));
					
					if(conta.getTipo().equals("D")||conta.getTipo().equals("d")){
						lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
						lblTipo.setForeground(Color.red);
						lblTipo.setText("Débito");
					}
					else{
						lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
						lblTipo.setForeground(Color.GREEN);
						lblTipo.setText("Crédito");
					}

				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		txtCodigo.setBounds(126, 56, 31, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtConta = new JTextField();
		txtConta.setEditable(false);
		txtConta.setBounds(162, 56, 168, 20);
		contentPane.add(txtConta);
		txtConta.setColumns(10);

		JLabel lblCdigoConta = new JLabel("C\u00F3digo Conta");
		lblCdigoConta.setBounds(34, 59, 82, 14);
		contentPane.add(lblCdigoConta);

		JLabel lblDiscriminao = new JLabel("Discrimina\u00E7\u00E3o");
		lblDiscriminao.setBounds(34, 102, 82, 14);
		contentPane.add(lblDiscriminao);

		txtDiscrim = new JTextField();
		txtDiscrim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtValor.requestFocus();
				}
			}
		});
		txtDiscrim.setColumns(10);
		txtDiscrim.setBounds(126, 99, 205, 20);
		contentPane.add(txtDiscrim);

		JButton btnConfirmarLanamento = new JButton("Confirmar Lan\u00E7amento");
		btnConfirmarLanamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtValor.getText() == null || txtValor.getText() == "") {
						JOptionPane.showMessageDialog(null,
								"Atenção! O campo valor deve ser informado!");
						txtValor.requestFocus();
					}
					if (txtDiscrim.getText() == null
							|| txtDiscrim.getText() == "") {
						JOptionPane
								.showMessageDialog(null,
										"Atenção! O campo discriminação deve ser informado!");
						txtDiscrim.requestFocus();
					}
					if (txtCodigo.getText() == null
							|| txtCodigo.getText() == "") {
						JOptionPane.showMessageDialog(null,
								"Atenção! O campo conta deve ser informado!");
						txtCodigo.requestFocus();
					} else {

						Conta conta = new Conta();
						Financeiro financeiro = new Financeiro();
						ContaController consulta = new ContaController();
						FinanceiroController cadastra = new FinanceiroController();
						
						conta = consulta.consultar(Integer.parseInt(txtCodigo.getText()));

						conta.setId_conta(Integer.parseInt(txtCodigo.getText()));
						conta.setTipo(conta.getTipo());
						financeiro.setConta(conta);
						financeiro.setDiscriminacao(txtDiscrim.getText());
						financeiro.setValor(Float.parseFloat(txtValor.getText()));
						
						if (cadastra.cadastrar(financeiro)) {
							JOptionPane.showMessageDialog(null,
									"Lançamento realizado com sucesso!");
							FrmLancFinanceiro.this.dispose();
							new FrmFinanceiro().setVisible(true);
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		btnConfirmarLanamento.setBounds(162, 209, 168, 23);
		contentPane.add(btnConfirmarLanamento);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnConfirmarLanamento.requestFocus();
				}
			}
		});
		txtValor.setColumns(10);
		txtValor.setBounds(126, 148, 204, 20);
		contentPane.add(txtValor);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(34, 151, 58, 14);
		contentPane.add(lblValor);
		
		lblTipo = new JLabel("");
		lblTipo.setBounds(261, 31, 82, 14);
		contentPane.add(lblTipo);

	}
}
