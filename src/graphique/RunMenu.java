package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		
		ActionListener al = new SpeedListener();
		v1.addActionListener(al);
		v2.addActionListener(al);
		v3.addActionListener(al);
		v4.addActionListener(al);
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(v1);
		bg.add(v2);
		bg.add(v3);
		bg.add(v4);
		
		//on selection Instant au début (vitesse initialisée à 0 dans le modele <=> Instant)
		v4.setSelected(true);
		
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
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> tab = new ArrayList<String>();
			tab.add("Instant");
			tab.add("1");
			tab.add("2");
			tab.add("3");
			
			int i = tab.indexOf(((JRadioButtonMenuItem)e.getSource()).getText());
			mainWindow.getModel().setSpeed(i);
			System.out.println(i);
		}
		
	}

	
}
