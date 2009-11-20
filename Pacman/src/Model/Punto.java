package Model;

import java.lang.Math;

public class Punto {
	private int x;
	private int y;
	
	public int x() {
		return x;
	}
	public void x(int x) {
		this.x = x;
	}
	
	public int y() {
		return y;
	}
	public void y(int y) {
		this.y = y;
	}
	
	public double distanciaAOtroPunto(Punto otroPunto){
		return Math.sqrt(Math.pow(this.x()+otroPunto.x(), 2) + Math.pow(this.y()+otroPunto.y(), 2));
	}


}
