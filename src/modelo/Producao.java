package modelo;

import java.util.List;

public class Producao {
	private String data;
	private String responsavel;
	private List<ItemProducao> listaitens;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public List<ItemProducao> getListaitens() {
		return listaitens;
	}
	public void setListaitens(List<ItemProducao> listaitens) {
		this.listaitens = listaitens;
	}
	
	
}
