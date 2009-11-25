package Model;

public class Juego {

	private Tablero tablero;
	private Jugador jugador;
		
	public Juego() {
		this.jugador = new Jugador();
		this.tablero = new Tablero("nivel1.xml", this);
	}

	public Tablero getTablero() {
		return this.tablero;
	}

	public Jugador getJugador() {

		return this.jugador;
	}
	
}
