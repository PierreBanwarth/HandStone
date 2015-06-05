import java.util.ArrayList;
import java.util.List;

public class CarteScore {
	List<Carte> cartesMatchup = new ArrayList<Carte>();
	
	
	Heros herotab = new Heros();
	Resultat res;

	public CarteScore(Resultat res){
		this.res = res;
	}
	public int size(){return cartesMatchup.size();}

	public void adbis(Carte carte ,List<Carte> cartesMatchup) {
		boolean memeA;
		boolean memeJ;
		boolean memenom;
		boolean trouv� = false;
		
		int i = 0;
		while(i<cartesMatchup.size() && trouv� == false){
				trouv� = false;
				memenom = cartesMatchup.get(i).getNomC().compareTo(carte.getNomC())==0;
				memeJ = cartesMatchup.get(i).getNomJ().compareTo(carte.getNomJ())==0;
				memeA = cartesMatchup.get(i).getNomA().compareTo(carte.getNomA())==0;
				
			if(memenom && memeJ && memeA){
				carte.setW(carte.getW()+cartesMatchup.get(i).getW());
				carte.setL(carte.getL()+cartesMatchup.get(i).getL());
				cartesMatchup.remove(i);
				cartesMatchup.add(carte);
				trouv� = true;
			}
		i++;
		}
		if(!trouv�){
			cartesMatchup.add(carte);
		}
	}
	
	public void setHerotab(Heros h){
		this.herotab = h;
	}

	public Carte getMatchup(int i) {
		// TODO Auto-generated method stub
		return cartesMatchup.get(i);
	}
	
}


