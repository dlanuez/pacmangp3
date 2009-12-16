package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.Playback;
import ar.uba.fi.algo3.titiritero.vista.KeyPressedController;

import Controller.FantasmaVivo;
import Controller.JuegoVivo;
import Controller.PacmanVivo;
import Model.juego.Juego;
import Model.viviente.Fantasma;
import View.PosicionableLaberinto;
import View.VentanaPrincipalJuego;
import View.VistaCantidadDeVidas;
import View.VistaFantasma;
import View.VistaJuego;
import View.VistaLaberintoFactory;
import View.VistaPacman;
import View.VistaTablero;

public class AccionJugar implements ActionListener {
	
	private Playback playback;
	private File file;
	private ControladorJuego controlador;
	private VentanaPrincipalJuego ventana;
	
	public void actionPerformed(ActionEvent arg0) {
		
		
		System.out.println("JUEGO!");
		
		this.controlador = new ControladorJuego();
		this.ventana = new VentanaPrincipalJuego(this.controlador);
		this.ventana.setVisible(true);
		
		Juego juego = new Juego("src/Model/nivel1.xml");
		try {
			juego.getTablero().inicializar();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}				
		
		file = new File("src/View/start.wav");
		playback = new Playback(file);
		
		JuegoVivo juegoVivo = new JuegoVivo(juego, controlador);
		inicializarControladorJuego(Color.RED, juego, juegoVivo);
		
		controlador.comenzarJuegoAsyn();
	}
	
	public void inicializarControladorJuego(Color color, Juego juego, JuegoVivo juegoVivo){		
		
		playback.start();		
		controlador.setIntervaloSimulacion(150);
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		ventana.addKeyListener(new KeyPressedController(controlador));
				
		PacmanVivo unPacman = new PacmanVivo(juego.getTablero().getPacman());
		VistaPacman vistaPacman = new VistaPacman();
		vistaPacman.setPosicionable(unPacman);		
		controlador.agregarObjetoVivo(juegoVivo);
		controlador.agregarObjetoVivo(unPacman);		
		controlador.agregarKeyPressObservador(unPacman);
		
		VistaTablero vistaTablero = new VistaTablero(512,600);
		vistaTablero.setPosicionable(vistaTablero);
		controlador.agregarDibujable(vistaTablero);
		VistaLaberintoFactory.generarTablero(controlador, juego, color);
		
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
		juegoVivo.setControlador(controlador);		
		
	}
	
	private void agregarFantasma(Fantasma fantasma, ControladorJuego controlador) {
		FantasmaVivo fantasmaVivo = new FantasmaVivo(fantasma);
		VistaFantasma vistaFantasma = new VistaFantasma(fantasmaVivo, 20,20);
		
		controlador.agregarDibujable(vistaFantasma);
		controlador.agregarObjetoVivo(fantasmaVivo);
		
	}

}
