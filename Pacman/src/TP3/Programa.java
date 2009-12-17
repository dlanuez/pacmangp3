package TP3;



import java.awt.Color;

import Controller.menu.Menu;
import Controller.menu.VentanaPrincipal;


public class Programa {

	public static final int ANCHO_VENTANA_PRINCIPAL = 512;
	public static final int ALTO_VENTANA_PRINCIPAL = 600;
	public static final int ANCHO_PANEL_JUEGO = 512;
	public static final int ALTO_PANEL_JUEGO = 512;
	public static final int TAMANIO_CASILLEROS = 32;
	
	public static void main(String[] args) {
		VentanaPrincipal ventana = new VentanaPrincipal(ANCHO_VENTANA_PRINCIPAL, ALTO_VENTANA_PRINCIPAL);
		Menu menu = new Menu (ventana);
		menu.setBackground(Color.BLACK);
		menu.setOpaque(true);
		ventana.setContentPane(menu);
		ventana.setVisible(true);
	}
			
}
