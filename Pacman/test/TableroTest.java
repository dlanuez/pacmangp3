import java.util.ArrayList;
import java.util.Iterator;

import Model.*;
import Model.excepciones.PosicionInvalidaException;
import junit.framework.TestCase;


public class TableroTest extends TestCase {
	private Juego juego;
	private Tablero tablero;
	private Punto punto;

	protected void setUp() throws Exception{
		super.setUp();
		this.juego = new Juego();
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
		this.punto = new Punto(2,2);
		Pacman pacman = this.tablero.getPacman();
		assertEquals(this.punto,pacman.getPosicion());
		ArrayList<Punto> listaDePuntos = this.tablero.getPosicionesCueva();
		Fantasma fantasma;
		Iterator<Punto> it = listaDePuntos.iterator();
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

	public void testGetFantasmasArray() {
		Fantasma fantasma[] = this.tablero.getFantasmasArray();
		assertEquals(5, fantasma.length);
		assertEquals("FantasmaRojo", fantasma[0].getClass().getSimpleName());
		assertEquals("FantasmaRosa", fantasma[1].getClass().getSimpleName());
		assertEquals("FantasmaNaranja", fantasma[2].getClass().getSimpleName());
		assertEquals("FantasmaAzul", fantasma[3].getClass().getSimpleName());
		assertEquals("FantasmaVerde", fantasma[4].getClass().getSimpleName());		
	}

	public void testGetFantasma() {
		assertEquals("FantasmaRojo", this.tablero.getFantasma(0).getClass().getSimpleName());
	}

}
