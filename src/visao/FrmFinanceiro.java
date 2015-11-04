package visao;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import util.PeppersTableModel;
import dao.FinanceiroDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmFinanceiro extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private PeppersTableModel modelo;
	private ResultSet rs;
	private ResultSetMetaData rsmt;
	private JTable table;
	private JButton btnIncluir;
	private JButton btnExcluir;
	private JButton btnPesquisar;
	private JLabel lblDataInicial;
/*
	public void abreFinanceiro(){
		int linha = table.getSelectedRow();
		Financeiro f = new Financeiro();
		Conta c = new Conta();
	    f.setC(c);Cod_prod(Integer.parseInt(table.getValueAt(linha, 0).toString()));
	    f.setDescricao(table.getValueAt(linha, 1).toString());
	    f.setEstoque(Float.parseFloat(table.getValueAt(linha, 2).toString()));
	    f.setPreco_custo(Float.parseFloat(table.getValueAt(linha, 3).toString()));
	    
		FrmFinanceiro.this.dispose();
		new FrmLancFinanceiro(f).setVisible(true);
		
	}*/
	
	public FrmFinanceiro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 703, 371);
		contentPane.add(scrollPane);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmFinanceiro.this.dispose();
				new FrmLancFinanceiro().setVisible(true);
			}
		});
		btnIncluir.setBackground(Color.WHITE);
		btnIncluir.setBounds(624, 393, 89, 43);
		contentPane.add(btnIncluir);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(525, 393, 89, 43);
		contentPane.add(btnExcluir);
		
		btnPesquisar = new JButton("Filtrar");
		btnPesquisar.setEnabled(false);
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.setBounds(275, 398, 89, 32);
		contentPane.add(btnPesquisar);
		
		lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(21, 407, 73, 14);
		contentPane.add(lblDataInicial);
		
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
			rs = new FinanceiroDAO().consultar();
			rsmt = rs.getMetaData();
			int numerodecolunas = rsmt.getColumnCount();
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnExcluir.setEnabled(true);
					
					if (e.getClickCount() > 1) {  
						//abreFinanceiro();
					} 
				}
			});
			
			table.setForeground(Color.RED);
			table.setModel(modelo);
			Object[] linha = null;
			
			modelo.addColumn("código");
			modelo.addColumn("discriminação");
			modelo.addColumn("tipo");
			modelo.addColumn("conta");
			modelo.addColumn("Valor");
			
			
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
