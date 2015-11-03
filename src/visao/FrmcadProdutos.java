package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.ProdutoDAO;
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

@SuppressWarnings("serial")
public class FrmcadProdutos extends JFrame {

	private JPanel contentPane;
	private JButton btnSalvar;
	private JButton btnCancel;
	private JButton btnExcluir;
	private JTextField textDesc;
	private JTextField textPrCusto;
	private JTextField texLucro;
	private JTextField txtCod;
	private JTextField textPrVenda;
	private JTextField textEstoque;
	private JButton btnEditar;

	public FrmcadProdutos(Produto p) {
	
		setTitle("PepperSoft - Cadastro de Produtos");
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
				 if(e.getKeyCode()==10){  
	                  salvaProduto();    
	                }  
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaProduto();
			}
		});
		btnSalvar.setIcon(null);
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setBounds(10, 394, 89, 43);
		contentPane.add(btnSalvar);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmcadProdutos.this.dispose();
				new FrmProdutos().setVisible(true);
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
				String title="Confirmação";
				int opc=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
				if(opc == JOptionPane.YES_OPTION){
					try {
	
						if(new ProdutoDAO().excluir(Integer.parseInt(txtCod.getText()))){
							UtilMenssage.msgSucesso();
							FrmcadProdutos.this.dispose();
							new FrmProdutos().setVisible(true);
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
		
		textDesc = new JTextField();
		textDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode()==10){  
	                    textPrCusto.requestFocus();  
	                }  
			}
		});
		textDesc.setColumns(10);
		textDesc.setBounds(175, 140, 418, 20);
		contentPane.add(textDesc);
		
		textPrCusto = new JTextField();
		textPrCusto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode()==10){  
	                    texLucro.requestFocus();  
	                }  
			}
		});
		textPrCusto.setColumns(10);
		textPrCusto.setBounds(175, 183, 122, 20);
		contentPane.add(textPrCusto);
		
		JLabel lblNome = new JLabel("Descri\u00E7\u00E3o");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(66, 141, 73, 19);
		contentPane.add(lblNome);
		
		JLabel lblUsuario = new JLabel("Pre\u00E7o Custo");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(66, 184, 99, 19);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha_1 = new JLabel("Margem Lucro");
		lblSenha_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha_1.setBounds(66, 229, 99, 19);
		contentPane.add(lblSenha_1);
		
		texLucro = new JTextField();
		texLucro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode()==10){  
	                    textPrVenda.requestFocus();  
	                }  
			}
		});
		texLucro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				double prcusto=(Double.parseDouble(textPrCusto.getText())),
					  prvenda=(Double.parseDouble(textPrVenda.getText()));
				//Margem de lucro = preço de venda – custo de produção / preço de venda x 100
				double margem= (prvenda - prcusto) / prvenda*100;
				DecimalFormat df = new DecimalFormat("0.##");  
				String dx = df.format(margem);  
				try {
					texLucro.setText(String.valueOf(dx));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				double margem = Double.parseDouble(texLucro.getText()),
					  prcusto=(Double.parseDouble(textPrCusto.getText()))	;
				double prvenda=prcusto+(prcusto*(margem/100));
				DecimalFormat df = new DecimalFormat("0.##");  
				String dx = df.format(prvenda);  
				textPrVenda.setText(String.valueOf(dx));
			}
		});
		texLucro.setColumns(10);
		texLucro.setBounds(175, 228, 122, 20);
		contentPane.add(texLucro);
		
		textPrVenda = new JTextField();
		textPrVenda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode()==10){  
	                    textEstoque.requestFocus();  
	                }  
			}
		});
		textPrVenda.setColumns(10);
		textPrVenda.setBounds(446, 228, 122, 20);
		contentPane.add(textPrVenda);
		
		JLabel lblCodigoProduto = new JLabel("Codigo Produto");
		lblCodigoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoProduto.setBounds(66, 89, 113, 31);
		contentPane.add(lblCodigoProduto);
		
		txtCod = new JTextField();
		txtCod.setEditable(false);
		txtCod.setColumns(10);
		txtCod.setBounds(175, 96, 122, 20);
		contentPane.add(txtCod);
		
		JLabel lblPreoVenda = new JLabel("Pre\u00E7o Venda");
		lblPreoVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreoVenda.setBounds(361, 229, 99, 19);
		contentPane.add(lblPreoVenda);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstoque.setBounds(66, 272, 99, 19);
		contentPane.add(lblEstoque);
		
		textEstoque = new JTextField();
		textEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode()==10){  
	                    btnSalvar.requestFocus();  
	             }  
			}
		});
		textEstoque.setColumns(10);
		textEstoque.setBounds(175, 273, 122, 20);
		contentPane.add(textEstoque);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textDesc.setEditable(true);
				textEstoque.setEditable(true);
				textPrCusto.setEditable(true);
				textPrVenda.setEditable(true);
				texLucro.setEditable(true);
				btnSalvar.setEnabled(true);
				btnExcluir.setEnabled(false);
				btnEditar.setEnabled(false);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(109, 394, 89, 43);
		contentPane.add(btnEditar);
		textDesc.requestFocus();
		
		if(p!=null){
			txtCod.setText(Integer.toString(p.getCod_prod()));
			textDesc.setText(p.getDescricao());			
			textEstoque.setText(Float.toString(p.getEstoque()));			
			textPrCusto.setText(Float.toString(p.getPreco_custo()));		
			textPrVenda.setText(Float.toString(p.getPreco_venda()));
			texLucro.setText(Float.toString(p.getMargemlucro()));
			textDesc.setEditable(false);
			textEstoque.setEditable(false);
			textPrCusto.setEditable(false);
			textPrVenda.setEditable(false);
			texLucro.setEditable(false);		
			btnExcluir.setEnabled(true);
			btnEditar.setEnabled(true);	
			btnSalvar.setEnabled(false);
		}
		
	}
