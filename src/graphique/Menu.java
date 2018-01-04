package graphique;

import javax.swing.JMenu;

public class Menu extends JMenu{

	private static final long serialVersionUID = 1L;
	protected Frame mainWindow;

	public Menu(String title, Frame window){
		super(title);
		mainWindow = window;
	}
	protected void notifyForUpdate(){
		
	}
}
