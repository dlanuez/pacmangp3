package Controller;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;


import Controller.menu.VentanaPrincipal;
import Model.juego.Juego;
import Model.viviente.Fantasma;
import Model.viviente.FantasmaAzul;
import Model.viviente.FantasmaNaranja;
import Model.viviente.FantasmaRojo;
import Model.viviente.FantasmaRosa;
import Model.viviente.FantasmaVerde;
import View.PosicionableLaberinto;
import View.VistaCantidadDeVidas;
import View.VistaFantasma;
import View.VistaJuego;
import View.VistaLaberintoFactory;
import View.VistaPacman;
import View.VistaTablero;
import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.Playback;

public class InicializadorJuego {
	
	private  ControladorJuego controlador;
	private  VentanaPrincipal ventana;
	private static Playback playback;
	private File file;
	
	public InicializadorJuego(VentanaPrincipal ventana){
		
		this.controlador = new ControladorJuego();
		this.ventana = ventana;
			
		Juego juego = new Juego("src/Model/nivel1.xml");
		juego.getTablero().inicializar();
			
		file = new File("src/View/start.wav");
		playback = new Playback(file);
		
		JuegoVivo juegoVivo = new JuegoVivo(juego, controlador, ventana, this);
		inicializarControladorJuego(Color.RED, juego, juegoVivo);
	}
	
	public  void inicializarControladorJuego(Color color, Juego juego, JuegoVivo juegoVivo){		
		controlador = new ControladorJuego();
	
		playback.start();		
		controlador.setIntervaloSimulacion(150);
		this.ventana.inicializarParaJuego(controlador);
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		
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
		
	private static void agregarFantasma(Fantasma fantasma, ControladorJuego controlador) {
		FantasmaVivo fantasmaVivo = new FantasmaVivo(fantasma);
		VistaFantasma vistaFantasma = null;
	
		if (fantasma instanceof FantasmaVerde)		
			vistaFantasma = new VistaFantasma(fantasmaVivo,"imagenes/FantasmaVerde.jpg");
		
		if (fantasma instanceof FantasmaRojo)		
			vistaFantasma = new VistaFantasma(fantasmaVivo,"imagenes/FantasmaRojo.jpg");
		
		if (fantasma instanceof FantasmaAzul)		
			vistaFantasma = new VistaFantasma(fantasmaVivo,"imagenes/FantasmaAzul.jpg");
		
		if (fantasma instanceof FantasmaNaranja)		
			vistaFantasma = new VistaFantasma(fantasmaVivo,"imagenes/FantasmaNaranja.jpg");
		
		if (fantasma instanceof FantasmaRosa)		
			vistaFantasma = new VistaFantasma(fantasmaVivo,"imagenes/FantasmaRosa.jpg");
		
		if (vistaFantasma == null) throw new RuntimeException("NO SE ENCONTRO LA INSTANCIA DEL FANTASMA");
		
		controlador.agregarDibujable(vistaFantasma);
		controlador.agregarObjetoVivo(fantasmaVivo);
		
	}

	public void comenzarJuegoAsyn(){
		controlador.comenzarJuegoAsyn();
	}

}
