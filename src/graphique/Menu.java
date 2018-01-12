package graphique;

import javax.swing.JMenu;

/*
 * Classe mère de tous les menus.
 * Possède une méthode notifyForUpdate (pour une utilisation groupée sur tous les menus mêmes ceux qui ne font rien)
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
