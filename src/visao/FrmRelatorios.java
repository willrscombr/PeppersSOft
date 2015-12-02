package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.FinanceiroController;
import controle.ProdutoController;
import dao.ClienteDAO;
import util.UtilFuncoes;

@SuppressWarnings("serial")
public class FrmRelatorios extends JFrame {

	private JPanel contentPane;
	private JButton btnProdutos;
	private JButton btnFinanceiro;
	private JButton btnPedidos;
	private JButton btnProducao;
	private JButton btnClientes;
	private JRadioButton rdbtnGeralProduto;
	private JRadioButton rdbtnGeralCliente;
	private JRadioButton rdbtnEstoquePositivo;
	private JRadioButton rdbtnEstoqueZerado;
	private JRadioButton rdbtnPessoaFisica;
	private JRadioButton rdbtnPessoaJuridica;
	private ButtonGroup grupoFinanceiro;
	private ButtonGroup grupoProduto;
	private ButtonGroup grupoCliente;
	private JRadioButton rdbtnFinanceiroGeral;
	private JRadioButton rdbtnFinanceiroPorData;
	private JLabel lblDataInicial;
	private JLabel lblDataFinal;
	private JFormattedTextField frmtdtxtfldDataInicial;
	private JFormattedTextField frmtdtxtfldDataFinal;

