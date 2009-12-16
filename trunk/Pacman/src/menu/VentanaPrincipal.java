package menu;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import menu.Menu;
import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.KeyPressedController;
import ar.uba.fi.algo3.titiritero.vista.Panel;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorJuego controladorJuego;
	private Panel panel;
	
	public VentanaPrincipal(){
			
		this.setTitle("Pacman - Grupo 3");
		this.setSize(512,600);
		this.setResizable(false);
		this.setBackground(Color.black);
		
		this.panel = new Menu(512,512, this);
		panel.setBackground(Color.black);
		this.add(panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public SuperficieDeDibujo getSuperficieDeDibujo(){
		return this.panel;
	}
	
	public void inicializarParaJuego(ControladorJuego controlador){
		
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
