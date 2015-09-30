package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class FrmClientes extends JFrame {
	private JTextField textField_3;

	public FrmClientes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 739, 486);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JButton button = new JButton("Salvar");
        button.setBounds(111, 393, 89, 43);
        button.setEnabled(false);
        button.setBackground(Color.WHITE);
        getContentPane().add(button);
        
        JButton button_1 = new JButton("Cancelar");
        button_1.setBounds(214, 393, 89, 43);
        button_1.setEnabled(false);
        button_1.setBackground(Color.WHITE);
        getContentPane().add(button_1);
        
        JButton button_2 = new JButton("Excluir");
        button_2.setBounds(313, 393, 89, 43);
        button_2.setEnabled(false);
        button_2.setBackground(Color.WHITE);
        getContentPane().add(button_2);
        
        JButton button_3 = new JButton("Incluir");
        button_3.setBounds(10, 393, 89, 43);
        button_3.setBackground(Color.WHITE);
        getContentPane().add(button_3);
        
        JLabel label_2 = new JLabel("Pesquisa");
        label_2.setBounds(81, 81, 73, 14);
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        getContentPane().add(label_2);
        
        textField_3 = new JTextField();
        textField_3.setBounds(190, 80, 224, 20);
        textField_3.setColumns(10);
        getContentPane().add(textField_3);
        
        JButton button_4 = new JButton("Localizar");
        button_4.setBounds(449, 75, 89, 31);
        button_4.setBackground(Color.WHITE);
        getContentPane().add(button_4);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(30, 115, 535, 242);
        tabbedPane.setToolTipText("");
        getContentPane().add(tabbedPane);
        
        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Fisica", null, tabbedPane_1, null);
        
        JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Juridica", null, tabbedPane_2, null);
	}
}
