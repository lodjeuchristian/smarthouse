import java.util.Vector;

public class SourceEnergie {

  private String nomSource;
  private double quantite;
 
  
public SourceEnergie(){
	
}
 public SourceEnergie(String nomSource, double quantite) {
	super();
	this.nomSource = nomSource;
	this.quantite = quantite;
 
}


 
public synchronized String getNomSource() {
	return nomSource;
}
 

public synchronized void setNomSource(String nomSource) {
	this.nomSource = nomSource;
}




public synchronized double getQuantite() {
	return quantite;
}




public synchronized void setQuantite(double quantite) {
	this.quantite = quantite;
}
@Override
public String toString() {
	return "SourceEnergie [nomSource=" + nomSource + ", quantite=" + quantite
			+ "]";
}


 
  
  
  
  
  

    

}