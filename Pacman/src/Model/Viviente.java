package Model;

import Model.excepciones.*;

 /* Clase utilizada para todo objeto que viva en el tablero */

public abstract class Viviente {
	
	private boolean vivo;
	private double velocidad;
	private Punto posicion;
	private Juego juego;
	private EstadoViviente estado;
	private int tiempoRestanteDeEstado;
	
	/* Se inicializa el tiempo restante de estado en -1. La velocidad inicial y el EstadiViviente del objeto deben
	 * inicializarse en el constructor de la clase descendiente.
	 */
	Viviente(Punto posicionInicial, Juego juego) throws PosicionInvalidaException{
		this.tiempoRestanteDeEstado = -1;
		this.setPosicion(posicionInicial);
		this.juego = juego;
		this.vivir();
		this.velocidad = 0;
	}
	
	/* Con cada movimiento se decrementa el tiempo restante del estado. En caso de ser -1,
	 * no sucede nada.
	 */
	public void irAIzquierda(){
		this.decrementarTiempoRestanteDeEstado();
		if(this.posicion.x() > 0)
			this.posicion.disminuirX();
	}
	
	public void irADerecha(){
		this.decrementarTiempoRestanteDeEstado();
		if(this.posicion.x() < this.juego.getTablero().getMaxPosX())
			this.posicion.aumentarX();
		
	}
	
	public void irArriba(){
		this.decrementarTiempoRestanteDeEstado();
		if(this.posicion.y() > 0)
			this.posicion.disminuirY();		
	}
	
	public void irAbajo(){
		this.decrementarTiempoRestanteDeEstado();
		if(this.posicion.y() < this.juego.getTablero().getMaxPosY())
			this.posicion.aumentarY();
	}
	
	public void cambiarEstado(int tiempoDeEstado) throws tiempoDeEstadoInvalidoException{
		if(tiempoDeEstado > 0){	
			this.tiempoRestanteDeEstado = tiempoDeEstado;
			this.toggleState();
		}
		else throw new tiempoDeEstadoInvalidoException();
	}
	
	private void decrementarTiempoRestanteDeEstado(){
		if(this.tiempoRestanteDeEstado > 0) this.tiempoRestanteDeEstado =- 1;
		
		if(this.tiempoRestanteDeEstado == 0){
			this.toggleState();
			this.tiempoRestanteDeEstado = -1;
		}
	}
	
	
	public void toggleState(){
		if(this.estado == EstadoViviente.CAZADOR)
			this.estado = EstadoViviente.PRESA;
		else this.estado = EstadoViviente.CAZADOR;
	}
	
	public void setPosicion(Punto nuevaPosicion) throws PosicionInvalidaException{
		if( (nuevaPosicion.x() >= 0) && (nuevaPosicion.y() >= 0)){
			if(	(nuevaPosicion.x() <= this.juego.getTablero().getMaxPosX()) && 
				(nuevaPosicion.y() <= this.juego.getTablero().getMaxPosY())){
				this.posicion.x(nuevaPosicion.x());
				this.posicion.y(nuevaPosicion.y());
			}
		}
		else throw new PosicionInvalidaException();
	}
	
	
	public EstadoViviente getEstado(){
		return this.estado;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) throws VelocidadInvalidaException {
		if(velocidad >= 0) this.velocidad = velocidad;
		else throw new VelocidadInvalidaException();
	}
	
	public void vivir(){
		this.vivo = true;
	}

	public void fenecer(){	
		// Fenecer: Morir, fallecer. Poner fin a una cosa, concluirla. (=D)
		this.vivo = false;
	}
	
	public boolean estaVivo(){
		return this.vivo;
	}
}
