package Model.item;




import Model.excepciones.tiempoDeEstadoInvalidoException;
import Model.juego.Juego;

public abstract class Item {
 
	private Juego juego;
	private boolean comido;
	
	//Configura la referencia al juego por la que se pasa por argumento.
	protected void setJuego(Juego juego){
		this.juego = juego;
	}
	
	//Devuelve la referencia al juego.
	protected Juego getJuego(){
		return this.juego;
	}
	
	public abstract void hacerEfecto() throws tiempoDeEstadoInvalidoException;

	public Item comer() {
		return new ItemNulo();
	}
	
	public void setComido(boolean comido){
		this.comido = comido;
	}
	
	public boolean getComido() {
		return this.comido;
	}
}
 