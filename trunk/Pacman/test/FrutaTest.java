import java.io.FileNotFoundException;

import Model.Punto;
import Model.item.Fruta;
import Model.juego.Juego;
import junit.framework.TestCase;


public class FrutaTest extends TestCase {

	private Fruta fruta;
	private Juego juego;
	
	public void testHacerEfectoOK(){
		this.juego = new Juego("src/Model/nivel1.xml", 16, 16);
		try {
			this.juego.getTablero().inicializar();
		} catch (FileNotFoundException e1) {
			fail("Arrojo Excepcion FileNotFound");
		}
		this.fruta = new Fruta(this.juego, 10, 0, new Punto(10,10));
		int puntosAntes = this.juego.getJugador().getPuntos();
		this.fruta.activador();
		this.fruta.hacerEfecto();
		assertEquals(this.juego.getJugador().getPuntos(), puntosAntes+20);	
	}
	
	public void testHacerEfectoErroneo(){
		this.juego = new Juego("src/Model/nivel1.xml", 16, 16);
		try {
			this.juego.getTablero().inicializar();
		} catch (FileNotFoundException e1) {
			fail("Arrojo Excepcion FileNotFound");
		}
		this.fruta = new Fruta(this.juego, 10, 0, new Punto(10,10));
		int puntosAntes = this.juego.getJugador().getPuntos();
		this.fruta.activador();
		this.fruta.hacerEfecto();
		assertFalse(this.juego.getJugador().getPuntos() == puntosAntes);
	}
}
