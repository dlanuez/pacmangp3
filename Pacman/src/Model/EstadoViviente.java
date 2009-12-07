package Model;

public enum EstadoViviente {	
	CAZADOR, PRESA;	
	
	public EstadoViviente toggleState(){
		if(this == EstadoViviente.CAZADOR)
			return EstadoViviente.PRESA;
		else return EstadoViviente.CAZADOR;
	}
	
}
