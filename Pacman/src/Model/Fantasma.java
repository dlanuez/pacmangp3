package Model;

import Model.excepciones.PosicionInvalidaException;

public class Fantasma extends Viviente {
	
	private Estrategia estrategia;
	private int puntosPorEsteFantasma;
	
	public Fantasma(Punto posicionInicial, Juego juego) throws PosicionInvalidaException{
		super(posicionInicial, juego);
		this.setEstado(EstadoViviente.CAZADOR);
		
	}
	
	private void irEnDireccion(Direcciones direccion){
		
		switch(direccion){
		case ARRIBA:
			this.irArriba();
			break;				
		case ABAJO:
			this.irAbajo();
			break;
		case IZQUIERDA:
			this.irAIzquierda();
			break;
		case DERECHA:
			this.irADerecha();
			break;
		}		
	}

	public void vivir(){
		super.vivir();
		this.mover();
	}
	
	public void mover(){
		
		Punto posicionPacman = this.getJuego().getTablero().getPacman().getPosicion();
		
		this.irEnDireccion(
			this.estrategia.calcularNuevaDireccion(
				this.getPosicion(), posicionPacman, this.getDireccionActual(), this.getJuego().getTablero()));
		
		this.buscarPacman(posicionPacman);
	}

	
	private void buscarPacman(Punto posicionPacman) {
		
		if(this.getPosicion().equals(posicionPacman))
			this.encontrePacman();
		
	}

	private void encontrePacman() {
		
		if(this.getEstado() == EstadoViviente.CAZADOR){
			this.getJuego().pacmanComido();
			this.getJuego().getTablero().getPacman().respawn();
		}
		else{
			this.getJuego().fantasmaComido(puntosPorEsteFantasma);
			try {
				this.setPosicion(this.getPosicionDeRespawn());
			} catch (PosicionInvalidaException e) {
				/* Las posiciones de respawn deberían ser válidas, ya que se leen del archivo
				 * laberinto.xml
				 */
				e.printStackTrace();
			}
		}
		
	}

	private Punto getPosicionDeRespawn() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
