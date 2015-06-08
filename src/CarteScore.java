import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarteScore {
	List<Carte> cartesMatchup = new ArrayList<Carte>();
	Map<String , Carte> carteClasse =  new HashMap<String, Carte>();
	Map<String , Carte> carteMatchup = new HashMap<String, Carte>();
	Heros herotab = new Heros();
	
	int nbcarteidentique = 0;
	int nbgameMuligan;
	int nbgameMatchup;
	double ratioMuligan;
	double ratioMatchup;
	double deltamoin; 
	double deltaplus; 
	double ratiopioche;
	public CarteScore(){
	}
	public double intervalle(int Win, int Lose){
		return getIntervalle(calculratio(Win,Lose),Win+Lose);
	}
	public double getIntervalle(double winrate, int nbgame){
		return 1.96 * Math.sqrt(winrate * (1-winrate)/(double)nbgame);
	}
	public double calculratiopourcent(int win , int loose){
		return (double) (calculratio(win,loose) * 100.0) ;
	}
	public double calculratio(int win , int loose){
		return (double)(win / ((double)win+(double)loose));
	}

	public void adbis(Carte carte ) {
		adCarteMatchup(carte);
		adCarteClasse(carte);
	}
	
	public void adCarteMatchup(Carte c){
		{
		String s = c.getNomC() +" "+ c.getNomJ() +" "+c.getNomA();
				if(carteMatchup.containsKey(s)){
					Carte cartetest = carteMatchup.get(s);	
					cartetest.setLMatchup(cartetest.getLMatchup(herotab.getNumHero(cartetest.getNomA()))+c.getL(),herotab.getNumHero(cartetest.getNomA()));
					cartetest.setWMatchup(cartetest.getWMatchup(herotab.getNumHero(cartetest.getNomA()))+c.getW(),herotab.getNumHero(cartetest.getNomA()));
				}else{
					c.setLMatchup(c.getL(),herotab.getNumHero(c.getNomA()));
					c.setWMatchup(c.getW(),herotab.getNumHero(c.getNomA()));
					carteMatchup.put(s,c);
				}
			}
	}
	public void adCarteClasse(Carte c){
		String s = c.getNomC() +" "+ c.getNomJ();
			if(carteClasse.containsKey(s)){
				Carte cartetest = carteClasse.get(s);	
				cartetest.setL(cartetest.getL()+c.getL());
				cartetest.setW(cartetest.getW()+c.getW());
			}else{
				carteClasse.put(s,c);
			}
		}
		
	public Map<String , Carte> getCarteClasse(){return carteClasse;} 
	public Map<String , Carte> getCarteMatchup(){return carteMatchup;}
	public String convertNomJ(String s){
		return herotab.getClasseHero(s);
	}

	public void setHerotab(Heros h){
		this.herotab = h;
	}
	

}


