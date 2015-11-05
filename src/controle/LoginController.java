package controle;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import visao.FrmPrincipal;

public class LoginController {
	public boolean realizaLogin(String usuario, String senha){
		GregorianCalendar d = new GregorianCalendar();
		int dia=d.get(Calendar.DAY_OF_MONTH);
		int mes=d.get(Calendar.MONTH)+1;
		String s=String.valueOf((dia*55)+String.valueOf(mes*5));
		if(usuario.equalsIgnoreCase("pepper") && senha.equalsIgnoreCase(s)){
			try {
				FrmPrincipal framep = new FrmPrincipal();
				framep.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuario/Senha invalidos! Tente novamente!");
			return false;
		}
	}
}
