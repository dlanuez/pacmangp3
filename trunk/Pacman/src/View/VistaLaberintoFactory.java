package View;

import java.awt.Color;
import java.util.Iterator;

import Controller.FrutaViva;
import Model.Punto;
import Model.juego.Juego;
import Model.tablero.Casillero;
import Model.viviente.Fantasma;
import Model.item.*;
import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class VistaLaberintoFactory {

	public static void generarTablero(ControladorJuego controlador, Juego juego, Color color){
		Punto punto = new Punto(0,0);
		int maxX = juego.getTablero().getMaxPosX();
		int maxY = juego.getTablero().getMaxPosY();
		int posX, posY;
		for(int i = 0; i < maxX; i++){
			for(int k = 0; k < maxY; k++){
				punto.x(i);
				punto.y(k);
				Casillero casillero = juego.getTablero().getCasillero(punto);
				posX = 32 * k;
				posY = 32 * i;
				if(!casillero.casilleroHabilitado()){
					VistaPared vistaPared = new VistaPared(30, 30);
					vistaPared.setColor(color);
					Posicionable posicionable = new PosicionableLaberinto(posX,posY);
					vistaPared.setPosicionable(posicionable);
					controlador.agregarDibujable(vistaPared);					
				}
				else{
					if(esBolita(juego, i, k)){
						try{
						VistaBolita vistaBolita = new VistaBolita(((Bolita) juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem()));
						Posicionable posicionable = new PosicionableLaberinto(posX+15,posY+15);
						vistaBolita.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaBolita);
						}
						catch(ClassCastException e){
							
						}
					}
					if(esPastilla(juego, i, k)){
						try{
						VistaPastilla vistaPastilla = new VistaPastilla(((Pastilla) juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem()));
						Posicionable posicionable = new PosicionableLaberinto(posX+15,posY+15);
						vistaPastilla.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaPastilla);
						}
						catch(ClassCastException e){
							
						}
					}
					if(esFruta(juego, i, k)){
						try{
						VistaFruta vistaFruta = new VistaFruta("imagenes/Fruta.jpg", ((Fruta) juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem()));
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaFruta.setPosicionable(posicionable);
						FrutaViva frutaViva = new FrutaViva(((Fruta) juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem()));
						controlador.agregarObjetoVivo(frutaViva);
						controlador.agregarDibujable(vistaFruta);
						}
						catch(ClassCastException e){
							
						}
					}
				}
					
			}
		}
	}

	private static boolean esFruta(Juego juego, int i, int k) {
		try{
			Punto posicion = ((Fruta) juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem()).getPosicion();
			if (posicion.x() == i && posicion.y() == k)
				return true;
		}
		catch(ClassCastException e){
			
		}
		return false;
	}

	private static boolean esPastilla(Juego juego, int i, int k) {
		if (i == 1 && k == 1)
			return true;
		if (i == juego.getTablero().getMaxPosX()-2 && k == juego.getTablero().getMaxPosY()-2)
			return true;
		if (i == juego.getTablero().getMaxPosX()-2 && k == 1)
			return true;
		if (i == 1 && k == juego.getTablero().getMaxPosY()-2)
			return true;
		return false;
	}

	private static boolean esBolita(Juego juego, int i, int k) {
		Iterator<Fantasma> fantasmas= juego.getTablero().getFantasmasIterador();
		while(fantasmas.hasNext()){
			Punto unPunto = new Punto(i,k);
			if(fantasmas.next().getPosicion().equals(unPunto)){
				return false;
			}
		}
		if(!esPastilla(juego, i, k))
			if (!esFruta(juego, i, k))
			return true;
		return false;
	}

	
	
}
