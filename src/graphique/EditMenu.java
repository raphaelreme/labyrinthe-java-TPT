package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenuItem;

final class EditMenu extends Menu{

	private static final long serialVersionUID = 1L;
	
	private final JMenuItem editable;
	
	public EditMenu(Frame window){
		super("Edit", window);
		
		editable = new JMenuItem("Edit : Disabled");
		editable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainWindow.getController().setEditable();
			}
		});
		
		this.add(editable);
	}
	
	
	@Override
	public void notifyForUpdate(){
		if (mainWindow.getModel().isEditable()){
			editable.setText("Edit : Enabled");
		}else{
			editable.setText("Edit : Disabled");
		}
		//On ne peut pas modifier le maze tant que celui ci sert de base au Thread de dijkstra
		editable.setEnabled(!mainWindow.getModel().isRunning());
	}
}
