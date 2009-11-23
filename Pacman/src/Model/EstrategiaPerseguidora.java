package Model;

import java.util.ArrayList;

public class EstrategiaPerseguidora extends Estrategia {

	@Override
	public Direcciones calcularNuevaDireccion(Punto posicionYo,
			Punto posicionEl, Direcciones dirActual, Tablero tablero) {
		
		ArrayList<Direcciones> dirPosibles = new ArrayList<Direcciones>();
		int deltaX, deltaY;
		Direcciones dirHorizontal, dirVertical;
		
		dirPosibles = direccionesPosibles(posicionYo, tablero);
		if(!puedeDoblar(dirActual, dirPosibles))
			return dirActual;
		deltaX = posicionEl.x() - posicionYo.x();
		deltaY = posicionEl.y() - posicionYo.y();
		dirHorizontal = encontrarDirHorizontal(deltaX);
		dirVertical = encontrarDirVertical(deltaY);
		//En caso de ser posible devuelve la direccion "que apunta"
		//mejor al objetivo. Si no, devuelve la segunda que mejor apunta.
		//Si tampoco se puede devuelve cualquier posicion posible.
		if( ((deltaX*deltaX - deltaY*deltaY) < 0)
				&& dirPosibles.contains(dirVertical) )
			return dirVertical;
		if(dirPosibles.contains(dirHorizontal))
			return dirHorizontal;
		return dirPosibles.get(0);
	}

	private Direcciones encontrarDirVertical(int deltaY) {
		if(deltaY < 0)
			return Direcciones.ARRIBA;
		else
			return Direcciones.ABAJO;
	}

	private Direcciones encontrarDirHorizontal(int deltaX) {
		if(deltaX < 0)
			return Direcciones.IZQUIERDA;
		else
			return Direcciones.DERECHA;
	}

}
