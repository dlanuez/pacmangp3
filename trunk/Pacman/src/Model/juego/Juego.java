package Model.juego;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import Model.excepciones.cantidadDeVidasInvalidaExeption;
import Model.tablero.Tablero;

public class Juego {

	private final int Vidas_Normal = 3;
	
	private Tablero tablero;
	private Jugador jugador;

	private ControladorJuego controlador;

	private String[] niveles;

	private int nivelActual;
		
	public Juego(String nivel0, String nivel1, String nivel2, ControladorJuego controlador) {
		this.niveles = new String[3];
		try{
			this.jugador = new Jugador(Vidas_Normal);
		}
		catch(cantidadDeVidasInvalidaExeption e){
			e.printStackTrace();
		}
		this.tablero = new Tablero(nivel0, this);
		this.controlador = controlador;
		this.niveles[0] = nivel0;
		this.niveles[1] = nivel1;
		this.niveles[2] = nivel2;
		this.nivelActual = 0;
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
		if (this.getJugador().restarVida())
			this.controlador.detenerJuego();
	}

	public void fantasmaComido(int puntosPorEsteFantasma) {
		this.getJugador().sumarPuntos(puntosPorEsteFantasma);		
	}

	public void pasarDeNivel() {
		this.nivelActual++;
		this.tablero = new Tablero(this.niveles[this.nivelActual], this);
		this.tablero.inicializar();
	}
	
}
