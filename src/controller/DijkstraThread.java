package controller;

import interfaces.PreviousInterface;
import interfaces.VertexInterface;

import java.util.ArrayList;

import modele.MazeModel;
import box.MBox;
import dijkstra.ASetWithOrder;
import dijkstra.Dijkstra;

class DijkstraThread implements Runnable{
	
	private static int compteur = 0;
	
	private MazeModel model;
	private boolean runThread = true;
	
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
		
		//On s'assure que le potentielle thread précédent s'est bien terminé avant de commencer !
		while (compteur != 0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		started();
		
		model.resetDijkstra();
		
		if (!runThread) {stopped();return;}
		
		//Affichage de l'algorithme
		if (speed != 0){
			ArrayList<VertexInterface> l = a.getList();
			
			for (int i=0; i<l.size(); i++){
				MBox m = (MBox)l.get(i);
				int[] tab = {m.getI(),m.getJ()};
				model.addDijkstra(tab);
				model.refresh();
				try {
					Thread.sleep(1000/speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (!runThread) {stopped();return;}
			}
			
			model.resetDijkstra();
			model.refresh();
			try {
				Thread.sleep(1000/speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (!runThread) {stopped();return;}
		
		//Affichage de la solution
		while (d.getPrev(v) != model.getMaze().getStart()){
			v = d.getPrev(v);
			MBox m = (MBox)v;
			int[] tab = {m.getI(),m.getJ()};
			model.addDijkstra(tab);
			if (!runThread) {stopped();return;}
		}
		model.setRunnable(true);
		model.refresh();
		stopped();
	}
	
	//Permet l'arret du thread
	public void stopThread(){
    	runThread = false;
    }
	
	private static void started(){
		compteur +=1;
	}
	private static void stopped(){
		compteur -=1;
	}
}
