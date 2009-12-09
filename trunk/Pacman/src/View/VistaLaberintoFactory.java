package View;

import java.util.Iterator;

import Model.Punto;
import Model.juego.Juego;
import Model.tablero.Casillero;
import Model.viviente.Fantasma;
import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.Posicionable;

public class VistaLaberintoFactory {

	public static void generarTablero(ControladorJuego controlador, Juego juego){
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
					if(esCruz(juego,i,k)){
						crearVista(controlador, posX, posY, "cruz.jpg");						
					}
					if(esHorizontal(juego,i,k)){
						crearVista(controlador, posX, posY, "horizontal.jpg");					
					}
					if(esVertical(juego,i,k)){
						crearVista(controlador, posX, posY, "vertical.jpg");						
					}
					if((i==0) && (k==0)){
						crearVista(controlador, posX, posY, "borde-izquierdo.jpg");						
					}
					if((i==(maxX-1)) && (k==0)){
						crearVista(controlador, posX, posY, "borde-inferior-izquierdo.jpg");
					}
					if((i==0) && (k==(maxY-1))){
						crearVista(controlador, posX, posY, "borde-derecho.jpg");					
					}
					if((i==(maxX-1)) && (k==(maxY-1))){
						crearVista(controlador, posX, posY, "borde-inferior-derecho.jpg");						
					}	
					if(esBordeIzquierdo(juego, i,k)){
						crearVista(controlador, posX, posY, "borde-izquierdo.jpg");						
					}
					if(esBordeDerechoInferior(juego,i,k)){
						crearVista(controlador, posX, posY, "borde-inferior-derecho.jpg");					
					}
					if(esBordeDerecho(juego,i,k)){
						crearVista(controlador, posX, posY, "borde-derecho.jpg");					
					}
					if(esBordeIzquierdoInferior(juego,i,k)){
						crearVista(controlador, posX, posY, "borde-inferior-izquierdo.jpg");
					}
					
				}
				else{
					if(esBolita(juego, i, k)){
						VistaBolita vistaBolita = new VistaBolita();
						Posicionable posicionable = new PosicionableLaberinto(posX+15,posY+15);
						vistaBolita.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaBolita);
						juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem().setControlador(controlador);
						juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem().setDibujable(vistaBolita);
					}
					if(esPastilla(juego, i, k)){
						VistaPastilla vistaPastilla = new VistaPastilla();
						Posicionable posicionable = new PosicionableLaberinto(posX+15,posY+15);
						vistaPastilla.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaPastilla);
						juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem().setControlador(controlador);
						juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem().setDibujable(vistaPastilla);
						
					}
					if(esFruta(juego, i, k)){
						VistaFruta vistaFruta = new VistaFruta("Fruta.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						//FrutaViva frutaViva = new FrutaViva(juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem());
						vistaFruta.setPosicionable(posicionable);
						juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem().setControlador(controlador);
						juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem().setDibujableImagen(vistaFruta);
						//controlador.agregarObjetoVivo(frutaViva);
					}
				}
					
			}
		}
	}

	private static boolean esFruta(Juego juego, int i, int k) {
		if (!esBolita(juego, i, k) && !esPastilla(juego, i, k))
			return true;
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
			return true;
		return false;
	}

	private static void crearVista(ControladorJuego controlador, int posX,
			int posY, String imagen) {
		VistaLaberinto vistaLaberinto;
		vistaLaberinto = new VistaLaberinto(imagen);
		Posicionable posicionable = new PosicionableLaberinto(posX,posY);
		vistaLaberinto.setPosicionable(posicionable);
		controlador.agregarDibujable(vistaLaberinto);
	}
	
	private static boolean esHorizontal(Juego juego, int x, int y){
		boolean bandera = false;
		if(x-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x-1,y)).casilleroHabilitado()){
				bandera = false;
			}			
		}		
		if(y-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x,y-1)).casilleroHabilitado()){
				bandera = true;
			}
		}		
		if(x+1 < juego.getTablero().getMaxPosX()){
			if(!juego.getTablero().getCasillero(new Punto(x+1,y)).casilleroHabilitado()){
				bandera = false;
			}
		}		
		if(y + 1 < juego.getTablero().getMaxPosY()){
			if(!juego.getTablero().getCasillero(new Punto(x,y+1)).casilleroHabilitado()){
				bandera = true;
			}
		}		
		return bandera;
	}
	
	private static boolean esVertical(Juego juego, int x, int y){
		boolean bandera = true;
		if(x-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x-1,y)).casilleroHabilitado()){
				bandera = true;
			}			
		}		
		if(y-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x,y-1)).casilleroHabilitado()){
				bandera = false;
			}
		}		
		if(x+1 < juego.getTablero().getMaxPosX()){
			if(!juego.getTablero().getCasillero(new Punto(x+1,y)).casilleroHabilitado()){
				bandera = true;
			}
		}		
		if(y + 1 < juego.getTablero().getMaxPosY()){
			if(!juego.getTablero().getCasillero(new Punto(x,y+1)).casilleroHabilitado()){
				bandera = false;
			}
		}		
		return bandera;
	}
	
	private static boolean esCruz(Juego juego, int x, int y){
		boolean bandera = false;
		if(x-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x-1,y)).casilleroHabilitado()){
				bandera &= true;
			}			
		}else{
			bandera = false;
		}
		if(y-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x,y-1)).casilleroHabilitado()){
				bandera &= true;
			}
		}else{
			bandera = false;
		}
		if(x+1 < juego.getTablero().getMaxPosX()){
			if(!juego.getTablero().getCasillero(new Punto(x+1,y)).casilleroHabilitado()){
				bandera = true;
			}
		}else{
			bandera = false;
		}
		if(y + 1 < juego.getTablero().getMaxPosY()){
			if(!juego.getTablero().getCasillero(new Punto(x,y+1)).casilleroHabilitado()){
				bandera &= true;
			}
		}else{
			bandera = false;
		}
		return bandera;
	}
	
	private static boolean esBordeIzquierdo(Juego juego, int x, int y){
		boolean bandera = false;
		if(x+1 < juego.getTablero().getMaxPosX()){
			if(!juego.getTablero().getCasillero(new Punto(x+1,y)).casilleroHabilitado()){
				bandera = true;
			}
		}		
		if(y + 1 < juego.getTablero().getMaxPosY()){
			if(!juego.getTablero().getCasillero(new Punto(x,y+1)).casilleroHabilitado()){
				bandera &= true;
			}else{
				bandera &=false;
			}
		}		
		if(x-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x-1,y)).casilleroHabilitado()){
				bandera &= false;
			}			
		}		
		if(y-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x,y-1)).casilleroHabilitado()){
				bandera &= false;
			}
		}				
		return bandera;
	}
	
	private static boolean esBordeDerecho(Juego juego, int x, int y) {
		boolean bandera = false;
		if(x-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x-1,y)).casilleroHabilitado()){
				bandera &= false;
			}			
		}		
		if(y-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x,y-1)).casilleroHabilitado()){
				bandera = true;
			}
		}		
		if(x+1 < juego.getTablero().getMaxPosX()){
			if(!juego.getTablero().getCasillero(new Punto(x+1,y)).casilleroHabilitado()){
				bandera &= true;
			}else{
				bandera &= false;
			}
		}else{
			bandera &= false;
		}		
		if(y + 1 < juego.getTablero().getMaxPosY()){
			if(!juego.getTablero().getCasillero(new Punto(x,y+1)).casilleroHabilitado()){
				bandera &= false;
			}
		}		
		return bandera;
	}
	
	private static boolean esBordeDerechoInferior(Juego juego, int x, int y) {
		boolean bandera = false;
		if(x-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x-1,y)).casilleroHabilitado()){
				bandera = true;
			}			
		}		
		if(y-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x,y-1)).casilleroHabilitado()){
				bandera &= true;
			}else{
				bandera &= false;
			}
		}		
		if(x+1 < juego.getTablero().getMaxPosX()){
			if(!juego.getTablero().getCasillero(new Punto(x+1,y)).casilleroHabilitado()){
				bandera &= false;
			}
		}		
		if(y + 1 < juego.getTablero().getMaxPosY()){
			if(!juego.getTablero().getCasillero(new Punto(x,y+1)).casilleroHabilitado()){
				bandera &= false;
			}
		}		
		return bandera;
	}
	
	private static boolean esBordeIzquierdoInferior(Juego juego, int x, int y) {
		boolean bandera = false;
		if(x-1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x-1,y)).casilleroHabilitado()){
				bandera = true;
			}			
		}		
		if(y + 1 < juego.getTablero().getMaxPosY()){
			if(!juego.getTablero().getCasillero(new Punto(x,y+1)).casilleroHabilitado()){
				bandera &= true;
			}else{
				bandera &= false;
			}
		}		
		if(x+1 < juego.getTablero().getMaxPosX()){
			if(!juego.getTablero().getCasillero(new Punto(x+1,y)).casilleroHabilitado()){
				bandera &= false;
			}
		}		
		if(y -1 >= 0){
			if(!juego.getTablero().getCasillero(new Punto(x,y-1)).casilleroHabilitado()){
				bandera &= false;
			}
		}		
		return bandera;
	}

	
}
