package Model;

public enum EstadoViviente {	
	CAZADOR, PRESA;	
	
	private EstadoViviente estado;
	
	public void setValue(EstadoViviente nuevoEstado){
		this.estado = nuevoEstado;
	}
	
	public void toggleState(){
		if(this.estado == EstadoViviente.CAZADOR)
			this.estado = EstadoViviente.PRESA;
		else this.estado = EstadoViviente.CAZADOR;
	}
}
