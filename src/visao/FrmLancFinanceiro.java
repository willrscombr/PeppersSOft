package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import modelo.Conta;
import modelo.Financeiro;
import dao.ContaDAO;
import dao.FinanceiroDAO;

@SuppressWarnings("serial")
public class FrmLancFinanceiro extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtConta;
	private JTextField txtDiscrim;
	private JTextField txtValor;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmLancFinanceiro() {
		setTitle("Lan\u00E7amento");
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
				ContaDAO consulta = new ContaDAO();

				try {
					Conta conta = consulta.consultar(Integer.parseInt(txtCodigo
							.getText()));

					txtConta.setText(String.valueOf(conta.getDescricao()));

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
		lblDiscriminao.setBounds(34, 88, 82, 14);
		contentPane.add(lblDiscriminao);

		JComboBox comboBox = new JComboBox();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtValor.requestFocus();
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Selecione",
				"Cr\u00E9dito", "D\u00E9bito" }));
		comboBox.setToolTipText("");
		comboBox.setBounds(173, 113, 157, 20);
		contentPane.add(comboBox);

		txtDiscrim = new JTextField();
		txtDiscrim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					comboBox.requestFocus();
				}
			}
		});
		txtDiscrim.setColumns(10);
		txtDiscrim.setBounds(125, 82, 205, 20);
		contentPane.add(txtDiscrim);

		JLabel lblTipoLanamento = new JLabel("Tipo Lan\u00E7amento");
		lblTipoLanamento.setBounds(34, 120, 129, 14);
		contentPane.add(lblTipoLanamento);

		JButton btnConfirmarLanamento = new JButton("Confirmar Lan\u00E7amento");
		btnConfirmarLanamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (comboBox.getSelectedItem() == "Selecione") {
						JOptionPane.showMessageDialog(null,
								"Por favor escolha o tipo de lançamento!");
						comboBox.requestFocus();
					}
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

						String tipo = null;
						if (comboBox.getSelectedItem() == "Crédito") {
							tipo = "C";
						}
						if (comboBox.getSelectedItem() == "Débito") {
							tipo = "D";
						}

						Financeiro financeiro = new Financeiro();
						FinanceiroDAO cadastra = new FinanceiroDAO();
						Conta conta = new Conta();
						conta.setCod_Conta(Integer.parseInt(txtCodigo.getText()));
						financeiro.setConta(conta);
						financeiro.setDisc(txtDiscrim.getText());
						financeiro.setTipo(tipo);
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
	}
}
