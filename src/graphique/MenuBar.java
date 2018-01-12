package graphique;

import javax.swing.JMenuBar;

/*
 * Cr�e la barre de menu de la fenetre
 */

public final class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	public MenuBar(Frame window){
		this.add(new FileMenu(window));
		this.add(new EditMenu(window));
		this.add(new RunMenu(window));
	}
	

    public void notifyForUpdate(){
    	//possible car un menu impl�mente une m�thode notifyforUpdate
    	for (int i=0; i<this.getMenuCount();i++){
    		((Menu)this.getMenu(i)).notifyForUpdate();
    	}
	}

}
