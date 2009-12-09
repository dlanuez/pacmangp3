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
	
	//Suma la cantidad de puntos otorgados por comer una fruta a los puntos del jugador.
	public void hacerEfecto(){
		if (this.activado){
			this.getJuego().getJugador().sumarPuntos(Fruta.puntosOtorgados);
			this.getControlador().removerDibujable(this.getDibujableImagen());
		}

	}

	public boolean estaActivo() {
		if (this.activado){
			if(this.tiempoActivoCorriendo == this.tiempoActivo){
				this.activado = false;
				this.tiempoActivoCorriendo = 0;
				this.getControlador().removerDibujable(this.getDibujableImagen());
				return false;
			}
			else{
				this.tiempoActivoCorriendo++;
				this.getControlador().agregarDibujable(this.getDibujableImagen());
				return true;
			}			
		}
		else{
			if(this.tiempoInactivoCorriendo == this.tiempoInactivo){
				this.activado = true;
				this.tiempoInactivoCorriendo = 0;
				this.getControlador().agregarDibujable(this.getDibujableImagen());
				return true;
			}
			else{
				this.tiempoInactivoCorriendo++;
				this.getControlador().removerDibujable(this.getDibujableImagen());
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
 
