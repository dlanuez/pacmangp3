package TP3;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import Model.Punto;
import Model.juego.Juego;
import Model.tablero.Casillero;
import View.Mesa;
import View.VentanaPrincipal;
import View.VistaLaberinto;
import View.Pelota;
import View.VistaPelota;

public class Programa {

	public static void main(String[] args) {
		ControladorJuego controlador = new ControladorJuego();
		//VistaLaberinto vistaLaberinto = new VistaLaberinto(0,0, "borde-izquierdo.jpg");
		Juego juego = new Juego("src/Model/nivel1.xml");
		juego.getTablero().inicializar();
		
		VentanaPrincipal ventana = new VentanaPrincipal(controlador);
		ventana.setVisible(true);
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		
		Mesa mesa = new Mesa(512,512);

		
		Pelota unaPelota = new Pelota(20,30);
		unaPelota.setMesa(mesa);
		
		VistaPelota vistaPelota = new VistaPelota();
		vistaPelota.setPosicionable(unaPelota);
		
		controlador.agregarObjetoVivo(unaPelota);
		
		generarTablero(controlador, juego);
		//controlador.agregarDibujable(vistaLaberinto);
		controlador.agregarDibujable(vistaPelota);
		controlador.setIntervaloSimulacion(20);
		controlador.comenzarJuego();
	}
	
	private static void generarTablero(ControladorJuego controlador, Juego juego){
		Punto punto = new Punto(0,0);
		int maxX = juego.getTablero().getMaxPosX();
		int maxY = juego.getTablero().getMaxPosY();
		int posX, posY;
		VistaLaberinto vistaLaberinto;
		for(int i = 0; i < maxX; i++){
			for(int k = 0; k < maxY; k++){
				punto.x(i);
				punto.y(k);
				Casillero casillero = juego.getTablero().getCasillero(punto);
				posX = 32 * k;
				posY = 32 * i;
				if(!casillero.casilleroHabilitado()){
					if(esCruz(juego,k,i)){
						vistaLaberinto = new VistaLaberinto(posX,posY,"cruz.jpg");
						controlador.agregarDibujable(vistaLaberinto);
					}
					if(esHorizontal(juego,k,i)){
						vistaLaberinto = new VistaLaberinto(posX,posY, "horizontal.jpg");
						controlador.agregarDibujable(vistaLaberinto);
					}
					if(esVertical(juego,k,i)){
						vistaLaberinto = new VistaLaberinto(posX,posY, "vertical.jpg");
						controlador.agregarDibujable(vistaLaberinto);
					}
					if((i==0) && (k==0)){
						vistaLaberinto = new VistaLaberinto(posX,posY,"borde-izquierdo.jpg");
						controlador.agregarDibujable(vistaLaberinto);
					}
					if((i==(maxX-1)) && (k==0)){
						vistaLaberinto = new VistaLaberinto(posX,posY,"borde-inferior-izquierdo.jpg");
						controlador.agregarDibujable(vistaLaberinto);
					}
					if((i==0) && (k==(maxY-1))){
						vistaLaberinto = new VistaLaberinto(posX,posY,"borde-derecho.jpg");
						controlador.agregarDibujable(vistaLaberinto);
					}
					if((i==(maxX-1)) && (k==(maxY-1))){
						vistaLaberinto = new VistaLaberinto(posX,posY,"borde-inferior-derecho.jpg");
						controlador.agregarDibujable(vistaLaberinto);
					}	
					
				}
			}
		}
	}
	
	
	
	private static boolean esHorizontal(Juego juego, int x, int y){
		boolean bandera = false;
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
	
	private static boolean esVertical(Juego juego, int x, int y){
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
	
	private static boolean esCruz(Juego juego, int x, int y){
		boolean bandera = true;
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
				bandera &= true;
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
}
