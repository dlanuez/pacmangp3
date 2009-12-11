package Model.juego;

import Model.excepciones.cantidadDeVidasInvalidaExeption;
import Model.tablero.Tablero;

public class Juego {

	private final int Vidas_Normal = 3;
	
	private Tablero tablero;
	private Jugador jugador;
		
	public Juego(String tablero) {
		try{
			this.jugador = new Jugador(Vidas_Normal);
		}
		catch(cantidadDeVidasInvalidaExeption e){
			e.printStackTrace();
		}
		this.tablero = new Tablero(tablero, this);
	}
	
	public Juego(String tablero, int maxX, int maxY){
		try{
			this.jugador = new Jugador(Vidas_Normal);
		}
		catch(cantidadDeVidasInvalidaExeption e){
			e.printStackTrace();
		}
		this.tablero = new Tablero(tablero, this, maxX, maxY);
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
