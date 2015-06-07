
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Handstone {
	private static final String zipPath = "C:\\Users\\pierre\\Desktop\\TEST";
	private static final String finalPath = "C:\\Users\\pierre\\Desktop\\TEST\\output_log.txt";
	static File folder = new File(zipPath);
	static File[] listOfFiles = folder.listFiles();
	static Carte carte;
	static Heros Herotab = new Heros();
	static Heros Herotabpioche = new Heros();
	static String playerName = "nebrilia";
	static boolean endofmuligan = false;
	private static boolean endofgame = false;
	private static int cartes= 0;
	static List<game> gamelist = new ArrayList<game>();

	static CarteScore scores = new CarteScore();
	public static void main (String[] args){
		for (File file : listOfFiles) {
			
		    if (file.isFile()) {
		    	int firstparenthese = file.toString().indexOf('(');
		    	if(file.toString().indexOf('(') != 0 && file.toString().indexOf('(') != -1){
			    	playerName = (String) file.toString().subSequence(zipPath.length()+1, firstparenthese);
			    	if(!playerName.isEmpty()){
			    		trygame(file.toString(),zipPath);
			    	}
		    	}
		    }
		}
		scores.setHerotab(Herotab);
		System.out.println(scores);
	}

	public static void trygame(String filepath, String zipPath){
	try{
	
	MyZip zip = new MyZip();
	File file = new File(filepath);
	File folder = new File(zipPath);
	zip.unzip(file, folder);
	// Cr�ation du flux buff�ris� sur un FileReader, imm�diatement suivi par un 
	// try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
	// est correctement instanci� (�vite les NullPointerException)
	BufferedReader buff = new BufferedReader(new FileReader(finalPath));
	 
	try {
	String line;
	// Lecture du fichier ligne par ligne. Cette boucle se termine
	// quand la m�thode retourne la valeur null.
	//[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=The Coin id=68 zone=HAND zonePos=5 cardId=GAME_005 player=1] to FRIENDLY HAND
	//cartes tir�s
	game newgame = new game(playerName);
	while ((line = buff.readLine()) != null) {
		if(line.contains("[Power]")){
			if(line.contains("tag=PLAYSTATE value=WON") && line.contains(playerName)){
				newgame.setWin(true);
			}
			if(line.contains("tag=PLAYSTATE value=WON") && !line.contains(playerName)){
				newgame.setWin(false);
			}
		}
		
		if(line.contains("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=") && line.contains("to FRIENDLY HAND")){
			line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=12 zone=HAND zonePos=0 cardId=EX1_402 player=1] to FRIENDLY HAND".length());
			newgame.addCartes(normalize(line));
		}
		if(line.contains("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=")&& line.contains("to FRIENDLY DECK") ){
			line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=27 zone=DECK zonePos=2 cardId=EX1_410 player=1] to FRIENDLY DECK".length());
			newgame.addCartesJetees(normalize(line));
			/*if(newgame.cartes.contains(line))
			{
				newgame.cartes.remove(line);
			}*/
		}
		if(line.contains("cardId=HERO_") && line.contains("TRANSITIONING card [")&& !line.contains("GRAVEYARD")){
			if(line.contains("FRIENDLY")){
				line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=4 zone=PLAY zonePos=0 cardId=HERO_01 player=1] to FRIENDLY PLAY (Hero)".length());
				newgame.setHeroJoueur(normalize(line));
			}
			if(line.contains("OPPOSING")){
				line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=36 zone=PLAY zonePos=0 cardId=HERO_03 player=2] to OPPOSING PLAY (Hero)".length());
				newgame.setHeroAdverse(normalize(line));	
			}
		}
		if(line.contains("[Power] GameState.DebugPrintPower() - TAG_CHANGE Entity=GameEntity tag=STEP value=MAIN_START")){
				endofmuligan = true;
		}		
	}
	scores = newgame.updateCarteScore(scores);
	Herotab = newgame.updateHeros(Herotab);
	
	}finally
	{
	// dans tous les cas, on ferme nos flux
	buff.close();
	}
	}
	catch (IOException ioe) {
	// erreur de fermeture des flux
	System.out.println("Erreur --" + ioe.toString());
	}
}
	
	public static String normalize(String string){
		if(string.endsWith(" id")){
			return string.substring(0,string.length()-3);
		}
		if(string.endsWith(" i")){
			return string.substring(0,string.length()-2);
		}
		if(string.endsWith(" ")){
			return string.substring(0,string.length()-1);	
		}
		else return string;
	}
	public static boolean isEndofgame() {
		return endofgame;
	}
	public static void setEndofgame(boolean endofgame) {
		Handstone.endofgame = endofgame;
	}
	public static int getCartes() {
		return cartes;
	}
	public static void setCartes(int cartes) {
		Handstone.cartes = cartes;
	}

}