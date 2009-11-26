package Model;

public class Casillero {
 
	private Item item;
	private EstadoCasillero estadoCasillero;
	
	public Casillero(EstadoCasillero estadoCasillero, Item item){
		this.estadoCasillero = estadoCasillero;
		this.item = item;
	}
	
	//Devuelve true si el casillero no es una pared y false si es una pared.
	public boolean casilleroHabilitado() {
		return this.estadoCasillero.estaHabilitado();	
	}
	
	//Devuelve el item que se encuentra en el casillero y convierte el item en itemNulo.
	public Item getItem() {
		if (this.item instanceof Fruta)
			if (!((Fruta)this.item).activado())
				return new ItemNulo();
		Item itemAux = this.item;
		this.item = new ItemNulo();
		return itemAux;
	}	
	
	//Devuelve el item sin modificarlo
	public Item obtenerItem(){
		return this.item;
	}
}
 
