package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controle.UsuarioController;
import modelo.Usuario;
import util.PeppersTableModel;
import util.UtilMenssage;

@SuppressWarnings("serial")
public class FrmUsuarios extends JFrame {

	private JPanel contentPane;
	private JButton btnAbrir;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnIncluir;

	private JScrollPane scrollPane;
	private JTable table;
	private PeppersTableModel modelo;
	private ResultSet rs;
	private ResultSetMetaData rsmt;

	public void abreUsuario() {
		int linha = table.getSelectedRow();
		Usuario u = new Usuario();
		u.setId_codigo(Integer.parseInt(table.getValueAt(linha, 0).toString()));
		u.setNome(table.getValueAt(linha, 1).toString());
		u.setUsuario(table.getValueAt(linha, 2).toString());
		u.setSenha(table.getValueAt(linha, 3).toString());
		u.setPermissao(table.getValueAt(linha, 4).toString());

		FrmUsuarios.this.dispose();
		new FrmcadUsuarios(u).setVisible(true);

	}

	public FrmUsuarios() {
		setTitle("PepperSoft - Cadastro de Usu\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 703, 358);
		getContentPane().add(scrollPane);

		btnIncluir = new JButton("Incluir");
		btnIncluir.setBackground(Color.WHITE);
		btnIncluir.setIcon(null);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUsuarios.this.dispose();
				new FrmcadUsuarios(null).setVisible(true);
			}
		});
		btnIncluir.setBounds(10, 394, 89, 43);
		contentPane.add(btnIncluir);

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreUsuario();
			}
		});
		btnEditar.setIcon(null);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(214, 394, 89, 43);
		contentPane.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();

				String message = "Deseja realmente excluir o produto?";
				String title = "Confirmação";
				int opc = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.YES_OPTION) {
					try {

						if (new UsuarioController().excluir(Integer.parseInt(table.getValueAt(linha, 0).toString()))) {
							UtilMenssage.msgSucesso();
							FrmUsuarios.this.dispose();
							new FrmUsuarios().setVisible(true);
						} else {
							UtilMenssage.msgError();
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnExcluir.setIcon(null);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(313, 394, 89, 43);
		contentPane.add(btnExcluir);

		btnAbrir = new JButton("Abrir");
		btnAbrir.setEnabled(false);
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreUsuario();
			}
		});
		btnAbrir.setIcon(null);
		btnAbrir.setBackground(Color.WHITE);
		btnAbrir.setBounds(111, 394, 89, 43);
		contentPane.add(btnAbrir);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 703, 358);
		contentPane.add(scrollPane_1);

		popularTabela();

		try {
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void popularTabela() {

		try {

			modelo = new PeppersTableModel();
			rs = new UsuarioController().consultar();
			rsmt = rs.getMetaData();
			int numerodecolunas = rsmt.getColumnCount();
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnAbrir.setEnabled(true);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					if (e.getClickCount() > 1) {
						abreUsuario();
					}
				}
			});

			table.setForeground(Color.RED);
			table.setModel(modelo);
			Object[] linha = null;

			modelo.addColumn("código");
			modelo.addColumn("nome");
			modelo.addColumn("usuario");
			modelo.addColumn("senha");
			modelo.addColumn("permissão");

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
