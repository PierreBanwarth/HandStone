import java.util.ArrayList;
import java.util.List;

public class CarteScore {
	List<Carte> cartesMatchup = new ArrayList<Carte>();
	
	List<Carte> Rogue = new ArrayList<Carte>();
	List<Carte> Paladin = new ArrayList<Carte>();
	List<Carte> Druid = new ArrayList<Carte>();
	
	List<Carte> Hunt = new ArrayList<Carte>();
	List<Carte> Chaman = new ArrayList<Carte>();
	List<Carte> Warrior = new ArrayList<Carte>();
	
	List<Carte> Mage = new ArrayList<Carte>();
	List<Carte> Warlock = new ArrayList<Carte>();
	List<Carte> Priest = new ArrayList<Carte>();
	
	
	
	Heros herotab = new Heros();
	Resultat res;
	public CarteScore(Resultat res){
		this.res = res;
	}
	public int size(){return cartesMatchup.size();}
	
	public List<Carte> listSelect(int i){
			switch (i) {
	        case 0:
	            return Rogue;
	        case 1:
	        	return Paladin;
	        case 2:
	        	return Druid;
	        case 3:
	        	return Hunt;
	        case 4:
	        	return Chaman;
	        case 5:
	        	return Warrior;
	        case 6:
	        	return Mage;
	        case 7:
	        	return Warlock;
	        case 8:
	        	return Priest;
		}
			return null;
		
		
		
	}
	public void adClasse(Carte c, int i){
			switch (i) {
	        case 0:
	            adbis(c,Rogue);
	        case 1:
	        	adbis(c,Paladin);
	        case 2:
	        	adbis(c,Druid);
	        case 3: 
	        	adbis(c,Hunt);
	        case 4:
	        	adbis(c,Chaman);
	        case 5:
	        	adbis(c,Warrior);
	        case 6:
	        	adbis(c,Mage);
	        case 7:
	        	adbis(c,Warlock);
	        case 8:
	        	adbis(c,Priest);
	        		
		}
		
		
	}
	public void adbis(Carte carte ,List<Carte> cartes) {
		/*boolean memeA;*/
		boolean memeJ;
		boolean memenom;
		boolean trouvé = false;
		
		int i = 0;
		while(i<cartes.size() && trouvé == false){
				trouvé = false;
				memenom = cartes.get(i).getNomC().compareTo(carte.getNomC())==0;
				memeJ = cartes.get(i).getNomJ().compareTo(carte.getNomJ())==0;
				//memeA = cartesMatchup.get(i).getNomA().compareTo(carte.getNomA())==0;
				
			if(memenom && memeJ /* && memeA*/){
				carte.setW(carte.getW()+cartes.get(i).getW());
				carte.setL(carte.getL()+cartes.get(i).getL());
				cartes.remove(i);
				cartes.add(carte);
				trouvé = true;
			}
		i++;
		}
		if(!trouvé){
			cartes.add(carte);
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


