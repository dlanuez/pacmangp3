package Controller;

import java.awt.Color;
import java.io.FileNotFoundException;

import Model.juego.Juego;
import TP3.Programa;
import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;

public class JuegoVivo implements ObjetoVivo {
	private Juego juego;
	private ControladorJuego controlador;
	private int contador = 0;

	public JuegoVivo(Juego juego, ControladorJuego controlador){
		this.juego = juego;	
		this.controlador = controlador;
	
	}
	
	public void vivir() {
		contador++;
		if(this.juego.getJugador().getVidas() == 0){
			this.controlador.detenerJuego();	
		}
		if(this.juego.getTablero().getCantidadDeBolitas() == 0){
			this.controlador.detenerJuego();
			try {
				this.juego.pasarDeNivel();
			} catch (FileNotFoundException e) {				
				finalizarJuego();
			}
			Color color;
			if(juego.getNivel() % 2 == 0){
				color = Color.BLUE;
			}else{
				color = Color.RED;
			}
			Programa.inicializarControlador(color, this.juego, this);
			this.controlador.comenzarJuego();
		}
	}
	
		

	private void finalizarJuego() {
		this.controlador.detenerJuego();
		
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
