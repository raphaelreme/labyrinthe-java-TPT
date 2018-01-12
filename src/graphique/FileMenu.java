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
    Saver saver;



    public FileMenu(Frame window){
        super("File",window);
        saver = new Saver (window);

        initNew();
        initOpen();
        initSave();
        initQuit();
    }



    private void initNew(){
        JMenuItem _new = new JMenuItem("New");

        _new.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (saver.save() == -1)
                    return;

            	String inputValue = (String) JOptionPane.showInputDialog(mainWindow,
            			"Please input a correct size value","Set size",JOptionPane.PLAIN_MESSAGE,
            			null,null,"10,10");
            	
            	
                int r = mainWindow.getController().setNew(inputValue);
                
                if (r==0){
                	return;
                }
                
                String s = "Invalid size";
                if (r==-1){
                	s += "\nShould be : int length,int width";
                } else if (r==1){
                	s += "\nNegativ length or width";
                } else {
                	s+= "\nHas to be smaller (50*50 max)";
                }
                JOptionPane.showMessageDialog(mainWindow,s,"Error",JOptionPane.ERROR_MESSAGE);
            	actionPerformed(e);
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
        save.addActionListener(saver);

        this.add(save);
    }

    private void initQuit(){
        JMenuItem quit = new JMenuItem("Quit");

        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainWindow.dispose();
            }
        });

        this.add(quit);
    }





    private class OpenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
            if (saver.save() == -1)
                return;

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
			int returnVal = fc.showOpenDialog(mainWindow);
			if (returnVal != 0){
                return;
			}
			
			int r = mainWindow.getController().setFile(fc.getSelectedFile());
			if (r==-1){
				JOptionPane.showMessageDialog(mainWindow,"Fichier inexistant","Erreur",JOptionPane.ERROR_MESSAGE);
				showDialog(fc);//fichier inexistant on demande de nouveau à ouvrir un fichier en reprenant au même endroit
			} else if (r == 1){
				JOptionPane.showMessageDialog(mainWindow,"Fichier invalide","Erreur",JOptionPane.ERROR_MESSAGE);
				showDialog(fc);//fichier invalide on demande de nouveau à ouvrir un fichier en reprenant au même endroit
			}
		}
    }

    /*
     * Ouvre le même genre de boite de dialogue que openListener
     * Differe dans la validation de l'entrée utilisateur
     */
    private class SaveListener extends OpenListener {

        @Override
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