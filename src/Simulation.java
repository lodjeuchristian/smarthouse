import java.util.Random;
import java.util.Vector;


public class Simulation implements Runnable{
	
	private Thermometre th1;
	private Thermometre th2;
	private IndicateurLuminosite il1;
	private IndicateurLuminosite il2; 
	private static int control37h;
	private static int dureePluie;
 
	public Simulation(){
		 
	}
	
	 
	public void run(){
		
		while(Maison.getInstance().isPlay()){
			
		    System.out.println("play");
			
			if(control37h != 37){
				control37h++; 
			}
			else{ 
				
				if(Maison.getInstance().getPiece().size()>0){ 
					Maison.getInstance().setIlPleut(true); 
					dureePluie++;
					if(dureePluie == 5){ 
						Maison.getInstance().setIlPleut(false); 
						control37h = 0;
						dureePluie = 0;
					}
				}
			}
			
		
			//Routine alétoire pour température extérieure :
			//on tire une valeur alétoire entre 0 et 1, une autre entre 0 et 1
			//En fonction de la seconde valeur aléatoire, on incrémente ou on décrémente la température externe
			//Les valeurs extrêmes sont -20 ° et 40 °
			//de meme, il pleut chaque 37 heures (Pour bien alterner la pluie le jour et la nuit) il pleut pendant 5h
			//hihihi génial
			
			
			Random r = new Random();
			int tempe1 = 0 + r.nextInt(2 - 0); //valeure aléatoire entre 0 et 1
			int ctrl = 0 + r.nextInt(2 - 0); 
			if(Maison.getInstance().getPiece().size()>0){
				if(Maison.getInstance().getPiece().get(0).getThermometre().getValeurExterieur() > 40){
					Maison.getInstance().getPiece().get(0).getThermometre().setValeurExterieur(40);
					
				}
				else
				if(Maison.getInstance().getPiece().get(0).getThermometre().getValeurExterieur() < -20){
					Maison.getInstance().getPiece().get(0).getThermometre().setValeurExterieur(-20);
					
				}
				else{
			 
					if(ctrl == 1){
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurExterieur(Maison.getInstance().getPiece().get(0).getThermometre().getValeurExterieur()+tempe1);
					}
					else{
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurExterieur(Maison.getInstance().getPiece().get(0).getThermometre().getValeurExterieur()-tempe1);
					}
				}
			}
 
			
			//TempératureInterne
			if(Maison.getInstance().getPiece().size() == 0){
				
			}
			else
			if(Maison.getInstance().getPiece().size() == 1){
	 
			// si les fenetres ou les volets sont fermés, la t° dépend des radiateurs
			//si on a pas eteint la temerature exigée, on incrémente la température de 
			//la puissance de rechauffement par heure de tous les radiateurs s'ils sont en marche biensûr
			// Si en incrémentant la valeur sera supérieure à la valeur exigée, incémenter manuellement
				int nbFen = Maison.getInstance().getPiece().get(0).getFenetre().size();
				double tmp = 0;
				if(nbFen == 1){
					if(!Maison.getInstance().getPiece().get(0).getFenetre().get(0).isOuvert() || !Maison.getInstance().getPiece().get(0).getFenetre().get(0).isVoletOuvert()){
						for(int j =0; j<Maison.getInstance().getPiece().get(0).getRadiateur().size();++j){
							if(Maison.getInstance().getPiece().get(0).getRadiateur().get(j).isEnMarche())
								tmp = tmp+Maison.getInstance().getPiece().get(0).getRadiateur().get(j).getDegreParHeure();
						}
					}
					
					double tempUpdate = Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur() + tmp;
						
					if(tempUpdate <= Maison.getInstance().getPiece().get(0).gettExigee()){
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurInterieur(tempUpdate);
					} 
					else
					if(tempUpdate > Maison.getInstance().getPiece().get(0).gettExigee()){
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurInterieur(Maison.getInstance().getPiece().get(0).gettExigee());
						
					}
				}
				else
				if(nbFen == 2){
					if((!Maison.getInstance().getPiece().get(0).getFenetre().get(0).isOuvert() || !Maison.getInstance().getPiece().get(0).getFenetre().get(0).isVoletOuvert())
						&& (!Maison.getInstance().getPiece().get(0).getFenetre().get(1).isOuvert() || !Maison.getInstance().getPiece().get(0).getFenetre().get(1).isVoletOuvert())
						){
						for(int j =0; j<Maison.getInstance().getPiece().get(0).getRadiateur().size();++j){
							if(Maison.getInstance().getPiece().get(0).getRadiateur().get(j).isEnMarche())
								tmp = tmp+Maison.getInstance().getPiece().get(0).getRadiateur().get(j).getDegreParHeure();
						}
					}
					
					double tempUpdate = Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur() + tmp;
						
					if(tempUpdate <= Maison.getInstance().getPiece().get(0).gettExigee()){
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurInterieur(tempUpdate);
					} 
					else
					if(tempUpdate > Maison.getInstance().getPiece().get(0).gettExigee()){
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurInterieur(Maison.getInstance().getPiece().get(0).gettExigee());
							
					}
				}
				
			}
			else
			if(Maison.getInstance().getPiece().size() == 2){
				//Piece1
				int nbFen = Maison.getInstance().getPiece().get(0).getFenetre().size();
				double tmp = 0;
				if(nbFen == 1){
					if(!Maison.getInstance().getPiece().get(0).getFenetre().get(0).isOuvert() || !Maison.getInstance().getPiece().get(0).getFenetre().get(0).isVoletOuvert()){
						for(int j =0; j<Maison.getInstance().getPiece().get(0).getRadiateur().size();++j){
							if(Maison.getInstance().getPiece().get(0).getRadiateur().get(j).isEnMarche())
								tmp = tmp+Maison.getInstance().getPiece().get(0).getRadiateur().get(j).getDegreParHeure();
						}
					}
					
					double tempUpdate = Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur() + tmp;
						
					if(tempUpdate <= Maison.getInstance().getPiece().get(0).gettExigee()){
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurInterieur(tempUpdate);
					} 
					else
					if(tempUpdate > Maison.getInstance().getPiece().get(0).gettExigee()){
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurInterieur(Maison.getInstance().getPiece().get(0).gettExigee());
						
					}
				}
				else
				if(nbFen == 2){
					if((!Maison.getInstance().getPiece().get(0).getFenetre().get(0).isOuvert() || !Maison.getInstance().getPiece().get(0).getFenetre().get(0).isVoletOuvert())
						&& (!Maison.getInstance().getPiece().get(0).getFenetre().get(1).isOuvert() || !Maison.getInstance().getPiece().get(0).getFenetre().get(1).isVoletOuvert())
						){
						tmp = 0;
						for(int j =0; j<Maison.getInstance().getPiece().get(0).getRadiateur().size();++j){
							if(Maison.getInstance().getPiece().get(0).getRadiateur().get(j).isEnMarche())	
								tmp = tmp+Maison.getInstance().getPiece().get(0).getRadiateur().get(j).getDegreParHeure();
						}
					}
					
					double tempUpdate = Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur() + tmp;
						
					if(tempUpdate <= Maison.getInstance().getPiece().get(0).gettExigee()){
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurInterieur(tempUpdate);
					} 
					else
					if(tempUpdate > Maison.getInstance().getPiece().get(0).gettExigee()){
						Maison.getInstance().getPiece().get(0).getThermometre().setValeurInterieur(Maison.getInstance().getPiece().get(0).gettExigee());
							
					}
				}
				//Piece2
				int nbFen2 = Maison.getInstance().getPiece().get(1).getFenetre().size();
				double tmp2 = 0;
				if(nbFen2 == 1){
					if(!Maison.getInstance().getPiece().get(1).getFenetre().get(0).isOuvert() || !Maison.getInstance().getPiece().get(1).getFenetre().get(0).isVoletOuvert()){
						for(int j =0; j<Maison.getInstance().getPiece().get(1).getRadiateur().size();++j){
							if(Maison.getInstance().getPiece().get(1).getRadiateur().get(j).isEnMarche())
								tmp2 = tmp2+Maison.getInstance().getPiece().get(1).getRadiateur().get(j).getDegreParHeure();
						}
					}
					
					double tempUpdate = Maison.getInstance().getPiece().get(1).getThermometre().getValeurInterieur() + tmp2;
						
					if(tempUpdate <= Maison.getInstance().getPiece().get(1).gettExigee()){
						Maison.getInstance().getPiece().get(1).getThermometre().setValeurInterieur(tempUpdate);
					} 
					else
					if(tempUpdate > Maison.getInstance().getPiece().get(1).gettExigee()){
						Maison.getInstance().getPiece().get(1).getThermometre().setValeurInterieur(Maison.getInstance().getPiece().get(1).gettExigee());
						
					}
				}
				else
				if(nbFen2 == 2){
					tmp2 = 0;
					if((!Maison.getInstance().getPiece().get(1).getFenetre().get(0).isOuvert() || !Maison.getInstance().getPiece().get(1).getFenetre().get(0).isVoletOuvert())
						&& (!Maison.getInstance().getPiece().get(1).getFenetre().get(1).isOuvert() || !Maison.getInstance().getPiece().get(1).getFenetre().get(1).isVoletOuvert())
						){
						for(int j =0; j<Maison.getInstance().getPiece().get(1).getRadiateur().size();++j){
							if(Maison.getInstance().getPiece().get(1).getRadiateur().get(j).isEnMarche())
								tmp2 = tmp2+Maison.getInstance().getPiece().get(1).getRadiateur().get(j).getDegreParHeure();
						}
					}
					
					double tempUpdate2 = Maison.getInstance().getPiece().get(1).getThermometre().getValeurInterieur() + tmp2;
						
					if(tempUpdate2 <= Maison.getInstance().getPiece().get(1).gettExigee()){
						Maison.getInstance().getPiece().get(1).getThermometre().setValeurInterieur(tempUpdate2);
					} 
					else
					if(tempUpdate2 > Maison.getInstance().getPiece().get(1).gettExigee()){
						Maison.getInstance().getPiece().get(1).getThermometre().setValeurInterieur(Maison.getInstance().getPiece().get(1).gettExigee());
							
					}
				}
				
			}	
			
			
			//Luminosité param1 = interieur eclairé  || param2 = jour
			boolean inteEcla;
			boolean exteJour;
			int heure = Maison.getInstance().getHeure();
			//il fait nuit de 18h à 5h et jour de 6h à 17h
			if((heure <24 && heure > 17) || (heure <6 && heure >= 0)){
				exteJour = false;
			}
			else{
				exteJour = true;
			}
			
			if(Maison.getInstance().getPiece().size()== 1){

				int tmp = 0;
				for(int i =0; i<Maison.getInstance().getPiece().get(0).getAmpoule().size();++i){
					if(Maison.getInstance().getPiece().get(0).getAmpoule().get(i).isEnMarche())
						tmp++;
				}
				if(tmp == 0){
					inteEcla = false;
				}
				else{
					inteEcla = true;
				}

				il1 = new IndicateurLuminosite(inteEcla, exteJour);
				Maison.getInstance().getPiece().get(0).setIndicateurLuminosite(il1);
			}
			else
			if(Maison.getInstance().getPiece().size() == 2){
				//PIECE 1
				int tmp = 0;
				for(int i =0; i<Maison.getInstance().getPiece().get(0).getAmpoule().size();++i){
					if(Maison.getInstance().getPiece().get(0).getAmpoule().get(i).isEnMarche())
						tmp++;
				}
				if(tmp == 0){
					inteEcla = false;
				}
				else{
					inteEcla = true;
				}
				il1 = new IndicateurLuminosite(inteEcla,exteJour); 
				Maison.getInstance().getPiece().get(0).setIndicateurLuminosite(il1);	
				
				//PIECE 2
				int tmp2 = 0;
				for(int i =0; i<Maison.getInstance().getPiece().get(1).getAmpoule().size();++i){
					if(Maison.getInstance().getPiece().get(1).getAmpoule().get(i).isEnMarche())
						tmp2++;
				}
				if(tmp2 == 0){
					inteEcla = false;
				}
				else{
					inteEcla = true;
				}
				
				il2 = new IndicateurLuminosite(inteEcla, exteJour); 
				Maison.getInstance().getPiece().get(1).setIndicateurLuminosite(il2);
			}
			
			
	 
			// si c'est la nuit ou s'il pleut, la source d'énergie est solaier si tous les appareil sont éteints, aucune source
			if(Maison.getInstance().getPiece().size()== 1){
				boolean ipl = Maison.getInstance().isIlPleut();
				boolean ilfn = Maison.getInstance().getPiece().get(0).getIndicateurLuminosite().isExterieurJour();
				if(ipl || !ilfn){
					Maison.getInstance().getPiece().get(0).getSourceEnergie().setNomSource("Electrique");
				}
				else{
					Maison.getInstance().getPiece().get(0).getSourceEnergie().setNomSource("Solaire");
				}
			}
			else
			if(Maison.getInstance().getPiece().size() == 2){
				boolean ipl = Maison.getInstance().isIlPleut();
				boolean ilfn = Maison.getInstance().getPiece().get(0).getIndicateurLuminosite().isExterieurJour();
				if(ipl || !ilfn){
					Maison.getInstance().getPiece().get(0).getSourceEnergie().setNomSource("Electrique");
					Maison.getInstance().getPiece().get(1).getSourceEnergie().setNomSource("Electrique");
				}
				else{
					Maison.getInstance().getPiece().get(0).getSourceEnergie().setNomSource("Solaire");
					Maison.getInstance().getPiece().get(1).getSourceEnergie().setNomSource("Solaire");
				}
				
			}
				
		System.out.println(" statut : " + Thread.currentThread().getState());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	//Setters and Getters
	
	public Thermometre getTh1() {
		return th1;
	}


	public void setTh1(Thermometre th1) {
		this.th1 = th1;
	}


	public Thermometre getTh2() {
		return th2;
	}


	public void setTh2(Thermometre th2) {
		this.th2 = th2;
	}


	public IndicateurLuminosite getIl1() {
		return il1;
	}


	public void setIl1(IndicateurLuminosite il1) {
		this.il1 = il1;
	}


	public IndicateurLuminosite getIl2() {
		return il2;
	}


	public void setIl2(IndicateurLuminosite il2) {
		this.il2 = il2;
	}

 
	

}
