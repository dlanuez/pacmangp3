
import Model.item.Bolita;
import Model.juego.Juego;
import junit.framework.TestCase;


public class BolitaTest extends TestCase {

	private Bolita bolita;
	private Juego juego;
	
	public void testHacerEfectoOK(){
		this.juego = new Juego("src/Model/nivel1.xml", 16, 16);
		this.juego.getTablero().inicializar();
		this.bolita = new Bolita(this.juego);
		int puntosAntes = this.juego.getJugador().getPuntos();
		this.bolita.hacerEfecto();
		assertEquals(this.juego.getJugador().getPuntos(), puntosAntes+10);	
	}
	
	public void testHacerEfectoErroneo(){
		this.juego = new Juego("src/Model/nivel1.xml", 16, 16);
		this.juego.getTablero().inicializar();
		this.bolita = new Bolita(this.juego);
		int puntosAntes = this.juego.getJugador().getPuntos();
		this.bolita.hacerEfecto();
		assertFalse(this.juego.getJugador().getPuntos() == puntosAntes);
	}

}
