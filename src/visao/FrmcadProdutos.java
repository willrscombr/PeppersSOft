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
import fabrica.ClienteFactory;
import interfaces.Cliente;
import modelo.Produto;
import util.UtilMenssage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class FrmcadProdutos extends JFrame {

	private JPanel contentPane;
	private JTextField textDesc;
	private JTextField textPrCusto;
	private JTextField texLucro;

	private JButton btnSalvar;
	private JButton btnCancel;
	private JButton btnExcluir;
	private JTextField txtCod;
	private JTextField textPrVenda;
	private JTextField textEstoque;

	public FrmcadProdutos() {
		setTitle("PepperSoft - Cadastro de Produtos");
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
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = new Produto();
				
				if(textDesc.getText().isEmpty() || textEstoque.getText().isEmpty()||textPrCusto.getText().isEmpty()||textPrVenda.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Atenção! Verifique os campos!");
				}
				else{
					produto.setDescricao(textDesc.getText());
					produto.setPreco_custo(Float.parseFloat(textPrVenda.getText()));
					produto.setPreco_venda(Long.valueOf(textEstoque.getText().trim()));
					produto.setEstoque(Long.valueOf(texLucro.getText().trim()));
					if(new ProdutoDAO().cadastrar(produto)){
						UtilMenssage.msgSucesso();
						FrmcadProdutos.this.dispose();
						new FrmProdutos().setVisible(true);
					}else{
						UtilMenssage.msgError();
					}
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
				
				FrmcadProdutos.this.dispose();
				new FrmProdutos().setVisible(true);
			}
		});
		btnCancel.setIcon(null);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(109, 394, 89, 43);
		contentPane.add(btnCancel);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setIcon(null);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(307, 394, 89, 43);
		contentPane.add(btnExcluir);
		
		textDesc = new JTextField();
		textDesc.setColumns(10);
		textDesc.setBounds(175, 140, 418, 20);
		contentPane.add(textDesc);
		
		textPrCusto = new JTextField();
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
		texLucro.setColumns(10);
		texLucro.setBounds(175, 228, 122, 20);
		contentPane.add(texLucro);
		
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
		lblPreoVenda.setBounds(362, 182, 99, 19);
		contentPane.add(lblPreoVenda);
		
		textPrVenda = new JTextField();
		textPrVenda.setColumns(10);
		textPrVenda.setBounds(471, 183, 122, 20);
		contentPane.add(textPrVenda);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstoque.setBounds(66, 272, 99, 19);
		contentPane.add(lblEstoque);
		
		textEstoque = new JTextField();
		textEstoque.setColumns(10);
		textEstoque.setBounds(175, 273, 122, 20);
		contentPane.add(textEstoque);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(208, 394, 89, 43);
		contentPane.add(btnEditar);
	}
}
