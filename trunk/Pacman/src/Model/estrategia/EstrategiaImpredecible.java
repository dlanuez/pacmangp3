package Model.estrategia;

import java.util.ArrayList;
import java.util.Random;

import Model.Direcciones;
import Model.Punto;
import Model.tablero.Tablero;

public class EstrategiaImpredecible extends Estrategia {

	@Override
	public Direcciones hacerCalcularNuevaDireccion(Punto posicionYo,
			Punto posicionEl, Direcciones dirActual, Tablero tablero) {
		
		ArrayList<Direcciones> dirPosibles;
		Random random = new Random();
		int eleccion;
		
		dirPosibles = direccionesPosibles(posicionYo, tablero);		
		
		if (!puedeDoblar(dirActual, dirPosibles)) return dirActual;
		
		eleccion = random.nextInt(dirPosibles.size());
		return dirPosibles.get(eleccion);
	}

}
