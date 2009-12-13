package TP3;

import java.util.Iterator;

import Controller.FantasmaVivo;
import Controller.JuegoVivo;
import Controller.PacmanVivo;
import Model.juego.Juego;
import Model.viviente.Fantasma;
import View.PosicionableLaberinto;
import View.VentanaPrincipal;
import View.VistaCantidadDeVidas;
import View.VistaFantasma;
import View.VistaJuego;
import View.VistaLaberintoFactory;
import View.VistaPacman;
import View.VistaTablero;
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
		/*
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		
		
		PacmanVivo unPacman;
		
		unPacman = new PacmanVivo(juego.getTablero().getPacman());	
		VistaPacman vistaPacman = new VistaPacman();
		vistaPacman.setPosicionable(unPacman);		
							
		controlador.agregarObjetoVivo(unPacman);
		controlador.agregarKeyPressObservador(unPacman);

		VistaTablero vistaTablero = new VistaTablero(512,600);
		vistaTablero.setPosicionable(vistaTablero);
		controlador.agregarDibujable(vistaTablero);

		VistaLaberintoFactory.generarTablero(controlador, juego);
		
		Iterator<Fantasma> fantasmaIterator = juego.getTablero().getFantasmasIterador();
		while(fantasmaIterator.hasNext()){
			agregarFantasma(fantasmaIterator.next(), controlador);
		}		
				
		controlador.agregarDibujable(vistaPacman);
		
		VistaJuego vistaJuego = new VistaJuego(juegoVivo);
		VistaCantidadDeVidas vistaCantidadDeVidas = new VistaCantidadDeVidas(30,juegoVivo);
		PosicionableLaberinto posicionable = new PosicionableLaberinto(20,530);
		vistaJuego.setPosicionable(posicionable);
		controlador.agregarDibujable(vistaJuego);
		controlador.agregarDibujable(vistaCantidadDeVidas);
		
		controlador.setIntervaloSimulacion(150);
		controlador.comenzarJuego();
		*/
	}

	private static void agregarFantasma(Fantasma fantasma, ControladorJuego controlador) {
		FantasmaVivo fantasmaVivo = new FantasmaVivo(fantasma);
		VistaFantasma vistaFantasma = new VistaFantasma(fantasmaVivo, 20,20);
		
		controlador.agregarDibujable(vistaFantasma);
		controlador.agregarObjetoVivo(fantasmaVivo);
		
	}
	
		
}
