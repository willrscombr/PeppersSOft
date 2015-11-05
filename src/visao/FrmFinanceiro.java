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
import util.UtilMenssage;
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
				new FrmLancFinanceiro().setVisible(true);
			}
		});
		btnIncluir.setBackground(Color.WHITE);
		btnIncluir.setBounds(624, 393, 89, 43);
		contentPane.add(btnIncluir);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();

				String message="Deseja realmente excluir o registro?";
				String title="Confirmação";
				int opc=JOptionPane.showConfirmDialog(null, message,title,JOptionPane.YES_NO_OPTION);
				if(opc == JOptionPane.YES_OPTION){
					try {
	
						if(new FinanceiroDAO().excluir(Integer.parseInt(table.getValueAt(linha, 0).toString()))){
							UtilMenssage.msgSucesso();
							FrmFinanceiro.this.dispose();
							new FrmFinanceiro().setVisible(true);
						}else{
							UtilMenssage.msgError();
						}
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}
			}	
		});
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
					
				}
			});
			
			table.setForeground(Color.RED);
			table.setModel(modelo);
			Object[] linha = null;
			
			modelo.addColumn("Código");
			modelo.addColumn("Discriminação");
			modelo.addColumn("Tipo");
			modelo.addColumn("Conta");
			modelo.addColumn("Valor");
			modelo.addColumn("Lançamento");
			
			
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
