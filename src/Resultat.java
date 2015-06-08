import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Resultat {
	private Heros herotab;
	private float ConfianceIntervalle = 0;
	private CarteScore carteScore;
	private List<game> gamelist = new ArrayList<game>();
	Translate translater  = new Translate();
	private int nbgameCarte;
	private int nbgameHero;
	private int nbGameMatchup;
	private int nbGameCarteMatchup;
	private int carteMatchupWin;
	private int carteMatchupLose;
	private int nbcarteMatchup;
	private float ratioCarte;
	private float ratioMatchup;
	private float deltamoin; 
	private float deltaplus; 
	private float ratiopioche;
	private float deltaratioHeroCarte;
	private float ratioCarteMatchup;
	private float ratioHero;
	private float deltaratioHeroCarteMatchup;
	private int nbWinMatchup;
	private int nbLoseMatchup;
	public HTMLgenerator html = new HTMLgenerator();
	private float deltaratioCarte;
	CarteScore score;
	HTMLgenerator sortieHTML = new HTMLgenerator();
	public Resultat(Heros Herotab,
					CarteScore carteScore){
		
		this.herotab = Herotab;
		score  = carteScore;
	}
	
	public String Calcule(){
		HTMLgenerator h = new HTMLgenerator();
		
		Map<String , Carte> carteClasse =   new HashMap<String, Carte>();
		Map<String , Carte> carteMatchup =  new HashMap<String, Carte>();
		
		carteClasse = score.getCarteClasse();
		carteMatchup = (score.getCarteMatchup());
		
		List<String> cles = new ArrayList<String>(carteClasse.keySet());
		Collections.sort(cles, new CarteComparator(carteClasse));
		Carte c;
		
		for(String id : cles){
			c = carteClasse.get(id);
			// calcul des données par rapport au ratio du héro
			int numhero = herotab.getNumHero(c.getNomJ());
			float deltaratio = herotab.getRatio(numhero) - c.getratio();
			double intervalle  = intervalle(c.getW(),c.getL());
			if(c.getW()+c.getL()>0){
				h.CarteToHtmlClasse(c , numhero ,deltaratio ,  intervalle);
			}
		}
		
		cles = new ArrayList<String>(carteMatchup.keySet());
		Collections.sort(cles, new CarteComparator(carteMatchup));
		
		for(String id : cles){
			int i = herotab.getNumHero(carteMatchup.get(id).getNomA());
			c = carteMatchup.get(id);
			c.setNomC(translater.ToEnglish(c.getNomC()));
			int numhero = herotab.getNumHero(c.getNomJ());
			int numheroAdverse = herotab.getNumHero(c.getNomA());
			float deltaratio = herotab.getRatioMatchup(numhero , numheroAdverse) - c.getratio();
			double intervalle  = intervalle(c.getW(),c.getL());
			
			if(c.getLMatchup(i) + c.getWMatchup(i)>0){
				h.CarteToHtmlMatchup(c ,numhero , numheroAdverse, i ,deltaratio ,  intervalle);
			}
		}
		return h.toString();
		
	}
	
	
	public String FinToHtml(){
		return html.FinFichierHtml();
	}
	public double intervalle(int Win, int Lose){
		return getIntervalle(calculratio(Win,Lose),Win+Lose);
	}
	public double getIntervalle(float winrate, int nbgame){
		return 1.96 * Math.sqrt(winrate * (1-winrate))/Math.sqrt((double)nbgame);
	}
	public float calculratiopourcent(int win , int loose){
		return (float) (calculratio(win,loose) * 100.0) ;
	}
	public float calculratio(int win , int loose){
		return (float)(win / ((float)win+(float)loose));
	}
	public float getRatioCarte(){
		return ratioCarte;
	}
	public float getRatioMatchup() {
		return ratioMatchup;
	}
	public void setRatioMatchup(float ratioMatchup) {
		this.ratioMatchup = ratioMatchup;
	}
	public float getDeltamoin() {
		return deltamoin;
	}
	public void setDeltamoin(float deltamoin) {
		this.deltamoin = deltamoin;
	}
	public float getDeltaplus() {
		return deltaplus;
	}
	public void setDeltaplus(float deltaplus) {
		this.deltaplus = deltaplus;
	}
	public float getRatiopioche() {
		return ratiopioche;
	}
	public void setRatiopioche(float ratiopioche) {
		this.ratiopioche = ratiopioche;
	}
	public float getRatioCarteMatchup() {
		return ratioCarteMatchup;
	}
	public void setRatioCarteMatchup(float ratioCarteMatchup) {
		this.ratioCarteMatchup = ratioCarteMatchup;
	}
	public int getNbGameCarteMatchup() {
		return nbGameCarteMatchup;
	}
	public void setNbGameCarteMatchup(int nbGameCarteMatchup) {
		this.nbGameCarteMatchup = nbGameCarteMatchup;
	}
	public int getnbgameCarte() {
		return nbgameCarte;
	}
	public void setnbgameCarte(int nbgameCarte) {
		this.nbgameCarte = nbgameCarte;
	}
	public float getConfianceIntervalle() {
		return ConfianceIntervalle;
	}
	public void setConfianceIntervalle(float confianceIntervalle) {
		ConfianceIntervalle = confianceIntervalle;
	}
	public List<game> getGamelist() {
		return gamelist;
	}
	public void setGamelist(List<game> gamelist) {
		this.gamelist = gamelist;
	}
	public Heros getHeroTab(){
		return herotab;
	}
	public CarteScore getCarteScore(){
		return carteScore;
	}
	public void setCarteScore(CarteScore c){
		 this.carteScore = c;
	}
	public float getDeltaratioCarte() {
		return deltaratioCarte;
	}
	public void setDeltaratioCarte(float deltaratioCarte) {
		this.deltaratioCarte = deltaratioCarte;
	}
	public int getNbcarteMatchup() {
		return nbcarteMatchup;
	}
	public void setNbcarteMatchup(int nbcarteMatchup) {
		this.nbcarteMatchup = nbcarteMatchup;
	}
	/*public float getRatioMatchup(int i, int j){
		return this.Herotab.getRatio(i, j);
	}*/
	public float getDeltaratioHeroCarteMatchup() {
		return deltaratioHeroCarteMatchup;
	}
	public void setDeltaratioHeroCarteMatchup(float deltaratioHeroCarteMatchup) {
		this.deltaratioHeroCarteMatchup = deltaratioHeroCarteMatchup;
	}
	public String htmldebut(){
		 String s = "<table cellspacing='0'> <!-- cellspacing='0' is important, must stay -->";
		 //s += "<CAPTION> <b>"+convertNomJ(herotab.getNomHero(hero))+" (win rate with = "+ herotab.getRatio(hero)+")</b></CAPTION>";
		 s += "<!-- Table Header -->";
	     s += "<thead><tr>";
		 s += "<th align=center> Name of the carte </th>";
		 s += "<th align=center> Hero played </th>";
		 s += "<th align=center> Number of game played </th>";
		 s += "<th align=center> Win rate </th>";
		 s += "<th align=center> Win rate variation </th>";
		 s += "<th align=center> Confidence Interval </th>";
		 s +="</tr></thead><!-- Table Header -->";
		 s +="<!-- Table Body --><tbody>";
		 return s;
	}
	public String htmlfin(){
		String s = "</tbody>";
		s += "</table>";
		s += "</BODY></HTML>";
		return s;
	}
	

}
