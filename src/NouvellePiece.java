import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class NouvellePiece extends JDialog {

	 
 
	private JLabel possedeHabitant; 
	private JLabel temExigee; 
	private JTextField txtTempExigee;
	private JTextField txtNbFenetres;
	private JLabel nbFenetres;
 
	private JRadioButton rbPossedeHabitant;
	private JRadioButton rbPossedePasHabitant;
	private ButtonGroup bgHabitant;
	
	private JTable tableauAppareil;
	private JTable tableauRadiateur;
	private JTable tableauAmpoule;
	private Vector<String> ligne;
	private  Vector<Vector<String>> elementsTableauAppareil;
	private Vector<String> titreTableauAppareil;
	private  Vector<Vector<String>> elementsTableauAmpoule;
	private Vector<String> titreTableauAmpoule;
	private  Vector<Vector<String>> elementsTableauRadiateur;
	private Vector<String> titreTableauRadiateur;
	private Vector<Fenetre> fenetre;
	private Vector <Appareil> appareil;
	private Vector <Ampoule> ampoule;
	private Vector <Radiateur> radiateur;
	private Vector <Habitant> habitant;
	private SourceEnergie sourceEnergie;
	public Piece p;
	private int error1 = 0, error2 = 0, error3 = 0, error4 =0;

 
	
 
	public NouvellePiece(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(550, 650);
		Image icone = Toolkit.getDefaultToolkit().getImage("logo.png");
		this.setIconImage(icone);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
 
	public void showZDialog(){
		this.setVisible(true);		
		 	
	}
	
	public void centrerTable(JTable table) {    
		  DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		  custom.setHorizontalAlignment(JLabel.CENTER); 
		  for (int i=0 ; i<table.getColumnCount() ; i++) 
		  table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	}
	
	public boolean isNumeric(String s){
		if(s.indexOf(".")!=s.lastIndexOf(".")){return false;}
		if(s.startsWith(".")){return false;}
		if(s.endsWith(".")){return false;}
		for (int i=0; i<s.length();i++){
			int ascii = (int)s.charAt(i);
			if(ascii<48 || ascii>57 && s.charAt(i)!='.'){return false;}
		}
		return true;
	}
	
	private void initComponent(){
 
		//fenetre
		JPanel panFenetre = new JPanel();
		panFenetre.setLayout(new FlowLayout(FlowLayout.LEFT));
		panFenetre.setBackground(Color.white);
		panFenetre.setBorder(BorderFactory.createTitledBorder("Fenêtres"));
		panFenetre.setPreferredSize(new Dimension(480, 60));
		nbFenetres = new JLabel("Nombre de Fenêtre (au plus 2) : ");
		txtNbFenetres = new JTextField("1");
		txtNbFenetres.setPreferredSize(new Dimension(70,23));
		panFenetre.add(nbFenetres);
		panFenetre.add(txtNbFenetres);
		
		//temperature
		JPanel panTemperature = new JPanel();
		panTemperature.setLayout(new FlowLayout(FlowLayout.LEFT));
		panTemperature.setBackground(Color.white);
		panTemperature.setBorder(BorderFactory.createTitledBorder("Température"));
		panTemperature.setPreferredSize(new Dimension(480, 60));
		temExigee = new JLabel("Température exigée : ");
		txtTempExigee = new JTextField("27");
		txtTempExigee.setPreferredSize(new Dimension(70,23));
		panTemperature.add(temExigee);
		panTemperature.add(txtTempExigee);
		
		//ampoules 
		JPanel panAmpoule = new JPanel();
		panAmpoule.setLayout(new FlowLayout(FlowLayout.LEFT));
		panAmpoule.setBackground(Color.white);
		panAmpoule.setBorder(BorderFactory.createTitledBorder("Ampoules"));
		panAmpoule.setPreferredSize(new Dimension(480, 110));
		titreTableauAmpoule = new Vector<String>();
		titreTableauAmpoule.addElement("N° Ampoule");
		titreTableauAmpoule.addElement("Consommation (W/h)");
		titreTableauAmpoule.addElement("Etat");
		
		    elementsTableauAmpoule = new Vector<Vector<String>>();
		    ligne = new Vector<String>();
			ligne.addElement("Ampoule 1");
			ligne.addElement("1");
			ligne.addElement("Allumer");
			elementsTableauAmpoule.add(ligne);
		    ligne = new Vector<String>();
			ligne.addElement("Ampoule 2");
			ligne.addElement("");
			ligne.addElement("Allumer");
			elementsTableauAmpoule.add(ligne);
		    ligne = new Vector<String>();
			ligne.addElement("Ampoule 3");
			ligne.addElement("");
			ligne.addElement("Allumer");
			elementsTableauAmpoule.add(ligne);
		    ligne = new Vector<String>();
			ligne.addElement("Ampoule 4");
			ligne.addElement("");
			ligne.addElement("Allumer");
			elementsTableauAmpoule.add(ligne);
		
		tableauAmpoule = new JTable(elementsTableauAmpoule,titreTableauAmpoule);
		centrerTable(tableauAmpoule);
		panAmpoule.add(new JScrollPane(tableauAmpoule));
 
		//Appareil
	
		JPanel panAppareil = new JPanel();
		panAppareil.setLayout(new FlowLayout(FlowLayout.LEFT));
		panAppareil.setBackground(Color.white);
		panAppareil.setBorder(BorderFactory.createTitledBorder("Appareils"));
		panAppareil.setPreferredSize(new Dimension(480, 110));
		titreTableauAppareil = new Vector<String>();
		titreTableauAppareil.addElement("Nom Appareil");
		titreTableauAppareil.addElement("Consommation (W/h)");
		titreTableauAppareil.addElement("Etat");
		
		    elementsTableauAppareil = new Vector<Vector<String>>();
		    ligne = new Vector<String>();
			ligne.addElement("Refrigérateur");
			ligne.addElement("5");
			ligne.addElement("En marche");
			elementsTableauAppareil.add(ligne);
		    ligne = new Vector<String>();
			ligne.addElement("");
			ligne.addElement("");
			ligne.addElement("En marche");
			elementsTableauAppareil.add(ligne);
		    ligne = new Vector<String>();
			ligne.addElement("");
			ligne.addElement("");
			ligne.addElement("En marche");
			elementsTableauAppareil.add(ligne);
		    ligne = new Vector<String>();
			ligne.addElement("");
			ligne.addElement("");
			ligne.addElement("En marche");
			elementsTableauAppareil.add(ligne);
		
		tableauAppareil = new JTable(elementsTableauAppareil,titreTableauAppareil);
		centrerTable(tableauAppareil);
		panAppareil.add(new JScrollPane(tableauAppareil));		
		//Radiateur
	 	
		JPanel panRadiateur = new JPanel();
		panRadiateur.setLayout(new FlowLayout(FlowLayout.LEFT));
		panRadiateur.setBackground(Color.white);
		panRadiateur.setBorder(BorderFactory.createTitledBorder("Radiateurs"));
	 	panRadiateur.setPreferredSize(new Dimension(480, 110));
		titreTableauRadiateur = new Vector<String>();
		titreTableauRadiateur.addElement("N° Radiateur");
		titreTableauRadiateur.addElement("Consommation");
		titreTableauRadiateur.addElement("Emission(°C/h)");
		titreTableauRadiateur.addElement("Etat");
		
		    elementsTableauRadiateur = new Vector<Vector<String>>();
		    ligne = new Vector<String>();
			ligne.addElement("Radiateur 1");
			ligne.addElement("3");
			ligne.addElement("1");
			ligne.addElement("En marche");
			elementsTableauRadiateur.add(ligne);
		    ligne = new Vector<String>();
			ligne.addElement("Radiateur 2");
			ligne.addElement("");
			ligne.addElement("");
			ligne.addElement("En marche");
			elementsTableauRadiateur.add(ligne);
		    ligne = new Vector<String>();
			ligne.addElement("Radiateur 3");
			ligne.addElement("");
			ligne.addElement("");
			ligne.addElement("En marche");
			elementsTableauRadiateur.add(ligne);
		    ligne = new Vector<String>();
			ligne.addElement("Radiateur 4");
			ligne.addElement("");
			ligne.addElement("");
			ligne.addElement("En marche");
			elementsTableauRadiateur.add(ligne);
		
		tableauRadiateur = new JTable(elementsTableauRadiateur,titreTableauRadiateur);
		centrerTable(tableauRadiateur);
		panRadiateur.add(new JScrollPane(tableauRadiateur));
		
	 	
		//Habitant
		JPanel panHabitant = new JPanel();
		panHabitant.setLayout(new FlowLayout(FlowLayout.LEFT));
		panHabitant.setBackground(Color.white);
		panHabitant.setBorder(BorderFactory.createTitledBorder("Habitant"));
		panHabitant.setPreferredSize(new Dimension(480, 50));
	 
		bgHabitant = new ButtonGroup();
		rbPossedeHabitant = new JRadioButton("Oui");
		rbPossedeHabitant.setSelected(true);
		rbPossedePasHabitant = new JRadioButton("Non");
		possedeHabitant = new JLabel("Un habitant est-il présent dans la pièce ?");
		bgHabitant.add(rbPossedeHabitant);
		bgHabitant.add(rbPossedePasHabitant);
		panHabitant.add(possedeHabitant);
		panHabitant.add(rbPossedeHabitant);
		panHabitant.add(rbPossedePasHabitant);
 
		
		//Fin panels

		
		 
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panFenetre);
		content.add(panTemperature);
		content.add(panAmpoule);
		content.add(panAppareil);
		content.add(panRadiateur);
		content.add(panHabitant);
	 
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				
				JOptionPane jop = new JOptionPane();
			
				ampoule = new Vector<Ampoule>();
				for(int i=0; i<elementsTableauAmpoule.size(); i++){
					
					Vector<String> values = elementsTableauAmpoule.get(i);				 					
					String conso = values.get(1);
					String nom = values.get(0);
					
					if(!isNumeric(conso)){
						error1 = 0;
						jop.showMessageDialog(null, "Toutes les consommations sont des valeurs numériques", "Information", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
					if(!conso.equals("")){
						error1 = 1;
						double d = new Double(conso).doubleValue();
						ampoule.add(new Ampoule(d,true, nom));
					}
		 	 
				}

				radiateur = new Vector<Radiateur>();
				for(int i=0; i<elementsTableauRadiateur.size(); i++){
				 	
					Vector<String> values = elementsTableauRadiateur.get(i);				 					
					String conso = values.get(1);
					String emission = values.get(2);
					String nom = values.get(0);
					
				 	if(!isNumeric(conso) || ! isNumeric(emission)){
					 	jop.showMessageDialog(null, "Toutes les consommations et emissions sont des valeurs numériques", "Information", JOptionPane.ERROR_MESSAGE);
					 	error2 = 0;
						return;
					}
					else
					if(!conso.equals("") && !emission.equals("")){
						error2 = 1;
						double d = new Double(conso).doubleValue();
						double e = new Double(emission).doubleValue();
					 	radiateur.add(new Radiateur(d,e,true,nom));
				 	}
		 
						
				 
				}
				
				habitant = new Vector<Habitant>();
				if(rbPossedeHabitant.isSelected()){
					habitant.add(new Habitant(true, "Habitant 1"));
				}
				else{
					habitant.add(new Habitant(false, "Habitant 1"));
				}
				

				appareil = new Vector<Appareil>();
				for(int i=0; i<elementsTableauAppareil.size(); i++){
					
					Vector<String> values = elementsTableauAppareil.get(i);				 					
					String nom = values.get(0);
					String conso = values.get(1);
					
					if(!isNumeric(conso)){
					 	jop.showMessageDialog(null, "Toutes les consommations sont des valeurs numériques", "Information", JOptionPane.ERROR_MESSAGE);
					 	error3 = 0;
						return;
					}
					else
					if(!conso.equals("") && !nom.equals("")){
						error3 = 1;
						double d = new Double(conso).doubleValue();
						appareil.add(new Appareil(nom,d,true));
					}
		 
	 
				 
				}

				fenetre = new Vector<Fenetre>();
				int nb = 1;
                if(!txtNbFenetres.getText().equals("")){
                	String nbF = txtNbFenetres.getText();
                	if(!isNumeric(nbF)){
                		jop.showMessageDialog(null, "Le nombre de fenêtre doit être une valeur numérique", "Information", JOptionPane.ERROR_MESSAGE);
                		error4 = 0;
                		return;	
                	}
                	else{
                		nb = new Double(txtNbFenetres.getText()).intValue();
                	}
                }
                
			 
				for(int i=0; i< ((nb<3)?nb:2); i++){
					error4 = 1;
					int tmp = i+1;
					fenetre.add(new Fenetre(false,false,"Fenetre "+tmp));
									 
				}
 
		 
			
		       	if(error1 == 1 && error2 == 1 && error3 == 1 && error4 == 1){
					//instanciation finale de la pièce
			 		int numpiece = Maison.getInstance().getPiece().size();
		       		String nomPiece = "Pièce N° "+numpiece;		      
		       		double temExi = new Double(txtTempExigee.getText()).doubleValue();
		       		sourceEnergie = new SourceEnergie("Solaire",500);
		       		IndicateurLuminosite il = new IndicateurLuminosite(false,false);
		       		Thermometre th = new Thermometre(10,10); 
		       		p = new Piece(nomPiece,appareil,ampoule,radiateur,fenetre,habitant,sourceEnergie,temExi,il,th,true);
					Maison.getInstance().addPiece(p);

					System.out.println(p);
					setVisible(false); 
			 	}
 
			}
		});
		
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		
		 
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	 
	 
	}
}
 

