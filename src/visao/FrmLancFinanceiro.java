package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ContaDAO;
import dao.FinanceiroDAO;
import modelo.Conta;
import modelo.Financeiro;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FrmLancFinanceiro extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtConta;
	private JTextField txtDiscrim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLancFinanceiro frame = new FrmLancFinanceiro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLancFinanceiro() {
		setTitle("Lan\u00E7amento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				ContaDAO consulta = new ContaDAO();
				
				try {
					Conta conta= new Conta();
					conta=consulta.consultar(Integer.parseInt(txtCodigo.getText()));
					
					txtConta.setText(String.valueOf(conta.getDescricao()));
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Deu treta");
					e.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Deu treta");
					e.printStackTrace();
				}
			}
		});
		
		txtCodigo.setBounds(126, 56, 31, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtConta = new JTextField();
		txtConta.setEditable(false);
		txtConta.setBounds(162, 56, 168, 20);
		contentPane.add(txtConta);
		txtConta.setColumns(10);
		
		JLabel lblCdigoConta = new JLabel("C\u00F3digo Conta");
		lblCdigoConta.setBounds(34, 59, 82, 14);
		contentPane.add(lblCdigoConta);
		
		JLabel lblDiscriminao = new JLabel("Discrimina\u00E7\u00E3o");
		lblDiscriminao.setBounds(34, 88, 82, 14);
		contentPane.add(lblDiscriminao);
		
		txtDiscrim = new JTextField();
		txtDiscrim.setColumns(10);
		txtDiscrim.setBounds(125, 82, 205, 20);
		contentPane.add(txtDiscrim);
		
		JLabel lblTipoLanamento = new JLabel("Tipo Lan\u00E7amento");
		lblTipoLanamento.setBounds(34, 120, 82, 14);
		contentPane.add(lblTipoLanamento);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "Cr\u00E9dito", "D\u00E9bito"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(126, 117, 157, 20);
		contentPane.add(comboBox);
		
		JButton btnConfirmarLanamento = new JButton("Confirmar Lan\u00E7amento");
		btnConfirmarLanamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, comboBox.getSelectedItem());
			}
		});
		btnConfirmarLanamento.setBounds(162, 209, 168, 23);
		contentPane.add(btnConfirmarLanamento);
	}
}
