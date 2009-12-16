package Controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.InicializadorJuego;

public class AccionJugar implements ActionListener {
	
	
	private VentanaPrincipal ventana;
	private InicializadorJuego inicializador;
	
	public AccionJugar(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	public void actionPerformed(ActionEvent arg0) {
		inicializador = new InicializadorJuego(this.ventana);
		this.inicializador.comenzarJuegoAsyn();		
	}
	
	

}
