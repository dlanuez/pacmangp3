package Model;
import Model.excepciones.*;

public abstract class Viviente {
	
	private boolean vivo;
	private double velocidad;
	private Punto posicion;
	private Juego juego;
	private EstadoViviente estado;
	private int turnosTranscurridos;
	
	public void irAIzquierda(){
		if(this.posicion.x() > 0)
			this.posicion.disminuirX();
	}
	
	public void irADerecha(){
		if(this.posicion.x() < this.juego.getTablero().getMaxPosX())
			this.posicion.aumentarX();
		
	}
	
	public void irArriba(){
		if(this.posicion.y() > 0)
			this.posicion.disminuirY();		
	}
	
	public void irAbajo(){
		if(this.posicion.y() < this.juego.getTablero().getMaxPosY())
			this.posicion.aumentarY();
	}
	
	public void cambiarEstado(int tiempoDeEstado){
		
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

	public void fenecer(){	// (=D)
		this.vivo = false;
	}
	
	public boolean estaVivo(){
		return this.vivo;
	}
}
