import Model.EstadoViviente;
import Model.Punto;
import Model.juego.Juego;
import Model.viviente.Fantasma;
import junit.framework.TestCase;


public class FantasmaTest extends TestCase {
	private Juego juego;
	private Punto punto;
	private Fantasma fantasma;
	
	protected void setUp() throws Exception {
		super.setUp();		
		this.juego = new Juego("src/Model/nivel1.xml");
		this.juego.getTablero().inicializar();
		this.fantasma = this.juego.getTablero().getFantasma(0);
	}

	public void testVivir() {
		this.punto = new Punto(fantasma.getPosicion().x(), fantasma.getPosicion().y());
		fantasma.vivir();
		//chequeo que el fantasma cambio de posicion
		assertNotSame(this.punto, fantasma.getPosicion());
		
	}

	public void testMover() {
		this.punto = new Punto(fantasma.getPosicion().x(), fantasma.getPosicion().y());
		fantasma.mover();
		assertNotSame(this.punto, fantasma.getPosicion());
	}

	public void testRevivir() {
		fantasma.fenecer();
		assertFalse(fantasma.estaVivo());
		fantasma.revivir(EstadoViviente.CAZADOR);
		assertTrue(fantasma.estaVivo());
	}

}
