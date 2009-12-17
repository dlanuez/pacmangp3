package Controller;

import java.awt.Color;
import Controller.menu.PanelFinDelJuego;
import Controller.menu.VentanaPrincipal;
import Model.juego.Juego;
import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;

public class JuegoVivo implements ObjetoVivo {
	private Juego juego;
	private ControladorJuego controlador;
	private VentanaPrincipal ventana;
	private int contador = 0;
	private InicializadorJuego inicializador;

	public JuegoVivo(Juego juego, ControladorJuego controlador, VentanaPrincipal ventana, InicializadorJuego inicializador){
		this.juego = juego;	
		this.controlador = controlador;
		this.ventana = ventana;
		this.inicializador = inicializador;
	
	}
	
	public void vivir() {
		contador++;
		if(this.juego.getJugador().getVidas() == 0){
			this.finalizarJuego(this.ventana);	
		}
		
		if(this.juego.getTablero().getCantidadDeBolitas() == 0){
			this.controlador.detenerJuego();
			
			if(this.juego.pasarDeNivel()){
				
				Color color;
				if(juego.getNivel() % 2 == 0) color = Color.BLUE;
				else color = Color.RED;
				
				inicializador.inicializarControladorJuego(color, this.juego, this);
				this.controlador.comenzarJuego();
			}
				
			else finalizarJuego(ventana);
		}
	}
	
		

	private void finalizarJuego(VentanaPrincipal ventana) {
		this.controlador.detenerJuego();
		PanelFinDelJuego panelFDJ = new PanelFinDelJuego(ventana,this.juego.getJugador());	
		//this.ventana.setFocusable(true);
		panelFDJ.setBackground(Color.BLACK);
		panelFDJ.setOpaque(true);
		this.ventana.setContentPane(panelFDJ);
		this.ventana.setVisible(true);
	}

	public String getTexto() {
		String texto = "Puntaje: ";		
		texto += (Integer.toString(this.juego.getJugador().getPuntos()));
		return texto;
	}
	
	

	public int getCantidadDeVidas() {		
		return this.juego.getJugador().getVidas();
	}
		

	public void setControlador(ControladorJuego controlador) {
		this.controlador = controlador;
		
	}
}
