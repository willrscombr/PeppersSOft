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
import modelo.Produto;
import util.PeppersTableModel;
import util.UtilMenssage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class FrmcadProducao extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private PeppersTableModel modelo;
	private ResultSet rs;
	private ResultSetMetaData rsmt;
	private JTextField textCodigoItem;
	private JTextField textDescricao;
	private JTextField textQtd;
	private JComboBox comboBox;
	private JButton btnIncluir;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JTextField textField;

	public FrmcadProducao() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		table = new JTable();
		contentPane.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 703, 188);
		getContentPane().add(scrollPane);
		
		JLabel lblItemProduo = new JLabel("Item Produ\u00E7\u00E3o");
		lblItemProduo.setBounds(10, 253, 99, 14);
		contentPane.add(lblItemProduo);
		
		textCodigoItem = new JTextField();
		textCodigoItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					comboBox.requestFocus();
				}
			}
		});
		textCodigoItem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				ProdutoController consulta = new ProdutoController();
				try {
					Produto produto = consulta.consultar(Integer.parseInt(textCodigoItem.getText()));
					textDescricao.setText(produto.getDescricao());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		textCodigoItem.setBounds(119, 247, 34, 20);
		contentPane.add(textCodigoItem);
		textCodigoItem.setColumns(10);
		
		textDescricao = new JTextField();
		textDescricao.setBounds(156, 247, 184, 20);
		textDescricao.setEditable(false);
		contentPane.add(textDescricao);
		textDescricao.setColumns(10);
		
		textQtd = new JTextField();
		textQtd.setBounds(277, 286, 63, 20);
		contentPane.add(textQtd);
		textQtd.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(187, 289, 89, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setBounds(10, 289, 64, 14);
		contentPane.add(lblUnidade);
		
		comboBox = new JComboBox();
		comboBox.setBounds(84, 283, 63, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"UN", "CX", "KG"}));
		comboBox.setToolTipText("");
		contentPane.add(comboBox);
		
		JButton button = new JButton("Editar");
		button.setBounds(109, 393, 89, 43);
		button.setEnabled(false);
		button.setBackground(Color.WHITE);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Salvar");
		button_1.setBounds(10, 393, 89, 43);
		button_1.setBackground(Color.WHITE);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Cancelar");
		button_2.setBounds(208, 393, 89, 43);
		button_2.setBackground(Color.WHITE);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Excluir");
		button_3.setBounds(305, 393, 89, 43);
		button_3.setEnabled(false);
		button_3.setBackground(Color.WHITE);
		contentPane.add(button_3);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(103, 11, 138, 20);
		contentPane.add(formattedTextField);
		
		JLabel label = new JLabel("Data Produ\u00E7\u00E3o");
		label.setBounds(10, 14, 89, 14);
		contentPane.add(label);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSalvar.setBounds(94, 314, 89, 23);
		contentPane.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(187, 314, 89, 23);
		contentPane.add(btnCancelar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(277, 314, 89, 23);
		contentPane.add(btnExcluir);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSalvar.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnIncluir.setEnabled(false);
			}
		});
		btnIncluir.setBounds(10, 314, 79, 23);
		contentPane.add(btnIncluir);
		
		JLabel lblResponsvel = new JLabel("Respons\u00E1vel");
		lblResponsvel.setBounds(251, 14, 89, 14);
		contentPane.add(lblResponsvel);
		
		textField = new JTextField();
		textField.setBounds(350, 11, 174, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.setBounds(376, 314, 89, 23);
		contentPane.add(btnNewButton);
		
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
					//btnAbrir.setEnabled(true);
					//btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					if (e.getClickCount() > 1) {  
					//	abreProduto();
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

