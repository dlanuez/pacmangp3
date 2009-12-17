package Controller;

import Model.EstadoViviente;
import Model.viviente.*;
import TP3.Programa;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class FantasmaVivo implements ObjetoVivo, Posicionable {
	private Fantasma fantasma;

	/* Esta clase relaciona el modelo con el controlador. Todo fantasma que sea controlado debe
	 * estar contenido en esta clase.
	 */
	public FantasmaVivo(Fantasma fantasma){
		this.fantasma = fantasma;
	}

	public void vivir() {
		this.fantasma.vivir();
		
	}

	
	public int getX() {	
		return Programa.TAMANIO_CASILLEROS * this.fantasma.getPosicion().y();
	}

	public int getY() {
		return Programa.TAMANIO_CASILLEROS * this.fantasma.getPosicion().x();
	}
	
	//Se utiliza para saber qué imágen del fantasma utilizar en la vista.
	public boolean estaCazando() {
		if(this.fantasma.getEstado() == EstadoViviente.CAZADOR) return true;
		return false;
	}
}
