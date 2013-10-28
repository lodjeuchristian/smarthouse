import java.util.Vector;

//Utilisation du Pattern Singleton

public final class Maison {
	 
     
    private static volatile Maison instance = null;
    private Vector<Piece> piece = new Vector<Piece>(); 
    private int nbPiece = 0; 
    private boolean ilPleut = false;
    public boolean isIlPleut() {
		return ilPleut;
	}


	private boolean play = true;
    private boolean stop = false;
    private int heure = 12;

 
    
    
    private Maison(){
    
    }
    
 
    public final static Maison getInstance() {
 
        if (Maison.instance == null) {
   
           synchronized(Maison.class) {
             if (Maison.instance == null) { 
            	 Maison.instance = new  Maison(); 
            }
           }
        }
        return Maison.instance;
    }

 

    public int getNbPiece() {
    	return nbPiece;
    }
 

	
	public void setNbPiece(int nbPiece) {
    	this.nbPiece = nbPiece;
    }



    public Vector<Piece> getPiece() {
    	return piece;
    }

    public Piece getPiece(int i) {
    	return piece.get(i);
    }


    public void setPiece(Vector<Piece> piece) {
    	this.piece = piece;
    }


    public void addPiece(Piece p){
    	piece.add(p);
    	nbPiece++;
    }
    
    public void removePiece(int i){
    	if(piece.size()>i){
    		piece.remove(i);
    		nbPiece--;
    	}
    }
    
    public void removeAllPiece(){
    	if(piece.size()>0)
    		piece.removeAllElements();
    		nbPiece = 0;
    }


	public boolean isPlay() {
		return play;
	}


	public void setPlay(boolean play) {
		this.play = play;
	}


	public boolean isStop() {
		return stop;
	}


	public void setStop(boolean stop) {
		this.stop = stop;
	}

	
	public int getHeure() {
		return heure;
	}


	public void setHeure(int heure) {
		this.heure = heure;
	}



	public void setIlPleut(boolean ilPleut) {
		this.ilPleut = ilPleut;
	}

	
	@Override
	public String toString() {
		return "Maison [heure=" + heure + ", ilPleut=" + ilPleut + ", nbPiece="
				+ nbPiece + ", piece=" + piece + ", play=" + play + ", stop="
				+ stop + "]";
	}


 
    
 
 
    

 

}

 