package View;

import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.Posicionable;
import Model.viviente.Pacman;


public class PacmanVivo implements ObjetoVivo, Posicionable{

	private Pacman pacman;		

	public PacmanVivo(Pacman pacman){
		this.pacman = pacman;
	}
	
	public void vivir() {
		//el controlador hace un pacman.ir_a_donde_se_apreto_la_tecla()!!
		this.pacman.vivir();
	}

	public int getX() {
		return 32 * this.pacman.getPosicion().y();
	}

	public int getY() {
		return 32 * this.pacman.getPosicion().x();
	}	
	
		
}