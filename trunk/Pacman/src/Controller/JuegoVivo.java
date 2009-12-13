package Controller;

import java.util.Iterator;

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
import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import ar.uba.fi.algo3.titiritero.vista.KeyPressedController;

public class JuegoVivo implements ObjetoVivo {
	private Juego juego;
	private ControladorJuego controlador;
	private VentanaPrincipal ventana;
	private int contador = 0;

	public JuegoVivo(Juego juego, VentanaPrincipal ventana){
		this.juego = juego;
		this.ventana = ventana;
	}
	
	public void vivir() {
		contador++;
		if(this.juego.getJugador().getVidas() == 0){
			this.controlador.detenerJuego();	
		}
		if(this.juego.getTablero().getCantidadDeBolitas() == 0){
			this.controlador.detenerJuego();
			this.juego.pasarDeNivel();
			this.inicializarControlador();
			this.controlador.comenzarJuego();
		}
	}

	
	public void inicializarControlador(){
		this.controlador = new ControladorJuego();
		this.controlador.setIntervaloSimulacion(150);
		this.controlador.setSuperficieDeDibujo(this.ventana.getSuperficieDeDibujo());
		this.ventana.addKeyListener(new KeyPressedController(this.controlador));
		PacmanVivo unPacman = new PacmanVivo(this.juego.getTablero().getPacman());
		VistaPacman vistaPacman = new VistaPacman();
		vistaPacman.setPosicionable(unPacman);		
		this.controlador.agregarObjetoVivo(this);
		this.controlador.agregarObjetoVivo(unPacman);		
		this.controlador.agregarKeyPressObservador(unPacman);
		
		VistaTablero vistaTablero = new VistaTablero(512,600);
		vistaTablero.setPosicionable(vistaTablero);
		this.controlador.agregarDibujable(vistaTablero);
		VistaLaberintoFactory.generarTablero(this.controlador, this.juego);
		
		Iterator<Fantasma> fantasmaIterator = this.juego.getTablero().getFantasmasIterador();
		while(fantasmaIterator.hasNext()){
			agregarFantasma(fantasmaIterator.next(), this.controlador);
		}
		
		this.controlador.agregarDibujable(vistaPacman);
		
		VistaJuego vistaJuego = new VistaJuego(this);
		
		VistaCantidadDeVidas vistaCantidadDeVidas = new VistaCantidadDeVidas(30,this);
		PosicionableLaberinto posicionable = new PosicionableLaberinto(20,530);
		vistaJuego.setPosicionable(posicionable);
		this.controlador.agregarDibujable(vistaJuego);
		this.controlador.agregarDibujable(vistaCantidadDeVidas);
		
	}
		
	private void agregarFantasma(Fantasma fantasma, ControladorJuego controlador) {
		FantasmaVivo fantasmaVivo = new FantasmaVivo(fantasma);
		VistaFantasma vistaFantasma = new VistaFantasma(fantasmaVivo, 20,20);
		
		controlador.agregarDibujable(vistaFantasma);
		controlador.agregarObjetoVivo(fantasmaVivo);
		
	}

	public String getTexto() {
		String texto = "Puntaje: ";
		texto += (Integer.toString(this.juego.getTablero().getCantidadDeBolitas()));
		//texto += (Integer.toString(this.juego.getJugador().getPuntos()));
		return texto;
	}

	public int getCantidadDeVidas() {		
		return this.juego.getJugador().getVidas();
	}
	
	public ControladorJuego getControladorJuego(){
		return this.controlador;
	}
}
