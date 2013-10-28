import java.util.Vector;

public class Appareil {

  private String nomApp;

  private double consoApp;

  private boolean enMarche;
  
  private double quantiteConso;

  
  


public Appareil(){
	
}

public Appareil(String nomApp, double consoApp, boolean enMarche) {
	this.nomApp = nomApp;
	this.consoApp = consoApp;
	this.enMarche = enMarche;
}
 

public synchronized String getNomApp() {
	return nomApp;
}

public synchronized void setNomApp(String nomApp) {
	this.nomApp = nomApp;
}

public synchronized double getConsoApp() {
	return consoApp;
}

public synchronized double getQuantiteConso() {
	return quantiteConso;
}

public synchronized void setQuantiteConso(double quantiteConso) {
	this.quantiteConso = quantiteConso;
}

public synchronized void setConsoApp(double consoApp) {
	this.consoApp = consoApp;
}

public synchronized boolean isEnMarche() {
	return enMarche;
}

public synchronized void setEnMarche(boolean enMarche) {
	this.enMarche = enMarche;
}

@Override
public String toString() {
	return "Appareil [consoApp=" + consoApp + ", enMarche=" + enMarche
			+ ", nomApp=" + nomApp + ", quantiteConso=" + quantiteConso + "]";
}

 

}