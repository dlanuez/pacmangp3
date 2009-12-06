package TP3;

import ar.uba.fi.algo3.titiritero.ControladorJuego;
import ar.uba.fi.algo3.titiritero.Posicionable;
import Model.Punto;
import Model.excepciones.PosicionInvalidaException;
import Model.excepciones.VelocidadInvalidaException;
import Model.juego.Juego;
import Model.tablero.Casillero;
import View.FantasmaVivo;
import View.Mesa;
import View.PosicionableLaberinto;
import View.VentanaPrincipal;
import View.VistaBolita;
import View.VistaFantasma;
import View.VistaFruta;
import View.VistaLaberinto;
import View.Pelota;
import View.VistaPacman;
import View.VistaPastilla;
import View.VistaPelota;
import View.PacmanVivo;
import View.VistaTablero;

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
			
			
			PacmanVivo unPacman;
			FantasmaVivo unFantasma;
			try {
				unPacman = new PacmanVivo(new Punto(1,8),juego);
				unFantasma = new FantasmaVivo(new Punto(1,14), juego);
			
				unPacman.setMesa(mesa);			
			
				VistaPacman vistaPacman = new VistaPacman();
				vistaPacman.setPosicionable(unPacman);
			
				VistaFantasma vistaFantasma = new VistaFantasma(20,20);
				vistaFantasma.setPosicionable(unFantasma);
				
				controlador.agregarObjetoVivo(unaPelota);
				controlador.agregarObjetoVivo(unPacman);
				controlador.agregarObjetoVivo(unFantasma);
			
				VistaTablero vistaTablero = new VistaTablero(512,512);
				vistaTablero.setPosicionable(mesa);
				controlador.agregarDibujable(vistaTablero);
			
				generarTablero(controlador, juego);
			
				//controlador.agregarDibujable(vistaLaberinto);
				controlador.agregarDibujable(vistaPelota);
				controlador.agregarDibujable(vistaPacman);
				controlador.agregarDibujable(vistaFantasma);
				controlador.setIntervaloSimulacion(300);
				controlador.comenzarJuego();
			} catch (PosicionInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (VelocidadInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
					if(esCruz(juego,i,k)){
						vistaLaberinto = new VistaLaberinto("cruz.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaLaberinto.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaLaberinto);
					}
					if(esHorizontal(juego,i,k)){
						vistaLaberinto = new VistaLaberinto("horizontal.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaLaberinto.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaLaberinto);
					}
					if(esVertical(juego,i,k)){
						vistaLaberinto = new VistaLaberinto("vertical.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaLaberinto.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaLaberinto);
					}
					if((i==0) && (k==0)){
						vistaLaberinto = new VistaLaberinto("borde-izquierdo.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaLaberinto.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaLaberinto);
					}
					if((i==(maxX-1)) && (k==0)){
						crearVista(controlador, posX, posY, "borde-inferior-izquierdo.jpg");
					}
					if((i==0) && (k==(maxY-1))){
						vistaLaberinto = new VistaLaberinto("borde-derecho.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaLaberinto.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaLaberinto);
					}
					if((i==(maxX-1)) && (k==(maxY-1))){
						vistaLaberinto = new VistaLaberinto("borde-inferior-derecho.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaLaberinto.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaLaberinto);
					}	
					if(esBordeIzquierdo(juego, i,k)){
						vistaLaberinto = new VistaLaberinto("borde-izquierdo.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaLaberinto.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaLaberinto);
					}
					if(esBordeDerechoInferior(juego,i,k)){
						vistaLaberinto = new VistaLaberinto("borde-inferior-derecho.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaLaberinto.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaLaberinto);
					}
					if(esBordeDerecho(juego,i,k)){
						vistaLaberinto = new VistaLaberinto("borde-derecho.jpg");
						Posicionable posicionable = new PosicionableLaberinto(posX,posY);
						vistaLaberinto.setPosicionable(posicionable);
						controlador.agregarDibujable(vistaLaberinto);
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
						vistaFruta.setPosicionable(posicionable);
						juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem().setControlador(controlador);
						juego.getTablero().getCasillero(new Punto(i, k)).obtenerItem().setDibujableImagen(vistaFruta);					}
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
		if (i == 10 && k == 8)
			return false;
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
