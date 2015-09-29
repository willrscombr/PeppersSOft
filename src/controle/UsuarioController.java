package controle;

import java.util.List;

import dao.UsuarioDAO;
import modelo.Usuario;

public class UsuarioController {

	UsuarioDAO dao;

	public UsuarioController() {
		dao = new UsuarioDAO();
	}

	public boolean cadastrar(Usuario usuario) throws Exception {
		return dao.cadastrar(usuario);
	}

	public boolean alterar(Usuario usuario) throws Exception {
		return dao.alterar(usuario);
	}

	public boolean consultar(Usuario usuario) throws Exception {
		return dao.consultar(usuario);
	}

	public boolean excluir(Usuario usuario) throws Exception {
		return dao.excluir(usuario);
	}

	public List<Usuario> listar() throws Exception {
		return dao.listar();
	}
}
