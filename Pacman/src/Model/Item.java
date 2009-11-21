package Model;

public abstract class Item {
 
	private Juego juego;
	 
	private int puntosOtorgados;
	
	protected void setJuego(Juego juego){
		this.juego = juego;
	}
	
	protected Juego getJuego(){
		return this.juego;
	}
	
	protected void setPuntosOtorgados(int puntos){
		this.puntosOtorgados = puntos;
	}
	
	protected int getPuntosOtorgados(){
		return this.puntosOtorgados;
	}
		
	
	public void hacerEfecto() {
	 
	}
	 
}
 