//método para salvar, editar produto...
	protected void salvaProduto() {
		Produto produto = new Produto();
		if(txtCod.getText().isEmpty()){
			
			if(textDesc.getText().isEmpty() || textEstoque.getText().isEmpty()||textPrCusto.getText().isEmpty()||textPrVenda.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Atenção! Verifique os campos!");
			}
			else{
				produto.setDescricao(textDesc.getText());
				produto.setPreco_custo(Float.parseFloat(textPrCusto.getText()));
				produto.setPreco_venda(Float.parseFloat(textPrVenda.getText()));
				produto.setMargemlucro(Float.parseFloat(texLucro.getText()));
				produto.setEstoque(Long.valueOf(textEstoque.getText().trim()));
				if(new ProdutoDAO().cadastrar(produto)){
					UtilMenssage.msgSucesso();
					FrmcadProdutos.this.dispose();
					new FrmProdutos().setVisible(true);
				}else{
					UtilMenssage.msgError();
				}
			}
		}else{
			if(textDesc.getText().isEmpty() || textEstoque.getText().isEmpty()||textPrCusto.getText().isEmpty()||textPrVenda.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Atenção! Verifique os campos!");
			}
			else{
				produto.setCod_prod(Integer.parseInt(txtCod.getText()));
				produto.setDescricao(textDesc.getText());
				produto.setPreco_custo(Float.parseFloat(textPrCusto.getText()));
				produto.setPreco_venda(Float.parseFloat(textPrVenda.getText()));
				produto.setMargemlucro(Float.parseFloat(texLucro.getText()));
				produto.setEstoque(Float.parseFloat(textEstoque.getText()));
				try {
					if(new ProdutoDAO().alterar(produto)){
						UtilMenssage.msgSucesso();
						FrmcadProdutos.this.dispose();
						new FrmProdutos().setVisible(true);
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
}
