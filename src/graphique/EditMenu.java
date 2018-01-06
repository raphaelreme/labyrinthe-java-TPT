package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenuItem;

public class EditMenu extends Menu{

	private static final long serialVersionUID = 1L;
	
	private JMenuItem editable;
	
	public EditMenu(Frame window){
		super("Edit", window);
		initEditable();
	}
	
	
	
	public void initEditable(){
		editable = new JMenuItem("Edit : Disabled");
		editable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainWindow.getController().setEditable();
			}
		});
		this.add(editable);
	}
	
	
	
	
	
	
	
	public void notifyForUpdate(){
		if (mainWindow.getModel().isEditable()){
			editable.setText("Edit : Enabled");
		}else{
			editable.setText("Edit : Disabled");
		}
	}
}
