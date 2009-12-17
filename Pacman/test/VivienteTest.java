import Model.*;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.excepciones.tiempoDeEstadoInvalidoException;
import Model.juego.Juego;
import Model.viviente.Pacman;
import Model.viviente.Viviente;
import junit.framework.TestCase;


public class VivienteTest extends TestCase {

	private Viviente viviente;
	private Juego juego;
	private Punto punto;
	
	/* Laberinto XML 4x2:
	 * --
	 * P-
	 * --
	 * --
	 * 
	 * ( - ) == nada; ( P ) == Pared
	 */
	
	protected void setUp() throws Exception {
		super.setUp();
		this.punto = new Punto(1,1);
		this.juego = new Juego("src/Model/laberinto.xml", 4, 2);
		this.viviente = new Pacman(this.punto, this.juego);
		this.viviente.setEstado(null);
		this.juego.getTablero().inicializar();
	}

	// Controla que todo se inicialice correctamente
	public void testVivienteOK() {
		assertTrue(viviente.estaVivo());
		assertTrue(viviente.getVelocidadActual() == 10);
		assertTrue(viviente.getDireccionActual() == Direcciones.IZQUIERDA);
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
				
	}

	public void testIrAIzquierda() {
		
		viviente.setDireccionActual(Direcciones.ARRIBA);
		
		//en (1,0) hay una pared, por lo tanto no debería poder ir hacia la izquierda.
		viviente.irAIzquierda();
		assertEquals(viviente.getPosicion(), new Punto(1,1));
		//su dirección no debería haber cambiado
		assertEquals(viviente.getDireccionActual(), Direcciones.ARRIBA);
		
		//en la línea de x=2 no hay nada
		try{
			viviente.setPosicion(2,1);
		}
		catch(PosicionInvalidaException e){
			e.printStackTrace();
		}
		
		assertEquals(viviente.getPosicion(), new Punto(2,1));
		viviente.irAIzquierda();
		assertEquals(viviente.getPosicion(), new Punto(2,0));
		assertEquals(viviente.getDireccionActual(), Direcciones.IZQUIERDA);
		
		//si su posición es cero no debe poder moverse más a la izquierda
		viviente.irAIzquierda();
		assertEquals(viviente.getPosicion(), new Punto(2,0));
		assertEquals(viviente.getDireccionActual(), Direcciones.IZQUIERDA);
	}
		
	public void testIrADerecha() {
		
		//si está en el límite derecho del tablero, no debe poder moverse más a la derecha
		viviente.irADerecha();
		assertEquals(viviente.getPosicion(), new Punto(1,1));
		//y la dirección debe permanecer igual a cuando se instanció el viviente
		assertEquals(viviente.getDireccionActual(), Direcciones.IZQUIERDA);
		
		
		//El límite en Y del tablero se considera una posición inválida.
		try {
			viviente.setPosicion(1, viviente.getJuego().getTablero().getMaxPosY());
			fail("Debería arrojar excepción, por encontrarse en el límite Y del tablero");
		} 
		catch (PosicionInvalidaException e) {
			assertTrue(true);
		}
		
		//en la línea de X = 2 no hay nada
		try{
			viviente.setPosicion(2,0);
		}
		catch(PosicionInvalidaException e){
			fail("(2,0) es una posición válida en laberinto.xml");
		}
		
		viviente.irADerecha();
		assertEquals(viviente.getPosicion(), new Punto(2,1));
		assertEquals(viviente.getDireccionActual(), Direcciones.DERECHA); 
		
		//No debe poder estar sobre el límite Y = 2.
		viviente.irADerecha();
		assertEquals(viviente.getPosicion(), new Punto(2,1));
		assertEquals(viviente.getDireccionActual(), Direcciones.DERECHA); 
	}

	public void testIrArriba() {
		viviente.irArriba();
		assertEquals(viviente.getPosicion(), new Punto(0,1));
		assertEquals(viviente.getDireccionActual(), Direcciones.ARRIBA); 
		
		//si es cero no debe poder moverse más a hacia arriba
		viviente.irArriba();
		assertEquals(viviente.getPosicion(), new Punto(0,1));
		assertEquals(viviente.getDireccionActual(), Direcciones.ARRIBA); 
	}

	public void testIrAbajo() {
		
		viviente.irAbajo();
		assertEquals(viviente.getPosicion(), new Punto(2,1));
		assertEquals(viviente.getDireccionActual(), Direcciones.ABAJO); 
		
				
		//El límite en Y del tablero se considera una posición inválida.
		try {
			viviente.setPosicion(1, viviente.getJuego().getTablero().getMaxPosY());
			fail("Debería arrojar excepción, por encontrarse en el límite Y del tablero");
		} 
		catch (PosicionInvalidaException e) {
			assertTrue(true);
		}
		
	
		try{
			viviente.setPosicion(2,1);
		}
		catch(PosicionInvalidaException e){
			fail("(3,0) es una posición válida en laberinto.xml");
		}
		
		viviente.irAbajo();
		assertEquals(viviente.getPosicion(), new Punto(3,1));
		assertEquals(viviente.getDireccionActual(), Direcciones.ABAJO); 
		
		//No debe poder estar sobre el límite X = 4.
		viviente.irAbajo();
		assertEquals(viviente.getPosicion(), new Punto(3,1));
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
			viviente.setPosicion(new Punto(1,1));
			viviente.setPosicion(new Punto(0,0));
			viviente.setPosicion(new Punto(viviente.getJuego().getTablero().getMaxPosX()-1,0));
			viviente.setPosicion(new Punto(0,viviente.getJuego().getTablero().getMaxPosY()-1));
			viviente.setPosicion(new Punto(viviente.getJuego().getTablero().getMaxPosX()-1,
										   viviente.getJuego().getTablero().getMaxPosY()-1));
			assertTrue(true);
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
			viviente.setPosicion(1,1);
			viviente.setPosicion(0,0);
			viviente.setPosicion(viviente.getJuego().getTablero().getMaxPosX()-1,0);
			viviente.setPosicion(0,viviente.getJuego().getTablero().getMaxPosY()-1);
			viviente.setPosicion(viviente.getJuego().getTablero().getMaxPosX()-1,
										   viviente.getJuego().getTablero().getMaxPosY()-1);
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
			viviente.setVelocidadActual(2);
			viviente.setVelocidadActual(0);
			viviente.setVelocidadActual(10);
		}
		catch(VelocidadInvalidaException e){
			fail("Las velocidades son válidas, y arroja una excepción de velocidad inválida");
		}
	}

	public void testSetVelocidadERROR() {
		try{
			viviente.setVelocidadActual(-1);
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
