package Model;
/*******************************************************
 * TP 3 Algoritmos y Programaci�n III
 * Clase Tablero
 * 
 * 
 * 
 *******************************************************/

import java.util.ArrayList;

import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;

public class Tablero {
	private String nivel;
	private int cantidadDeBolitas;
	private final int MAX_POS_X;
	private final int MAX_POS_Y;
	private Casillero matriz[][];
	private Fantasma fantasmas[];
	private Pacman pacman;
	private Juego juego;
	private ArrayList<Punto> cueva;
	
	public Tablero(String nivel, Juego juego){
		this.juego = juego;
		MAX_POS_X = 4;
		MAX_POS_Y = 2;
		this.nivel = nivel;
		this.cantidadDeBolitas = 0;
	}
	
	public Tablero(String nivel, Juego juego, int maxX, int maxY){
		this.juego=juego;
		MAX_POS_X = maxX;
		MAX_POS_Y = maxY;
		this.nivel = nivel;
		this.cantidadDeBolitas = 0;
	}

	public void inicializar() {
		InicializadorTablero inicializador = new InicializadorTablero(nivel, juego, MAX_POS_X, MAX_POS_Y);
		matriz = inicializador.generarTablero();
		calcularCantidadDeBolitas();
		Punto punto = new Punto(2,2);
		
		try {
			pacman = new Pacman(punto, juego);
		} catch (PosicionInvalidaException e1) {			
			e1.printStackTrace();
		} catch (VelocidadInvalidaException e) {
			e.printStackTrace();
		}
		
		
		fantasmas = new Fantasma[5];
		cueva = averiguarPuntosFantasma();		
		
	 	try{
			fantasmas[0] = new FantasmaRojo(this.cueva.get(0), juego);
			fantasmas[1] = new FantasmaRosa(this.cueva.get(1),juego);
			fantasmas[2] = new FantasmaNaranja(this.cueva.get(2), juego);
			fantasmas[3] = new FantasmaAzul(this.cueva.get(3), juego);
			fantasmas[4] = new FantasmaVerde(this.cueva.get(4), juego);
		}catch(PosicionInvalidaException e){
			e.printStackTrace();
		} catch (VelocidadInvalidaException e) {
			e.printStackTrace();
		}
	}
	

	private ArrayList<Punto> averiguarPuntosFantasma() {
		ArrayList<Punto> puntoAuxiliar = new ArrayList<Punto>();
		for(int i = 0; i < MAX_POS_X; i++){
			for(int k = 0; k < MAX_POS_Y; k++){
				if((matriz[i][k].casilleroHabilitado()) && (matriz[i][k].obtenerItem().getClass().getSimpleName().equals("ItemNulo"))){
					puntoAuxiliar.add(new Punto(i,k));				
				}
			}
		}
		return puntoAuxiliar;
	}

	public Casillero getCasillero(Punto punto){
		return matriz[punto.x()][punto.y()];
	}
	
	public void resetearPosiciones() throws PosicionInvalidaException{		
		try{
			final Punto punto;
			punto = new Punto(2,2);
			pacman.setPosicion(punto);
			int i = 0;
			for (Fantasma f : fantasmas){				
				f.setPosicion(cueva.get(i));
				f.revivir();
				i++;
			}
		}catch(PosicionInvalidaException e){
			throw new PosicionInvalidaException();
		}		
	}
	
	public int getMaxPosX() {
		return this.MAX_POS_X;
	}
	
	public int getMaxPosY() {
		return this.MAX_POS_Y;
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
	

	public void decrementarContadorBolitas(){
		this.cantidadDeBolitas--;
	}

	public int getCantidadDeBolitas() {
		return cantidadDeBolitas;
	}
	
	public ArrayList<Punto> getPosicionesCueva(){
		return this.cueva;
	}
	
	private void calcularCantidadDeBolitas() {
		for(int i = 0; i < MAX_POS_X; i++){
			for(int k = 0; k < MAX_POS_Y; k++){
				if(!(matriz[i][k].obtenerItem().getClass().getSimpleName().equals("ItemNulo"))){
					this.cantidadDeBolitas++;
				}
			}
		}
	}
	
}
