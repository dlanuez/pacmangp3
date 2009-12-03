package View;

import ar.uba.fi.algo3.titiritero.Posicionable;

public class PosicionableLaberinto implements Posicionable {
	private int x;
	private int y;
	
	public PosicionableLaberinto(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {		
		return this.y;
	}
	

}
