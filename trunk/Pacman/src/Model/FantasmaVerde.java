package Model;

import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;

//este tipo de fantasma no se puede agarrar

public class FantasmaVerde extends Fantasma {

	public FantasmaVerde(Punto posicionInicial, Juego juego) throws PosicionInvalidaException, VelocidadInvalidaException {
		super(posicionInicial, juego);
		this.setVelocidad(1); //TODO poner una velocidad real.
		this.setEstrategia(new EstrategiaPerseguidora());
	}
	
	@Override
	public void cambiarEstado(int tiempoDeEstado){
		//silent return.
	}

}
