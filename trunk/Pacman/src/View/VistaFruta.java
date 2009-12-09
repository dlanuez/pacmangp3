package View;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.vista.Imagen;

public class VistaFruta extends Imagen {
	
	private ControladorJuego controlador;
	private String nombreImagen;

	public VistaFruta(String nombreImagen, ControladorJuego controlador){
		super();
		this.nombreImagen = nombreImagen;
		this.setNombreArchivoImagen(nombreImagen);
		this.controlador = controlador;
	}

	public void comido() {
		this.controlador.removerDibujable(this);
	}

	public void desactivado() {
		this.setNombreArchivoImagen("");
	}

	public void activado() {
		this.setNombreArchivoImagen(this.nombreImagen);
	}
}
