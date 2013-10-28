import java.util.Vector;

public class Habitant {

  private boolean estPresent;
  private String nom;

  

  public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public void ouvrirFenetre() {
  }

  public void fermeFenetre(){
	  
  }
	  
 
public Habitant(){
	
}

public Habitant(boolean estPresent, String nom) {
	this.estPresent = estPresent;
	this.nom = nom;
}

public boolean isEstPresent() {
	return estPresent;
}

public void setEstPresent(boolean estPresent) {
	this.estPresent = estPresent;
}

 
public void exigerTemperature(double d) {
  }

  public void exigerLumiere(boolean b){
  }

@Override
public String toString() {
	return "Habitant [estPresent=" + estPresent + ", nom=" + nom + "]";
}

}