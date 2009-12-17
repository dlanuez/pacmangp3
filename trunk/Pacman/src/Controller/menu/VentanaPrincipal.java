package Controller.menu;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import TP3.Programa;
import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.KeyPressedController;
import ar.uba.fi.algo3.titiritero.vista.Panel;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorJuego controladorJuego;
	private Panel panel;
	
	/* Esta clase contiene todos los paneles y elementos que se utilizan durante el juego.
	 * 
	 */
	public VentanaPrincipal(int ancho, int alto){
			
		this.setTitle("Pacman - Grupo 3");
		this.setSize(ancho,alto);
		this.setResizable(false);
		this.setBackground(Color.black);		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public SuperficieDeDibujo getSuperficieDeDibujo(){
		return this.panel;
	}
	
	//Cambia el panel de inicio (Menu) por un panel que es actualizado por el controlador durante la simulación.
	public void inicializarParaJuego(ControladorJuego controlador){
		
		this.panel = new Panel(Programa.ANCHO_PANEL_JUEGO, Programa.ALTO_PANEL_JUEGO, controlador);
		this.setContentPane(this.panel);		
		
		this.setControlador(controlador);
		
		this.addKeyListener(new KeyPressedController(this.getControladorJuego()));
		
		this.setFocusable(true);
		this.setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
				
	}

	
	public void setControlador(ControladorJuego controlador) {
		this.controladorJuego = controlador;
		this.panel.setControlador(controlador);
		this.panel.setVisible(true);
	}

	public void borrarKeyListeners() {
		KeyListener[] keyListener = this.getKeyListeners();
		for(int i = 0; i < keyListener.length; i ++){
			this.removeKeyListener(keyListener[i]);
		}
		
	}

	public ControladorJuego getControladorJuego() {
		return controladorJuego;
	}
			
}
