package Model;

import java.util.ArrayList;

public abstract class EstrategiaUnoAUno extends Estrategia {
	
	public Direcciones calcularNuevaDireccion(Punto posicionYo, Punto posicionEl,
			Direcciones dirActual, Tablero tablero, EstadoViviente estado) {
		
		ArrayList<Direcciones> dirPosibles = new ArrayList<Direcciones>();
		int deltaX, deltaY;
		Direcciones dirHorizontal, dirVertical;
		
		dirPosibles = direccionesPosibles(posicionYo, tablero);
		if (!puedeDoblar(dirActual, dirPosibles))
			return dirActual;
		deltaX = posicionEl.x() - posicionYo.x();
		deltaY = posicionEl.y() - posicionYo.y();
		dirHorizontal = encontrarDirHorizontal(deltaX, estado);
		dirVertical = encontrarDirVertical(deltaY, estado);
		//En caso de ser posible devuelve la direccion "que apunta"
		//mejor al objetivo. Si no, devuelve la segunda que mejor apunta.
		//Si tampoco se puede devuelve cualquier posicion posible.
		if ( ((deltaX*deltaX - deltaY*deltaY) < 0)
				&& dirPosibles.contains(dirVertical) )
			return dirVertical;
		if (dirPosibles.contains(dirHorizontal))
			return dirHorizontal;
		return dirPosibles.get(0);
	}

	private Direcciones encontrarDirVertical(int deltaY, EstadoViviente estado) {
		
		Direcciones direccion;
		
		//Asigna la direccion para estado CAZADOR. Si es PRESA luego la invierte.
		if (deltaY < 0)
			direccion = Direcciones.ARRIBA;
		else
			direccion = Direcciones.ABAJO;
		
		if (estado == EstadoViviente.PRESA)
			direccion = direccion.masNoventaAntiHorario().masNoventaAntiHorario();
		
		return direccion;
	}

	private Direcciones encontrarDirHorizontal(int deltaX, EstadoViviente estado) {
		
		Direcciones direccion;
		
		//Asigna la direccion para estado CAZADOR. Si es PRESA luego la invierte.
		if (deltaX < 0)
			direccion = Direcciones.IZQUIERDA;
		else
			direccion = Direcciones.DERECHA;
		
		if (estado == EstadoViviente.PRESA)
			direccion = direccion.masNoventaAntiHorario().masNoventaAntiHorario();
		
		return direccion;
	}

}
