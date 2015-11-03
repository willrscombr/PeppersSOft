package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.UsuarioDAO;
import modelo.Usuario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class FrmUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textUsuario;
	private JTextField textSenha;
	private JButton btnSalvar;
	private JButton btnCancel;
	private JButton btnExcluir;
	private JButton btnIncluir;

	public FrmUsuarios() {
		setTitle("PepperSoft - Cadastro de Usu\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setBackground(Color.WHITE);
		btnIncluir.setIcon(null);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalvar.setEnabled(true);
				btnCancel.setEnabled(true);
				btnIncluir.setEnabled(false);
				textNome.setEnabled(true);
				textNome.setEditable(true);
				textUsuario.setEnabled(true);
				textUsuario.setEditable(true);
				textSenha.setEnabled(true);
				textSenha.setEditable(true);
			}
		});
		btnIncluir.setBounds(10, 394, 89, 43);
		contentPane.add(btnIncluir);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario(0);
				usuario.setNome(textNome.getText());
				usuario.setLogin(textUsuario.getText());
				usuario.setSenha(textSenha.getText());
				UsuarioDAO cadastra = new UsuarioDAO();
				try {
					cadastra.cadastrar(usuario);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalvar.setIcon(null);
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setBounds(111, 394, 89, 43);
		contentPane.add(btnSalvar);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setEnabled(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalvar.setEnabled(false);
				btnCancel.setEnabled(false);
				btnIncluir.setEnabled(true);
				textNome.setEnabled(false);
				textNome.setEditable(false);
				textUsuario.setEnabled(false);
				textUsuario.setEditable(false);
				textSenha.setEnabled(false);
				textSenha.setEditable(false);
			}
		});
		btnCancel.setIcon(null);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(214, 394, 89, 43);
		contentPane.add(btnCancel);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setIcon(null);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(313, 394, 89, 43);
		contentPane.add(btnExcluir);
		
		textNome = new JTextField();
		textNome.setEnabled(false);
		textNome.setEditable(false);
		textNome.setColumns(10);
		textNome.setBounds(178, 125, 249, 20);
		contentPane.add(textNome);
		
		textUsuario = new JTextField();
		textUsuario.setEditable(false);
		textUsuario.setEnabled(false);
		textUsuario.setColumns(10);
		textUsuario.setBounds(178, 181, 249, 20);
		contentPane.add(textUsuario);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(69, 131, 73, 14);
		contentPane.add(lblNome);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(69, 182, 73, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha_1 = new JLabel("Senha");
		lblSenha_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha_1.setBounds(69, 235, 73, 14);
		contentPane.add(lblSenha_1);
		
		textSenha = new JTextField();
		textSenha.setEditable(false);
		textSenha.setEnabled(false);
		textSenha.setColumns(10);
		textSenha.setBounds(178, 234, 249, 20);
		contentPane.add(textSenha);
	}
}
