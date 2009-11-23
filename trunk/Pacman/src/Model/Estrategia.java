package Model;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Estrategia {
	
	//Devuelve una direccion de movimiento hacia el proximo casillero.
	//La direccion es hacia un casillero cuya validez ya esta chequeada.
	public abstract Movimiento calcularNuevoMovimiento(Punto posicionYo,
			Punto posicionEl, Movimiento movActual, Tablero tablero);
	
	protected ArrayList<Movimiento> movimientosPosibles(Punto posicionYo, Tablero tablero){
		
		ArrayList<Movimiento> movPosibles = new ArrayList<Movimiento>();
		Punto posicion;
		
		//Agregar a movPosibles los movimientos que correspondan
		probarDerecha(posicionYo, tablero, movPosibles);
		probarIzquierda(posicionYo, tablero, movPosibles);
		probarAbajo(posicionYo, tablero, movPosibles);
		probarArriba(posicionYo, tablero, movPosibles);
		return movPosibles;
		
	}
	
	protected boolean puedeDoblar(Movimiento movActual,
			ArrayList<Movimiento> movPosibles){
		
		boolean puedeDoblar = false;
		Movimiento unaPosibilidad;
		Iterator<Movimiento> i = movPosibles.iterator();
		
		while (i.hasNext()){
			unaPosibilidad = i.next();
			if ( (unaPosibilidad == movActual.menosNoventaAntiHorario())
				|| (unaPosibilidad == movActual.masNoventaAntiHorario()) )
				puedeDoblar = true;
		}
		return puedeDoblar;
		
	}

	private void probarArriba(Punto posicionYo, Tablero tablero,
			ArrayList<Movimiento> movPosibles) {
		Punto posicion;
		posicion = posicionYo;
		posicion.disminuirY();
		if (posicion.x() >= 0)
			if(tablero.getCasillero(posicion).casilleroHabilitado())
				movPosibles.add(Movimiento.ARRIBA);
	}

	private void probarAbajo(Punto posicionYo, Tablero tablero,
			ArrayList<Movimiento> movPosibles) {
		Punto posicion;
		posicion = posicionYo;
		posicion.aumentarY();
		if (posicion.x() <= tablero.getMaxPosY())
			if(tablero.getCasillero(posicion).casilleroHabilitado())
				movPosibles.add(Movimiento.ABAJO);
	}

	private void probarIzquierda(Punto posicionYo, Tablero tablero,
			ArrayList<Movimiento> movPosibles) {
		Punto posicion;
		posicion = posicionYo;
		posicion.disminuirX();
		if (posicion.x() >= 0)
			if(tablero.getCasillero(posicion).casilleroHabilitado())
				movPosibles.add(Movimiento.IZQUIERDA);
	}

	private void probarDerecha(Punto posicionYo, Tablero tablero,
			ArrayList<Movimiento> movPosibles) {
		Punto posicion;
		posicion = posicionYo;
		posicion.aumentarX();
		if (posicion.x() <= tablero.getMaxPosX())
			if(tablero.getCasillero(posicion).casilleroHabilitado())
				movPosibles.add(Movimiento.DERECHA);
	}
	
}
