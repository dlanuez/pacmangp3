import Model.EstadoViviente;
import junit.framework.TestCase;


public class EstadoVivienteTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testToggleState() {
		EstadoViviente miEstado;
		
		miEstado = EstadoViviente.PRESA;
		
		assertTrue(miEstado == EstadoViviente.PRESA);
		
		miEstado.toggleState();
		assertEquals(EstadoViviente.CAZADOR, miEstado.toggleState());
		
		
		miEstado = EstadoViviente.CAZADOR;
		
		assertTrue(miEstado == EstadoViviente.CAZADOR);
		
		miEstado.toggleState();
		assertEquals(EstadoViviente.PRESA, miEstado.toggleState());
		
	}
	
	
}
