package TP3;


import menu.Menu;
import menu.VentanaPrincipalMenu;

public class Programa {

		
	public static void main(String[] args) {

		VentanaPrincipalMenu ventana = new VentanaPrincipalMenu();
		Menu menu = new Menu(200, 200);
		ventana.setContentPane(menu);
		ventana.setVisible(true);
	}
	


		
}
