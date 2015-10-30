package controle;

import javax.swing.JOptionPane;
import visao.FrmPrincipal;

public class LoginController {
	public boolean realizaLogin(String usuario, String senha){
		if(usuario.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")){
			//JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
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
