package TP3;


import Controller.JuegoVivo;
import Model.juego.Juego;
import View.VentanaPrincipal;
import ar.uba.fi.algo3.titiritero.ControladorJuego;

public class Programa {

	public static void main(String[] args) {
		ControladorJuego controlador = new ControladorJuego();
		//VistaLaberinto vistaLaberinto = new VistaLaberinto(0,0, "borde-izquierdo.jpg");
		Juego juego = new Juego("src/Model/nivel1.xml");
		juego.getTablero().inicializar();		
	
		VentanaPrincipal ventana = new VentanaPrincipal(controlador);
		ventana.setVisible(true);
		
		JuegoVivo juegoVivo = new JuegoVivo(juego, ventana);
		juegoVivo.inicializarControlador();
		juegoVivo.getControladorJuego().comenzarJuego();
		
	}

	
		
}
