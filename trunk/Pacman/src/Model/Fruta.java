package Model;

public class Fruta extends Item {

	private int tiempoActivo;
	private static int puntosOtorgados;

	public Fruta(Juego juego, int tiempoActivo){
		this.setJuego(juego);
		Fruta.puntosOtorgados = 20;
		this.tiempoActivo = tiempoActivo;
	}
	
	//Configura los puntos otorgados de la clase Fruta.
	public final void setPuntosOtorgados(int puntos){
		Fruta.puntosOtorgados = puntos;
	}
	
	//Suma la cantidad de puntos otorgados por comer una fruta a los puntos del jugador.
	public void hacerEfecto(){
		this.getJuego().getJugador().sumarPuntos(Fruta.puntosOtorgados);
	}
}
 
