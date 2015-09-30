package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class FrmUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;

	private JButton btnSalvar;
	private JButton btnCancel;
	private JButton btnExcluir;
	private JButton btnIncluir;
	private JButton btnLocalizar;

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
			}
		});
		btnIncluir.setBounds(10, 394, 89, 43);
		contentPane.add(btnIncluir);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPesquisa.setBounds(26, 39, 73, 14);
		contentPane.add(lblPesquisa);
		
		textField = new JTextField();
		textField.setBounds(155, 38, 272, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.setBackground(Color.WHITE);
		btnLocalizar.setBounds(437, 33, 89, 31);
		contentPane.add(btnLocalizar);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(178, 125, 249, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(178, 181, 249, 20);
		contentPane.add(textField_2);
		
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
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		textField_4.setBounds(178, 234, 249, 20);
		contentPane.add(textField_4);
	}
}
