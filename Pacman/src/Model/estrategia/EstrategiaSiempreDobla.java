package Model.estrategia;

import java.util.ArrayList;

import Model.Direcciones;
import Model.Punto;
import Model.tablero.Tablero;

public class EstrategiaSiempreDobla extends Estrategia {
	
	Direcciones ultimoGiro = Direcciones.DERECHA;
	Direcciones dirDeseada;
	ArrayList<Direcciones> dirPosibles;

	//Gira a 90 grados hacia la derecha o hacia la izquierda contrariamente
	//al ultimo giro realizado. En caso de no poder hacerlo, se mueve a
	//cualquier casillero posible.
	@Override
	public Direcciones hacerCalcularNuevaDireccion(Punto posicionYo,
			Punto posicionEl, Direcciones dirActual, Tablero tablero) {
		
		dirPosibles = direccionesPosibles(posicionYo, tablero);
		if (!puedeDoblar(dirActual, dirPosibles)) return dirActual;
		
		if (ultimoGiro == Direcciones.DERECHA)
			dirDeseada = dirActual.masNoventaAntiHorario();
		else
			dirDeseada = dirActual.menosNoventaAntiHorario();
		
		if (dirPosibles.contains(dirDeseada)){
			ultimoGiro = ultimoGiro.masNoventaAntiHorario().masNoventaAntiHorario();
			return dirDeseada;
		}
		return dirPosibles.get(0);
	}

}
