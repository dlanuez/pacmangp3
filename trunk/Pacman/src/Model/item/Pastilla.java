package Model.item;

import java.util.Iterator;

import Model.excepciones.tiempoDeEstadoInvalidoException;
import Model.juego.Juego;
import Model.viviente.Fantasma;

public class Pastilla extends Item {
	
	private int tiempoDeEstado;
	private static int puntosOtorgados = 5;

	public Pastilla(Juego juego, int tiempoDeEstado){
		this.setJuego(juego);
		this.tiempoDeEstado = tiempoDeEstado;
		this.setComido(false);
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
		Iterator<Fantasma> fantasmas = this.getJuego().getTablero().getFantasmasIterador();
		while(fantasmas.hasNext()){
			fantasmas.next().cambiarEstado(this.tiempoDeEstado);
		}
		this.setComido(true);
	}

}
 
