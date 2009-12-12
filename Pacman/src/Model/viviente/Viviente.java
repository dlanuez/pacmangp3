package Model.viviente;

import Model.Direcciones;
import Model.EstadoViviente;
import Model.Punto;
import Model.excepciones.*;
import Model.juego.Juego;

 /* Clase utilizada para todo objeto que viva en el tablero */

public abstract class Viviente {
	
	private boolean vivo;
	private int velocidadActual;
	private Punto posicion;
	private Direcciones direccionActual;
	private Juego juego;
	private EstadoViviente estado;
	private int tiempoRestanteDeEstado;
	
	private final int VELOCIDAD_MAX = 10;
	
    /* Se inicializa el tiempo restante de estado en -1. La velocidadActual inicial y el EstadoViviente del objeto deben
	 * inicializarse en el constructor de la clase descendiente.
	 */
	Viviente(Punto posicionInicial, Juego juego) throws PosicionInvalidaException{
		this.posicion = posicionInicial;
		this.juego = juego;
		this.setVivo();
		this.velocidadActual = 0;
		this.direccionActual = Direcciones.IZQUIERDA;
		this.tiempoRestanteDeEstado = -1;
	}
	
	public void vivir(){
		this.decrementarTiempoRestanteDeEstado();
	}
	
	public void irAIzquierda(){
		Punto puntoAuxiliar = this.posicion.clonar();
		puntoAuxiliar.disminuirY();
		if(this.posicionValida(puntoAuxiliar)){
			this.posicion.disminuirY();
			this.setDireccionActual(Direcciones.IZQUIERDA);
		}
	}
	
	public void irADerecha(){
		Punto puntoAuxiliar = this.posicion.clonar();
		puntoAuxiliar.aumentarY();
		if(this.posicionValida(puntoAuxiliar)){
				this.posicion.aumentarY();
				this.setDireccionActual(Direcciones.DERECHA);
		}		
	}
		

	public void irArriba(){
		Punto puntoAuxiliar = this.posicion.clonar();
		puntoAuxiliar.disminuirX();
		if(this.posicionValida(puntoAuxiliar)){
			this.posicion.disminuirX();
			this.setDireccionActual(Direcciones.ARRIBA);
		}
	}
	
	public void irAbajo(){
		Punto puntoAuxiliar = this.posicion.clonar();
		puntoAuxiliar.aumentarX();
		if( this.posicionValida(puntoAuxiliar)){
			this.posicion.aumentarX();
			this.setDireccionActual(Direcciones.ABAJO);
		}
	}
	
	private boolean posicionValida(Punto puntoAuxiliar){
		if( this.posicionExiste(puntoAuxiliar) && this.posicionHabilitada(puntoAuxiliar))
			return true;
		return false;
	}
	
	private boolean posicionHabilitada(Punto puntoAuxiliar) {
		return this.juego.getTablero().getCasillero(puntoAuxiliar).casilleroHabilitado();	
	}
	
	private boolean posicionExiste(Punto puntoAuxiliar){
		if( (puntoAuxiliar.x() >= 0) && (puntoAuxiliar.y() >= 0)){
			if(	(puntoAuxiliar.x() < this.juego.getTablero().getMaxPosX() ) && 
				(puntoAuxiliar.y() < this.juego.getTablero().getMaxPosY() ) )
				return true;
		}
		return false;
	}
	
	public void cambiarEstado(int tiempoDeEstado) throws tiempoDeEstadoInvalidoException{
		if(tiempoDeEstado > 0){	
			/* Verifica que estuviera en el estado normal. Si tiempoRestanteDeEstado =! -1, 
			 * entonces todavía no se acabó el efecto de la pelotita anterior, y se reinicia el contador
			 * pero no se vuelve a cambiar el estado del Viviente. Esto solo ocurre cuando el contador
			 * llega a 0. 
			 */
			if(this.tiempoRestanteDeEstado == -1) 
				this.toggleState();
			this.tiempoRestanteDeEstado = tiempoDeEstado;
		}
		else throw new tiempoDeEstadoInvalidoException();
	}
	
	public void setPosicion(int x, int y) throws PosicionInvalidaException{
		Punto posicion = new Punto(x, y);
		this.setPosicion(posicion);
	}
		
	public void setPosicion(Punto nuevaPosicion) throws PosicionInvalidaException{
		if( this.posicionExiste(nuevaPosicion) ){
				this.posicion.x(nuevaPosicion.x());
				this.posicion.y(nuevaPosicion.y());
		}
		else throw new PosicionInvalidaException();
	}
	
	public Punto getPosicion(){
		return this.posicion;
	}
	
		
	protected void toggleState(){
		if(this.estado != null)
			this.estado = estado.toggleState();
		else throw new EstadoNoInicializadoExeption();
	}
		

	protected void reiniciarTiempoRestanteDeEstado(){
		this.tiempoRestanteDeEstado = -1;
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

	public int getVelocidadActual() {
		return velocidadActual;
	}

	public void setVelocidadActual(int velocidad) throws VelocidadInvalidaException {
		if(velocidad >= 0 && velocidad <= VELOCIDAD_MAX) this.velocidadActual = velocidad;
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
	
	public void revivir(EstadoViviente estadoViviente) {
		this.setVivo();
		this.setEstado(estadoViviente);
		this.reiniciarTiempoRestanteDeEstado();
	}
	
}
