package Model;

public class Fruta extends Item {

	private int tiempoActivo;

	public Fruta(Juego juego, int puntosFruta, int tiempoActivo){
		this.setJuego(juego);
		this.setPuntosOtorgados(puntosFruta);
		this.tiempoActivo = tiempoActivo;
	}
	
	public void hacerEfecto(){
		this.getJuego().getJugador().sumarPuntos(this.getPuntosOtorgados());
	}
}
 
