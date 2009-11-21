package Model;

public class Casillero {
 
	private Item item;
	private EstadoCasillero estadoCasillero;
	
	public Casillero(EstadoCasillero estadoCasillero){
		this.estadoCasillero = estadoCasillero;
		this.item = null;
	}
	 
	public boolean casilleroHabilitado() {
		if (this.estadoCasillero == PARED)
			return false;
		else if (this.estadoCasillero == PISO)
			return true;			
	}
	 
	public Item getItem() {
		return this.item;
	}
	 
	public void setItem(Item item) {
		this.item = item;
	}
	 
}
 
