
public class Hero {
	private static String nom;
	private static String Classe;
	private static int Win;
	private static int Lose;
	private static int numHero;
	public Hero(String nom, int Win , int Lose){
		 this.setNom(nom);
		 this.Win = Win;
		 this.Lose = Lose;
		 switch (nom) {
         case "Valeera Sanguinar":
        	 setClasse("Rogue");setNumHero(0);
             break;
         case "Uther Lightbringer":
        	 setClasse("Paladin");setNumHero(1);
             break;
         case "Malfurion Stormrage":
        	 setClasse("Druid");setNumHero(2);
             break;
         case "Rexxar":
        	 setClasse("Hunter");setNumHero(3);
             break;
         case "Thrall":
        	 setClasse("Shaman");setNumHero(4);
             break;
         case "Garrosh Hellscream":
        	 setClasse("Warrior");setNumHero(5);
             break;
         case "Jaina Proudmoore":
        	 setClasse("Mage");setNumHero(6);
             break;
         case "Gul'dan":
        	 setClasse("Warlock");setNumHero(7);
             break;
         case "Anduin Wrynn":
        	 setClasse("Priest");setNumHero(8);
             break;
     }
		 
		 
	 }
	public  String getClasse() {
		return Classe;
	}
	public  void setClasse(String classe) {
		Classe = classe;
	}
	public  int getWin() {
		return Win;
	}
	public void setWin(int win) {
		Win = win;
	}
	public  int getLose() {
		return Lose;
	}
	public  void setLose(int lose) {
		Lose = lose;
	}
	public  int getNumHero() {
		return numHero;
	}
	public  void setNumHero(int numHero) {
		Hero.numHero = numHero;
	}
	public   String getNom() {
		return nom;
	}
	public  void setNom(String nom) {
		Hero.nom = nom;
	}
	 

}
