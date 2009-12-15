package TP3;

import Controller.MenuKPObservador;
import Model.juego.Menu;
import View.VentanaPrincipal;
import View.VistaMenu;
import ar.uba.fi.algo3.titiritero.ControladorJuego;


public class Programa {
	
	private static ControladorJuego controlador;
	private static VentanaPrincipal ventana = null;
	

	public static void main(String[] args) {
		
		controlador = new ControladorJuego();
		
		instanciarVentana();
		
		Menu menu = new Menu("Algo-Man", ventana);
		MenuKPObservador menuKPObservador = new MenuKPObservador(menu);
		VistaMenu vistaMenu = new VistaMenu(463,530, menu, 20, 22);
				
		inicializarControladorMenu(controlador, ventana, menuKPObservador, vistaMenu);
		controlador.comenzarJuego();	
		
	}
	
	public static void inicializarControladorMenu(ControladorJuego controlador, VentanaPrincipal ventana, MenuKPObservador menuKPObservador, VistaMenu vistaMenu){
		
		controlador.setIntervaloSimulacion(150);
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		
		controlador.agregarKeyPressObservador(menuKPObservador);
	
		controlador.agregarDibujable(vistaMenu);
	}
	
	private static void instanciarVentana(){
		if(ventana == null){
			ventana = new VentanaPrincipal(controlador);
			ventana.setVisible(true);
		}
	}

	
		
}
