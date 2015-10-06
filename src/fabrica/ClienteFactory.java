package fabrica;

import modelo.Fisica;
import modelo.Juridica;

public class ClienteFactory {
	
	public static Fisica clienteFisica(){
		
		return new Fisica();
	}
	public static Juridica clientejuridica(){
		return new Juridica(); 
	}

}
