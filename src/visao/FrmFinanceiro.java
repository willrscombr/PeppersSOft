package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FrmFinanceiro extends JFrame {

	private JPanel contentPane;

	public FrmFinanceiro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 703, 371);
		contentPane.add(scrollPane);
		
		JButton button = new JButton("Incluir");
		button.setBackground(Color.WHITE);
		button.setBounds(624, 393, 89, 43);
		contentPane.add(button);
		
		JButton button_3 = new JButton("Excluir");
		button_3.setEnabled(false);
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(525, 393, 89, 43);
		contentPane.add(button_3);
		
		JButton btnPesquisar = new JButton("Filtrar");
		btnPesquisar.setEnabled(false);
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.setBounds(275, 398, 89, 32);
		contentPane.add(btnPesquisar);
		
		JLabel lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(21, 407, 73, 14);
		contentPane.add(lblDataInicial);
		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
