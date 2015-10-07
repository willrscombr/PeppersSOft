package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import dao.ClienteDAO;
import fabrica.ClienteFactory;
import interfaces.Cliente;

@SuppressWarnings("serial")
public class FrmClientes extends JFrame {

	private JTextField textField;
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel();
	private ResultSet rs ;
	private ResultSetMetaData rsmt;
	private JTextField textCodigo;
	private JTextField textNome;
	

	public FrmClientes(){
		setTitle("Lista de Clientes");
		getContentPane().setLayout(null);
		setSize(800,800);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(512, 40, 236, 309);
		getContentPane().add(scrollPane);
		try {
			rs = new ClienteDAO().consultar();
			rsmt = rs.getMetaData();
			int numerodecolunas = rsmt.getColumnCount();
			table = new JTable();
			table.setModel(modelo);
			/*for (int i = 0; i < numerodecolunas; i++) {
				//new String []
				modelo.addColumn(rsmt.getColumnLabel(i));
			}*/
			
			Object[] linha = null;
			modelo.addColumn("codigo");
			modelo.addColumn("nome");
			
			while(rs.next()){
				linha = new Object [numerodecolunas];
				
				for (int j = 0; j < rsmt.getColumnCount(); j++) {
					linha[j] = rs.getObject(j+1);
					
				}
				modelo.addRow(linha);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Deu pau");
		}
		
		
		
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 32, 447, 317);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(12, 14, 54, 15);
		panel.add(lblCodigo);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 66, 70, 36);
		panel.add(lblNome);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(151, 12, 114, 19);
		panel.add(textCodigo);
		textCodigo.setColumns(10);
		
		textNome = new JTextField();
		textNome.setBounds(151, 75, 114, 19);
		panel.add(textNome);
		textNome.setColumns(10);
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente cliente  = new ClienteFactory().clienteFisica();
				
				cliente.setNome(textNome.getText());
				cliente.setEndereco("Teste");
				cliente.setTelefone(00000000);
				new ClienteDAO().cadastrar(cliente);
				
			}
		});
		btnAdicionar.setBounds(42, 207, 117, 25);
		panel.add(btnAdicionar);
		
		
	}
}
