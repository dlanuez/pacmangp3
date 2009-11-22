package Model;

public enum EstadoCasillero {
	PARED, PISO;

	public boolean estaHabilitado() {
		if (this.equals(PARED))
			return false;
		//Si no es pared devuelve true.
		return true;
	}
}
