package View;

import java.awt.Color;

import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Texto;

public class VistaTexto extends Texto  implements Posicionable {

	String texto;
	private boolean seleccionado;
	private final int posicionInicialX;
	private final int posicionInicialY;
	private Color colorDesSeleccionado;
	private Color colorSeleccionado;
	
	
	public VistaTexto(Color colorDesSeleccionado, Color colorSeleccionado, String texto, int posicionInicialX, int posicionInicialY){
		this.colorDesSeleccionado = colorDesSeleccionado;
		this.colorSeleccionado = colorSeleccionado;
		
		this.setColor(colorDesSeleccionado);
		this.setTexto(texto);
		this.setFuente("SansSerif", 20);
		this.setPosicionable(this);
		this.seleccionado = false;
		
		if(posicionInicialX > 0)
			this.posicionInicialX = posicionInicialX;
		else throw new IllegalArgumentException();
		
		if(posicionInicialY > 0)
			this.posicionInicialY = posicionInicialY;
		else throw new IllegalArgumentException();
	}
	
	@Override
	protected String getTexto() {	
		return texto;
	}
	
	public void setTexto(String texto){
		this.texto = texto;
	}

	public int getX() {
		return posicionInicialX;
	}

	public int getY() {
		return posicionInicialY;
	}
	
	public void seleccionar(){
		this.seleccionado = true;
	}
	
	public void desSeleccionar(){
		this.seleccionado = false;
	}
	
	public void dibujar(SuperficieDeDibujo superfice){
		if(this.seleccionado)
			this.setColor(this.colorSeleccionado);
		else this.setColor(this.colorDesSeleccionado);
		super.dibujar(superfice);
	}
}
