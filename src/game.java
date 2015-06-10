import java.sql.ResultSet;
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
	private int numgame;
	private int idGame;
	private int lastID;
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
	public int getLastID(DBConnexion db) throws Exception
	{
		String query = "";
		if(db.isConnected()) {
				query += " SELECT MAX(gameID) FROM games;";
				ResultSet res = db.read(query);
				if (res.next())
				{
				   int idgame = res.getInt("MAX(gameID)");
				   System.out.println(idgame);
				}
				idGame = res.getInt(1);
		}
		query = "";
		return idGame;
	}
	public void exportToDB (DBConnexion db) {
		String query = "";
		

		
		if(db.isConnected()) {
				
				query = "";
				query += "INSERT INTO games ";
				query += "(playerName, opponentName, championPlayed, ChampionOpponent, hasWon, type, date)";
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
				query += type.replace("\'", "&#039");
				query += "', '";
				query +=dateHeure.replace("\'", "&#039");
				query += "');";
				db.modify(query);
				System.out.println(query);
				try {
					lastID = idGame;
					idGame = getLastID(db);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(lastID == idGame -1){
				for(String card : mainDepart) {
					
					query = "";
					query += "INSERT INTO cards ";
					query += "(gameID, CardName, isInStartingHand, wasKeep)";
					query += " values ('";
					query += idGame;
					query += "', '";
					query += card.replace("\'", "&#039");
					query += "', '";
					query += "1";
					query += "', '";
					query += "1";
					query += "');";
					db.modify(query);
				}
				for(String card : cartesjetees) {
					query = "";
					query += "INSERT INTO cards ";
					query += "(gameID, CardID, isInStartingHand, wasKeep)";
					query += " values ('";
					query += idGame;
					query += "', '";
					query += card.replace("\'", "&#039");
					query += "', '";
					query += "1";
					query += "', '";
					query += "0";
					query += "');";
					db.modify(query);
				}
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
	public int getNumgame() {
		return numgame;
	}
	public void setNumgame(int numgame) {
		this.numgame = numgame;
	}
}

