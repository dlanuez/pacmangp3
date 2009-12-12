package View;

import Model.item.Fruta;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Imagen;

public class VistaFruta extends Imagen {
	
	private Fruta fruta;

	public VistaFruta(String nombreImagen, Fruta fruta){
		super();
		this.setNombreArchivoImagen(nombreImagen);
		this.fruta = fruta;
	}

	public void dibujar(SuperficieDeDibujo superfice){
		if (!this.fruta.getComido())
			if (this.fruta.getActivado())
				super.dibujar(superfice);
	}
}
