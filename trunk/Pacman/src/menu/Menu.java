package menu;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import ar.uba.fi.algo3.titiritero.vista.Panel;

import java.awt.event.KeyEvent;

public class Menu extends Panel {
   
	private static final long serialVersionUID = 1L;
	protected JButton bJugar, bInfo, bSalir;

    public Menu(int ancho, int alto, VentanaPrincipal ventana) {
    	
    	super(ancho, alto);
       // ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
       // ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
       // ImageIcon rightButtonIcon = createImageIcon("images/left.gif");

        bJugar = new JButton("Jugar"/*, leftButtonIcon*/);
        bJugar.setVerticalTextPosition(AbstractButton.CENTER);
        bJugar.setHorizontalTextPosition(AbstractButton.LEADING); 
        bJugar.setMnemonic(KeyEvent.VK_J);
        bJugar.setAlignmentY(200);
       

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
        
        bJugar.setOpaque(true);
        bInfo.setOpaque(true);
        bSalir.setOpaque(true);
        //bJugar.setToolTipText("Click this button to disable the middle button.");
        //bInfo.setToolTipText("This middle button does nothing when you click it.");
        //bSalir.setToolTipText("Click this button to enable the middle button.");

        //Add Components to this container, using the default FlowLayout.
        add(bJugar);
        add(bInfo);
        add(bSalir);
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

    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */

    /*public void actionPerformed(ActionEvent e) {
    if ("disable".equals(e.getActionCommand())) {
        bInfo.setEnabled(false);
        bJugar.setEnabled(false);
        bSalir.setEnabled(true);
    } else {
        bInfo.setEnabled(true);
        bJugar.setEnabled(true);
        bSalir.setEnabled(false);
    }
	}*/
    
    
    /* private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("ButtonDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Menu newContentPane = new Menu();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }*/

    /*public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }*/
}