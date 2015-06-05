import java.util.ArrayList;
import java.util.List;

public class CarteScore {
	List<Carte> cartesMatchup = new ArrayList<Carte>();
	
	List<Carte> carteMatchupPioche = new ArrayList<Carte>();
	Heros herotab = new Heros();
	Heros herotabpiochhe = new Heros();
	
	int nbcarteidentique = 0;
	int nbgameMuligan;
	int nbgameMatchup;
	float ratioMuligan;
	float ratioMatchup;
	float deltamoin; 
	float deltaplus; 
	float ratiopioche;
	public CarteScore(){
	}
	
	@Override public String toString() {
		float deltaratio;
		float ratiopioche;
		float ratioMatchup = 0;
		int nbgameTot;
		int nbWinTot;
		 String s = "<HTML>"+System.getProperty("line.separator");
		 s += "<BODY>"+System.getProperty("line.separator");
		 
			
		for(int hero = 1; hero <10;hero++){
			 
			 s += "<table cellspacing='0'> <!-- cellspacing='0' is important, must stay -->";
			 s += "<CAPTION> <b>"+convertNomJ(herotab.getNomHero(hero))+" (win rate with = "+ herotab.getRatio(hero)+")</b></CAPTION>";
			 s += "<!-- Table Header -->";
		     s += "<thead><tr>";
			 s += "<th align=center> Name of the carte </th>";
			 s += "<th align=center> Hero played </th>";
			 s += "<th align=center> Number of game played </th>";
			 s += "<th align=center> Win rate </th>";
			 s += "<th align=center> Win rate variation </th>";
			 s += "<th align=center> Confidence Interval </th>";
			 s +="</tr></thead><!-- Table Header -->";
			 s +="<!-- Table Body --><tbody>";
			for(int i =0;i<cartesMatchup.size();i++){
				nbgameMuligan = cartesMatchup.get(i).getW()+cartesMatchup.get(i).getL();
				if(cartesMatchup.get(i).getNomJ().compareTo(herotab.getNomHero(hero))==0 && nbgameMuligan > 10)
				{
					   s+= "<tr>";
					   s+= cartesMatchup.get(i).toString();
					   deltaratio =  cartesMatchup.get(i).getratio() - this.herotab.getRatio(cartesMatchup.get(i).getNomJ());
					  
					   if(deltaratio>0){
						   s+= "<td align=center><font size=\"2\" color=\"green\">"+String.format("%.2f", deltaratio)+"</font></td>";
					   }else{
						   s+= "<td align=center><font size=\"2\" color=\"red\">"+String.format("%.2f", deltaratio)+"</font></td>"+System.getProperty("line.separator");
					   }
					   nbgameMuligan = cartesMatchup.get(i).getW()+cartesMatchup.get(i).getL();
					   ratioMuligan = cartesMatchup.get(i).getratio();
					   nbgameMatchup=this.herotab.getNbgame(cartesMatchup.get(i).getNomJ());
					   ratioMatchup = (int) this.herotab.getRatio(cartesMatchup.get(i).getNomJ());
					   deltamoin = deltaratio - (float) getIntervalle((float)(ratioMuligan/100.0),nbgameMuligan) - (float) getIntervalle((float)(ratioMatchup/100.0),nbgameMatchup); 
					   deltaplus = deltaratio + (float) getIntervalle((float)(ratioMuligan/100.0),nbgameMuligan) + (float) getIntervalle((float)(ratioMatchup/100.0),nbgameMatchup); 
					   
					   s+="<td align=center>["+ String.format("%.2f",deltamoin)+";"+  String.format("%.2f",deltaplus)+"]</td>"+System.getProperty("line.separator");
					   /*
					   for(int j = 0; j<carteMatchupPioche.size();j++){
						   if(carteMatchupPioche.get(j).getNomC().compareTo(carteMatchupPioche.get(i).getNomC())==0 &&  carteMatchupPioche.get(j).getNomJ().compareTo(carteMatchupPioche.get(i).getNomJ())==0){
							   
							   nbWinTot = (carteMatchupPioche.get(j).getW());
							   nbgameTot = (carteMatchupPioche.get(j).getW() + carteMatchupPioche.get(j).getL());
							   ratiopioche =calculratiopourcent(nbWinTot,nbgameTot) - ratioMatchup;
							   if(ratiopioche>0){
								   s+= "<td><font size=\"2\" color=\"green\">"+ratiopioche+"</font></td>"+System.getProperty("line.separator");
							   }else{
								   s+= "<td><font size=\"2\" color=\"red\">"+ratiopioche+"</font></td>"+System.getProperty("line.separator");
							   }
							   s+="<td>"+nbWinTot+"</td>"+System.getProperty("line.separator");
							   s+="<td>"+nbgameTot+"</td>"+System.getProperty("line.separator");
							   s+="<td>"+carteMatchupPioche.get(j).getW()+"</td>"+System.getProperty("line.separator");
							   s+="<td>"+carteMatchupPioche.get(j).getL()+"</td>"+System.getProperty("line.separator");
							   s+="<td>"+cartesMatchup.get(i).getW()+"</td>"+System.getProperty("line.separator");
							   s+="<td>"+cartesMatchup.get(i).getL()+"</td>"+System.getProperty("line.separator");
						   }
					   }*/	
					   s+= "</tr>";
				}
				
			}

			
		}
		s += "</tr>";
		s += "</tbody>";
		s += "</table>";
		s += "</BODY></HTML>";
		
		return s;
	}
	public double getIntervalle(float winrate, int nbgame){
		return 1.96 * Math.sqrt(winrate * (1-winrate)/(double)nbgame);
	}
	public float calculratiopourcent(int win , int loose){
		return (float) (calculratio(win,loose) * 100.0) ;
	}
	public float calculratio(int win , int loose){
		return (float)(win / ((float)win+(float)loose));
	}

