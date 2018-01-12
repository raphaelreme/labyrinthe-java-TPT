package graphique;

import javax.swing.JMenuBar;

/*
 * Crée la barre de menu de la fenetre
 */

public final class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	public MenuBar(Frame window){
		this.add(new FileMenu(window));
		this.add(new EditMenu(window));
		this.add(new RunMenu(window));
	}
	

    public void notifyForUpdate(){
    	//possible car un menu implémente une méthode notifyforUpdate
    	for (int i=0; i<this.getMenuCount();i++){
    		((Menu)this.getMenu(i)).notifyForUpdate();
    	}
	}

}
