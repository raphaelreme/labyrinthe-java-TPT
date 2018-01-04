package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
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
                mainWindow.getModel().changeFile(null);
            }
        });

        this.add(_new);
    }


    private void initOpen(){
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

        this.add(op);
    }

    private void initSave(){
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new SaveMenuItemListener());

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