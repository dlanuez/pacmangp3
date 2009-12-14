package Model.estrategia;

import Model.Direcciones;
import Model.EstadoViviente;
import Model.Punto;
import Model.tablero.Tablero;

public class EstrategiaEscapadora extends EstrategiaUnoAUno {

	@Override
	public Direcciones hacerCalcularNuevaDireccion(Punto posicionYo,
			Punto posicionEl, Direcciones dirActual, Tablero tablero) {
		
		return calcularNuevaDireccion(posicionYo, posicionEl, dirActual,
				tablero, EstadoViviente.PRESA);
		
	}

}
