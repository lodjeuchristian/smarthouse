public class Fenetre {

  private String nom;
  private boolean ouvert;

  private boolean voletOuvert;

public Fenetre(){
	
}
 
public Fenetre(boolean ouvert, boolean voletOuvert, String nom) {

		this.ouvert = ouvert;
		this.voletOuvert = voletOuvert;
		this.nom = nom;
}

  
public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

 
public boolean isOuvert() {
	return ouvert;
}


public void setOuvert(boolean ouvert) {
	this.ouvert = ouvert;
}


public boolean isVoletOuvert() {
	return voletOuvert;
}


public void setVoletOuvert(boolean voletOuvert) {
	this.voletOuvert = voletOuvert;
}
 
public String toString() {
	return "Fenetre [nom=" + nom + ", ouvert=" + ouvert + ", voletOuvert="
			+ voletOuvert + "]";
}




   

}