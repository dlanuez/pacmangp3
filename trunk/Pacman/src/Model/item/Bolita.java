package Model.item;


import Model.juego.Juego;
import View.VistaBolita;

public class Bolita extends Item {

	private static int puntosOtorgados = 10;
	private VistaBolita dibujable;

	//El puntaje otorgado por defecto es 10.
	public Bolita(Juego juego){
		this.setJuego(juego);
	}
	
	//Configura los puntos otorgados de la clase Bolita.
	public final void setPuntosOtorgados(int puntos){
		Bolita.puntosOtorgados = puntos;
	}
	
	public void setVistaBolita(VistaBolita dibujable){
		this.dibujable = dibujable;
	}
	
	//Suma la cantidad de puntos otorgados por comer una bolita a los puntos del jugador.
	public void hacerEfecto(){
		this.getJuego().getJugador().sumarPuntos(Bolita.puntosOtorgados);
		this.getJuego().getTablero().decrementarContadorBolitas();
		this.dibujable.comido();
	}

	@Override
	public Item comer() {
		return new ItemNulo();
	}
}
 
