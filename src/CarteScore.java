import java.util.ArrayList;
import java.util.Collections;
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
	float ratioMuligan;
	float ratioMatchup;
	float deltamoin; 
	float deltaplus; 
	float ratiopioche;
	public CarteScore(){
	}
	
	@Override public String toString() {
		float deltaratio;
		float ratioMatchup = 0;
		 String s = "<HTML>"+System.getProperty("line.separator");
		 s += "<BODY>"+System.getProperty("line.separator");
		for(int hero = 1; hero <10;hero++){
			 
			 s += "<table cellspacing='0'> <!-- cellspacing='0' is important, must stay -->";
			 s += "<CAPTION> <b>"+convertNomJ(herotab.getNomHero(hero))+" (win rate with = "+ herotab.getRatio(hero)+")</b></CAPTION>";
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
			for(int i =0;i<cartesMatchup.size();i++){
				nbgameMuligan = cartesMatchup.get(i).getW()+cartesMatchup.get(i).getL();
				if(cartesMatchup.get(i).getNomJ().compareTo(herotab.getNomHero(hero))==0 && nbgameMuligan >0)
				{
					  // s+= "<tr>";
					   //s+= cartesMatchup.get(i).toString();
					   deltaratio =  cartesMatchup.get(i).getratio() - this.herotab.getRatio(cartesMatchup.get(i).getNomJ());
					  
					   if(deltaratio>0){
						  // s+= "<td align=center><font size=\"2\" color=\"green\">"+String.format("%.2f", deltaratio)+"</font></td>";
					   }else{
						 //  s+= "<td align=center><font size=\"2\" color=\"red\">"+String.format("%.2f", deltaratio)+"</font></td>"+System.getProperty("line.separator");
					   }
					   nbgameMuligan = cartesMatchup.get(i).getW()+cartesMatchup.get(i).getL();
					   ratioMuligan = cartesMatchup.get(i).getratio();
					   nbgameMatchup=this.herotab.getNbgame(cartesMatchup.get(i).getNomJ());
					   ratioMatchup = (int) this.herotab.getRatio(cartesMatchup.get(i).getNomJ());
					   deltamoin = deltaratio - (float) getIntervalle((float)(ratioMuligan/100.0),nbgameMuligan) - (float) getIntervalle((float)(ratioMatchup/100.0),nbgameMatchup); 
					   deltaplus = deltaratio + (float) getIntervalle((float)(ratioMuligan/100.0),nbgameMuligan) + (float) getIntervalle((float)(ratioMatchup/100.0),nbgameMatchup); 
					   
					  // s+="<td align=center>["+ String.format("%.2f",deltamoin)+";"+  String.format("%.2f",deltaplus)+"]</td>"+System.getProperty("line.separator");
					  //s+= "</tr>";
				}
				
			}

			
		}
		//s += "</tr>";
		//s += "</tbody>";
		//s += "</table>";
		//s += "</BODY></HTML>";
		//s = null;
		List<String> cles = new ArrayList<String>(carteClasse.keySet());
		Collections.sort(cles, new CarteComparator(carteClasse));
		Carte c;
		for(String id : cles){
			c = carteClasse.get(id);
			if(c.getW()+c.getL()>50){
				s +=  c.toString();
			}
		}
		cles = new ArrayList<String>(carteMatchup.keySet());
		Collections.sort(cles, new CarteComparator(carteMatchup));
		for(String id : cles){
			int i = herotab.getNumHero(carteMatchup.get(id).getNomA());
			c = carteMatchup.get(id);
			if(c.getLMatchup(i) + c.getWMatchup(i)>25){
			s += c.toString2(i);
			}
		}
	    	
		s += "</tbody>";
		s += "</table>";
		s += "</BODY></HTML>";
		return s;
	}
	public double getIntervalle(float winrate, int nbgame){
		return 1.96 * Math.sqrt(winrate * (1-winrate)/(double)nbgame);
	}
	public float calculratiopourcent(int win , int loose){
		return (float) (calculratio(win,loose) * 100.0) ;
	}
	public float calculratio(int win , int loose){
		return (float)(win / ((float)win+(float)loose));
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
		
		
	public String convertNomJ(String s){
		
	return herotab.getClasseHero(s);
	}

	public void setHerotab(Heros h){
		this.herotab = h;
	}
}


