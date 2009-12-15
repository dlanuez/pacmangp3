package Model.juego;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Menu {
	
	private String titulo;
	private ArrayList<String> opciones;
	private int opcionActual;
				
	public Menu(String titulo){
		this.titulo = titulo;
		this.opcionActual = 0;
		this.opciones = new ArrayList<String>();
		this.agregarOpcion("Jugar");
		this.agregarOpcion("HighScores");
		this.agregarOpcion("Salir");
	}
	
	public void agregarOpcion(String opcion){
		this.opciones.add(opcion);
	}
	
	public String getOpcion(int numero){
		if(numero >= 0 && numero < this.opciones.size())
			return this.opciones.get(numero);
		else return "";
	}

	public int getOpcionActual(){
		return this.opcionActual;
	}
	
	public void elegir(int codigo) {
		
		switch(codigo){
			case KeyEvent.VK_UP:
				this.subir();
				break;
			case KeyEvent.VK_DOWN:
				this.bajar();
				break;
			case KeyEvent.VK_ENTER:
				this.seleccionar();
				break;
			case KeyEvent.VK_ESCAPE:
				this.salirPrograma();		
				break;	
		}
	}

	private void salirPrograma() {
		System.exit(0);		
	}

	private void seleccionar() {
		String eleccion = this.getOpcion(this.opcionActual);
		
		if(eleccion == "Jugar"){
			//TODO
			/*Juego juego = new Juego("src/Model/nivel1.xml");
			juego.getTablero().inicializar();	
			
			JuegoVivo juegoVivo = new JuegoVivo(juego, ventana);
			juegoVivo.inicializarControlador();
			juegoVivo.getControladorJuego().comenzarJuego();*/
			System.out.println("JUGAR!");
		}
		
		if(eleccion == "HighScores"){
			System.out.println("HS!!");
		}
		
		if(eleccion == "Salir"){
			this.salirPrograma();
		}
	
	}

	private void subir() {
		if(this.opcionActual > 0)
			this.opcionActual--;		
	}

	private void bajar() {
		if(this.opcionActual < this.opciones.size()-1)
			this.opcionActual++;		
	}
	
	public String getTitulo() {
		return titulo;
	}

}