import junit.framework.TestCase;
import Model.Casillero;
import Model.Juego;
import Model.InicializadorTablero;

public class InicializadorTableroTest extends TestCase {
	public void testGenerarTablero(){
		Casillero casilleros[][];
		Juego juego = new Juego();
		InicializadorTablero inicializador = new InicializadorTablero("src/Model/laberinto.xml", juego, 4, 2);
		casilleros = inicializador.generarTablero();
		assertEquals(4,casilleros.length);		
		assertEquals("Bolita", casilleros[0][0].getItem().getClass().getSimpleName());
		assertEquals("Pastilla", casilleros[0][1].getItem().getClass().getSimpleName());
		assertFalse(casilleros[1][0].casilleroHabilitado());
		assertEquals("ItemNulo",casilleros[1][1].getItem().getClass().getSimpleName());
		assertEquals("ItemNulo",casilleros[2][0].getItem().getClass().getSimpleName());
		assertEquals("ItemNulo",casilleros[2][1].getItem().getClass().getSimpleName());
		assertEquals("ItemNulo",casilleros[3][0].getItem().getClass().getSimpleName());
		assertEquals("ItemNulo",casilleros[3][1].getItem().getClass().getSimpleName());
	}
}
