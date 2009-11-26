package Model;

import java.util.ArrayList;
import java.util.Iterator;

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
		if (posicion.x() >= 0)
			if(tablero.getCasillero(posicion).casilleroHabilitado())
				dirPosibles.add(Direcciones.ARRIBA);
		
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
	
}
