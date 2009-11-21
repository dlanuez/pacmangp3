package Model;
/*******************************************************
 * TP 3 Algoritmos y Programación III
 * Clase Tablero
 * 
 * 
 * 
 *******************************************************/

import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;

public class Tablero {
	
	private final int MAX_POS_X;
	private final int MAX_POS_Y;
	private Casillero matriz[][];
	private Fantasma fantasmas[];
	private Pacman pacman;
	private Juego juego;
	
	public Tablero(){
		Punto punto = new Punto(2,2);
		try {
			pacman = new Pacman(punto, juego);
		} catch (PosicionInvalidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (VelocidadInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MAX_POS_X = 8;
		MAX_POS_Y = 8;
		matriz = new Casillero[MAX_POS_X][MAX_POS_Y];
		/*Modificar cada Fantasma() por el fantasma que corresponda*/
		punto = new Punto(1,1);
		try{
			fantasmas[0] = new FantasmaRojo(punto, juego);
			fantasmas[1] = new FantasmaRosa(punto,juego);
			fantasmas[2] = new FantasmaNaranja(punto, juego);
			fantasmas[3] = new FantasmaAzul(punto, juego);
			fantasmas[4] = new FantasmaVerde(punto, juego);
		}catch(PosicionInvalidaException e){
			e.printStackTrace();
		}
	}
	
	public Casillero getCasillero(Punto punto){
		return matriz[punto.x()][punto.y()];
	}
	
	public void resetearPosiciones() throws PosicionInvalidaException{		
		try{
			final Punto punto;
			punto = new Punto(5,5);
			pacman.setPosicion(punto);
			punto.x(4);
			punto.y(4);
			for (Fantasma f : fantasmas){
				f.setPosicion(punto);
			}
		}catch(PosicionInvalidaException e){
			throw new PosicionInvalidaException();
		}
		
	}
	
	public final int getMaxPosX() {
		return this.MAX_POS_X;
	}
	
	public final int getMaxPosY() {
		return this.MAX_POS_X;
	}

	public Pacman getPacman(){
		return pacman;
	}
	
	public Fantasma[] getFantasmasArray(){
		return fantasmas;
	}
	
	public Fantasma getFantasma(int posicion){
		return fantasmas[posicion];
	}
}
