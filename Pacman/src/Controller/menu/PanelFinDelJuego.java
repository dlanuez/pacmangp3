package Controller.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.juego.Jugador;

public class PanelFinDelJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	protected JButton bJugar, bSalir;

    public PanelFinDelJuego(int ancho, int alto, VentanaPrincipal ventana, Jugador jugador) {
    	    	
        ImageIcon bottomIcon = createImageIcon("imagenes/game-over.jpg");
        ImageIcon pacmanIcon = createImageIcon("imagenes/black-pacman.jpg");
           	
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	
    	//--------------------------------------------------------
    	String mensaje;
    	if(jugador.restarVida()) mensaje = "PERDISTE!";
    	else mensaje = "GANASTE!";
    	
    	String puntaje = "Puntaje total: " + Integer.toString(jugador.getPuntos());
    	
    	JTextField bInfoJugador = new JTextField(mensaje);
    	JTextField bInfoPuntaje = new JTextField(puntaje);
        
        bInfoJugador.setHorizontalAlignment(JTextField.CENTER);
        bInfoPuntaje.setHorizontalAlignment(JTextField.CENTER);
        
        //--------------------------------------------------------
        	
    	JLabel gameOver = new JLabel(bottomIcon); 
    	JLabel blackPacman = new JLabel(pacmanIcon); 
    	
    	//--------------------------------------------------------
   
        bJugar = new JButton("Volver a jugar!"/*, leftButtonIcon*/);
        bJugar.setVerticalTextPosition(AbstractButton.CENTER);
        bJugar.setHorizontalTextPosition(AbstractButton.LEADING); 
        bJugar.setMnemonic(KeyEvent.VK_J);
       
        bSalir = new JButton("Salir..."/*, rightButtonIcon*/);
        bSalir.setMnemonic(KeyEvent.VK_S);
   
        //Listen for actions
        bJugar.addActionListener(new AccionJugar(ventana));
        bSalir.addActionListener(new AccionSalir());
               
        //ToolTipText
        bJugar.setToolTipText("Empezar un juego nuevo");
        bInfoPuntaje.setToolTipText("Puntuacion total");
        bSalir.setToolTipText("Salir");
        
        
        setFocusableOpaqueVisible(bJugar);
        setFocusableOpaqueVisible(bSalir);
        setFocusableOpaqueVisible(bInfoJugador);
        setFocusableOpaqueVisible(bInfoPuntaje);
         
        //Iconos
        gameOver.setAlignmentX(CENTER_ALIGNMENT);
        blackPacman.setAlignmentX(CENTER_ALIGNMENT);
        //Botones
        bJugar.setAlignmentX(CENTER_ALIGNMENT);
        bSalir.setAlignmentX(CENTER_ALIGNMENT);
        //Texto
        bInfoJugador.setAlignmentX(CENTER_ALIGNMENT);
        bInfoPuntaje.setAlignmentX(CENTER_ALIGNMENT);
       
        
        //Add Components to this container, using BoxLayout (all the facha)
        add(Box.createRigidArea(new Dimension(0,20)));
        add(blackPacman);
        add(Box.createRigidArea(new Dimension(0,50)));
        add(bJugar);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(bInfoJugador);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(bInfoPuntaje);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(bSalir);
        add(Box.createRigidArea(new Dimension(0,30)));
        add(gameOver);
        
        this.setVisible(true);
        
    }
    
    private void setFocusableOpaqueVisible(JButton boton){
    	boton.setOpaque(true);
    	boton.setFocusable(false);
    	boton.setVisible(true);
    }

    private void setFocusableOpaqueVisible(JTextField boton){
    	boton.setOpaque(true);
    	boton.setFocusable(false);
    	boton.setVisible(true);
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Menu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
  
}