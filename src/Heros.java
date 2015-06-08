
public class Heros {
	public Hero[]  HeroTab = new Hero[9];
	public static int persoWin[] = new int[9];
	public static int persoLose[] = new int[9];
	public static int persoWinMatchup[][] = new int[9][9];
	public static int persoLoseMatchup[][] = new int[9][9];
	public Heros(){
		HeroTab[0] = new Hero("Valeera Sanguinar",0,0);
		HeroTab[1] = new Hero("Uther Lightbringer",0,0);
		HeroTab[2] = new Hero("Malfurion Stormrage",0,0);
		HeroTab[3] = new Hero("Rexxar",0,0);
		HeroTab[4] = new Hero("Thrall",0,0);
		HeroTab[5] = new Hero("Garrosh Hellscream",0,0);
		HeroTab[6] = new Hero("Jaina Proudmoore",0,0);
		HeroTab[7] = new Hero("Gul'dan",0,0);
		HeroTab[8] = new Hero("Anduin Wrynn",0,0);
	}
	public void Majratio(int heroname, int opponentName,boolean win){
		
	    if(win){
	    	persoWin[heroname]++;
	    	persoWinMatchup[heroname][opponentName]++;
	    }else{
	    	persoLose[heroname]++;
	    	persoLoseMatchup[heroname][opponentName]++;
	    }
	    
	}

	public double getRatioMatchup(int heroname, int opponentName){

	    return ((double)persoWinMatchup[heroname][opponentName] / ((double)	persoWinMatchup[heroname][opponentName] + (double)persoLoseMatchup[heroname][opponentName]))*100;
	    
	}
	public double getRatioMatchup(String heroname ,String opponentName){
		 int Player  = getNumHero(heroname);
		 int Opponent =  getNumHero(opponentName);
		return getRatioMatchup(Player, Opponent);
	}
	public double getRatio(String heroname){
	   return getRatio(getNumHero(heroname));
	}
	public double getRatio(int heronum){
	    return ((double)	persoWin[heronum] / ((double)	persoWin[heronum] + (double)persoLose[heronum]))*100;
	}
	
	public String getNomHero(int i){
		Hero h = HeroTab[i];
		return h.getNom();
    }
	
	public String getClasseHero(String s){
		Hero h  =  HeroTab[(getNumHero(s))];
		return h.getClasse();
    }
	public String getClasseHero(int i){
		 Hero h  = HeroTab[i];
		 return h.getClasse();
		
   }
	
	public int getNbgame(String heroname){
		Hero h  = HeroTab[(getNumHero(heroname))];
		return h.getWin() + h.getLose();
	   
	}
	public int getNumHero(String s){
		for(int i = 0;i<9;i++){
			if(HeroTab[i].getNom().compareTo(s)==0){
				return i;
			}
		}
		return 0;
	}


}
