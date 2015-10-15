package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import dao.ClienteDAO;
import fabrica.ClienteFactory;
import interfaces.Cliente;

import javax.swing.JDesktopPane;

import java.awt.FlowLayout;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.PeppersTableModel;
import util.UtilMenssage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class FrmClientes extends JFrame {

	private JTextField textField;
	private JTable table;
	private PeppersTableModel modelo ;
	private ResultSet rs ;
	private ResultSetMetaData rsmt;
	private JTextField textCodigo;
	private JTextField textNome;
	private JTextField textCadFed;
	private JTextField textCadEst;
	private JTextField textEndereco;
	private JTextField textTelefone;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JScrollPane scrollPane;
	

	public FrmClientes() {
		setTitle("Lista de Clientes");
		getContentPane().setLayout(null);
		setSize(1610, 699);
		table = new JTable();
		scrollPane = new JScrollPane();
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				// TODO Auto-generated method stub
				
				Object obj = table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
				JOptionPane.showMessageDialog(null,obj.toString());
		
		}});
			
		scrollPane.setBounds(10, 103, 333, 546);
		getContentPane().add(scrollPane);
	
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(373, 109, 757, 475);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(12, 14, 54, 15);
		panel.add(lblCodigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 40, 70, 36);
		panel.add(lblNome);

		textCodigo = new JTextField();
		textCodigo.setBounds(84, 11, 54, 19);
		textCodigo.setEditable(false);
		panel.add(textCodigo);
		textCodigo.setColumns(10);

		textNome = new JTextField();
		textNome.setBounds(82, 48, 336, 19);
		textNome.setEnabled(false);
		panel.add(textNome);
		textNome.setColumns(10);

		btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente cliente = new ClienteFactory().clienteFisica();

				cliente.setNome(textNome.getText());
				cliente.setEndereco(textEndereco.getText());
				cliente.setTelefone(Long.valueOf(textTelefone.getText().trim()));
				cliente.setNumCadNacional(Long.valueOf(textCadFed.getText().trim()));
				cliente.setNumCadEstadual(Long.valueOf(textCadEst.getText().trim()));
				
				
				if(new ClienteDAO().cadastrar(cliente)){
					UtilMenssage.msgSucesso();
				}else{
					UtilMenssage.msgError();
				}
				popularTabela();
				limparCampos();
				habilitarCampos(false);
				habilitarBotoes(true);
			}
		});
		btnSalvar.setBounds(290, 440, 116, 25);
		btnSalvar.setEnabled(false);
		panel.add(btnSalvar);

		JLabel lblCpfcnpj = new JLabel("CPF/CNPJ:");
		lblCpfcnpj.setBounds(10, 87, 70, 14);
		panel.add(lblCpfcnpj);

		JLabel lblRgie = new JLabel("RG/IE:");
		lblRgie.setBounds(238, 84, 46, 14);
		panel.add(lblRgie);

		textCadFed = new JTextField();
		textCadFed.setBounds(84, 84, 131, 20);
		textCadFed.setEnabled(false);
		panel.add(textCadFed);
		textCadFed.setColumns(10);

		textCadEst = new JTextField();
		textCadEst.setBounds(310, 81, 131, 20);
		textCadEst.setEnabled(false);
		panel.add(textCadEst);
		textCadEst.setColumns(10);

		JLabel lblEndereo = new JLabel("Endere\u00E7o: ");
		lblEndereo.setBounds(12, 128, 68, 14);
		panel.add(lblEndereo);

		textEndereco = new JTextField();
		textEndereco.setBounds(84, 123, 334, 20);
		textEndereco.setEnabled(false);
		panel.add(textEndereco);
		textEndereco.setColumns(10);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				habilitarCampos(false);
				habilitarBotoes(true);
				limparCampos();
				
			}
		});
		btnCancelar.setBounds(452, 441, 108, 23);
		btnCancelar.setEnabled(false);
		panel.add(btnCancelar);

		JLabel lblTelefone = new JLabel("Telefone: ");
		lblTelefone.setBounds(12, 168, 70, 14);
		panel.add(lblTelefone);

		textTelefone = new JTextField();
		textTelefone.setBounds(82, 165, 177, 20);
		textTelefone.setEnabled(false);
		panel.add(textTelefone);
		textTelefone.setColumns(10);

		btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				habilitarCampos(true);
				habilitarBotoes(false);
				
			}
		});
		btnAdicionar.setBounds(23, 441, 99, 23);
		panel.add(btnAdicionar);

		btnAlterar = new JButton("ALTERAR");
		btnAlterar.setBounds(170, 441, 89, 23);
		panel.add(btnAlterar);

		btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBounds(613, 441, 89, 23);
		panel.add(btnExcluir);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel_1.setBackground(Color.RED);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBounds(0, 0, 80000, 92);
		getContentPane().add(panel_1);
		
	
		
		popularTabela();
		
		
		

	}
	private void habilitarCampos(boolean var){
		textCadEst.setEnabled(var);
		textCadFed.setEnabled(var);
		textEndereco.setEnabled(var);
		textNome.setEnabled(var);
		textTelefone.setEnabled(var);
		btnSalvar.setEnabled(var);
		btnCancelar.setEnabled(var);
		
	}
	private void limparCampos(){
		textCadEst.setText(null);
		textCadFed.setText(null);
		textEndereco.setText(null);
		textNome.setText(null);
		textTelefone.setText(null);
	}
	
	private void habilitarBotoes(boolean var){
		btnAdicionar.setEnabled(var);
		btnAlterar.setEnabled(var);
		btnExcluir.setEnabled(var);
		
	}
	private void limparTabela(){
		table.removeAll();
	}
	private void popularTabela(){
		
		try {
			
			modelo = new PeppersTableModel();
			rs = new ClienteDAO().consultar();
			rsmt = rs.getMetaData();
			int numerodecolunas = rsmt.getColumnCount();
			table = new JTable();
			table.setForeground(Color.RED);
			table.setModel(modelo);
			/*
			 * for (int i = 0; i < numerodecolunas; i++) { //new String []
			 * modelo.addColumn(rsmt.getColumnLabel(i)); }
			 */

			Object[] linha = null;
			modelo.addColumn("codigo");
			modelo.addColumn("nome");
			modelo.addColumn("telefone");

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
			JOptionPane.showMessageDialog(null, "Deu pau");
		}
	}
}
