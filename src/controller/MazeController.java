package controller;

import java.io.File;
import java.util.ArrayList;

import exception.MazeReadingException;
import modele.MazeModel;

/*
 * Cette classe fait l'intermédiaire entre les events reçus et la modifications du modèle
 * Controle les données reçues et les traite avant de les envoyer au modèle
 * 
 * Chacune de ses fonctions renvoie un entier caractérisant le contrôle
 * Si tout s'est bien passé elles renvoient 0
 */
public class MazeController {

	private MazeModel model;
	private DijkstraThread dijkstraThread;
	
	public MazeController(MazeModel m){
		this.model = m;
	}
	
	
	
	//File Menu
	public int setFile(File f){
		if (f == null){
			try {
				model.setFile(f); //f == null, il n'y a jamais d'exception ici
			} catch (MazeReadingException e) {
				e.printStackTrace();
			}
			
			if (dijkstraThread != null){
				dijkstraThread.stopThread();
			}
			model.resetDijkstra();
			model.refresh();
			return 0;
		}
		
		if (f.exists()){
			try {
				model.setFile(f);
			} catch (MazeReadingException e){
				e.printStackTrace();
				try {
					//On remet le fichier précendent en place.
					model.setFile(model.getFile()); // Le fichier précédent était valide, cela ne lève pas d'exception
				} catch (MazeReadingException e1) {	
					
				}
				return 1;
			}
			
			if (dijkstraThread != null){
				dijkstraThread.stopThread();
			}
			model.resetDijkstra();
			model.refresh();
			return 0;
		}
		
		return -1;
	}
	
	public int save(File f){
		if (f==null){
			return -1;
		}
		
		
		//Verif qu'on enregistre bien un .txt
		String name = f.getName();
				
        
        if (!name.contains(".")){
            model.save(new File(f.getPath() + ".txt"));
            return 0;
        }

        if (name.substring(name.length()-4).equals(".txt")){
            model.save(f);
            return 0;
        }
        
		return -1;
	}
	
	
	
	
	//EditMenu
	public int setEditable(){
		model.setEditable(!model.isEditable());
		model.refresh();
		return 0;
	}
	
	
		
	
	//RunMenu
	public int run(){
		if (model.getMaze().getLength()==0){
			return -1;
		}
		
		if (dijkstraThread != null){
			if (model.isPaused()) {
				dijkstraThread.changePaused();
			}
			dijkstraThread.stopThread();
		}
		
		dijkstraThread = new DijkstraThread(model);
		Thread t = new Thread(dijkstraThread);
		t.setName("DijkstraThread");
		t.start();
		
		return 0;
	}
	
	public int pause(){
		if (dijkstraThread != null){
			dijkstraThread.changePaused();
		}
		return 0;
	}
	
	public int stop(){
		if (dijkstraThread != null){
			if (model.isPaused()){
				dijkstraThread.changePaused();
			}
			dijkstraThread.stopThread();
		}
		return 0;
	}
	
	public int clean(){
		model.resetDijkstra();
		model.refresh();
		return 0;
	}
	
	public int setSpeed(String s){
		ArrayList<String> tab = new ArrayList<String>();
		tab.add("Instant");
		tab.add("1");
		tab.add("2");
		tab.add("5");
		tab.add("10");
		
		int i = tab.indexOf(s);
		
		if (i == 0){
			model.setSpeed(0);
			model.refresh();
			return 0;
		}
		if (i!=-1){
			model.setSpeed(new Integer(tab.get(i)).intValue());
			model.refresh();
			return 0;
		}
		return -1; //En cas d'erreur
	}
	

}
