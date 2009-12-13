package Model.juego;

import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.cantidadDeVidasInvalidaExeption;
import Model.tablero.Tablero;
import Model.viviente.Fantasma;

public class Juego {

	private final int Vidas_Normal = 3;
	
	private Tablero tablero;
	private Jugador jugador;

	private int nivelActual;
			
	
	public Juego(String tablero, int maxX, int maxY){
		try{
			this.jugador = new Jugador(Vidas_Normal);
		}
		catch(cantidadDeVidasInvalidaExeption e){
			e.printStackTrace();
		}
		this.tablero = new Tablero(tablero, this, maxX, maxY);
		this.nivelActual = 1;
	}
	
	public Juego(String tablero){
		try{
			this.jugador = new Jugador(Vidas_Normal);
		}catch(cantidadDeVidasInvalidaExeption e){
			e.printStackTrace();
		}
		this.tablero = new Tablero(tablero, this, 16, 16);
		this.nivelActual = 1;
	}

	public Tablero getTablero() {
		return this.tablero;
	}

	public Jugador getJugador() {

		return this.jugador;
	}

	public void pacmanComido() {
		this.getTablero().getPacman().fenecer();
		this.getJugador().restarVida();
		try {
			this.getTablero().resetearPosiciones();
		} catch (PosicionInvalidaException e) {
			e.printStackTrace();
		}
	}

	public void fantasmaComido(Fantasma fantasma) {
		this.getJugador().sumarPuntos(fantasma.getPuntosPorEsteFantasmaConCarinioParaCabu());
		fantasma.fenecer();
        try {
        	fantasma.setPosicion(fantasma.getPuntoDeRespawn());
        }
        catch (PosicionInvalidaException e) {
                /* Las posiciones de respawn deberían ser válidas, ya que se leen del archivo
                 * laberinto.xml. Si se llega a esta exepción, lo más probable es que
                 * getPosicionDeRespawn() haya devuelto un valor nulo.
                 */
                e.printStackTrace();
        }
		
	}

	public void pasarDeNivel() {
		this.nivelActual++;
		this.tablero = new Tablero("src/Model/nivel"+Integer.toString(this.nivelActual)+".xml", this);
		this.tablero.inicializar();
	}

	public int getNivel() {	
		return this.nivelActual;
	}
	
	
}
