package Model;

public enum EstadoCasillero {
	PARED, PISO;

	public boolean estaHabilitado() {
		if (this == EstadoCasillero.PARED)
			return false;
		//Si no es pared devuelve true.
		return true;
	}
}
