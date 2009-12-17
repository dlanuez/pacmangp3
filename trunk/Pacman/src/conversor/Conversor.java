package conversor;
import java.io.*;


public class Conversor {
	private File archivo, archivoEscritura;
	private BufferedWriter output;
	private int numeroDeLinea;
	public Conversor(String archivo){
		this.archivo = new File(archivo);
		int indice = archivo.indexOf(".");
		String archivoEscritura = archivo.substring(0,indice);
		this.archivoEscritura = new File(archivoEscritura+".xml");
	}
	
	/* Esta clase se usa para pasar los niveles hechos en txt a xml.
	 * (Los archivos txt deben seguir los estandares definidos en el informe!)
	 */
	public void convertirTxtAXml(){
		try {
			FileInputStream archivo = new FileInputStream(this.archivo);
			//FileOutputStream archivoEscritura = new FileOutputStream(this.archivoEscritura);
			this.output = new BufferedWriter(new FileWriter(this.archivoEscritura));
			InputStreamReader lector = new InputStreamReader(archivo);
			BufferedReader lectorConBuffer = new BufferedReader(lector);
			String linea = null;
			numeroDeLinea = 0;
			escribirLinea("<tablero>");
			while((linea=lectorConBuffer.readLine()) != null){
				numeroDeLinea++;
				crearXml(linea);
			}
			escribirLinea("</tablero>");
			this.output.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	private void crearXml(String linea) {
		for(int i = 0; i < linea.length(); i++){
			String cadenaXml = "\t<casillero>";
			escribirLinea(cadenaXml);
			cadenaXml = "\t\t<posicionX>" + numeroDeLinea + "</posicionX>";
			escribirLinea(cadenaXml);
			cadenaXml = "\t\t<posicionY>"+ (i + 1) + "</posicionY>";
			escribirLinea(cadenaXml);
			cadenaXml = "\t\t<item>" + linea.charAt(i) + "</item>";
			escribirLinea(cadenaXml);
			cadenaXml = "\t</casillero>";
			escribirLinea(cadenaXml);
		}
		
	}

	private void escribirLinea(String cadenaXml) {
		try {
			this.output.write(cadenaXml);
			this.output.newLine();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
}
