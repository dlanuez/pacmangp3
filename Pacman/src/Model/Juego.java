package Model;

import Model.excepciones.cantidadDeVidasInvalidaExeption;

public class Juego {

	private final int Vidas_Normal = 3;
	
	private Tablero tablero;
	private Jugador jugador;
		
	public Juego() {
		try{
			this.jugador = new Jugador(Vidas_Normal);
		}
		catch(cantidadDeVidasInvalidaExeption e){
			e.printStackTrace();
		}
		this.tablero = new Tablero("laberinto.xml", this);
	}

	public Tablero getTablero() {
		return this.tablero;
	}

	public Jugador getJugador() {

		return this.jugador;
	}

	public void pacmanComido() {
		this.getJugador().restarVida();
	}

	public void fantasmaComido(int puntosPorEsteFantasma) {
		this.getJugador().sumarPuntos(puntosPorEsteFantasma);		
	}
	
}