	public FrmRelatorios() {

		setTitle("PepperSoft - Relat\u00F3rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 586, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int op = 0;

				if (rdbtnEstoquePositivo.isSelected()) {
					op = 1;
				} else if (rdbtnEstoqueZerado.isSelected()) {
					op = 2;
				}

				ProdutoController controle = new ProdutoController();
				try {
					controle.gerarRelatorio(op);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
					e1.printStackTrace();
				}
			}
		});

		btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int op = 0;

				if (rdbtnPessoaFisica.isSelected()) {
					op = 1;
				} else if (rdbtnPessoaJuridica.isSelected()) {
					op = 2;
				}

				ClienteDAO controle = new ClienteDAO();
				try {
					controle.gerarRelatorio(op);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
					e1.printStackTrace();
				}
			}
		});
		btnClientes.setBounds(442, 308, 100, 57);
		contentPane.add(btnClientes);
		btnProdutos.setBounds(442, 240, 100, 57);
		contentPane.add(btnProdutos);

		UtilFuncoes d = new UtilFuncoes();

		btnFinanceiro = new JButton("Financeiro");
		btnFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FinanceiroController controle = new FinanceiroController();

				if (rdbtnFinanceiroGeral.isSelected()) {
					try {
						controle.gerarRelatorio();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
						e.printStackTrace();
					}
				} else {
					Date data = new Date(System.currentTimeMillis());

					String datai = frmtdtxtfldDataInicial.getText();
					String dataf = frmtdtxtfldDataFinal.getText();
					String di = d.formataData(datai);
					String df = d.formataData(dataf);
					if (di == "0") {
						di = data.toString();
					}
					if (df == "0") {
						df = data.toString();
					}
					try {
						controle.gerarRelDetalhado(di, df);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
						e.printStackTrace();
					}
				}
			}
		});

		btnPedidos = new JButton("Pedidos");
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPedidos.setBounds(441, 171, 100, 57);
		contentPane.add(btnPedidos);
		btnFinanceiro.setBounds(441, 103, 100, 57);
		contentPane.add(btnFinanceiro);

		btnProducao = new JButton("Producao");
		btnProducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProducao.setBounds(442, 35, 100, 57);
		contentPane.add(btnProducao);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 28, 550, 1);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 96, 550, 1);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 164, 550, 1);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 233, 550, 1);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 301, 550, 1);
		contentPane.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 372, 550, 1);
		contentPane.add(separator_5);

		rdbtnEstoquePositivo = new JRadioButton("Estoque Positivo");
		rdbtnEstoquePositivo.setBounds(260, 240, 140, 23);
		contentPane.add(rdbtnEstoquePositivo);

		rdbtnEstoqueZerado = new JRadioButton("Estoque Zerado");
		rdbtnEstoqueZerado.setBounds(260, 266, 140, 23);
		contentPane.add(rdbtnEstoqueZerado);

		rdbtnPessoaFisica = new JRadioButton("Pessoa F\u00EDsica");
		rdbtnPessoaFisica.setBounds(260, 308, 140, 23);
		contentPane.add(rdbtnPessoaFisica);

		rdbtnPessoaJuridica = new JRadioButton("Pessoa Jur\u00EDdica");
		rdbtnPessoaJuridica.setBounds(260, 334, 140, 23);
		contentPane.add(rdbtnPessoaJuridica);

		rdbtnGeralProduto = new JRadioButton("Geral");
		rdbtnGeralProduto.setBounds(145, 266, 109, 23);
		rdbtnGeralProduto.setSelected(true);
		contentPane.add(rdbtnGeralProduto);

		rdbtnGeralCliente = new JRadioButton("Geral");
		rdbtnGeralCliente.setBounds(145, 334, 109, 23);
		rdbtnGeralCliente.setSelected(true);
		contentPane.add(rdbtnGeralCliente);

		rdbtnFinanceiroGeral = new JRadioButton("Geral");
		rdbtnFinanceiroGeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmtdtxtfldDataInicial.setEnabled(false);
				frmtdtxtfldDataInicial.setText("");
				frmtdtxtfldDataFinal.setEnabled(false);
				frmtdtxtfldDataFinal.setText("");
				
			}
		});
		rdbtnFinanceiroGeral.setBounds(145, 103, 109, 23);
		rdbtnFinanceiroGeral.setSelected(true);
		contentPane.add(rdbtnFinanceiroGeral);

		rdbtnFinanceiroPorData = new JRadioButton("Por Data");
		rdbtnFinanceiroPorData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmtdtxtfldDataInicial.setEnabled(true);
				frmtdtxtfldDataFinal.setEnabled(true);
			}
		});
		rdbtnFinanceiroPorData.setBounds(145, 133, 109, 23);
		contentPane.add(rdbtnFinanceiroPorData);

		lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(265, 115, 80, 14);
		contentPane.add(lblDataInicial);

		lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(360, 115, 80, 14);
		contentPane.add(lblDataFinal);

		frmtdtxtfldDataInicial = new JFormattedTextField();
		frmtdtxtfldDataInicial.setEnabled(false);
		frmtdtxtfldDataInicial.setBounds(260, 133, 80, 20);
		contentPane.add(frmtdtxtfldDataInicial);

		frmtdtxtfldDataFinal = new JFormattedTextField();
		frmtdtxtfldDataFinal.setEnabled(false);
		frmtdtxtfldDataFinal.setBounds(351, 133, 80, 20);
		contentPane.add(frmtdtxtfldDataFinal);

		grupoProduto = new ButtonGroup();
		grupoProduto.add(rdbtnGeralProduto);
		grupoProduto.add(rdbtnEstoquePositivo);
		grupoProduto.add(rdbtnEstoqueZerado);

		grupoCliente = new ButtonGroup();
		grupoCliente.add(rdbtnGeralCliente);
		grupoCliente.add(rdbtnPessoaFisica);
		grupoCliente.add(rdbtnPessoaJuridica);

		grupoFinanceiro = new ButtonGroup();
		grupoFinanceiro.add(rdbtnFinanceiroGeral);
		grupoFinanceiro.add(rdbtnFinanceiroPorData);

		try {
			MaskFormatter mf1 = new MaskFormatter("##/##/####");
			mf1.install(frmtdtxtfldDataInicial);
			MaskFormatter mf2 = new MaskFormatter("##/##/####");
			mf2.install(frmtdtxtfldDataFinal);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
