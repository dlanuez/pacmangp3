package Model.excepciones;

public class VelocidadInvalidaException extends Exception {
	
	@Override
	public String toString() {
		return "Se inicializó el objeto viviente con una velocidad invalida";
	}
	
	private static final long serialVersionUID = 1L;

}
