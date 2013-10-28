import java.awt.*;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
 
import javax.imageio.ImageIO;
import javax.swing.*;

public class Draw extends JPanel implements Runnable{
	private int nbP;
	private Vector<Piece> pieces;
	private int maisonx;
	private int maisony;
	
	private int nbFenetres;
	private int nbAmpoules;
	private int nbAppareils;
	private int nbRadiateurs;
	private boolean habitantPresent;
	
 
	public int getNbP() {
		return nbP;
	}
	
	public void setNbP(int nbP) {
		this.nbP = nbP;
	}
 
	public Vector<Piece> getPieces() {
		return pieces;
	} 
	
	public void setPieces(Vector<Piece> pieces) {
		this.pieces = pieces;
	}

	public boolean ilFaitJour(){ 
		return  Maison.getInstance().getPiece().get(0).getIndicateurLuminosite().isExterieurJour();
	 }
	
	public boolean ilPleut(){ 
		return  Maison.getInstance().isIlPleut(); 
	}
	
	public void run(){
		
		while(true){ 
			this.repaint();
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) { 	 
				e.printStackTrace();
			}
		}
	}
	public void paintComponent(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		int wScreen = this.getWidth();
		int hScreen = this.getHeight();
		int w = wScreen/3;
		int h = hScreen /2;
		int x = wScreen/3;
        int y = hScreen/4;	
			
			
		if(Maison.getInstance().getPiece().size() == 0){
		    g2d.setStroke(new BasicStroke(4));
		    g2d.setPaint(Color.BLACK);
			g2d.fillRect(0,0, this.getWidth(), this.getHeight());
		}
		else
		if(Maison.getInstance().getPiece().size() == 1){ 
			
			//dessin de l'extérieur 
			if(ilFaitJour() && ilPleut()){
			    try {
			    	System.out.println("IL FAIT JOUR ET IL PLEUT");
	                Image img = ImageIO.read(new File("pluiejour.png"));
	                g2d.setPaint(Color.WHITE);
	                g.drawImage(img, 0,0,this.getWidth(),this.getHeight(), null);

			    } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
			    }	
			}
			else
			if(!ilFaitJour() && ilPleut()){
			    try {
			    	System.out.println("IL FAIT NUIT ET IL PLEUT");
	                Image img = ImageIO.read(new File("pluienuit.png"));
	                g2d.setPaint(Color.WHITE);
	                g.drawImage(img, 0,0,this.getWidth(),this.getHeight(), null);

			    } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
			    }		
			}
			else
			if(ilFaitJour() && !ilPleut()){
				System.out.println("IL FAIT JOUR ET IL NE PLEUT PAS");
			    g2d.setPaint(Color.WHITE);
				g2d.fillRect(0,0, this.getWidth(), this.getHeight()-20);						
			}
			else
			if(!ilFaitJour() && !ilPleut()){
				System.out.println("IL FAIT NUIT ET IL NE PLEUT PAS");
			    g2d.setPaint(Color.DARK_GRAY);
				g2d.fillRect(0,0, this.getWidth(), this.getHeight()-20);							
			}
			 
			
		    //tracé de la barre d'état
		    g2d.setStroke(new BasicStroke(1));
		    g2d.setPaint(Color.BLACK);
		    g2d.drawRect(0,hScreen - 20,wScreen,20);
	    	Font font1 = new Font("GEORGIA",1, 12); 
            g.setFont(font1);  
            
		    if(Maison.getInstance().isStop()){
		    	g.setColor(Color.LIGHT_GRAY);
		    	g2d.fillRect(0,hScreen - 22,wScreen,20);
			    g2d.setPaint(Color.BLACK);
			    g2d.drawRect(0,hScreen - 22,wScreen,20);
		    	g.setColor(Color.RED);
		    	g.drawString("Arrêtée",4,hScreen - 8);    	
		    }
		    else
		    if(Maison.getInstance().isPlay()){
		    	g.setColor(Color.LIGHT_GRAY);
		    	g2d.fillRect(0,hScreen - 22,wScreen,20);
			    g2d.setPaint(Color.BLACK);
			    g2d.drawRect(0,hScreen - 22,wScreen,20);
			    g2d.setPaint(Color.BLACK);
		    	g.drawString("En cours",4,hScreen - 8);
		    }
		    else
		    if(!Maison.getInstance().isPlay()){ 
		    	g.setColor(Color.LIGHT_GRAY);
		    	g2d.fillRect(0,hScreen - 22,wScreen,20);
			    g2d.setPaint(Color.BLACK);
			    g2d.drawRect(0,hScreen - 22,wScreen,20);
		    	g.setColor(Color.RED);
		    	g.drawString("En pause...",4,hScreen - 8);	    	
		    }
 
		    
						
			nbFenetres = this.pieces.get(0).getFenetre().size();
			nbAmpoules = this.pieces.get(0).getAmpoule().size();
			nbAppareils = this.pieces.get(0).getAppareil().size();
			nbRadiateurs = this.pieces.get(0).getRadiateur().size();
			habitantPresent = this.pieces.get(0).getHabitant().get(0).isEstPresent();

			 
		 
		// 	this.pieces.get(0).getRadiateur().get(0).setEnMarche(false);
		 //	this.pieces.get(0).getAppareil().get(1).setEnMarche(false);
		// 	this.pieces.get(0).getAppareil().get(2).setEnMarche(false);
		// 	this.pieces.get(0).getAppareil().get(3).setEnMarche(false);
		//	this.pieces.get(0).getAppareil().get(0).setEnMarche(false);
		  
		// 	this.pieces.get(0).getRadiateur().get(1).setEnMarche(false);
		 //	this.pieces.get(0).getRadiateur().get(2).setEnMarche(false);
		 //	this.pieces.get(0).getRadiateur().get(3).setEnMarche(false);
		 //	this.pieces.get(0).getAmpoule().get(0).setEnMarche(false);
		//	this.pieces.get(0).getAmpoule().get(1).setEnMarche(false);
		//	this.pieces.get(0).getAmpoule().get(2).setEnMarche(false);
		//	this.pieces.get(0).getAmpoule().get(3).setEnMarche(false);
		//	this.pieces.get(0).getFenetre().get(0).setVoletOuvert(true);
		//	this.pieces.get(0).getFenetre().get(0).setOuvert(true);
		//	this.pieces.get(0).getFenetre().get(1).setVoletOuvert(true);
		//	this.pieces.get(0).getFenetre().get(1).setOuvert(true); 
			
	        	Rectangle2D.Double piece1 = new Rectangle2D.Double(x,y,w,w);
	        	this.maisonx = x;
	        	this.maisony = y;

			    
	
	            
	            //tracé du conteneur principal
			    g2d.setStroke(new BasicStroke(4));
			    boolean etatAmpoules = this.pieces.get(0).getAmpoule().get(0).isEnMarche();
			    if(etatAmpoules == true){
			    	if(this.pieces.get(0).getAmpoule().size() == 1)
			    		g2d.setPaint(Color.getHSBColor(100,0,30));
			    	else
			    	if(this.pieces.get(0).getAmpoule().size() == 2)
			    		g2d.setPaint(Color.getHSBColor(100,0,20));
			    	else
				    if(this.pieces.get(0).getAmpoule().size() == 3)
				    	g2d.setPaint(Color.getHSBColor(100,0,10));
				    else
				    if(this.pieces.get(0).getAmpoule().size() == 4)
				    	g2d.setPaint(Color.WHITE);
			    }
			    else{
			    	g2d.setPaint(Color.GRAY);
			    }
			    g2d.fill(piece1);
		    	Font fontm = new Font("courrier", Font.CENTER_BASELINE, w/30);
	            g.setFont(fontm); g.setColor(Color.RED); 
	            String nomPiece = this.pieces.get(0).getNom(); 
	            g.drawString(nomPiece, x,y);
	            
	            
			    //tracé du contour noir foncé
			    g2d.setPaint(Color.BLACK);
			    g2d.draw(piece1); 
			    
			    

			    
			    //tracé des fenetres
			    g2d.setStroke(new BasicStroke(4));
			    g2d.setPaint(Color.BLACK);
			    if(nbFenetres == 1){
			    	//si les volet  et la fenetre sont fermé
			    	if(!this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(0).isOuvert()){
					    g2d.setPaint(Color.GRAY);
					    g2d.setStroke(new BasicStroke(2));
					    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - 4,w/4,8);
					    g2d.draw(fenetre2);	
			    	}
			    	else
			    	//si les volets sont ouverts et la fenetre fermée
			    	if(this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(0).isOuvert()){
			    	    g2d.setPaint(Color.GRAY);
					    g2d.setStroke(new BasicStroke(1));
					    Rectangle2D.Double fenetre1 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
					    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
					    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
					    g2d.draw(fenetre1);
					    g2d.draw(fenetre2);	
					    g2d.setStroke(new BasicStroke(2));
					    g2d.draw(fenetre3);	
			    	}
			    	else
			    	//si les volets sont ouverts et la fenetre ouverte
			    	if(this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && this.pieces.get(0).getFenetre().get(0).isOuvert()){
			    	    
					    g2d.setStroke(new BasicStroke(1));
					    Rectangle2D.Double fenetre1 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
					    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
					    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony,4,w/11);
					    Rectangle2D.Double fenetre4 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,4,w/11);
					    Rectangle2D.Double fenetre5 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
					    g2d.setPaint(Color.GRAY);
					    g2d.draw(fenetre1);
					    g2d.draw(fenetre2);
					    g2d.draw(fenetre3);	
					    g2d.draw(fenetre4);	
					    g2d.setPaint(Color.WHITE);
					    g2d.setStroke(new BasicStroke(4));
					    g2d.draw(fenetre5);
					}
			   		  
			    }
			    else
			    if(nbFenetres == 2){
			    
			    	if(!this.pieces.get(0).getFenetre().get(1).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(1).isOuvert()){
					    g2d.setPaint(Color.GRAY);
					    g2d.setStroke(new BasicStroke(2));
					    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75),maisony - 4+w,w/4,8);
					    g2d.draw(fenetre2);	
			    	}
			    	else
			    	//si les volets sont ouverts et la fenetre fermée
			    	if(this.pieces.get(0).getFenetre().get(1).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(1).isOuvert()){
			    	    g2d.setPaint(Color.GRAY);
					    g2d.setStroke(new BasicStroke(1));
					    Rectangle2D.Double fenetre0 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5+w,4,w/11);
					    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5+w,4,w/11);
					    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony+w,w/4,4);
					    g2d.draw(fenetre0);
					    g2d.draw(fenetre2);	
					    g2d.setStroke(new BasicStroke(2));
					    g2d.draw(fenetre3);	
			    	}
			    	else
			    	//si les volets sont ouverts et la fenetre ouverte
			    	if(this.pieces.get(0).getFenetre().get(1).isVoletOuvert() && this.pieces.get(0).getFenetre().get(1).isOuvert()){
			    	    
					    g2d.setStroke(new BasicStroke(1));
					    Rectangle2D.Double fenetre00 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5+w,4,w/11);
					    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5+w,4,w/11);
					    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony+w,4,w/11);
					    Rectangle2D.Double fenetre4 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony+w,4,w/11);
					    Rectangle2D.Double fenetre5 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony+w,w/4,4);
					    g2d.setPaint(Color.GRAY);
					    g2d.draw(fenetre00);
					    g2d.draw(fenetre2);
					    g2d.draw(fenetre3);	
					    g2d.draw(fenetre4);	
					    g2d.setPaint(Color.WHITE);
					    g2d.setStroke(new BasicStroke(4));
					    g2d.draw(fenetre5);
					}	
				    
				    
				    //fenetre1
			    	//si les volet  et la fenetre sont fermé
			    	if(!this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(0).isOuvert()){
					    g2d.setPaint(Color.GRAY);
					    g2d.setStroke(new BasicStroke(2));
					    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - 4,w/4,8);
					    g2d.draw(fenetre2);	
			    	}
			    	else
			    	//si les volets sont ouverts et la fenetre fermée
			    	if(this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(0).isOuvert()){
			    	    g2d.setPaint(Color.GRAY);
					    g2d.setStroke(new BasicStroke(1));
					    Rectangle2D.Double fenetre0 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
					    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
					    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
					    g2d.draw(fenetre0);
					    g2d.draw(fenetre2);	
					    g2d.setStroke(new BasicStroke(2));
					    g2d.draw(fenetre3);	
			    	}
			    	else
			    	//si les volets sont ouverts et la fenetre ouverte
			    	if(this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && this.pieces.get(0).getFenetre().get(0).isOuvert()){
			    	    
					    g2d.setStroke(new BasicStroke(1));
					    Rectangle2D.Double fenetre00 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
					    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
					    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony,4,w/11);
					    Rectangle2D.Double fenetre4 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,4,w/11);
					    Rectangle2D.Double fenetre5 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
					    g2d.setPaint(Color.GRAY);
					    g2d.draw(fenetre00);
					    g2d.draw(fenetre2);
					    g2d.draw(fenetre3);	
					    g2d.draw(fenetre4);	
					    g2d.setPaint(Color.WHITE);
					    g2d.setStroke(new BasicStroke(4));
					    g2d.draw(fenetre5);
					}	
			    }
	 
			    
			   //Tracé du personnage 
			    if(habitantPresent == true){
				    try {
		                Image img = ImageIO.read(new File("personne1.png"));
		                g.drawImage(img, x+w/4, y+w/2, w/15,w/10, this);
		                //Pour une image de fond
		                //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				    } catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
				    }
			    }
			    else{
				    try {
		                Image img = ImageIO.read(new File("personne1.png"));
		                g.drawImage(img, x-w/4, y+w/2, w/15,w/10, this);
		                //Pour une image de fond
		                //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				    } catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
				    }
			    }
			    
			    //tracé des l'ampoules
			    try {
			    	Font font = new Font("courrier", Font.CENTER_BASELINE, w/35);
	                   
	                if(nbAmpoules == 1){
	                	if(this.pieces.get(0).getAmpoule().get(0).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE); 
			                Image img1 = ImageIO.read(new File("ampoule2.png"));  
			                g.drawImage(img1, x+w/2-w/14, y+w/3+w/8, w/10,w/12, this);      
			                g.drawString("Amp1", x+w/2-w/14, y+w/3+w/8);
	                	}
	                	else{
	                		g.setFont(font); g.setColor(Color.DARK_GRAY); 
	    	                g.fillOval(x+w/2-w/18, y+w/3+w/8, w/14,w/14);      
			                g.drawString("Amp1", x+w/2-w/18, y+w/3+w/8);                		
	                	}
	                }
	                else
	                if(nbAmpoules == 2){
	                	if(this.pieces.get(0).getAmpoule().get(0).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE); 
		                    Image img1 = ImageIO.read(new File("ampoule2.png"));
			                g.drawImage(img1, x+w/2-w/14, y+w/4, w/10,w/12, this);      
			                g.drawString("Amp1", x+w/2-w/14, y+w/4);
	                	}
	                	else{
	                		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			                g.fillOval(x+w/2-w/18, y+w/4, w/14,w/14);      
			                g.drawString("Amp1", x+w/2-w/18, y+w/4);               		
	                	}
	                	if(this.pieces.get(0).getAmpoule().get(1).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE); 
		            	    Image img2 = ImageIO.read(new File("ampoule2.png"));
		                    g.drawImage(img2, x+w/2-w/14, y+w/2+w/8, w/10,w/12, this);
		                    g.drawString("Amp2", x+w/2-w/14, y+w/2+w/8);
	                	}
	                	else{
	                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			                g.fillOval(x+w/2-w/18, y+w/2+w/8, w/14,w/14);      
			                g.drawString("Amp2", x+w/2-w/18, y+w/2+w/8);                		
	                	}
	                } 
	                else
	                if(nbAmpoules == 3){
	                	if(this.pieces.get(0).getAmpoule().get(0).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE); 
	                		Image img1 = ImageIO.read(new File("ampoule2.png"));
			                g.drawImage(img1, x+w/2-w/14, y+w/7, w/10,w/12, this);      
			                g.drawString("Amp1", x+w/2-w/14, y+w/7);
	                	}
	                	else{
	                		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			                g.fillOval(x+w/2-w/18, y+w/7, w/14,w/14);      
			                g.drawString("Amp1", x+w/2-w/18, y+w/7);      		
	                	}
	                	if(this.pieces.get(0).getAmpoule().get(1).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE);
		            	    Image img2 = ImageIO.read(new File("ampoule2.png"));
		                    g.drawImage(img2, x+w/2-w/14, y+w/3+w/10, w/10,w/12, this);
		                    g.drawString("Amp2", x+w/2-w/14, y+w/3+w/10);
	                	}
	                	else{
	                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			                g.fillOval(x+w/2-w/18, y+w/3+w/10, w/14,w/14);      
			                g.drawString("Amp2", x+w/2-w/18, y+w/3+w/10);               		
	                	}
	                	if(this.pieces.get(0).getAmpoule().get(2).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE);
		                	Image img3 = ImageIO.read(new File("ampoule2.png"));
		                    g.drawImage(img3, x+w/2-w/14, y+w/2 + w/4, w/10,w/12, this);
		                    g.drawString("Amp3", x+w/2-w/14,  y+w/2 + w/4);   
	                	}
	                	else{
	                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			                g.fillOval(x+w/2-w/18, y+w/2 + w/4, w/14,w/14);      
			                g.drawString("Amp3", x+w/2-w/18, y+w/2 + w/4); 	
	                	}
	                }
	                else
	                if(nbAmpoules == 4){
	                	if(this.pieces.get(0).getAmpoule().get(0).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE);
		                    Image img1 = ImageIO.read(new File("ampoule2.png"));
			                g.drawImage(img1, x+w/2-w/14, y+w/9, w/10,w/12, this);      
			                g.drawString("Amp1", x+w/2-w/14, y+w/9);
	                	}
	                	else{
	                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			                g.fillOval(x+w/2-w/18,  y+w/9, w/14,w/14);      
			                g.drawString("Amp1", x+w/2-w/18,  y+w/9);        		
	                	}
	                	if(this.pieces.get(0).getAmpoule().get(1).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE);
		            	    Image img2 = ImageIO.read(new File("ampoule2.png"));
		                    g.drawImage(img2, x+w/2-w/14, y+w/4+w/10, w/10,w/12, this);
		                    g.drawString("Amp2", x+w/2-w/14, y+w/4+w/10);
	                	}
	                	else{
	                   		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			                g.fillOval(x+w/2-w/18, y+w/4+w/10, w/14,w/14);      
			                g.drawString("Amp2", x+w/2-w/18, y+w/4+w/10);    		
	                	}
	                	if(this.pieces.get(0).getAmpoule().get(2).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE);
		                	Image img3 = ImageIO.read(new File("ampoule2.png"));
		                    g.drawImage(img3, x+w/2-w/14, y+w/2 + w/10, w/10,w/12, this);
		                    g.drawString("Amp3", x+w/2-w/14, y+w/2 + w/10); 
	                	}
	                	else{
	                   		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			                g.fillOval(x+w/2-w/18, y+w/2 + w/10, w/14,w/14);      
			                g.drawString("Amp3", x+w/2-w/18, y+w/2 + w/10);  		
	                	}
	                	if(this.pieces.get(0).getAmpoule().get(3).isEnMarche()){
	                		g.setFont(font); g.setColor(Color.ORANGE);
		                    Image img4 = ImageIO.read(new File("ampoule2.png"));
		                    g.drawImage(img4, x+w/2-w/14, y+w/2+w/3, w/10,w/12, this);
		                    g.drawString("Amp4", x+w/2-w/14, y+w/2+w/3);
	                	}
	                	else{
	                 		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			                g.fillOval(x+w/2-w/18, y+w/2+w/3, w/14,w/14);      
			                g.drawString("Amp4", x+w/2-w/18,y+w/2+w/3);    		
	                	}
	                }
	 
			    } catch (IOException e) {
	 
	                e.printStackTrace();
			    }
			    
		
			    //tracé des radiateurs
			    try {
			    	Font font = new Font("courrier", Font.CENTER_BASELINE, w/35);
	                g.setFont(font); g.setColor(Color.BLUE);  
	                 
			    	if(nbRadiateurs ==1){
			    		String conso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getConsoApp()+"W/h";
			    		String  qteConso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getQuantiteConso()+"W";
			    		String dPH = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getDegreParHeure()+"°C/h";
			    		
		                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
		                g.drawImage(img1, x+w/20, y+w/9, w/10,w/8, this);	
		                
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad1", x+w/20, y+w/9);
		                if(this.pieces.get(0).getRadiateur().get(0).isEnMarche()){
		                	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
		                	g.setFont(font); g.setColor(Color.BLACK);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso,x+w/20+w/80,y+w/9+w/26);
			                g.drawString(dPH,x+w/20+w/80,y+w/9+w/13);
			                g.drawString(qteConso,x+w/20+w/80,y+w/9+w/9);
		                } 
			    		else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
		                	g.setFont(font); g.setColor(Color.DARK_GRAY);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso,x+w/20+w/80,y+w/9+w/26);
			                g.drawString(dPH,x+w/20+w/80,y+w/9+w/13);
			                g.drawString(qteConso,x+w/20+w/80,y+w/9+w/9);
			    		} 
			    			
			    		 
			    	}
			    	else
			    	if(nbRadiateurs ==2){
			     		String conso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getConsoApp()+"W/h";
			    		String  qteConso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getQuantiteConso()+"W";
			    		String dPH = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getDegreParHeure()+"°C/h";
			     		String conso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getConsoApp()+"W/h";
			    		String  qteConso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getQuantiteConso()+"W";
			    		String dPH2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getDegreParHeure()+"°C/h";
			    		
		                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
		                g.drawImage(img1, x+w/20, y+w/9, w/10,w/8, this);	
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad1", x+w/20, y+w/9);
		                if(this.pieces.get(0).getRadiateur().get(0).isEnMarche()){
		                  	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
		                	g.setFont(font); g.setColor(Color.BLACK);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso,x+w/20,y+w/9+w/26);
			                g.drawString(dPH,x+w/20,y+w/9+w/13);
			                g.drawString(qteConso,x+w/20,y+w/9+w/9);
		                } 
			    		else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
		                   	g.setFont(font); g.setColor(Color.DARK_GRAY);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso,x+w/20,y+w/9+w/26);
			                g.drawString(dPH,x+w/20,y+w/9+w/13);
			                g.drawString(qteConso,x+w/20,y+w/9+w/9);
			    		} 
			    		
		                Image img4 = ImageIO.read(new File("radiateur2h.jpg"));
		                g.drawImage(img4, x+w/2+w/3, y+w/2+w/4, w/10,w/8, this);
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad2", x+w/2+w/3, y+w/2+w/4);
		                if(this.pieces.get(0).getRadiateur().get(1).isEnMarche()){
		                	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);
		                   	g.setFont(font); g.setColor(Color.BLACK);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
			                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/13);
			                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/9);
		                } 
			    		else{
			    			g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);	
		                	g.setFont(font); g.setColor(Color.DARK_GRAY);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
		                    g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
			                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/15);
			                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/10);
			    		} 
		            }
			    	else
			    	if(nbRadiateurs ==3){
			     		String conso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getConsoApp()+"W/h";
			    		String  qteConso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getQuantiteConso()+"W";
			    		String dPH = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getDegreParHeure()+"°C/h";
			     		String conso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getConsoApp()+"W/h";
			    		String  qteConso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getQuantiteConso()+"W";
			    		String dPH2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getDegreParHeure()+"°C/h";
			     		String conso3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getConsoApp()+"W/h";
			    		String  qteConso3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getQuantiteConso()+"W";
			    		String dPH3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getDegreParHeure()+"°C/h";
			    		
		                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
		                g.drawImage(img1, x+w/20, y+w/9, w/10,w/8, this);	
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad1", x+w/20, y+w/9);
		                if(this.pieces.get(0).getRadiateur().get(0).isEnMarche()){
		                  	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
		                	g.setFont(font); g.setColor(Color.BLACK);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso,x+w/20,y+w/9+w/26);
			                g.drawString(dPH,x+w/20,y+w/9+w/13);
			                g.drawString(qteConso,x+w/20,y+w/9+w/9);
		                } 
			    		else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
		                   	g.setFont(font); g.setColor(Color.DARK_GRAY);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso,x+w/20,y+w/9+w/26);
			                g.drawString(dPH,x+w/20,y+w/9+w/13);
			                g.drawString(qteConso,x+w/20,y+w/9+w/9);
			    		} 
			    		
		                Image img4 = ImageIO.read(new File("radiateur2h.jpg"));
		                g.drawImage(img4, x+w/2+w/3, y+w/2+w/4, w/10,w/8, this);
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad2", x+w/2+w/3, y+w/2+w/4);
		                if(this.pieces.get(0).getRadiateur().get(1).isEnMarche()){
		                	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);
		                   	g.setFont(font); g.setColor(Color.BLACK);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
			                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/13);
			                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/9);
		                } 
			    		else{
			    			g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);	
		                	g.setFont(font); g.setColor(Color.DARK_GRAY);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
		                    g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
			                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/15);
			                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/10);
			    		} 
		                
		                Image img3 = ImageIO.read(new File("radiateur2h.jpg"));
		                g.drawImage(img3, x+w/2+w/3, y+w/9, w/10,w/8, this);
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad3", x+w/2+w/3, y+w/9);
		                if(this.pieces.get(0).getRadiateur().get(2).isEnMarche()){
		                	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect( x+w/2+w/3, y+w/9,w/50,w/50);
		                	g.setFont(font); g.setColor(Color.BLACK);
		                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
		                    g.drawString(conso3,x+w/2+w/3, y+w/9+w/26);
			                g.drawString(dPH3,x+w/2+w/3, y+w/9 +w/13);
			                g.drawString(qteConso3,x+w/2+w/3, y+w/9+w/9);
		                } 
			    		else{
			    			g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/2+w/3, y+w/9,w/50,w/50);	
		                	g.setFont(font); g.setColor(Color.DARK_GRAY);
		                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
		                    g.drawString(conso3,x+w/2+w/3, y+w/9+w/26);
			                g.drawString(dPH3,x+w/2+w/3, y+w/9 +w/13);
			                g.drawString(qteConso3,x+w/2+w/3, y+w/9+w/9);
			    		} 
		                
			    	}
			    	else
			    	if(nbRadiateurs == 4){
			     		String conso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getConsoApp()+"W/h";
			    		String  qteConso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getQuantiteConso()+"W";
			    		String dPH = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getDegreParHeure()+"°C/h";
			     		String conso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getConsoApp()+"W/h";
			    		String  qteConso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getQuantiteConso()+"W";
			    		String dPH2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getDegreParHeure()+"°C/h";
			     		String conso3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getConsoApp()+"W/h";
			    		String  qteConso3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getQuantiteConso()+"W";
			    		String dPH3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getDegreParHeure()+"°C/h";
			      		String conso4 = Maison.getInstance().getPiece().get(0).getRadiateur().get(3).getConsoApp()+"W/h";
			    		String  qteConso4 = Maison.getInstance().getPiece().get(0).getRadiateur().get(3).getQuantiteConso()+"W";
			    		String dPH4 = Maison.getInstance().getPiece().get(0).getRadiateur().get(3).getDegreParHeure()+"°C/h";
			    		
			    		
		                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
		                g.drawImage(img1, x+w/20, y+w/9, w/10,w/8, this);	
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad1", x+w/20, y+w/9);
		                if(this.pieces.get(0).getRadiateur().get(0).isEnMarche()){
		                  	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
		                	g.setFont(font); g.setColor(Color.BLACK);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso,x+w/20,y+w/9+w/26);
			                g.drawString(dPH,x+w/20,y+w/9+w/13);
			                g.drawString(qteConso,x+w/20,y+w/9+w/9);
		                } 
			    		else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
		                   	g.setFont(font); g.setColor(Color.DARK_GRAY);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso,x+w/20,y+w/9+w/26);
			                g.drawString(dPH,x+w/20,y+w/9+w/13);
			                g.drawString(qteConso,x+w/20,y+w/9+w/9);
			    		} 
			    		
		                Image img4 = ImageIO.read(new File("radiateur2h.jpg"));
		                g.drawImage(img4, x+w/2+w/3, y+w/2+w/4, w/10,w/8, this);
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad2", x+w/2+w/3, y+w/2+w/4);
		                if(this.pieces.get(0).getRadiateur().get(1).isEnMarche()){
		                	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);
		                   	g.setFont(font); g.setColor(Color.BLACK);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
			                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/13);
			                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/9);
		                } 
			    		else{
			    			g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);	
		                	g.setFont(font); g.setColor(Color.DARK_GRAY);
		                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
		                    g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
			                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/15);
			                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/10);
			    		} 
		                
		                Image img3 = ImageIO.read(new File("radiateur2h.jpg"));
		                g.drawImage(img3, x+w/2+w/3, y+w/9, w/10,w/8, this);
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad3", x+w/2+w/3, y+w/9);
		                if(this.pieces.get(0).getRadiateur().get(2).isEnMarche()){
		                	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect( x+w/2+w/3, y+w/9,w/50,w/50);
		                	g.setFont(font); g.setColor(Color.BLACK);
		                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
		                    g.drawString(conso3,x+w/2+w/3, y+w/9+w/26);
			                g.drawString(dPH3,x+w/2+w/3, y+w/9 +w/13);
			                g.drawString(qteConso3,x+w/2+w/3, y+w/9+w/9);
		                } 
			    		else{
			    			g.setFont(font); g.setColor(Color.DARK_GRAY);
		                	g.fillRect(x+w/2+w/3, y+w/9,w/50,w/50);	
		                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
		                    g.drawString(conso3,x+w/2+w/3, y+w/9+w/26);
			                g.drawString(dPH3,x+w/2+w/3, y+w/9 +w/13);
			                g.drawString(qteConso3,x+w/2+w/3, y+w/9+w/9);
			    		} 
		                
		                Image img5 = ImageIO.read(new File("radiateur2h.jpg"));
		                g.drawImage(img5, x+w/20, y+w/2+w/4, w/10,w/8, this);
		                g.setFont(font); g.setColor(Color.BLUE);
		                g.drawString("Rad4",x+w/20, y+w/2+w/4);
		                if(this.pieces.get(0).getRadiateur().get(3).isEnMarche()){
		                	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20, y+w/2+w/4,w/50,w/50);
		                	g.setFont(font); g.setColor(Color.BLACK);
		                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
		                    g.drawString(conso4,x+w/20, y+w/2+w/4+w/26);
			                g.drawString(dPH4,x+w/20, y+w/2+w/4+w/13);
			                g.drawString(qteConso4,x+w/20, y+w/2+w/4+w/9);
		                } 
			    		else{
			    		   	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);
			    			g.setFont(font); g.setColor(Color.DARK_GRAY);
		                	g.fillRect(x+w/2+w/3, y+w/20,w/50,w/50);	
		                    g.drawString(conso4,x+w/20, y+w/2+w/4+w/26);
			                g.drawString(dPH4,x+w/20, y+w/2+w/4+w/13);
			                g.drawString(qteConso4,x+w/20, y+w/2+w/4+w/9);
			    		} 
			    	}
	 
			    } catch (IOException e) {
	 
	                e.printStackTrace();
			    }
			   
			    //tracé des appareils
			    try {
	
			    	Font font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/35);
	                g.setFont(font); g.setColor(Color.BLACK);  
			    	if(nbAppareils == 1){
			    		String conso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getConsoApp()+"W/h";
			    		String qteConso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getQuantiteConso()+"W";
			    		String napp1;
				    	String nomApp1 = this.pieces.get(0).getAppareil().get(0).getNomApp();
				    	if(nomApp1.length()<4)
				    		napp1 = nomApp1;
				    	else
				    		napp1 = nomApp1.substring(0, 3);
				    	Image img1 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img1, x+w/20, y+w/3+w/30, w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp1, x+w/20, y+w/3+w/30);
		                
		                if(this.pieces.get(0).getAppareil().get(0).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50); 
		                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
		                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);	                
		                }
		                else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50);		
		                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
		                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);		         
		                }
		                
			    	}
			    	else
			    	if(nbAppareils == 2){
			    		String conso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getConsoApp()+"W/h";
			    		String qteConso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getQuantiteConso()+"W";
			    		String conso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getConsoApp()+"W/h";
			    		String qteConso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getQuantiteConso()+"W";
			    		String napp1,napp2;
				    	String nomApp1 = this.pieces.get(0).getAppareil().get(0).getNomApp();
				    	if(nomApp1.length()<4)
				    		napp1 = nomApp1;
				    	else
				    		napp1 = nomApp1.substring(0, 3);
				    	Image img1 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img1, x+w/20, y+w/3+w/30, w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp1, x+w/20, y+w/3+w/30);
		                if(this.pieces.get(0).getAppareil().get(0).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50); 
		                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
		                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);	                
		                }
		                else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50);		
		                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
		                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);		         
		                }
	
				    	String nomApp2 = this.pieces.get(0).getAppareil().get(1).getNomApp();
				    	if(nomApp2.length()<4)
				    		napp2 = nomApp2;
				    	else
				    		napp2 = nomApp2.substring(0, 3);
			    		Image img2 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img2, x+w/2+w/3, y+w/3+w/30, w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp2, x+w/2+w/3, y+w/3+w/30);
		                if(this.pieces.get(0).getAppareil().get(1).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
		                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
		                }
		                else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50);	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                  	g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/30+w/12);
		                    g.drawString(qteConso2, x+w/2+w/3,y+w/3+w/30+w/9+w/100);
		                }
			    	}
			    	else 
			    	if(nbAppareils == 3){
			       		String conso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getConsoApp()+"W/h";
			    		String qteConso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getQuantiteConso()+"W";
			    		String conso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getConsoApp()+"W/h";
			    		String qteConso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getQuantiteConso()+"W";
			    		String conso3 = Maison.getInstance().getPiece().get(0).getAppareil().get(2).getConsoApp()+"W/h";
			    		String qteConso3 = Maison.getInstance().getPiece().get(0).getAppareil().get(2).getQuantiteConso()+"W";
			    		
			    		String napp1,napp2,napp4;
				    	String nomApp1 = this.pieces.get(0).getAppareil().get(0).getNomApp();
				    	if(nomApp1.length()<4)
				    		napp1 = nomApp1;
				    	else
				    		napp1 = nomApp1.substring(0, 3);
				    	Image img1 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img1, x+w/20, y+w/3+w/30,w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp1, x+w/20, y+w/3+w/30);
		                if(this.pieces.get(0).getAppareil().get(0).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50); 
		                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
		                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);	                
		                }
		                else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50);		
		                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
		                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);		         
		                }
	
				    	String nomApp2 = this.pieces.get(0).getAppareil().get(1).getNomApp();
				    	if(nomApp2.length()<4)
				    		napp2 = nomApp2;
				    	else
				    		napp2 = nomApp2.substring(0, 3);
			    		Image img2 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img2, x+w/2+w/3, y+w/3+w/30,w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp2, x+w/2+w/3, y+w/3+w/30);
		                if(this.pieces.get(0).getAppareil().get(1).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
		                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
		                }
		                else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50);	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                  	g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
		                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
		                }
		                
		            	String nomApp4 = this.pieces.get(0).getAppareil().get(2).getNomApp();
		    	    	if(nomApp4.length()<4)
				    		napp4 = nomApp4;
				    	else
				    		napp4 = nomApp4.substring(0, 3);
				    	Image img4 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img4, x+w/20, y+w/3+w/6, w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp4, x+w/20, y+w/3+w/6);
	 
		                if(this.pieces.get(0).getAppareil().get(2).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso3, x+w/20, y+w/3+w/6+w/20);
		                    g.drawString(qteConso3, x+w/20, y+w/3+w/6+w/12);
		                }
		                else{
		                   	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso3, x+w/20, y+w/3+w/6+w/20);
		                    g.drawString(qteConso3, x+w/20, y+w/3+w/6+w/12);
		                }
			    	}
			    	else
			    	if(nbAppareils == 4){
			      		String conso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getConsoApp()+"W/h";
			    		String qteConso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getQuantiteConso()+"W";
			    		String conso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getConsoApp()+"W/h";
			    		String qteConso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getQuantiteConso()+"W";
			    		String conso3 = Maison.getInstance().getPiece().get(0).getAppareil().get(2).getConsoApp()+"W/h";
			    		String qteConso3 = Maison.getInstance().getPiece().get(0).getAppareil().get(2).getQuantiteConso()+"W";
			     		String conso4 = Maison.getInstance().getPiece().get(0).getAppareil().get(3).getConsoApp()+"W/h";
			    		String qteConso4 = Maison.getInstance().getPiece().get(0).getAppareil().get(3).getQuantiteConso()+"W";
			    		
			    		String napp1,napp2,napp4,napp33;
				    	String nomApp1 = this.pieces.get(0).getAppareil().get(0).getNomApp();
				    	if(nomApp1.length()<4)
				    		napp1 = nomApp1;
				    	else
				    		napp1 = nomApp1.substring(0, 3);
				    	Image img1 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img1, x+w/20, y+w/3+w/30,w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp1, x+w/20, y+w/3+w/30);
		                if(this.pieces.get(0).getAppareil().get(0).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50); 
		                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
		                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);	                
		                }
		                else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50);		
		                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
		                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);		         
		                }
		                
				    	String nomApp2 = this.pieces.get(0).getAppareil().get(1).getNomApp();
				    	if(nomApp2.length()<4)
				    		napp2 = nomApp2;
				    	else
				    		napp2 = nomApp2.substring(0, 3);
			    		Image img2 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img2, x+w/2+w/3, y+w/3+w/30,w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp2, x+w/2+w/3, y+w/3+w/30);
		                if(this.pieces.get(0).getAppareil().get(1).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
		                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
		                }
		                else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50);	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                  	g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
		                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
		                }
	 
				    	String nomApp4 = this.pieces.get(0).getAppareil().get(2).getNomApp();
				    	if(nomApp2.length()<4)
				    		napp4 = nomApp4;
				    	else
				    		napp4 = nomApp4.substring(0, 3);
			    		Image img4 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img4, x+w/20, y+w/3+w/6,w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp4, x+w/20, y+w/3+w/6);
		                if(this.pieces.get(0).getAppareil().get(2).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect(x+w/20, y+w/3+w/6,w/50,w/50); 	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso3, x+w/20, y+w/3+w/6+w/20);
		                    g.drawString(qteConso3, x+w/20, y+w/3+w/6+w/12);
		                }
		                else{
		                   	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(x+x/20,y+w/3+w/6,w/50,w/50); 	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso3, x+w/20, y+w/3+w/6+w/20);
		                    g.drawString(qteConso3, x+w/20, y+w/3+w/6+w/12);
		                }
		                
		                
		                //4
				    	String nomApp33 = this.pieces.get(0).getAppareil().get(3).getNomApp();
				    	if(nomApp33.length()<4)
				    		napp33 = nomApp33;
				    	else
				    		napp33 = nomApp33.substring(0, 3);
				    	Image img33 = ImageIO.read(new File("appareil1.png"));
		                g.drawImage(img33, x+w/2+w/3, y+w/3+w/6, w/12,w/10, this);
		                g.setFont(font); g.setColor(Color.BLACK);
		                g.drawString(napp33, x+w/2+w/3, y+w/3+w/6);
		                if(this.pieces.get(0).getAppareil().get(3).isEnMarche()){
		                   	g.setFont(font); g.setColor(Color.GREEN);
		                	g.fillRect( x+w/2+w/3, y+w/3+w/6,w/50,w/50); 	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                    g.setFont(font); g.setColor(Color.GREEN);
		                    g.drawString(conso4,  x+w/2+w/3, y+w/3+w/6+w/20);
		                    g.drawString(qteConso4,  x+w/2+w/3, y+w/3+w/6+w/12);
		                }
		                else{
		                	g.setFont(font); g.setColor(Color.GRAY);
		                	g.fillRect(  x+w/20, y+w/3+w/6,w/50,w/50);	
		                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
		                  	g.setFont(font); g.setColor(Color.GRAY);
		                    g.drawString(conso4, x+w/2+w/3, y+w/3+w/6+w/20);
		                    g.drawString(qteConso4, x+w/2+w/3, y+w/3+w/6+w/12);
		                }
		                
				    	 
			    	}
	  
			    } catch (IOException e) {
	 
	                e.printStackTrace();
			    }
			
			    //Tracé des informations extérieures
			    g2d.setPaint(Color.WHITE);
			    g2d.fillRect(wScreen-w/3-1,1,w/3-1,w/5);
			    g2d.setStroke(new BasicStroke(1));
			    g2d.setPaint(Color.getHSBColor(100,0,50)); 
			    g2d.fillRect(wScreen-w/3-1,1,w/3-1,w/5);
			    g2d.setPaint(Color.DARK_GRAY);
			    g2d.drawRect(wScreen-w/3-1,1,w/3-1,w/5);
			    double tempExt = Maison.getInstance().getPiece().get(0).getThermometre().getValeurExterieur();
			    String te = tempExt+"";
			    boolean ilP = Maison.getInstance().isIlPleut();
			    String iP;
			    int heure = Maison.getInstance().getHeure();
			    String test = heure+"";
			    String he;
			    if(test.length() == 1){
			    	he = "Il est 0"+heure+" h 00";
			    }
			    else{
			    	he = "Il est "+heure+" h 00";
			    }
			     
			    if(ilP){
			    	iP = "Il pleut";
			    }
			    else{
			    	iP = "Pas de pluie";
			    }
			    boolean il;
			    il = Maison.getInstance().getPiece().get(0).getIndicateurLuminosite().isExterieurJour();
			    String iL;
			    if(il){
			    	iL = "Il fait jour";
			    }
			    else{
			    	iL = "Il fait nuit";
			    }
			    font1 = new Font("ARIAL",1, w/20);
			    g2d.setFont(font1); 
			    if(tempExt<10)
			    	g2d.setPaint(Color.BLUE);
			    else
			    	g2d.setPaint(Color.RED);
			    
			    g2d.drawString(te+" °C",wScreen-w/3+w/50,w/18 );
			    font1 = new Font("TIMES NEW ROMAN",1, w/32);
			    g2d.setFont(font1); 
			    g2d.setPaint(Color.DARK_GRAY);
			    g2d.drawString(iP,wScreen-w/3+w/50,w/10 );
			    g2d.drawString(iL,wScreen-w/3+w/50,w/7 );
			    g2d.drawString(he,wScreen-w/3+w/50,w/7+w/25 );
			    
			    

			    //Tracé des informations intérieure
	 
			    g2d.setPaint(Color.WHITE);
			    g2d.fillRect(x+w/2+w/4-w/12,y+w-y/4-w/80,w/4-1+w/12-2,w/8-2);
			    g2d.setStroke(new BasicStroke(1));
			    g2d.setPaint(Color.getHSBColor(100,0,50)); 
			    g2d.fillRect(x+w/2+w/4-w/12,y+w-y/4-w/80,w/4-1+w/12-2,w/8-2);
			    g2d.setPaint(Color.DARK_GRAY);
			    g2d.drawRect(x+w/2+w/4-w/12,y+w-y/4-w/80,w/4-1+w/12-2,w/8-2);
			    double tempInt = Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur();
			    double tempEx = Maison.getInstance().getPiece().get(0).gettExigee();
			    String ti = tempInt+"";
			    String se = Maison.getInstance().getPiece().get(0).getSourceEnergie().getNomSource();
			    double qr = Maison.getInstance().getPiece().get(0).getSourceEnergie().getQuantite();
			    String sR = qr+"";
			    boolean estE = Maison.getInstance().getPiece().get(0).getIndicateurLuminosite().isInterieurEclairer();
			    String estEcl = "";
			    if(estE){
			    	int t = Maison.getInstance().getPiece().get(0).getAmpoule().size();
			    	if(t==1)
			    		estEcl = "Pièce éclairée à 25%";
			    	else
			    	if(t==2)
			    		estEcl = "Pièce éclairée à 50%";
			    	else
			    	if(t==3)
			    		estEcl = "Pièce éclairée à 75%";
			    	else
			    	if(t==4)
			    		estEcl = "Pièce éclairée à 100%";
			    }
			    else{
			    	estEcl = "Pièce obscure";
			    }
			    	
  
			    font1 = new Font("ARIAL",1, w/26);
			    g2d.setFont(font1); 
			    if(tempInt<10)
			    	g2d.setPaint(Color.BLUE);
			    else
			    	g2d.setPaint(Color.RED);
			    
			    g2d.drawString(ti+" °C",x+w/2+w/4-w/13,y+w-y/5 );
			    font1 = new Font("TIMES NEW ROMAN",1, w/32);
			    g2d.setFont(font1); 
			    g2d.setPaint(Color.DARK_GRAY);
			    g2d.drawString("T° exigée : "+tempEx+" ° C",x+w/2+w/4-w/13,y+w-y/9 );
			    g2d.drawString(estEcl,x+w/2+w/4-w/13,y+w-y/45 );
			    
			    //tracé du second rectangle intérieur
			    
			    g2d.setPaint(Color.WHITE);
			    g2d.fillRect(x+2,y+w-y/4-w/80,w/4-1+w/13-2,w/8-2);
			    g2d.setStroke(new BasicStroke(1));
			    g2d.setPaint(Color.getHSBColor(100,0,50)); 
			    g2d.fillRect(x+2,y+w-y/4-w/80,w/4-1+w/13-2,w/8-2);
			    g2d.setPaint(Color.DARK_GRAY);
			    g2d.drawRect(x+2,y+w-y/4-w/80,w/4-1+w/13-2,w/8-2);
			    
			    	//infos
			    double tempInt2 = Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur();
			    String ti2 = tempInt+"";
			    String se2 = Maison.getInstance().getPiece().get(0).getSourceEnergie().getNomSource();
			    double qr2 = Maison.getInstance().getPiece().get(0).getSourceEnergie().getQuantite();
			    String sR2 = qr2+"";
			    double consoT = Maison.getInstance().getPiece().get(0).getConsoTotale();
			    String consoTo = consoT+"";
			    double qteConsoT = Maison.getInstance().getPiece().get(0).getQuantiteConsoTotale();
			    String qteConsoTo = qteConsoT+"";
 
			    
			    g2d.drawString("Source : "+se2,x+w/50,y+w-y/5 );
			    font1 = new Font("TIMES NEW ROMAN",1, w/32);
			    g2d.setFont(font1); 
			    g2d.setPaint(Color.DARK_GRAY);
			    g2d.drawString("Conso : "+consoTo+" W/h",x+w/50,y+w-y/9 );
			    g2d.drawString("Qtéconso : "+qteConsoTo+" W",x+w/50,y+w-y/45 );
			    
			}
			else
			if(Maison.getInstance().getPiece().size() == 2){
				
				
//PIECE 1 =================================================================================================
				
//PIECE 1 ================================================================================================
				//paramètre global de l'abscisse 
				x = wScreen/6;
				
				//dessin de l'extérieur 
				if(ilFaitJour() && ilPleut()){
				    try {
				    	System.out.println("IL FAIT JOUR ET IL PLEUT");
		                Image img = ImageIO.read(new File("pluiejour.png"));
		                g2d.setPaint(Color.WHITE);
		                g.drawImage(img, 0,0,this.getWidth(),this.getHeight(), null);

				    } catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
				    }	
				}
				else
				if(!ilFaitJour() && ilPleut()){
				    try {
				    	System.out.println("IL FAIT NUIT ET IL PLEUT");
		                Image img = ImageIO.read(new File("pluienuit.png"));
		                g2d.setPaint(Color.WHITE);
		                g.drawImage(img, 0,0,this.getWidth(),this.getHeight(), null);

				    } catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
				    }		
				}
				else
				if(ilFaitJour() && !ilPleut()){
					System.out.println("IL FAIT JOUR ET IL NE PLEUT PAS");
				    g2d.setPaint(Color.WHITE);
					g2d.fillRect(0,0, this.getWidth(), this.getHeight()-20);						
				}
				else
				if(!ilFaitJour() && !ilPleut()){
					System.out.println("IL FAIT NUIT ET IL NE PLEUT PAS");
				    g2d.setPaint(Color.DARK_GRAY);
					g2d.fillRect(0,0, this.getWidth(), this.getHeight()-20);							
				}
				 
				
			    //tracé de la barre d'état
			    g2d.setStroke(new BasicStroke(1));
			    g2d.setPaint(Color.BLACK);
			    g2d.drawRect(0,hScreen - 20,wScreen,20);
		    	Font font1 = new Font("GEORGIA",1, 12); 
	            g.setFont(font1);  
	            
			    if(Maison.getInstance().isStop()){
			    	g.setColor(Color.LIGHT_GRAY);
			    	g2d.fillRect(0,hScreen - 22,wScreen,20);
				    g2d.setPaint(Color.BLACK);
				    g2d.drawRect(0,hScreen - 22,wScreen,20);
			    	g.setColor(Color.RED);
			    	g.drawString("Arrêtée",4,hScreen - 8);    	
			    }
			    else
			    if(Maison.getInstance().isPlay()){
			    	g.setColor(Color.LIGHT_GRAY);
			    	g2d.fillRect(0,hScreen - 22,wScreen,20);
				    g2d.setPaint(Color.BLACK);
				    g2d.drawRect(0,hScreen - 22,wScreen,20);
				    g2d.setPaint(Color.BLACK);
			    	g.drawString("En cours",4,hScreen - 8);
			    }
			    else
			    if(!Maison.getInstance().isPlay()){ 
			    	g.setColor(Color.LIGHT_GRAY);
			    	g2d.fillRect(0,hScreen - 22,wScreen,20);
				    g2d.setPaint(Color.BLACK);
				    g2d.drawRect(0,hScreen - 22,wScreen,20);
			    	g.setColor(Color.RED);
			    	g.drawString("En pause...",4,hScreen - 8);	    	
			    }
	 
			    
							
				nbFenetres = this.pieces.get(0).getFenetre().size();
				nbAmpoules = this.pieces.get(0).getAmpoule().size();
				nbAppareils = this.pieces.get(0).getAppareil().size();
				nbRadiateurs = this.pieces.get(0).getRadiateur().size();
				habitantPresent = this.pieces.get(0).getHabitant().get(0).isEstPresent();

				 
			 
			// 	this.pieces.get(0).getRadiateur().get(0).setEnMarche(false);
			 //	this.pieces.get(0).getAppareil().get(1).setEnMarche(false);
			// 	this.pieces.get(0).getAppareil().get(2).setEnMarche(false);
			// 	this.pieces.get(0).getAppareil().get(3).setEnMarche(false);
			//	this.pieces.get(0).getAppareil().get(0).setEnMarche(false);
			  
			// 	this.pieces.get(0).getRadiateur().get(1).setEnMarche(false);
			 //	this.pieces.get(0).getRadiateur().get(2).setEnMarche(false);
			 //	this.pieces.get(0).getRadiateur().get(3).setEnMarche(false);
			 //	this.pieces.get(0).getAmpoule().get(0).setEnMarche(false);
			//	this.pieces.get(0).getAmpoule().get(1).setEnMarche(false);
			//	this.pieces.get(0).getAmpoule().get(2).setEnMarche(false);
			//	this.pieces.get(0).getAmpoule().get(3).setEnMarche(false);
			//	this.pieces.get(0).getFenetre().get(0).setVoletOuvert(true);
			//	this.pieces.get(0).getFenetre().get(0).setOuvert(true);
			//	this.pieces.get(0).getFenetre().get(1).setVoletOuvert(true);
			//	this.pieces.get(0).getFenetre().get(1).setOuvert(true); 
				
		        	Rectangle2D.Double piece1 = new Rectangle2D.Double(x,y,w,w);
		        	this.maisonx = x;
		        	this.maisony = y;

				    
		
		            
		            //tracé du conteneur principal
				    g2d.setStroke(new BasicStroke(4));
				    boolean etatAmpoules = this.pieces.get(0).getAmpoule().get(0).isEnMarche();
				    if(etatAmpoules == true){
				    	if(this.pieces.get(0).getAmpoule().size() == 1)
				    		g2d.setPaint(Color.getHSBColor(100,0,30));
				    	else
				    	if(this.pieces.get(0).getAmpoule().size() == 2)
				    		g2d.setPaint(Color.getHSBColor(100,0,20));
				    	else
					    if(this.pieces.get(0).getAmpoule().size() == 3)
					    	g2d.setPaint(Color.getHSBColor(100,0,10));
					    else
					    if(this.pieces.get(0).getAmpoule().size() == 4)
					    	g2d.setPaint(Color.WHITE);
				    }
				    else{
				    	g2d.setPaint(Color.GRAY);
				    }
				    g2d.fill(piece1);
			    	Font fontm = new Font("courrier", Font.CENTER_BASELINE, w/30);
		            g.setFont(fontm); g.setColor(Color.RED); 
		            String nomPiece = this.pieces.get(0).getNom(); 
		            g.drawString(nomPiece, x,y);
		            
		            
				    //tracé du contour noir foncé
				    g2d.setPaint(Color.BLACK);
				    g2d.draw(piece1); 
				    
				    

				    
				    //tracé des fenetres
				    g2d.setStroke(new BasicStroke(4));
				    g2d.setPaint(Color.BLACK);
				    if(nbFenetres == 1){
				    	//si les volet  et la fenetre sont fermé
				    	if(!this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(0).isOuvert()){
						    g2d.setPaint(Color.GRAY);
						    g2d.setStroke(new BasicStroke(2));
						    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - 4,w/4,8);
						    g2d.draw(fenetre2);	
				    	}
				    	else
				    	//si les volets sont ouverts et la fenetre fermée
				    	if(this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(0).isOuvert()){
				    	    g2d.setPaint(Color.GRAY);
						    g2d.setStroke(new BasicStroke(1));
						    Rectangle2D.Double fenetre1 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
						    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
						    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
						    g2d.draw(fenetre1);
						    g2d.draw(fenetre2);	
						    g2d.setStroke(new BasicStroke(2));
						    g2d.draw(fenetre3);	
				    	}
				    	else
				    	//si les volets sont ouverts et la fenetre ouverte
				    	if(this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && this.pieces.get(0).getFenetre().get(0).isOuvert()){
				    	    
						    g2d.setStroke(new BasicStroke(1));
						    Rectangle2D.Double fenetre1 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
						    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
						    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony,4,w/11);
						    Rectangle2D.Double fenetre4 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,4,w/11);
						    Rectangle2D.Double fenetre5 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
						    g2d.setPaint(Color.GRAY);
						    g2d.draw(fenetre1);
						    g2d.draw(fenetre2);
						    g2d.draw(fenetre3);	
						    g2d.draw(fenetre4);	
						    g2d.setPaint(Color.WHITE);
						    g2d.setStroke(new BasicStroke(4));
						    g2d.draw(fenetre5);
						}
				   		  
				    }
				    else
				    if(nbFenetres == 2){
				    
				    	if(!this.pieces.get(0).getFenetre().get(1).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(1).isOuvert()){
						    g2d.setPaint(Color.GRAY);
						    g2d.setStroke(new BasicStroke(2));
						    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75),maisony - 4+w,w/4,8);
						    g2d.draw(fenetre2);	
				    	}
				    	else
				    	//si les volets sont ouverts et la fenetre fermée
				    	if(this.pieces.get(0).getFenetre().get(1).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(1).isOuvert()){
				    	    g2d.setPaint(Color.GRAY);
						    g2d.setStroke(new BasicStroke(1));
						    Rectangle2D.Double fenetre0 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5+w,4,w/11);
						    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5+w,4,w/11);
						    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony+w,w/4,4);
						    g2d.draw(fenetre0);
						    g2d.draw(fenetre2);	
						    g2d.setStroke(new BasicStroke(2));
						    g2d.draw(fenetre3);	
				    	}
				    	else
				    	//si les volets sont ouverts et la fenetre ouverte
				    	if(this.pieces.get(0).getFenetre().get(1).isVoletOuvert() && this.pieces.get(0).getFenetre().get(1).isOuvert()){
				    	    
						    g2d.setStroke(new BasicStroke(1));
						    Rectangle2D.Double fenetre00 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5+w,4,w/11);
						    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5+w,4,w/11);
						    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony+w,4,w/11);
						    Rectangle2D.Double fenetre4 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony+w,4,w/11);
						    Rectangle2D.Double fenetre5 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony+w,w/4,4);
						    g2d.setPaint(Color.GRAY);
						    g2d.draw(fenetre00);
						    g2d.draw(fenetre2);
						    g2d.draw(fenetre3);	
						    g2d.draw(fenetre4);	
						    g2d.setPaint(Color.WHITE);
						    g2d.setStroke(new BasicStroke(4));
						    g2d.draw(fenetre5);
						}	
					    
					    
					    //fenetre1
				    	//si les volet  et la fenetre sont fermé
				    	if(!this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(0).isOuvert()){
						    g2d.setPaint(Color.GRAY);
						    g2d.setStroke(new BasicStroke(2));
						    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - 4,w/4,8);
						    g2d.draw(fenetre2);	
				    	}
				    	else
				    	//si les volets sont ouverts et la fenetre fermée
				    	if(this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(0).getFenetre().get(0).isOuvert()){
				    	    g2d.setPaint(Color.GRAY);
						    g2d.setStroke(new BasicStroke(1));
						    Rectangle2D.Double fenetre0 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
						    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
						    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
						    g2d.draw(fenetre0);
						    g2d.draw(fenetre2);	
						    g2d.setStroke(new BasicStroke(2));
						    g2d.draw(fenetre3);	
				    	}
				    	else
				    	//si les volets sont ouverts et la fenetre ouverte
				    	if(this.pieces.get(0).getFenetre().get(0).isVoletOuvert() && this.pieces.get(0).getFenetre().get(0).isOuvert()){
				    	    
						    g2d.setStroke(new BasicStroke(1));
						    Rectangle2D.Double fenetre00 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
						    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
						    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony,4,w/11);
						    Rectangle2D.Double fenetre4 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,4,w/11);
						    Rectangle2D.Double fenetre5 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
						    g2d.setPaint(Color.GRAY);
						    g2d.draw(fenetre00);
						    g2d.draw(fenetre2);
						    g2d.draw(fenetre3);	
						    g2d.draw(fenetre4);	
						    g2d.setPaint(Color.WHITE);
						    g2d.setStroke(new BasicStroke(4));
						    g2d.draw(fenetre5);
						}	
				    }
		 
				    
				   //Tracé du personnage 
				    if(habitantPresent == true){
					    try {
			                Image img = ImageIO.read(new File("personne1.png"));
			                g.drawImage(img, x+w/4, y+w/2, w/15,w/10, this);
			                //Pour une image de fond
			                //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
					    } catch (IOException e) {
			                // TODO Auto-generated catch block
			                e.printStackTrace();
					    }
				    }
				    else{
					    try {
			                Image img = ImageIO.read(new File("personne1.png"));
			                g.drawImage(img, x-w/4, y+w/2, w/15,w/10, this);
			                //Pour une image de fond
			                //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
					    } catch (IOException e) {
			                // TODO Auto-generated catch block
			                e.printStackTrace();
					    }
				    }
				    
				    //tracé des l'ampoules
				    try {
				    	Font font = new Font("courrier", Font.CENTER_BASELINE, w/35);
		                   
		                if(nbAmpoules == 1){
		                	if(this.pieces.get(0).getAmpoule().get(0).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE); 
				                Image img1 = ImageIO.read(new File("ampoule2.png"));  
				                g.drawImage(img1, x+w/2-w/14, y+w/3+w/8, w/10,w/12, this);      
				                g.drawString("Amp1", x+w/2-w/14, y+w/3+w/8);
		                	}
		                	else{
		                		g.setFont(font); g.setColor(Color.DARK_GRAY); 
		    	                g.fillOval(x+w/2-w/18, y+w/3+w/8, w/14,w/14);      
				                g.drawString("Amp1", x+w/2-w/18, y+w/3+w/8);                		
		                	}
		                }
		                else
		                if(nbAmpoules == 2){
		                	if(this.pieces.get(0).getAmpoule().get(0).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE); 
			                    Image img1 = ImageIO.read(new File("ampoule2.png"));
				                g.drawImage(img1, x+w/2-w/14, y+w/4, w/10,w/12, this);      
				                g.drawString("Amp1", x+w/2-w/14, y+w/4);
		                	}
		                	else{
		                		g.setFont(font); g.setColor(Color.DARK_GRAY); 
				                g.fillOval(x+w/2-w/18, y+w/4, w/14,w/14);      
				                g.drawString("Amp1", x+w/2-w/18, y+w/4);               		
		                	}
		                	if(this.pieces.get(0).getAmpoule().get(1).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE); 
			            	    Image img2 = ImageIO.read(new File("ampoule2.png"));
			                    g.drawImage(img2, x+w/2-w/14, y+w/2+w/8, w/10,w/12, this);
			                    g.drawString("Amp2", x+w/2-w/14, y+w/2+w/8);
		                	}
		                	else{
		                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
				                g.fillOval(x+w/2-w/18, y+w/2+w/8, w/14,w/14);      
				                g.drawString("Amp2", x+w/2-w/18, y+w/2+w/8);                		
		                	}
		                } 
		                else
		                if(nbAmpoules == 3){
		                	if(this.pieces.get(0).getAmpoule().get(0).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE); 
		                		Image img1 = ImageIO.read(new File("ampoule2.png"));
				                g.drawImage(img1, x+w/2-w/14, y+w/7, w/10,w/12, this);      
				                g.drawString("Amp1", x+w/2-w/14, y+w/7);
		                	}
		                	else{
		                		g.setFont(font); g.setColor(Color.DARK_GRAY); 
				                g.fillOval(x+w/2-w/18, y+w/7, w/14,w/14);      
				                g.drawString("Amp1", x+w/2-w/18, y+w/7);      		
		                	}
		                	if(this.pieces.get(0).getAmpoule().get(1).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE);
			            	    Image img2 = ImageIO.read(new File("ampoule2.png"));
			                    g.drawImage(img2, x+w/2-w/14, y+w/3+w/10, w/10,w/12, this);
			                    g.drawString("Amp2", x+w/2-w/14, y+w/3+w/10);
		                	}
		                	else{
		                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
				                g.fillOval(x+w/2-w/18, y+w/3+w/10, w/14,w/14);      
				                g.drawString("Amp2", x+w/2-w/18, y+w/3+w/10);               		
		                	}
		                	if(this.pieces.get(0).getAmpoule().get(2).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE);
			                	Image img3 = ImageIO.read(new File("ampoule2.png"));
			                    g.drawImage(img3, x+w/2-w/14, y+w/2 + w/4, w/10,w/12, this);
			                    g.drawString("Amp3", x+w/2-w/14,  y+w/2 + w/4);   
		                	}
		                	else{
		                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
				                g.fillOval(x+w/2-w/18, y+w/2 + w/4, w/14,w/14);      
				                g.drawString("Amp3", x+w/2-w/18, y+w/2 + w/4); 	
		                	}
		                }
		                else
		                if(nbAmpoules == 4){
		                	if(this.pieces.get(0).getAmpoule().get(0).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE);
			                    Image img1 = ImageIO.read(new File("ampoule2.png"));
				                g.drawImage(img1, x+w/2-w/14, y+w/9, w/10,w/12, this);      
				                g.drawString("Amp1", x+w/2-w/14, y+w/9);
		                	}
		                	else{
		                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
				                g.fillOval(x+w/2-w/18,  y+w/9, w/14,w/14);      
				                g.drawString("Amp1", x+w/2-w/18,  y+w/9);        		
		                	}
		                	if(this.pieces.get(0).getAmpoule().get(1).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE);
			            	    Image img2 = ImageIO.read(new File("ampoule2.png"));
			                    g.drawImage(img2, x+w/2-w/14, y+w/4+w/10, w/10,w/12, this);
			                    g.drawString("Amp2", x+w/2-w/14, y+w/4+w/10);
		                	}
		                	else{
		                   		g.setFont(font); g.setColor(Color.DARK_GRAY); 
				                g.fillOval(x+w/2-w/18, y+w/4+w/10, w/14,w/14);      
				                g.drawString("Amp2", x+w/2-w/18, y+w/4+w/10);    		
		                	}
		                	if(this.pieces.get(0).getAmpoule().get(2).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE);
			                	Image img3 = ImageIO.read(new File("ampoule2.png"));
			                    g.drawImage(img3, x+w/2-w/14, y+w/2 + w/10, w/10,w/12, this);
			                    g.drawString("Amp3", x+w/2-w/14, y+w/2 + w/10); 
		                	}
		                	else{
		                   		g.setFont(font); g.setColor(Color.DARK_GRAY); 
				                g.fillOval(x+w/2-w/18, y+w/2 + w/10, w/14,w/14);      
				                g.drawString("Amp3", x+w/2-w/18, y+w/2 + w/10);  		
		                	}
		                	if(this.pieces.get(0).getAmpoule().get(3).isEnMarche()){
		                		g.setFont(font); g.setColor(Color.ORANGE);
			                    Image img4 = ImageIO.read(new File("ampoule2.png"));
			                    g.drawImage(img4, x+w/2-w/14, y+w/2+w/3, w/10,w/12, this);
			                    g.drawString("Amp4", x+w/2-w/14, y+w/2+w/3);
		                	}
		                	else{
		                 		g.setFont(font); g.setColor(Color.DARK_GRAY); 
				                g.fillOval(x+w/2-w/18, y+w/2+w/3, w/14,w/14);      
				                g.drawString("Amp4", x+w/2-w/18,y+w/2+w/3);    		
		                	}
		                }
		 
				    } catch (IOException e) {
		 
		                e.printStackTrace();
				    }
				    
			
				    //tracé des radiateurs
				    try {
				    	Font font = new Font("courrier", Font.CENTER_BASELINE, w/35);
		                g.setFont(font); g.setColor(Color.BLUE);  
		                 
				    	if(nbRadiateurs ==1){
				    		String conso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getConsoApp()+"W/h";
				    		String  qteConso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getQuantiteConso()+"W";
				    		String dPH = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getDegreParHeure()+"°C/h";
				    		
			                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
			                g.drawImage(img1, x+w/20, y+w/9, w/10,w/8, this);	
			                
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad1", x+w/20, y+w/9);
			                if(this.pieces.get(0).getRadiateur().get(0).isEnMarche()){
			                	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
			                	g.setFont(font); g.setColor(Color.BLACK);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso,x+w/20+w/80,y+w/9+w/26);
				                g.drawString(dPH,x+w/20+w/80,y+w/9+w/13);
				                g.drawString(qteConso,x+w/20+w/80,y+w/9+w/9);
			                } 
				    		else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
			                	g.setFont(font); g.setColor(Color.DARK_GRAY);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso,x+w/20+w/80,y+w/9+w/26);
				                g.drawString(dPH,x+w/20+w/80,y+w/9+w/13);
				                g.drawString(qteConso,x+w/20+w/80,y+w/9+w/9);
				    		} 
				    			
				    		 
				    	}
				    	else
				    	if(nbRadiateurs ==2){
				     		String conso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getConsoApp()+"W/h";
				    		String  qteConso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getQuantiteConso()+"W";
				    		String dPH = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getDegreParHeure()+"°C/h";
				     		String conso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getConsoApp()+"W/h";
				    		String  qteConso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getQuantiteConso()+"W";
				    		String dPH2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getDegreParHeure()+"°C/h";
				    		
			                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
			                g.drawImage(img1, x+w/20, y+w/9, w/10,w/8, this);	
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad1", x+w/20, y+w/9);
			                if(this.pieces.get(0).getRadiateur().get(0).isEnMarche()){
			                  	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
			                	g.setFont(font); g.setColor(Color.BLACK);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso,x+w/20,y+w/9+w/26);
				                g.drawString(dPH,x+w/20,y+w/9+w/13);
				                g.drawString(qteConso,x+w/20,y+w/9+w/9);
			                } 
				    		else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
			                   	g.setFont(font); g.setColor(Color.DARK_GRAY);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso,x+w/20,y+w/9+w/26);
				                g.drawString(dPH,x+w/20,y+w/9+w/13);
				                g.drawString(qteConso,x+w/20,y+w/9+w/9);
				    		} 
				    		
			                Image img4 = ImageIO.read(new File("radiateur2h.jpg"));
			                g.drawImage(img4, x+w/2+w/3, y+w/2+w/4, w/10,w/8, this);
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad2", x+w/2+w/3, y+w/2+w/4);
			                if(this.pieces.get(0).getRadiateur().get(1).isEnMarche()){
			                	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);
			                   	g.setFont(font); g.setColor(Color.BLACK);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
				                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/13);
				                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/9);
			                } 
				    		else{
				    			g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);	
			                	g.setFont(font); g.setColor(Color.DARK_GRAY);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                    g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
				                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/15);
				                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/10);
				    		} 
			            }
				    	else
				    	if(nbRadiateurs ==3){
				     		String conso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getConsoApp()+"W/h";
				    		String  qteConso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getQuantiteConso()+"W";
				    		String dPH = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getDegreParHeure()+"°C/h";
				     		String conso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getConsoApp()+"W/h";
				    		String  qteConso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getQuantiteConso()+"W";
				    		String dPH2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getDegreParHeure()+"°C/h";
				     		String conso3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getConsoApp()+"W/h";
				    		String  qteConso3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getQuantiteConso()+"W";
				    		String dPH3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getDegreParHeure()+"°C/h";
				    		
			                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
			                g.drawImage(img1, x+w/20, y+w/9, w/10,w/8, this);	
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad1", x+w/20, y+w/9);
			                if(this.pieces.get(0).getRadiateur().get(0).isEnMarche()){
			                  	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
			                	g.setFont(font); g.setColor(Color.BLACK);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso,x+w/20,y+w/9+w/26);
				                g.drawString(dPH,x+w/20,y+w/9+w/13);
				                g.drawString(qteConso,x+w/20,y+w/9+w/9);
			                } 
				    		else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
			                   	g.setFont(font); g.setColor(Color.DARK_GRAY);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso,x+w/20,y+w/9+w/26);
				                g.drawString(dPH,x+w/20,y+w/9+w/13);
				                g.drawString(qteConso,x+w/20,y+w/9+w/9);
				    		} 
				    		
			                Image img4 = ImageIO.read(new File("radiateur2h.jpg"));
			                g.drawImage(img4, x+w/2+w/3, y+w/2+w/4, w/10,w/8, this);
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad2", x+w/2+w/3, y+w/2+w/4);
			                if(this.pieces.get(0).getRadiateur().get(1).isEnMarche()){
			                	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);
			                   	g.setFont(font); g.setColor(Color.BLACK);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
				                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/13);
				                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/9);
			                } 
				    		else{
				    			g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);	
			                	g.setFont(font); g.setColor(Color.DARK_GRAY);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                    g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
				                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/15);
				                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/10);
				    		} 
			                
			                Image img3 = ImageIO.read(new File("radiateur2h.jpg"));
			                g.drawImage(img3, x+w/2+w/3, y+w/9, w/10,w/8, this);
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad3", x+w/2+w/3, y+w/9);
			                if(this.pieces.get(0).getRadiateur().get(2).isEnMarche()){
			                	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect( x+w/2+w/3, y+w/9,w/50,w/50);
			                	g.setFont(font); g.setColor(Color.BLACK);
			                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                    g.drawString(conso3,x+w/2+w/3, y+w/9+w/26);
				                g.drawString(dPH3,x+w/2+w/3, y+w/9 +w/13);
				                g.drawString(qteConso3,x+w/2+w/3, y+w/9+w/9);
			                } 
				    		else{
				    			g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/2+w/3, y+w/9,w/50,w/50);	
			                	g.setFont(font); g.setColor(Color.DARK_GRAY);
			                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                    g.drawString(conso3,x+w/2+w/3, y+w/9+w/26);
				                g.drawString(dPH3,x+w/2+w/3, y+w/9 +w/13);
				                g.drawString(qteConso3,x+w/2+w/3, y+w/9+w/9);
				    		} 
			                
				    	}
				    	else
				    	if(nbRadiateurs == 4){
				     		String conso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getConsoApp()+"W/h";
				    		String  qteConso = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getQuantiteConso()+"W";
				    		String dPH = Maison.getInstance().getPiece().get(0).getRadiateur().get(0).getDegreParHeure()+"°C/h";
				     		String conso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getConsoApp()+"W/h";
				    		String  qteConso2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getQuantiteConso()+"W";
				    		String dPH2 = Maison.getInstance().getPiece().get(0).getRadiateur().get(1).getDegreParHeure()+"°C/h";
				     		String conso3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getConsoApp()+"W/h";
				    		String  qteConso3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getQuantiteConso()+"W";
				    		String dPH3 = Maison.getInstance().getPiece().get(0).getRadiateur().get(2).getDegreParHeure()+"°C/h";
				      		String conso4 = Maison.getInstance().getPiece().get(0).getRadiateur().get(3).getConsoApp()+"W/h";
				    		String  qteConso4 = Maison.getInstance().getPiece().get(0).getRadiateur().get(3).getQuantiteConso()+"W";
				    		String dPH4 = Maison.getInstance().getPiece().get(0).getRadiateur().get(3).getDegreParHeure()+"°C/h";
				    		
				    		
			                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
			                g.drawImage(img1, x+w/20, y+w/9, w/10,w/8, this);	
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad1", x+w/20, y+w/9);
			                if(this.pieces.get(0).getRadiateur().get(0).isEnMarche()){
			                  	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
			                	g.setFont(font); g.setColor(Color.BLACK);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso,x+w/20,y+w/9+w/26);
				                g.drawString(dPH,x+w/20,y+w/9+w/13);
				                g.drawString(qteConso,x+w/20,y+w/9+w/9);
			                } 
				    		else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/20,y+w/9,w/50,w/50);
			                   	g.setFont(font); g.setColor(Color.DARK_GRAY);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso,x+w/20,y+w/9+w/26);
				                g.drawString(dPH,x+w/20,y+w/9+w/13);
				                g.drawString(qteConso,x+w/20,y+w/9+w/9);
				    		} 
				    		
			                Image img4 = ImageIO.read(new File("radiateur2h.jpg"));
			                g.drawImage(img4, x+w/2+w/3, y+w/2+w/4, w/10,w/8, this);
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad2", x+w/2+w/3, y+w/2+w/4);
			                if(this.pieces.get(0).getRadiateur().get(1).isEnMarche()){
			                	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);
			                   	g.setFont(font); g.setColor(Color.BLACK);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
				                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/13);
				                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/9);
			                } 
				    		else{
				    			g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);	
			                	g.setFont(font); g.setColor(Color.DARK_GRAY);
			                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                    g.drawString(conso2,x+w/2+w/3, y+w/2+w/4+w/26);
				                g.drawString(dPH2,x+w/2+w/3, y+w/2+w/4+w/15);
				                g.drawString(qteConso2,x+w/2+w/3, y+w/2+w/4+w/10);
				    		} 
			                
			                Image img3 = ImageIO.read(new File("radiateur2h.jpg"));
			                g.drawImage(img3, x+w/2+w/3, y+w/9, w/10,w/8, this);
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad3", x+w/2+w/3, y+w/9);
			                if(this.pieces.get(0).getRadiateur().get(2).isEnMarche()){
			                	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect( x+w/2+w/3, y+w/9,w/50,w/50);
			                	g.setFont(font); g.setColor(Color.BLACK);
			                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                    g.drawString(conso3,x+w/2+w/3, y+w/9+w/26);
				                g.drawString(dPH3,x+w/2+w/3, y+w/9 +w/13);
				                g.drawString(qteConso3,x+w/2+w/3, y+w/9+w/9);
			                } 
				    		else{
				    			g.setFont(font); g.setColor(Color.DARK_GRAY);
			                	g.fillRect(x+w/2+w/3, y+w/9,w/50,w/50);	
			                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                    g.drawString(conso3,x+w/2+w/3, y+w/9+w/26);
				                g.drawString(dPH3,x+w/2+w/3, y+w/9 +w/13);
				                g.drawString(qteConso3,x+w/2+w/3, y+w/9+w/9);
				    		} 
			                
			                Image img5 = ImageIO.read(new File("radiateur2h.jpg"));
			                g.drawImage(img5, x+w/20, y+w/2+w/4, w/10,w/8, this);
			                g.setFont(font); g.setColor(Color.BLUE);
			                g.drawString("Rad4",x+w/20, y+w/2+w/4);
			                if(this.pieces.get(0).getRadiateur().get(3).isEnMarche()){
			                	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20, y+w/2+w/4,w/50,w/50);
			                	g.setFont(font); g.setColor(Color.BLACK);
			                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
			                    g.drawString(conso4,x+w/20, y+w/2+w/4+w/26);
				                g.drawString(dPH4,x+w/20, y+w/2+w/4+w/13);
				                g.drawString(qteConso4,x+w/20, y+w/2+w/4+w/9);
			                } 
				    		else{
				    		   	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/2+w/3, y+w/2+w/4,w/50,w/50);
				    			g.setFont(font); g.setColor(Color.DARK_GRAY);
			                	g.fillRect(x+w/2+w/3, y+w/20,w/50,w/50);	
			                    g.drawString(conso4,x+w/20, y+w/2+w/4+w/26);
				                g.drawString(dPH4,x+w/20, y+w/2+w/4+w/13);
				                g.drawString(qteConso4,x+w/20, y+w/2+w/4+w/9);
				    		} 
				    	}
		 
				    } catch (IOException e) {
		 
		                e.printStackTrace();
				    }
				   
				    //tracé des appareils
				    try {
		
				    	Font font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/35);
		                g.setFont(font); g.setColor(Color.BLACK);  
				    	if(nbAppareils == 1){
				    		String conso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getConsoApp()+"W/h";
				    		String qteConso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getQuantiteConso()+"W";
				    		String napp1;
					    	String nomApp1 = this.pieces.get(0).getAppareil().get(0).getNomApp();
					    	if(nomApp1.length()<4)
					    		napp1 = nomApp1;
					    	else
					    		napp1 = nomApp1.substring(0, 3);
					    	Image img1 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img1, x+w/20, y+w/3+w/30, w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp1, x+w/20, y+w/3+w/30);
			                
			                if(this.pieces.get(0).getAppareil().get(0).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50); 
			                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
			                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);	                
			                }
			                else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50);		
			                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
			                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);		         
			                }
			                
				    	}
				    	else
				    	if(nbAppareils == 2){
				    		String conso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getConsoApp()+"W/h";
				    		String qteConso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getQuantiteConso()+"W";
				    		String conso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getConsoApp()+"W/h";
				    		String qteConso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getQuantiteConso()+"W";
				    		String napp1,napp2;
					    	String nomApp1 = this.pieces.get(0).getAppareil().get(0).getNomApp();
					    	if(nomApp1.length()<4)
					    		napp1 = nomApp1;
					    	else
					    		napp1 = nomApp1.substring(0, 3);
					    	Image img1 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img1, x+w/20, y+w/3+w/30, w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp1, x+w/20, y+w/3+w/30);
			                if(this.pieces.get(0).getAppareil().get(0).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50); 
			                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
			                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);	                
			                }
			                else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50);		
			                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
			                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);		         
			                }
		
					    	String nomApp2 = this.pieces.get(0).getAppareil().get(1).getNomApp();
					    	if(nomApp2.length()<4)
					    		napp2 = nomApp2;
					    	else
					    		napp2 = nomApp2.substring(0, 3);
				    		Image img2 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img2, x+w/2+w/3, y+w/3+w/30, w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp2, x+w/2+w/3, y+w/3+w/30);
			                if(this.pieces.get(0).getAppareil().get(1).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
			                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
			                }
			                else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50);	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                  	g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/30+w/12);
			                    g.drawString(qteConso2, x+w/2+w/3,y+w/3+w/30+w/9+w/100);
			                }
				    	}
				    	else 
				    	if(nbAppareils == 3){
				       		String conso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getConsoApp()+"W/h";
				    		String qteConso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getQuantiteConso()+"W";
				    		String conso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getConsoApp()+"W/h";
				    		String qteConso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getQuantiteConso()+"W";
				    		String conso3 = Maison.getInstance().getPiece().get(0).getAppareil().get(2).getConsoApp()+"W/h";
				    		String qteConso3 = Maison.getInstance().getPiece().get(0).getAppareil().get(2).getQuantiteConso()+"W";
				    		
				    		String napp1,napp2,napp4;
					    	String nomApp1 = this.pieces.get(0).getAppareil().get(0).getNomApp();
					    	if(nomApp1.length()<4)
					    		napp1 = nomApp1;
					    	else
					    		napp1 = nomApp1.substring(0, 3);
					    	Image img1 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img1, x+w/20, y+w/3+w/30,w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp1, x+w/20, y+w/3+w/30);
			                if(this.pieces.get(0).getAppareil().get(0).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50); 
			                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
			                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);	                
			                }
			                else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50);		
			                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
			                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);		         
			                }
		
					    	String nomApp2 = this.pieces.get(0).getAppareil().get(1).getNomApp();
					    	if(nomApp2.length()<4)
					    		napp2 = nomApp2;
					    	else
					    		napp2 = nomApp2.substring(0, 3);
				    		Image img2 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img2, x+w/2+w/3, y+w/3+w/30,w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp2, x+w/2+w/3, y+w/3+w/30);
			                if(this.pieces.get(0).getAppareil().get(1).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
			                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
			                }
			                else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50);	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                  	g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
			                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
			                }
			                
			            	String nomApp4 = this.pieces.get(0).getAppareil().get(2).getNomApp();
			    	    	if(nomApp4.length()<4)
					    		napp4 = nomApp4;
					    	else
					    		napp4 = nomApp4.substring(0, 3);
					    	Image img4 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img4, x+w/20, y+w/3+w/6, w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp4, x+w/20, y+w/3+w/6);
		 
			                if(this.pieces.get(0).getAppareil().get(2).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso3, x+w/20, y+w/3+w/6+w/20);
			                    g.drawString(qteConso3, x+w/20, y+w/3+w/6+w/12);
			                }
			                else{
			                   	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso3, x+w/20, y+w/3+w/6+w/20);
			                    g.drawString(qteConso3, x+w/20, y+w/3+w/6+w/12);
			                }
				    	}
				    	else
				    	if(nbAppareils == 4){
				      		String conso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getConsoApp()+"W/h";
				    		String qteConso = Maison.getInstance().getPiece().get(0).getAppareil().get(0).getQuantiteConso()+"W";
				    		String conso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getConsoApp()+"W/h";
				    		String qteConso2 = Maison.getInstance().getPiece().get(0).getAppareil().get(1).getQuantiteConso()+"W";
				    		String conso3 = Maison.getInstance().getPiece().get(0).getAppareil().get(2).getConsoApp()+"W/h";
				    		String qteConso3 = Maison.getInstance().getPiece().get(0).getAppareil().get(2).getQuantiteConso()+"W";
				     		String conso4 = Maison.getInstance().getPiece().get(0).getAppareil().get(3).getConsoApp()+"W/h";
				    		String qteConso4 = Maison.getInstance().getPiece().get(0).getAppareil().get(3).getQuantiteConso()+"W";
				    		
				    		String napp1,napp2,napp4,napp33;
					    	String nomApp1 = this.pieces.get(0).getAppareil().get(0).getNomApp();
					    	if(nomApp1.length()<4)
					    		napp1 = nomApp1;
					    	else
					    		napp1 = nomApp1.substring(0, 3);
					    	Image img1 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img1, x+w/20, y+w/3+w/30,w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp1, x+w/20, y+w/3+w/30);
			                if(this.pieces.get(0).getAppareil().get(0).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50); 
			                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
			                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);	                
			                }
			                else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+w/20, y+w/3+w/30,w/50,w/50);		
			                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso, x+w/20, y+w/3+w/30+w/20);
			                    g.drawString(qteConso, x+w/20, y+w/3+w/30+w/12);		         
			                }
			                
					    	String nomApp2 = this.pieces.get(0).getAppareil().get(1).getNomApp();
					    	if(nomApp2.length()<4)
					    		napp2 = nomApp2;
					    	else
					    		napp2 = nomApp2.substring(0, 3);
				    		Image img2 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img2, x+w/2+w/3, y+w/3+w/30,w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp2, x+w/2+w/3, y+w/3+w/30);
			                if(this.pieces.get(0).getAppareil().get(1).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
			                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
			                }
			                else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect( x+w/2+w/3, y+w/3+w/30,w/50,w/50);	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                  	g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso2, x+w/2+w/3, y+w/3+w/12);
			                    g.drawString(qteConso2, x+w/2+w/3, y+w/3+w/9+w/100);
			                }
		 
					    	String nomApp4 = this.pieces.get(0).getAppareil().get(2).getNomApp();
					    	if(nomApp2.length()<4)
					    		napp4 = nomApp4;
					    	else
					    		napp4 = nomApp4.substring(0, 3);
				    		Image img4 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img4, x+w/20, y+w/3+w/6,w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp4, x+w/20, y+w/3+w/6);
			                if(this.pieces.get(0).getAppareil().get(2).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect(x+w/20, y+w/3+w/6,w/50,w/50); 	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso3, x+w/20, y+w/3+w/6+w/20);
			                    g.drawString(qteConso3, x+w/20, y+w/3+w/6+w/12);
			                }
			                else{
			                   	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(x+x/20,y+w/3+w/6,w/50,w/50); 	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso3, x+w/20, y+w/3+w/6+w/20);
			                    g.drawString(qteConso3, x+w/20, y+w/3+w/6+w/12);
			                }
			                
			                
			                //4
					    	String nomApp33 = this.pieces.get(0).getAppareil().get(3).getNomApp();
					    	if(nomApp33.length()<4)
					    		napp33 = nomApp33;
					    	else
					    		napp33 = nomApp33.substring(0, 3);
					    	Image img33 = ImageIO.read(new File("appareil1.png"));
			                g.drawImage(img33, x+w/2+w/3, y+w/3+w/6, w/12,w/10, this);
			                g.setFont(font); g.setColor(Color.BLACK);
			                g.drawString(napp33, x+w/2+w/3, y+w/3+w/6);
			                if(this.pieces.get(0).getAppareil().get(3).isEnMarche()){
			                   	g.setFont(font); g.setColor(Color.GREEN);
			                	g.fillRect( x+w/2+w/3, y+w/3+w/6,w/50,w/50); 	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                    g.setFont(font); g.setColor(Color.GREEN);
			                    g.drawString(conso4,  x+w/2+w/3, y+w/3+w/6+w/20);
			                    g.drawString(qteConso4,  x+w/2+w/3, y+w/3+w/6+w/12);
			                }
			                else{
			                	g.setFont(font); g.setColor(Color.GRAY);
			                	g.fillRect(  x+w/20, y+w/3+w/6,w/50,w/50);	
			                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
			                  	g.setFont(font); g.setColor(Color.GRAY);
			                    g.drawString(conso4, x+w/2+w/3, y+w/3+w/6+w/20);
			                    g.drawString(qteConso4, x+w/2+w/3, y+w/3+w/6+w/12);
			                }
			                
					    	 
				    	}
		  
				    } catch (IOException e) {
		 
		                e.printStackTrace();
				    }
				
				    //Tracé des informations extérieures
				    g2d.setPaint(Color.WHITE);
				    g2d.fillRect(wScreen-w/3-1,1,w/3-1,w/5);
				    g2d.setStroke(new BasicStroke(1));
				    g2d.setPaint(Color.getHSBColor(100,0,50)); 
				    g2d.fillRect(wScreen-w/3-1,1,w/3-1,w/5);
				    g2d.setPaint(Color.DARK_GRAY);
				    g2d.drawRect(wScreen-w/3-1,1,w/3-1,w/5);
				    double tempExt = Maison.getInstance().getPiece().get(0).getThermometre().getValeurExterieur();
				    String te = tempExt+"";
				    boolean ilP = Maison.getInstance().isIlPleut();
				    String iP;
				    int heure = Maison.getInstance().getHeure();
				    String test = heure+"";
				    String he;
				    if(test.length() == 1){
				    	he = "Il est 0"+heure+" h 00";
				    }
				    else{
				    	he = "Il est "+heure+" h 00";
				    }
				     
				    if(ilP){
				    	iP = "Il pleut";
				    }
				    else{
				    	iP = "Pas de pluie";
				    }
				    boolean il;
				    il = Maison.getInstance().getPiece().get(0).getIndicateurLuminosite().isExterieurJour();
				    String iL;
				    if(il){
				    	iL = "Il fait jour";
				    }
				    else{
				    	iL = "Il fait nuit";
				    }
				    font1 = new Font("ARIAL",1, w/20);
				    g2d.setFont(font1); 
				    if(tempExt<10)
				    	g2d.setPaint(Color.BLUE);
				    else
				    	g2d.setPaint(Color.RED);
				    
				    g2d.drawString(te+" °C",wScreen-w/3+w/50,w/18 );
				    font1 = new Font("TIMES NEW ROMAN",1, w/32);
				    g2d.setFont(font1); 
				    g2d.setPaint(Color.DARK_GRAY);
				    g2d.drawString(iP,wScreen-w/3+w/50,w/10 );
				    g2d.drawString(iL,wScreen-w/3+w/50,w/7 );
				    g2d.drawString(he,wScreen-w/3+w/50,w/7+w/25 );
				    
				    

				    //Tracé des informations intérieure
		 
				    g2d.setPaint(Color.WHITE);
				    g2d.fillRect(x+w/2+w/4-w/12,y+w-y/4-w/80,w/4-1+w/12-2,w/8-2);
				    g2d.setStroke(new BasicStroke(1));
				    g2d.setPaint(Color.getHSBColor(100,0,50)); 
				    g2d.fillRect(x+w/2+w/4-w/12,y+w-y/4-w/80,w/4-1+w/12-2,w/8-2);
				    g2d.setPaint(Color.DARK_GRAY);
				    g2d.drawRect(x+w/2+w/4-w/12,y+w-y/4-w/80,w/4-1+w/12-2,w/8-2);
				    double tempInt = Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur();
				    double tempEx = Maison.getInstance().getPiece().get(0).gettExigee();
				    String ti = tempInt+"";
				    String se = Maison.getInstance().getPiece().get(0).getSourceEnergie().getNomSource();
				    double qr = Maison.getInstance().getPiece().get(0).getSourceEnergie().getQuantite();
				    String sR = qr+"";
				    boolean estE = Maison.getInstance().getPiece().get(0).getIndicateurLuminosite().isInterieurEclairer();
				    String estEcl = "";
				    if(estE){
				    	int t = Maison.getInstance().getPiece().get(0).getAmpoule().size();
				    	if(t==1)
				    		estEcl = "Pièce éclairée à 25%";
				    	else
				    	if(t==2)
				    		estEcl = "Pièce éclairée à 50%";
				    	else
				    	if(t==3)
				    		estEcl = "Pièce éclairée à 75%";
				    	else
				    	if(t==4)
				    		estEcl = "Pièce éclairée à 100%";
				    }
				    else{
				    	estEcl = "Pièce obscure";
				    }
				    	
	  
				    font1 = new Font("ARIAL",1, w/26);
				    g2d.setFont(font1); 
				    if(tempInt<10)
				    	g2d.setPaint(Color.BLUE);
				    else
				    	g2d.setPaint(Color.RED);
				    
				    g2d.drawString(ti+" °C",x+w/2+w/4-w/13,y+w-y/5 );
				    font1 = new Font("TIMES NEW ROMAN",1, w/32);
				    g2d.setFont(font1); 
				    g2d.setPaint(Color.DARK_GRAY);
				    g2d.drawString("T° exigée : "+tempEx+" ° C",x+w/2+w/4-w/13,y+w-y/9 );
				    g2d.drawString(estEcl,x+w/2+w/4-w/13,y+w-y/45 );
				    
				    //tracé du second rectangle intérieur
				    
				    g2d.setPaint(Color.WHITE);
				    g2d.fillRect(x+2,y+w-y/4-w/80,w/4-1+w/13-2,w/8-2);
				    g2d.setStroke(new BasicStroke(1));
				    g2d.setPaint(Color.getHSBColor(100,0,50)); 
				    g2d.fillRect(x+2,y+w-y/4-w/80,w/4-1+w/13-2,w/8-2);
				    g2d.setPaint(Color.DARK_GRAY);
				    g2d.drawRect(x+2,y+w-y/4-w/80,w/4-1+w/13-2,w/8-2);
				    
				    	//infos
				    double tempInt2 = Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur();
				    String ti2 = tempInt+"";
				    String se2 = Maison.getInstance().getPiece().get(0).getSourceEnergie().getNomSource();
				    double qr2 = Maison.getInstance().getPiece().get(0).getSourceEnergie().getQuantite();
				    String sR2 = qr2+"";
				    double consoT = Maison.getInstance().getPiece().get(0).getConsoTotale();
				    String consoTo = consoT+"";
				    double qteConsoT = Maison.getInstance().getPiece().get(0).getQuantiteConsoTotale();
				    String qteConsoTo = qteConsoT+"";
	 
				    
				    g2d.drawString("Source : "+se2,x+w/50,y+w-y/5 );
				    font1 = new Font("TIMES NEW ROMAN",1, w/32);
				    g2d.setFont(font1); 
				    g2d.setPaint(Color.DARK_GRAY);
				    g2d.drawString("Conso : "+consoTo+" W/h",x+w/50,y+w-y/9 );
				    g2d.drawString("Qtéconso : "+qteConsoTo+" W",x+w/50,y+w-y/45 );
			
				    
