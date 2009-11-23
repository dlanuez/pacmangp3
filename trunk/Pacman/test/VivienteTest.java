import Model.*;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import junit.framework.TestCase;


public class VivienteTest extends TestCase {

	private Viviente viviente;
	private Juego juego;
	private Punto punto;
	private EstadoViviente estadoViviente;
		
	protected void setUp() throws Exception {
		super.setUp();
		this.punto = new Punto(1,1);
		this.juego = new Juego();
		this.viviente = new Pacman(this.punto, this.juego);
	}

	public void testVivienteOK() {
		assertTrue(viviente.estaVivo());
		//TODO assertTrue(viviente.getVelocidad() == 1);
		//assertTrue(viviente.getDireccionActual() == null);
	}
	
	/*public void testVivienteERROR() {
		
		try{
			viviente = new Pacman(new Punto(-1,0), null);
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		catch(VelocidadInvalidaException e){
			fail("La velocidad con la que se inicializa el Pacman debería ser válida.");
		}
				
	}

	public void testIrAIzquierda() {
		viviente.irAIzquierda();
		assertEquals(viviente.getPosicion(), new Punto(0,1));
	}
	

	public void testIrADerecha() {
		fail("Not yet implemented");
	}

	public void testIrArriba() {
		fail("Not yet implemented");
	}

	public void testIrAbajo() {
		fail("Not yet implemented");
	}

	public void testCambiarEstado() {
		fail("Not yet implemented");
	}

	public void testToggleState() {
		fail("Not yet implemented");
	}

	public void testSetPosicionOK() {
		try{
			viviente.setPosicion(new Punto(2,2));
			viviente.setPosicion(new Punto(0,0));
			viviente.setPosicion(new Punto(viviente.getJuego().getTablero().getMaxPosX(),0));
			viviente.setPosicion(new Punto(0,viviente.getJuego().getTablero().getMaxPosY()));
		}
		catch(PosicionInvalidaException e){
			fail("Las posiciones son válidas, y arroja una excepción de posición inválida");
		}
	}

	public void testSetEstado() {
		fail("Not yet implemented");
	}

	public void testGetEstado() {
		fail("Not yet implemented");
	}

	public void testGetVelocidad() {
		fail("Not yet implemented");
	}

	public void testSetVelocidadOK() {
		try{
			viviente.setVelocidad(2);
			viviente.setVelocidad(0);
		}
		catch(VelocidadInvalidaException e){
			fail("Las velocidades son válidas, y arroja una excepción de velocidad inválida");
		}
	}

	public void testVivir() {
		fail("Not yet implemented");
	}

	public void testFenecer() {
		fail("Not yet implemented");
	}

	public void testEstaVivo() {
		fail("Not yet implemented");
	}
	*/
}
