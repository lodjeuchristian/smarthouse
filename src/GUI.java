import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;


public class GUI extends JFrame{

 
	private Draw house;
	private Simulation simulation;
	private Thread t;
	private Thread t2;
	private Thread tDraw;
	
	
	private JMenuItem jouer;
	private JMenuItem pause;
	private JMenuItem arreter;
	private JMenuItem sauvegarder;
	private JMenuItem supprimerpiece;
	private JMenuItem nouvellePiece;
 
	
	
	public GUI(String titre){ 
		simulation = new Simulation();
		house = new Draw(); 
		
		house.addMouseListener(new MouseListener() {
			
 
			
			//Si on double-clique n'importe où sur l'écran
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2) {
			        System.out.println("dbclic");
			        //on recupère les coordonnée de la souris et on verifie si ce sont ceux de l'habitant...
			    }
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		Image icone = Toolkit.getDefaultToolkit().getImage("logo.png");
		this.setIconImage(icone);
		t = new Thread(simulation);
		tDraw = new Thread(house);
		t2 = new Thread(new SimulationAppareil()); 
		this.setTitle(titre);
		getContentPane().setBackground(Color.BLACK);
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(getMenu());
		this.setVisible(true);
		
		
	
	}
	 
  
	public void actualiser(){
		setSize(getWidth()+1,getHeight()+1);
		setSize(getWidth()-1,getHeight()-1);	
	
	
	}
	

	
	public void lanceDraw(){
		actualiser();

	 	
		Vector<Piece> p = Maison.getInstance().getPiece(); 
		house.setPieces(p);
		house.setNbP(p.size());
		this.setContentPane(house);
		house.repaint();
	}
	
	public void supprimerPiece(){
		try{
			int l;
			do{
				JOptionPane jopa = new JOptionPane();
				String nom = jopa.showInputDialog(null, "Entrer le numéro de la pièce à supprimer)", "Suppression d'une pièce", JOptionPane.INFORMATION_MESSAGE);
				l = new Double(nom).intValue();
			}
			while(l>Maison.getInstance().getNbPiece());
			Maison.getInstance().removePiece(l);
			Maison.getInstance().setNbPiece(Maison.getInstance().getNbPiece()-1);	 
 
		}catch(Exception x){
		 
		}				
	}
	
	private JMenuBar getMenu(){
		
		
		JMenuBar barreMenu = new JMenuBar();
		
		
		JMenu fichier = new JMenu("Fichier");
		fichier.setMnemonic('F');
		
		JMenuItem nouvelleMaison = new JMenuItem("Nouvelle maison");
		nouvelleMaison.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		nouvelleMaison.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane jop = new JOptionPane();			
				int option = jop.showConfirmDialog(null, "L'application ne supporte qu'une seule maison à la fois !\nVoulez-vous continuer ?", "Attention", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				int i = 1;
				if(option == JOptionPane.OK_OPTION)
				{ 
		
					try{
						do{
							JOptionPane jopa = new JOptionPane();
							String nom = jopa.showInputDialog(null, "Entrer le nombre de pièces (2 au maximum)", "Nombre de pières", JOptionPane.INFORMATION_MESSAGE);
							i = new Double(nom).intValue();
						}
						while(i>2);
						Maison.getInstance().removeAllPiece();
						Maison.getInstance().setNbPiece(0);	 
						Maison.getInstance().setHeure(12);
						if(Maison.getInstance().isStop())
							Maison.getInstance().setStop(false);
						System.out.println("VOICI LA MAISON : "+Maison.getInstance());
		
						for(int j = 1; j<=i; ++j){
							NouvellePiece zd = new NouvellePiece(null, "Piece " + j + "", true);
							zd.showZDialog(); 	
						}
					}catch(Exception a){
					 
					}		
				}
				 
			    lanceDraw();
				t = new Thread(simulation);
				t2 = new Thread(new SimulationAppareil());
				tDraw = new Thread(house);
				
				t.start(); 
				t2.start();
				tDraw.start();
			    jouer.setEnabled(false);
				pause.setEnabled(true);
				arreter.setEnabled(true);
				sauvegarder.setEnabled(true);
				supprimerpiece.setEnabled(true);
				if(Maison.getInstance().getNbPiece() == 2)
					nouvellePiece.setEnabled(false);
			}
			
		});

 
		nouvellePiece = new JMenuItem("Nouvelle pièce");
		nouvellePiece.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		nouvellePiece.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(Maison.getInstance().getNbPiece()<2){
					if(Maison.getInstance().getPiece().size() == 1)
						nouvellePiece.setEnabled(false);
					NouvellePiece zd = new NouvellePiece(null, "Nouvelle Pièce", true);
					zd.showZDialog();   
				 	lanceDraw();
					actualiser();
					Maison.getInstance().setStop(false);
					Maison.getInstance().setPlay(true);
					t = new Thread(simulation);
					t2 = new Thread(new SimulationAppareil());
					tDraw = new Thread(house);
					
					tDraw.start();
					t.start(); 
					t2.start();
				    jouer.setEnabled(false);
					pause.setEnabled(true);
					arreter.setEnabled(true);
					sauvegarder.setEnabled(true);
					supprimerpiece.setEnabled(true);
				
					
				}
				else{
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "La maison ne peut contenir que 2 pièces", "Information", JOptionPane.ERROR_MESSAGE);
					return;
				}
			 
			}
		});
		
		supprimerpiece = new JMenuItem("Supprimer une pièce");
		supprimerpiece.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		supprimerpiece.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				supprimerPiece();
				lanceDraw();
				t = new Thread(simulation);
				t2 = new Thread(new SimulationAppareil());
				tDraw = new Thread(house);
				t.start(); 
				t2.start();
				tDraw.start();
				nouvellePiece.setEnabled(true);
			}
		});
		
		JMenuItem ouvrir = new JMenuItem("Ouvrir");
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		ouvrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				 ConfigLoader loader = new ConfigLoader();
				 if(Maison.getInstance().isStop()){
					 jouer.setEnabled(true);
					 pause.setEnabled(false);
					 arreter.setEnabled(false);
				 }
				 else
				 if(Maison.getInstance().isPlay()){
					jouer.setEnabled(false);
					pause.setEnabled(true);
					arreter.setEnabled(true);
				 }
				 else
				 if(!Maison.getInstance().isPlay()){
					jouer.setEnabled(true);
					pause.setEnabled(false);
					arreter.setEnabled(true);
				 }
						  
					
				 loader.load();
				 lanceDraw();
				 t = new Thread(simulation);
				 t2 = new Thread(new SimulationAppareil());
				 tDraw = new Thread(house);
				 t.start(); 
				 t2.start();
				 tDraw.start();
			}
		});
		sauvegarder = new JMenuItem("Sauvegarder");
		sauvegarder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		sauvegarder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				 ConfigSaver saver = new ConfigSaver();
				 saver.save();
				 t = new Thread(simulation);
				 t2 = new Thread(new SimulationAppareil());
				 tDraw = new Thread(house);
				 t.start(); 
				 t2.start();
				 tDraw.start();
			}
		});
		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				tDraw.stop();
				t.stop();
				t2.stop();
				dispose();
				 
			}
		});
		
		//sauvegarder.setEnabled(false);
		
		fichier.add(nouvelleMaison);
		fichier.add(nouvellePiece);
		fichier.add(supprimerpiece);
		fichier.addSeparator();
		fichier.add(ouvrir);
		fichier.add(sauvegarder);
		fichier.addSeparator();
		fichier.add(quitter);

		
		JMenu simulation = new JMenu("Simulation");
		simulation.setMnemonic('S');
		
		
		jouer = new JMenuItem("Jouer");
		jouer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_MASK));
		jouer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				Maison.getInstance().setPlay(true);
				Maison.getInstance().setStop(false);
				t = new Thread(new Simulation());
				t2 = new Thread(new SimulationAppareil());
				tDraw = new Thread(house);
				t.start(); 
				t2.start();
				tDraw.start();
				pause.setEnabled(true);
				arreter.setEnabled(true);
				jouer.setEnabled(false);			
			}
		});	
		pause = new JMenuItem("Pause");
		pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
		pause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				Maison.getInstance().setPlay(false);
				Maison.getInstance().setStop(false); 
				pause.setEnabled(false);
				arreter.setEnabled(true);
				jouer.setEnabled(true);	
			}
		});
		arreter = new JMenuItem("Arreter");
		arreter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
		arreter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				JOptionPane jop = new JOptionPane();			
				int option = jop.showConfirmDialog(null, "Voulez-vous complètement arrêter la simulation et reinitialiser l'application ?", "Attention", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				int i = 1;
				if(option == JOptionPane.OK_OPTION)
				{ 
					Maison.getInstance().setPlay(false);
					Maison.getInstance().setStop(true);
					pause.setEnabled(false);
					arreter.setEnabled(false);
					jouer.setEnabled(false);
					sauvegarder.setEnabled(false);
					supprimerpiece.setEnabled(false);
					nouvellePiece.setEnabled(true);
					Maison.getInstance().removeAllPiece();  
					t = new Thread(new Simulation());
					t2 = new Thread(new SimulationAppareil());
					tDraw = new Thread(house);
					t.start(); 
					t2.start();
					tDraw.start();
					getContentPane().setBackground(Color.BLACK);
				}
			}
		});
		
		if(Maison.getInstance().isPlay()){
			pause.setEnabled(true);
			arreter.setEnabled(true);
			jouer.setEnabled(false);
		}
	//	penser à créer les attributs jouer pause et arreter pour etre accessible partout dans la classe
	     jouer.setEnabled(false);
		 pause.setEnabled(false);
		 arreter.setEnabled(false);
		 sauvegarder.setEnabled(false);
		 supprimerpiece.setEnabled(false);
		
		simulation.add(jouer);
		simulation.addSeparator();
		simulation.add(pause);
		simulation.addSeparator();
		simulation.add(arreter);
		
		
		JMenu aideMenu = new JMenu("Aide");
		aideMenu.setMnemonic('A');
		
		JMenuItem aPropos = new JMenuItem("A Propos");
		aPropos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.CTRL_MASK));
		aPropos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				System.out.println("I");
			}
		});	
		JMenuItem aide = new JMenuItem("Aide");
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_MASK));
		aide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
			 
			}
		});
		 
		aideMenu.add(aPropos);
		aideMenu.addSeparator();
		aideMenu.add(aide);
	 
		
		
		
		barreMenu.add(fichier);
		barreMenu.add(simulation);
		barreMenu.add(aideMenu);	
		return barreMenu;
	}
}