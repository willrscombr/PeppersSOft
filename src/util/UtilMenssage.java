package util;

import javax.swing.JOptionPane;

public class UtilMenssage {
	
	public static void msgSucesso(){
		JOptionPane.showMessageDialog(null, "Opera��o Realizada com sucesso");
	}
	public static void msgError(){
		JOptionPane.showMessageDialog(null, "Ocorreu um erro, entre em contato com o Administrador do Sistema");
	}
	
	public static void msgJaExiste(){
		JOptionPane.showMessageDialog(null, "Aten��o. Registro j� existente na base de dados!");
	}

}
