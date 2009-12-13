package conversor;

public class Programa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Conversor conversor = new Conversor("src/nivel2.txt");
		conversor.convertirTxtAXml();

	}

}
