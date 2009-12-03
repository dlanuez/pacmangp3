import java.util.Iterator;

import Model.*;
import Model.excepciones.PosicionInvalidaException;
import Model.juego.Juego;
import Model.tablero.Tablero;
import Model.viviente.Fantasma;
import Model.viviente.Pacman;
import junit.framework.TestCase;


public class TableroTest extends TestCase {
	private Juego juego;
	private Tablero tablero;
	private Punto punto;

	protected void setUp() throws Exception{
		super.setUp();
		this.juego = new Juego("src/Model/laberinto.xml");
		this.tablero = new Tablero("src/Model/laberinto.xml",juego, 4,2);	
		this.tablero.inicializar();
	}
	//Inicializo el tablero con el laberinto de prueba y chequeo que me cargue todo ok
	public void testInicializarYGetCasillero() {		
		assertEquals("Bolita", this.tablero.getCasillero(new Punto(0,0)).obtenerItem().getClass().getSimpleName());
		assertEquals("Pastilla", this.tablero.getCasillero(new Punto(0,1)).obtenerItem().getClass().getSimpleName());
		assertFalse(this.tablero.getCasillero(new Punto(1,0)).casilleroHabilitado());
		assertEquals("ItemNulo",this.tablero.getCasillero(new Punto(1,1)).obtenerItem().getClass().getSimpleName());
		assertEquals("ItemNulo",this.tablero.getCasillero(new Punto(2,0)).obtenerItem().getClass().getSimpleName());
		assertEquals("ItemNulo",this.tablero.getCasillero(new Punto(2,1)).obtenerItem().getClass().getSimpleName());
		assertEquals("ItemNulo",this.tablero.getCasillero(new Punto(3,0)).obtenerItem().getClass().getSimpleName());
		assertEquals("ItemNulo",this.tablero.getCasillero(new Punto(3,1)).obtenerItem().getClass().getSimpleName());		
	}


	public void testResetearPosiciones() {
		try {
			this.tablero.resetearPosiciones();
		} catch (PosicionInvalidaException e) {
			// TODO Auto-generated catch block
			fail("Tiro excepcion");
		}
		this.punto = new Punto(1,1);
		Pacman pacman = this.tablero.getPacman();
		assertEquals(this.punto,pacman.getPosicion());		
		Fantasma fantasma;
		Iterator<Punto> it = this.tablero.getPosicionesCuevaIterador();
		int i = 0;
		while(it.hasNext()){
			fantasma = this.tablero.getFantasma(i);
			assertEquals(fantasma.getPosicion(), it.next());
			i++;
		}
	}
	
	public void testGetCantidadDeBolitas(){
		assertEquals(2, this.tablero.getCantidadDeBolitas());
	}

	public void testGetPacman() {
		assertEquals("Pacman", this.tablero.getPacman().getClass().getSimpleName());
	}

	public void testGetFantasmasIterador() {
		Iterator<Fantasma> fantasmas = this.tablero.getFantasmasIterador();		
		assertEquals("FantasmaRojo", fantasmas.next().getClass().getSimpleName());
		assertEquals("FantasmaRosa", fantasmas.next().getClass().getSimpleName());
		assertEquals("FantasmaNaranja", fantasmas.next().getClass().getSimpleName());
		assertEquals("FantasmaAzul", fantasmas.next().getClass().getSimpleName());
		assertEquals("FantasmaVerde", fantasmas.next().getClass().getSimpleName());		
	}

	public void testGetFantasma() {
		assertEquals("FantasmaRojo", this.tablero.getFantasma(0).getClass().getSimpleName());
	}
	
	/*
	 *
	 *Este test es para probar si el tablero se inicializa bien con 
	 *el diseño definitivo del laberinto para el Nivel1 
	 *
	*/
	
	public void testTableroConNivel(){
		Tablero otroTablero = new Tablero("src/Model/nivel1.xml", juego, 16,16);
		otroTablero.inicializar();
		
	}
}
