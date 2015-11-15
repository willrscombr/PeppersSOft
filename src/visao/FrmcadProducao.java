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
import controle.ProdutoController;
import util.PeppersTableModel;
import util.UtilMenssage;
import modelo.Produto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class FrmcadProducao extends JFrame {

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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public FrmcadProducao() {
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
		scrollPane.setBounds(10, 30, 703, 188);
		getContentPane().add(scrollPane);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("imagens\\icones\\add_16.bmp"));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmcadProducao.this.dispose();
			    new FrmcadProdutos(null).setVisible(true);
			}
		});
		
		btnIncluir.setBackground(Color.WHITE);
		btnIncluir.setBounds(10, 394, 89, 43);
		contentPane.add(btnIncluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
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
				String title="Confirmação";
				int opc=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
				if(opc == JOptionPane.YES_OPTION){
					try {
	
						if(new ProdutoController().excluir(Integer.parseInt(table.getValueAt(linha, 0).toString()))){
							UtilMenssage.msgSucesso();
							FrmcadProducao.this.dispose();
							new FrmcadProducao().setVisible(true);
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
			}
		});
		btnAbrir.setEnabled(false);
		btnAbrir.setBackground(Color.WHITE);
		btnAbrir.setBounds(109, 394, 89, 43);
		contentPane.add(btnAbrir);
		
		JLabel lblDataProduo = new JLabel("Data Produ\u00E7\u00E3o");
		lblDataProduo.setBounds(530, 253, 81, 14);
		contentPane.add(lblDataProduo);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(604, 250, 109, 20);
		contentPane.add(formattedTextField);
		
		JLabel lblItemProduo = new JLabel("Item Produ\u00E7\u00E3o");
		lblItemProduo.setBounds(10, 253, 79, 14);
		contentPane.add(lblItemProduo);
		
		textField = new JTextField();
		textField.setBounds(84, 250, 34, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(121, 250, 184, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(457, 250, 63, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(398, 253, 63, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setBounds(312, 253, 46, 14);
		contentPane.add(lblUnidade);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"UN", "CX", "KG"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(353, 250, 41, 20);
		contentPane.add(comboBox);
		
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
			rs = new ProdutoController().consultar();
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
					} 
				}
			});
			
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

