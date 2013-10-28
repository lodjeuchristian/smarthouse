import java.util.Vector;


public class SimulationAppareil implements Runnable{

	Vector <Appareil> appareil1;
	Vector <Ampoule> ampoules1;
	Vector <Radiateur> radiateur1;
	Vector <Appareil> appareil2;
	Vector <Ampoule> ampoules2;
	Vector <Radiateur> radiateur2;
 
	public SimulationAppareil(){
		if(Maison.getInstance().getPiece().size() == 1){
			appareil1 = new Vector<Appareil>();
			ampoules1 = new Vector<Ampoule>();
			radiateur1 = new Vector<Radiateur>();
			appareil1 = Maison.getInstance().getPiece().get(0).getAppareil();
			ampoules1 = Maison.getInstance().getPiece().get(0).getAmpoule();
			radiateur1 = Maison.getInstance().getPiece().get(0).getRadiateur();
		}
		else
		if(Maison.getInstance().getPiece().size() == 2){
			appareil1 = new Vector<Appareil>();
			ampoules1 = new Vector<Ampoule>();
			radiateur1 = new Vector<Radiateur>();
			appareil1 = Maison.getInstance().getPiece().get(0).getAppareil();
			ampoules1 = Maison.getInstance().getPiece().get(0).getAmpoule();
			radiateur1 = Maison.getInstance().getPiece().get(0).getRadiateur();
			
			appareil2 = new Vector<Appareil>();
			ampoules2 = new Vector<Ampoule>();
			radiateur2 = new Vector<Radiateur>();
			appareil2 = Maison.getInstance().getPiece().get(1).getAppareil();
			ampoules2 = Maison.getInstance().getPiece().get(1).getAmpoule();
			radiateur2 = Maison.getInstance().getPiece().get(1).getRadiateur();
		}
	}

