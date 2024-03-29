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

import controle.ProducaoController;
import controle.ProdutoController;
import util.PeppersTableModel;
import util.UtilMenssage;
import modelo.Produto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class FrmProducao extends JFrame {

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
	
	public void abreProduto(){
		int linha = table.getSelectedRow();
		Produto p = new Produto();
	    p.setId_produto(Integer.parseInt(table.getValueAt(linha, 0).toString()));
	    p.setDescricao(table.getValueAt(linha, 1).toString());
	    p.setEstoque(Float.parseFloat(table.getValueAt(linha, 2).toString()));
	    p.setPr_custo(Float.parseFloat(table.getValueAt(linha, 3).toString()));
	    p.setPr_venda(Float.parseFloat(table.getValueAt(linha, 4).toString()));
	    p.setMargem_lucro(Float.parseFloat(table.getValueAt(linha, 5).toString()));
	    
		FrmProducao.this.dispose();
		new FrmcadProdutos(p).setVisible(true);
		
	}
	
	public FrmProducao() {
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
		scrollPane.setBounds(10, 32, 703, 335);
		getContentPane().add(scrollPane);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("imagens\\icones\\add_16.bmp"));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmProducao.this.dispose();
			    new FrmcadProducao().setVisible(true);
			}
		});
		
		btnIncluir.setBackground(Color.WHITE);
		btnIncluir.setBounds(10, 394, 89, 43);
		contentPane.add(btnIncluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreProduto();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(208, 394, 89, 43);
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();

				String message="Deseja realmente excluir o produto?";
				String title="Confirma��o";
				int opc=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
				if(opc == JOptionPane.YES_OPTION){
					try {
	
						if(new ProducaoController().excluir(Integer.parseInt(table.getValueAt(linha, 0).toString()))){
							UtilMenssage.msgSucesso();
							FrmProducao.this.dispose();
							new FrmProducao().setVisible(true);
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
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(305, 394, 89, 43);
		contentPane.add(btnExcluir);
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreProduto();
			}
		});
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
			rs = new ProducaoController().consultar();
			rsmt = rs.getMetaData();
			int numerodecolunas = rsmt.getColumnCount();
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnAbrir.setEnabled(true);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					if (e.getClickCount() > 1) {  
						abreProduto();
					} 
				}
			});
			
			table.setForeground(Color.RED);
			table.setModel(modelo);
			Object[] linha = null;
			
			modelo.addColumn("Codigo");
			modelo.addColumn("Data Produ��o");
			modelo.addColumn("Data lan�amento");
			modelo.addColumn("Respons�vel");

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
			JOptionPane.showMessageDialog(null, "Erro! Verifique a conex�o!");
		}
	}
}

