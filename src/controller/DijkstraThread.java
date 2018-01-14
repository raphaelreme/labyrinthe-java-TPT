package controller;

import interfaces.PreviousInterface;
import interfaces.VertexInterface;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import modele.MazeModel;
import box.MBox;
import dijkstra.ASetWithOrder;
import dijkstra.Dijkstra;

/*
 * Thread dédié à dijkstra.
 * Les méthodes de pause et d'arret de la classe Thread ne devant plus être utilisée,
 * cette classe gère elle même ces actions.
 * 
 */
final class DijkstraThread implements Runnable{
	
	private static int compteur = 0;
	
	private final MazeModel model;
	private boolean runThread = true;
	private boolean pauseThread = false;
	
	public DijkstraThread(MazeModel m){
		model = m;
	}

	@Override
	public void run() {
		/*
		 * En parallèle model.getSpeed() peut changer au cours de l'execution de run
		 * D'ou l'intérêt de n'y acceder qu'une seule fois 
		 */
		int speed = model.getSpeed();
		
		ASetWithOrder a = new ASetWithOrder();			
		PreviousInterface d = Dijkstra.dijkstra(model.getMaze(), model.getMaze().getStart(),a);
		VertexInterface v = model.getMaze().getEnd();
		
		//On s'assure que le potentiel thread précédent s'est bien terminé avant de commencer !
		while (compteur != 0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		started();
		pause();
		
		model.resetDijkstra();
		
		if (!runThread) {stopped();return;}
		
		//Affichage de l'algorithme
		if (speed != 0){
			ArrayList<VertexInterface> l = a.getList();
			
			for (int i=0; i<l.size(); i++){
				MBox m = (MBox)l.get(i);
				int[] tab = {m.getI(),m.getJ()};
				model.addDijkstra(tab);
				refresh();
				try {
					Thread.sleep(1000/speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pause();
				if (!runThread) {stopped();return;}
			}
			
			model.resetDijkstra();
			refresh();
			try {
				Thread.sleep(1000/speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		pause();
		if (!runThread) {stopped();return;}
		
		//Affichage de la solution
		while (d.getPrev(v) != model.getMaze().getStart() && d.getPrev(v)!= null){
			v = d.getPrev(v);
			MBox m = (MBox)v;
			int[] tab = {m.getI(),m.getJ()};
			model.addDijkstra(tab);
			if (!runThread) {stopped();return;}
		}
		//model.refresh() dans le stopped.
		stopped();
	}
	
	
	
	//Permet l'arret/pause du thread
	public void stopThread(){
    	runThread = false;
    }
	public void changePaused(){
		pauseThread = !pauseThread;
		model.setPaused(pauseThread);
		refresh();
	}
	
	private synchronized void started(){
		compteur +=1;
		model.setRunning(true);
		refresh();
	}
	private void stopped(){
		//on passe à faux avant de liberer le demarrage d'un autre DijkstraThread (compteur -=1)
		model.setRunning(false);
		compteur -=1;
		refresh();
	}
	
	/*
	 * Met en pause le thread
	 */
	private void pause(){
		while (pauseThread){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* 
	 * Met à jour le modèle qui va lui même prevenir la fénetre de se réafficher :
	 * cela doit se faire dans l'EDT et non dans ce Thread.
	 */
	private void refresh(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				model.refresh();
			}
		});
	}
	
}
