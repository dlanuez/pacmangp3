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
		this.juego = new Juego("src/Model/nivel1.xml");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pacman.setDireccionActual(Direcciones.DERECHA);
		pacman.vivir();
		//Como en el laberinto de prueba en esa posicion hay un fantasma me come
		assertFalse(pacman.estaVivo());		
		pacman.vivir();
		//Ahora pacman deberia reespawnearse a su posicion inicial
		assertEquals(puntoAuxiliar, pacman.getPosicion());
	}
	
	public void testRevivir(){
		Punto puntoAuxiliar = pacman.getPosicion();
		try {
			pacman.setPosicion(new Punto(2,1));
		} catch (PosicionInvalidaException e) {
			// TODO Auto-generated catch block
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
	
	public void testReEspawnear(){
		Punto puntoAuxiliar = pacman.getPosicion();
		try {
			pacman.setPosicion(new Punto(2,1));
		} catch (PosicionInvalidaException e) {
			// TODO Auto-generated catch block
			fail("Tiro excepcion");
		}
		pacman.reEspawnear();
		assertEquals(puntoAuxiliar, pacman.getPosicion());
	}
}
