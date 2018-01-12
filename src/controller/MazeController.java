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
	public int setNew(String s){
		if (s==null){
			return 0;
		}
		
		
		String[] tab = s.split(",");
		if (tab.length != 2){
			return -1;
		}
		int length;
		int width;
		try{
			length = Integer.valueOf(tab[0]);
			width = Integer.valueOf(tab[1]);
		}catch (NumberFormatException e){
			return -1;
		}
		
		if (width<=0||length<=0){
			return 1;
		}
		if (width>=50||length>=50){
			return 2;
		}
		model.setSaved(true);
		model.changeSize(length,width);
		model.refresh();
		
		return 0;
	}
	
	
	public int setFile(File f){
		if (f == null){
			return -1;
		}
		
		if (f.exists()){
			try {
				model.setFile(f);
				model.setSaved(true);
			} catch (MazeReadingException e){
				System.out.println(e.getMessage());
				try {
					//On remet le fichier précendent en place.
					model.setFile(model.getFile()); // Le fichier précédent était valide, cela ne lève pas d'exception
					model.setSaved(true);
				} catch (MazeReadingException e1) {	
					e1.printStackTrace();
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
			model.setSaved(true);
            return 0;
        }

        if (name.substring(name.length()-4).equals(".txt")){
            model.save(f);
			model.setSaved(true);
            return 0;
        }
        
		return -1;
	}
	
	
	
	
	//EditMenu
	public int setEditable(){
		model.setEditable(!model.isEditable());
		model.resetDijkstra();
		model.refresh();
		return 0;
	}
	
	
		
	
	//RunMenu
	public int run(){
		if (!model.isValidMaze()){
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
