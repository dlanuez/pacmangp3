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
		if (this.estadoCasillero == EstadoCasillero.PARED)
			return false;
		//Si no es pared devuelve true.
		return true;		
	}
	
	//Devuelve el item que se encuentra en el casillero.
	public Item getItem() {
		return this.item;
	}	 
}
 
