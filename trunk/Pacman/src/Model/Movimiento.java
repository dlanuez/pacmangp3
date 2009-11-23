package Model;

public enum Movimiento {
	
	ARRIBA,ABAJO,DERECHA,IZQUIERDA;

	public Movimiento masNoventaAntiHorario() {
		
		Movimiento mov = this;
		
		switch (mov){
		case ARRIBA: mov = IZQUIERDA;
		case ABAJO: mov = DERECHA;
		case IZQUIERDA: mov = ABAJO;
		case DERECHA: mov = ARRIBA;
		}
		return mov;
	}
	
	public Movimiento menosNoventaAntiHorario() {
		
		Movimiento mov = this;
		
		switch (mov){
		case ARRIBA: mov = DERECHA;
		case ABAJO: mov = IZQUIERDA;
		case IZQUIERDA: mov = ARRIBA;
		case DERECHA: mov = ABAJO;
		}
		return mov;
	}
	
}
