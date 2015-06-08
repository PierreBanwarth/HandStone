
public class Hero {
	private  String nom;
	private String Classe;
	private  int Win;
	private  int Lose;
	private  int numHero;
	public Hero(String nom, int Win , int Lose){
		 this.setNom(nom);
		 
		 this.Win = Win;
		 this.Lose = Lose;
		 if(nom.compareTo("Valeera Sanguinar")==0){
			 this.setClasse("Rogue");this.setNumHero(0);
		 }
		 else if(nom.compareTo("Uther Lightbringer")==0){
			 this.setClasse("Paladin");this.setNumHero(1);
		 }
		 else if(nom.compareTo("Malfurion Stormrage")==0){
			 this.setClasse("Druid");this.setNumHero(2);
		 }
		 else if(nom.compareTo("Rexxar")==0){
			 this.setClasse("Hunter");this.setNumHero(3);}
		 else if(nom.compareTo("Thrall")==0){this.setClasse("Shaman");this.setNumHero(4); }
		 else if(nom.compareTo("Garrosh Hellscream")==0){this.setClasse("Warrior");this.setNumHero(5); }
		 else if(nom.compareTo("Jaina Proudmoore")==0){this.setClasse("Mage");this.setNumHero(6);}
		 else if(nom.compareTo("Gul'dan")==0){this.setClasse("Warlock");this.setNumHero(7); }
		 else if(nom.compareTo("Anduin Wrynn")==0){this.setClasse("Priest");this.setNumHero(8);}
		 
		 
	 }
	public  String getClasse() {
		return Classe;
	}
	public  void setClasse(String classe) {
		this.Classe = classe;
	}
	public  int getWin() {
		return Win;
	}
	public void setWin(int win) {
		this.Win = win;
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
		this.numHero = numHero;
	}
	public   String getNom() {
		return nom;
	}
	public  void setNom(String nom) {
		this.nom = nom;
	}
	
	 

}
