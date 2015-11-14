package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controle.UsuarioController;
import modelo.Usuario;

public class FrmLogin {

	private JFrame frmPeppersoftControle;
	private JTextField textUsuario;
	private JPasswordField pwdSenha;
	private JButton btnEntrar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin window = new FrmLogin();
					window.frmPeppersoftControle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmLogin() {
		initialize();
	}

	private void initialize() {
		frmPeppersoftControle = new JFrame();
		frmPeppersoftControle.setTitle("PepperSoft - Controle de Acesso");
		frmPeppersoftControle.setBounds(100, 100, 450, 300);

		frmPeppersoftControle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPeppersoftControle.getContentPane().setLayout(null);

		textUsuario = new JTextField();
		textUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					pwdSenha.requestFocus();
				}
			}
		});
		textUsuario.setBounds(173, 75, 156, 20);
		frmPeppersoftControle.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		frmPeppersoftControle.setLocationRelativeTo(null);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(77, 78, 46, 14);
		frmPeppersoftControle.getContentPane().add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(77, 126, 46, 14);
		frmPeppersoftControle.getContentPane().add(lblSenha);

		btnEntrar = new JButton("Entrar");
		btnEntrar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {

					String login = textUsuario.getText();
					String senha = pwdSenha.getText();
					
					Usuario usuario = new Usuario();
					usuario.setUsuario(login);
					usuario.setSenha(senha);
					
					UsuarioController controller = new UsuarioController();

					try {
						String tipo = controller.autenticar(usuario);
						if (tipo != null) {
							frmPeppersoftControle.dispose();
							FrmPrincipal framep = new FrmPrincipal(tipo, usuario.getUsuario());
							framep.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Usuario/Senha invalidos! Tente novamente!");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnEntrar.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				String login = textUsuario.getText();
				String senha = pwdSenha.getText();
				
				Usuario usuario = new Usuario();
				usuario.setUsuario(login);
				usuario.setSenha(senha);
				
				UsuarioController controller = new UsuarioController();

				try {
					String tipo = controller.autenticar(usuario);
					if (tipo != null) {
						frmPeppersoftControle.dispose();
						FrmPrincipal framep = new FrmPrincipal(tipo, usuario.getUsuario());
						framep.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Usuario/Senha invalidos! Tente novamente!");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEntrar.setBounds(173, 176, 156, 23);
		frmPeppersoftControle.getContentPane().add(btnEntrar);

		pwdSenha = new JPasswordField();
		pwdSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnEntrar.requestFocus();
				}
			}
		});
		pwdSenha.setBounds(173, 124, 156, 20);
		frmPeppersoftControle.getContentPane().add(pwdSenha);

	}
}
