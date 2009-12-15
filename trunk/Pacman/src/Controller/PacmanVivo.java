package Controller;

import java.awt.event.KeyEvent;

import ar.uba.fi.algo3.titiritero.KeyPressedObservador;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;
import Model.viviente.Pacman;


public class PacmanVivo implements ObjetoVivo, Posicionable, KeyPressedObservador{

	private Pacman pacman;	


	public PacmanVivo(Pacman pacman){
		this.pacman = pacman;		
	}
	
	public void vivir() {			
		this.pacman.vivir();				
	}

	public int getX() {
		return 32 * this.pacman.getPosicion().y();
	}

	public int getY() {
		return 32 * this.pacman.getPosicion().x();
	}

	public void keyPressed(KeyEvent event) {
		int codigo = event.getKeyCode();
		//System.out.println(codigo);
		this.pacman.cambiarDireccion(codigo);
	}	
	
		
}