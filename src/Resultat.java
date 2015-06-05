import java.util.ArrayList;
import java.util.List;


public class Resultat {
	private Heros Herotab;
	private float ConfianceIntervalle = 0;
	private CarteScore carteScore;
	private List<game> gamelist = new ArrayList<game>();
	
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
	public Resultat(){
		
	}
	public Resultat(Heros Herotab,
					CarteScore carteScore,
					List<game> gamelist ){
		
		this.Herotab = Herotab;
		this.carteScore = carteScore;
		this.setGamelist(gamelist);
	}
	
	public void setUpVariables(int i){
		// les noms des Heros
		String PlayerHero = Herotab.getClasseName(Herotab.transition(carteScore.getMatchup(i).getNomJ()));
		String OpponentHero = Herotab.getClasseName(Herotab.transition(carteScore.getMatchup(i).getNomA()));
		// calcul du Win Rate pour la carte Pour le Hero
		nbgameCarte = carteScore.getMatchup(i).getW()+carteScore.getMatchup(i).getL();
		ratioCarte = carteScore.getMatchup(i).getratio();
		setDeltaratioCarte(ratioCarte - ratioHero);
		// calcul du Win Rate de la carte Pour le matchup
		carteMatchupWin =  carteScore.getMatchup(i).getW(); 
		carteMatchupLose = carteScore.getMatchup(i).getL();
		setNbcarteMatchup(carteMatchupLose +carteMatchupWin);
		setRatioCarteMatchup(calculratiopourcent(carteMatchupWin,carteMatchupLose));
		
		// calcul du Win Rate pour le Hero
		nbgameHero= Herotab.getNbgame(Herotab.transition(PlayerHero));
		ratioHero = Herotab.getRatio(Herotab.transition(PlayerHero));
		// calcul du Win Rate pour le Matchup
		nbGameMatchup= Herotab.getNbgameMatchup(Herotab.transition(PlayerHero),Herotab.transition(OpponentHero));
		nbWinMatchup = Herotab.getWinMatchup(Herotab.transition(PlayerHero),Herotab.transition(OpponentHero));
		
		nbLoseMatchup = nbGameMatchup - nbWinMatchup;
		setRatioMatchup(calculratiopourcent(nbWinMatchup,nbLoseMatchup));
		
		// intervalle de confiance
		deltaratioHeroCarte = ratioHero - ratioCarte;
		setDeltaratioHeroCarteMatchup(getRatioMatchup()  - getRatioMatchup());
		setDeltamoin(deltaratioHeroCarte - (float) getIntervalle((float)(ratioCarte/100.0),nbgameCarte) - (float) getIntervalle((float)(ratioHero/100.0),nbgameHero)); 
		setDeltaplus(deltaratioHeroCarte + (float) getIntervalle((float)(ratioCarte/100.0),nbgameCarte) + (float) getIntervalle((float)(ratioHero/100.0),nbgameHero)); 
	}
	public String Tableaumatchup(){
		return html.TableauMatchup(Herotab);
	}
	public String resDebutHtml(){
		String s = html.enteteHtml();
		s += html.TableauMatchup(Herotab);
		for(int classe = 0;classe<9;classe++){
			s += html.TableauClasse(this,classe);
		}
	return s;
	}
	public String resClasseHtml(){
		String s = null;
		for(int classe = 0;classe<9;classe++){
			s += html.TableauClasse(this,classe);
		}
	return s;
	}
	public String resClasseCarteMatchupHtml(Resultat res,int i, int j){
		
		String s = html.TableauClasseMatchup(res,i,j);
		return s;
	}
	
	public String resToHtml(){
		String s = html.enteteHtml();
		s += html.TableauMatchup(Herotab);
		/*for(int classe = 0;classe<9;classe++){
			s += html.TableauClasse(this,classe);
			for(int classeAdverse = 0;classeAdverse<9 ; classeAdverse++){
				s += html.TableauClasseMatchup(this,classe,classeAdverse);
			}
		}*/
		return s;
		
	}
	public String FinToHtml(){
		return html.FinFichierHtml();
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
		return Herotab;
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
	public float getRatioMatchup(int i, int j){
		return this.Herotab.getRatio(i, j);
	}
	public float getDeltaratioHeroCarteMatchup() {
		return deltaratioHeroCarteMatchup;
	}
	public void setDeltaratioHeroCarteMatchup(float deltaratioHeroCarteMatchup) {
		this.deltaratioHeroCarteMatchup = deltaratioHeroCarteMatchup;
	}
	

}
