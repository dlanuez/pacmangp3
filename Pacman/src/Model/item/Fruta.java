package Model.item;

import Model.Punto;
import Model.juego.Juego;
import View.VistaFruta;

public class Fruta extends Item {
	private int tiempoActivo;
	private int tiempoInactivo;
	private int tiempoActivoCorriendo;
	private int tiempoInactivoCorriendo;
	private boolean activado;
	private static int puntosOtorgados = 20;
	private VistaFruta dibujable;

	public Fruta(Juego juego, int tiempoActivo, int tiempoInactivo, Punto posicion){
		this.setJuego(juego);
		this.tiempoActivo = tiempoActivo;
		this.tiempoInactivo = tiempoInactivo;
		this.activado = false;
	}
	
	//Configura los puntos otorgados de la clase Fruta.
	public final void setPuntosOtorgados(int puntos){
		Fruta.puntosOtorgados = puntos;
	}
	
	public void setVistaFruta(VistaFruta dibujable){
		this.dibujable = dibujable;
	}
	
	//Suma la cantidad de puntos otorgados por comer una fruta a los puntos del jugador.
	public void hacerEfecto(){
		if (this.activado){
			this.getJuego().getJugador().sumarPuntos(Fruta.puntosOtorgados);
			this.dibujable.comido();
		}

	}

	public boolean estaActivo() {
		if (this.activado){
			if(this.tiempoActivoCorriendo == this.tiempoActivo){
				this.activado = false;
				this.tiempoActivoCorriendo = 0;
				this.dibujable.desactivado();
				return false;
			}
			else{
				this.tiempoActivoCorriendo++;
				this.dibujable.activado();
				return true;
			}			
		}
		else{
			if(this.tiempoInactivoCorriendo == this.tiempoInactivo){
				this.activado = true;
				this.tiempoInactivoCorriendo = 0;
				this.dibujable.activado();
				return true;
			}
			else{
				this.tiempoInactivoCorriendo++;
				this.dibujable.desactivado();
				return false;
			}
		}
	}

	@Override
	public Item comer() {
		if (this.estaActivo())
			return new ItemNulo();
		return this;
	}
}
 
