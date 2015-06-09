import java.util.ArrayList;
import java.util.List;


public class game {
	private String NomGame;
	String nomjoueur;
	List<String> cartes = new ArrayList<String>(); 
	List<String> cartesjetees = new  ArrayList<String>();
	List<String> mainDepart = new  ArrayList<String>(); 
	CarteScore carteScore;
	private Boolean win = false;
	private String heroJoueur;
	private  String heroAdverse;
	private int nbcartesdepart = 3;
	private String playerName;
	private String opponentName;
	private String dateHeure;
	private String type;
	public game(String name){
		nomjoueur = name;
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
	public void addCartesJetees(String s)
	{
		normalize(s);
		cartesjetees.add(s);
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
		
		for(int i = 0 ; i< cartesjetees.size();i++){
			s = s + "\n cartes jet�s" + cartesjetees.get(i);
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
	public CarteScore updateCarteScore(CarteScore score){
		for(int j = 0; j < this.mainDepart.size();j++){
			Carte carte = new Carte(this.mainDepart.get(j),this.getHeroJoueur(),this.getHeroAdverse(),this.getWin());
			score.adbis(carte);
		}
		return score;
	}
	public Heros updateHeros(Heros Herotab){
	 Herotab.Majratio(Herotab.getNumHero(this.getHeroJoueur()),Herotab.getNumHero(this.getHeroAdverse()), this.getWin());
	 return Herotab;
	}
	public int toInt(boolean b){
		if(b){return 1;}
		else{return 0;}
	}
	public void exportToDB (DBConnexion db) {
		String query = "";
		
		if(db.isConnected()) {
			
				query = "";
				query += "INSERT INTO gameData ";
				query += "(playerName, opponentName, championPlayed, ChampionOpponent, hasWon, sourceFile, type, date)";
				query += " values ('";
				query += playerName.replace("\'", "&#039");
				query += "', '";
				query += opponentName.replace("\'", "&#039");
				query += "', '";
				query += heroJoueur.replace("\'", "&#039");
				query += "', '";
				query += heroAdverse.replace("\'", "&#039");
				query += "', '";
				query += toInt(win);
				query += "', '";
				query += NomGame.replace("\'", "&#039");
				query += "', '";
				query += type.replace("\'", "&#039");
				query += "', '";
				query +=dateHeure.replace("\'", "&#039");
				query += "');";
				System.out.println(query.substring(query.lastIndexOf("(")));
				db.modify(query);
			
			for(String card : mainDepart) {
				query = "";
				query += "INSERT INTO cards ";
				query += "(cardID, isInStartingHand, wasKeep, sourceFile)";
				query += " values ('";
				query += card.replace("\'", "&#039");
				query += "', '";
				query += "1";
				query += "', '";
				query += "1";
				query += "', '";
				query += NomGame.replace("\'", "&#039");
				query += "');";
				System.out.println(query.substring(query.lastIndexOf("(")));
				db.modify(query);
			}
			for(String card : cartesjetees) {
				query = "";
				query += "INSERT INTO cards ";
				query += "(cardID, isInStartingHand, wasKeep, sourceFile)";
				query += " values ('";
				query += card.replace("\'", "&#039");
				query += "', '";
				query += "1";
				query += "', '";
				query += "0";
				query += "', '";
				query += NomGame.replace("\'", "&#039");
				query += "');";
				System.out.println(query.substring(query.indexOf("(")));
				db.modify(query);
			}
		}
	}
	
	public String getNomGame() {
		return NomGame;
	}
	public void setNomGame(String nomGame) {
		NomGame = nomGame;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getOpponentName() {
		return opponentName;
	}
	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}
	public String getDateHeure() {
		return dateHeure;
	}
	public void setDateHeure(String dateHeure) {
		this.dateHeure = dateHeure;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

