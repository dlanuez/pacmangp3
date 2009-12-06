package View;

import Model.item.Fruta;
import Model.item.Item;
import ar.uba.fi.algo3.titiritero.ObjetoVivo;

public class FrutaViva implements ObjetoVivo {

	private Fruta fruta;

	public FrutaViva(Item item){
		this.fruta = (Fruta) item;
	}
	
	@Override
	public void vivir() {
		this.fruta.estaActivo();
		
	}

	
}
