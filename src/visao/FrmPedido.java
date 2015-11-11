package visao;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FrmPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	public FrmPedido() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(30, 83, 70, 15);
		contentPane.add(lblCliente);
		
		textField = new JTextField();
		textField.setBounds(98, 81, 92, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade: ");
		lblQuantidade.setBounds(196, 83, 92, 15);
		contentPane.add(lblQuantidade);
		
		textField_1 = new JTextField();
		textField_1.setBounds(304, 81, 70, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(415, 83, 70, 15);
		contentPane.add(lblValor);
		
		textField_2 = new JTextField();
		textField_2.setBounds(467, 81, 114, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(85, 29, 70, 15);
		contentPane.add(lblNome);
		
		JLabel lblClie = new JLabel("Clie");
		lblClie.setBounds(151, 29, 70, 15);
		contentPane.add(lblClie);
		
		JLabel lblCpfcnpj = new JLabel("Cpf/Cnpj");
		lblCpfcnpj.setBounds(265, 29, 70, 15);
		contentPane.add(lblCpfcnpj);
		
		JLabel lblCp = new JLabel("CP");
		lblCp.setBounds(374, 29, 70, 15);
		contentPane.add(lblCp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 280, 286, -136);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"4", "4", "1", "asdasda", "1"},
				{"3", "3", "1", "zxczxc", "2"},
			},
			new String[] {
				"Subtotal", "Valor", "Quant", "Descricao", "CodProduto"
			}
		));
		scrollPane.setViewportView(table);
	}
}
