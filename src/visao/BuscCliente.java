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
import util.PeppersTableModel;

import java.sql.ResultSetMetaData;

import dao.ClienteDAO;

public class BuscCliente extends JFrame{
	private JTextField textBusca;
	private JTable table;
	private PeppersTableModel modelo ;
	private ResultSet rs;
	private ResultSetMetaData rsmt;
	private JScrollPane scrollPane ;
	private Cliente cliente;
	

	public BuscCliente(){
		this.cliente = new Cliente();
		//this.cliente.setVazio();
		setTitle("PepperSoft - Pesquisa Cliente");
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
				BuscCliente.this.dispose();
			}
		});
		Cancelar.setBounds(22, 228, 117, 25);
		getContentPane().add(Cancelar);
		
		JButton Confirmar = new JButton("Confirmar");
		Confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SetCliente();
				BuscCliente.this.dispose();
				
			}
		});
		Confirmar.setBounds(354, 228, 117, 25);
		getContentPane().add(Confirmar);
		
	}


private void popularTabela() throws Exception{
	
	
		
		modelo = new PeppersTableModel();
		rs = new ClienteDAO().Busca(textBusca.getText());
		rsmt = rs.getMetaData();
		int numerodecolunas = rsmt.getColumnCount();
		table = new JTable();
		
		
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
	
}


private void SetCliente(){
	int linha = table.getSelectedRow();
	
	this.cliente.setCodigo(Integer.parseInt(table.getValueAt(linha, 0).toString()));
	this.cliente.setNome(table.getValueAt(linha, 1).toString());
	this.cliente.setNumCadNacional(Long.valueOf(table.getValueAt(linha, 2).toString()));
	this.cliente.setNumCadEstadual(Long.valueOf(table.getValueAt(linha, 3).toString()));
	this.cliente.setTelefone(Long.valueOf(table.getValueAt(linha, 4).toString()));
	this.cliente.setEndereco(table.getValueAt(linha, 5).toString());
	this.cliente.setEndereco(table.getValueAt(linha, 6).toString());
	
}
public Cliente getCliente(){
	
	return this.cliente;
}
}
