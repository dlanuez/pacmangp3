package Model;
/*******************************************************
 * TP 3 Algoritmos y Programaci�n III
 * Clase Tablero
 * 
 * 
 * 
 *******************************************************/

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
	
	public Tablero(String nivel, Juego juego){
		this.juego = juego;
		MAX_POS_X = 16;
		MAX_POS_Y = 16;
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
		this.cantidadDeBolitas = calcularCantidadDeBolitas();
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
		
		
		fantasmas = new Fantasma[5];
		Punto puntoInicialFantasma = averiguarPuntosFantasma();		
		
	 	try{
			fantasmas[0] = new FantasmaRojo(puntoInicialFantasma, juego);
			fantasmas[1] = new FantasmaRosa(puntoInicialFantasma,juego);
			fantasmas[2] = new FantasmaNaranja(puntoInicialFantasma, juego);
			fantasmas[3] = new FantasmaAzul(puntoInicialFantasma, juego);
			fantasmas[4] = new FantasmaVerde(puntoInicialFantasma, juego);
		}catch(PosicionInvalidaException e){
			e.printStackTrace();
		} catch (VelocidadInvalidaException e) {
			e.printStackTrace();
		}
	}
	

	private Punto averiguarPuntosFantasma() {
		Punto puntoAuxiliar = null;
		for(int i = 0; i < MAX_POS_X; i++){
			for(int k = 0; k < MAX_POS_Y; k++){
				if((matriz[i][k].casilleroHabilitado()) && (matriz[i][k].getItem() == null)){
					puntoAuxiliar = new Punto(i,k);				
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
	
	private int calcularCantidadDeBolitas() {
		for(int i = 0; i < MAX_POS_X; i++){
			for(int k = 0; k < MAX_POS_Y; k++){
				if(matriz[i][k].getItem() != null){
					this.cantidadDeBolitas++;
				}
			}
		}
		return 0;
	}
}
