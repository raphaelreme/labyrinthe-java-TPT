package graphique;

import javax.swing.JMenu;

/*
 * Classe m�re de tous les menus.
 * Poss�de une m�thode notifyForUpdate (pour une utilisation group�e sur tous les menus m�mes ceux qui ne font rien)
 */
public class Menu extends JMenu{

	private static final long serialVersionUID = 1L;
	protected Frame mainWindow;

	public Menu(String title, Frame window){
		super(title);
		mainWindow = window;
	}
	
	
	protected void notifyForUpdate(){}
}
