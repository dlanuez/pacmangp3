package TP3;



import java.awt.Color;

import Controller.menu.Menu;
import Controller.menu.VentanaPrincipal;


public class Programa {

	public static void main(String[] args) {
		VentanaPrincipal ventana = new VentanaPrincipal();
		Menu menu = new Menu (10,40, ventana);
		menu.setBackground(Color.BLACK);
		menu.setOpaque(true);
		ventana.setContentPane(menu);
		ventana.setVisible(true);
	}
			
}
