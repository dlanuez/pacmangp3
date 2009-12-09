package Model.item;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.vista.Figura;
import ar.uba.fi.algo3.titiritero.vista.Imagen;
import Model.excepciones.tiempoDeEstadoInvalidoException;
import Model.juego.Juego;

public abstract class Item {
 
	private Juego juego;
	private Figura dibujable;
	private Imagen dibujableImagen;
	private ControladorJuego controlador;
	
	//Configura la referencia al juego por la que se pasa por argumento.
	protected void setJuego(Juego juego){
		this.juego = juego;
	}
	
	//Devuelve la referencia al juego.
	protected Juego getJuego(){
		return this.juego;
	}
	
	//Metodo abstracto.
	public abstract void hacerEfecto() throws tiempoDeEstadoInvalidoException;

	public abstract Item comer();

	public void setDibujable(Figura dibujable) {
		this.dibujable = dibujable;
	}
	
	protected Figura getDibujable(){
		return this.dibujable;
	}

	public void setDibujableImagen(Imagen dibujable) {
		this.dibujableImagen = dibujable;
	}
	
	protected Imagen getDibujableImagen(){
		return this.dibujableImagen;
	}

	public void setControlador(ControladorJuego controlador) {
		this.controlador = controlador;
	}
	
	protected ControladorJuego getControlador(){
		return this.controlador;
	}
}
 