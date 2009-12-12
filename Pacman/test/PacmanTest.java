import Model.*;
import Model.excepciones.PosicionInvalidaException;
import Model.juego.Juego;
import Model.viviente.Pacman;
import junit.framework.TestCase;


public class PacmanTest extends TestCase {

	private Pacman pacman;
	private Juego juego;
	private Punto punto;
			
	protected void setUp() throws Exception {
		super.setUp();		
		this.juego = new Juego("src/Model/nivel1.xml", 16, 16);
		this.punto = new Punto(1,1);
		this.juego.getTablero().inicializar();
		this.pacman = this.juego.getTablero().getPacman();
		this.pacman.setEstado(null);
		this.pacman.setPosicion(this.punto);
	}
	
	public void testVivir(){
		Punto puntoAuxiliar = pacman.getPosicion();
		try {
			this.pacman.setPosicion(new Punto(2,1));
		} catch (PosicionInvalidaException e) {			
			e.printStackTrace();
		}
		
		pacman.setDireccionActual(Direcciones.DERECHA);
		pacman.vivir();
	
		assertTrue(pacman.estaVivo());		
		pacman.vivir();
		try{//Se coloca al pacman en la misma posicion que un fantasma
			pacman.setPosicion(this.juego.getTablero().getFantasma(1).getPosicion());
		}
		catch(PosicionInvalidaException e){
			e.printStackTrace();
		}
		pacman.vivir();
		assertFalse(pacman.estaVivo());
		//Ahora pacman deberia reespawnearse a su posicion inicial
		assertEquals(puntoAuxiliar, pacman.getPosicion());
	}
	
	public void testRevivir(){
		Punto puntoAuxiliar = pacman.getPosicion();
		try {
			pacman.setPosicion(new Punto(2,1));
		} catch (PosicionInvalidaException e) {			
			fail("tiro excepcion");
		}
		pacman.fenecer();
		assertFalse(pacman.estaVivo());
		pacman.revivir();
		assertTrue(pacman.estaVivo());
		assertEquals(pacman.getEstado(), EstadoViviente.PRESA);
		//Chequeo que se resetee la posicion
		assertEquals(puntoAuxiliar, pacman.getPosicion());
	}
	
	public void testReSpawn(){
		Punto puntoAuxiliar = pacman.getPosicion();
		try {
			pacman.setPosicion(new Punto(2,1));
		} catch (PosicionInvalidaException e) {
			// TODO Auto-generated catch block
			fail("Tiro excepcion");
		}
		pacman.reSpawn();
		assertEquals(puntoAuxiliar, pacman.getPosicion());
	}
}
