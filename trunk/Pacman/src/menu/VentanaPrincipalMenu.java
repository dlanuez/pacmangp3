package menu;

import java.awt.Color;
import javax.swing.JFrame;

import menu.Menu;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;

public class VentanaPrincipalMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private Menu panel;
	
	public VentanaPrincipalMenu(){
			
		this.setTitle("Pacman - Grupo 3");
		this.setSize(512,600);
		this.setResizable(false);
		this.setBackground(Color.black);
		
		this.panel = new Menu(512,512);
		panel.setBackground(Color.black);
		this.add(panel);
	}
	
	public SuperficieDeDibujo getSuperficieDeDibujo(){
		return this.panel;
	}
			
}