	public void adbis(Carte carte ,List<Carte> cartesMatchup) {
		boolean memeA;
		boolean memeJ;
		boolean memenom;
		boolean trouvé = false;
		
		int i = 0;
		while(i<cartesMatchup.size() && trouvé == false){
				trouvé = false;
				memenom = cartesMatchup.get(i).getNomC().compareTo(carte.getNomC())==0;
				memeJ = cartesMatchup.get(i).getNomJ().compareTo(carte.getNomJ())==0;
				memeA = cartesMatchup.get(i).getNomA().compareTo(carte.getNomA())==0;
				
			if(memenom && memeJ && memeA){
				carte.setW(carte.getW()+cartesMatchup.get(i).getW());
				carte.setL(carte.getL()+cartesMatchup.get(i).getL());
				cartesMatchup.remove(i);
				cartesMatchup.add(carte);
				trouvé = true;
			}
		i++;
		}
		if(!trouvé){
			cartesMatchup.add(carte);
		}
	}
	public String convertNomJ(String s){
		switch (s) {
        case "Valeera Sanguinar":
            return "Rogue";
        case "Uther Lightbringer":
       	 return "Paladin";
        case "Malfurion Stormrage":
       	 return "Druid";
        case "Rexxar":
       	 return "Hunt"; 
        case "Thrall":
       	 return "Chaman"; 
        case "Garrosh Hellscream":
       	 return "Warrior"; 	        	
        case "Jaina Proudmoore":
       	 return "Mage";
        case "Gul'dan":
       	 return "Warlock";    
        case "Anduin Wrynn":
       	 return "Priest"; 

		}
	return "";
	}

	public void setHerotabPioche(Heros herotab2) {
		// TODO Auto-generated method stub
		this.herotabpiochhe = herotab2;
	}
	public void setHerotab(Heros h){
		this.herotab = h;
	}
}


