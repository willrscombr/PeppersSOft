package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import modelo.Cliente;
import modelo.Produto;
import util.UtilMenssage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class FrmcadCliente extends JFrame {

	private JPanel contentPane;
	private JButton btnSalvar;
	private JButton btnCancel;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JTextField textCodigo;
	private JTextField textNome;
	private JTextField textNumCadNac;
	private JTextField textNumCadEst;
	private JTextField textEndereco;
	private JTextField textTelefone;
	private JLabel lblTipo;
	JComboBox comboBoxPessoa;

	public FrmcadCliente(Cliente cliente) {
	
		setTitle("PepperSoft - Cadastro de Clientes");
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
		btnSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode()==10){  
	                  salvaCliente();    
	                }  
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaCliente();
			}
		});
		btnSalvar.setIcon(null);
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setBounds(10, 394, 89, 43);
		contentPane.add(btnSalvar);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmcadCliente.this.dispose();
				new FrmClientes().setVisible(true);
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
				String message="Deseja realmente excluir o produto?";
				String title="Confirma��o";
				int opc=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
				if(opc == JOptionPane.YES_OPTION){
					try {
	
						if(new ClienteDAO().excluir(Integer.parseInt(textCodigo.getText()))){
							UtilMenssage.msgSucesso();
							FrmcadCliente.this.dispose();
							new FrmClientes().setVisible(true);
						}else{
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
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarCampos(true);
				textEndereco.setEditable(true);
				textNome.setEditable(true);
				textNumCadEst.setEditable(true);
				textNumCadNac.setEditable(true);
				textTelefone.setEditable(true);
				comboBoxPessoa.setEditable(true);
				btnSalvar.setEnabled(true);
				btnExcluir.setEnabled(false);
				btnEditar.setEnabled(false);
				btnCancel.setEnabled(true);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(109, 394, 89, 43);
		contentPane.add(btnEditar);
		
		JLabel label_2 = new JLabel("Codigo:");
		label_2.setBounds(49, 44, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Nome:");
		label_3.setBounds(47, 70, 70, 36);
		contentPane.add(label_3);
		
		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setColumns(10);
		textCodigo.setBounds(121, 41, 54, 19);
		contentPane.add(textCodigo);
				
		textNome = new JTextField();
		textNome.setEnabled(false);
		textNome.setColumns(10);
		textNome.setBounds(119, 78, 336, 19);
		contentPane.add(textNome);
		
		JLabel label_4 = new JLabel("CPF/CNPJ:");
		label_4.setBounds(47, 117, 70, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("RG/IE:");
		label_5.setBounds(275, 114, 46, 14);
		contentPane.add(label_5);
		
		textNumCadNac = new JTextField();
		textNumCadNac.setEnabled(false);
		textNumCadNac.setColumns(10);
		textNumCadNac.setBounds(121, 114, 131, 20);
		contentPane.add(textNumCadNac);
		textNumCadNac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				String caracteres="0987654321";
			       if(!caracteres.contains(ev.getKeyChar()+"")){
			              ev.consume();
			       }
			}
		});
		
		textNumCadEst = new JTextField();
		textNumCadEst.setEnabled(false);
		textNumCadEst.setColumns(10);
		textNumCadEst.setBounds(347, 111, 131, 20);
		contentPane.add(textNumCadEst);
		textNumCadEst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				String caracteres="0987654321";
			       if(!caracteres.contains(ev.getKeyChar()+"")){
			              ev.consume();
			       }
			}
		});
		
		JLabel label_6 = new JLabel("Endereço: ");
		label_6.setBounds(49, 157, 110, 14);
		contentPane.add(label_6);
		
		textEndereco = new JTextField();
		textEndereco.setEnabled(false);
		textEndereco.setColumns(10);
		textEndereco.setBounds(121, 155, 334, 20);
		contentPane.add(textEndereco);
		
		JLabel label_7 = new JLabel("Telefone: ");
		label_7.setBounds(49, 199, 110, 14);
		contentPane.add(label_7);
		
		textTelefone = new JTextField();
		textTelefone.setEnabled(false);
		textTelefone.setColumns(10);
		textTelefone.setBounds(121, 197, 177, 20);
		contentPane.add(textTelefone);
		textTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				String caracteres="0987654321";
			       if(!caracteres.contains(ev.getKeyChar()+"")){
			              ev.consume();
			       }
			}
		});
		
		lblTipo = new JLabel("Tipo Pessoa:");
		lblTipo.setBounds(47, 229, 89, 15);
		contentPane.add(lblTipo);
		
		comboBoxPessoa = new JComboBox();
		comboBoxPessoa.setModel(new DefaultComboBoxModel(new String[] {"FISICA", "JURIDICA"}));
		comboBoxPessoa.setBounds(166, 224, 131, 24);
		comboBoxPessoa.setEnabled(false);
		contentPane.add(comboBoxPessoa);
		
		habilitarCampos(true);
		
		if(cliente != null){
			//preenche o objeto e deixa os campos sem editar
			//cliente.setCodigo(Integer.valueOf(textCodigo.getText()));
			textCodigo.setText(String.valueOf(cliente.getCodigo()));
			textEndereco.setText(cliente.getEndereco());
			textNome.setText(cliente.getNome());
			textNumCadEst.setText(String.valueOf(cliente.getNumCadEstadual()));
			textNumCadNac.setText(String.valueOf(cliente.getNumCadNacional()));
			textTelefone.setText(String.valueOf(cliente.getTelefone()));
			comboBoxPessoa.setSelectedIndex(cliente.getTipo());
			
			habilitarCampos(false);
			
			btnExcluir.setEnabled(true);
			btnEditar.setEnabled(true);	
			btnSalvar.setEnabled(false);
		}
		
	}
