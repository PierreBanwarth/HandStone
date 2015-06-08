import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Resultat {
	private Heros herotab;
	private double ConfianceIntervalle = 0;
	private CarteScore carteScore;
	private List<game> gamelist = new ArrayList<game>();
	Translate translater  = new Translate();
	private int nbgameCarte;
	private int nbGameCarteMatchup;
	private int nbcarteMatchup;
	private double ratioCarte;
	private double ratioMatchup;
	private double deltamoin; 
	private double deltaplus; 
	private double ratiopioche;
	private double ratioCarteMatchup;
	private double deltaratioHeroCarteMatchup;
	public HTMLgenerator html = new HTMLgenerator();
	private double deltaratioCarte;
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
		h.setHerotab(herotab);
		for(String id : cles){
			c = carteClasse.get(id);
			
			c.setNomC(translater.ToEnglish(c.getNomC()));
			// calcul des données par rapport au ratio du héro
			int numhero = herotab.getNumHero(c.getNomJ());
			double deltaratio = herotab.getRatio(numhero) - c.getratio();
			double intervalle  = intervalle(c.getW(),c.getL()) * 100;
			if(c.getW()+c.getL()>50){
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
			double deltaratio = herotab.getRatioMatchup(numhero , numheroAdverse) - c.getratioMatchup(numheroAdverse);
			double intervalle  = intervalle(c.getW(),c.getL()) * 100;
			
			if(c.getLMatchup(i) + c.getWMatchup(i)>25){
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
	public double getIntervalle(double winrate, int nbgame){
		return 1.96 * Math.sqrt(winrate * (1-winrate))/Math.sqrt((double)nbgame);
	}
	public double calculratiopourcent(int win , int loose){
		return (double) (calculratio(win,loose) * 100.0) ;
	}
	public double calculratio(int win , int loose){
		return (double)(win / ((double)win+(double)loose));
	}
	public double getRatioCarte(){
		return ratioCarte;
	}
	public double getRatioMatchup() {
		return ratioMatchup;
	}
	public void setRatioMatchup(double ratioMatchup) {
		this.ratioMatchup = ratioMatchup;
	}
	public double getDeltamoin() {
		return deltamoin;
	}
	public void setDeltamoin(double deltamoin) {
		this.deltamoin = deltamoin;
	}
	public double getDeltaplus() {
		return deltaplus;
	}
	public void setDeltaplus(double deltaplus) {
		this.deltaplus = deltaplus;
	}
	public double getRatiopioche() {
		return ratiopioche;
	}
	public void setRatiopioche(double ratiopioche) {
		this.ratiopioche = ratiopioche;
	}
	public double getRatioCarteMatchup() {
		return ratioCarteMatchup;
	}
	public void setRatioCarteMatchup(double ratioCarteMatchup) {
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
	public double getConfianceIntervalle() {
		return ConfianceIntervalle;
	}
	public void setConfianceIntervalle(double confianceIntervalle) {
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
	public double getDeltaratioCarte() {
		return deltaratioCarte;
	}
	public void setDeltaratioCarte(double deltaratioCarte) {
		this.deltaratioCarte = deltaratioCarte;
	}
	public int getNbcarteMatchup() {
		return nbcarteMatchup;
	}
	public void setNbcarteMatchup(int nbcarteMatchup) {
		this.nbcarteMatchup = nbcarteMatchup;
	}
	/*public double getRatioMatchup(int i, int j){
		return this.Herotab.getRatio(i, j);
	}*/
	public double getDeltaratioHeroCarteMatchup() {
		return deltaratioHeroCarteMatchup;
	}
	public void setDeltaratioHeroCarteMatchup(double deltaratioHeroCarteMatchup) {
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
