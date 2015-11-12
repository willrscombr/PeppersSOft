package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.sql.ResultSetMetaData;

import dao.ClienteDAO;

public class BuscCliente extends JFrame{
	private JTextField textBusca;
	private JTable table;
	public BuscCliente(){
		
		setTitle("PepperSoft - Pesquisa Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setEnabled(false);
		lblNome.setBounds(5, 17, 70, 15);
		getContentPane().add(lblNome);
		
		textBusca = new JTextField();
		textBusca.setBounds(93, 15, 181, 19);
		getContentPane().add(textBusca);
		textBusca.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 78, 476, 138);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton Buscar = new JButton("Buscar");
		Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDAO dao = new ClienteDAO();
				try {
					ResultSet rs = dao.Busca(textBusca.getText());
					ResultSetMetaData rsmt = rs.getMetaData();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		Buscar.setBounds(335, 12, 117, 25);
		getContentPane().add(Buscar);
		
		JButton Cancelar = new JButton("Cancelar");
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscCliente.this.dispose();
			}
		});
		Cancelar.setBounds(22, 228, 117, 25);
		getContentPane().add(Cancelar);
		
		JButton Confirmar = new JButton("Confirmar");
		Confirmar.setBounds(354, 228, 117, 25);
		getContentPane().add(Confirmar);
		
	}
}
