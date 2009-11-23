package Model;

import java.lang.Math;

/* Clase utilizada para representar las posiciones de los objetos Vivientes en el tablero.
 * Puede tomar valores negativos, por lo tanto la validación de las posiciones debe hacerse en la clase
 * que lo utilice.
 */

public class Punto {
	private int x;
	private int y;
	
	public Punto(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int x() {
		return this.x;
	}
	public void x(int x) {
		this.x = x;
	}
	
	public int y() {
		return this.y;
	}
	public void y(int y) {
		this.y = y;
	}
	
	public void aumentarX(){
		this.x++;
	}
	
	public void disminuirX(){
		this.x--;
	}
	
	public void aumentarY(){
		this.y++;
	}
	
	public void disminuirY(){
		this.y--;
	}
	
	public double distanciaAOtroPunto(Punto otroPunto){
		return Math.sqrt(Math.pow(this.x()+otroPunto.x(), 2) + Math.pow(this.y()+otroPunto.y(), 2));
	}
	
	@Override
	public boolean equals(Object unPunto){
		Punto elPunto = (Punto)unPunto;
		if( (this.x() == elPunto.x()) && (this.y() == elPunto.y()) )
				return true;
		return false;		
	}


}
