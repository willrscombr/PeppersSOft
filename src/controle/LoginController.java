package controle;

import javax.swing.JOptionPane;

import visao.FrmPrincipal;

public class LoginController {
	public void realizaLogin(String usuario, String senha){
		if(usuario.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")){
			JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
			try {
				FrmPrincipal framep = new FrmPrincipal();
				framep.setVisible(true);
				framep.setVisible(false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuario/Senha invalidos! Tente novamente!");
		}
	}
}
