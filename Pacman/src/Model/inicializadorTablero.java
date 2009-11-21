package Model;

import Model.Bolita;
import Model.Pastilla;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class inicializadorTablero {
	private File archivo;
	private Casillero casilleros[][];
	private Juego juego;
	
	public inicializadorTablero(String archivo, Juego juego){
		this.archivo = new File(archivo);
		this.juego = juego;
		casilleros = new Casillero[2][2];
	}
	
	public Casillero[][] generarTablero(){
		int posX,posY, itemValor;

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
					itemValor = convertirAEntero((Node)item.item(0));					
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

	private Casillero getTipoCasillero(int item) {
		Casillero casillero;
		if(item == 1){
			casillero = new Casillero(EstadoCasillero.PISO);
			casillero.setItem(new Bolita(this.juego, 500));
			return casillero;
		}
		if(item == 2){
			casillero = new Casillero(EstadoCasillero.PISO);
			casillero.setItem(new Pastilla(this.juego, 1000,50));
			return casillero;
		}
		if(item == 0){
			casillero = new Casillero(EstadoCasillero.PARED);
			return casillero;
		}
		return null;
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
