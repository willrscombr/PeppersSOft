package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.PeppersTableModel;
import dao.ProdutoDAO;
import modelo.Produto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class FrmProdutos extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private PeppersTableModel modelo;
	private ResultSet rs;
	private ResultSetMetaData rsmt;
	private JButton btnIncluir;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnAbrir;
	
	
	public FrmProdutos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		table = new JTable();
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {				
				int linha = table.getSelectedRow();
				
				btnAbrir.setEnabled(true);
 
				if (e.getClickCount() > 1) {  	
					Produto p = new Produto();
				    p.setCod_prod(Integer.parseInt(table.getValueAt(linha, 0).toString()));
				    p.setDescricao(table.getValueAt(linha, 1).toString());
				    p.setEstoque(Float.parseFloat(table.getValueAt(linha, 2).toString()));
				    p.setPreco_custo(Float.parseFloat(table.getValueAt(linha, 3).toString()));
				    p.setPreco_venda(Float.parseFloat(table.getValueAt(linha, 4).toString()));
				    p.setMargemlucro(Float.parseFloat(table.getValueAt(linha, 5).toString()));
				    
					FrmProdutos.this.dispose();
					new FrmcadProdutos(p).setVisible(true);
					} 
				
			}
		});
		scrollPane.setBounds(10, 11, 703, 358);
		getContentPane().add(scrollPane);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmProdutos.this.dispose();
			    new FrmcadProdutos(null).setVisible(true);
			}
		});
		
		btnIncluir.setBackground(Color.WHITE);
		btnIncluir.setBounds(10, 394, 89, 43);
		contentPane.add(btnIncluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(208, 394, 89, 43);
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(305, 394, 89, 43);
		contentPane.add(btnExcluir);
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.setEnabled(false);
		btnAbrir.setBackground(Color.WHITE);
		btnAbrir.setBounds(109, 394, 89, 43);
		contentPane.add(btnAbrir);
		
		popularTabela();
		
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void popularTabela(){
		
		try {
			
			modelo = new PeppersTableModel();
			rs = new ProdutoDAO().consultar();
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
				modelo.addRow(linha);
			}
			scrollPane.setViewportView(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro! Verifique a conexão!");
		}
	}
}

