import java.io.FileNotFoundException;

import junit.framework.TestCase;
import Model.juego.Juego;
import Model.tablero.Casillero;
import Model.tablero.InicializadorTablero;

public class InicializadorTableroTest extends TestCase {
	public void testGenerarTablero(){
		Casillero casilleros[][] = null;
		Juego juego = new Juego("src/Model/nivel1.xml");
		InicializadorTablero inicializador = new InicializadorTablero("src/Model/laberinto.xml", juego, 4, 2);
		try {
			casilleros = inicializador.generarTablero();
		} catch (FileNotFoundException e) {			
			fail("arrojo Excepcion FileNotFound");
		}
		assertEquals(4,casilleros.length);		
		assertEquals("ItemNulo", casilleros[0][0].getItem().getClass().getSimpleName());
		assertEquals("Pastilla", casilleros[0][1].getItem().getClass().getSimpleName());
		assertFalse(casilleros[1][0].casilleroHabilitado());
		assertEquals("Bolita",casilleros[1][1].getItem().getClass().getSimpleName());
		assertEquals("ItemNulo",casilleros[2][0].getItem().getClass().getSimpleName());
		assertEquals("ItemNulo",casilleros[2][1].getItem().getClass().getSimpleName());
		assertEquals("ItemNulo",casilleros[3][0].getItem().getClass().getSimpleName());
		assertEquals("ItemNulo",casilleros[3][1].getItem().getClass().getSimpleName());
	}
}
