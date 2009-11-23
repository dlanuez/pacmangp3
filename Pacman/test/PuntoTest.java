import Model.Punto;
import java.lang.Math;
import junit.framework.TestCase;


public class PuntoTest extends TestCase {

	private Punto punto;
	
	protected void setUp() throws Exception {
		super.setUp();
		punto = new Punto(0,0);
	}

	public void testPunto() {
		assertTrue(punto.x() == 0);
		assertTrue(punto.y() == 0);
	}

	public void testX() {
		assertTrue(punto.x() == 0);
	}

	public void testXInt() {
		punto.x(1);
		assertTrue(punto.x() == 1);
		punto.x(-1);
		assertTrue(punto.x() == -1);
	}
	
	public void testY() {
		assertTrue(punto.y() == 0);
	}

	public void testYInt() {
		punto.y(1);
		assertTrue(punto.y() == 1);
		punto.y(-1);
		assertTrue(punto.y() == -1);
	}

	public void testAumentarX() {
		punto.aumentarX();
		assertTrue(punto.x() == 1);
		assertTrue(punto.y() == 0);
	}

	public void testDisminuirX() {
		punto.disminuirX();
		assertTrue(punto.x() == -1);
		assertTrue(punto.y() == 0);
	}

	public void testAumentarY() {
		punto.aumentarY();
		assertTrue(punto.x() == 0);
		assertTrue(punto.y() == 1);
	}

	public void testDisminuirY() {
		punto.disminuirY();
		assertTrue(punto.x() == 0);
		assertTrue(punto.y() == -1);
	}

	public void testDistanciaAOtroPunto() {
		Punto otroPunto = new Punto(10,10);
		assertEquals(Math.sqrt(200), punto.distanciaAOtroPunto(otroPunto));
	}

}
