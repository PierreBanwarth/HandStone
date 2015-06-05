import java.util.ArrayList;
import java.util.List;




public class Heros {
	public static List<Hero> HeroTab = new  ArrayList<Hero>();
	public static int persoWin[] = new int[9];
	public static int persoLose[] = new int[9];
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
	public void Majratio(String heroname, boolean win){
	    String nomPerso;
	    Hero h;
	    int Win;
	    int Lose;
		for(int i = 0;i<9;i++){
	    	 h  = HeroTab.get(i);
			nomPerso = h.getNom();
	    	 if(nomPerso.compareTo(heroname)==0){
	    		 if(win){
	    			 Win = h.getWin() +1;
	    			 h.setWin(Win);
	    		 }else{
	    			 Win = h.getWin() +1;
	    			 h.setWin(Win);
	    		 }
	    			 
	    	 }
	         
	     }
	}

	public float getRatio(String heroname){
		String nomPerso;
	    Hero h;
		for(int i = 0;i<9;i++){
	    	 h  = HeroTab.get(i);
			 nomPerso = h.getNom();
	    	 if(nomPerso.compareTo(heroname)==0){
	    		 return ((float)h.getWin() / ((float)h.getWin() + (float)h.getLose()))*100;
	    	 }
	     }
		return 0;
	}
	public float getRatio(int heronum){
		
	    Hero h = HeroTab.get(heronum);
	    return ((float)h.getWin() / ((float)h.getWin() + (float)h.getLose()))*100;
	}
	
	public String getNomHero(int i){
		Hero h = HeroTab.get(i);
		return h.getNom();
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
	
	
	public int getNbgame(String heroname){
		String nomPerso;
	    Hero h;
		for(int i = 0;i<9;i++){
	    	 h  = HeroTab.get(i);
			 nomPerso = h.getNom();
	    	 if(nomPerso.compareTo(heroname)==0){
	    		 return h.getWin() + h.getLose();
	    	 }
	     }
		return 0;
	}


}
