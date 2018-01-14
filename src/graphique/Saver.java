package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * Cette classe regroupe tout le code dédié à la sauvegarde.
 * Sa première fonction est d'écouter le(s) bouton(s) save de l'interface
 * 
 * Sa seconde fonction est d'être appelée sur sa fonction save à chaque changement de fichier :
 * new, open mais aussi lors de la fermeture de la fenetre. Elle permet alors à l'utilisateur
 * de sauvegarder son Maze si celui ci n'est pas déjà enregistré !
 */
final class Saver implements ActionListener {

    private final Frame mainWindow;

    public Saver(Frame mainWindow){
        this.mainWindow = mainWindow;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        action();
    }

    /*
     * Déclenche le choix du fichier pour la sauvegarde du Maze.
     * Renvoie 0 si la sauvegarde s'est bien effectué, -1 sinon.
     */
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
            return showDialog(fc); //Le format n'est pas valide, on recommence
        }

        return 0;
    }

    /*
     * Vérifie si l'utilisateur veut sauver son travail.
     * Cette fonction doit être appelée avant chaque action affectant de la zone de travail de l'utilisateur
     * 
     * Renvoie 0 pour confirmer l'action : le travail est alors sauvé ou abandonné par l'utilisateur
     * Renvoie -1 pour annuler l'action, si l'une des boites de dialogue a été fermée.
     */
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