	public void run() {
		while(Maison.getInstance().isPlay()){
		if(Maison.getInstance().getPiece().size() == 1){ 
			//si la température est sup égale à la température exigée, on éteint tous les radiateurs
			// si non on allume tous les radiateurs
			if(Maison.getInstance().getPiece().get(0).gettExigee() <= Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur()){
				for(int k=0; k<Maison.getInstance().getPiece().get(0).getRadiateur().size();++k){
					Maison.getInstance().getPiece().get(0).getRadiateur().get(k).setEnMarche(false);
				}
			}
			else{
				for(int k=0; k<Maison.getInstance().getPiece().get(0).getRadiateur().size();++k){
					Maison.getInstance().getPiece().get(0).getRadiateur().get(k).setEnMarche(true);
				}		
			}
			boolean actionUser = false;	//pour savoir si l'utilisateur n'a pas volontairement éteint un appareil
			//Si l'utilisateur est abscent et si actionUser ==fase alors on eteint tous les appareils
			if(!Maison.getInstance().getPiece().get(0).getHabitant().get(0).isEstPresent() && !actionUser){
				for(int k=0; k<Maison.getInstance().getPiece().get(0).getAppareil().size();++k){
					Maison.getInstance().getPiece().get(0).getAppareil().get(k).setEnMarche(false);
				}
				for(int k=0; k<Maison.getInstance().getPiece().get(0).getAmpoule().size();++k){
					Maison.getInstance().getPiece().get(0).getAmpoule().get(k).setEnMarche(false);
				}
				for(int k=0; k<Maison.getInstance().getPiece().get(0).getRadiateur().size();++k){
					Maison.getInstance().getPiece().get(0).getRadiateur().get(k).setEnMarche(false);
				}
			}
			else
			if(Maison.getInstance().getPiece().get(0).getHabitant().get(0).isEstPresent() && !actionUser){
				for(int k=0; k<Maison.getInstance().getPiece().get(0).getAppareil().size();++k){
					Maison.getInstance().getPiece().get(0).getAppareil().get(k).setEnMarche(true);
				}
				for(int k=0; k<Maison.getInstance().getPiece().get(0).getAmpoule().size();++k){
					Maison.getInstance().getPiece().get(0).getAmpoule().get(k).setEnMarche(true);
				}
				for(int k=0; k<Maison.getInstance().getPiece().get(0).getRadiateur().size();++k){
					Maison.getInstance().getPiece().get(0).getRadiateur().get(k).setEnMarche(true);
				}
			}
			
			//Calcul de  la quantité consommée totale et de la conso totale de tous les appareils de la maison	
			double qtc = 0;
			double ct = 0;
			for(int i =0;i<appareil1.size();++i)
				qtc = qtc + appareil1.get(i).getQuantiteConso();
			for(int i =0;i<ampoules1.size();++i)
				qtc = qtc + ampoules1.get(i).getQuantiteConso();
			for(int i =0;i<radiateur1.size();++i)
				qtc = qtc + radiateur1.get(i).getQuantiteConso();
			Maison.getInstance().getPiece().get(0).setQuantiteConsoTotale(qtc);
			
			for(int i =0;i<appareil1.size();++i)
				ct = ct + appareil1.get(i).getConsoApp();
			for(int i =0;i<ampoules1.size();++i)
				ct = ct + ampoules1.get(i).getConsoApp();
			for(int i =0;i<radiateur1.size();++i)
				ct = ct + radiateur1.get(i).getConsoApp();
			Maison.getInstance().getPiece().get(0).setConsoTotale(ct);
				//appareils
				if(appareil1.size() == 1){
					if(appareil1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(0).setQuantiteConso(appareil1.get(0).getQuantiteConso() + appareil1.get(0).getConsoApp());
					}
				}
				else
				if(appareil1.size() == 2){
					if(appareil1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(0).setQuantiteConso(appareil1.get(0).getQuantiteConso() + appareil1.get(0).getConsoApp());
					}
					if(appareil1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(1).setQuantiteConso(appareil1.get(1).getQuantiteConso() + appareil1.get(1).getConsoApp());
					}
				}
				else
				if(appareil1.size() == 3){
					if(appareil1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(0).setQuantiteConso(appareil1.get(0).getQuantiteConso() + appareil1.get(0).getConsoApp());
					}
					if(appareil1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(1).setQuantiteConso(appareil1.get(1).getQuantiteConso() + appareil1.get(1).getConsoApp());
					}	
					if(appareil1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(2).setQuantiteConso(appareil1.get(2).getQuantiteConso() + appareil1.get(2).getConsoApp());
					}
				}
				else
				if(appareil1.size() == 4){
					if(appareil1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(0).setQuantiteConso(appareil1.get(0).getQuantiteConso() + appareil1.get(0).getConsoApp());
					}
					if(appareil1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(1).setQuantiteConso(appareil1.get(1).getQuantiteConso() + appareil1.get(1).getConsoApp());
					}	
					if(appareil1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(2).setQuantiteConso(appareil1.get(2).getQuantiteConso() + appareil1.get(2).getConsoApp());
					}		
					if(appareil1.get(3).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(3).setQuantiteConso(appareil1.get(3).getQuantiteConso() + appareil1.get(3).getConsoApp());
					}
				}
				
				//ampoules
				if(ampoules1.size() == 1){
					if(ampoules1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(0).setQuantiteConso(ampoules1.get(0).getQuantiteConso() + ampoules1.get(0).getConsoApp());
					}
				}
				else
				if(ampoules1.size() == 2){
					if(ampoules1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(0).setQuantiteConso(ampoules1.get(0).getQuantiteConso() + ampoules1.get(0).getConsoApp());
					}
					if(ampoules1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(1).setQuantiteConso(ampoules1.get(1).getQuantiteConso() + ampoules1.get(1).getConsoApp());
					}
				}
				else
				if(ampoules1.size() == 3){
					if(ampoules1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(0).setQuantiteConso(ampoules1.get(0).getQuantiteConso() + ampoules1.get(0).getConsoApp());
					}
					if(ampoules1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(1).setQuantiteConso(ampoules1.get(1).getQuantiteConso() + ampoules1.get(1).getConsoApp());
					}	
					if(ampoules1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(2).setQuantiteConso(ampoules1.get(2).getQuantiteConso() + ampoules1.get(2).getConsoApp());
					}
				}
				else
				if(ampoules1.size() == 4){
					if(ampoules1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(0).setQuantiteConso(ampoules1.get(0).getQuantiteConso() + ampoules1.get(0).getConsoApp());
					}
					if(ampoules1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(1).setQuantiteConso(ampoules1.get(1).getQuantiteConso() + ampoules1.get(1).getConsoApp());
					}	
					if(ampoules1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(2).setQuantiteConso(ampoules1.get(2).getQuantiteConso() + ampoules1.get(2).getConsoApp());
					}		
					if(ampoules1.get(3).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(3).setQuantiteConso(ampoules1.get(3).getQuantiteConso() + ampoules1.get(3).getConsoApp());
					}
				}
				
				//radiateurs
				if(radiateur1.size() == 1){
					if(radiateur1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(0).setQuantiteConso(radiateur1.get(0).getQuantiteConso() + radiateur1.get(0).getConsoApp());
					}
				}
				else
				if(radiateur1.size() == 2){
					if(radiateur1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(0).setQuantiteConso(radiateur1.get(0).getQuantiteConso() + radiateur1.get(0).getConsoApp());
					}
					if(radiateur1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(1).setQuantiteConso(radiateur1.get(1).getQuantiteConso() + radiateur1.get(1).getConsoApp());
					}
				}
				else
				if(radiateur1.size() == 3){
					if(radiateur1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(0).setQuantiteConso(radiateur1.get(0).getQuantiteConso() + radiateur1.get(0).getConsoApp());
					}
					if(radiateur1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(1).setQuantiteConso(radiateur1.get(1).getQuantiteConso() + radiateur1.get(1).getConsoApp());
					}	
					if(radiateur1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(2).setQuantiteConso(radiateur1.get(2).getQuantiteConso() + radiateur1.get(2).getConsoApp());
					}
				}
				else
				if(radiateur1.size() == 4){
					if(radiateur1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(0).setQuantiteConso(radiateur1.get(0).getQuantiteConso() + radiateur1.get(0).getConsoApp());
					}
					if(radiateur1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(1).setQuantiteConso(radiateur1.get(1).getQuantiteConso() + radiateur1.get(1).getConsoApp());
					}	
					if(radiateur1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(2).setQuantiteConso(radiateur1.get(2).getQuantiteConso() + radiateur1.get(2).getConsoApp());
					}		
					if(radiateur1.get(3).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(3).setQuantiteConso(radiateur1.get(3).getQuantiteConso() + radiateur1.get(3).getConsoApp());
					}
				}
 
			}
			else
			if(Maison.getInstance().getPiece().size() == 2){
				//si la température est sup égale à la température exigée, on éteint tous les radiateurs
				// si non on allume tous les radiateurs
				if(Maison.getInstance().getPiece().get(0).gettExigee() <= Maison.getInstance().getPiece().get(0).getThermometre().getValeurInterieur()){
					for(int k=0; k<Maison.getInstance().getPiece().get(0).getRadiateur().size();++k){
						Maison.getInstance().getPiece().get(0).getRadiateur().get(k).setEnMarche(false);
					}
				}
				else{
					for(int k=0; k<Maison.getInstance().getPiece().get(0).getRadiateur().size();++k){
						Maison.getInstance().getPiece().get(0).getRadiateur().get(k).setEnMarche(true);
					}		
				}
				
				boolean actionUser = false;	//pour savoir si l'utilisateur n'a pas volontairement éteint un appareil
				//Si l'utilisateur est abscent et si actionUser ==fase alors on eteint tous les appareils
				//piece1
				
				if(!Maison.getInstance().getPiece().get(0).getHabitant().get(0).isEstPresent() && !actionUser){
					for(int k=0; k<Maison.getInstance().getPiece().get(0).getAppareil().size();++k){
						Maison.getInstance().getPiece().get(0).getAppareil().get(k).setEnMarche(false);
					}
					for(int k=0; k<Maison.getInstance().getPiece().get(0).getAmpoule().size();++k){
						Maison.getInstance().getPiece().get(0).getAmpoule().get(k).setEnMarche(false);
					}
					for(int k=0; k<Maison.getInstance().getPiece().get(0).getRadiateur().size();++k){
						Maison.getInstance().getPiece().get(0).getRadiateur().get(k).setEnMarche(false);
					}
				}
				else
				if(Maison.getInstance().getPiece().get(0).getHabitant().get(0).isEstPresent() && !actionUser){
					for(int k=0; k<Maison.getInstance().getPiece().get(0).getAppareil().size();++k){
						Maison.getInstance().getPiece().get(0).getAppareil().get(k).setEnMarche(true);
					}
					for(int k=0; k<Maison.getInstance().getPiece().get(0).getAmpoule().size();++k){
						Maison.getInstance().getPiece().get(0).getAmpoule().get(k).setEnMarche(true);
					}
					for(int k=0; k<Maison.getInstance().getPiece().get(0).getRadiateur().size();++k){
						Maison.getInstance().getPiece().get(0).getRadiateur().get(k).setEnMarche(true);
					}
				}
				
				//piece2
				//si la température est sup égale à la température exigée, on éteint tous les radiateurs
				// si non on allume tous les radiateurs
				if(Maison.getInstance().getPiece().get(1).gettExigee() <= Maison.getInstance().getPiece().get(1).getThermometre().getValeurInterieur()){
					for(int k=0; k<Maison.getInstance().getPiece().get(1).getRadiateur().size();++k){
						Maison.getInstance().getPiece().get(1).getRadiateur().get(k).setEnMarche(false);
					}
				}
				else{
					for(int k=0; k<Maison.getInstance().getPiece().get(1).getRadiateur().size();++k){
						Maison.getInstance().getPiece().get(1).getRadiateur().get(k).setEnMarche(true);
					}		
				}
				
				boolean actionUser2 = false;	//pour savoir si l'utilisateur n'a pas volontairement éteint un appareil
				//Si l'utilisateur est abscent et si actionUser ==fase alors on eteint tous les appareils
				if(!Maison.getInstance().getPiece().get(1).getHabitant().get(0).isEstPresent() && !actionUser2){
					for(int k=0; k<Maison.getInstance().getPiece().get(1).getAppareil().size();++k){
						Maison.getInstance().getPiece().get(1).getAppareil().get(k).setEnMarche(false);
					}
					for(int k=0; k<Maison.getInstance().getPiece().get(1).getAmpoule().size();++k){
						Maison.getInstance().getPiece().get(1).getAmpoule().get(k).setEnMarche(false);
					}
					for(int k=0; k<Maison.getInstance().getPiece().get(1).getRadiateur().size();++k){
						Maison.getInstance().getPiece().get(1).getRadiateur().get(k).setEnMarche(false);
					}
				}
				else
				if(Maison.getInstance().getPiece().get(1).getHabitant().get(0).isEstPresent() && !actionUser2){
					for(int k=0; k<Maison.getInstance().getPiece().get(1).getAppareil().size();++k){
						Maison.getInstance().getPiece().get(1).getAppareil().get(k).setEnMarche(true);
					}
					for(int k=0; k<Maison.getInstance().getPiece().get(1).getAmpoule().size();++k){
						Maison.getInstance().getPiece().get(1).getAmpoule().get(k).setEnMarche(true);
					}
					for(int k=0; k<Maison.getInstance().getPiece().get(1).getRadiateur().size();++k){
						Maison.getInstance().getPiece().get(1).getRadiateur().get(k).setEnMarche(true);
					}
				}
				
			//Calcul de  la quantité consommée totale et de la conso totale de tous les appareils de la maison	
			//piece 1
			double qtc = 0;
			double ct = 0;
			for(int i =0;i<appareil1.size();++i)
				qtc = qtc + appareil1.get(i).getQuantiteConso();
			for(int i =0;i<ampoules1.size();++i)
				qtc = qtc + ampoules1.get(i).getQuantiteConso();
			for(int i =0;i<radiateur1.size();++i)
				qtc = qtc + radiateur1.get(i).getQuantiteConso();
			Maison.getInstance().getPiece().get(0).setQuantiteConsoTotale(qtc);
			
			for(int i =0;i<appareil1.size();++i)
				ct = ct + appareil1.get(i).getConsoApp();
			for(int i =0;i<ampoules1.size();++i)
				ct = ct + ampoules1.get(i).getConsoApp();
			for(int i =0;i<radiateur1.size();++i)
				ct = ct + radiateur1.get(i).getConsoApp();
			Maison.getInstance().getPiece().get(0).setConsoTotale(ct);
			
			//Piece 2
			double qtc2 = 0;
			double ct2 = 0;
			for(int i =0;i<appareil2.size();++i)
				qtc2 = qtc2 + appareil2.get(i).getQuantiteConso();
			for(int i =0;i<ampoules2.size();++i)
				qtc2 = qtc2 + ampoules2.get(i).getQuantiteConso();
			for(int i =0;i<radiateur2.size();++i)
				qtc2 = qtc2 + radiateur2.get(i).getQuantiteConso();
			Maison.getInstance().getPiece().get(1).setQuantiteConsoTotale(qtc2);
			
			for(int i =0;i<appareil2.size();++i)
				ct2 = ct2 + appareil2.get(i).getConsoApp();
			for(int i =0;i<ampoules2.size();++i)
				ct2 = ct2 + ampoules2.get(i).getConsoApp();
			for(int i =0;i<radiateur2.size();++i)
				ct2 = ct2 + radiateur2.get(i).getConsoApp();
			Maison.getInstance().getPiece().get(1).setConsoTotale(ct2);
			
			
			//Piece 1
				//appareils
				if(appareil1.size() == 1){
					if(appareil1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(0).setQuantiteConso(appareil1.get(0).getQuantiteConso() + appareil1.get(0).getConsoApp());
					}
				}
				else
				if(appareil1.size() == 2){
					if(appareil1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(0).setQuantiteConso(appareil1.get(0).getQuantiteConso() + appareil1.get(0).getConsoApp());
					}
					if(appareil1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(1).setQuantiteConso(appareil1.get(1).getQuantiteConso() + appareil1.get(1).getConsoApp());
					}
				}
				else
				if(appareil1.size() == 3){
					if(appareil1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(0).setQuantiteConso(appareil1.get(0).getQuantiteConso() + appareil1.get(0).getConsoApp());
					}
					if(appareil1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(1).setQuantiteConso(appareil1.get(1).getQuantiteConso() + appareil1.get(1).getConsoApp());
					}	
					if(appareil1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(2).setQuantiteConso(appareil1.get(2).getQuantiteConso() + appareil1.get(2).getConsoApp());
					}
				}
				else
				if(appareil1.size() == 4){
					if(appareil1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(0).setQuantiteConso(appareil1.get(0).getQuantiteConso() + appareil1.get(0).getConsoApp());
					}
					if(appareil1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(1).setQuantiteConso(appareil1.get(1).getQuantiteConso() + appareil1.get(1).getConsoApp());
					}	
					if(appareil1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(2).setQuantiteConso(appareil1.get(2).getQuantiteConso() + appareil1.get(2).getConsoApp());
					}		
					if(appareil1.get(3).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil1.get(3).setQuantiteConso(appareil1.get(3).getQuantiteConso() + appareil1.get(3).getConsoApp());
					}
				}
				
				//ampoules
				if(ampoules1.size() == 1){
					if(ampoules1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(0).setQuantiteConso(ampoules1.get(0).getQuantiteConso() + ampoules1.get(0).getConsoApp());
					}
				}
				else
				if(ampoules1.size() == 2){
					if(ampoules1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(0).setQuantiteConso(ampoules1.get(0).getQuantiteConso() + ampoules1.get(0).getConsoApp());
					}
					if(ampoules1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(1).setQuantiteConso(ampoules1.get(1).getQuantiteConso() + ampoules1.get(1).getConsoApp());
					}
				}
				else
				if(ampoules1.size() == 3){
					if(ampoules1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(0).setQuantiteConso(ampoules1.get(0).getQuantiteConso() + ampoules1.get(0).getConsoApp());
					}
					if(ampoules1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(1).setQuantiteConso(ampoules1.get(1).getQuantiteConso() + ampoules1.get(1).getConsoApp());
					}	
					if(ampoules1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(2).setQuantiteConso(ampoules1.get(2).getQuantiteConso() + ampoules1.get(2).getConsoApp());
					}
				}
				else
				if(ampoules1.size() == 4){
					if(ampoules1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(0).setQuantiteConso(ampoules1.get(0).getQuantiteConso() + ampoules1.get(0).getConsoApp());
					}
					if(ampoules1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(1).setQuantiteConso(ampoules1.get(1).getQuantiteConso() + ampoules1.get(1).getConsoApp());
					}	
					if(ampoules1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(2).setQuantiteConso(ampoules1.get(2).getQuantiteConso() + ampoules1.get(2).getConsoApp());
					}		
					if(ampoules1.get(3).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules1.get(3).setQuantiteConso(ampoules1.get(3).getQuantiteConso() + ampoules1.get(3).getConsoApp());
					}
				}
				
				//radiateurs
				if(radiateur1.size() == 1){
					if(radiateur1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(0).setQuantiteConso(radiateur1.get(0).getQuantiteConso() + radiateur1.get(0).getConsoApp());
					}
				}
				else
				if(radiateur1.size() == 2){
					if(radiateur1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(0).setQuantiteConso(radiateur1.get(0).getQuantiteConso() + radiateur1.get(0).getConsoApp());
					}
					if(radiateur1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(1).setQuantiteConso(radiateur1.get(1).getQuantiteConso() + radiateur1.get(1).getConsoApp());
					}
				}
				else
				if(radiateur1.size() == 3){
					if(radiateur1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(0).setQuantiteConso(radiateur1.get(0).getQuantiteConso() + radiateur1.get(0).getConsoApp());
					}
					if(radiateur1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(1).setQuantiteConso(radiateur1.get(1).getQuantiteConso() + radiateur1.get(1).getConsoApp());
					}	
					if(radiateur1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(2).setQuantiteConso(radiateur1.get(2).getQuantiteConso() + radiateur1.get(2).getConsoApp());
					}
				}
				else
				if(radiateur1.size() == 4){
					if(radiateur1.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(0).setQuantiteConso(radiateur1.get(0).getQuantiteConso() + radiateur1.get(0).getConsoApp());
					}
					if(radiateur1.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(1).setQuantiteConso(radiateur1.get(1).getQuantiteConso() + radiateur1.get(1).getConsoApp());
					}	
					if(radiateur1.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(2).setQuantiteConso(radiateur1.get(2).getQuantiteConso() + radiateur1.get(2).getConsoApp());
					}		
					if(radiateur1.get(3).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur1.get(3).setQuantiteConso(radiateur1.get(3).getQuantiteConso() + radiateur1.get(3).getConsoApp());
					}
				}
				
			//Piece2
				
				//appareils
				if(appareil2.size() == 1){
					if(appareil2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(0).setQuantiteConso(appareil2.get(0).getQuantiteConso() + appareil2.get(0).getConsoApp());
					}
				}
				else
				if(appareil2.size() == 2){
					if(appareil2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(0).setQuantiteConso(appareil2.get(0).getQuantiteConso() + appareil2.get(0).getConsoApp());
					}
					if(appareil2.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(1).setQuantiteConso(appareil2.get(1).getQuantiteConso() + appareil2.get(1).getConsoApp());
					}
				}
				else
				if(appareil2.size() == 3){
					if(appareil2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(0).setQuantiteConso(appareil2.get(0).getQuantiteConso() + appareil2.get(0).getConsoApp());
					}
					if(appareil2.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(1).setQuantiteConso(appareil2.get(1).getQuantiteConso() + appareil2.get(1).getConsoApp());
					}	
					if(appareil2.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(2).setQuantiteConso(appareil2.get(2).getQuantiteConso() + appareil2.get(2).getConsoApp());
					}
				}
				else
				if(appareil2.size() == 4){
					if(appareil2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(0).setQuantiteConso(appareil2.get(0).getQuantiteConso() + appareil2.get(0).getConsoApp());
					}
					if(appareil2.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(1).setQuantiteConso(appareil2.get(1).getQuantiteConso() + appareil2.get(1).getConsoApp());
					}	
					if(appareil2.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(2).setQuantiteConso(appareil2.get(2).getQuantiteConso() + appareil2.get(2).getConsoApp());
					}		
					if(appareil2.get(3).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						appareil2.get(3).setQuantiteConso(appareil2.get(3).getQuantiteConso() + appareil2.get(3).getConsoApp());
					}
				}
				
				//ampoules
				if(ampoules2.size() == 1){
					if(ampoules2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(0).setQuantiteConso(ampoules2.get(0).getQuantiteConso() + ampoules2.get(0).getConsoApp());
					}
				}
				else
				if(ampoules2.size() == 2){
					if(ampoules2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(0).setQuantiteConso(ampoules2.get(0).getQuantiteConso() + ampoules2.get(0).getConsoApp());
					}
					if(ampoules2.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(1).setQuantiteConso(ampoules2.get(1).getQuantiteConso() + ampoules2.get(1).getConsoApp());
					}
				}
				else
				if(ampoules2.size() == 3){
					if(ampoules2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(0).setQuantiteConso(ampoules2.get(0).getQuantiteConso() + ampoules2.get(0).getConsoApp());
					}
					if(ampoules2.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(1).setQuantiteConso(ampoules2.get(1).getQuantiteConso() + ampoules2.get(1).getConsoApp());
					}	
					if(ampoules2.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(2).setQuantiteConso(ampoules2.get(2).getQuantiteConso() + ampoules2.get(2).getConsoApp());
					}
				}
				else
				if(ampoules2.size() == 4){
					if(ampoules2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(0).setQuantiteConso(ampoules2.get(0).getQuantiteConso() + ampoules2.get(0).getConsoApp());
					}
					if(ampoules2.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(1).setQuantiteConso(ampoules2.get(1).getQuantiteConso() + ampoules2.get(1).getConsoApp());
					}	
					if(ampoules2.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(2).setQuantiteConso(ampoules2.get(2).getQuantiteConso() + ampoules2.get(2).getConsoApp());
					}		
					if(ampoules2.get(3).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						ampoules2.get(3).setQuantiteConso(ampoules2.get(3).getQuantiteConso() + ampoules2.get(3).getConsoApp());
					}
				}
				
				//radiateurs
				if(radiateur2.size() == 1){
					if(radiateur2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(0).setQuantiteConso(radiateur2.get(0).getQuantiteConso() + radiateur2.get(0).getConsoApp());
					}
				}
				else
				if(radiateur2.size() == 2){
					if(radiateur2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(0).setQuantiteConso(radiateur2.get(0).getQuantiteConso() + radiateur2.get(0).getConsoApp());
					}
					if(radiateur2.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(1).setQuantiteConso(radiateur2.get(1).getQuantiteConso() + radiateur2.get(1).getConsoApp());
					}
				}
				else
				if(radiateur2.size() == 3){
					if(radiateur2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(0).setQuantiteConso(radiateur2.get(0).getQuantiteConso() + radiateur2.get(0).getConsoApp());
					}
					if(radiateur2.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(1).setQuantiteConso(radiateur2.get(1).getQuantiteConso() + radiateur2.get(1).getConsoApp());
					}	
					if(radiateur2.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(2).setQuantiteConso(radiateur2.get(2).getQuantiteConso() + radiateur2.get(2).getConsoApp());
					}
				}
				else
				if(radiateur2.size() == 4){
					if(radiateur2.get(0).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(0).setQuantiteConso(radiateur2.get(0).getQuantiteConso() + radiateur2.get(0).getConsoApp());
					}
					if(radiateur2.get(1).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(1).setQuantiteConso(radiateur2.get(1).getQuantiteConso() + radiateur2.get(1).getConsoApp());
					}	
					if(radiateur2.get(2).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(2).setQuantiteConso(radiateur2.get(2).getQuantiteConso() + radiateur2.get(2).getConsoApp());
					}		
					if(radiateur2.get(3).isEnMarche()){
						//après chaque seconde, on incrémente la quantité totale consomée par l'ancienne valeur + la consommation
						radiateur2.get(3).setQuantiteConso(radiateur2.get(3).getQuantiteConso() + radiateur2.get(3).getConsoApp());
					}
				}
			}
			
		//Heure + 1h chaque seconde
			
		if(Maison.getInstance().getHeure() == 23){
			Maison.getInstance().setHeure(0);
		}
		else{
			Maison.getInstance().setHeure(Maison.getInstance().getHeure()+1);
		}
			
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
