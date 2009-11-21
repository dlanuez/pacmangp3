package Model;

public class Casillero {
 
	private Item item;
	private EstadoCasillero estadoCasillero;
	
	public Casillero(EstadoCasillero estadoCasillero){
		this.estadoCasillero = estadoCasillero;
		this.item = null;
	}
	
	//Devuelve true si el casillero no es una pared y false si es una pared.
	public boolean casilleroHabilitado() {
		if (this.estadoCasillero == EstadoCasillero.PARED)
			return false;
		//Si no es pared devuelve true.
		return true;		
	}
	
	//Devuelve el item que se encuentra en el casillero. Si no hay item, devuelve null.
	public Item getItem() {
		if (this.item != null){
			Item itemAux = this.item;
			this.item = null;
			return itemAux;
		}
		else
			return null;
	}
	
	//Configura el item con el item que se pasa por argumento.
	public void setItem(Item item) {
		this.item = item;
	}
	 
}
 
