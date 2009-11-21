package Model;

public class Pastilla extends Item {
	private int tiempoDeEstado;

	public Pastilla(Juego juego, int puntosPastilla, int tiempoDeEstado){
		this.setJuego(juego);
		this.setPuntosOtorgados(puntosPastilla);
		this.tiempoDeEstado = tiempoDeEstado;
	}
	
	public void hacerEfecto(){
		this.getJuego().getJugador().sumarPuntos(this.getPuntosOtorgados());
		this.getJuego().getTablero().getPacman().cambiarEstado(this.tiempoDeEstado);
		for(int i = 0; i < 5; i++)
			this.getJuego().getTablero().getFantasma(i).cambiarEstado(this.tiempoDeEstado);
	}
}
 
