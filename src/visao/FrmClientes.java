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

import util.PeppersTableModel;
import util.UtilMenssage;
import dao.ClienteDAO;
import dao.ProdutoDAO;
import fabrica.ClienteFactory;
import modelo.Cliente;
import modelo.Produto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class FrmClientes extends JFrame {

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
	
	public void abreCliente(){
		int linha = table.getSelectedRow();
		Cliente cliente = new Cliente();
		cliente.setCodigo(Integer.parseInt(table.getValueAt(linha, 0).toString()));
		cliente.setNome(table.getValueAt(linha, 1).toString());
		cliente.setNumCadNacional(Integer.valueOf(table.getValueAt(linha, 2).toString()));
		cliente.setNumCadEstadual(Integer.valueOf(table.getValueAt(linha, 3).toString()));
		cliente.setTelefone(Integer.valueOf(table.getValueAt(linha, 4).toString()));
		cliente.setEndereco(table.getValueAt(linha, 5).toString());
		cliente.setEndereco(table.getValueAt(linha, 6).toString());
		
	    
		FrmClientes.this.dispose();
		new FrmcadCliente(cliente).setVisible(true);
		
	}
	
	public FrmClientes() {
		setTitle("PepperSoft - Lista de Produtos");
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
		scrollPane.setBounds(10, 11, 703, 358);
		getContentPane().add(scrollPane);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmClientes.this.dispose();
			    new FrmcadCliente(null).setVisible(true);
			}
		});
		
		btnIncluir.setBackground(Color.WHITE);
		btnIncluir.setBounds(10, 394, 89, 43);
		contentPane.add(btnIncluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreCliente();
				
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
	
						if(new ClienteDAO().excluir(Integer.parseInt(table.getValueAt(linha, 0).toString()))){
							UtilMenssage.msgSucesso();
							FrmClientes.this.dispose();
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
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(305, 394, 89, 43);
		contentPane.add(btnExcluir);
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreCliente();
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
			rs = new ClienteDAO().consultar();
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
						abreCliente();
					} 
				}
			});
			
			table.setForeground(Color.RED);
			table.setModel(modelo);
			Object[] linha = null;
			
			modelo.addColumn("codigo");
			modelo.addColumn("nome");
			modelo.addColumn("CPF/CNPJ");
			modelo.addColumn("RG/IE");
			modelo.addColumn("telefone");
			modelo.addColumn("tipo");
			modelo.addColumn("endereco");
			
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro! Verifique a conex�o!");
		}
	}
}

