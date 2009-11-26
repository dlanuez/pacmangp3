package Model;

public class EstrategiaSiempreDobla extends Estrategia {
	
	

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
		
		return null;
	}

}
