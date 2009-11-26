package Model;

import Model.excepciones.*;

 /* Clase utilizada para todo objeto que viva en el tablero */

public abstract class Viviente {
	
	private boolean vivo;
	private double velocidad;
	private Punto posicion;
	private Direcciones direccionActual;
	private Juego juego;
	private EstadoViviente estado;
	private int tiempoRestanteDeEstado;
	
    /* Se inicializa el tiempo restante de estado en -1. La velocidad inicial y el EstadoViviente del objeto deben
	 * inicializarse en el constructor de la clase descendiente.
	 */
	Viviente(Punto posicionInicial, Juego juego) throws PosicionInvalidaException{
		this.posicion = posicionInicial;
		this.juego = juego;
		this.setVivo();
		this.velocidad = 0;
		this.direccionActual = null;
		this.tiempoRestanteDeEstado = -1;
	}
	
	public void vivir(){
		this.decrementarTiempoRestanteDeEstado();
	}
	
	public void irAIzquierda(){
		if(this.posicion.x() > 0){
			this.posicion.disminuirX();
			this.setDireccionActual(Direcciones.IZQUIERDA);
		}
	}
	
	public void irADerecha(){
		if(this.posicion.x() < this.juego.getTablero().getMaxPosX()){
			this.posicion.aumentarX();
			this.setDireccionActual(Direcciones.DERECHA);
		}		
	}
	
	public void irArriba(){
		if(this.posicion.y() > 0){
			this.posicion.disminuirY();
			this.setDireccionActual(Direcciones.ARRIBA);
		}
	}
	
	public void irAbajo(){
		if(this.posicion.y() < this.juego.getTablero().getMaxPosY()){
			this.posicion.aumentarY();
			this.setDireccionActual(Direcciones.ABAJO);
		}
	}
	
	public void cambiarEstado(int tiempoDeEstado) throws tiempoDeEstadoInvalidoException{
		if(tiempoDeEstado > 0){	
			this.tiempoRestanteDeEstado = tiempoDeEstado;
			this.toggleState();
		}
		else throw new tiempoDeEstadoInvalidoException();
	}
	
	public void setPosicion(int x, int y) throws PosicionInvalidaException{
		Punto posicion = new Punto(x, y);
		this.setPosicion(posicion);
	}
		
	public void setPosicion(Punto nuevaPosicion) throws PosicionInvalidaException{
		if( this.validarPosicion(nuevaPosicion) ){
				this.posicion.x(nuevaPosicion.x());
				this.posicion.y(nuevaPosicion.y());
		}
		else throw new PosicionInvalidaException();
	}
	
	public Punto getPosicion(){
		return this.posicion;
	}
	
		
	private void toggleState(){
		if(this.estado != null)
			this.estado = estado.toggleState();
		else throw new EstadoNoInicializadoExeption();
	}
		

	/* Con cada turno se decrementa el tiempo restante del estado. En caso de ser -1,
	 * no sucede nada.
	 */	
	private void decrementarTiempoRestanteDeEstado(){
		if(this.tiempoRestanteDeEstado > 0) this.tiempoRestanteDeEstado--;
		
		if(this.tiempoRestanteDeEstado == 0){
			this.toggleState();
			this.tiempoRestanteDeEstado = -1;
		}
	}
	
	private boolean validarPosicion(Punto posicion){
		if( (posicion.x() >= 0) && (posicion.y() >= 0)){
			if(	(posicion.x() <= this.juego.getTablero().getMaxPosX() ) && 
				(posicion.y() <= this.juego.getTablero().getMaxPosY() ) )
				return true;
		}
		return false;
	}
	
	public void setDireccionActual(Direcciones direccionActual){
		this.direccionActual = direccionActual;
	}
	
	public Direcciones getDireccionActual(){
		return this.direccionActual;
	}
	
	public void setEstado(EstadoViviente estado){
		this.estado = estado;
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
	
	public void setVivo(){
		this.vivo = true;
	}

	public void fenecer(){	
		// Fenecer: Morir, fallecer. Poner fin a una cosa, concluirla. (=D)
		this.vivo = false;
	}
	
	public boolean estaVivo(){
		return this.vivo;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
}
