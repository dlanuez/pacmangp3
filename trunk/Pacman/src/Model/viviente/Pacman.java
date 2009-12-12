package Model.viviente;

import java.util.Iterator;

import Model.Direcciones;
import Model.EstadoViviente;
import Model.Punto;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.excepciones.tiempoDeEstadoInvalidoException;
import Model.juego.Juego;

public class Pacman extends Viviente {
	
	private Punto posicionDeRespawn;
	
	public Pacman(Punto posicionInicial, Juego juego)
			throws PosicionInvalidaException {
		super(posicionInicial, juego);
		try{
			this.setVelocidadActual(10); 
		}
		catch(VelocidadInvalidaException e){
			System.out.print(e.toString());
		}
		this.setEstado(EstadoViviente.PRESA);
		this.posicionDeRespawn = posicionInicial.clonar();
		this.setDireccionActual(Direcciones.IZQUIERDA);
	}

	public void vivir() {
		
		if (!this.estaVivo())
			this.revivir(EstadoViviente.PRESA);

		super.vivir();
		
		if(this.getDireccionActual() != null)
			this.mover();
		
		try{
			this.getJuego().getTablero().getCasillero(this.getPosicion())
										.getItem().hacerEfecto();
		} 
		catch (tiempoDeEstadoInvalidoException e) {			
			e.printStackTrace();
		}

		Fantasma fantasma = this.fantasmaEnMiPosicion();
		if (fantasma != null)
				this.encontreFantasma(fantasma);
	}

	
	private void encontreFantasma(Fantasma fantasma) {		
		if(this.getEstado() == EstadoViviente.PRESA)
			this.getJuego().pacmanComido();					
		else
			this.getJuego().fantasmaComido(fantasma);	
	}
	
	/*Metedo que recorre la coleccion de fantasmas con un iterador provisto por el tablero para averiguar 
	 *si hay algun fantasma en la posicion del pacman
	 */
	private Fantasma fantasmaEnMiPosicion() {
		
		Iterator<Fantasma> fantasmas = this.getJuego().getTablero().getFantasmasIterador();
		while(fantasmas.hasNext()){
			Fantasma fantasmaAuxiliar = fantasmas.next();
			if(this.getPosicion().equals(fantasmaAuxiliar.getPosicion()))
				return fantasmaAuxiliar;
		}
		return null;		
	}
	
	private boolean validarMovimiento(Punto posicionNueva) {
		if((posicionNueva.x() < 0) || (posicionNueva.x() >= this.getJuego().getTablero().getMaxPosX())
				|| (posicionNueva.y() <0) || (posicionNueva.y()>=this.getJuego().getTablero().getMaxPosY())){
			return false;
		}else if (this.getJuego().getTablero().getCasillero(posicionNueva)
				.casilleroHabilitado()){
				return true;
		}else{
				return false;
		}
	}

	//Metodo que reposiciona al PacMan en su posicion inicial.
	
	public void reSpawn(){
		try {
			this.setPosicion(this.posicionDeRespawn);
		} catch (PosicionInvalidaException e) {
			e.printStackTrace();
		}
	}

	//Este metodo recibe el codigo de la tecla presionada y cambia la direccion del Pacman
	public void cambiarDireccion(int codigo) {		
		if(codigo == 37){
			if((this.getDireccionActual() != Direcciones.IZQUIERDA) && (direccionValida(Direcciones.IZQUIERDA))){
				this.setDireccionActual(Direcciones.IZQUIERDA);
			}
		}
		if(codigo == 38){
			if((this.getDireccionActual() != Direcciones.ARRIBA) && (direccionValida(Direcciones.ARRIBA))){
				this.setDireccionActual(Direcciones.ARRIBA);
			}
		}
		if(codigo == 39){
			if((this.getDireccionActual() != Direcciones.DERECHA) && (direccionValida(Direcciones.DERECHA))){
				this.setDireccionActual(Direcciones.DERECHA);
			}
		}
		if(codigo == 40){
			if((this.getDireccionActual() != Direcciones.ABAJO) && (direccionValida(Direcciones.ABAJO))){
				this.setDireccionActual(Direcciones.ABAJO);
			}
		}
		
		
	}

	private boolean direccionValida(Direcciones direccion) {
		Punto puntoAuxiliar = this.getPosicion().clonar();
		if(direccion == Direcciones.ARRIBA){
			puntoAuxiliar.disminuirX();
			
		}
		if(direccion == Direcciones.DERECHA){
			puntoAuxiliar.aumentarY();			
		}
		if(direccion == Direcciones.ABAJO){
			puntoAuxiliar.aumentarX();
		}
		if(direccion == Direcciones.IZQUIERDA){
			puntoAuxiliar.disminuirY();
		}
		return this.getJuego().getTablero().getCasillero(puntoAuxiliar).casilleroHabilitado();
	}
	
	private void mover(){
		
		Punto posicionActual = this.getPosicion().clonar();
		
		switch (this.getDireccionActual()) {
			case ABAJO: {
				posicionActual.aumentarX();
				if (this.validarMovimiento(posicionActual))
					this.irAbajo();
				break;
			}
			case ARRIBA: {
				posicionActual.disminuirX();
				if (this.validarMovimiento(posicionActual))
					this.irArriba();
				break;
			}
			case DERECHA: {
				posicionActual.aumentarY();
				if (this.validarMovimiento(posicionActual))
					this.irADerecha();
				break;
			}
			case IZQUIERDA: {
				posicionActual.disminuirY();
				if (this.validarMovimiento(posicionActual))
					this.irAIzquierda();
				break;
			}
		}
	}

}
