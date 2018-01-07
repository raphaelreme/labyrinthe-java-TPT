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
	
	private JMenuItem pause;
	private JMenuItem stop;
	private JMenuItem clean;
	private JRadioButtonMenuItem selectedSpeed;

	public RunMenu(Frame window) {
		super("Run", window);
		initRun();
		initPause();
		initStop();
		initClean();
		initSpeed();
		
	}
	
	
	private void initRun(){
		JMenuItem run = new JMenuItem("Run");
		
		run.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int r = mainWindow.getController().run();
				
				if (r == -1){
					JOptionPane.showMessageDialog(mainWindow,"Pas de Maze","Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.add(run);
	}
	
	private void initStop(){
		stop = new JMenuItem("Stop");
		stop.setEnabled(false);
		
		stop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainWindow.getController().stop();
			}
		});
		
		this.add(stop);
	}
	
	private void initPause(){
		pause = new JMenuItem("Pause");
		pause.setEnabled(false);
		
		pause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainWindow.getController().pause();
			}
		});
		
		this.add(pause);
	}
	
	private void initClean(){
		clean = new JMenuItem("Clean");
		
		clean.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainWindow.getController().clean();
			}
		});
		
		this.add(clean);
	}
	
	private void initSpeed(){
		JMenu speed = new JMenu("Speed");
		
		JRadioButtonMenuItem v1 = new JRadioButtonMenuItem("1");
		JRadioButtonMenuItem v2 = new JRadioButtonMenuItem("2");
		JRadioButtonMenuItem v3 = new JRadioButtonMenuItem("5");
		JRadioButtonMenuItem v4 = new JRadioButtonMenuItem("10");
		JRadioButtonMenuItem v5 = new JRadioButtonMenuItem("Instant");
		
		ActionListener al = new SpeedListener();
		v1.addActionListener(al);
		v2.addActionListener(al);
		v3.addActionListener(al);
		v4.addActionListener(al);
		v5.addActionListener(al);
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(v1);
		bg.add(v2);
		bg.add(v3);
		bg.add(v4);
		bg.add(v5);
		
		//on selection Instant au début (vitesse initialisée à 0 dans le modele <=> Instant)
		v5.setSelected(true);
		selectedSpeed = v5;
		
		speed.add(v1);
		speed.add(v2);
		speed.add(v3);
		speed.add(v4);
		speed.add(v5);
		
		this.add(speed);
	}
	
	
	public void notifyForUpdate(){
		boolean b = mainWindow.getModel().isRunning();
		
		stop.setEnabled(b);
		pause.setEnabled(b);
		clean.setEnabled(!b);
		
		if (b && mainWindow.getModel().isPaused()){
			pause.setText("Resume");
		} else {
			pause.setText("Pause");
		}
		
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
