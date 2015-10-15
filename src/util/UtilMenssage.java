package util;

import javax.swing.JOptionPane;

public class UtilMenssage {
	
	public static void msgSucesso(){
		JOptionPane.showMessageDialog(null, "Operação Realizada com sucesso");
	}
	public static void msgError(){
		JOptionPane.showMessageDialog(null, "Ocorreu um erro, entre em contato com o Administrador do Sistema");
	}

}
