package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class Saver implements ActionListener {

    private Frame mainWindow;

    public Saver(Frame mainWindow){
        this.mainWindow = mainWindow;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        action();
    }


    public int action(){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Texte","txt");
        fc.setCurrentDirectory(new File("file"));
        fc.setFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        if (mainWindow.getModel().getFile()!=null){
            fc.setSelectedFile(new File("File\\"+mainWindow.getModel().getFile()));
        }

        return showDialog(fc);
    }


    private int showDialog(JFileChooser fc){
        int returnVal = fc.showSaveDialog(mainWindow);
        if (returnVal != 0){
            return -1;
        }

        int r = mainWindow.getController().save(fc.getSelectedFile());
        if (r == -1){
            return showDialog(fc); //Le format n'est pas valide, on recomence
        }

        return 0;
    }

    public int save(){
        if (mainWindow.getModel().isSaved())
            return 0;

        int n = JOptionPane.showOptionDialog(mainWindow,"Would you like to save ?","Save",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                null,null,null);

        if (n == JOptionPane.OK_OPTION){
            return action();

        } else if (n == JOptionPane.NO_OPTION){
            return 0;
        }

        return -1;


    }
}