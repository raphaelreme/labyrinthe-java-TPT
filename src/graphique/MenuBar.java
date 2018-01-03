package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;


/*
 * Crée la barre de menu de la fenetre
 */

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private Frame mainWindow;
	
	public MenuBar(Frame window){
		mainWindow = window;
		initMenuFile();
	}
	
	public void initMenuFile(){
		JMenu file = new JMenu("File");
		/*
		 * à modifer : Passage par le contrôleur !
		 */
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		JMenuItem op = new JMenuItem("Open");
		op.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Texte","txt");
				fc.setCurrentDirectory(new File("file"));
				fc.setFileFilter(filter);
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(mainWindow);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					mainWindow.getModel().changeFile(file.getPath());
				}
				
			}
		});
		
		JMenuItem _new = new JMenuItem("New");
		_new.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainWindow.getModel().changeFile(null);
			}
		});
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Texte","txt");
				fc.setCurrentDirectory(new File("file"));
				fc.setFileFilter(filter);
				int returnVal = fc.showSaveDialog(mainWindow);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					mainWindow.getModel().getM().saveToTextFile(file.getPath());
				}
			}
		});
		
		
		file.add(_new);
		file.add(op);
		file.add(save);
		//file.add(new JMenuItem("Save"));
		file.add(quit);
		
		this.add(file);
	}
}
