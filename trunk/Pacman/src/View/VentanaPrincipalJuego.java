package View;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.KeyPressedController;
import ar.uba.fi.algo3.titiritero.vista.Panel;

public class VentanaPrincipalJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorJuego controladorJuego;
	private Panel panel;
	
	public VentanaPrincipalJuego(ControladorJuego controlador){
		
		this.setTitle("Pacman - Grupo 3");
		this.setSize(512,600);
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		
		this.controladorJuego = controlador;
		this.panel = new Panel(512,512);
		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.BLUE);
		this.add(panel);
		
		this.addKeyListener(new KeyPressedController(this.controladorJuego));
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	public SuperficieDeDibujo getSuperficieDeDibujo(){
		return this.panel;
	}
	
}