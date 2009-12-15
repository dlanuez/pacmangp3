package Controller;


import java.awt.event.KeyEvent;
import ar.uba.fi.algo3.titiritero.KeyPressedObservador;
import Model.juego.Menu;

public class MenuKPObservador implements KeyPressedObservador {

	private Menu menu;
	private boolean teclaApretada;
	
	public MenuKPObservador(Menu menu){
		this.menu = menu;
		this.teclaApretada = false;
	}
	
	public void vivir() {
		//this.teclaApretada = false;
	}

	public void keyPressed(KeyEvent event) {
		
        int codigo = event.getKeyCode();
       
        //if(!this.teclaApretada)
        	this.menu.elegir(codigo);
       
        //this.teclaApretada = true;
	}
	
	public void KeyReleased(KeyEvent e){
		this.teclaApretada = false;
	}
}
