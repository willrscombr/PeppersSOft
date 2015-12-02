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















import controle.FinanceiroController;
import util.PeppersTableModel;
import util.UtilMenssage;
import dao.ClienteDAO;
import dao.VendasDao;
import modelo.Cliente;
import modelo.Conta;
import modelo.Financeiro;
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
import java.util.Iterator;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



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
	private Cliente cliente = null;
	private int cont = 0;
	private Float totalpedido = (float) 0;
	private JLabel lblvalordes;
	private JLabel lblValorTotal;
	private JButton btnIncluirItem ;

	public FrmPedido() {
		setTitle("PepperSoft - Registro de Venda");
		venda = new Venda();
		listaitemvenda = new ArrayList<ItemVenda>();
		modelo = new PeppersTableModel();
		table = new JTable();
		produto = new Produto();
		produto.setId_produto(0);
		
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
		textProdDes.setEditable(false);
		textProdDes.setBounds(67, 57, 156, 19);
		contentPane.add(textProdDes);
		textProdDes.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quanti: ");
		lblQuantidade.setBounds(292, 57, 64, 15);
		contentPane.add(lblQuantidade);
		
		textQuant = new JTextField();
		textQuant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				String caracteres="0987654321.";
			       if(!caracteres.contains(ev.getKeyChar()+"")){
			              ev.consume();
			       }
			}
		});
		textQuant.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textSubtotal.setText(String.valueOf(Float.valueOf(textQuant.getText())*Float.valueOf(textProduVal.getText())));
			}
		});
		textQuant.setText("1");
		textQuant.setBounds(342, 57, 53, 19);
		contentPane.add(textQuant);
		textQuant.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(405, 59, 70, 15);
		contentPane.add(lblValor);
		
		textProduVal = new JTextField();
		textProduVal.setEditable(false);
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
		
		JLabel lblClie = new JLabel("");
		lblClie.setBounds(224, 6, 70, 15);
		contentPane.add(lblClie);
		
		JLabel lblCpfcnpj = new JLabel("Cpf/Cnpj:");
		lblCpfcnpj.setBounds(364, 6, 70, 15);
		contentPane.add(lblCpfcnpj);
		
		JLabel lblCp = new JLabel("0");
		lblCp.setBounds(453, 6, 70, 15);
		contentPane.add(lblCp);
		
		lblCod = new JLabel("Cod:");
		lblCod.setBounds(12, 6, 70, 15);
		contentPane.add(lblCod);
		
		labelcod2 = new JLabel("0");
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
		btnAlterarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				//listaitemvenda.set(linha, );
			}
		});
		btnAlterarItem.setEnabled(false);
		btnAlterarItem.setBounds(150, 403, 117, 25);
		contentPane.add(btnAlterarItem);
		
		btnExcluirItem = new JButton("excluir");
		btnExcluirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				totalpedido = (totalpedido - Float.valueOf(table.getValueAt(linha, 5).toString()));
				lblValorTotal.setText(String.valueOf(totalpedido));
				listaitemvenda.remove(linha);
				modelo.removeRow(linha);
				//lblValorTotal.setText(String.valueOf(totalpedido - (((ItemVenda) listaitemvenda.get(linha-1)).getProduto().getPr_venda())));
				//System.out.println(listaitemvenda.size());
				textProdDes.setText(null);
				textQuant.setText(String.valueOf(1));
				textProduVal.setText(null);
				textSubtotal.setText(null);

				
			}
		});
		btnExcluirItem.setEnabled(false);
		btnExcluirItem.setBounds(281, 403, 117, 25);
		contentPane.add(btnExcluirItem);
		
		JButton btnNewButton_3 = new JButton("Finalizar Pedido");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				venda.setListaitempedido(listaitemvenda);
				if(((Integer.valueOf(labelcod2.getText()) > 0 && venda.getListaitempedido().size() > 0 )) ){
					venda.setCliente(cliente);
				
			try {
				new VendasDao().cadastrar(venda);
				Iterator  itotalvenda = venda.getListaitempedido().iterator();
				Float totalvenda = (float) 0;
				while (itotalvenda.hasNext()) {
					ItemVenda itemvenda = (ItemVenda) itotalvenda.next();
					totalvenda = totalvenda + itemvenda.getSubtotal();
					
				}
				Financeiro finan = new Financeiro();
				Conta conta = new Conta();
				conta.setId_conta(3);
				conta.setDescricao("VENDAS");
				conta.setTipo("C");
				finan.setConta(conta);
				finan.setDiscriminacao(" Venda : " + venda.getCodVenda()+" Cliente: "+venda.getCliente().getNome());
				finan.setValor(totalvenda);
				new FinanceiroController().cadastrar(finan);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
					FrmPedido.this.dispose();
					JOptionPane.showMessageDialog(null, "Venda gravada com sucesso !!!!");
				
			}else{
				JOptionPane.showMessageDialog(null,"Cliente ou item vazio");
			}
			}
		});
		btnNewButton_3.setBounds(547, 403, 180, 25);
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
							btnIncluirItem.setEnabled(true);
							textProduVal.setText(String.valueOf(produto.getPr_venda()));
						}
				});
			
				
				
				
			}
		});
		btnP.setBounds(226, 55, 44, 23);
		contentPane.add(btnP);
		

		btnIncluirItem = new JButton("incluir");
		
		btnIncluirItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((!textProdDes.getText().equals(null)) && (!(produto.getId_produto() == 0))){
					boolean encontrou = false;
					for(int i = 0; i < listaitemvenda.size(); i++){
						if(((ItemVenda) listaitemvenda.get(i)).getProduto().getId_produto() == produto.getId_produto()){
							encontrou = true;
						}
					}
				if(!encontrou){
					if(!(produto.getEstoque() - Float.valueOf(textQuant.getText()) < 0)){
				cont = cont+1;
				rowlist = new ArrayList<String>();
				rowlist.add(cont);
				rowlist.add(produto.getId_produto());
				rowlist.add(produto.getDescricao());
				rowlist.add(textQuant.getText());
				rowlist.add(produto.getPr_venda());
				rowlist.add(Float.valueOf(textQuant.getText()) * produto.getPr_venda());
				modelo.addRow(rowlist.toArray());
				//Altera o estoque dentro do objeto produto para atualizar no banco de dados
				produto.setEstoque(produto.getEstoque() - Float.valueOf(textQuant.getText()));
				
				listaitemvenda.add(new ItemVenda(cont,produto,Float.valueOf(textQuant.getText())));
				totalpedido = totalpedido + (Float.valueOf(textQuant.getText()) * produto.getPr_venda());
				lblValorTotal.setText(String.valueOf(totalpedido));
				textProdDes.setText(null);
				textQuant.setText(String.valueOf(1));
				textProduVal.setText(null);
				textSubtotal.setText(null);
				btnIncluirItem.setEnabled(false);
					}else{
						JOptionPane.showMessageDialog(null,"Estoque Insuficiente");
					}
				}else{
					JOptionPane.showMessageDialog(null,"Produto ja adicionado");
				}	
				}else{
					JOptionPane.showMessageDialog(null,"Produto nao selecionado");
				}
				
				
				
			}
		});
		btnIncluirItem.setBounds(22, 403, 117, 25);
		contentPane.add(btnIncluirItem);
		
		textSubtotal = new JTextField("");
		textSubtotal.setEditable(false);
		textSubtotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		textSubtotal.setBounds(612, 57, 70, 17);
		contentPane.add(textSubtotal);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmPedido.this.dispose();
			}
		});
		btnCancelar.setBounds(410, 404, 121, 23);
		contentPane.add(btnCancelar);
		
		lblvalordes = new JLabel("Valor Total: R$ ");
		lblvalordes.setBounds(439, 378, 149, 14);
		contentPane.add(lblvalordes);
		
		lblValorTotal = new JLabel("");
		lblValorTotal.setForeground(Color.GREEN);
		lblValorTotal.setBounds(564, 378, 101, 14);
		contentPane.add(lblValorTotal);
		
		
	}
private void popularTabela(){
		
		try {
			//JOptionPane.showMessageDialog(null, "teste");
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						//btnAlterarItem.setEnabled(true);
						btnExcluirItem.setEnabled(true);
					if (e.getClickCount() > 1) {  
						int linha = table.getSelectedColumnCount();
						textProdDes.setText((String) table.getValueAt(linha, 3));
						textQuant.setText ((String) table.getValueAt(linha,4));
						textProduVal.setText((String) table.getValueAt(linha,5));
						textSubtotal.setText((String) table.getValueAt(linha,6));
						
						
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
