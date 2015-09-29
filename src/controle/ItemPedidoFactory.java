package controle;

import java.util.ArrayList;
import java.util.List;

import modelo.ItemPedido;

public class ItemPedidoFactory {
	
	private ItemPedido itempedido;
	private List<ItemPedido> listaitempedido;
	
	public void addItem(ItemPedido itempedido){
	listaitempedido = new ArrayList<>();
	listaitempedido.add(itempedido);
	}
	public List<ItemPedido> getItens(){
		return this.listaitempedido;
	}

}
