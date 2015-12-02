package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;









import util.PeppersTableModel;
import util.UtilMenssage;
import dao.ClienteDAO;
import dao.VendasDao;
import modelo.Cliente;
import modelo.ItemVenda;
import modelo.Venda;
import modelo.Produto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;



@SuppressWarnings("serial")
public class FrmPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textProdDes;
	private JTextField textQuant;
	private JTextField textProduVal;
	private JLabel labelcod2;
	private JLabel lblCod; 
	private JTable table;
	private JScrollPane  scrollPane;
	private Venda venda;
	private JTextField textSubtotal;
	private ResultSet rs;
	private ResultSetMetaData rsmt;
	private PeppersTableModel modelo;
	private Produto produto;
	private List listaitemvenda;
	private JButton btnExcluirItem;
	private JButton btnAlterarItem ;
	private List rowlist;
	private Cliente cliente ;
	private int cont = 0;

	public FrmPedido() {
		venda = new Venda();
		listaitemvenda = new ArrayList<ItemVenda>();
		modelo = new PeppersTableModel();
		table = new JTable();
		
		
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
		
		textProdDes = new JTextField();
		textProdDes.setBounds(67, 57, 156, 19);
		contentPane.add(textProdDes);
		textProdDes.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quanti: ");
		lblQuantidade.setBounds(292, 57, 64, 15);
		contentPane.add(lblQuantidade);
		
		textQuant = new JTextField();
		textQuant.setBounds(342, 57, 53, 19);
		contentPane.add(textQuant);
		textQuant.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(405, 59, 70, 15);
		contentPane.add(lblValor);
		
		textProduVal = new JTextField();
		textProduVal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textSubtotal.setText(String.valueOf(Float.valueOf(textQuant.getText())*Float.valueOf(textProduVal.getText())));
			}
		});
		textProduVal.setBounds(439, 57, 92, 19);
		contentPane.add(textProduVal);
		textProduVal.setColumns(10);
		
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
		
		JLabel lblSubtotal = new JLabel("SubTotal:");
		lblSubtotal.setBounds(556, 57, 70, 15);
		contentPane.add(lblSubtotal);
		
		JButton btnBusca = new JButton("Buscar Cliente");
		btnBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			BuscCliente busccli =	new BuscCliente();
			busccli.setVisible(true);
			busccli.addWindowListener(new WindowAdapter(){
				public void windowClosed(WindowEvent e){
					//JOptionPane.showMessageDialog(null,"fechou");
					cliente = busccli.getCliente();
					lblClie.setText(cliente.getNome());
					labelcod2.setText(String.valueOf(cliente.getCodigo()));
					lblCp.setText(String.valueOf(cliente.getNumCadNacional()));
					
				}
			
			});
			
			
			}
		});
		btnBusca.setBounds(558, 0, 152, 25);
		contentPane.add(btnBusca);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 112, 699, 255);
		contentPane.add(scrollPane);
		
		popularTabela();
		//scrollPane.setViewportView(table);
		
		
		
		btnAlterarItem = new JButton("alterar");
		btnAlterarItem.setEnabled(false);
		btnAlterarItem.setBounds(150, 379, 117, 25);
		contentPane.add(btnAlterarItem);
		
		btnExcluirItem = new JButton("excluir");
		btnExcluirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				listaitemvenda.remove(linha);
				modelo.removeRow(linha);
				System.out.println(listaitemvenda.size());
				
			}
		});
		btnExcluirItem.setEnabled(false);
		btnExcluirItem.setBounds(281, 379, 117, 25);
		contentPane.add(btnExcluirItem);
		
		JButton btnNewButton_3 = new JButton("Finalizar Pedido");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				venda.setCliente(cliente);
				venda.setListaitempedido(listaitemvenda);
			try {
				System.out.println(	new VendasDao().buscarUltimaVenda());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
					//FrmPedido.this.dispose();
				
			}
		});
		btnNewButton_3.setBounds(558, 379, 169, 25);
		contentPane.add(btnNewButton_3);
		
		JButton btnP = new JButton("P");
		btnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscProduto bproduto = new BuscProduto();
				bproduto.setVisible(true);
				bproduto.addWindowListener(new WindowAdapter(){
						public void windowClosed(WindowEvent e){
							produto = bproduto.getProduto();
							textProdDes.setText(String.valueOf(produto.getDescricao()));
							textQuant.setText("0");
							textProduVal.setText(String.valueOf(produto.getPr_venda()));
						}
				});
			
				
				
				
			}
		});
		btnP.setBounds(226, 55, 44, 23);
		contentPane.add(btnP);
		

		JButton btnIncluirItem = new JButton("incluir");
		
		btnIncluirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cont = cont+1;
				rowlist = new ArrayList<String>();
				rowlist.add(cont);
				rowlist.add(produto.getId_produto());
				rowlist.add(produto.getDescricao());
				rowlist.add(textQuant.getText());
				rowlist.add(produto.getPr_venda());
				rowlist.add(Float.valueOf(textQuant.getText()) * produto.getPr_venda());
				modelo.addRow(rowlist.toArray());
				listaitemvenda.add(new ItemVenda(cont,produto,Float.valueOf(textQuant.getText())));
				
				
				
			}
		});
		btnIncluirItem.setBounds(22, 379, 117, 25);
		contentPane.add(btnIncluirItem);
		
		textSubtotal = new JTextField("");
		textSubtotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		textSubtotal.setBounds(612, 57, 70, 17);
		contentPane.add(textSubtotal);
		
		
	}
private void popularTabela(){
		
		try {
			//JOptionPane.showMessageDialog(null, "teste");
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						btnAlterarItem.setEnabled(true);
						btnExcluirItem.setEnabled(true);
					if (e.getClickCount() > 1) {  
						
					} 
				}
			});
			
			table.setForeground(Color.RED);
			table.setModel(modelo);
			Object[] linha = null;
			modelo.addColumn("Indice");
			modelo.addColumn("cod Produto");
			modelo.addColumn("descricao");
			modelo.addColumn("quantidade");
			modelo.addColumn("valor");
			modelo.addColumn("subtotal");
			//modelo.addColumn("tipo");
			//modelo.addColumn("endereco");
			
		
			scrollPane.setViewportView(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro! Verifique a conex�o!");
		}
	}
	public void atualizarTabela(){
		
	}
}
