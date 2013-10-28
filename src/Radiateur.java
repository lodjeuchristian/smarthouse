public class Radiateur extends Appareil {

  private double degreParHeure;

  
  
  public Radiateur(){
	  
  }
  
  public Radiateur(double consoApp, double emission,  boolean enMarche, String nom) {
		super(nom, consoApp, enMarche);
		this.degreParHeure = emission;
	 
	}
 
public String toString() {
	return "Radiateur [degreParHeure=" + degreParHeure + ", getConsoApp()="
			+ getConsoApp() + ", getNomApp()=" + getNomApp()
			+ ", isEnMarche()=" + isEnMarche() + "]";
}



public synchronized double getDegreParHeure() {
	return degreParHeure;
}



public synchronized void setDegreParHeure(double degreParHeure) {
	this.degreParHeure = degreParHeure;
}





}