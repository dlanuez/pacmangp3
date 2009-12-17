package Model.juego;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import Model.excepciones.ArchivoInvalidoException;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.cantidadDeVidasInvalidaExeption;
import Model.tablero.Tablero;
import Model.viviente.Fantasma;

public class Juego {

	private final int Vidas_Normal = 3;
	
	private Tablero tablero;
	private Jugador jugador;

	private int nivelActual;
			
	
	public Juego(String tablero, int maxX, int maxY){
		try{
			this.jugador = new Jugador(Vidas_Normal);
		}
		catch(cantidadDeVidasInvalidaExeption e){
			e.printStackTrace();
		}
		this.tablero = new Tablero(tablero, this, maxX, maxY);
		this.nivelActual = 1;
	}
	
	public Juego(String tablero){
		try{
			this.jugador = new Jugador(Vidas_Normal);
		}catch(cantidadDeVidasInvalidaExeption e){
			e.printStackTrace();
		}
		this.tablero = new Tablero(tablero, this, 16, 16);
		this.nivelActual = 1;
	}

	public Tablero getTablero() {
		return this.tablero;
	}

	public Jugador getJugador() {

		return this.jugador;
	}

	public void pacmanComido() {
		this.getTablero().getPacman().fenecer();
		this.getJugador().restarVida();
		try {
			this.getTablero().resetearPosiciones();
		} catch (PosicionInvalidaException e) {
			e.printStackTrace();
		}
	}

	public void fantasmaComido(Fantasma fantasma) {
		this.getJugador().sumarPuntos(fantasma.getPuntosPorEsteFantasmaConCarinioParaCabu());
		fantasma.fenecer();
        try {
        	fantasma.setPosicion(fantasma.getPuntoDeRespawn());
        }
        catch (PosicionInvalidaException e) {
                /* Las posiciones de respawn deberían ser válidas, ya que se leen del archivo
                 * laberinto.xml. Si se llega a esta exepción, lo más probable es que
                 * getPosicionDeRespawn() haya devuelto un valor nulo.
                 */
                e.printStackTrace();
        }
		
	}

	//devuelve true si se puede pasar de nivel. false si el nivel no existe
	public boolean pasarDeNivel() {
		this.nivelActual++;
		String nombreDelSiguienteNivel = "src/Model/nivel"+Integer.toString(this.nivelActual)+".xml";
		
		if(this.existeElNivel(nombreDelSiguienteNivel)){		
			this.tablero = new Tablero(nombreDelSiguienteNivel, this);
			this.tablero.inicializar();
			return true;
		}
		else return false;
	}

	public int getNivel() {	
		return this.nivelActual;
	}
	
	private boolean existeElNivel(String archivo){
		if((archivo == "") || (archivo == null)){			
			throw new ArchivoInvalidoException();
		}
		File fArchivo = new File(archivo);
			
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fArchivo);			
			return true;
		}
		catch(ParserConfigurationException e){
			return false;
		}
		catch(Exception e){
			return false;
		}
	}
	
}
