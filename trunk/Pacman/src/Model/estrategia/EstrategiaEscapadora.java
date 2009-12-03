package Model.estrategia;

import Model.Direcciones;
import Model.EstadoViviente;
import Model.Punto;
import Model.tablero.Tablero;

public class EstrategiaEscapadora extends EstrategiaUnoAUno {

	@Override
	public Direcciones calcularNuevaDireccion(Punto posicionYo,
			Punto posicionEl, Direcciones dirActual, Tablero tablero) {
		
		if (posicionYo == null)
			throw new NullPointerException();
		if (posicionEl == null)
			throw new NullPointerException();
		if (dirActual == null)
			throw new NullPointerException();
		if (tablero == null)
			throw new NullPointerException();
		
		return calcularNuevaDireccion(posicionYo, posicionEl, dirActual,
				tablero, EstadoViviente.PRESA);
		
	}

}
