package Model;

import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;

public class Pacman extends Viviente {
	
	private Punto posicionDeRespawn;
	
	public Pacman(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego);
		this.posicionDeRespawn = posicionInicial;
		this.setVelocidad(1); //TODO poner una velocidad real.
		this.setEstado(EstadoViviente.PRESA);
	}
	
	public void vivir(){
		super.vivir();
	}

	public void respawn() {
		try {
			this.setPosicion(this.posicionDeRespawn);
		} catch (PosicionInvalidaException e) {
			// la posicionDeRespawn ya fue chequeada en el constructor, y no puede ser cambiada.
			e.printStackTrace();
		}
		
	}

}
