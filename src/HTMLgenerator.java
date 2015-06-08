import java.util.ArrayList;
import java.util.List;



public class HTMLgenerator {
	public static List<String> cartesClasse = new ArrayList<String>();
	public static List<String> cartesMatchup = new ArrayList<String>();
	private Heros herotab = new Heros();
	public String enteteHtml(){
		String s = "<!DOCTYPE html>";
		 s += "<html>";
		 s += "<head>";
		 s += "<meta charset=\"utf-8\">";
		 s += "<title>HandStone</title>";
		 s += "<link rel=\"stylesheet\" href=\"reset.css\" type=\"text/css\" />";
		 s += "<link rel=\"stylesheet\" href=\"styl.css\" type=\"text/css\" />";
		 s += "<link rel=\"stylesheet\" href=\"table.css\" type=\"text/css\" />";
		 s += "<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,400,600,700&subset=latin,latin-ext' rel='stylesheet' type='text/css' />";
		 s += "</head>";
		 s += "<body>";
		 s += "<a name=\"haut\">";
		 s += "<!-- *********  Header  ********** -->";
		 s += "<div id=\"header\">";
		 s += "<div id=\"header_in\">";
		 s += "<h1><a href=\"index.html\"><b>Hand</b>Stone</a></h1>";
		 s += "<div id=\"menu\">";
		 s += "<ul>";
		 s += "<li><a href=\"index.html\" class=\"active\">Home</a></li>";
		 s += "<li><a href=\"result.html\">Results</a></li>";
		 s += "<!--<li><a href=\"Upload.php\">Upload</a></li>-->";
		 s += "<!--<li><a href=\"FAQ.html\">FAQ</a></li>-->";
		 s += "<!--<li><a href=\"\">Contact</a></li>-->";
		 s += "</ul>";
		 s += "</div>";
		 s += "</div>";
		 s += "</div>";
		 s += "<!-- *********  Main part (slider)  ********** -->";
		 s += "<div id=\"main_part_inner\">";
		 s += "<div id=\"main_part_inner_in\">";
		 s += "<h2>Results</h2>";       
		 s += "</div>";
		 s += "</div>";   
		 s += "<!-- *********  Content  ********** -->";   
		 s += "<div id=\"content_inner\">";
		return s;
		
	}
	
	public String TableauMatchup(Heros herotab){
		String s = enteteHtml();
		 s += "<div class=\"CSSTableGenerator\" >";
		 s += "<table>";
		 s += "<tr>";
		 for(int i = 0;i<9;i++){
		 s += "<th>"+ herotab.getClasseHero(i)+"</th>";
		 }
		 s += "<td> Classe Winrate</td>";
		 s +="</tr>";
	
		 for(int i = 0; i<9 ; i++){
			 s += "<tr>";
			 for(int j = 0; j<9 ; j++){
				 s += "<td>"+ herotab.getRatioMatchup(i,j)+"</td>";
			 }
			 s +="<td>"+ herotab.getRatio(i)+"</td>";
			 s += "</tr>";
		 }
		 
		 
		s += "</tr>";
		s += "</table>";
		s += "</div>";   
		return s;	
	}
	public  void CarteToHtmlClasse(Carte c ,int numH, float deltaratio , double intervalle){

		String s ="<tr><td align=center>"+c.getNomC()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+herotab.getClasseHero(c.getNomJ())+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+c.getW()+"|"+c.getL()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+String.format("%.2f", c.getratio()) +"%"+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+deltaratio+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+intervalle+"</td></tr>"+System.getProperty("line.separator");
		   cartesClasse.add(s);
	}
	public  void CarteToHtmlMatchup(Carte c,int numH, int numO, int i , float deltaratio , double intervalle){

		String s ="<tr><td align=center>"+c.getNomC()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+c.getNomJ()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+c.getNomA()+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+(c.getWMatchup(i)+c.getLMatchup(i))+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+String.format("%.2f", c.getratioMatchup(i)) +"%"+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+deltaratio+"</td>"+System.getProperty("line.separator");
		   s+="<td align=center>"+intervalle+"</td></tr>"+System.getProperty("line.separator");
		   cartesMatchup.add(s);
	}
	
	public String FinFichierHtml(){
		String s = "</tr>";
		s += "</table>";
		

		s += "</div>";
		s += "</div>";
		s += "<!-- *********  Footer  ********** -->";

		s += "<hr class=\"cleanit\">";

		s += "<div id=\"footer\">";
		s += "<div id=\"footer_in\">";

		s += "<p><a href=\"#haut\">Haut</a></p>";
		s += "<span>HandStone Project</span>";


		s += "</div>";
		s += "</div>";

		s += "</body>";
		return s += "</html>";
	}
	public String toString(){
		String s = null;
		s += enteteHtml();
		s += "<table>";
		
		for(int i = 0;i<cartesClasse.size();i++){
			s+= cartesClasse.get(i);
		}
		s += "</table>";
		s += "</div>";   
		s += "<table>";
		s += "<tr>";
		for(int i = 0;i<cartesMatchup.size();i++){
			s+= cartesMatchup.get(i);
		}
		s += "</tr>";
		s += "</table>";
		s += "</div>";   
		s+=FinFichierHtml();
		return s;
		
		
		
		
	}

	public Heros getHerotab() {
		return herotab;
	}

	public void setHerotab(Heros herotab) {
		this.herotab = herotab;
	}
	
}
