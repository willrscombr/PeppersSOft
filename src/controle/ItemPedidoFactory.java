package controle;

import java.util.ArrayList;
import java.util.List;

import modelo.ItemVenda;

public class ItemPedidoFactory {
	
	@SuppressWarnings("unused")
	private ItemVenda itempedido;
	private List<ItemVenda> listaitempedido;
	
	public void addItem(ItemVenda itempedido){
	listaitempedido = new ArrayList<>();
	listaitempedido.add(itempedido);
	}
	public List<ItemVenda> getItens(){
		return this.listaitempedido;
	}

}
