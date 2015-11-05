package fabrica;

import modelo.Cliente;
import modelo.FORNECEDOR;

public class ClienteFactory {
	
	public static Cliente clienteFisica(){
		
		return new Cliente();
	}
	public static FORNECEDOR clientejuridica(){
		return new FORNECEDOR(); 
	}

}
