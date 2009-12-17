package Controller;

import java.awt.event.KeyEvent;

import ar.uba.fi.algo3.titiritero.KeyPressedObservador;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;
import Model.viviente.Pacman;
import TP3.Programa;


public class PacmanVivo implements ObjetoVivo, Posicionable, KeyPressedObservador{

	private Pacman pacman;	

	/* Esta clase contiene al pacman del modelo, e implementa los metodos necesarios para ser controlado 
	 * con el teclado.
	 */
	public PacmanVivo(Pacman pacman){
		this.pacman = pacman;		
	}
	
	public void vivir() {			
		this.pacman.vivir();				
	}

	public int getX() {
		return Programa.TAMANIO_CASILLEROS * this.pacman.getPosicion().y();
	}

	public int getY() {
		return Programa.TAMANIO_CASILLEROS * this.pacman.getPosicion().x();
	}

	public void keyPressed(KeyEvent event) {
		int codigo = event.getKeyCode();
		//System.out.println(codigo);
		this.pacman.cambiarDireccion(codigo);
	}	
	
		
}