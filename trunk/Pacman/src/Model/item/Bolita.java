package Model.item;


import Model.juego.Juego;

public class Bolita extends Item {

	private static int puntosOtorgados = 10;

	//El puntaje otorgado por defecto es 10.
	public Bolita(Juego juego){
		this.setJuego(juego);
		this.setComido(false);
	}
	
	//Configura los puntos otorgados de la clase Bolita.
	public final void setPuntosOtorgados(int puntos){
		Bolita.puntosOtorgados = puntos;
	}
	
	//Suma la cantidad de puntos otorgados por comer una bolita a los puntos del jugador.
	public void hacerEfecto(){
		this.getJuego().getJugador().sumarPuntos(Bolita.puntosOtorgados);
		this.getJuego().getTablero().decrementarContadorBolitas();
		this.setComido(true);
	}

}
 
