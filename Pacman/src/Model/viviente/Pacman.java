package Model.viviente;

import java.util.Iterator;

import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;

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
			this.setVelocidad(1); // TODO poner una velocidad real.
		}
		catch(VelocidadInvalidaException e){
			System.out.print(e.toString());
		}
		this.setEstado(EstadoViviente.PRESA);
		this.posicionDeRespawn = posicionInicial;
		this.setDireccionActual(Direcciones.IZQUIERDA);
	}

	public void vivir() {
		
		Punto posicionActual =new Punto(this.getPosicion().x(), this.getPosicion().y());
		Fantasma fantasma;

		if (!this.estaVivo()) {
			this.revivir();
		}

		super.vivir();
		
		if(this.getDireccionActual() != null){
			
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
		
		try {
			this.getJuego().getTablero().getCasillero(this.getPosicion()).getItem().hacerEfecto();
		} catch (tiempoDeEstadoInvalidoException e) {			
			e.printStackTrace();
		}

		fantasma = fantasmaEnMiPosicion();
		if (fantasma != null) {
			if (this.getEstado() == EstadoViviente.CAZADOR) {
				fantasma.fenecer();
				this.getJuego().fantasmaComido(
						fantasma.getPuntosPorEsteFantasmaConCarinioParaCabu());
			} else {
				this.fenecer();
				this.getJuego().pacmanComido();
			}
		}
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

	public void revivir() {
		this.setVivo();
		try {
			this.getJuego().getTablero().resetearPosiciones();
		} catch (PosicionInvalidaException e) {
			e.printStackTrace();
		}
		this.setEstado(EstadoViviente.PRESA);

	}
	//Metodo que reposiciona al PacMan en su posicion inicial.
	
	public void reEspawnear(){
		try {
			this.setPosicion(this.posicionDeRespawn);
		} catch (PosicionInvalidaException e) {
			e.printStackTrace();
		}
	}

}
