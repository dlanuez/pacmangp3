package Controller;

import ar.uba.fi.algo3.titiritero.ObjetoVivo;
import Model.item.Fruta;

public class FrutaViva implements ObjetoVivo {

	private Fruta fruta;

	public FrutaViva(Fruta fruta) {
		this.fruta = fruta;
	}

	public void vivir() {
		this.fruta.activador();
	}

}
