package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import Controller.InicializadorJuego;
import View.VentanaPrincipalJuego;

public class AccionJugar implements ActionListener {
	
	private ControladorJuego controlador;
	private VentanaPrincipalJuego ventana;
	private InicializadorJuego inicializador;
	
	public void actionPerformed(ActionEvent arg0) {
		
		this.ventana = new VentanaPrincipalJuego(this.controlador);
		this.ventana.setVisible(true);
		
		System.out.println("JUEGO!");
		inicializador = new InicializadorJuego(this.ventana);
		this.inicializador.comenzarJuegoAsyn();		
	}
	
	

}
