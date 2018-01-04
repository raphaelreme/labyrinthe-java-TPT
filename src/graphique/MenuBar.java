package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;


/*
 * Crée la barre de menu de la fenetre
 */

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	public MenuBar(Frame window){

		this.add(new FileMenu(window));
		this.add(new EditMenu(window));
		this.add(new RunMenu(window));
		
		/*
		 * à modifer : Passage par le contrôleur dans les actionListeners des menuItems
		 */		
	}
	

    public void notifyForUpdate(){
    	//possible car un menu implémente une méthode notifyforUpdate
    	for (int i=0; i<this.getMenuCount();i++){
    		((Menu)this.getMenu(i)).notifyForUpdate();
    	}
	}

}
