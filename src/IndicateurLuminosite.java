import java.util.Vector;

public class IndicateurLuminosite{

	private boolean interieurEclairer;
	private boolean exterieurJour;
   

	
	public IndicateurLuminosite(boolean interieurEclairer, boolean exterieurJour) {
		super();
		this.interieurEclairer = interieurEclairer;
		this.exterieurJour = exterieurJour;
	}



	public synchronized boolean isInterieurEclairer() {
		return interieurEclairer;
	}



	public synchronized void setInterieurEclairer(boolean interieurEclairer) {
		this.interieurEclairer = interieurEclairer;
	}



	public synchronized boolean isExterieurJour() {
		return exterieurJour;
	}



	public synchronized void setExterieurJour(boolean exterieurJour) {
		this.exterieurJour = exterieurJour;
	}



	public String toString() {
		return "IndicateurLuminosite [exterieurJour=" + exterieurJour
				+ ", interieurEclairer=" + interieurEclairer + "]";
	}
	
	
 
 

}