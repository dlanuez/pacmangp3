package Model;

import Model.excepciones.tiempoDeEstadoInvalidoException;

public class Pastilla extends Item {
	private static final int cantidadDeFantasmas = 5;
	private int tiempoDeEstado;
	private static int puntosOtorgados;

	public Pastilla(Juego juego, int tiempoDeEstado){
		this.setJuego(juego);
		Pastilla.puntosOtorgados = 5;
		this.tiempoDeEstado = tiempoDeEstado;
	}
	
	//Configura los puntos otorgados de la clase Pastilla.
	public final void setPuntosOtorgados(int puntos){
		Pastilla.puntosOtorgados = puntos;
	}
	
	//Suma la cantidad de puntos otorgados por comer una bolita a los puntos del jugador
	//y le cambia el estadoViviente al pacman y a los fantasmas.
	public void hacerEfecto()throws tiempoDeEstadoInvalidoException{
		this.getJuego().getJugador().sumarPuntos(Pastilla.puntosOtorgados);
		this.getJuego().getTablero().decrementarContadorBolitas();
		this.getJuego().getTablero().getPacman().cambiarEstado(this.tiempoDeEstado);
		for(int i = 0; i < cantidadDeFantasmas; i++)
			this.getJuego().getTablero().getFantasma(i).cambiarEstado(this.tiempoDeEstado);
	}
}
 
