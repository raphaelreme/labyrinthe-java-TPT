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

	private JMenuItem editable;
	
	public MenuBar(Frame window){
		mainWindow = window;
		initFileMenu();
		initEditMenu();
		initRunMenu();
	}
	
	public void initFileMenu(){
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
		save.addActionListener(new SaveMenuItemListener());
		
		
		file.add(_new);
		file.add(op);
		file.add(save);
		file.add(quit);
		
		this.add(file);
	}

	public void initEditMenu(){
		JMenu edit = new JMenu("Edit");

		editable = new JMenuItem("Edit : Disabled");
		editable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainWindow.getModel().setEditable(!mainWindow.getModel().isEditable());
			}
		});

		edit.add(editable);

		this.add(edit);
	}

	public void initRunMenu(){
		JMenu run = new JMenu("Run");


		this.add(run);
	}

    public void notifyForUpdate(){
		if (mainWindow.getModel().isEditable()){
			editable.setText("Edit : Enabled");
		}else{
			editable.setText("Edit : Disabled");
		}

	}



	private class SaveMenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Texte","txt");
			fc.setCurrentDirectory(new File("file"));
			fc.setFileFilter(filter);
			fc.setAcceptAllFileFilterUsed(false);
			if (mainWindow.getModel().getFile()!=null){
				fc.setSelectedFile(new File("File\\"+mainWindow.getModel().getFile()));
			}

			showDialog(fc);
		}

		public void showDialog(JFileChooser fc){
			int returnVal = fc.showSaveDialog(mainWindow);
			if (returnVal != 0){
				return;
			}
			File f = fc.getSelectedFile();
			String name = f.getName();

			//Verif qu'on enregistre bien un .txt
			if (!name.contains(".")){
				mainWindow.getModel().getM().saveToTextFile(f.getPath()+".txt");
				return;
			}

			if (name.substring(name.length()-4).equals(".txt")){
				mainWindow.getModel().getM().saveToTextFile(f.getPath());
				return;
			}
			//sinon on recommence
			showDialog(fc);
		}
	}

}
