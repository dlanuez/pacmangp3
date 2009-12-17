package Model.viviente;

import java.awt.Color;

import Model.Direcciones;
import Model.EstadoViviente;
import Model.Punto;
import Model.estrategia.Estrategia;
import Model.estrategia.EstrategiaEscapadora;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;

public class Fantasma extends Viviente {
	
	private Estrategia estrategia;
	private int puntosPorEsteFantasma;
	protected static Color colorPresa = Color.BLUE;
	protected static int velocidadPresa = 8;
	private int velocidadCazador;
	private Color colorCazador;
	private Color colorActual;
	private int tiempoDeEspera;
	
	public Fantasma(Punto posicionInicial, Juego juego, int velocidadCazador, Color colorCazador) throws PosicionInvalidaException, VelocidadInvalidaException{
		super(posicionInicial.clonar(), juego);
		
		this.setEstado(EstadoViviente.CAZADOR);
		
		this.setColorCazador(colorCazador);
		this.setColorActual(colorCazador);
		
		this.setVelocidadCazador(velocidadCazador);
		this.setVelocidadActual(velocidadCazador);
		//se setea el tiempo de espera para el movimiento del fantasma, en función de su velocidad
		this.reiniciarTiempoDeEspera();
		
	}
	
	public void mover(){
		
		if(!esperar()){
			Punto posicionPacman = this.getJuego().getTablero().getPacman().getPosicion();
			
			Direcciones dir;
			
			if (this.getEstado() == EstadoViviente.CAZADOR)
				
				dir = this.estrategia.calcularNuevaDireccion( 
						this.getPosicion(),
						posicionPacman,
						this.getDireccionActual(),
						this.getJuego().getTablero());
				
			
			else
				
				dir =(new EstrategiaEscapadora()).calcularNuevaDireccion(
						this.getPosicion(), 
						posicionPacman,
						this.getDireccionActual(),
						this.getJuego().getTablero());
			
			this.irEnDireccion(dir);
			this.buscarPacman(posicionPacman);
		}
		
	}

	public int getPuntosPorEsteFantasmaConCarinioParaCabu(){
		return this.puntosPorEsteFantasma;
		//return Cabu;
	}
		
	@Override
	public void vivir(){
		if (!this.estaVivo()){
			this.revivir(EstadoViviente.CAZADOR);
			this.setColorActual(this.colorCazador);
			try {
				this.setVelocidadActual(this.velocidadCazador);
			} catch (VelocidadInvalidaException e) {
				e.printStackTrace();
			}
		}
		super.vivir();
		this.mover();
	}
	
	private void reiniciarTiempoDeEspera(){
		this.tiempoDeEspera = 10 - this.getVelocidadActual();
	}
	
	private boolean esperar(){
		if(this.tiempoDeEspera == 0){
			this.reiniciarTiempoDeEspera();
			return false;
		}
		this.tiempoDeEspera--;
		return true;		
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
		if(this.getEstado() == EstadoViviente.CAZADOR)
			this.getJuego().pacmanComido();					
		else
			this.getJuego().fantasmaComido(this);	
	}
	
	public void setEstrategia(Estrategia e){
		this.estrategia = e;
	}

	public void setColorActual(Color color) {
		this.colorActual = color;
	}

	public Color getColorActual() {
		return colorActual;
	}
	
	@Override
	protected void toggleState(){
		super.toggleState();
		try{
			if(this.getEstado() == EstadoViviente.CAZADOR){
				this.setVelocidadActual(this.velocidadCazador);
				this.setColorActual(this.colorCazador);
			}
			else{
				this.setVelocidadActual(Fantasma.velocidadPresa);
				this.setColorActual(Fantasma.colorPresa);
			}
		}
		catch(VelocidadInvalidaException e){
			e.toString();
		}
	}

	public void setVelocidadCazador(int velocidadCazador) {
		this.velocidadCazador = velocidadCazador;
	}

	public void setColorCazador(Color colorCazador) {
		this.colorCazador = colorCazador;
	}

	public void cambiarColorACazador() {
		this.setColorActual(this.colorCazador);		
	}
	
}
