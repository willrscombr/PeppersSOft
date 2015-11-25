package fabrica;

import modelo.Cliente;


public class ClienteFactory {
	
	public static Cliente clienteFisica(){
		
		return new Cliente();
	}
	

}
