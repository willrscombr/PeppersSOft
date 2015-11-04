package visao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

public class FrmGeraSenha {

	public static void main(String[] args) {
		GregorianCalendar d = new GregorianCalendar();
		int dia=d.get(Calendar.DAY_OF_MONTH);
		int mes=d.get(Calendar.MONTH)+1;
		String senha=String.valueOf((dia*55)+String.valueOf(mes*5));
		JOptionPane.showMessageDialog(null, "A senha de hoje é: "+senha);
	}

}
