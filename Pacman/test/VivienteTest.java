import Model.*;
import Model.excepciones.EstadoNoInicializadoExeption;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.excepciones.tiempoDeEstadoInvalidoException;
import junit.framework.TestCase;


public class VivienteTest extends TestCase {

	private Viviente viviente;
	private Juego juego;
	private Punto punto;
			
	protected void setUp() throws Exception {
		super.setUp();
		this.punto = new Punto(1,1);
		this.juego = new Juego();
		this.viviente = new Pacman(this.punto, this.juego);
		this.viviente.setEstado(null);
	}

	// Controla que todo se inicialice correctamente
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
		assertEquals(viviente.getDireccionActual(), Direcciones.IZQUIERDA);
		//si es cero no debe poder moverse más a la izquierda
		viviente.irAIzquierda();
		assertEquals(viviente.getPosicion(), new Punto(0,1));
		assertEquals(viviente.getDireccionActual(), Direcciones.IZQUIERDA);
	}
		
	public void testIrADerecha() {
		viviente.irADerecha();
		assertEquals(viviente.getPosicion(), new Punto(2,1));
		assertEquals(viviente.getDireccionActual(), Direcciones.DERECHA);
		
		//si está en el límite derecho del tablero, no debe poder moverse más a la derecha
		try {
			viviente.setPosicion(viviente.getJuego().getTablero().getMaxPosX(), 1);
			//la última dirección debe seguir almacenada.
			assertEquals(viviente.getDireccionActual(), Direcciones.DERECHA); 
		} 
		catch (PosicionInvalidaException e) {
			fail("El límite en X del tablero se considera una posición válida.");
		}
		
		viviente.irADerecha();
		assertEquals(viviente.getPosicion(), new Punto(viviente.getJuego().getTablero().getMaxPosX(),1));
		assertEquals(viviente.getDireccionActual(), Direcciones.DERECHA); 
	}

	public void testIrArriba() {
		viviente.irArriba();
		assertEquals(viviente.getPosicion(), new Punto(1,0));
		assertEquals(viviente.getDireccionActual(), Direcciones.ARRIBA); 
		//si es cero no debe poder moverse más a hacia arriba
		viviente.irArriba();
		assertEquals(viviente.getPosicion(), new Punto(1,0));
		assertEquals(viviente.getDireccionActual(), Direcciones.ARRIBA); 
	}

	public void testIrAbajo() {
		viviente.irAbajo();
		assertEquals(viviente.getPosicion(), new Punto(1,2));
		assertEquals(viviente.getDireccionActual(), Direcciones.ABAJO); 
		
		//si está en el límite inferior del tablero, no debe poder moverse más hacia abajo
		try {
			viviente.setPosicion(1, viviente.getJuego().getTablero().getMaxPosY());
			//la última dirección debe seguir almacenada.
			assertEquals(viviente.getDireccionActual(), Direcciones.ABAJO); 
		} 
		catch (PosicionInvalidaException e) {
			fail("El límite en X del tablero se considera una posición válida.");
		}
		
		viviente.irAbajo();
		assertEquals(viviente.getPosicion(), new Punto(1, viviente.getJuego().getTablero().getMaxPosY()));
		assertEquals(viviente.getDireccionActual(), Direcciones.ABAJO); 
	}

	// Verifica que se lance una excepción cuando el estado no esta inicializado
	/*public void testToggleStateERROR() {
		try{
			viviente.toggleState();
			fail("El estado no estaba inicializado y no lanza excepción.");
		}
		catch(EstadoNoInicializadoExeption e){
			assertTrue(true);
		}
	}

	public void testToggleStateOK() {
		viviente.setEstado(EstadoViviente.PRESA);
		
		viviente.toggleState();
		assertTrue(viviente.getEstado() == EstadoViviente.CAZADOR);
		viviente.toggleState();
		assertTrue(viviente.getEstado() == EstadoViviente.PRESA);
	}*/
	
	public void testCambiarEstadoERROR(){
		viviente.setEstado(EstadoViviente.PRESA);
		
		try{
			viviente.cambiarEstado(0);
			fail("El tiempo de estado no es válido, y no arroja excepción.");
		}
		catch(tiempoDeEstadoInvalidoException e){
			assertTrue(true);
		}
		try{
			viviente.cambiarEstado(-1);
			fail("El tiempo de estado no es válido, y no arroja excepción.");
		}
		catch(tiempoDeEstadoInvalidoException e){
			assertTrue(true);
		}
		
	}
	
	public void testCambiarEstadoOK(){
		viviente.setEstado(EstadoViviente.PRESA);
		
		try{
			viviente.cambiarEstado(10);
		}
		catch(tiempoDeEstadoInvalidoException e){
			fail("El tiempo de estado es válido y arroja excepción.");
		}
		
		assertTrue(viviente.getEstado() == EstadoViviente.CAZADOR);
		
		for( int i = 10; i > 1; i--){
			viviente.vivir();
			assertTrue(viviente.getEstado() == EstadoViviente.CAZADOR);
		}
		
		viviente.vivir();
		assertTrue(viviente.getEstado() == EstadoViviente.PRESA);
		viviente.vivir();
		assertTrue(viviente.getEstado() == EstadoViviente.PRESA);
	}

	public void testSetPosicionOK() {
		try{
			viviente.setPosicion(new Punto(2,2));
			viviente.setPosicion(new Punto(0,0));
			viviente.setPosicion(new Punto(viviente.getJuego().getTablero().getMaxPosX(),0));
			viviente.setPosicion(new Punto(0,viviente.getJuego().getTablero().getMaxPosY()));
			viviente.setPosicion(new Punto(viviente.getJuego().getTablero().getMaxPosX(),
										   viviente.getJuego().getTablero().getMaxPosY()));
		}
		catch(PosicionInvalidaException e){
			fail("Las posiciones son válidas, y arroja una excepción de posición inválida");
		}
	}
	

	public void testSetPosicionERROR() {
		try{
			viviente.setPosicion(new Punto(-1,0));
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			viviente.setPosicion(new Punto(0,-1));
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			viviente.setPosicion(new Punto(viviente.getJuego().getTablero().getMaxPosX()+1,0));
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			viviente.setPosicion(new Punto(0,viviente.getJuego().getTablero().getMaxPosY()+1));
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
	}

	public void testSetPosicionINT_OK(){
		try{
			viviente.setPosicion(2,2);
			viviente.setPosicion(0,0);
			viviente.setPosicion(viviente.getJuego().getTablero().getMaxPosX(),0);
			viviente.setPosicion(0,viviente.getJuego().getTablero().getMaxPosY());
			viviente.setPosicion(viviente.getJuego().getTablero().getMaxPosX(),
										   viviente.getJuego().getTablero().getMaxPosY());
		}
		catch(PosicionInvalidaException e){
			fail("Las posiciones son válidas, y arroja una excepción de posición inválida");
		}
	}
	
	public void testSetPosicionINT_ERROR(){
		try{
			viviente.setPosicion(-1,0);
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			viviente.setPosicion(0,-1);
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			viviente.setPosicion(viviente.getJuego().getTablero().getMaxPosX()+1,0);
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			viviente.setPosicion(0,viviente.getJuego().getTablero().getMaxPosY()+1);
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
	}
	
	public void testSetEstado() {
		viviente.setEstado(EstadoViviente.CAZADOR);
		assertTrue(viviente.getEstado() == EstadoViviente.CAZADOR);
		
		viviente.setEstado(EstadoViviente.PRESA);
		assertTrue(viviente.getEstado() == EstadoViviente.PRESA);
		
		viviente.setEstado(null);
		assertTrue(viviente.getEstado() == null);
	}

	public void testSetVelocidadOK() {
		try{
			viviente.setVelocidad(2);
			viviente.setVelocidad(0);
			viviente.setVelocidad(5.31);
		}
		catch(VelocidadInvalidaException e){
			fail("Las velocidades son válidas, y arroja una excepción de velocidad inválida");
		}
	}

	public void testSetVelocidadERROR() {
		try{
			viviente.setVelocidad(-1);
			fail("Debería arrojar una exepción: por convención las velocidades sólo pueden ser positivas o nulas.");
		}
		catch(VelocidadInvalidaException e){
			assertTrue(true);
		}
	}
	
	public void testFenecer(){		
		assertTrue(viviente.estaVivo() == true);
		viviente.fenecer();
		assertTrue(viviente.estaVivo() == false);		
	}
	
	public void testSetVivo(){
		viviente.fenecer();
		viviente.setVivo();
		assertTrue(viviente.estaVivo() == true);
	}
	
}