package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class RunMenu extends Menu{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuItem run;
	private JRadioButtonMenuItem selectedSpeed;

	public RunMenu(Frame window) {
		super("Run", window);
		initRun();
		initStop();
		initSpeed();
		
	}
	
	
	public void initRun(){
		run = new JMenuItem("Run");
		
		run.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.getController().run();
			}
		});
		
		this.add(run);
	}
	
	public void initStop(){
		JMenuItem stop = new JMenuItem("Stop");
		
		stop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainWindow.getController().stop();
			}
		});
		
		this.add(stop);
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
		selectedSpeed = v4;
		
		speed.add(v1);
		speed.add(v2);
		speed.add(v3);
		speed.add(v4);
		
		this.add(speed);
	}
	
	
	public void notifyForUpdate(){
		//On ne peut pas run l'algo si on peut modifier le labyrinthe
		run.setEnabled(mainWindow.getModel().isRunnable());
	}
	
	
	
	
	
	private class SpeedListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int r =mainWindow.getController().setSpeed(((JRadioButtonMenuItem)e.getSource()).getText());
			
			if (r == -1){
				JOptionPane.showMessageDialog(mainWindow,"Changement de vitesse invalide","Erreur",JOptionPane.ERROR_MESSAGE);
				selectedSpeed.setSelected(true); //On reselectionne le bon bouton
			} else if (r == 0){
				selectedSpeed = (JRadioButtonMenuItem)e.getSource();
			}
			
		}
		
	}

	
}
