package controle;

import java.sql.ResultSet;
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

	public ResultSet consultar() throws Exception {
		return dao.consultar();
	}
	
	public boolean consultar(Usuario usuario) throws Exception {
		return dao.consultar(usuario);
	}

	public boolean excluir(int id) throws Exception {
		return dao.excluir(id);
	}

	public List<Usuario> listar() throws Exception {
		return dao.listar();
	}
}
