package Controller.menu;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

public class Menu extends JPanel {
   
	private static final long serialVersionUID = 1L;
	protected JButton bJugar, bInfo, bSalir;

    public Menu(int ancho, int alto, VentanaPrincipal ventana) {
    	    	
        ImageIcon tituloIcon = createImageIcon("titulo.gif");
       // ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
       // ImageIcon rightButtonIcon = createImageIcon("images/left.gif");
    	
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	
    	JButton titulo = new JButton(tituloIcon);
    	titulo.setBackground(Color.YELLOW);
    	
    	
        bJugar = new JButton("Jugar"/*, leftButtonIcon*/);
        bJugar.setVerticalTextPosition(AbstractButton.CENTER);
        bJugar.setHorizontalTextPosition(AbstractButton.LEADING); 
        bJugar.setMnemonic(KeyEvent.VK_J);
       
       

        bInfo = new JButton("Info"/*, middleButtonIcon*/);
        bInfo.setVerticalTextPosition(AbstractButton.BOTTOM);
        bInfo.setHorizontalTextPosition(AbstractButton.CENTER);
        bInfo.setMnemonic(KeyEvent.VK_I);

        bSalir = new JButton("Salir"/*, rightButtonIcon*/);
        bSalir.setMnemonic(KeyEvent.VK_S);
   
        //Listen for actions on buttons 1 and 3.
        bJugar.addActionListener(new AccionJugar(ventana));
        bInfo.addActionListener(new AccionInfo());
        bSalir.addActionListener(new AccionSalir());
        
       
        
        bJugar.setToolTipText("Comenzar juego");
        bInfo.setToolTipText("Informacion inutil");
        bSalir.setToolTipText("...");
        
        setFocusableOpaqueVisible(bJugar);
        setFocusableOpaqueVisible(bInfo);
        setFocusableOpaqueVisible(bSalir);
        
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        bJugar.setAlignmentX(CENTER_ALIGNMENT);
        bInfo.setAlignmentX(CENTER_ALIGNMENT);
        bSalir.setAlignmentX(CENTER_ALIGNMENT);
        
        //Add Components to this container, using the default FlowLayout.
        add(titulo);
        add(Box.createRigidArea(new Dimension(0,150)));
        add(bJugar);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(bInfo);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(bSalir);
    }
    
    private void setFocusableOpaqueVisible(JButton boton){
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