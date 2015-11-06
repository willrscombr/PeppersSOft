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

import dao.UsuarioDAO;
import modelo.Usuario;

public class FrmLogin {

	private JFrame frame;
	private JTextField textUsuario;
	private JPasswordField pwdSenha;
	private JButton btnEntrar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin window = new FrmLogin();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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
		frame.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		frame.setLocationRelativeTo(null);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(77, 78, 46, 14);
		frame.getContentPane().add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(77, 126, 46, 14);
		frame.getContentPane().add(lblSenha);

		btnEntrar = new JButton("Entrar");
		btnEntrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					String user = (textUsuario.getText());
					@SuppressWarnings("deprecation")
					String senha = pwdSenha.getText();

					Usuario usuario = new Usuario();
					usuario.setUsuario(textUsuario.getText());
					usuario.setSenha(pwdSenha.getText());
					UsuarioDAO dao = new UsuarioDAO();

					try {
						if (dao.realizarLogin(usuario)) {
							frame.dispose();
							FrmPrincipal framep = new FrmPrincipal();
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

			public void actionPerformed(ActionEvent arg0) {
				String user = (textUsuario.getText());
				@SuppressWarnings("deprecation")
				String senha = pwdSenha.getText();

				Usuario usuario = new Usuario();
				usuario.setUsuario(textUsuario.getText());
				usuario.setSenha(pwdSenha.getText());
				UsuarioDAO dao = new UsuarioDAO();

				try {
					if (dao.realizarLogin(usuario)) {
						frame.dispose();
						FrmPrincipal framep = new FrmPrincipal();
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
		frame.getContentPane().add(btnEntrar);

		JLabel lblPadroAdminAdmin = new JLabel("Padr\u00E3o: admin admin");
		lblPadroAdminAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPadroAdminAdmin.setBounds(77, 21, 220, 30);
		frame.getContentPane().add(lblPadroAdminAdmin);

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
		frame.getContentPane().add(pwdSenha);

	}
}
