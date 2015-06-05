


public class Heros {
	// GLobal WIn Rate
	private static int globalWin = 0;
	private static int globalLoose = 0;
	// Classe WIN RATE
	public static int persoWin[] = new int[9];
	public static int persoLose[] = new int[9];
	// Matchup Win RATE
	public static int persoMatchupWin[][] = new int[9][9];
	public static int persoMatchupLose[][] = new int[9][9];
	public Heros(){
		for(int i = 0;i<9;i++){
			persoMatchupWin[i] = new int[9];
			persoMatchupLose[i]= new int[9];
		}
		
	}
	public int getWinMatchup(int s1, int s2){
		
		return persoMatchupWin[(s1)][(s2)];
	}
	public int getLoseMatchup(int s1, int s2){
		
		return persoMatchupLose[(s1)][(s2)];
	}
	public int getnbMatchup(int s1, int s2){
		
		return persoMatchupLose[s1][s2] + persoMatchupWin[s1][s2];
	}
	public String getClasseName(int heronum){
		switch (heronum) {
        case 0:
            return "Rogue";
        case 1:
        	return "Paladin";
        case 2:
        	return "Druid";
        case 3:
        	return "Hunt";
        case 4:
        	return "Chaman";
        case 5:
        	return "Warrior";
        case 6:
        	return "Mage";
        case 7:
        	return "Warlock";
        case 8:
        	return "Priest";
    }
		return "Priest";
	}
	public int transition(String heroname){
		switch (heroname) {
        case "Valeera Sanguinar":
            return 0;
        case "Uther Lightbringer":
        	return 1;
        case "Malfurion Stormrage":
        	return 2;
        case "Rexxar":
        	return 3;
        case "Thrall":
        	return 4;
        case "Garrosh Hellscream":
        	return 5;
        case "Jaina Proudmoore":
        	return 6;
        case "Gul'dan":
        	return 7;
        case "Anduin Wrynn":
        	return 8;
    }
		return 0;	
	}
	public String transition(int heronum){
		switch (heronum) {
        case 0:
            return "Valeera Sanguinar";
        case 1:
        	return "Uther Lightbringer";
        case 2:
        	return "Malfurion Stormrage";
        case 3:
        	return "Rexxar";
        case 4:
        	return "Thrall";
        case 5:
        	return "Garrosh Hellscream";
        case 6:
        	return "Jaina Proudmoore";
        case 7:
        	return "Gul'dan";
        case 8:
        	return "Anduin Wrynn";
    }
		return "Anduin Wrynn";
		
		
		
	}
	public void majRatio(String heroname,String heroadverse, boolean Win){
                if(Win){
                	persoMatchupWin[transition(heroname)][transition(heroadverse)]++;
                	persoWin[transition(heroname)]++;
                	setGlobalWin(getGlobalWin() + 1);
                }else{
                	persoMatchupLose[transition(heroname)][transition(heroadverse)]++;
                	persoLose[transition(heroname)]++;
                	setGlobalLoose(getGlobalLoose() + 1);
               }
             
		
	}
	public float getRatio(int HeroNum){
		return ((float)persoWin[HeroNum] / ((float)persoWin[HeroNum] + (float)persoLose[HeroNum]))*100;
	     
	}
	public float getRatio(int HeroNum, int OpponentNum){
		return ((float)persoMatchupWin[HeroNum][OpponentNum] / ((float)persoMatchupWin[HeroNum][OpponentNum] + (float)persoMatchupLose[HeroNum][OpponentNum]))*100;
	     
	}
	public int getNbgame(int HeroNum){
	     return persoWin[HeroNum] + persoLose[HeroNum];
	}

	public int getNbgameMatchup(int HeroNum , int opponent){
	     return persoMatchupWin[HeroNum][opponent] + persoMatchupWin[HeroNum][opponent];
	}
	public static int getGlobalWin() {
		return globalWin;
	}
	public static void setGlobalWin(int globalWin) {
		Heros.globalWin = globalWin;
	}
	public static int getGlobalLoose() {
		return globalLoose;
	}
	public static void setGlobalLoose(int globalLoose) {
		Heros.globalLoose = globalLoose;
	}



}
