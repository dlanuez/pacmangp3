package Model.juego;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import Controller.InicializadorJuego;
import View.VentanaPrincipal;

public class Menu {
	
	private String titulo;
	private ArrayList<String> opciones;
	private int opcionActual;
	private VentanaPrincipal ventana;
		
	//TODO agregar constantes para texto de las opciones.
	
	public Menu(String titulo, VentanaPrincipal ventana){
		this.titulo = titulo;
		this.opcionActual = 0;
		this.opciones = new ArrayList<String>();
		this.agregarOpcion("Jugar");
		this.agregarOpcion("Info");
		this.agregarOpcion("Salir");
		
		this.ventana = ventana;
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
			System.out.println("JUGAR!");
			this.jugar();		
		}
		
		if(eleccion == "Info"){
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

	private void jugar(){
		InicializadorJuego inicializadorJuego = new InicializadorJuego(this.ventana);		
		inicializadorJuego.comenzarJuego();
	}
}