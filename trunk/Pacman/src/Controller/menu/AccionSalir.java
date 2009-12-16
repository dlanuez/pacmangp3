package Controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionSalir implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		System.out.println("SALIR!");
		System.exit(0);
	}

}