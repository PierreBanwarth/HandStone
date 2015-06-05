


public class Heros {
	
	public static int persoWin[] = new int[9];
	public static int persoLose[] = new int[9];
	
	public void majRatio(String heroname, boolean win){
	     switch (heroname) {
	         case "Valeera Sanguinar":
	             if(win){persoWin[0]++;}else{persoLose[0]++;}
	             break;
	         case "Uther Lightbringer":
	             if(win){persoWin[1]++;}else{persoLose[1]++;}
	             break;
	         case "Malfurion Stormrage":
	             if(win){persoWin[2]++;}else{persoLose[2]++;}
	             break;
	         case "Rexxar":
	             if(win){persoWin[3]++;}else{persoLose[3]++;}
	             break;
	         case "Thrall":
	             if(win){persoWin[4]++;}else{persoLose[4]++;}
	             break;
	         case "Garrosh Hellscream":
	             if(win){persoWin[5]++;}else{persoLose[5]++;}
	             break;
	         case "Jaina Proudmoore":
	             if(win){persoWin[6]++;}else{persoLose[6]++;}
	             break;
	         case "Gul'dan":
	             if(win){persoWin[7]++;}else{persoLose[7]++;}
	             break;
	         case "Anduin Wrynn":
	             if(win){persoWin[8]++;}else{persoLose[8]++;}
	             break;
	     }
	}

	public float getRatio(String heroname){
	     switch (heroname) {
	         case "Valeera Sanguinar":
	             return ((float)persoWin[0] / ((float)persoWin[0] + (float)persoLose[0]))*100;
	         case "Uther Lightbringer":
	        	 return ((float)persoWin[1] / ((float)persoWin[1] + (float)persoLose[1]))*100; 
	         case "Malfurion Stormrage":
	        	 return ((float)persoWin[2] / ((float)persoWin[2] + (float)persoLose[2]))*100; 
	         case "Rexxar":
	        	 return ((float)persoWin[3] / ((float)persoWin[3] + (float)persoLose[3]))*100; 
	         case "Thrall":
	        	 return ((float)persoWin[4] / ((float)persoWin[4] + (float)persoLose[4]))*100; 
	         case "Garrosh Hellscream":
	        	 return ((float)persoWin[5] / ((float)persoWin[5] + (float)persoLose[5]))*100; 	        	
	         case "Jaina Proudmoore":
	        	 return ((float)persoWin[6] / ((float)persoWin[6] + (float)persoLose[6]))*100; 	
	         case "Gul'dan":
	        	 return ((float)persoWin[7] / ((float)persoWin[7] + (float)persoLose[7]))*100;    
	         case "Anduin Wrynn":
	        	 return ((float)persoWin[8] / ((float)persoWin[8] + (float)persoLose[8]))*100; 

	     }
		return 0;
	}
	public float getRatio(int heroname){
	     switch (heroname) {
	         case 1:
	             return ((float)persoWin[0] / ((float)persoWin[0] + (float)persoLose[0]))*100;
	         case 2:
	        	 return ((float)persoWin[1] / ((float)persoWin[1] + (float)persoLose[1]))*100; 
	         case 3:
	        	 return ((float)persoWin[2] / ((float)persoWin[2] + (float)persoLose[2]))*100; 
	         case 4:
	        	 return ((float)persoWin[3] / ((float)persoWin[3] + (float)persoLose[3]))*100; 
	         case 5:
	        	 return ((float)persoWin[4] / ((float)persoWin[4] + (float)persoLose[4]))*100; 
	         case 6:
	        	 return ((float)persoWin[5] / ((float)persoWin[5] + (float)persoLose[5]))*100; 	        	
	         case 7:
	        	 return ((float)persoWin[6] / ((float)persoWin[6] + (float)persoLose[6]))*100; 	
	         case 8:
	        	 return ((float)persoWin[7] / ((float)persoWin[7] + (float)persoLose[7]))*100;    
	         case 9:
	        	 return ((float)persoWin[8] / ((float)persoWin[8] + (float)persoLose[8]))*100; 

	     }
		return 0;
	}
	public String getNomHero(int i){
		switch (i) {
        case 1:
            return"Valeera Sanguinar";
        case 2:
       	 return "Uther Lightbringer";
        case 3:
       	 return "Malfurion Stormrage";
        case 4:
       	 return "Rexxar"; 
        case 5:
       	 return "Thrall"; 
        case 6:
       	 return "Gul'dan"; 	        	
        case 7:
       	 return "Anduin Wrynn";
        case 8:
       	 return "Jaina Proudmoore";    
        case 9:
       	 return "Garrosh Hellscream"; 

    }
	return "";
	}
	public int getNbgame(String heroname){
	     switch (heroname) {
	         case "Valeera Sanguinar":
	             return persoWin[0] + persoLose[0];
	         case "Uther Lightbringer":
	        	 return persoWin[1] + persoLose[1];
	         case "Malfurion Stormrage":
	        	 return persoWin[2] + persoLose[2];
	         case "Rexxar":
	        	 return persoWin[3] + persoLose[3]; 
	         case "Thrall":
	        	 return persoWin[4] +persoLose[4]; 
	         case "Garrosh Hellscream":
	        	 return persoWin[5] + persoLose[5]; 	        	
	         case "Jaina Proudmoore":
	        	 return persoWin[6] + persoLose[6];
	         case "Gul'dan":
	        	 return persoWin[7] + persoLose[7];    
	         case "Anduin Wrynn":
	        	 return persoWin[8] + persoLose[8]; 

	     }
		return 0;
	}


}
