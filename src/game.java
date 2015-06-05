import java.util.ArrayList;
import java.util.List;


public class game {
	
	String nomjoueur;
	List<String> cartes = new ArrayList<String>(); 
	List<String> cartesjet�es = new  ArrayList<String>();
	List<String> mainDepart = new  ArrayList<String>(); 
	CarteScore carteScore;
	private boolean arena = false;
	private boolean win = false;
	private String playerName;
	
	private String heroJoueur;
	private  String heroAdverse;
	private String PlayerClasse;
	private String OpponentClasse;
	private int nbcartesdepart = 3;
	private Heros Herotab;
	
	public game(String name, Heros Herotab, String playerName){
		nomjoueur = name;
		this.setHerotab(Herotab);	
		this.playerName = playerName;
	}
	public void addCartes(String s){
		if(s.endsWith(" i")){
			s =(String) s.subSequence(0,s.length()-2);
		}
		if(s.contains("Coin")){nbcartesdepart++;}
		if(nbcartesdepart > 0){
			mainDepart.add(s);
			nbcartesdepart--;
		}
		cartes.add(s);
		
	}
	public void addCartesJet�es(String s)
	{
		normalize(s);
		cartesjet�es.add(s);
		mainDepart.remove(s);
		nbcartesdepart++;
	}
	// quand on sais la win on ajoute toute les cartes dans la main de depart dans carte score
	public void setWin(boolean b){

		win = b;
	}
	public boolean getWin(){
		return win;
		
	}
	public String getHeroJoueur() {
		return heroJoueur;
	}
	public void setHeroJoueur(String s) {
		normalize(s);
		this.heroJoueur = s;
	}
	public String getHeroAdverse() {
		
		return heroAdverse;
		
	}
	public void setHeroAdverse(String s) {
		normalize(s);
		this.heroAdverse = s;
	}
	@Override public String toString() {
		
		String s ="\n -------------------------------------------------------------------------\n";
		s+=" Partie de "+nomjoueur + "\n" + "Hero joueur : "+ heroJoueur + "\nHero de l'adversaire : " + heroAdverse;
		
		for(int i = 0 ; i< cartesjet�es.size();i++){
			s = s + "\n cartes jet�s" + cartesjet�es.get(i);
		}
		s = s + "\n Main de d�part \n";
		for(int i = 0 ; i< mainDepart.size() ;i++){
			s +=  mainDepart.get(i) + "\n";
		}
		if(win)
			{s +=  "partie gagn� \n";}
		else{s +=  "partie perdu \n";}
		s +="\n -------------------------------------------------------------------------\n";
		return s;
	}
	public static String normalize(String string){
		if(string.endsWith(" i")){
			return string.substring(0,string.length()-2);
		}
		if(string.endsWith(" ")){
			
			return string.substring(0,string.length()-1);	
		}
		else return string;
	}
	public boolean isArena() {
		return arena;
	}
	public void setArena(boolean arena) {
		this.arena = arena;
	}
	public Heros getHerotab() {
		return Herotab;
	}
	public void setHerotab(Heros herotab) {
		Herotab = herotab;
	}
	public String getPseudoJoueur() {
		return playerName;
	}
	public void setPseudoJoueur(String pseudoJoueur) {
		playerName = pseudoJoueur;
	}
	public String getPlayerClasse() {
		return PlayerClasse;
	}
	public void setPlayerClasse(String joueurClasse) {
		PlayerClasse = joueurClasse;
	}
	public String getOpponentClasse() {
		return OpponentClasse;
	}
	public void setOpponentClasse(String opponentClasse) {
		OpponentClasse = opponentClasse;
	}
}

