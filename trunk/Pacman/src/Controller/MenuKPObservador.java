package Controller;


import java.awt.event.KeyEvent;
import ar.uba.fi.algo3.titiritero.KeyPressedObservador;
import Model.juego.Menu;

public class MenuKPObservador implements KeyPressedObservador {

	private Menu menu;
	
	public MenuKPObservador(Menu menu){
		this.menu = menu;
	}
	
	public void keyPressed(KeyEvent event) {
        int codigo = event.getKeyCode();
       	this.menu.elegir(codigo);
	}
	
}