//PIECE 2 ==========================================================================================
				

//PIECE 2 ==========================================================================================
					
				    
					
					nbFenetres = this.pieces.get(1).getFenetre().size();
					nbAmpoules = this.pieces.get(1).getAmpoule().size();
					nbAppareils = this.pieces.get(1).getAppareil().size();
					nbRadiateurs = this.pieces.get(1).getRadiateur().size();
					habitantPresent = this.pieces.get(1).getHabitant().get(0).isEstPresent();

					 
				 
				// 	this.pieces.get(1).getRadiateur().get(0).setEnMarche(false);
				 //	this.pieces.get(1).getAppareil().get(1).setEnMarche(false);
				// 	this.pieces.get(1).getAppareil().get(2).setEnMarche(false);
				// 	this.pieces.get(1).getAppareil().get(3).setEnMarche(false);
				//	this.pieces.get(1).getAppareil().get(0).setEnMarche(false);
				  
				// 	this.pieces.get(1).getRadiateur().get(1).setEnMarche(false);
				 //	this.pieces.get(1).getRadiateur().get(2).setEnMarche(false);
				 //	this.pieces.get(1).getRadiateur().get(3).setEnMarche(false);
				 //	this.pieces.get(1).getAmpoule().get(0).setEnMarche(false);
				//	this.pieces.get(1).getAmpoule().get(1).setEnMarche(false);
				//	this.pieces.get(1).getAmpoule().get(2).setEnMarche(false);
				//	this.pieces.get(1).getAmpoule().get(3).setEnMarche(false);
				//	this.pieces.get(1).getFenetre().get(0).setVoletOuvert(true);
				//	this.pieces.get(1).getFenetre().get(0).setOuvert(true);
				//	this.pieces.get(1).getFenetre().get(1).setVoletOuvert(true);
				//	this.pieces.get(1).getFenetre().get(1).setOuvert(true); 
					
			        	Rectangle2D.Double piece2 = new Rectangle2D.Double(3*x,y,w,w);
			        	this.maisonx = 3*x;
			        	this.maisony = y;

					    
			
			            
			            //tracé du conteneur principal
					    g2d.setStroke(new BasicStroke(4));
					    boolean etatAmpoules2p = this.pieces.get(1).getAmpoule().get(0).isEnMarche();
					    if(etatAmpoules2p == true){
					    	if(this.pieces.get(1).getAmpoule().size() == 1)
					    		g2d.setPaint(Color.getHSBColor(100,0,30));
					    	else
					    	if(this.pieces.get(1).getAmpoule().size() == 2)
					    		g2d.setPaint(Color.getHSBColor(100,0,20));
					    	else
						    if(this.pieces.get(1).getAmpoule().size() == 3)
						    	g2d.setPaint(Color.getHSBColor(100,0,10));
						    else
						    if(this.pieces.get(1).getAmpoule().size() == 4)
						    	g2d.setPaint(Color.WHITE);
					    }
					    else{
					    	g2d.setPaint(Color.GRAY);
					    }
					    g2d.fill(piece2);
				    	Font fontm2p = new Font("courrier", Font.CENTER_BASELINE, w/30);
			            g.setFont(fontm2p); g.setColor(Color.RED); 
			            String nomPiece2p = this.pieces.get(1).getNom(); 
			            g.drawString(nomPiece2p, 3*x,y);
			            
			            
					    //tracé du contour noir foncé
					    g2d.setPaint(Color.BLACK);
					    g2d.draw(piece2); 
					    
					    

					    
					    //tracé des fenetres
					    g2d.setStroke(new BasicStroke(4));
					    g2d.setPaint(Color.BLACK);
					    if(nbFenetres == 1){
					    	//si les volet  et la fenetre sont fermé
					    	if(!this.pieces.get(1).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(1).getFenetre().get(0).isOuvert()){
							    g2d.setPaint(Color.GRAY);
							    g2d.setStroke(new BasicStroke(2));
							    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - 4,w/4,8);
							    g2d.draw(fenetre2);	
					    	}
					    	else
					    	//si les volets sont ouverts et la fenetre fermée
					    	if(this.pieces.get(1).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(1).getFenetre().get(0).isOuvert()){
					    	    g2d.setPaint(Color.GRAY);
							    g2d.setStroke(new BasicStroke(1));
							    Rectangle2D.Double fenetre1 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
							    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
							    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
							    g2d.draw(fenetre1);
							    g2d.draw(fenetre2);	
							    g2d.setStroke(new BasicStroke(2));
							    g2d.draw(fenetre3);	
					    	}
					    	else
					    	//si les volets sont ouverts et la fenetre ouverte
					    	if(this.pieces.get(1).getFenetre().get(0).isVoletOuvert() && this.pieces.get(1).getFenetre().get(0).isOuvert()){
					    	    
							    g2d.setStroke(new BasicStroke(1));
							    Rectangle2D.Double fenetre1 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
							    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
							    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony,4,w/11);
							    Rectangle2D.Double fenetre4 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,4,w/11);
							    Rectangle2D.Double fenetre5 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
							    g2d.setPaint(Color.GRAY);
							    g2d.draw(fenetre1);
							    g2d.draw(fenetre2);
							    g2d.draw(fenetre3);	
							    g2d.draw(fenetre4);	
							    g2d.setPaint(Color.WHITE);
							    g2d.setStroke(new BasicStroke(4));
							    g2d.draw(fenetre5);
							}
					   		  
					    }
					    else
					    if(nbFenetres == 2){
					    
					    	if(!this.pieces.get(1).getFenetre().get(1).isVoletOuvert() && !this.pieces.get(1).getFenetre().get(1).isOuvert()){
							    g2d.setPaint(Color.GRAY);
							    g2d.setStroke(new BasicStroke(2));
							    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75),maisony - 4+w,w/4,8);
							    g2d.draw(fenetre2);	
					    	}
					    	else
					    	//si les volets sont ouverts et la fenetre fermée
					    	if(this.pieces.get(1).getFenetre().get(1).isVoletOuvert() && !this.pieces.get(1).getFenetre().get(1).isOuvert()){
					    	    g2d.setPaint(Color.GRAY);
							    g2d.setStroke(new BasicStroke(1));
							    Rectangle2D.Double fenetre0 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5+w,4,w/11);
							    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5+w,4,w/11);
							    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony+w,w/4,4);
							    g2d.draw(fenetre0);
							    g2d.draw(fenetre2);	
							    g2d.setStroke(new BasicStroke(2));
							    g2d.draw(fenetre3);	
					    	}
					    	else
					    	//si les volets sont ouverts et la fenetre ouverte
					    	if(this.pieces.get(1).getFenetre().get(1).isVoletOuvert() && this.pieces.get(1).getFenetre().get(1).isOuvert()){
					    	    
							    g2d.setStroke(new BasicStroke(1));
							    Rectangle2D.Double fenetre00 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5+w,4,w/11);
							    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5+w,4,w/11);
							    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony+w,4,w/11);
							    Rectangle2D.Double fenetre4 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony+w,4,w/11);
							    Rectangle2D.Double fenetre5 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony+w,w/4,4);
							    g2d.setPaint(Color.GRAY);
							    g2d.draw(fenetre00);
							    g2d.draw(fenetre2);
							    g2d.draw(fenetre3);	
							    g2d.draw(fenetre4);	
							    g2d.setPaint(Color.WHITE);
							    g2d.setStroke(new BasicStroke(4));
							    g2d.draw(fenetre5);
							}	
						    
						    
						    //fenetre1
					    	//si les volet  et la fenetre sont fermé
					    	if(!this.pieces.get(1).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(1).getFenetre().get(0).isOuvert()){
							    g2d.setPaint(Color.GRAY);
							    g2d.setStroke(new BasicStroke(2));
							    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - 4,w/4,8);
							    g2d.draw(fenetre2);	
					    	}
					    	else
					    	//si les volets sont ouverts et la fenetre fermée
					    	if(this.pieces.get(1).getFenetre().get(0).isVoletOuvert() && !this.pieces.get(1).getFenetre().get(0).isOuvert()){
					    	    g2d.setPaint(Color.GRAY);
							    g2d.setStroke(new BasicStroke(1));
							    Rectangle2D.Double fenetre0 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
							    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
							    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
							    g2d.draw(fenetre0);
							    g2d.draw(fenetre2);	
							    g2d.setStroke(new BasicStroke(2));
							    g2d.draw(fenetre3);	
					    	}
					    	else
					    	//si les volets sont ouverts et la fenetre ouverte
					    	if(this.pieces.get(1).getFenetre().get(0).isVoletOuvert() && this.pieces.get(1).getFenetre().get(0).isOuvert()){
					    	    
							    g2d.setStroke(new BasicStroke(1));
							    Rectangle2D.Double fenetre00 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony - maisony/5,4,w/11);
							    Rectangle2D.Double fenetre2 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony - maisony/5,4,w/11);
							    Rectangle2D.Double fenetre3 = new Rectangle2D.Double(maisonx +w/2+w/10+1 ,maisony,4,w/11);
							    Rectangle2D.Double fenetre4 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,4,w/11);
							    Rectangle2D.Double fenetre5 = new Rectangle2D.Double(maisonx +((float)w/2.75) ,maisony,w/4,4);
							    g2d.setPaint(Color.GRAY);
							    g2d.draw(fenetre00);
							    g2d.draw(fenetre2);
							    g2d.draw(fenetre3);	
							    g2d.draw(fenetre4);	
							    g2d.setPaint(Color.WHITE);
							    g2d.setStroke(new BasicStroke(4));
							    g2d.draw(fenetre5);
							}	
					    }
			 
					    
					   //Tracé du personnage 
					    if(habitantPresent == true){
						    try {
				                Image img2 = ImageIO.read(new File("personne1.png"));
				                g.drawImage(img2, 4*x+w/7, y+w/2, w/15,w/10, this);
				                //Pour une image de fond
				                //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
						    } catch (IOException e) {
				                // TODO Auto-generated catch block
				                e.printStackTrace();
						    }
					    }
					    else{
						    try {
				                Image img2 = ImageIO.read(new File("personne1.png"));
				                g.drawImage(img2, 4*x+w/2+w/6, y+w/2, w/15,w/10, this);
				                //Pour une image de fond
				                //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
						    } catch (IOException e) {
				                // TODO Auto-generated catch block
				                e.printStackTrace();
						    }
					    }
					    
					    //tracé des l'ampoules
					    try {
					    	Font font = new Font("courrier", Font.CENTER_BASELINE, w/35);
			                   
			                if(nbAmpoules == 1){
			                	if(this.pieces.get(1).getAmpoule().get(0).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE); 
					                Image img1 = ImageIO.read(new File("ampoule2.png"));  
					                g.drawImage(img1, 3*x+w/2-w/14, y+w/3+w/8, w/10,w/12, this);      
					                g.drawString("Amp1", 3*x+w/2-w/14, y+w/3+w/8);
			                	}
			                	else{
			                		g.setFont(font); g.setColor(Color.DARK_GRAY); 
			    	                g.fillOval(3*x+w/2-w/18, y+w/3+w/8, w/14,w/14);      
					                g.drawString("Amp1", 3*x+w/2-w/18, y+w/3+w/8);                		
			                	}
			                }
			                else
			                if(nbAmpoules == 2){
			                	if(this.pieces.get(1).getAmpoule().get(0).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE); 
				                    Image img1 = ImageIO.read(new File("ampoule2.png"));
					                g.drawImage(img1, 3*x+w/2-w/14, y+w/4, w/10,w/12, this);      
					                g.drawString("Amp1", 3*x+w/2-w/14, y+w/4);
			                	}
			                	else{
			                		g.setFont(font); g.setColor(Color.DARK_GRAY); 
					                g.fillOval(3*x+w/2-w/18, y+w/4, w/14,w/14);      
					                g.drawString("Amp1", 3*x+w/2-w/18, y+w/4);               		
			                	}
			                	if(this.pieces.get(1).getAmpoule().get(1).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE); 
				            	    Image img2 = ImageIO.read(new File("ampoule2.png"));
				                    g.drawImage(img2, 3*x+w/2-w/14, y+w/2+w/8, w/10,w/12, this);
				                    g.drawString("Amp2", 3*x+w/2-w/14, y+w/2+w/8);
			                	}
			                	else{
			                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
					                g.fillOval(3*x+w/2-w/18, y+w/2+w/8, w/14,w/14);      
					                g.drawString("Amp2", 3*x+w/2-w/18, y+w/2+w/8);                		
			                	}
			                } 
			                else
			                if(nbAmpoules == 3){
			                	if(this.pieces.get(1).getAmpoule().get(0).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE); 
			                		Image img1 = ImageIO.read(new File("ampoule2.png"));
					                g.drawImage(img1, 3*x+w/2-w/14, y+w/7, w/10,w/12, this);      
					                g.drawString("Amp1", 3*x+w/2-w/14, y+w/7);
			                	}
			                	else{
			                		g.setFont(font); g.setColor(Color.DARK_GRAY); 
					                g.fillOval(3*x+w/2-w/18, y+w/7, w/14,w/14);      
					                g.drawString("Amp1", 3*x+w/2-w/18, y+w/7);      		
			                	}
			                	if(this.pieces.get(1).getAmpoule().get(1).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE);
				            	    Image img2 = ImageIO.read(new File("ampoule2.png"));
				                    g.drawImage(img2, 3*x+w/2-w/14, y+w/3+w/10, w/10,w/12, this);
				                    g.drawString("Amp2", 3*x+w/2-w/14, y+w/3+w/10);
			                	}
			                	else{
			                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
					                g.fillOval(3*x+w/2-w/18, y+w/3+w/10, w/14,w/14);      
					                g.drawString("Amp2", 3*x+w/2-w/18, y+w/3+w/10);               		
			                	}
			                	if(this.pieces.get(1).getAmpoule().get(2).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE);
				                	Image img3 = ImageIO.read(new File("ampoule2.png"));
				                    g.drawImage(img3, 3*x+w/2-w/14, y+w/2 + w/4, w/10,w/12, this);
				                    g.drawString("Amp3", 3*x+w/2-w/14,  y+w/2 + w/4);   
			                	}
			                	else{
			                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
					                g.fillOval(3*x+w/2-w/18, y+w/2 + w/4, w/14,w/14);      
					                g.drawString("Amp3", 3*x+w/2-w/18, y+w/2 + w/4); 	
			                	}
			                }
			                else
			                if(nbAmpoules == 4){
			                	if(this.pieces.get(1).getAmpoule().get(0).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE);
				                    Image img1 = ImageIO.read(new File("ampoule2.png"));
					                g.drawImage(img1, 3*x+w/2-w/14, y+w/9, w/10,w/12, this);      
					                g.drawString("Amp1", 3*x+w/2-w/14, y+w/9);
			                	}
			                	else{
			                  		g.setFont(font); g.setColor(Color.DARK_GRAY); 
					                g.fillOval(3*x+w/2-w/18,  y+w/9, w/14,w/14);      
					                g.drawString("Amp1", 3*x+w/2-w/18,  y+w/9);        		
			                	}
			                	if(this.pieces.get(1).getAmpoule().get(1).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE);
				            	    Image img2 = ImageIO.read(new File("ampoule2.png"));
				                    g.drawImage(img2, 3*x+w/2-w/14, y+w/4+w/10, w/10,w/12, this);
				                    g.drawString("Amp2", 3*x+w/2-w/14, y+w/4+w/10);
			                	}
			                	else{
			                   		g.setFont(font); g.setColor(Color.DARK_GRAY); 
					                g.fillOval(3*x+w/2-w/18, y+w/4+w/10, w/14,w/14);      
					                g.drawString("Amp2", 3*x+w/2-w/18, y+w/4+w/10);    		
			                	}
			                	if(this.pieces.get(1).getAmpoule().get(2).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE);
				                	Image img3 = ImageIO.read(new File("ampoule2.png"));
				                    g.drawImage(img3, 3*x+w/2-w/14, y+w/2 + w/10, w/10,w/12, this);
				                    g.drawString("Amp3", 3*x+w/2-w/14, y+w/2 + w/10); 
			                	}
			                	else{
			                   		g.setFont(font); g.setColor(Color.DARK_GRAY); 
					                g.fillOval(3*x+w/2-w/18, y+w/2 + w/10, w/14,w/14);      
					                g.drawString("Amp3", 3*x+w/2-w/18, y+w/2 + w/10);  		
			                	}
			                	if(this.pieces.get(1).getAmpoule().get(3).isEnMarche()){
			                		g.setFont(font); g.setColor(Color.ORANGE);
				                    Image img4 = ImageIO.read(new File("ampoule2.png"));
				                    g.drawImage(img4, 3*x+w/2-w/14, y+w/2+w/3, w/10,w/12, this);
				                    g.drawString("Amp4", 3*x+w/2-w/14, y+w/2+w/3);
			                	}
			                	else{
			                 		g.setFont(font); g.setColor(Color.DARK_GRAY); 
					                g.fillOval(3*x+w/2-w/18, y+w/2+w/3, w/14,w/14);      
					                g.drawString("Amp4", 3*x+w/2-w/18,y+w/2+w/3);    		
			                	}
			                }
			 
					    } catch (IOException e) {
			 
			                e.printStackTrace();
					    }
					    
				
					    //tracé des radiateurs
					    try {
					    	Font font = new Font("courrier", Font.CENTER_BASELINE, w/35);
			                g.setFont(font); g.setColor(Color.BLUE);  
			                 
					    	if(nbRadiateurs ==1){
					    		String conso = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getConsoApp()+"W/h";
					    		String  qteConso = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getQuantiteConso()+"W";
					    		String dPH = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getDegreParHeure()+"°C/h";
					    		
				                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
				                g.drawImage(img1, 3*x+w/20, y+w/9, w/10,w/8, this);	
				                
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad1", 3*x+w/20, y+w/9);
				                if(this.pieces.get(1).getRadiateur().get(0).isEnMarche()){
				                	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20,y+w/9,w/50,w/50);
				                	g.setFont(font); g.setColor(Color.BLACK);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso,3*x+w/20+w/80,y+w/9+w/26);
					                g.drawString(dPH,3*x+w/20+w/80,y+w/9+w/13);
					                g.drawString(qteConso,3*x+w/20+w/80,y+w/9+w/9);
				                } 
					    		else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/20,y+w/9,w/50,w/50);
				                	g.setFont(font); g.setColor(Color.DARK_GRAY);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso,3*x+w/20+w/80,y+w/9+w/26);
					                g.drawString(dPH,3*x+w/20+w/80,y+w/9+w/13);
					                g.drawString(qteConso,3*x+w/20+w/80,y+w/9+w/9);
					    		} 
					    			
					    		 
					    	}
					    	else
					    	if(nbRadiateurs ==2){
					     		String conso = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getConsoApp()+"W/h";
					    		String  qteConso = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getQuantiteConso()+"W";
					    		String dPH = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getDegreParHeure()+"°C/h";
					     		String conso2 = Maison.getInstance().getPiece().get(1).getRadiateur().get(1).getConsoApp()+"W/h";
					    		String  qteConso2 = Maison.getInstance().getPiece().get(1).getRadiateur().get(1).getQuantiteConso()+"W";
					    		String dPH2 = Maison.getInstance().getPiece().get(1).getRadiateur().get(1).getDegreParHeure()+"°C/h";
					    		
				                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
				                g.drawImage(img1, 3*x+w/20, y+w/9, w/10,w/8, this);	
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad1", 3*x+w/20, y+w/9);
				                if(this.pieces.get(1).getRadiateur().get(0).isEnMarche()){
				                  	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20,y+w/9,w/50,w/50);
				                	g.setFont(font); g.setColor(Color.BLACK);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso,3*x+w/20,y+w/9+w/26);
					                g.drawString(dPH,3*x+w/20,y+w/9+w/13);
					                g.drawString(qteConso,3*x+w/20,y+w/9+w/9);
				                } 
					    		else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/20,y+w/9,w/50,w/50);
				                   	g.setFont(font); g.setColor(Color.DARK_GRAY);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso,3*x+w/20,y+w/9+w/26);
					                g.drawString(dPH,3*x+w/20,y+w/9+w/13);
					                g.drawString(qteConso,3*x+w/20,y+w/9+w/9);
					    		} 
					    		
				                Image img4 = ImageIO.read(new File("radiateur2h.jpg"));
				                g.drawImage(img4, 3*x+w/2+w/3, y+w/2+w/4, w/10,w/8, this);
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad2", 3*x+w/2+w/3, y+w/2+w/4);
				                if(this.pieces.get(1).getRadiateur().get(1).isEnMarche()){
				                	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/2+w/3, y+w/2+w/4,w/50,w/50);
				                   	g.setFont(font); g.setColor(Color.BLACK);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso2,3*x+w/2+w/3, y+w/2+w/4+w/26);
					                g.drawString(dPH2,3*x+w/2+w/3, y+w/2+w/4+w/13);
					                g.drawString(qteConso2,3*x+w/2+w/3, y+w/2+w/4+w/9);
				                } 
					    		else{
					    			g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/2+w/3, y+w/2+w/4,w/50,w/50);	
				                	g.setFont(font); g.setColor(Color.DARK_GRAY);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                    g.drawString(conso2,3*x+w/2+w/3, y+w/2+w/4+w/26);
					                g.drawString(dPH2,3*x+w/2+w/3, y+w/2+w/4+w/15);
					                g.drawString(qteConso2,3*x+w/2+w/3, y+w/2+w/4+w/10);
					    		} 
				            }
					    	else
					    	if(nbRadiateurs ==3){
					     		String conso = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getConsoApp()+"W/h";
					    		String  qteConso = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getQuantiteConso()+"W";
					    		String dPH = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getDegreParHeure()+"°C/h";
					     		String conso2 = Maison.getInstance().getPiece().get(1).getRadiateur().get(1).getConsoApp()+"W/h";
					    		String  qteConso2 = Maison.getInstance().getPiece().get(1).getRadiateur().get(1).getQuantiteConso()+"W";
					    		String dPH2 = Maison.getInstance().getPiece().get(1).getRadiateur().get(1).getDegreParHeure()+"°C/h";
					     		String conso3 = Maison.getInstance().getPiece().get(1).getRadiateur().get(2).getConsoApp()+"W/h";
					    		String  qteConso3 = Maison.getInstance().getPiece().get(1).getRadiateur().get(2).getQuantiteConso()+"W";
					    		String dPH3 = Maison.getInstance().getPiece().get(1).getRadiateur().get(2).getDegreParHeure()+"°C/h";
					    		
				                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
				                g.drawImage(img1, 3*x+w/20, y+w/9, w/10,w/8, this);	
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad1", 3*x+w/20, y+w/9);
				                if(this.pieces.get(1).getRadiateur().get(0).isEnMarche()){
				                  	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20,y+w/9,w/50,w/50);
				                	g.setFont(font); g.setColor(Color.BLACK);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso,3*x+w/20,y+w/9+w/26);
					                g.drawString(dPH,3*x+w/20,y+w/9+w/13);
					                g.drawString(qteConso,3*x+w/20,y+w/9+w/9);
				                } 
					    		else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/20,y+w/9,w/50,w/50);
				                   	g.setFont(font); g.setColor(Color.DARK_GRAY);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso,3*x+w/20,y+w/9+w/26);
					                g.drawString(dPH,3*x+w/20,y+w/9+w/13);
					                g.drawString(qteConso,3*x+w/20,y+w/9+w/9);
					    		} 
					    		
				                Image img4 = ImageIO.read(new File("radiateur2h.jpg"));
				                g.drawImage(img4, 3*x+w/2+w/3, y+w/2+w/4, w/10,w/8, this);
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad2", 3*x+w/2+w/3, y+w/2+w/4);
				                if(this.pieces.get(1).getRadiateur().get(1).isEnMarche()){
				                	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/2+w/3, y+w/2+w/4,w/50,w/50);
				                   	g.setFont(font); g.setColor(Color.BLACK);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso2,3*x+w/2+w/3, y+w/2+w/4+w/26);
					                g.drawString(dPH2,3*x+w/2+w/3, y+w/2+w/4+w/13);
					                g.drawString(qteConso2,3*x+w/2+w/3, y+w/2+w/4+w/9);
				                } 
					    		else{
					    			g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/2+w/3, y+w/2+w/4,w/50,w/50);	
				                	g.setFont(font); g.setColor(Color.DARK_GRAY);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                    g.drawString(conso2,3*x+w/2+w/3, y+w/2+w/4+w/26);
					                g.drawString(dPH2,3*x+w/2+w/3, y+w/2+w/4+w/15);
					                g.drawString(qteConso2,3*x+w/2+w/3, y+w/2+w/4+w/10);
					    		} 
				                
				                Image img3 = ImageIO.read(new File("radiateur2h.jpg"));
				                g.drawImage(img3, 3*x+w/2+w/3, y+w/9, w/10,w/8, this);
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad3", 3*x+w/2+w/3, y+w/9);
				                if(this.pieces.get(1).getRadiateur().get(2).isEnMarche()){
				                	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect( 3*x+w/2+w/3, y+w/9,w/50,w/50);
				                	g.setFont(font); g.setColor(Color.BLACK);
				                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                    g.drawString(conso3,3*x+w/2+w/3, y+w/9+w/26);
					                g.drawString(dPH3,3*x+w/2+w/3, y+w/9 +w/13);
					                g.drawString(qteConso3,3*x+w/2+w/3, y+w/9+w/9);
				                } 
					    		else{
					    			g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/2+w/3, y+w/9,w/50,w/50);	
				                	g.setFont(font); g.setColor(Color.DARK_GRAY);
				                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                    g.drawString(conso3,3*x+w/2+w/3, y+w/9+w/26);
					                g.drawString(dPH3,3*x+w/2+w/3, y+w/9 +w/13);
					                g.drawString(qteConso3,3*x+w/2+w/3, y+w/9+w/9);
					    		} 
				                
					    	}
					    	else
					    	if(nbRadiateurs == 4){
					     		String conso = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getConsoApp()+"W/h";
					    		String  qteConso = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getQuantiteConso()+"W";
					    		String dPH = Maison.getInstance().getPiece().get(1).getRadiateur().get(0).getDegreParHeure()+"°C/h";
					     		String conso2 = Maison.getInstance().getPiece().get(1).getRadiateur().get(1).getConsoApp()+"W/h";
					    		String  qteConso2 = Maison.getInstance().getPiece().get(1).getRadiateur().get(1).getQuantiteConso()+"W";
					    		String dPH2 = Maison.getInstance().getPiece().get(1).getRadiateur().get(1).getDegreParHeure()+"°C/h";
					     		String conso3 = Maison.getInstance().getPiece().get(1).getRadiateur().get(2).getConsoApp()+"W/h";
					    		String  qteConso3 = Maison.getInstance().getPiece().get(1).getRadiateur().get(2).getQuantiteConso()+"W";
					    		String dPH3 = Maison.getInstance().getPiece().get(1).getRadiateur().get(2).getDegreParHeure()+"°C/h";
					      		String conso4 = Maison.getInstance().getPiece().get(1).getRadiateur().get(3).getConsoApp()+"W/h";
					    		String  qteConso4 = Maison.getInstance().getPiece().get(1).getRadiateur().get(3).getQuantiteConso()+"W";
					    		String dPH4 = Maison.getInstance().getPiece().get(1).getRadiateur().get(3).getDegreParHeure()+"°C/h";
					    		
					    		
				                Image img1 = ImageIO.read(new File("radiateur2v.jpg"));
				                g.drawImage(img1, 3*x+w/20, y+w/9, w/10,w/8, this);	
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad1", 3*x+w/20, y+w/9);
				                if(this.pieces.get(1).getRadiateur().get(0).isEnMarche()){
				                  	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20,y+w/9,w/50,w/50);
				                	g.setFont(font); g.setColor(Color.BLACK);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso,3*x+w/20,y+w/9+w/26);
					                g.drawString(dPH,3*x+w/20,y+w/9+w/13);
					                g.drawString(qteConso,3*x+w/20,y+w/9+w/9);
				                } 
					    		else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/20,y+w/9,w/50,w/50);
				                   	g.setFont(font); g.setColor(Color.DARK_GRAY);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso,3*x+w/20,y+w/9+w/26);
					                g.drawString(dPH,3*x+w/20,y+w/9+w/13);
					                g.drawString(qteConso,3*x+w/20,y+w/9+w/9);
					    		} 
					    		
				                Image img4 = ImageIO.read(new File("radiateur2h.jpg"));
				                g.drawImage(img4, 3*x+w/2+w/3, y+w/2+w/4, w/10,w/8, this);
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad2", 3*x+w/2+w/3, y+w/2+w/4);
				                if(this.pieces.get(1).getRadiateur().get(1).isEnMarche()){
				                	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/2+w/3, y+w/2+w/4,w/50,w/50);
				                   	g.setFont(font); g.setColor(Color.BLACK);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
					                g.drawString(conso2,3*x+w/2+w/3, y+w/2+w/4+w/26);
					                g.drawString(dPH2,3*x+w/2+w/3, y+w/2+w/4+w/13);
					                g.drawString(qteConso2,3*x+w/2+w/3, y+w/2+w/4+w/9);
				                } 
					    		else{
					    			g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/2+w/3, y+w/2+w/4,w/50,w/50);	
				                	g.setFont(font); g.setColor(Color.DARK_GRAY);
				                	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                    g.drawString(conso2,3*x+w/2+w/3, y+w/2+w/4+w/26);
					                g.drawString(dPH2,3*x+w/2+w/3, y+w/2+w/4+w/15);
					                g.drawString(qteConso2,3*x+w/2+w/3, y+w/2+w/4+w/10);
					    		} 
				                
				                Image img3 = ImageIO.read(new File("radiateur2h.jpg"));
				                g.drawImage(img3, 3*x+w/2+w/3, y+w/9, w/10,w/8, this);
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad3", 3*x+w/2+w/3, y+w/9);
				                if(this.pieces.get(1).getRadiateur().get(2).isEnMarche()){
				                	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect( 3*x+w/2+w/3, y+w/9,w/50,w/50);
				                	g.setFont(font); g.setColor(Color.BLACK);
				                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                    g.drawString(conso3,3*x+w/2+w/3, y+w/9+w/26);
					                g.drawString(dPH3,3*x+w/2+w/3, y+w/9 +w/13);
					                g.drawString(qteConso3,3*x+w/2+w/3, y+w/9+w/9);
				                } 
					    		else{
					    			g.setFont(font); g.setColor(Color.DARK_GRAY);
				                	g.fillRect(3*x+w/2+w/3, y+w/9,w/50,w/50);	
				                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                    g.drawString(conso3,3*x+w/2+w/3, y+w/9+w/26);
					                g.drawString(dPH3,3*x+w/2+w/3, y+w/9 +w/13);
					                g.drawString(qteConso3,3*x+w/2+w/3, y+w/9+w/9);
					    		} 
				                
				                Image img5 = ImageIO.read(new File("radiateur2h.jpg"));
				                g.drawImage(img5, 3*x+w/20, y+w/2+w/4, w/10,w/8, this);
				                g.setFont(font); g.setColor(Color.BLUE);
				                g.drawString("Rad4",3*x+w/20, y+w/2+w/4);
				                if(this.pieces.get(1).getRadiateur().get(3).isEnMarche()){
				                	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20, y+w/2+w/4,w/50,w/50);
				                	g.setFont(font); g.setColor(Color.BLACK);
				                   	font = new Font("ARIAL", Font.CENTER_BASELINE, w/35);  
				                    g.drawString(conso4,3*x+w/20, y+w/2+w/4+w/26);
					                g.drawString(dPH4,3*x+w/20, y+w/2+w/4+w/13);
					                g.drawString(qteConso4,3*x+w/20, y+w/2+w/4+w/9);
				                } 
					    		else{
					    		   	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/2+w/3, y+w/2+w/4,w/50,w/50);
					    			g.setFont(font); g.setColor(Color.DARK_GRAY);
				                	g.fillRect(3*x+w/2+w/3, y+w/20,w/50,w/50);	
				                    g.drawString(conso4,3*x+w/20, y+w/2+w/4+w/26);
					                g.drawString(dPH4,3*x+w/20, y+w/2+w/4+w/13);
					                g.drawString(qteConso4,3*x+w/20, y+w/2+w/4+w/9);
					    		} 
					    	}
			 
					    } catch (IOException e) {
			 
			                e.printStackTrace();
					    }
					   
					    //tracé des appareils
					    try {
			
					    	Font font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/35);
			                g.setFont(font); g.setColor(Color.BLACK);  
					    	if(nbAppareils == 1){
					    		String conso = Maison.getInstance().getPiece().get(1).getAppareil().get(0).getConsoApp()+"W/h";
					    		String qteConso = Maison.getInstance().getPiece().get(1).getAppareil().get(0).getQuantiteConso()+"W";
					    		String napp1;
						    	String nomApp1 = this.pieces.get(1).getAppareil().get(0).getNomApp();
						    	if(nomApp1.length()<4)
						    		napp1 = nomApp1;
						    	else
						    		napp1 = nomApp1.substring(0, 3);
						    	Image img1 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img1, 3*x+w/20, y+w/3+w/30, w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp1, 3*x+w/20, y+w/3+w/30);
				                
				                if(this.pieces.get(1).getAppareil().get(0).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20, y+w/3+w/30,w/50,w/50); 
				                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso, 3*x+w/20, y+w/3+w/30+w/20);
				                    g.drawString(qteConso, 3*x+w/20, y+w/3+w/30+w/12);	                
				                }
				                else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/20, y+w/3+w/30,w/50,w/50);		
				                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso, 3*x+w/20, y+w/3+w/30+w/20);
				                    g.drawString(qteConso, 3*x+w/20, y+w/3+w/30+w/12);		         
				                }
				                
					    	}
					    	else
					    	if(nbAppareils == 2){
					    		String conso = Maison.getInstance().getPiece().get(1).getAppareil().get(0).getConsoApp()+"W/h";
					    		String qteConso = Maison.getInstance().getPiece().get(1).getAppareil().get(0).getQuantiteConso()+"W";
					    		String conso2 = Maison.getInstance().getPiece().get(1).getAppareil().get(1).getConsoApp()+"W/h";
					    		String qteConso2 = Maison.getInstance().getPiece().get(1).getAppareil().get(1).getQuantiteConso()+"W";
					    		String napp1,napp2;
						    	String nomApp1 = this.pieces.get(1).getAppareil().get(0).getNomApp();
						    	if(nomApp1.length()<4)
						    		napp1 = nomApp1;
						    	else
						    		napp1 = nomApp1.substring(0, 3);
						    	Image img1 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img1, 3*x+w/20, y+w/3+w/30, w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp1, 3*x+w/20, y+w/3+w/30);
				                if(this.pieces.get(1).getAppareil().get(0).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20, y+w/3+w/30,w/50,w/50); 
				                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso, 3*x+w/20, y+w/3+w/30+w/20);
				                    g.drawString(qteConso, 3*x+w/20, y+w/3+w/30+w/12);	                
				                }
				                else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/20, y+w/3+w/30,w/50,w/50);		
				                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso, 3*x+w/20, y+w/3+w/30+w/20);
				                    g.drawString(qteConso, 3*x+w/20, y+w/3+w/30+w/12);		         
				                }
			
						    	String nomApp2 = this.pieces.get(1).getAppareil().get(1).getNomApp();
						    	if(nomApp2.length()<4)
						    		napp2 = nomApp2;
						    	else
						    		napp2 = nomApp2.substring(0, 3);
					    		Image img2 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img2, 3*x+w/2+w/3, y+w/3+w/30, w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp2, 3*x+w/2+w/3, y+w/3+w/30);
				                if(this.pieces.get(1).getAppareil().get(1).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect( 3*x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso2, 3*x+w/2+w/3, y+w/3+w/12);
				                    g.drawString(qteConso2, 3*x+w/2+w/3, y+w/3+w/9+w/100);
				                }
				                else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect( 3*x+w/2+w/3, y+w/3+w/30,w/50,w/50);	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                  	g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso2, 3*x+w/2+w/3, y+w/3+w/30+w/12);
				                    g.drawString(qteConso2, 3*x+w/2+w/3,y+w/3+w/30+w/9+w/100);
				                }
					    	}
					    	else 
					    	if(nbAppareils == 3){
					       		String conso = Maison.getInstance().getPiece().get(1).getAppareil().get(0).getConsoApp()+"W/h";
					    		String qteConso = Maison.getInstance().getPiece().get(1).getAppareil().get(0).getQuantiteConso()+"W";
					    		String conso2 = Maison.getInstance().getPiece().get(1).getAppareil().get(1).getConsoApp()+"W/h";
					    		String qteConso2 = Maison.getInstance().getPiece().get(1).getAppareil().get(1).getQuantiteConso()+"W";
					    		String conso3 = Maison.getInstance().getPiece().get(1).getAppareil().get(2).getConsoApp()+"W/h";
					    		String qteConso3 = Maison.getInstance().getPiece().get(1).getAppareil().get(2).getQuantiteConso()+"W";
					    		
					    		String napp1,napp2,napp4;
						    	String nomApp1 = this.pieces.get(1).getAppareil().get(0).getNomApp();
						    	if(nomApp1.length()<4)
						    		napp1 = nomApp1;
						    	else
						    		napp1 = nomApp1.substring(0, 3);
						    	Image img1 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img1, 3*x+w/20, y+w/3+w/30,w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp1, 3*x+w/20, y+w/3+w/30);
				                if(this.pieces.get(1).getAppareil().get(0).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20, y+w/3+w/30,w/50,w/50); 
				                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso, 3*x+w/20, y+w/3+w/30+w/20);
				                    g.drawString(qteConso, 3*x+w/20, y+w/3+w/30+w/12);	                
				                }
				                else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/20, y+w/3+w/30,w/50,w/50);		
				                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso, 3*x+w/20, y+w/3+w/30+w/20);
				                    g.drawString(qteConso, 3*x+w/20, y+w/3+w/30+w/12);		         
				                }
			
						    	String nomApp2 = this.pieces.get(1).getAppareil().get(1).getNomApp();
						    	if(nomApp2.length()<4)
						    		napp2 = nomApp2;
						    	else
						    		napp2 = nomApp2.substring(0, 3);
					    		Image img2 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img2, 3*x+w/2+w/3, y+w/3+w/30,w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp2, 3*x+w/2+w/3, y+w/3+w/30);
				                if(this.pieces.get(1).getAppareil().get(1).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect( 3*x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso2, 3*x+w/2+w/3, y+w/3+w/12);
				                    g.drawString(qteConso2, 3*x+w/2+w/3, y+w/3+w/9+w/100);
				                }
				                else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect( 3*x+w/2+w/3, y+w/3+w/30,w/50,w/50);	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                  	g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso2, 3*x+w/2+w/3, y+w/3+w/12);
				                    g.drawString(qteConso2, 3*x+w/2+w/3, y+w/3+w/9+w/100);
				                }
				                
				            	String nomApp4 = this.pieces.get(1).getAppareil().get(2).getNomApp();
				    	    	if(nomApp4.length()<4)
						    		napp4 = nomApp4;
						    	else
						    		napp4 = nomApp4.substring(0, 3);
						    	Image img4 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img4, 3*x+w/20, y+w/3+w/6, w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp4, 3*x+w/20, y+w/3+w/6);
			 
				                if(this.pieces.get(1).getAppareil().get(2).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect( 3*x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso3, 3*x+w/20, y+w/3+w/6+w/20);
				                    g.drawString(qteConso3, 3*x+w/20, y+w/3+w/6+w/12);
				                }
				                else{
				                   	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect( 3*x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso3, 3*x+w/20, y+w/3+w/6+w/20);
				                    g.drawString(qteConso3, 3*x+w/20, y+w/3+w/6+w/12);
				                }
					    	}
					    	else
					    	if(nbAppareils == 4){
					      		String conso = Maison.getInstance().getPiece().get(1).getAppareil().get(0).getConsoApp()+"W/h";
					    		String qteConso = Maison.getInstance().getPiece().get(1).getAppareil().get(0).getQuantiteConso()+"W";
					    		String conso2 = Maison.getInstance().getPiece().get(1).getAppareil().get(1).getConsoApp()+"W/h";
					    		String qteConso2 = Maison.getInstance().getPiece().get(1).getAppareil().get(1).getQuantiteConso()+"W";
					    		String conso3 = Maison.getInstance().getPiece().get(1).getAppareil().get(2).getConsoApp()+"W/h";
					    		String qteConso3 = Maison.getInstance().getPiece().get(1).getAppareil().get(2).getQuantiteConso()+"W";
					     		String conso4 = Maison.getInstance().getPiece().get(1).getAppareil().get(3).getConsoApp()+"W/h";
					    		String qteConso4 = Maison.getInstance().getPiece().get(1).getAppareil().get(3).getQuantiteConso()+"W";
					    		
					    		String napp1,napp2,napp4,napp33;
						    	String nomApp1 = this.pieces.get(1).getAppareil().get(0).getNomApp();
						    	if(nomApp1.length()<4)
						    		napp1 = nomApp1;
						    	else
						    		napp1 = nomApp1.substring(0, 3);
						    	Image img1 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img1, 3*x+w/20, y+w/3+w/30,w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp1, 3*x+w/20, y+w/3+w/30);
				                if(this.pieces.get(1).getAppareil().get(0).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20, y+w/3+w/30,w/50,w/50); 
				                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso, 3*x+w/20, y+w/3+w/30+w/20);
				                    g.drawString(qteConso, 3*x+w/20, y+w/3+w/30+w/12);	                
				                }
				                else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+w/20, y+w/3+w/30,w/50,w/50);		
				                	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso, 3*x+w/20, y+w/3+w/30+w/20);
				                    g.drawString(qteConso, 3*x+w/20, y+w/3+w/30+w/12);		         
				                }
				                
						    	String nomApp2 = this.pieces.get(1).getAppareil().get(1).getNomApp();
						    	if(nomApp2.length()<4)
						    		napp2 = nomApp2;
						    	else
						    		napp2 = nomApp2.substring(0, 3);
					    		Image img2 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img2, 3*x+w/2+w/3, y+w/3+w/30,w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp2, 3*x+w/2+w/3, y+w/3+w/30);
				                if(this.pieces.get(1).getAppareil().get(1).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect( 3*x+w/2+w/3, y+w/3+w/30,w/50,w/50); 	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso2, 3*x+w/2+w/3, y+w/3+w/12);
				                    g.drawString(qteConso2, 3*x+w/2+w/3, y+w/3+w/9+w/100);
				                }
				                else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect( 3*x+w/2+w/3, y+w/3+w/30,w/50,w/50);	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                  	g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso2, 3*x+w/2+w/3, y+w/3+w/12);
				                    g.drawString(qteConso2, 3*x+w/2+w/3, y+w/3+w/9+w/100);
				                }
			 
						    	String nomApp4 = this.pieces.get(1).getAppareil().get(2).getNomApp();
						    	if(nomApp2.length()<4)
						    		napp4 = nomApp4;
						    	else
						    		napp4 = nomApp4.substring(0, 3);
					    		Image img4 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img4, 3*x+w/20, y+w/3+w/6,w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp4, 3*x+w/20, y+w/3+w/6);
				                if(this.pieces.get(1).getAppareil().get(2).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect(3*x+w/20, y+w/3+w/6,w/50,w/50); 	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso3, 3*x+w/20, y+w/3+w/6+w/20);
				                    g.drawString(qteConso3, 3*x+w/20, y+w/3+w/6+w/12);
				                }
				                else{
				                   	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(3*x+3*x/20,y+w/3+w/6,w/50,w/50); 	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso3, 3*x+w/20, y+w/3+w/6+w/20);
				                    g.drawString(qteConso3, 3*x+w/20, y+w/3+w/6+w/12);
				                }
				                
				                
				                //4
						    	String nomApp33 = this.pieces.get(1).getAppareil().get(3).getNomApp();
						    	if(nomApp33.length()<4)
						    		napp33 = nomApp33;
						    	else
						    		napp33 = nomApp33.substring(0, 3);
						    	Image img33 = ImageIO.read(new File("appareil1.png"));
				                g.drawImage(img33, 3*x+w/2+w/3, y+w/3+w/6, w/12,w/10, this);
				                g.setFont(font); g.setColor(Color.BLACK);
				                g.drawString(napp33, 3*x+w/2+w/3, y+w/3+w/6);
				                if(this.pieces.get(1).getAppareil().get(3).isEnMarche()){
				                   	g.setFont(font); g.setColor(Color.GREEN);
				                	g.fillRect( 3*x+w/2+w/3, y+w/3+w/6,w/50,w/50); 	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                    g.setFont(font); g.setColor(Color.GREEN);
				                    g.drawString(conso4,  3*x+w/2+w/3, y+w/3+w/6+w/20);
				                    g.drawString(qteConso4,  3*x+w/2+w/3, y+w/3+w/6+w/12);
				                }
				                else{
				                	g.setFont(font); g.setColor(Color.GRAY);
				                	g.fillRect(  3*x+w/20, y+w/3+w/6,w/50,w/50);	
				                  	font = new Font("TIMES NEW ROMAN", Font.CENTER_BASELINE, w/43);
				                  	g.setFont(font); g.setColor(Color.GRAY);
				                    g.drawString(conso4, 3*x+w/2+w/3, y+w/3+w/6+w/20);
				                    g.drawString(qteConso4, 3*x+w/2+w/3, y+w/3+w/6+w/12);
				                }
				                
						    	 
					    	}
			  
					    } catch (IOException e) {
			 
			                e.printStackTrace();
					    }
					
 

					    //Tracé des informations intérieure
			 
					    g2d.setPaint(Color.WHITE);
					    g2d.fillRect(3*x+w/2+w/4-w/12,y+w-y/4-w/80,w/4-1+w/12-2,w/8-2);
					    g2d.setStroke(new BasicStroke(1));
					    g2d.setPaint(Color.getHSBColor(100,0,50)); 
					    g2d.fillRect(3*x+w/2+w/4-w/12,y+w-y/4-w/80,w/4-1+w/12-2,w/8-2);
					    g2d.setPaint(Color.DARK_GRAY);
					    g2d.drawRect(3*x+w/2+w/4-w/12,y+w-y/4-w/80,w/4-1+w/12-2,w/8-2);
					    double tempInt2p = Maison.getInstance().getPiece().get(1).getThermometre().getValeurInterieur();
					    double tempEx2p = Maison.getInstance().getPiece().get(1).gettExigee();
					    String ti2p = tempInt2p+"";
					    String se2p = Maison.getInstance().getPiece().get(1).getSourceEnergie().getNomSource();
					    double qr2p = Maison.getInstance().getPiece().get(1).getSourceEnergie().getQuantite();
					    String sR2p = qr2p+"";
					    boolean estE2p = Maison.getInstance().getPiece().get(1).getIndicateurLuminosite().isInterieurEclairer();
					    String estEcl2p = "";
					    if(estE2p){
					    	int t2p = Maison.getInstance().getPiece().get(1).getAmpoule().size();
					    	if(t2p==1)
					    		estEcl2p = "Pièce éclairée à 25%";
					    	else
					    	if(t2p==2)
					    		estEcl = "Pièce éclairée à 50%";
					    	else
					    	if(t2p==3)
					    		estEcl = "Pièce éclairée à 75%";
					    	else
					    	if(t2p==4)
					    		estEcl2p = "Pièce éclairée à 100%";
					    }
					    else{
					    	estEcl2p = "Pièce obscure";
					    }
					    	
		  
					    font1 = new Font("ARIAL",1, w/26);
					    g2d.setFont(font1); 
					    if(tempInt<10)
					    	g2d.setPaint(Color.BLUE);
					    else
					    	g2d.setPaint(Color.RED);
					    
					    g2d.drawString(ti2p+" °C",3*x+w/2+w/4-w/13,y+w-y/5 );
					    font1 = new Font("TIMES NEW ROMAN",1, w/32);
					    g2d.setFont(font1); 
					    g2d.setPaint(Color.DARK_GRAY);
					    g2d.drawString("T° exigée : "+tempEx2p+" ° C",3*x+w/2+w/4-w/13,y+w-y/9 );
					    g2d.drawString(estEcl2p,3*x+w/2+w/4-w/13,y+w-y/45 );
					    
					    //tracé du second rectangle intérieur
					    
					    g2d.setPaint(Color.WHITE);
					    g2d.fillRect(3*x+2,y+w-y/4-w/80,w/4-1+w/13-2,w/8-2);
					    g2d.setStroke(new BasicStroke(1));
					    g2d.setPaint(Color.getHSBColor(100,0,50)); 
					    g2d.fillRect(3*x+2,y+w-y/4-w/80,w/4-1+w/13-2,w/8-2);
					    g2d.setPaint(Color.DARK_GRAY);
					    g2d.drawRect(3*x+2,y+w-y/4-w/80,w/4-1+w/13-2,w/8-2);
					    
					    	//infos
					    double tempInt22p = Maison.getInstance().getPiece().get(1).getThermometre().getValeurInterieur();
					    String ti22p = tempInt2p+"";
					    String se22p = Maison.getInstance().getPiece().get(1).getSourceEnergie().getNomSource();
					    double qr22p = Maison.getInstance().getPiece().get(1).getSourceEnergie().getQuantite();
					    String sR22p = qr22p+"";
					    double consoT2p = Maison.getInstance().getPiece().get(1).getConsoTotale();
					    String consoTo2p = consoT2p+"";
					    double qteConsoT2p = Maison.getInstance().getPiece().get(1).getQuantiteConsoTotale();
					    String qteConsoTo2p = qteConsoT2p+"";
		 
					    
					    g2d.drawString("Source : "+se22p,3*x+w/50,y+w-y/5 );
					    font1 = new Font("TIMES NEW ROMAN",1, w/32);
					    g2d.setFont(font1); 
					    g2d.setPaint(Color.DARK_GRAY);
					    g2d.drawString("Conso : "+consoTo2p+" W/h",3*x+w/50,y+w-y/9 );
					    g2d.drawString("Qtéconso : "+qteConsoTo2p+" W",3*x+w/50,y+w-y/45 );
				
			}
 
		}
	 

 
 
}
