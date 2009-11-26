import Model.*;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.excepciones.tiempoDeEstadoInvalidoException;
import junit.framework.TestCase;


public class PacmanTest extends TestCase {

	private Pacman pacman;
	private Juego juego;
	private Punto punto;
			
	protected void setUp() throws Exception {
		super.setUp();
		this.punto = new Punto(1,1);
		this.juego = new Juego();
		this.juego.getTablero().inicializar();
		this.pacman = new Pacman(this.punto, this.juego);
		this.pacman.setEstado(null);
		this.juego.getTablero().inicializar();
	}

	// Controla que todo se inicialice correctamente
	public void testVivienteOK() {
		assertTrue(pacman.estaVivo());
		assertTrue(pacman.getVelocidad() == 1);
		assertTrue(pacman.getDireccionActual() == null);
		assertTrue(pacman.getJuego() == this.juego);
		assertTrue(pacman.getPosicion() == this.punto);
	}
	
	// Controla que si se inicializa un objeto con una posición inválida, se lance una exepción
	public void testVivienteERROR() {		
		try{
			pacman = new Pacman(new Punto(-1,0), null);
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		catch(VelocidadInvalidaException e){
			fail("La velocidad con la que se inicializa el Pacman debería ser válida.");
		}
				
	}

	public void testIrAIzquierda() {
		pacman.irAIzquierda();
		assertEquals(pacman.getPosicion(), new Punto(1,0));
		assertEquals(pacman.getDireccionActual(), Direcciones.IZQUIERDA);
		//si es cero no debe poder moverse más a la izquierda
		pacman.irAIzquierda();
		assertEquals(pacman.getPosicion(), new Punto(1,0));
		assertEquals(pacman.getDireccionActual(), Direcciones.IZQUIERDA);
	}
		
	public void testIrADerecha() {
		pacman.irADerecha();
		assertEquals(pacman.getPosicion(), new Punto(1,2));
		assertEquals(pacman.getDireccionActual(), Direcciones.DERECHA);
		
		//si está en el límite derecho del tablero, no debe poder moverse más a la derecha
		try {
			pacman.setPosicion(1, pacman.getJuego().getTablero().getMaxPosY());
			//la última dirección debe seguir almacenada.
			assertEquals(pacman.getDireccionActual(), Direcciones.DERECHA); 
		} 
		catch (PosicionInvalidaException e) {
			fail("El límite en X del tablero se considera una posición válida.");
		}
		
		pacman.irADerecha();
		assertEquals(pacman.getPosicion(), new Punto(1,pacman.getJuego().getTablero().getMaxPosY()));
		assertEquals(pacman.getDireccionActual(), Direcciones.DERECHA); 
	}

	public void testIrArriba() {
		pacman.irArriba();
		assertEquals(pacman.getPosicion(), new Punto(0,1));
		assertEquals(pacman.getDireccionActual(), Direcciones.ARRIBA); 
		//si es cero no debe poder moverse más a hacia arriba
		pacman.irArriba();
		assertEquals(pacman.getPosicion(), new Punto(0,1));
		assertEquals(pacman.getDireccionActual(), Direcciones.ARRIBA); 
	}

	public void testIrAbajo() {
		pacman.irAbajo();
		assertEquals(pacman.getPosicion(), new Punto(2,1));
		assertEquals(pacman.getDireccionActual(), Direcciones.ABAJO); 
		
		//si está en el límite inferior del tablero, no debe poder moverse más hacia abajo
		try {
			pacman.setPosicion(pacman.getJuego().getTablero().getMaxPosX(), 1);
			//la última dirección debe seguir almacenada.
			assertEquals(pacman.getDireccionActual(), Direcciones.ABAJO); 
		} 
		catch (PosicionInvalidaException e) {
			fail("El límite en X del tablero se considera una posición válida.");
		}
		
		pacman.irAbajo();
		assertEquals(pacman.getPosicion(), new Punto(pacman.getJuego().getTablero().getMaxPosX(), 1));
		assertEquals(pacman.getDireccionActual(), Direcciones.ABAJO); 
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
		pacman.setEstado(EstadoViviente.PRESA);
		
		try{
			pacman.cambiarEstado(0);
			fail("El tiempo de estado no es válido, y no arroja excepción.");
		}
		catch(tiempoDeEstadoInvalidoException e){
			assertTrue(true);
		}
		try{
			pacman.cambiarEstado(-1);
			fail("El tiempo de estado no es válido, y no arroja excepción.");
		}
		catch(tiempoDeEstadoInvalidoException e){
			assertTrue(true);
		}
		
	}
	
	public void testCambiarEstadoOK(){
		pacman.setEstado(EstadoViviente.PRESA);
		
		try{
			pacman.cambiarEstado(10);
		}
		catch(tiempoDeEstadoInvalidoException e){
			fail("El tiempo de estado es válido y arroja excepción.");
		}
		
		assertTrue(pacman.getEstado() == EstadoViviente.CAZADOR);
		
		for( int i = 10; i > 1; i--){
			pacman.vivir();
			assertTrue(pacman.getEstado() == EstadoViviente.CAZADOR);
		}
		
		pacman.vivir();
		assertTrue(pacman.getEstado() == EstadoViviente.PRESA);
		pacman.vivir();
		assertTrue(pacman.getEstado() == EstadoViviente.PRESA);
	}

	public void testSetPosicionOK() {
		try{
			pacman.setPosicion(new Punto(2,2));
			pacman.setPosicion(new Punto(0,0));
			pacman.setPosicion(new Punto(pacman.getJuego().getTablero().getMaxPosX(),0));
			pacman.setPosicion(new Punto(0,pacman.getJuego().getTablero().getMaxPosY()));
			pacman.setPosicion(new Punto(pacman.getJuego().getTablero().getMaxPosX(),
										   pacman.getJuego().getTablero().getMaxPosY()));
		}
		catch(PosicionInvalidaException e){
			fail("Las posiciones son válidas, y arroja una excepción de posición inválida");
		}
	}
	

	public void testSetPosicionERROR() {
		try{
			pacman.setPosicion(new Punto(-1,0));
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			pacman.setPosicion(new Punto(0,-1));
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			pacman.setPosicion(new Punto(pacman.getJuego().getTablero().getMaxPosX()+1,0));
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			pacman.setPosicion(new Punto(0,pacman.getJuego().getTablero().getMaxPosY()+1));
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
	}

	public void testSetPosicionINT_OK(){
		try{
			pacman.setPosicion(2,2);
			pacman.setPosicion(0,0);
			pacman.setPosicion(pacman.getJuego().getTablero().getMaxPosX(),0);
			pacman.setPosicion(0,pacman.getJuego().getTablero().getMaxPosY());
			pacman.setPosicion(pacman.getJuego().getTablero().getMaxPosX(),
										   pacman.getJuego().getTablero().getMaxPosY());
		}
		catch(PosicionInvalidaException e){
			fail("Las posiciones son válidas, y arroja una excepción de posición inválida");
		}
	}
	
	public void testSetPosicionINT_ERROR(){
		try{
			pacman.setPosicion(-1,0);
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			pacman.setPosicion(0,-1);
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			pacman.setPosicion(pacman.getJuego().getTablero().getMaxPosX()+1,0);
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
		try{
			pacman.setPosicion(0,pacman.getJuego().getTablero().getMaxPosY()+1);
			fail("La posición es inválida, debería arrojar una excepción.");
		}
		catch(PosicionInvalidaException e){
			assertTrue(true);
		}
	}
	
	public void testSetEstado() {
		pacman.setEstado(EstadoViviente.CAZADOR);
		assertTrue(pacman.getEstado() == EstadoViviente.CAZADOR);
		
		pacman.setEstado(EstadoViviente.PRESA);
		assertTrue(pacman.getEstado() == EstadoViviente.PRESA);
		
		pacman.setEstado(null);
		assertTrue(pacman.getEstado() == null);
	}

	public void testSetVelocidadOK() {
		try{
			pacman.setVelocidad(2);
			pacman.setVelocidad(0);
			pacman.setVelocidad(5.31);
		}
		catch(VelocidadInvalidaException e){
			fail("Las velocidades son válidas, y arroja una excepción de velocidad inválida");
		}
	}

	public void testSetVelocidadERROR() {
		try{
			pacman.setVelocidad(-1);
			fail("Debería arrojar una exepción: por convención las velocidades sólo pueden ser positivas o nulas.");
		}
		catch(VelocidadInvalidaException e){
			assertTrue(true);
		}
	}
	
	public void testFenecer(){		
		assertTrue(pacman.estaVivo() == true);
		pacman.fenecer();
		assertTrue(pacman.estaVivo() == false);		
	}
	
	public void testSetVivo(){
		pacman.fenecer();
		pacman.setVivo();
		assertTrue(pacman.estaVivo() == true);
	}
	
}