//m�todo para salvar, editar produto...
	protected void salvaCliente() {
		Cliente cliente = new Cliente();
		if(textCodigo.getText().isEmpty()){
			
			if(textEndereco.getText().isEmpty() || textNome.getText().isEmpty()||textNumCadEst.getText().isEmpty()||textNumCadNac.getText().isEmpty()||textTelefone.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Aten��o! Verifique os campos!");
			}
			else{
				
				//cliente.setCodigo(Integer.valueOf(textCodigo.getText()));
				cliente.setEndereco(textEndereco.getText());
				cliente.setNome(textNome.getText());
				cliente.setNumCadEstadual(Long.valueOf(textNumCadEst.getText()));
				cliente.setNumCadNacional(Long.valueOf(textNumCadNac.getText()));
				cliente.setTelefone(Long.valueOf(textTelefone.getText()));
				
				cliente.setTipo(comboBoxPessoa.getSelectedIndex());
				
				
				if(new ClienteDAO().cadastrar(cliente)){
					//JOptionPane.showMessageDialog(null, "Foi");
					UtilMenssage.msgSucesso();
					FrmcadCliente.this.dispose();
					new FrmClientes().setVisible(true);
				}else{
					UtilMenssage.msgError();
					JOptionPane.showMessageDialog(null, "NAO Foi");
				}
			}
		}else{
			//validacao .isEmpty ||
			if(textEndereco.getText().isEmpty() || textNome.getText().isEmpty()||textNumCadEst.getText().isEmpty()||textNumCadNac.getText().isEmpty()||textTelefone.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Aten��o! Verifique os campos!");
			}
			else{
				//JOptionPane.showMessageDialog(null, "Foi!");
				cliente.setCodigo(Integer.valueOf(textCodigo.getText()));
				cliente.setEndereco(textEndereco.getText());
				cliente.setNome(textNome.getText());
				cliente.setNumCadEstadual(Long.valueOf(textNumCadEst.getText()));
				cliente.setNumCadNacional(Long.valueOf(textNumCadNac.getText()));
				cliente.setTelefone(Long.valueOf(textTelefone.getText()));
				cliente.setTipo(comboBoxPessoa.getSelectedIndex());
				try {
					if(new ClienteDAO().alterar(cliente)){
						UtilMenssage.msgSucesso();
						FrmcadCliente.this.dispose();
						new FrmClientes().setVisible(true);
					}else{
						UtilMenssage.msgError();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}	
		
	}
	
	private void habilitarCampos(boolean var){
		textNumCadEst.setEnabled(var);
		textNumCadNac.setEnabled(var);
		textEndereco.setEnabled(var);
		textNome.setEnabled(var);
		textTelefone.setEnabled(var);
		btnSalvar.setEnabled(var);
		btnCancel.setEnabled(var);
		comboBoxPessoa.setEnabled(var);
		
	}
	private void limparCampos(){
		textNumCadEst.setText(null);
		textNumCadNac.setText(null);
		textEndereco.setText(null);
		textNome.setText(null);
		textTelefone.setText(null);
	}
	
	private void habilitarBotoes(boolean var){
		
		btnEditar.setEnabled(var);
		btnExcluir.setEnabled(var);
		btnSalvar.setEnabled(!var);
		
	}
}
