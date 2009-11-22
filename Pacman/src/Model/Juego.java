package Model;

public class Juego {

	private Tablero tablero;
	private Jugador jugador;
		
	public Juego() {
		this.jugador = new Jugador();
		this.tablero = new Tablero();
	}

	public Tablero getTablero() {
		return tablero;
	}

	public Jugador getJugador() {

		return this.jugador;
	}
	
}
