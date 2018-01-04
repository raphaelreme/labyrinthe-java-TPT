package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class RunMenu extends Menu{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuItem run;

	public RunMenu(Frame window) {
		super("Run", window);
		initRun();
		initSpeed();
	}
	
	
	public void initRun(){
		run = new JMenuItem("Run");
		
		this.add(run);
	}
	
	public void initSpeed(){
		JMenu speed = new JMenu("Speed");
		
		JRadioButtonMenuItem v1 = new JRadioButtonMenuItem("1");
		JRadioButtonMenuItem v2 = new JRadioButtonMenuItem("2");
		JRadioButtonMenuItem v3 = new JRadioButtonMenuItem("3");
		JRadioButtonMenuItem v4 = new JRadioButtonMenuItem("Instant");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(v1);
		bg.add(v2);
		bg.add(v3);
		bg.add(v4);
		
		speed.add(v1);
		speed.add(v2);
		speed.add(v3);
		speed.add(v4);
		
		this.add(speed);
	}
	
	
	public void notifyForUpdate(){
		//On ne peut pas run l'algo si on peut modifier le labyrinthe
		run.setEnabled(!mainWindow.getModel().isEditable());
	}
	
	
	private class SpeedListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
}
