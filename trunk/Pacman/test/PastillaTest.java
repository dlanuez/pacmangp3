

import Model.excepciones.tiempoDeEstadoInvalidoException;
import Model.item.Pastilla;
import Model.juego.Juego;
import junit.framework.TestCase;


public class PastillaTest extends TestCase {

	private Pastilla pastilla;
	private Juego juego;
	
	public void testHacerEfectoOK(){
		this.juego = new Juego("src/Model/nivel1.xml", 16, 16);
		this.juego.getTablero().inicializar();
		this.pastilla = new Pastilla(this.juego, 10);
		try{
			this.pastilla.hacerEfecto();
		}
		catch (tiempoDeEstadoInvalidoException e){
			fail("Arrojo error de tiempo invalido (negativo), con tiempo valido");
		}	
	}
	
	public void testHacerEfectoErroneo(){
		this.juego = new Juego("src/Model/nivel1.xml", 16, 16);
		this.juego.getTablero().inicializar();
		this.pastilla = new Pastilla(this.juego, -5);
		try{
			this.pastilla.hacerEfecto();
			fail("Permitio hacer efecto con tiempo invalido (negativo)");
		}
		catch (tiempoDeEstadoInvalidoException e){
			assertTrue(true);
		}	
	}

}
