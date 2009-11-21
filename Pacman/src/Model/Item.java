package Model;

public abstract class Item {
 
	private Juego juego;
	 
	private int puntosOtorgados;
	
	//Configura la referencia al juego por la que se pasa por argumento.
	protected void setJuego(Juego juego){
		this.juego = juego;
	}
	
	//Devuelve la referencia al juego.
	protected Juego getJuego(){
		return this.juego;
	}
	
	//Configura los puntos otorgados del item.
	protected void setPuntosOtorgados(int puntos){
		this.puntosOtorgados = puntos;
	}
	
	//Devuelve los puntos otorgados del item.
	protected int getPuntosOtorgados(){
		return this.puntosOtorgados;
	}
	
	//Metodo abstracto.
	public abstract void hacerEfecto();
}
 