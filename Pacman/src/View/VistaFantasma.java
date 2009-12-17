package View;



import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Imagen;
import Controller.FantasmaVivo;

public class VistaFantasma extends Imagen {

	private FantasmaVivo fantasmaVivo;
	private String nombreImagenCazador;
	private String nombreImagenPresa;
	
	public VistaFantasma(FantasmaVivo fantasma, String nombreImagenVivo) {
		super();
		this.nombreImagenCazador = nombreImagenVivo;
		this.setNombreArchivoImagen(nombreImagenVivo);
		this.fantasmaVivo = fantasma;
		this.setPosicionable(fantasma);
		this.nombreImagenPresa = "imagenes/FantasmaPresa.jpg";
	}
	
	public FantasmaVivo getFantasma(){
		return this.fantasmaVivo;
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
		this.setImagen(fantasmaVivo.estaCazando());
		super.dibujar(superfice);
	}
	
	public void setImagen(boolean estaCazando){
		if(estaCazando) this.setNombreArchivoImagen(this.nombreImagenCazador);
		else this.setNombreArchivoImagen(this.nombreImagenPresa);		
	}

}
