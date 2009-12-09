package View;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.vista.Imagen;

public class VistaFruta extends Imagen {
	
	private ControladorJuego controlador;

	public VistaFruta(String nombreImagen, ControladorJuego controlador){
		super();
		this.setNombreArchivoImagen(nombreImagen);
		this.controlador = controlador;
	}

	public void desactivado() {
		this.controlador.removerDibujable(this);
	}

	public void activado() {
		this.controlador.agregarDibujable(this);
	}
}
