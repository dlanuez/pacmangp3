package Model;

public class Bolita extends Item {

	public Bolita(Juego juego, int puntosBolita){
		this.setJuego(juego);
		this.setPuntosOtorgados(puntosBolita);
	}
	
	//Suma la cantidad de puntos otorgados por comer una bolita a los puntos del jugador.
	public void hacerEfecto(){
		this.getJuego().getJugador().sumarPuntos(this.getPuntosOtorgados());
	}
}
 
