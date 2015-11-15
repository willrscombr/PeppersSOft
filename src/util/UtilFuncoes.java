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
	public boolean isNumeric(String n){
		try {
			@SuppressWarnings("unused")
			int num = Integer.parseInt(n);
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public String formataData(String data){
		String datas[] = data.split("/");
		String d="0"; 
		if(this.isNumeric(datas[0])){
			d = datas[2]+"-"+datas[1]+"-"+datas[0];
		}
		return d;  
	}
}
