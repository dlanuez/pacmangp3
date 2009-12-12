package Model.juego;

import Model.excepciones.cantidadDeVidasInvalidaExeption;

public class Jugador {
	private int puntos;
	private int vidas;
	
	//Inicializa al jugador con 0 puntos y 3 vidas.
	public Jugador(){
		this.puntos = 0;
		this.vidas = 3;
	}
		
		
	public Jugador(int cantidadDeVidas) throws cantidadDeVidasInvalidaExeption {
		if(cantidadDeVidas > 0)
			this.vidas = cantidadDeVidas;
		else throw new cantidadDeVidasInvalidaExeption();
		this.puntos = 0;
	}
	
	//Le suma los puntos otorgados a los puntos del jugador.
	public void sumarPuntos(int puntosOtorgados) {
		this.puntos += puntosOtorgados;
		if (this.puntos%1000 == 0)
			this.sumarVida();
	}
	
	//Le quita una vida al jugador y devuelve true si el jugador se quedo sin vidas.
	public boolean restarVida(){
		this.vidas--;
		if (this.vidas <= 0){
			return true;
		}
		else
			return false;
	}
	
	//Le suma 1 vida al jugador.
	public void sumarVida(){
		this.vidas ++;
	}
	
}
