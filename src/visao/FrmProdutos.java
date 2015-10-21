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
import dao.ProdutoDAO;

@SuppressWarnings("serial")
public class FrmProdutos extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private PeppersTableModel modelo;
	private ResultSet rs;
	private ResultSetMetaData rsmt;
	
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
		scrollPane.setBounds(10, 11, 703, 358);
		getContentPane().add(scrollPane);
		
		JButton button = new JButton("Incluir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBackground(Color.WHITE);
		button.setBounds(10, 394, 89, 43);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Salvar");
		button_1.setEnabled(false);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(111, 394, 89, 43);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Cancelar");
		button_2.setEnabled(false);
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(214, 394, 89, 43);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Excluir");
		button_3.setEnabled(false);
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(313, 394, 89, 43);
		contentPane.add(button_3);
		
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
			JOptionPane.showMessageDialog(null, "Deu pau");
		}
	}
}

