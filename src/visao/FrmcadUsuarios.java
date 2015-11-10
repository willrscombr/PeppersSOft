package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.UsuarioController;
import modelo.Usuario;
import util.UtilMenssage;

@SuppressWarnings("serial")
public class FrmcadUsuarios extends JFrame {

	private JPanel contentPane;
	private JButton btnSalvar;
	private JButton btnCancel;
	private JButton btnExcluir;
	private JTextField txtNome;
	private JTextField txtUsuario;
	private JTextField txtCod;
	private JButton btnEditar;
	private JPasswordField pwdSenha;
	private JComboBox comboBox;

	public FrmcadUsuarios(Usuario u) {

		setTitle("PepperSoft - Cadastro de Usuarios");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		btnSalvar = new JButton("Salvar");
		btnSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					try {
						salvaUsuario();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvaUsuario();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalvar.setIcon(null);
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setBounds(10, 394, 89, 43);
		contentPane.add(btnSalvar);

		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmcadUsuarios.this.dispose();
				new FrmUsuarios().setVisible(true);
			}
		});
		btnCancel.setIcon(null);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(208, 394, 89, 43);
		contentPane.add(btnCancel);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Deseja realmente excluir o Usuário?";
				String title = "Confirmação";
				int opc = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					try {

						if (new UsuarioController().excluir(Integer.parseInt(txtCod.getText()))) {
							UtilMenssage.msgSucesso();
							FrmcadUsuarios.this.dispose();
							new FrmUsuarios().setVisible(true);
						} else {
							UtilMenssage.msgError();
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnExcluir.setIcon(null);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(307, 394, 89, 43);
		contentPane.add(btnExcluir);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtUsuario.requestFocus();
				}
			}
		});

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setEditable(true);
				txtUsuario.setEditable(true);
				pwdSenha.setEditable(true);
				btnSalvar.setEnabled(true);
				btnExcluir.setEnabled(false);
				btnEditar.setEnabled(false);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(109, 394, 89, 43);
		contentPane.add(btnEditar);

		JLabel lblCodigo = new JLabel("Codigo ");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(66, 89, 113, 31);
		contentPane.add(lblCodigo);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(66, 141, 73, 19);
		contentPane.add(lblNome);

		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(66, 184, 99, 19);
		contentPane.add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(66, 229, 99, 19);
		contentPane.add(lblSenha);

		JLabel lblPermissao = new JLabel("Permiss\u00E3o");
		lblPermissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPermissao.setBounds(66, 272, 99, 19);
		contentPane.add(lblPermissao);

		txtCod = new JTextField();
		txtCod.setEditable(false);
		txtCod.setColumns(10);
		txtCod.setBounds(175, 96, 122, 20);
		contentPane.add(txtCod);
		txtNome.setColumns(10);
		txtNome.setBounds(175, 140, 274, 20);
		contentPane.add(txtNome);

		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					pwdSenha.requestFocus();
				}
			}
		});
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(175, 183, 274, 20);
		contentPane.add(txtUsuario);

		pwdSenha = new JPasswordField();
		pwdSenha.setBounds(175, 230, 274, 20);
		contentPane.add(pwdSenha);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Selecione", "Gerencial", "Limitado" }));
		comboBox.setBounds(176, 273, 122, 20);
		contentPane.add(comboBox);
		txtNome.requestFocus();

		if (u != null) {
			txtCod.setText(Integer.toString(u.getId_codigo()));
			txtNome.setText(u.getNome());
			comboBox.setSelectedItem(u.getPermissao());

			txtUsuario.setText(u.getUsuario());
			pwdSenha.setText(u.getSenha());
			txtNome.setEditable(false);
			comboBox.setSelectedItem(u.getPermissao());
			txtUsuario.setEditable(false);
			pwdSenha.setEditable(false);
			btnExcluir.setEnabled(true);
			btnEditar.setEnabled(true);
			btnSalvar.setEnabled(false);
		}

	}

	// método para salvar, editar Usuario...
	protected void salvaUsuario() throws Exception {
		Usuario usuario = new Usuario();
		if (txtCod.getText().isEmpty()) {

			if (txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || pwdSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Atenção! Verifique os campos!");
			} else {
				usuario.setNome(txtNome.getText());
				usuario.setUsuario(txtUsuario.getText());
				usuario.setSenha(pwdSenha.getText());

				String permissao = null;
				if (comboBox.getSelectedItem() == "Gerencial") {
					permissao = "G";
				}
				if (comboBox.getSelectedItem() == "Limitado") {
					permissao = "L";
				}

				usuario.setPermissao(permissao);

				if (new UsuarioController().cadastrar(usuario)) {
					UtilMenssage.msgSucesso();
					FrmcadUsuarios.this.dispose();
					new FrmUsuarios().setVisible(true);
				} else {
					UtilMenssage.msgError();
				}
			}
		} else {
			if (txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || pwdSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Atenção! Verifique os campos!");
			} else {
				usuario.setId_codigo(Integer.parseInt(txtCod.getText()));
				usuario.setNome(txtNome.getText());
				usuario.setUsuario(txtUsuario.getText());
				usuario.setSenha(pwdSenha.getText());

				String permissao = null;
				if (comboBox.getSelectedItem() == "Gerencial") {
					permissao = "G";
				}
				if (comboBox.getSelectedItem() == "Limitado") {
					permissao = "L";
				}

				usuario.setPermissao(permissao);
				try {
					if (new UsuarioController().alterar(usuario)) {
						UtilMenssage.msgSucesso();
						FrmcadUsuarios.this.dispose();
						new FrmUsuarios().setVisible(true);
					} else {
						UtilMenssage.msgError();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}
}
