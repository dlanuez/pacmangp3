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
	private Casillero[][] casilleros;
	public inicializadorTablero(String archivo){
		this.archivo = new File(archivo);
	}
	
	public Casillero[][] generarTablero(){
		int posX,posY, itemValor;
		Punto punto;
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			NodeList listaDeNodos = doc.getElementsByTagName("casillero");
			for(int i = 0; i < listaDeNodos.getLength(); i++){
				Node nodo = listaDeNodos.item(i);
				if(nodo.getNodeType() == Node.ELEMENT_NODE){
					Element elemento = (Element) nodo; 				
					NodeList posicionX = getXmlTag(elemento,"posicionX");
					NodeList posicionY = getXmlTag(elemento, "posicionY");
					NodeList item = getXmlTag(elemento, "item");
					posX = convertirAEntero(posicionX);
					posY = convertirAEntero(posicionY);	
					itemValor = convertirAEntero(item);
					punto = new Punto(posX,posY);
					casilleros[punto.x()][punto.y()] = getTipoCasillero(itemValor);
				}
			}
		}catch(ParserConfigurationException e){
			
		}catch(IOException e){
			
		}catch(Exception e){
			
		}
		return casilleros;
	}

	private Casillero getTipoCasillero(int item) {
		// TODO Auto-generated method stub
		Casillero casillero;
		if(item == 1){
			casillero = new Casillero(EstadoCasillero.PISO);
			casillero.setItem(new Bolita());
			return casillero;
		}
		if(item == 2){
			casillero = new Casillero(EstadoCasillero.PISO);
			casillero.setItem(new Pastilla());
			return casillero;
		}
		if(item == 0){
			casillero = new Casillero(EstadoCasillero.PARED);
			return casillero;
		}
		return null;
	}

	private int convertirAEntero(NodeList nodo) {
		return Integer.parseInt(nodo.item(0).getNodeValue());
	}
	
	private NodeList getXmlTag(Element elemento, String tag){
		NodeList elementoLista = elemento.getElementsByTagName(tag);
		Element elementoNodo = (Element) elementoLista.item(0);
		return elementoNodo.getChildNodes();
	}
}
