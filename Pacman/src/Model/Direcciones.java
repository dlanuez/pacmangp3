package Model;

public enum Direcciones {

	ARRIBA,ABAJO,DERECHA,IZQUIERDA;

	public Direcciones masNoventaAntiHorario() {
		
		Direcciones dir = this;
		
		switch (dir){
			case ARRIBA:
				dir = IZQUIERDA;
				break;
			case ABAJO: 
				dir = DERECHA;
				break;
			case IZQUIERDA:
				dir = ABAJO;
				break;
			case DERECHA: 
				dir = ARRIBA;
				break;
		}
		return dir;
	}
	
	public Direcciones menosNoventaAntiHorario() {
		
		Direcciones dir = this;
		
		switch (dir){
			case ARRIBA: 
				dir = DERECHA;
				break;
			case ABAJO: 
				dir = IZQUIERDA;
				break;
			case IZQUIERDA:
				dir = ARRIBA;
				break;
			case DERECHA:
				dir = ABAJO;
				break;
		}
		return dir;
	}
	
	public String toString(){
		
		Direcciones dir = this;
		
		switch (dir){
			case ARRIBA: 
				return "ARRIBA";
			case ABAJO: 
				return "ABAJO";
			case IZQUIERDA:
				return "IZQUIERDA";
			case DERECHA:
				return "DERECHA";
		}
		
		return "No fucking idea!";
	}
	
}
