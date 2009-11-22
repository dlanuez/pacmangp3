package Model;

import Model.excepciones.tiempoDeEstadoInvalidoException;

public class Juego {

	private Tablero tablero;
	private Jugador jugador;
	private int turno;
	private int tiempoRestanteDeEstado;
	
	//Se inicializa el tiempo restante de estado en -1.
	
	public Juego() {
		this.jugador = new Jugador();
		this.tablero = new Tablero();
		this.tiempoRestanteDeEstado = -1;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public Jugador getJugador() {

		return this.jugador;
	}
	
	/* Con cada turno se decrementa el tiempo restante del estado. En caso de ser -1,
	 * no sucede nada.
	 */
	public void incrementarTurno(){
		this.turno =+ 1;
		this.decrementarTiempoRestanteDeEstado();
	}
	
	public int getTurno(){
		return this.turno;
	}
	
	private void decrementarTiempoRestanteDeEstado(){
		if(this.tiempoRestanteDeEstado > 0) this.tiempoRestanteDeEstado =- 1;
		
		if(this.tiempoRestanteDeEstado == 0){
			this.vivientesToggleState();
			this.tiempoRestanteDeEstado = -1;
		}
	}

	public void cambiarEstadoVivientes(int tiempoDeEstado) throws tiempoDeEstadoInvalidoException{
		if(tiempoDeEstado > 0){	
			this.tiempoRestanteDeEstado = tiempoDeEstado;
			this.vivientesToggleState();
		}
		else throw new tiempoDeEstadoInvalidoException();
	}
	
	public void vivientesToggleState(){
		this.getTablero().getPacman().toggleState();
		int cantidadDeFantasmas = this.getTablero().getFantasmasArray().length;
		for(int i = 0; i <= cantidadDeFantasmas; i++)
			this.getTablero().getFantasma(i).toggleState();		
	}
}
