public class Thermometre{

  private double valeurInterieur;
  private double valeurExterieur;

  
  
 
public Thermometre(){
	
}
  
public Thermometre(double valeurInterieur, double valeurExterieur) {
	super();
	this.valeurInterieur = valeurInterieur;
	this.valeurExterieur = valeurExterieur;
}





public synchronized double getValeurInterieur() {
	return valeurInterieur;
}





public synchronized void setValeurInterieur(double valeurInterieur) {
	this.valeurInterieur = valeurInterieur;
}





public synchronized double getValeurExterieur() {
	return valeurExterieur;
}





public synchronized void setValeurExterieur(double valeurExterieur) {
	this.valeurExterieur = valeurExterieur;
}





public String toString() {
	return "Thermometre [valeurExterieur=" + valeurExterieur
			+ ", valeurInterieur=" + valeurInterieur + "]";
}
  
  
  

}