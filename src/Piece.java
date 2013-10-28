import java.util.Vector;

public class Piece {

 
  private String nom;
  private double tExigee; 
  private boolean lExigeeEclairee;
  private double consoTotale;
  private double quantiteConsoTotale;  
  private IndicateurLuminosite indicateurLuminosite;
  private Thermometre thermometre;
  private SourceEnergie sourceEnergie;
  private Vector<Appareil> appareil;
  private Vector<Ampoule> ampoule;
  private Vector<Radiateur> radiateur;
  private Vector<Fenetre> fenetre;
  private Vector<Habitant> habitant;
 

  public Piece(){
	  
  }


// constructeur avec tous les champs
  
public Piece(String nom, Vector<Appareil> appareil, Vector<Ampoule> ampoule,
		Vector<Radiateur> radiateur, Vector<Fenetre> fenetre,
		Vector<Habitant> habitant, SourceEnergie sourceEnergie, double tExigee, 
		IndicateurLuminosite indicateurLuminosite, Thermometre thermometre,boolean lExigeeEclairee) {
	super();
	this.nom = nom; 
	this.indicateurLuminosite = indicateurLuminosite;
	this.thermometre = thermometre;
	this.sourceEnergie = sourceEnergie;
	this.appareil = appareil;
	this.ampoule = ampoule;
	this.radiateur = radiateur;
	this.fenetre = fenetre;
	this.habitant = habitant;
	this.tExigee = tExigee;
	this.lExigeeEclairee = lExigeeEclairee;
	
}


// constructeurs avec tous les champs, saufs les capteurs

public Piece(String nom, Vector<Appareil> appareil, Vector<Ampoule> ampoule,
		Vector<Radiateur> radiateur, Vector<Fenetre> fenetre,
		Vector<Habitant> habitant, SourceEnergie sourceEnergie, double tExigee) {
	super();
	this.nom = nom;
	this.appareil = appareil;
	this.ampoule = ampoule;
	this.radiateur = radiateur;
	this.fenetre = fenetre;
	this.habitant = habitant;
	this.sourceEnergie = sourceEnergie;
	this.tExigee = tExigee;
	 
}


 

public synchronized double getConsoTotale() {
	return consoTotale;
}


public synchronized void setConsoTotale(double consoTotale) {
	this.consoTotale = consoTotale;
}


public synchronized double getQuantiteConsoTotale() {
	return quantiteConsoTotale;
}



public synchronized boolean islExigeeEclairee() {
	return lExigeeEclairee;
}


public synchronized void setlExigeeEclairee(boolean lExigeeEclairee) {
	this.lExigeeEclairee = lExigeeEclairee;
}


public synchronized void setQuantiteConsoTotale(double quantiteConsoTotale) {
	this.quantiteConsoTotale = quantiteConsoTotale;
}


public synchronized String getNom() {
	return nom;
}


public synchronized void setNom(String nom) {
	this.nom = nom;
}

 

public synchronized SourceEnergie getSourceEnergie() {
	return sourceEnergie;
}


public synchronized void setSourceEnergie(SourceEnergie sourceEnergie) {
	this.sourceEnergie = sourceEnergie;
}


public synchronized double gettExigee() {
	return tExigee;
}


public synchronized void settExigee(double tExigee) {
	this.tExigee = tExigee;
}


public synchronized IndicateurLuminosite getIndicateurLuminosite() {
	return indicateurLuminosite;
}


public synchronized void setIndicateurLuminosite(IndicateurLuminosite indicateurLuminosite) {
	this.indicateurLuminosite = indicateurLuminosite;
}


public synchronized Thermometre getThermometre() {
	return thermometre;
}


public synchronized void setThermometre(Thermometre thermometre) {
	this.thermometre = thermometre;
}


public synchronized Vector<Appareil> getAppareil() {
	return appareil;
}


public synchronized void setAppareil(Vector<Appareil> appareil) {
	this.appareil = appareil;
}


public synchronized Vector<Ampoule> getAmpoule() {
	return ampoule;
}


public synchronized void setAmpoule(Vector<Ampoule> ampoule) {
	this.ampoule = ampoule;
}


public synchronized Vector<Radiateur> getRadiateur() {
	return radiateur;
}


public synchronized void setRadiateur(Vector<Radiateur> radiateur) {
	this.radiateur = radiateur;
}


public synchronized Vector<Fenetre> getFenetre() {
	return fenetre;
}


public synchronized void setFenetre(Vector<Fenetre> fenetre) {
	this.fenetre = fenetre;
}


public synchronized Vector<Habitant> getHabitant() {
	return habitant;
}


public synchronized void setHabitant(Vector<Habitant> habitant) {
	this.habitant = habitant;
}


@Override
public String toString() {
	return "Piece [ampoule=" + ampoule + ", appareil=" + appareil
			+ ", consoTotale=" + consoTotale + ", fenetre=" + fenetre
			+ ", habitant=" + habitant + ", indicateurLuminosite="
			+ indicateurLuminosite 
			+ ", lExigeeEclairee=" + lExigeeEclairee + ", nom=" + nom
			+ ", quantiteConsoTotale=" + quantiteConsoTotale + ", radiateur="
			+ radiateur + ", sourceEnergie=" + sourceEnergie + ", tExigee="
			+ tExigee + ", thermometre=" + thermometre + "]";
}


 

 
  
  
}  