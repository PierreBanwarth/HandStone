import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class fileManager {
	private static final String zipPath = "C:\\Users\\tagadatsointsoin\\Desktop\\data HS bordel";
	private static final String finalPath = "C:\\Users\\tagadatsointsoin\\Desktop\\data HS bordel\\output_log.txt";
	private static final String resPath = "C:\\Users\\tagadatsointsoin\\Desktop\\result Handstone\\";
	private static final String resPathMatchup = "C:\\Users\\tagadatsointsoin\\Desktop\\result Handstone\\Matchup\\";
	private static final String resPathClasse = "C:\\Users\\tagadatsointsoin\\Desktop\\result Handstone\\Classe\\";

	private static String playerName;
	//StringConversion stringTools = new stringTools();
	static Heros herotab;
	private static MyZip zip;
	public fileManager(Heros herotab, String playerName){
		this.setPlayerName(playerName);
		this.herotab = herotab;
		
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
	public static void ecrire(String nomFic, String texte)
	{
		//on va chercher le chemin et le nom du fichier et on me tout ca dans un String
		String adressedufichier = nomFic;
	
		//on met try si jamais il y a une exception
		try
		{
			/**
			 * BufferedWriter a besoin d un FileWriter, 
			 * les 2 vont ensemble, on donne comme argument le nom du fichier
			 * true signifie qu on ajoute dans le fichier (append), on ne marque pas par dessus 
			 
			 */
			FileWriter fw = new FileWriter(adressedufichier, true);
			
			// le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
			BufferedWriter output = new BufferedWriter(fw);
			
			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
			output.write(texte);
			//on peut utiliser plusieurs fois methode write
			
			output.flush();
			//ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter
			
			output.close();
			//et on le ferme
			System.out.println("fichier créé");
		}
		catch(IOException ioe){
			System.out.print("Erreur : ");
			ioe.printStackTrace();
			}

	}
	// this function is used in Handstone.java to create the first list of card
	public static game trygame(String filepath, String zipPath){
			boolean endofmuligan;
			zip = new MyZip();
			File file = new File(filepath);
			File folder = new File(zipPath);
			try {
				MyZip.unzip(file, folder);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Création du flux bufférisé sur un FileReader, immédiatement suivi par un 
			// try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
			// est correctement instancié (évite les NullPointerException)
			BufferedReader buff = null;
			try {
				buff = new BufferedReader(new FileReader(finalPath));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	
			String line;
			// 	Lecture du fichier ligne par ligne. Cette boucle se termine
			// quand la méthode retourne la valeur null.
			//[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=The Coin id=68 zone=HAND zonePos=5 cardId=GAME_005 player=1] to FRIENDLY HAND
			//cartes tirés
			game newgame = new game(playerName);
			boolean isArena = false;
			try {
				while ((line = buff.readLine()) != null) {
					newgame.setArena(line.contains("[Bob] ---RegisterScreenForge---"));
					if(line.contains("[Bob] ---RegisterScreenForge---")){
					isArena = true;
						}			
						if(line.contains("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=") && line.contains("to FRIENDLY HAND")){
							line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=12 zone=HAND zonePos=0 cardId=EX1_402 player=1] to FRIENDLY HAND".length());
							newgame.addCartes(normalize(line));
						}
						if(line.contains("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=")&& line.contains("to FRIENDLY DECK") ){
							line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=27 zone=DECK zonePos=2 cardId=EX1_410 player=1] to FRIENDLY DECK".length());
							newgame.addCartesJetées(normalize(line));
						}
						if(line.contains("cardId=HERO_") && line.contains("TRANSITIONING card [")&& !line.contains("GRAVEYARD")){
							if(line.contains("FRIENDLY")){
								line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=4 zone=PLAY zonePos=0 cardId=HERO_01 player=1] to FRIENDLY PLAY (Hero)".length());
								newgame.setHeroJoueur(normalize(line));
								newgame.setPlayerClasse(herotab.getClasseName(herotab.transition(newgame.getHeroJoueur())));
							}
							if(line.contains("OPPOSING")){
									line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=36 zone=PLAY zonePos=0 cardId=HERO_03 player=2] to OPPOSING PLAY (Hero)".length());
									newgame.setHeroAdverse(normalize(line));
									newgame.setOpponentClasse(herotab.getClasseName(herotab.transition(newgame.getHeroAdverse())));
							}
						}
						if(line.contains("[Power] GameState.DebugPrintPower() - TAG_CHANGE Entity=GameEntity tag=STEP value=MAIN_START")){
							endofmuligan = true;
						}
						if(line.contains("[Power]")){
							if(line.contains("tag=PLAYSTATE value=WON") && line.contains(playerName)){	
								newgame.setWin(true);
							}
							if(line.contains("tag=PLAYSTATE value=WON") && !line.contains(playerName)){
								newgame.setWin(false);
							}
					
						}
					}
				//if(!isArena){	
			
				//}
			}catch (IOException e){
				e.printStackTrace();
			}
			return  newgame;
	}
	public static String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		fileManager.playerName = playerName;
	}
}
	