package Model;

import java.util.*;

import Model.excepciones.PosicionInvalidaException;

public class Fantasma extends Viviente {
	
	private Estrategia estrategia;
	private int puntosPorEsteFantasma;
	
	public Fantasma(Punto posicionInicial, Juego juego) throws PosicionInvalidaException{
		super(posicionInicial, juego);
		this.setEstado(EstadoViviente.CAZADOR);
		
	}
	
	public void mover(){
		
		Punto posicionPacman = this.getJuego().getTablero().getPacman().getPosicion();
		
		if (this.getEstado() == EstadoViviente.CAZADOR)
			this.irEnDireccion(
					this.estrategia.calcularNuevaDireccion( this.getPosicion(),
									posicionPacman, this.getDireccionActual(),
									this.getJuego().getTablero()));
		else
			this.irEnDireccion(
					(new EstrategiaEscapadora()).calcularNuevaDireccion(
								this.getPosicion(), posicionPacman,
								this.getDireccionActual(),
								this.getJuego().getTablero()));
		
		this.buscarPacman(posicionPacman);
	}

	public int getPuntosPorEsteFantasmaConCarinioParaCabu(){
		return this.puntosPorEsteFantasma;
		//return Cabu;
	}
		
	@Override
	public void vivir(){
		if (!this.estaVivo()){
			this.revivir();
		}
		super.vivir();
		this.mover();
	}
	
	public void revivir(){
		this.setVivo();
		this.setEstado(EstadoViviente.CAZADOR);
	}
	
	private void buscarPacman(Punto posicionPacman) {
		
		if(this.getPosicion().equals(posicionPacman))
			this.encontrePacman();
		
	}

	private void irEnDireccion(Direcciones direccion){
			
			switch(direccion){
			case ARRIBA:
				this.irArriba();
				break;				
			case ABAJO:
				this.irAbajo();
				break;
			case IZQUIERDA:
				this.irAIzquierda();
				break;
			case DERECHA:
				this.irADerecha();
				break;
			}		
		}
	
	private void encontrePacman() {
		
		if(this.getEstado() == EstadoViviente.CAZADOR){
			this.getJuego().pacmanComido();
			this.getJuego().getTablero().getPacman().fenecer();
			try {
				this.getJuego().getTablero().resetearPosiciones();
			} catch (PosicionInvalidaException e) {
				e.printStackTrace();
			}
		}
		else{
			this.getJuego().fantasmaComido(this.puntosPorEsteFantasma);
			this.fenecer();
			try {
				this.setPosicion(this.getPosicionDeRespawn());
			} catch (PosicionInvalidaException e) {
				/* Las posiciones de respawn deberían ser válidas, ya que se leen del archivo
				 * laberinto.xml. Si se llega a esta exepción, lo más probable es que
				 * getPosicionDeRespawn() haya devuelto un valor nulo.
				 */
				e.printStackTrace();
			}
		}
		
	}

	/* Este método busca y devuelve  una posición en la cueva que sea distinta
	 * de las posiciones de todos los fantasmas. Si no se encuentra ninguna
	 * posición libre en la cueva, devuelve null. 
	 */
	private Punto getPosicionDeRespawn() {
		
		//lista con las posiciones de la cueva

		Iterator<Fantasma> fantasmas = this.getJuego().getTablero().getFantasmasIterador();
		
		//lista con las posiciones de los fantasmas
		ArrayList<Punto> posicionesFantasmas = new ArrayList<Punto>();
		
		while(fantasmas.hasNext()){
			posicionesFantasmas.add(fantasmas.next().getPosicion());
		}		
		
		
		//-----------------------------------------------
		
		Iterator<Punto> itPC =this.getJuego().getTablero().getPosicionesCuevaIterador();
		
		while(itPC.hasNext()){
			int valorDeRetorno = 0;
			Punto posicionCuevaActual = itPC.next();
			//busca si la posicion de la cueva coincide con la de algún fantasma
			//si devuelve -1, entonces no se encontró, y la posición está disponible.
			valorDeRetorno = posicionesFantasmas.indexOf(posicionCuevaActual);
			
			if(valorDeRetorno == -1) //posicion disponible
				return posicionCuevaActual;
				
		}
		
		return null;
	}
	
	public void setEstrategia(Estrategia e){
		this.estrategia = e;
	}
	
}
