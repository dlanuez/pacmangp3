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
		assertTrue(viviente.getVelocidad() == 1);
		assertTrue(viviente.getDireccionActual() == null);
		assertTrue(viviente.getJuego() == this.juego);
		assertTrue(viviente.getPosicion() == this.punto);
	}
	
	// Controla que si se inicializa un objeto con una posición inválida, se lance una exepción
	public void testVivienteERROR() {		
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
		//si es cero no debe poder moverse más a la izquierda
		viviente.irAIzquierda();
		assertEquals(viviente.getPosicion(), new Punto(0,1));
	}
		
	public void testIrADerecha() {
		viviente.irADerecha();
		assertEquals(viviente.getPosicion(), new Punto(2,1));
		
		//si está al límite derecho del tablero, no debe poder moverse más a la derecha
		try {
			viviente.setPosicion(viviente.getJuego().getTablero().getMaxPosX(), 1);
		} catch (PosicionInvalidaException e) {
			fail("El límite en X del tablero se considera una posición válida.");
		}
		viviente.irADerecha();
		assertEquals(viviente.getPosicion(), new Punto(viviente.getJuego().getTablero().getMaxPosX(),1));
	}

	public void testIrArriba() {
		viviente.irArriba();
		assertEquals(viviente.getPosicion(), new Punto(1,0));
		//si es cero no debe poder moverse más a hacia arriba
		viviente.irArriba();
		assertEquals(viviente.getPosicion(), new Punto(1,0));
	}

	public void testIrAbajo() {
		viviente.irAbajo();
		assertEquals(viviente.getPosicion(), new Punto(1,2));
		
		//si está en el límite inferior del tablero, no debe poder moverse más hacia abajo
		try {
			viviente.setPosicion(1, viviente.getJuego().getTablero().getMaxPosY());
		} catch (PosicionInvalidaException e) {
			fail("El límite en X del tablero se considera una posición válida.");
		}
		viviente.irAbajo();
		assertEquals(viviente.getPosicion(), new Punto(1, viviente.getJuego().getTablero().getMaxPosY()));
	}

	/*public void testCambiarEstado() {
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

	public void testSetPosicionINT(){
		fail("not yersadasda");
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
