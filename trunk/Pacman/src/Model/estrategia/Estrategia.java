package Model.estrategia;

import java.util.ArrayList;
import java.util.Iterator;

import Model.Direcciones;
import Model.Punto;
import Model.tablero.Tablero;

public abstract class Estrategia {
	
	//Devuelve una direccion de movimiento hacia el proximo casillero.
	//La direccion es hacia un casillero cuya validez ya esta chequeada.
	public abstract Direcciones calcularNuevaDireccion(Punto posicionYo,
			Punto posicionEl, Direcciones dirActual, Tablero tablero);
	
	protected ArrayList<Direcciones> direccionesPosibles(Punto posicionYo, Tablero tablero){
		
		ArrayList<Direcciones> dirPosibles = new ArrayList<Direcciones>();
		
		//Agregar a dirPosibles las direcciones que correspondan
		probarDerecha(posicionYo, tablero, dirPosibles);
		probarIzquierda(posicionYo, tablero, dirPosibles);
		probarAbajo(posicionYo, tablero, dirPosibles);
		probarArriba(posicionYo, tablero, dirPosibles);
		return dirPosibles;
		
	}
	
	protected boolean puedeDoblar(Direcciones dirActual,
			ArrayList<Direcciones> dirPosibles){
		
		boolean puedeDoblar = false;
		Direcciones unaPosibilidad;
		Iterator<Direcciones> i = dirPosibles.iterator();
		
		while (i.hasNext()){
			unaPosibilidad = i.next();
			if ( (unaPosibilidad == dirActual.menosNoventaAntiHorario())
				|| (unaPosibilidad == dirActual.masNoventaAntiHorario()) )
				puedeDoblar = true;
		}
		return puedeDoblar;
		
	}

	private void probarArriba(Punto posicionYo, Tablero tablero,
			ArrayList<Direcciones> dirPosibles) {
		
		Punto posicion;
		
		posicion = posicionYo;
		posicion.disminuirX();
		
		if (posicion.x() >= 0){
			if(tablero.getCasillero(posicion).casilleroHabilitado()){
				dirPosibles.add(Direcciones.ARRIBA);
				//System.out.print(posicion.toString());
				//System.out.println(" habilitado");
			}
		}
		else{
			//System.out.print(posicion.toString());
			//System.out.println(" NO habilitado");
		}
			
		
	}

	private void probarAbajo(Punto posicionYo, Tablero tablero,
			ArrayList<Direcciones> dirPosibles) {
		
		Punto posicion;
		
		posicion = posicionYo;
		posicion.aumentarX();
		if (posicion.x() < tablero.getMaxPosX())
			if(tablero.getCasillero(posicion).casilleroHabilitado())
				dirPosibles.add(Direcciones.ABAJO);
		
	}

	private void probarIzquierda(Punto posicionYo, Tablero tablero,
			ArrayList<Direcciones> dirPosibles) {
		
		Punto posicion;
		
		posicion = posicionYo;
		posicion.disminuirY();
		if (posicion.y() >= 0)
			if(tablero.getCasillero(posicion).casilleroHabilitado())
				dirPosibles.add(Direcciones.IZQUIERDA);
		
	}

	private void probarDerecha(Punto posicionYo, Tablero tablero,
			ArrayList<Direcciones> dirPosibles) {
		
		Punto posicion;
		
		posicion = posicionYo;
		posicion.aumentarY();
		if (posicion.y() < tablero.getMaxPosY())
			if(tablero.getCasillero(posicion).casilleroHabilitado())
				dirPosibles.add(Direcciones.DERECHA);
		
	}
	
	protected ArrayList<Punto> caminoMasCorto(Punto inicio, Punto fin, ArrayList<Punto> aEvitar){
		
		ArrayList<Punto> recorridoArriba;
		ArrayList<Punto> recorridoAbajo;
		ArrayList<Punto> recorridoIzquierda;
		ArrayList<Punto> recorridoDerecha;
		Punto proxPunto;
		ArrayList<ArrayList<Punto>> caminos;
		int mejorCamino;
		
		if (inicio.equals(fin))
			return aEvitar;
		
		caminos = new ArrayList<ArrayList<Punto>>();
		
		proxPunto = inicio;
		proxPunto.disminuirX();
		recorridoArriba = aEvitar;
		recorridoArriba.add(proxPunto);
		recorridoArriba = caminoMasCorto(proxPunto, fin, recorridoArriba);
		caminos.add(recorridoArriba);
		
		proxPunto = inicio;
		proxPunto.aumentarX();
		recorridoAbajo = aEvitar;
		recorridoAbajo.add(proxPunto);
		recorridoAbajo = caminoMasCorto(proxPunto, fin, recorridoAbajo);
		caminos.add(recorridoAbajo);
		
		proxPunto = inicio;
		proxPunto.disminuirY();
		recorridoIzquierda = aEvitar;
		recorridoIzquierda.add(proxPunto);
		recorridoIzquierda = caminoMasCorto(proxPunto, fin, recorridoIzquierda);
		caminos.add(recorridoIzquierda);
		
		proxPunto = inicio;
		proxPunto.aumentarY();
		recorridoDerecha = aEvitar;
		recorridoDerecha.add(proxPunto);
		recorridoDerecha = caminoMasCorto(proxPunto, fin, recorridoDerecha);
		caminos.add(recorridoDerecha);
		
		mejorCamino = compararCaminos(caminos);
		if (mejorCamino == -1) return null;
		return caminos.get(mejorCamino);
	}
	
	private int compararCaminos(ArrayList<ArrayList<Punto>> caminos){
		
		int n = 0;
		int mejorCamino = -1;
		int largo = 999999; 
		Iterator<ArrayList<Punto>> i = caminos.iterator();
		ArrayList<Punto> camino;
		
		while (i.hasNext()){
			camino = i.next();
			if (camino != null){
				if (camino.size() <= largo){
					largo = camino.size();
					mejorCamino = n;
				}
			}
			n++;
		}
		return mejorCamino;
		
	}
	
}
