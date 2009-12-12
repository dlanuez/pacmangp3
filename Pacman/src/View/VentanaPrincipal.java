package View;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.KeyPressedController;
import ar.uba.fi.algo3.titiritero.vista.Panel;

public class VentanaPrincipal extends Frame {
	private static final long serialVersionUID = 1L;
	private ControladorJuego controladorJuego;
	private Panel panel;
	
	public VentanaPrincipal(ControladorJuego controlador){
		this.setTitle("Pacman - Grupo 3");
		this.controladorJuego = controlador;
		this.setSize(512,600);
		this.setResizable(false);
		this.setBackground(Color.black);
		this.panel = new Panel(512,512,this.controladorJuego);
		panel.setBackground(Color.black);
		this.add(panel);
		this.addKeyListener(new KeyPressedController(controlador));
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
