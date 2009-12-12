package Model.item;

import Model.Punto;
import Model.juego.Juego;

public class Fruta extends Item {
	private int tiempoActivo;
	private int tiempoInactivo;
	private int tiempoActivoCorriendo;
	private int tiempoInactivoCorriendo;
	private boolean activado;
	private static int puntosOtorgados = 20;
	private Punto posicion;

	public Fruta(Juego juego, int tiempoActivo, int tiempoInactivo, Punto posicion){
		this.setJuego(juego);
		this.tiempoActivo = tiempoActivo;
		this.tiempoActivoCorriendo = 0;
		this.tiempoInactivoCorriendo = 0;
		this.tiempoInactivo = tiempoInactivo;
		this.activado = false;
		this.posicion = posicion;
		this.setComido(false);
	}
	
	//Configura los puntos otorgados de la clase Fruta.
	public final void setPuntosOtorgados(int puntos){
		Fruta.puntosOtorgados = puntos;
	}
	
	public Punto getPosicion(){
		return this.posicion;
	}
	
	//Suma la cantidad de puntos otorgados por comer una fruta a los puntos del jugador.
	public void hacerEfecto(){
		if (this.activado){
			this.getJuego().getJugador().sumarPuntos(Fruta.puntosOtorgados);
			this.setComido(true);
		}

	}

	public boolean getActivado(){
		return this.activado;
	}
	
	public boolean activador() {
		if (this.activado){
			if(this.tiempoActivoCorriendo == this.tiempoActivo){
				this.activado = false;
				this.tiempoActivoCorriendo = 0;
				return false;
			}
			else{
				this.tiempoActivoCorriendo++;
				return true;
			}			
		}
		else{
			if(this.tiempoInactivoCorriendo == this.tiempoInactivo){
				this.activado = true;
				this.tiempoInactivoCorriendo = 0;
				return true;
			}
			else{
				this.tiempoInactivoCorriendo++;
				return false;
			}
		}
	}

	@Override
	public Item comer() {
		if (this.activado)
			return new ItemNulo();
		return this;
	}
}
 
