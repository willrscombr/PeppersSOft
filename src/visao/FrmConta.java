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

import controle.ContaController;
import modelo.Conta;
import modelo.Financeiro;
import dao.ContaDAO;
import dao.FinanceiroDAO;

@SuppressWarnings("serial")
public class FrmConta extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDesc;
	private JButton btnConfirmarLanamento;
	private JComboBox comboBox;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmConta() {
		setTitle("PeeperSoft - Cadastro de Contas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtDesc.requestFocus();
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

					txtDesc.setText(String.valueOf(conta.getDescricao()));

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

		JLabel lblCdigoConta = new JLabel("C\u00F3digo Conta");
		lblCdigoConta.setBounds(46, 59, 70, 14);
		contentPane.add(lblCdigoConta);

		JLabel lblDiscriminao = new JLabel("Descri\u00E7\u00E3o");
		lblDiscriminao.setBounds(66, 90, 52, 14);
		contentPane.add(lblDiscriminao);

		comboBox = new JComboBox();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnConfirmarLanamento.requestFocus();
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Selecione",
				"Cr\u00E9dito", "D\u00E9bito" }));
		comboBox.setToolTipText("");
		comboBox.setBounds(126, 117, 205, 20);
		contentPane.add(comboBox);

		txtDesc = new JTextField();
		txtDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					comboBox.requestFocus();
				}
			}
		});
		txtDesc.setColumns(10);
		txtDesc.setBounds(126, 87, 205, 20);
		contentPane.add(txtDesc);

		JLabel lblTipoLanamento = new JLabel("Tipo Lan\u00E7amento");
		lblTipoLanamento.setBounds(34, 120, 129, 14);
		contentPane.add(lblTipoLanamento);

		btnConfirmarLanamento = new JButton("Cadastrar Conta");
		btnConfirmarLanamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (comboBox.getSelectedItem() == "Selecione") {
						JOptionPane.showMessageDialog(null,
								"Por favor escolha o tipo de conta!");
						comboBox.requestFocus();
					}

					if (txtDesc.getText() == null || txtDesc.getText() == "") {
						JOptionPane
								.showMessageDialog(null,
										"Atenção! O campo descrição deve ser informado!");
						txtDesc.requestFocus();
					} else {

						String tipo = null;
						if (comboBox.getSelectedItem() == "Crédito") {
							tipo = "C";
						}
						if (comboBox.getSelectedItem() == "Débito") {
							tipo = "D";
						}

						ContaController cadastra = new ContaController();
						Conta conta = new Conta();

						conta.setDescricao(txtDesc.getText());
						conta.setTipo(tipo);

						if (cadastra.cadastrar(conta)) {
							JOptionPane.showMessageDialog(null,
									"Lançamento realizado com sucesso!");
							FrmConta.this.dispose();
							new FrmConta().setVisible(true);
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		btnConfirmarLanamento.setBounds(163, 184, 168, 23);
		contentPane.add(btnConfirmarLanamento);
	}
}
