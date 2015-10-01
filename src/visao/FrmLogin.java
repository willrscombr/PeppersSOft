package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin {

	private JFrame frame;
	private JTextField textUsuario;
	private JTextField textSenha;

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
		textUsuario.setBounds(173, 75, 156, 20);
		frame.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		
		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(173, 123, 156, 20);
		frame.getContentPane().add(textSenha);
		frame.setLocationRelativeTo(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(77, 78, 46, 14);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(77, 126, 46, 14);
		frame.getContentPane().add(lblSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario= (textUsuario.getText()); 
				String senha = textSenha.getText();
				if(usuario.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")){
					JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
					try {
						FrmPrincipal framep = new FrmPrincipal();
						framep.setVisible(true);
						frame.setVisible(false);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Usuario/Senha invalidos! Tente novamente!");
				}
			}
		});
		btnEntrar.setBounds(173, 176, 156, 23);
		frame.getContentPane().add(btnEntrar);
		
		JLabel lblPadroAdminAdmin = new JLabel("Padr\u00E3o: admin admin");
		lblPadroAdminAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPadroAdminAdmin.setBounds(77, 21, 220, 30);
		frame.getContentPane().add(lblPadroAdminAdmin);
	}
}
