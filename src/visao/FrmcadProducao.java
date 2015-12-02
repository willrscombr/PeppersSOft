package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.ProducaoController;
import controle.ProdutoController;
import modelo.ItemProducao;
import modelo.Producao;
import modelo.Produto;
import util.PeppersTableModel;
import util.UtilFuncoes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
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
	private JTextField textCodigoItem;
	private JTextField textDescricao;
	private JTextField textQtd;
	private JComboBox comboBox;
	private JButton btnIncluir;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JTextField textResponsavel;
	private JFormattedTextField formattedTextField;
	private JButton btnEditar;
	private JButton button_1;
	private JButton button;
	private JButton button_2;
	private JButton button_3;
	private int cont=0;
	private List<String> listitens = new ArrayList<String>();
	private List<ItemProducao> listaitens=new ArrayList<ItemProducao>();
	private ProducaoController p = new ProducaoController();
	private ItemProducao itens = new ItemProducao();
	
	private Producao producao = new Producao();
	public FrmcadProducao() {
		setTitle("PepperSoft - Lan\u00E7amento de Produ\u00E7\u00E3o");
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
		textCodigoItem.setEnabled(false);
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
					textDescricao.setText(String.valueOf(produto.getDescricao()));
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
		textQtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnSalvar.requestFocus();
				}
			}
		});
		textQtd.setEnabled(false);
		textQtd.setBounds(220, 278, 63, 20);
		contentPane.add(textQtd);
		textQtd.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(156, 281, 89, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setBounds(10, 281, 64, 14);
		contentPane.add(lblUnidade);
		
		comboBox = new JComboBox();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					textQtd.requestFocus();
				}
			}
		});
		comboBox.setEnabled(false);
		comboBox.setBounds(67, 278, 63, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"UN", "CX", "KG"}));
		comboBox.setToolTipText("");
		contentPane.add(comboBox);
		
		button = new JButton("Editar");
		button.setBounds(109, 393, 89, 43);
		button.setEnabled(false);
		button.setBackground(Color.WHITE);
		contentPane.add(button);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(103, 11, 138, 20);
		contentPane.add(formattedTextField);
		UtilFuncoes d = new UtilFuncoes();
		
		MaskFormatter mf;
		try {
			mf = new MaskFormatter("##/##/####");
			mf.install(formattedTextField);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		button_1 = new JButton("Salvar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					salvaProducao();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void salvaProducao() throws Exception {
				String dt = formattedTextField.getText();	
				String data = d.formataData(dt);

				if(data == "0"){
					JOptionPane.showMessageDialog(null, "É necessário informar a data da produção!!!");
				}else if(textResponsavel.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "É necessário informar o responsavel da produção!!!");
				}else if(listitens.size()==0){
					JOptionPane.showMessageDialog(null, "É necessário incluir ao menos algum item!!!");
				}else{//se tudo der certo
				producao.setData("2015-12-02");
				producao.setListaitens(listaitens);
				producao.setResponsavel("vinicius");
				p.cadastrar(producao);
				}
			}
		});
		button_1.setBounds(10, 393, 89, 43);
		button_1.setBackground(Color.WHITE);
		contentPane.add(button_1);
		
		button_2 = new JButton("Cancelar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmcadProducao.this.dispose();
				new FrmProducao().setVisible(true);
			}
		});
		button_2.setBounds(208, 393, 89, 43);
		button_2.setBackground(Color.WHITE);
		contentPane.add(button_2);
		
		button_3 = new JButton("Excluir");
		button_3.setBounds(305, 393, 89, 43);
		button_3.setEnabled(false);
		button_3.setBackground(Color.WHITE);
		contentPane.add(button_3);
		
		
		
		JLabel label = new JLabel("Data Produ\u00E7\u00E3o");
		label.setBounds(10, 14, 89, 14);
		contentPane.add(label);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					
				}
			}
		});
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluiItem();
				//popularTabela();
				btnCancelar.setEnabled(false);
				btnSalvar.setEnabled(false);
				btnIncluir.setEnabled(true);
				textCodigoItem.setEnabled(false);
				textCodigoItem.setText("");
				textDescricao.setEnabled(false);
				textDescricao.setText("");
				textQtd.setEnabled(false);
				textQtd.setText("");
				comboBox.setEnabled(false);
			}
		});
		btnSalvar.setBounds(94, 314, 89, 23);
		contentPane.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCancelar.setEnabled(false);
				btnSalvar.setEnabled(false);
				btnIncluir.setEnabled(true);
				textCodigoItem.setEnabled(false);
				textCodigoItem.setText("");
				textDescricao.setEnabled(false);
				textDescricao.setText("");
				textQtd.setEnabled(false);
				textQtd.setText("");
				comboBox.setEnabled(false);
				
				
			}
		});
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
				textCodigoItem.setEnabled(true);
				textDescricao.setEnabled(true);
				textQtd.setEnabled(true);
				comboBox.setEnabled(true);
				textCodigoItem.requestFocus();
			}
		});
		btnIncluir.setBounds(10, 314, 79, 23);
		contentPane.add(btnIncluir);
		
		JLabel lblResponsvel = new JLabel("Respons\u00E1vel");
		lblResponsvel.setBounds(251, 14, 89, 14);
		contentPane.add(lblResponsvel);
		
		textResponsavel = new JTextField();
		textResponsavel.setBounds(350, 11, 174, 20);
		contentPane.add(textResponsavel);
		textResponsavel.setColumns(10);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBounds(367, 314, 89, 23);
		contentPane.add(btnEditar);
		
		popularTabela();
		
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
private void incluiItem(){
	itens.setCoditemprod(Integer.parseInt(textCodigoItem.getText()));
	itens.setItemdesc(textDescricao.getText());
	itens.setItemund(comboBox.getSelectedItem().toString());
	itens.setQtdprod(Integer.parseInt(textQtd.getText()));

	cont = cont + 1;
	listitens = new ArrayList<String>();
	listitens.add(String.valueOf(itens.getCoditemprod()));
	listitens.add(String.valueOf(itens.getItemdesc()));
	listitens.add(String.valueOf(itens.getQtdprod()));
	listitens.add(String.valueOf(itens.getItemund()));
	
	
	modelo.addRow(listitens.toArray());
	
	listaitens.add(new ItemProducao(itens.getCoditemprod(),itens.getItemdesc(),itens.getItemund(),itens.getProduto(),itens.getQtdprod()));
}	
private void popularTabela(){
		
		try {
			if(listaitens!=null){

			modelo = new PeppersTableModel();
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
				}
			});	
			table.setForeground(Color.RED);
			table.setModel(modelo);		
			modelo.addColumn("Codigo");
			modelo.addColumn("Descrição");
			modelo.addColumn("Quantidade");
			modelo.addColumn("Unidade");
			scrollPane.setViewportView(table);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro! Verifique a conexão!");
		}
	}

}

