package Model;

import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.excepciones.tiempoDeEstadoInvalidoException;

public class Pacman extends Viviente {
	
	private Punto posicionDeRespawn;
	
	public Pacman(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego);
		this.posicionDeRespawn = posicionInicial;
		this.setVelocidad(1); //TODO poner una velocidad real.
		this.setEstado(EstadoViviente.PRESA);
	}
	
	public void vivir(){
		Punto posicionActual = this.getPosicion();
		Fantasma fantasma;
		
		if (!this.estaVivo()){
			this.revivir();
		}
		
		super.vivir();
		switch (this.getDireccionActual()){
			case ABAJO : {posicionActual.aumentarX();
						if (this.validarMovimiento(posicionActual))
								this.irAbajo(); 
						break;}
			case ARRIBA : {posicionActual.disminuirX();
						if (this.validarMovimiento(posicionActual))
								this.irArriba(); 
						break;}
			case DERECHA : {posicionActual.aumentarY();
						if (this.validarMovimiento(posicionActual))
								this.irADerecha(); 
						break;}
			case IZQUIERDA: {posicionActual.disminuirY();
						if (this.validarMovimiento(posicionActual))
								this.irAIzquierda(); 
						break;}
		}
		try {
			this.getJuego().getTablero().getCasillero(this.getPosicion()).getItem().hacerEfecto();
		} catch (tiempoDeEstadoInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fantasma = fantasmaEnMiPosicion();
		if (fantasma != null){
			if (this.getEstado() == EstadoViviente.CAZADOR){
				fantasma.fenecer();
				this.getJuego().getJugador().sumarPuntos(fantasma.getPuntosPorEsteFantasmaConCarinioParaCabu());
			}
			else{
				this.fenecer();
				this.getJuego().getJugador().restarVida();
			}
		}
	}

	private Fantasma fantasmaEnMiPosicion() {
		int cantidadDeFantasmas = this.getJuego().getTablero().getFantasmasArray().length;
		for(int i = 0; i < cantidadDeFantasmas; i++)
			if (this.getPosicion() == this.getJuego().getTablero().getFantasma(i).getPosicion()){
				return this.getJuego().getTablero().getFantasma(i);
			}
		return null;
	}

	private boolean validarMovimiento(Punto posicionNueva) {
		if (this.getJuego().getTablero().getCasillero(posicionNueva).casilleroHabilitado())
			return true;
		else
			return false;
	}

	public void revivir() {
		this.setVivo();
		try {
			this.setPosicion(this.posicionDeRespawn);
		} catch (PosicionInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setEstado(EstadoViviente.PRESA);
		
	}

}
