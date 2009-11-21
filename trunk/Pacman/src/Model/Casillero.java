package Model;

public class Casillero {
 
	private Item item;
	private EstadoCasillero estadoCasillero;
	
	public Casillero(EstadoCasillero estadoCasillero){
		this.estadoCasillero = estadoCasillero;
		this.item = null;
	}
	 
	public boolean casilleroHabilitado() {
		if (this.estadoCasillero == EstadoCasillero.PARED)
			return false;
		//Si no es pared devuelve true.
		return true;		
	}
	 
	public Item getItem() {
		if (this.item != null){
			Item itemAux = this.item;
			this.item = null;
			return itemAux;
		}
		else
			return null;
	}
	 
	public void setItem(Item item) {
		this.item = item;
	}
	 
}
 
