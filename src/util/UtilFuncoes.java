package util;

import java.util.Calendar;

public class UtilFuncoes {
	public String retornaData(){
		int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int mes = Calendar.getInstance().get(Calendar.MONTH)+1;
		int ano = Calendar.getInstance().get(Calendar.YEAR);
		String data = ano+"-"+mes+"-"+dia;
		
		return data;
	}
}
