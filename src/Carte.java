
public class Carte implements Comparable<Carte> {
	// name of the card
	public String nom;
	// name of the player hero
	public String nomjoueur;
	// name of the opponent hero
	public String nomadversaire;
	// nomber of win related to this card
	public int win = 0;
	// nomber of lose related to this card
	public int lose = 0;
	public int[] LoseMatchup = new int[9];
	public int[] WinMatchup = new int[9];
	
	public Carte(String string, String heroJoueur, String heroAdverse,
			boolean win) {
		// TODO Auto-generated constructor stub
		this.nom = string;
		this.nomjoueur = heroJoueur;
		this.nomadversaire = heroAdverse;
		if(win){
			this.win = 1;
		}else{
			this.lose = 1;
		}
		for(int i = 0;i<9;i++){
			LoseMatchup[i]=0;
			WinMatchup[i]=0;
		}
	}
	public String getNomJ(){return nomjoueur;}
	
	public String convertNomJ(){
		switch (getNomJ()) {
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
	public String convertNomA(){
		switch (getNomA()) {
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
	public String getNomA(){return nomadversaire;}
	public String getNomC(){return nom;}
	public int getW(){return win;}
	public int getL(){return lose;}
	
	public void setWMatchup(int a, int i ){WinMatchup[i] =  a;}
	public void setLMatchup(int a, int i ){LoseMatchup[i]=  a;}
	
	public int getWMatchup(int i){return WinMatchup[i];}
	public int getLMatchup(int i){return LoseMatchup[i];}
	public void setW(int a ){win =  a;}
	public void setL(int a ){lose = a;}
	public float getratio(){
		return ((float)getW() / ((float)getW() + (float)getL()))*100; 
	}
	public float getratioMatchup(int i){
		return ((float) getWMatchup(i) / ((float)getWMatchup(i) + (float)getLMatchup(i)))*100; 
	}
@Override public String toString() {
	if(getratio()>=0){
			
		   String s ="<tr>";
		   s+="<td align=center>"+getNomC()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+convertNomJ()+"</td>"+System.getProperty("line.separator");
		  // s+="<td align=center>"+convertNomA()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+this.getW()+"|"+this.getL()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+String.format("%.2f", getratio()) +"%"+"</td>"+System.getProperty("line.separator");
		   s+= "</tr>";
		   return s;
	}
	else{
		return "problem : nb win"+getW()+"nb lose"+getL()+System.getProperty("line.separator");
	}
	}
public String toString2(int i) {
	if(getratio()>=0){
			
		   String s ="<tr>";
		   s+="<td align=center>"+getNomC()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+convertNomJ()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+convertNomA()+"</td>"+System.getProperty("line.separator");
		  // s+="<td align=center>"+convertNomA()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+(this.getWMatchup(i)+this.getLMatchup(i))+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+String.format("%.2f", getratioMatchup(i)) +"%"+"</td>"+System.getProperty("line.separator");
		   s+= "</tr>";
		   return s;
	}
	else{
		return "problem : nb win"+getW()+"nb lose"+getL()+System.getProperty("line.separator");
	}
	}
@Override
public int compareTo(Carte arg0) {
	int compJoueur = this.getNomJ().compareTo(arg0.getNomJ());
	int compCarte = this.getNomC().compareTo(arg0.getNomC());
	boolean compRatio =  getratio() > arg0.getratio();
	if(compJoueur == 0){
		if(compRatio){
			return compCarte;
		}
		else{
			return compCarte;
		}
	}
	return compJoueur;
		
	}
	
}