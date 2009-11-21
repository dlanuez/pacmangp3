package Model;

public class Bolita extends Item {

	public Bolita(Juego juego, int puntosBolita){
		this.setJuego(juego);
		this.setPuntosOtorgados(puntosBolita);
	}
	
	public void hacerEfecto(){
		this.getJuego().getJugador().sumarPuntos(this.getPuntosOtorgados());
	}
}
 
