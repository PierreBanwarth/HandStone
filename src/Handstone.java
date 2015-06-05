
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Handstone {

	private static final String zipPath = "C:\\Users\\tagadatsointsoin\\Desktop\\data HS bordel";
	private static final String finalPath = "C:\\Users\\tagadatsointsoin\\Desktop\\data HS bordel\\output_log.txt";
	private static final String resPath = "C:\\Users\\tagadatsointsoin\\Desktop\\result Handstone\\";
	private static final String resPathMatchup = "C:\\Users\\tagadatsointsoin\\Desktop\\result Handstone\\Matchup\\";
	private static final String resPathClasse = "C:\\Users\\tagadatsointsoin\\Desktop\\result Handstone\\Classe\\";
	
	static int NBgame= 0;
	static File folder = new File(zipPath);
	static File[] listOfFiles = folder.listFiles();
	static Carte carte;
	static Heros Herotab = new Heros();
	static String playerName = "nebrilia";
	static boolean endofmuligan = false;
	private static boolean endofgame = false;
	private static int cartes= 0;
	
	
	static List<game> gamelist = new ArrayList<game>();
	
	public static void main (String[] args){
		Resultat res = new Resultat();
		CarteScore scores = new CarteScore(res);
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
		game g;
		res = new Resultat(Herotab,scores,gamelist);
		System.out.println(gamelist.size());
		for(int i = 0 ; i< gamelist.size();i++){
			g = gamelist.get(i);
			if(g.getHeroJoueur()!= null && g.getHeroAdverse() !=null){
			Herotab.majRatio(g.getHeroJoueur(),g.getHeroAdverse(), g.getWin());
			for(int j = 0; j < g.mainDepart.size();j++){
				if(g.getHeroJoueur() != null && g.getHeroAdverse() != null ){
				int player = Herotab.transition(g.getHeroJoueur());
				
				int opponent = Herotab.transition(g.getHeroAdverse());
				carte = new Carte(g.mainDepart.get(j),g.getHeroJoueur(),g.getHeroAdverse(),g.getWin(),Herotab.getClasseName(player),Herotab.getClasseName(opponent));
				scores.adClasse(carte,player);
				scores.adbis(carte,scores.cartesMatchup);
				}
			}
			}
			
			// on a ajouté
			
			
		}
		res = new Resultat(Herotab,scores,gamelist);
		System.out.println(res.resDebutHtml());
		// on crée le premier fichier matchup
		ecrire(resPath+"MatchupTab.html",res.Tableaumatchup());
		
		for(int classeJ =0;classeJ<9;classeJ++){
			String Classe = res.resClasseHtml(classeJ);
			ecrire(resPathClasse+Herotab.getClasseName(classeJ)+".html",Classe);
			for(int classeP =0;classeP<9;classeP++){
				String s = res.resClasseCarteMatchupHtml(res,classeJ,classeP);
				System.out.println(s);
				ecrire(resPathMatchup+Herotab.getClasseName(classeJ)+Herotab.getClasseName(classeP)+".html",s);
			}
			
		
		}
		String s = res.FinToHtml();
		System.out.println(s);
	
				
}

	public static void trygame(String filepath, String zipPath){
	try{
	
	MyZip zip = new MyZip();
	File file = new File(filepath);
	File folder = new File(zipPath);
	zip.unzip(file, folder);
	// Création du flux bufférisé sur un FileReader, immédiatement suivi par un 
	// try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
	// est correctement instancié (évite les NullPointerException)
	BufferedReader buff = new BufferedReader(new FileReader(finalPath));
	 
	try {
	String line;
	// Lecture du fichier ligne par ligne. Cette boucle se termine
	// quand la méthode retourne la valeur null.
	//[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=The Coin id=68 zone=HAND zonePos=5 cardId=GAME_005 player=1] to FRIENDLY HAND
	//cartes tirés
	game newgame = new game(playerName,Herotab,playerName);
	boolean isArena = false;
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
			/*if(newgame.cartes.contains(line))
			{
				newgame.cartes.remove(line);
			}*/
		}
		if(line.contains("cardId=HERO_") && line.contains("TRANSITIONING card [")&& !line.contains("GRAVEYARD")){
			if(line.contains("FRIENDLY")){
				line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=4 zone=PLAY zonePos=0 cardId=HERO_01 player=1] to FRIENDLY PLAY (Hero)".length());
				newgame.setHeroJoueur(normalize(line));
				newgame.setPlayerClasse(Herotab.getClasseName(Herotab.transition(newgame.getHeroJoueur())));
			}
			if(line.contains("OPPOSING")){
				line = line.substring("[Zone] ZoneChangeList.ProcessChanges() - TRANSITIONING card [name=".length() ,line.length()- "id=36 zone=PLAY zonePos=0 cardId=HERO_03 player=2] to OPPOSING PLAY (Hero)".length());
				newgame.setHeroAdverse(normalize(line));
				newgame.setOpponentClasse(Herotab.getClasseName(Herotab.transition(newgame.getHeroAdverse())));
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
	gamelist.add(newgame);
	//}
	
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
	public static String normalize(String string){
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