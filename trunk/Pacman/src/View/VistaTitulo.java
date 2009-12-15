package View;

import java.awt.Color;
import View.VistaTexto;

public class VistaTitulo extends VistaTexto{
	
	
	public VistaTitulo(Color unColor, String texto, int posicionInicialX, int posicionInicialY){
		super(unColor, unColor, texto, posicionInicialX, posicionInicialY);
		this.setFuente("Serif", 34);
	}
		
}
