package Model;

public class EstrategiaEscapadora extends EstrategiaUnoAUno {

	@Override
	public Direcciones calcularNuevaDireccion(Punto posicionYo,
			Punto posicionEl, Direcciones dirActual, Tablero tablero) {
		
		return calcularNuevaDireccion(posicionYo, posicionEl, dirActual,
				tablero, EstadoViviente.PRESA);
		
	}

}
