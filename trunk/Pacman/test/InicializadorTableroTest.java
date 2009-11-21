import junit.framework.TestCase;
import Model.Casillero;
import Model.Juego;
import Model.inicializadorTablero;

public class InicializadorTableroTest extends TestCase {
	public void testGenerarTablero(){
		Casillero casilleros[][];
		Juego juego = new Juego();
		inicializadorTablero inicializador = new inicializadorTablero("src/Model/laberinto.xml", juego);
		casilleros = inicializador.generarTablero();
		assertEquals(2,casilleros.length);		
		assertEquals("Bolita", casilleros[0][0].getItem().getClass().getSimpleName());
		assertEquals("Bolita", casilleros[0][1].getItem().getClass().getSimpleName());
		assertEquals("Bolita", casilleros[1][0].getItem().getClass().getSimpleName());
		assertEquals("Pastilla", casilleros[1][1].getItem().getClass().getSimpleName());
	}
}
