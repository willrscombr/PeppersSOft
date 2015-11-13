package controle;

import java.util.List;

import modelo.Usuario;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UsuarioREL {
	
	private String path; // Caminho base

	private String pathToReportPackage; // Caminho para o package onde est�o
										// armazenados os relatorios Jarper

	// Recupera os caminhos para que a classe possa encontrar os relat�rios
	public UsuarioREL() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "jr/";
		System.out.println(path);
	}

	// Imprime/gera uma lista de Clientes
	public void imprimir(List<Usuario> usuarios) throws Exception {
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "UsuariosRel.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(usuarios));

		JasperExportManager.exportReportToPdfFile(print, "d:/Relatorio_de_Usuarios.pdf");
	}

	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}

}
