import java.util.ArrayList;
import java.util.List;

public class Heros {
	public static List<Hero> HeroTab = new  ArrayList<Hero>();
	public static int persoWin[] = new int[9];
	public static int persoLose[] = new int[9];
	public static int persoWinMatchup[][] = new int[9][9];
	public static int persoLoseMatchup[][] = new int[9][9];
	public Heros(){
		HeroTab.add(0,new Hero("Valeera Sanguinar",0,0));
		HeroTab.add(1,new Hero("Uther Lightbringer",0,0));
		HeroTab.add(2,new Hero("Malfurion Stormrage",0,0));
		
		HeroTab.add(3,new Hero("Rexxar",0,0));
		HeroTab.add(4,new Hero("Thrall",0,0));
		HeroTab.add(5,new Hero("Garrosh Hellscream",0,0));
		
		HeroTab.add(6,new Hero("Jaina Proudmoore",0,0));
		HeroTab.add(7,new Hero("Gul'dan",0,0));
		HeroTab.add(8,new Hero("Anduin Wrynn",0,0));
		
		
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

	public float getRatioMatchup(int heroname, int opponentName){

	    return ((float)	persoWinMatchup[heroname][opponentName] / ((float)	persoWinMatchup[heroname][opponentName] + (float)persoLoseMatchup[heroname][opponentName]))*100;
	    
	}
	public float getRatioMatchup(String heroname ,String opponentName){
		 int Player  = getNumHero(heroname);
		 int Opponent =  getNumHero(opponentName);
		return getRatioMatchup(Player, Opponent);
	}
	public float getRatio(String heroname){
		
	    Hero h  = HeroTab.get(getNumHero(heroname));
	    return ((float)h.getWin() / ((float)h.getWin() + (float)h.getLose()))*100;
	}
	public float getRatio(int heronum){
		
	    Hero h = HeroTab.get(heronum);
	    return ((float)h.getWin() / ((float)h.getWin() + (float)h.getLose()))*100;
	}
	
	public String getNomHero(int i){
		Hero h = HeroTab.get(i);
		return h.getNom();
    }
	
	public String getClasseHero(String s){
		 Hero h  = HeroTab.get(getNumHero(s));
		 return h.getClasse();
		
    }
	public int getNbgame(String heroname){
		Hero h  = HeroTab.get(getNumHero(heroname));
		return h.getWin() + h.getLose();
	   
	}
	public int getNumHero(String s){
		int res = 0;
		for(int j = 0;j<9;j++){
			if(s.compareTo(HeroTab.get(j).getNom())==0){
				res = j;
			}
		}
		return res;
    }


}
