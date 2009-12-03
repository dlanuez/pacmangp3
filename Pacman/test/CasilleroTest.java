import Model.EstadoCasillero;
import Model.item.Bolita;
import Model.item.ItemNulo;
import Model.juego.Juego;
import Model.tablero.Casillero;
import junit.framework.TestCase;


public class CasilleroTest extends TestCase {
	
	private Casillero casillero;
	private Bolita bolita;
	private EstadoCasillero estadoCasillero;
	private Juego juego;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.juego = new Juego("src/Model/nivel1.xml");
		this.bolita = new Bolita(juego);
		this.estadoCasillero = EstadoCasillero.PISO;
		this.casillero = new Casillero(this.estadoCasillero, this.bolita);		
	}

	public void testCasilleroHabilitadoPisoOK(){
		assertTrue(this.casillero.casilleroHabilitado());
	}
	
	public void testCasilleroHabilitadoParedOK(){
		this.bolita = new Bolita(juego);
		this.estadoCasillero = EstadoCasillero.PARED;
		this.casillero = new Casillero(this.estadoCasillero, this.bolita);
		assertFalse(this.casillero.casilleroHabilitado());
	}
	
	public void testGetItemOK(){
		assertTrue(this.casillero.getItem().getClass() == this.bolita.getClass());
	}
	
	public void testGetItemNuloOK(){
		ItemNulo item = new ItemNulo();
		this.casillero.getItem();
		assertTrue(this.casillero.getItem().getClass() == item.getClass());
	}

}
