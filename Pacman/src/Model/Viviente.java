package Model;

public abstract class Viviente {
	
	private boolean vivo;
	private double velocidad;
	private Punto posicion;
	private Juego juego;
	private EstadoViviente estado;
	
	public void irAIzquierda(){
		
	}
	
	public void irADerecha(){
		
	}
	
	public void irArriba(){
		
	}
	
	public void irAbajo(){
		
	}
	
	public void cambiarEstado(int tiempoDeEstado){
		
	}
	
	public void setPosicion(Punto nuevaPosicion){
		this.posicion.x(nuevaPosicion.x());
		this.posicion.y(nuevaPosicion.y());
	}
	
	public EstadoViviente getEstado(){
		return this.estado;
	}

}
