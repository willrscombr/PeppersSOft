package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import util.PeppersTableModel;
import util.UtilFuncoes;
import util.UtilMenssage;
import controle.FinanceiroController;
import dao.FinanceiroDAO;

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
	private JTextPane textCredito;
	private JTextPane textDebito;
	private float credito=0;
	private float debito=0;
	private JFormattedTextField frmtdtxtfldData;
	
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
		scrollPane.setBounds(10, 55, 703, 299);
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
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FinanceiroController f = new FinanceiroController();
				
				String data = frmtdtxtfldData.getText();
				try {
					f.consultaData(data);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.setBounds(610, 11, 89, 32);
		contentPane.add(btnPesquisar);
		
		textCredito = new JTextPane();
		textCredito.setEditable(false);
		textCredito.setFont(new Font("Tahoma", Font.BOLD, 14));
		textCredito.setForeground(new Color(0, 128, 0));
		textCredito.setBackground(SystemColor.menu);
		textCredito.setBounds(10, 404, 105, 32);
		contentPane.add(textCredito);

		textDebito = new JTextPane();
		textDebito.setEditable(false);
		textDebito.setFont(new Font("Tahoma", Font.BOLD, 14));
		textDebito.setForeground(Color.RED);
		textDebito.setBackground(SystemColor.menu);
		textDebito.setBounds(125, 404, 105, 32);
		contentPane.add(textDebito);

		JTextPane textSaldo = new JTextPane();
		textSaldo.setEditable(false);
		textSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textSaldo.setForeground(Color.BLUE);
		textSaldo.setBackground(SystemColor.menu);
		textSaldo.setBounds(239, 404, 105, 32);
		contentPane.add(textSaldo);

		somaCredito();
		somaDebito();
		float saldo = credito - debito;
		textSaldo.setText("R$ "+String.valueOf(saldo));
		
		JLabel lblCrdito = new JLabel("Cr\u00E9dito");
		lblCrdito.setForeground(new Color(0, 128, 0));
		lblCrdito.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCrdito.setBounds(10, 376, 79, 23);
		contentPane.add(lblCrdito);
		
		JLabel lblDbito = new JLabel("D\u00E9bito");
		lblDbito.setForeground(Color.RED);
		lblDbito.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDbito.setBounds(125, 376, 79, 23);
		contentPane.add(lblDbito);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setForeground(Color.BLUE);
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSaldo.setBounds(240, 376, 79, 23);
		contentPane.add(lblSaldo);
		
		frmtdtxtfldData = new JFormattedTextField();
		frmtdtxtfldData.setBounds(472, 17, 112, 20);
		contentPane.add(frmtdtxtfldData);
		try {
			MaskFormatter mf;
			mf = new MaskFormatter("##/##/####");
			mf.install(frmtdtxtfldData);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		popularTabela();
		
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	UtilFuncoes d = new UtilFuncoes();
	String data = d.retornaData();
	
	private void somaCredito(){
		FinanceiroController consulta = new FinanceiroController();
	
		try {
         	String sql = "select sum(valor) as soma_credito from financeiro where tipo_lanc = 'C'and data = '"+data+"'";
			ResultSet rs = consulta.consultaCredito(sql);
			
			while(rs.next()){  
				credito=rs.getFloat("soma_credito"); 
		    } 
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textCredito.setText("R$ "+String.valueOf(credito));
	}
	
	private void somaDebito(){
		FinanceiroController consulta = new FinanceiroController();

		try {
			String sql = "select sum(valor) as soma_debito from financeiro where tipo_lanc = 'D'and data = '"+data+"'";
			ResultSet rs = consulta.consultaDebito(sql);
			
			while(rs.next()){  
				debito=rs.getFloat("soma_debito"); 
		    } 
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textDebito.setText("R$ "+String.valueOf(debito));
	}
	
	private void popularTabela() {

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
