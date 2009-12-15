package Model.tablero;

import Model.EstadoCasillero;
import Model.Punto;
import Model.excepciones.ArchivoInvalidoException;
import Model.excepciones.TipoDeCasilleroInexistenteException;
import Model.item.Bolita;
import Model.item.Fruta;
import Model.item.ItemNulo;
import Model.item.Pastilla;
import Model.juego.Juego;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InicializadorTablero {
	private File archivo;
	private Casillero casilleros[][];
	private Juego juego;
	private Punto puntoPacman;
	
	private final int tiempoDeEfectoPastilla = 30;
	
	public InicializadorTablero(String archivo, Juego juego, int maxX, int maxY){
		if((archivo == "") || (archivo == null)){			
				throw new ArchivoInvalidoException();
		}
		this.archivo = new File(archivo);
		this.juego = juego;
		casilleros = new Casillero[maxX][maxY];
	}
	
	public Casillero[][] generarTablero() throws FileNotFoundException{
		int posX,posY;
		String itemValor;

		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();
			NodeList listaDeNodos = doc.getElementsByTagName("casillero");
			for(int i = 0; i < listaDeNodos.getLength(); i++){
				Node nodo = listaDeNodos.item(i);
				if(nodo.getNodeType() == Node.ELEMENT_NODE){
					Element elemento = (Element) nodo; 				
					NodeList posicionX = getXmlTag(elemento,"posicionX");
					NodeList posicionY = getXmlTag(elemento, "posicionY");
					NodeList item = getXmlTag(elemento, "item");
					posX = convertirAEntero((Node)posicionX.item(0)) - 1;
					posY = convertirAEntero((Node)posicionY.item(0)) - 1;	
					itemValor = (String)item.item(0).getNodeValue();					
					casilleros[posX][posY] = getTipoCasillero(itemValor, posX,posY);
				}
			}
		}catch(ParserConfigurationException e){
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return casilleros;
	}

	private Casillero getTipoCasillero(String item, int posX, int posY) {
		Casillero casillero;
		if(item.equals("1")){			
			casillero = new Casillero(EstadoCasillero.PISO, new Bolita(this.juego));
			return casillero;
		}else if(item.equals("2")){
			casillero = new Casillero(EstadoCasillero.PISO, new Pastilla(this.juego,this.tiempoDeEfectoPastilla));
			return casillero;
		}else if(item.equals("P")){
			casillero = new Casillero(EstadoCasillero.PARED, new ItemNulo());
			return casillero;
		}else if(item.equals("0")){
			casillero = new Casillero(EstadoCasillero.PISO, new ItemNulo());
			return casillero;
		}else if(item.equals("O")){
			casillero = new Casillero(EstadoCasillero.PISO, new ItemNulo());
			setPuntoPacman(new Punto(posX, posY));
			return casillero;
		}else if(item.equals("3")){
			casillero = new Casillero(EstadoCasillero.PISO, new Fruta(this.juego, 10, 20, new Punto(posX, posY)));
			return casillero;
		}else{
			throw new TipoDeCasilleroInexistenteException();
		}
	}

	private int convertirAEntero(Node nodo) {	
		return Integer.parseInt(nodo.getNodeValue());
	}
	
	private NodeList getXmlTag(Element elemento, String tag){
		NodeList elementoLista = elemento.getElementsByTagName(tag);
		Element elementoNodo = (Element) elementoLista.item(0);
		return elementoNodo.getChildNodes();
	}

	public void setPuntoPacman(Punto puntoPacman) {
		this.puntoPacman = puntoPacman;
	}

	public Punto getPuntoPacman() {
		return puntoPacman;
	}
}
