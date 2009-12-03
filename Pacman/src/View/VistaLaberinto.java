package View;

import java.awt.Graphics;

import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Imagen;

public class VistaLaberinto extends Imagen {
	private int x,y;
	
	public VistaLaberinto(int posX, int posY, String nombreImagen){
		super();
		
		this.x = posX;
		this.y = posY;
		this.setNombreArchivoImagen(nombreImagen);
	}
	
	public void dibujar(SuperficieDeDibujo superficeDeDibujo) {
		Graphics grafico = (Graphics)superficeDeDibujo.getBuffer();
		grafico.drawImage(this.getImagen(), this.x, this.y, null);
	}
}
