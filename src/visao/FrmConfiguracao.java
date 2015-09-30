package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.ConnectionFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrmConfiguracao {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConfiguracao window = new FrmConfiguracao();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrmConfiguracao() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnTestaConexo = new JButton("Testa Conex\u00E3o");
		btnTestaConexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectionFactory conn = new ConnectionFactory();
				try {
					conn.getConnection();
					JOptionPane.showMessageDialog(null, "Conexão bem sucedida!");			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Não foi possível se conectar! erro: "+ e.getMessage());
				}
			}
		});
		btnTestaConexo.setBounds(32, 36, 133, 23);
		frame.getContentPane().add(btnTestaConexo);
	}
}
