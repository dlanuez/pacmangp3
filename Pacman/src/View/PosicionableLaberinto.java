package View;

import ar.uba.fi.algo3.titiritero.Posicionable;

public class PosicionableLaberinto implements Posicionable {
	private int x;
	private int y;
	
	public PosicionableLaberinto(int x, int y){
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return this.x;
	}


	public int getY() {		
		return this.y;
	}
	

}
