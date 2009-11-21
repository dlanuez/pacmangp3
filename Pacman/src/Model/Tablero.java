package Model;

public class Tablero {

	private final int MAX_POS_X;
	private final int MAX_POS_Y;
	private Casillero matriz[MAX_POS_X, MAX_POS_Y];
	private Fantasma fantasma[5];
	private Pacman pacman;
	
	public Tablero (){
		pacman = new Pacman();
		/*Modificar cada Fantasma() por el fantasma que corresponda*/
		fantasma[0] = new Fantasma();
		fantasma[1] = new Fantasma();
		fantasma[2] = new Fantasma();
		fantasma[3] = new Fantasma();
		fantasma[4] = new Fantasma();
		
	}
	
	public final int getMaxPosX() {
		return this.MAX_POS_X;
	}
	
	public final int getMaxPosY() {
		return this.MAX_POS_X;
	}

}
