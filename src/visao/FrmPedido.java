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
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JLabel labelcod2;
	private JLabel lblCod; 
	private JTextField textField_3;
	private JTable table_1;

	public FrmPedido() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(12, 59, 70, 15);
		contentPane.add(lblProduto);
		
		textField = new JTextField();
		textField.setBounds(81, 57, 156, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quanti: ");
		lblQuantidade.setBounds(292, 57, 64, 15);
		contentPane.add(lblQuantidade);
		
		textField_1 = new JTextField();
		textField_1.setBounds(347, 57, 53, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(405, 59, 70, 15);
		contentPane.add(lblValor);
		
		textField_2 = new JTextField();
		textField_2.setBounds(451, 57, 92, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(142, 6, 70, 15);
		contentPane.add(lblNome);
		
		JLabel lblClie = new JLabel("*.*");
		lblClie.setBounds(224, 6, 70, 15);
		contentPane.add(lblClie);
		
		JLabel lblCpfcnpj = new JLabel("Cpf/Cnpj:");
		lblCpfcnpj.setBounds(364, 6, 70, 15);
		contentPane.add(lblCpfcnpj);
		
		JLabel lblCp = new JLabel("*.*");
		lblCp.setBounds(453, 6, 70, 15);
		contentPane.add(lblCp);
		
		lblCod = new JLabel("Cod:");
		lblCod.setBounds(12, 6, 70, 15);
		contentPane.add(lblCod);
		
		labelcod2 = new JLabel("*.*");
		labelcod2.setBounds(59, 6, 70, 15);
		contentPane.add(labelcod2);
		
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
		
		JLabel lblSubtotal = new JLabel("SubTotal:");
		lblSubtotal.setBounds(556, 57, 70, 15);
		contentPane.add(lblSubtotal);
		
		textField_3 = new JTextField();
		textField_3.setBounds(635, 57, 83, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnBusca = new JButton("Buscar Cliente");
		btnBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BuscCliente().setVisible(true);
			}
		});
		btnBusca.setBounds(558, 0, 152, 25);
		contentPane.add(btnBusca);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 112, 699, 255);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnIncluirItem = new JButton("incluir");
		btnIncluirItem.setBounds(22, 379, 117, 25);
		contentPane.add(btnIncluirItem);
		
		JButton btnAlterarItem = new JButton("alterar");
		btnAlterarItem.setBounds(150, 379, 117, 25);
		contentPane.add(btnAlterarItem);
		
		JButton btnExcluirItem = new JButton("excluir");
		btnExcluirItem.setBounds(281, 379, 117, 25);
		contentPane.add(btnExcluirItem);
		
		JButton btnNewButton_3 = new JButton("Finalizar Pedido");
		btnNewButton_3.setBounds(558, 379, 169, 25);
		contentPane.add(btnNewButton_3);
		
		
	}
}
