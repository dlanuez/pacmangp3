package Model.estrategia;

import java.util.ArrayList;
import java.util.Iterator;

import Model.Direcciones;
import Model.Punto;
import Model.tablero.Tablero;
import Model.viviente.Fantasma;

public class EstrategiaEmboscadora extends Estrategia {

	@Override
	public Direcciones hacerCalcularNuevaDireccion(Punto posicionYo,
			Punto posicionEl, Direcciones dirActual, Tablero tablero) {

		ArrayList<Direcciones> dirPosibles = direccionesPosibles(posicionYo, tablero);
		Punto nuevaPosicion;
		
		if (!puedeDoblar(dirActual, dirPosibles))
			return dirActual;
		
		Punto posFantasma = posicionFantasmaMasCercanoAPacman(tablero, posicionYo);
		ArrayList<Punto> aEvitar = new ArrayList<Punto>();
		aEvitar.add(posFantasma);
		aEvitar = caminoMasCorto(posicionYo, posicionEl, aEvitar);
		if ((aEvitar == null) || (aEvitar.size() == 1))
				return dirPosibles.get(0);
		nuevaPosicion = aEvitar.get(1);
		return direccion(posicionYo, nuevaPosicion);
	}
	
	
	
	private Punto posicionFantasmaMasCercanoAPacman(Tablero tablero, Punto excluir){
		
		Iterator<Fantasma> i = tablero.getFantasmasIterador();
		double menorDistancia = 99999;
		double distanciaActual;
		Punto posFantasmaActual;
		Punto posFantasmaMasCercano;
		Punto posPacman;

		posFantasmaMasCercano = excluir;
		posPacman = tablero.getPacman().getPosicion();
		while(i.hasNext()){
			posFantasmaActual = i.next().getPosicion();
			if (!posFantasmaActual.equals(excluir)){
				distanciaActual = posFantasmaActual.distanciaAOtroPunto(posPacman);
				if (distanciaActual <= menorDistancia){
					menorDistancia = distanciaActual;
					posFantasmaMasCercano = posFantasmaActual;
				}
			}
		}
		return posFantasmaMasCercano;
	}

}
