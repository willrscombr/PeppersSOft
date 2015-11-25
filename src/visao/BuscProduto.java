package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.Cliente;
import modelo.Produto;
import util.PeppersTableModel;

import java.sql.ResultSetMetaData;

import dao.ClienteDAO;
import dao.ProdutoDAO;

public class BuscProduto extends JFrame{
	private JTextField textBusca;
	private JTable table;
	private PeppersTableModel modelo ;
	private ResultSet rs;
	private ResultSetMetaData rsmt;
	private JScrollPane scrollPane ;
	private Produto produto;
	

	public BuscProduto(){
		this.produto = new Produto();
		//this.cliente.setVazio();
		setTitle("PepperSoft - Pesquisa Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setEnabled(false);
		lblNome.setBounds(5, 17, 70, 15);
		getContentPane().add(lblNome);
		
		textBusca = new JTextField();
		textBusca.setBounds(93, 15, 181, 19);
		getContentPane().add(textBusca);
		textBusca.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 78, 476, 138);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton Buscar = new JButton("Buscar");
		Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ClienteDAO dao = new ClienteDAO();
				try {
					popularTabela();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		Buscar.setBounds(335, 12, 117, 25);
		getContentPane().add(Buscar);
		
		JButton Cancelar = new JButton("Cancelar");
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscProduto.this.dispose();
			}
		});
		Cancelar.setBounds(22, 228, 117, 25);
		getContentPane().add(Cancelar);
		
		JButton Confirmar = new JButton("Confirmar");
		Confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SetProduto();
				BuscProduto.this.dispose();
				
			}
		});
		Confirmar.setBounds(354, 228, 117, 25);
		getContentPane().add(Confirmar);
		
	}


private void popularTabela() throws Exception{
	
	
		
		modelo = new PeppersTableModel();
		rs = new ProdutoDAO().Busca(textBusca.getText());
		rsmt = rs.getMetaData();
		int numerodecolunas = rsmt.getColumnCount();
		table = new JTable();
		
		
		table.setForeground(Color.RED);
		table.setModel(modelo);
		Object[] linha = null;
		
		modelo.addColumn("código");
		modelo.addColumn("descrição");
		modelo.addColumn("preço custo");
		modelo.addColumn("preço venda");
		modelo.addColumn("estoque");
		modelo.addColumn("margem lucro");
		
		while (rs.next()) {
			linha = new Object[numerodecolunas];

			for (int j = 0; j < rsmt.getColumnCount(); j++) {
				linha[j] = rs.getObject(j + 1);

			}
			if(linha[5].equals(0) ){
				linha[5] = "FISICA";
			}else if(linha[5].equals(1)){
				linha[5] = "JURIDICA";
			}else{
				linha[5] = "";
			}
			modelo.addRow(linha);
		}
		scrollPane.setViewportView(table);
	
}


private void SetProduto(){
	int linha = table.getSelectedRow();
	
	this.produto.setId_produto(Integer.parseInt(table.getValueAt(linha, 0).toString()));
	this.produto.setDescricao(table.getValueAt(linha, 1).toString());
	this.produto.setPr_venda(Float.valueOf(table.getValueAt(linha, 2).toString()));
	this.produto.setEstoque(Float.valueOf(table.getValueAt(linha, 3).toString()));
	
	
}
public Produto getProduto(){
	
	return this.produto;
}
}
