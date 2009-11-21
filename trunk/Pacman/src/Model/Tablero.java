package Model;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Tablero {
	
	private final int MAX_POS_X;
	private final int MAX_POS_Y;
	private Casillero matriz[][];
	private Fantasma fantasma[];
	private Pacman pacman;
	
	public Tablero(){
		pacman = new Pacman();
		MAX_POS_X = 8;
		MAX_POS_Y = 8;
		matriz = new Casillero[MAX_POS_X][MAX_POS_Y];
		/*Modificar cada Fantasma() por el fantasma que corresponda*/
		fantasma[0] = new FantasmaRojo();
		fantasma[1] = new FantasmaRosa();
		fantasma[2] = new FantasmaNaranja();
		fantasma[3] = new FantasmaAzul();
		fantasma[4] = new FantasmaVerde();
		
	}
	
	public Casillero getCasillero(Punto punto){
		return matriz[punto.x()][punto.y()];
	}
	
	public void resetearPosiciones(){
		final Punto punto;
		punto = new Punto(5,5);
		pacman.setPosicion(punto);
		
	}
	
	public final int getMaxPosX() {
		return this.MAX_POS_X;
	}
	
	public final int getMaxPosY() {
		return this.MAX_POS_X;
	}

}
