package TP3;

import java.util.Iterator;

import Controller.FantasmaVivo;
import Controller.PacmanVivo;
import Model.juego.Juego;
import Model.viviente.Fantasma;
import View.VentanaPrincipal;
import View.VistaFantasma;
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
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
				
		
		
		PacmanVivo unPacman;

		
		unPacman = new PacmanVivo(juego.getTablero().getPacman());	
		VistaPacman vistaPacman = new VistaPacman();
		vistaPacman.setPosicionable(unPacman);		
							
		controlador.agregarObjetoVivo(unPacman);
		controlador.agregarKeyPressObservador(unPacman);

		VistaTablero vistaTablero = new VistaTablero(512,512);
		vistaTablero.setPosicionable(vistaTablero);
		controlador.agregarDibujable(vistaTablero);

		VistaLaberintoFactory.generarTablero(controlador, juego);
		
		Iterator<Fantasma> fantasmaIterator = juego.getTablero().getFantasmasIterador();
		while(fantasmaIterator.hasNext()){
			agregarFantasma(fantasmaIterator.next(), controlador);
		}		
				
		controlador.agregarDibujable(vistaPacman);		
		controlador.setIntervaloSimulacion(200);
		controlador.comenzarJuego();
	}

	private static void agregarFantasma(Fantasma fantasma, ControladorJuego controlador) {
		FantasmaVivo unFantasma = new FantasmaVivo(fantasma);
		VistaFantasma vistaFantasma = new VistaFantasma(20,20);
		vistaFantasma.setColor(unFantasma.getColor());
		vistaFantasma.setPosicionable(unFantasma);
		controlador.agregarDibujable(vistaFantasma);
		controlador.agregarObjetoVivo(unFantasma);
		
	}
	
		
}
