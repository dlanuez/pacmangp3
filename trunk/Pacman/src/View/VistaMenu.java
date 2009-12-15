package View;

import java.awt.Color;

import java.util.ArrayList;

import Model.Punto;
import Model.juego.Menu;
import ar.uba.fi.algo3.titiritero.Posicionable;
import ar.uba.fi.algo3.titiritero.SuperficieDeDibujo;
import ar.uba.fi.algo3.titiritero.vista.Cuadrado;


public class VistaMenu extends Cuadrado implements Posicionable {

	private VistaTitulo titulo;
	private ArrayList<VistaTexto> opciones;
	private Menu menu;
	
	private final int posicionInicialX;
	private final int posicionInicialY;
	
	private final int margenIzquierdoTitulo = 200;
	private final int margenSuperiorTitulo = 100;
	
	private final int margenIzquierdoOpciones = 100;
	private final int separacionEntreOpciones = 50;
	
	private Color colorTitulo = Color.YELLOW;
	private Color colorOpcionSeleccionada = Color.RED;
	private Color colorOpcionDesSeleccionada =  Color.WHITE;
	
	public VistaMenu(int ancho, int alto, Menu menu,  int posicionInicialX, int posicionInicialY) {
	
		super(ancho, alto);
		this.setPosicionable(this);
		this.setColor(Color.BLACK);
		
		if(posicionInicialX > 0)
			this.posicionInicialX = posicionInicialX;
		else throw new IllegalArgumentException();
		
		if(posicionInicialY > 0)
			this.posicionInicialY = posicionInicialY;
		else throw new IllegalArgumentException();
		
		this.menu = menu;
		
		this.titulo = new VistaTitulo(this.colorTitulo, menu.getTitulo(),
				this.getPosicionInicialTitulo().x(), this.getPosicionInicialTitulo().y() );
		
		this.opciones = new ArrayList<VistaTexto>();
		int i = 0;
		while(menu.getOpcion(i) != ""){
			this.agregarOpcion(menu.getOpcion(i));
			i++;
		}
	}

	public int getX() {
		return posicionInicialX;
	}

	public int getY() {
		return posicionInicialY;
	}
	
	@Override
	public void dibujar(SuperficieDeDibujo superfice){
		
		super.dibujar(superfice);
		
		this.titulo.dibujar(superfice);
		
		this.cambiarColorDeOpciones(menu.getOpcionActual());
		
		for(VistaTexto v : opciones){
			v.dibujar(superfice);
		}
	}

	public VistaTexto getOpcion(int numero) {
		return opciones.get(numero);
	}

	public void agregarOpcion(String opcion) {
		int numeroDeOpcion = this.opciones.size() + 1;
		
		VistaTexto vistaTexto = new VistaTexto(
				this.colorOpcionDesSeleccionada, this.colorOpcionSeleccionada, opcion, 
				this.getPosicionInicialOpcion(numeroDeOpcion).x(), this.getPosicionInicialOpcion(numeroDeOpcion).y());
		
		this.opciones.add(vistaTexto);
	}
	
	private Punto getPosicionInicialTitulo(){
		int posX = this.posicionInicialX + this.margenIzquierdoTitulo;
		int posY = this.posicionInicialY + this.margenSuperiorTitulo;
		return new Punto(posX, posY);
	}
	
	private Punto getPosicionInicialOpcion(int numeroDeOpcion){
		
		if(numeroDeOpcion < 0) throw new IllegalArgumentException();
		
		int posX = this.posicionInicialX + this.margenIzquierdoOpciones;
		int posY = this.posicionInicialY + this.margenSuperiorTitulo + (numeroDeOpcion+1) * this.separacionEntreOpciones;
		return new Punto(posX, posY);
	}

	public void setColorOpcionDesSeleccionada(Color colorOpcionDesSeleccionada) {
		this.colorOpcionDesSeleccionada = colorOpcionDesSeleccionada;
	}

	public void setColorOpcionSeleccionada(Color colorOpcionSeleccionada) {
		this.colorOpcionSeleccionada = colorOpcionSeleccionada;
	}

	public void setColorTituloSeleccionado(Color colorTituloSeleccionado) {
		this.colorTitulo = colorTituloSeleccionado;
	}
	
	public void cambiarColorDeOpciones(int opcionSeleccionada){
		int i = 0;
		while(menu.getOpcion(i) != ""){
			this.opciones.get(i).desSeleccionar();
			i++;
		}
		if(opcionSeleccionada < 0 || opcionSeleccionada >= this.opciones.size())
			throw new IndexOutOfBoundsException("El numero de opcion excede la cantidad de opciones.");
		this.opciones.get(opcionSeleccionada).seleccionar();
	}
}
