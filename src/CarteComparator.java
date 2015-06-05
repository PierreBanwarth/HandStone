import java.util.Comparator;
import java.util.Map;


public class CarteComparator  implements Comparator<String>{
	    private Map<String, Carte> personnes;//pour garder une copie du Map que l'on souhaite traiter
	 
	    public CarteComparator(Map<String, Carte> personnes){
	        this.personnes = personnes; //stocker la copie pour qu'elle soit accessible dans compare()
	    }
	 
	    public int compare(String s1, String s2){
	        //récupérer les personnes du Map par leur identifiant
	    	Carte c1 = personnes.get(s1);
	        Carte c2 = personnes.get(s2);
	
	       //comparer les deux clés en fonction de l'age des personnes qu'ils indexent.
	       return c1.compareTo(c2);
    }
}

