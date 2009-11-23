package Model;

public enum Direcciones {

	ARRIBA,ABAJO,DERECHA,IZQUIERDA;

	public Direcciones masNoventaAntiHorario() {
		
		Direcciones dir = this;
		
		switch (dir){
		case ARRIBA: dir = IZQUIERDA;
		case ABAJO: dir = DERECHA;
		case IZQUIERDA: dir = ABAJO;
		case DERECHA: dir = ARRIBA;
		}
		return dir;
	}
	
	public Direcciones menosNoventaAntiHorario() {
		
		Direcciones dir = this;
		
		switch (dir){
		case ARRIBA: dir = DERECHA;
		case ABAJO: dir = IZQUIERDA;
		case IZQUIERDA: dir = ARRIBA;
		case DERECHA: dir = ABAJO;
		}
		return dir;
	}
	
}
