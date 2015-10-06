package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FrmClientes extends JFrame {
	private JTextField textField;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable table;

	public FrmClientes() {
		setTitle("Lista de Clientes");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 26, 678, 474);
		getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(698, 26, 346, 474);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "Wilto n"},
			},
			new String[] {
				"Codigo", "Nome"
			}
		));
		scrollPane.setViewportView(table);
		modelo.addColumn("CodCliente");
		modelo.addColumn("NomeCliente");
		
	}
}
