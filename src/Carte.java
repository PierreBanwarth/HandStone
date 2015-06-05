
public class Carte {
	// name of the card
	public String nom;
	// name of the player hero
	public String nomjoueur;
	// name of the opponent hero
	public String nomadversaire;
	public String classePlayer;
	public String classeOpponent;
	
	// nomber of win related to this card
	public int win = 0;
	// nomber of lose related to this card
	public int lose = 0;

	public Carte(String string, String heroJoueur, String heroAdverse,
			boolean win ,String classePlayer, String classeOpponent) {
		// TODO Auto-generated constructor stub
		this.nom = string;
		this.nomjoueur = heroJoueur;
		this.nomadversaire = heroAdverse;
		this.classePlayer = classePlayer;
		this.classeOpponent = classeOpponent;
		if(win){
			this.win = 1;
		}else{
			this.lose = 1;
		}
	}
	public String getNomJ(){return nomjoueur;}

	public String getNomA(){return nomadversaire;}
	public String getNomC(){return nom;}
	public int getW(){return win;}
	public int getL(){return lose;}
	public void setW(int a ){win =  a;}
	public void setL(int a ){lose = a;}
	public float getratio(){
		return ((float)getW() / ((float)getW() + (float)getL()))*100; 
	}

	
}
