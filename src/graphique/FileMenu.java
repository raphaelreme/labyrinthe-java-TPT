package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileMenu extends Menu{

    private static final long serialVersionUID = 1L;




    public FileMenu(Frame window){
        super("File",window);
        initNew();
        initOpen();
        initSave();
        initQuit();
    }



    private void initNew(){
        JMenuItem _new = new JMenuItem("New");

        /*
         * à modif pour demander une taille et autre
         */
        _new.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainWindow.getController().setFile(null);
            }
        });

        this.add(_new);
    }


    private void initOpen(){
        JMenuItem op = new JMenuItem("Open");
        op.addActionListener(new OpenListener());

        this.add(op);
    }

    private void initSave(){
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new SaveListener());

        this.add(save);
    }

    private void initQuit(){
        JMenuItem quit = new JMenuItem("Quit");

        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        this.add(quit);
    }





    private class OpenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Texte","txt");
            fc.setCurrentDirectory(new File("file"));
            fc.setFileFilter(filter);
            fc.setAcceptAllFileFilterUsed(false);
            
            showDialog(fc);
		}
		
		public void showDialog(JFileChooser fc){
			int returnVal = fc.showOpenDialog(mainWindow);
			if (returnVal != 0){
                return;
			}
			
			int r = mainWindow.getController().setFile(fc.getSelectedFile());
			if (r==-1){
				JOptionPane.showMessageDialog(mainWindow,"Fichier inexistant","Erreur",JOptionPane.ERROR_MESSAGE);
				showDialog(fc);//fichier inexistant on demande de nouveau à ouvrir un fichier
			} else if (r == 1){
				JOptionPane.showMessageDialog(mainWindow,"Fichier invalide","Erreur",JOptionPane.ERROR_MESSAGE);
				showDialog(fc);//fichier invalide on demande de nouveau à ouvrir un fichier
			}
		}
    }

    private class SaveListener implements ActionListener {

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
            
            int r = mainWindow.getController().save(fc.getSelectedFile());
            if (r == -1){
            	showDialog(fc); //Le format n'est pas valide, on recomence
            }
        }
    }
}