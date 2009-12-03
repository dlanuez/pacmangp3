package Model.tablero;

import Model.EstadoCasillero;
import Model.excepciones.ArchivoInvalidoException;
import Model.excepciones.TipoDeCasilleroInexistenteException;
import Model.item.Bolita;
import Model.item.ItemNulo;
import Model.item.Pastilla;
import Model.juego.Juego;

import java.io.File;
import java.io.IOException;

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
	
	public InicializadorTablero(String archivo, Juego juego, int maxX, int maxY){
		if((archivo == "") || (archivo == null)){			
				throw new ArchivoInvalidoException();
		}
		this.archivo = new File(archivo);
		this.juego = juego;
		casilleros = new Casillero[maxX][maxY];
	}
	
	public Casillero[][] generarTablero(){
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
					casilleros[posX][posY] = getTipoCasillero(itemValor);
				}
			}
		}catch(ParserConfigurationException e){
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return casilleros;
	}

	private Casillero getTipoCasillero(String item) {
		Casillero casillero;
		if(item.equals("1")){
			casillero = new Casillero(EstadoCasillero.PISO, new Bolita(this.juego));
			return casillero;
		}else if(item.equals("2")){
			casillero = new Casillero(EstadoCasillero.PISO, new Pastilla(this.juego,50));
			return casillero;
		}else if(item.equals("P")){
			casillero = new Casillero(EstadoCasillero.PARED, new ItemNulo());
			return casillero;
		}else if(item.equals("0")){
			casillero = new Casillero(EstadoCasillero.PISO, new ItemNulo());
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
}
