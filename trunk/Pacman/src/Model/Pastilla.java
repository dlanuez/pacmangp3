package Model;

import Model.excepciones.tiempoDeEstadoInvalidoException;

public class Pastilla extends Item {
	private int tiempoDeEstado;

	public Pastilla(Juego juego, int puntosPastilla, int tiempoDeEstado){
		this.setJuego(juego);
		this.setPuntosOtorgados(puntosPastilla);
		this.tiempoDeEstado = tiempoDeEstado;
	}
	
	//Suma la cantidad de puntos otorgados por comer una bolita a los puntos del jugador
	//y le cambia el estadoViviente al pacman y a los fantasmas.
	public void hacerEfecto(){
		this.getJuego().getJugador().sumarPuntos(this.getPuntosOtorgados());
		try {
			this.getJuego().getTablero().getPacman().cambiarEstado(this.tiempoDeEstado);
		} catch (tiempoDeEstadoInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < 5; i++)
			try {
				this.getJuego().getTablero().getFantasma(i).cambiarEstado(this.tiempoDeEstado);
			} catch (tiempoDeEstadoInvalidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
 
