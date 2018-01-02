package graphique;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import modele.MazeModel;

public class Frame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	MazeModel model = new MazeModel();
	Panel contentPanel;


	public Frame(){
		this.setTitle("Maze");
		this.setIconImage(model.getStart());
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPanel = new Panel(this);
		JMenu m = new JMenu("File");
		m.add(new JMenuItem("Quit"));
		JMenuItem op = new JMenuItem("Open");
		op.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Texte","txt");
				fc.setCurrentDirectory(new File("file"));
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(Frame.this);
				File file = fc.getSelectedFile();
				System.out.println(file.getPath()+" : "+returnVal);
				model.changeFile(file.getPath());
			}
		});
		m.add(op);
		JMenuBar mb = new JMenuBar();
		mb.add(m);
		mb.setBackground(Color.gray);
		this.setJMenuBar(mb);
		
		
		model.addObserver(this);
		
		
		this.setContentPane(contentPanel);

		this.setVisible(true);
		
		
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		//component.update

		
		
	}
	
	public MazeModel getModel(){
		return model;
	}
	
}
