package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.UtilMenssage;

@SuppressWarnings("serial")
public class FrmcadUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtUsuario;
	private JButton btnEditar;
	private JButton btnCancel;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JPasswordField pwdSenha;
	private JTextField txtPermissao;
	private JLabel lblNome;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblPermissao;

	public FrmcadUsuarios(Usuario u) {
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
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setIcon(null);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Usuario usuario = new Usuario();
				
				if (txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || pwdSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Atenção! Verifique os campos!");
				} else {
					usuario.setNome(txtNome.getText());
					usuario.setUsuario(txtUsuario.getText());
					usuario.setSenha(pwdSenha.getText());
					usuario.setNivel(txtPermissao.getText());
					
					try {
						if(new UsuarioDAO().cadastrar(usuario)){
							UtilMenssage.msgSucesso();
							FrmcadUsuarios.this.dispose();
							new FrmUsuarios().setVisible(true);
						}else{
							UtilMenssage.msgError();
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				btnEditar.setEnabled(true);
				btnCancel.setEnabled(true);
				btnSalvar.setEnabled(false);
				txtNome.setEnabled(true);
				txtNome.setEditable(true);
				txtUsuario.setEnabled(true);
				txtUsuario.setEditable(true);
			}
		});
		btnSalvar.setBounds(10, 394, 89, 43);
		contentPane.add(btnSalvar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setEditable(true);
				txtUsuario.setEditable(true);
				btnSalvar.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
			}
		});
		btnEditar.setIcon(null);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(111, 394, 89, 43);
		contentPane.add(btnEditar);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setEnabled(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmcadUsuarios.this.dispose();
				new FrmUsuarios().setVisible(true);
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
				String message="Deseja realmente excluir o produto?";
				String title="Confirmação";
				
				int opc=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
				if(opc == JOptionPane.YES_OPTION){
					try {
	
						/*if(new UsuarioDAO().excluir(Integer.parseInt(txtCod.getText()))){
							UtilMenssage.msgSucesso();
							FrmcadUsuarios.this.dispose();
							new FrmUsuarios().setVisible(true);
						}else{
							UtilMenssage.msgError();
						}*/
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		});
		btnExcluir.setIcon(null);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(313, 394, 89, 43);
		contentPane.add(btnExcluir);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(178, 125, 249, 20);
		contentPane.add(txtNome);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(178, 181, 249, 20);
		contentPane.add(txtUsuario);
		
		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(69, 131, 73, 14);
		contentPane.add(lblNome);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(69, 182, 73, 14);
		contentPane.add(lblUsuario);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(69, 235, 73, 14);
		contentPane.add(lblSenha);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setBounds(178, 234, 249, 20);
		contentPane.add(pwdSenha);
		
		lblPermissao = new JLabel("Permiss\u00E3o");
		lblPermissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPermissao.setBounds(69, 288, 108, 14);
		contentPane.add(lblPermissao);
		
		txtPermissao = new JTextField();
		txtPermissao.setBounds(178, 287, 249, 20);
		contentPane.add(txtPermissao);
		txtPermissao.setColumns(10);
	}
}
