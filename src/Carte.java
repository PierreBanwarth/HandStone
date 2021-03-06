
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
	private Heros heros = new Heros();
	public Carte(String string, String heroJoueur, String heroAdverse,
			boolean win) {
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
	
	public String convert(String s){
		return heros.getClasseHero(s);
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
	public double getratio(){
		return ((double)getW() / ((double)getW() + (double)getL()))*100; 
	}
	public double getratioMatchup(int i){
		return ((double) getWMatchup(i) / ((double)getWMatchup(i) + (double)getLMatchup(i)))*100; 
	}
	public void setNomC(String s){
		this.nom = s;
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