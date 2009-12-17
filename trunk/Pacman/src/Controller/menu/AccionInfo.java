package Controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AccionInfo implements ActionListener {

	//Texto para mostrar en el boton "Info"
	public void actionPerformed(ActionEvent ae) {
		JOptionPane.showMessageDialog(null,
				"Trabajo Practico Algoritmos y Programacion III\n" + "\n"
				+ "Integrantes: \n"
				+ "		Selvanovich Elias \n"
				+ "		Arnaudo Juan Pablo \n"
				+ "		Canuhe Diego \n"
				+ "		Cava Gabriel Alejandro"
				
				
				, "Algo3-Man", 1);
	}
		 
}
