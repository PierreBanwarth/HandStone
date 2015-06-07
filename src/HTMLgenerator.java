import java.util.List;


public class HTMLgenerator {
	
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
		 s += "<th>"+ herotab.getClasseName(i)+"</th>";
		 }
		 s += "<td> Classe Winrate</td>";
		 s +="</tr>";
	
		 for(int i = 0; i<9 ; i++){
			 s += "<tr>";
			 for(int j = 0; j<9 ; j++){
				 s += "<td>"+ herotab.getRatio(i,j)+"</td>";
			 }
			 s +="<td>"+ herotab.getRatio(i)+"</td>";
			 s += "</tr>";
		 }
		 
		 
		s += "</tr>";
		s += "</table>";
		s += "</div>";   
		//s += FinFichierHtml();
		
		return s;	
	}
	
	public String TableauClasseMatchup(Resultat res, int classeJoueur , int classeAdverse){
		String s= "<div class=\"CSSTableGenerator\" >";
		 s += "<table>";
		 s += "<tr>";
		 s += "<td> Card </td>";
		 s += "<td> player </td>";
		 s += "<td> opponent </td>";
		 s += "<td> Number of data</td>";
		 s += "<td> Win rate </td>";
		 s += "<td> Win rate variation </td>";
		 s += "<td> Confidence Interval </td>";
		 s +="</tr>";
		 Heros herotab = res.getHeroTab();
	     CarteScore cartescore = res.getCarteScore();
	     for(int i =0;i<cartescore.size();i++){
			 res.setUpVariables(i);
			if(cartescore.getMatchup(i).getNomJ().compareTo(herotab.transition(classeJoueur))==0 && res.getnbgameCarte() > 10)
			{
				   s +="<tr>";
				   s += "<td> "+cartescore.getMatchup(i).getNomC()+ "</td>";
				   s += "<td> "+herotab.getClasseName(classeJoueur)+" </td>";
				   s += "<td> "+herotab.getClasseName(classeAdverse)+" </td>";
				   float a = res.getRatioCarteMatchup();
				   float b = res.getRatioMatchup(classeJoueur,classeAdverse);
				   float c = res.getDeltaratioHeroCarteMatchup();
					 s += "<td> "+res.getNbcarteMatchup()+" </td>";
					 s += "<td>"+ res.getRatioCarteMatchup()+ "</td>";
					 
					 if(b-a>0){
						   s+= "<td><font size=\"2\" color=\"green\">"+String.format("%.2f",a - b)+"</font></td>";
					   }else{
						   s+= "<td><font size=\"2\" color=\"red\">"+String.format("%.2f", a - b)+"</font></td>"+System.getProperty("line.separator");
					   }
					 s += "<td> ["+res.getDeltaplus() + ","+res.getDeltaplus()+"] </td>";
				   
				
				   s+= "</tr>";
				   
			}
			
	}
	    s += "</table>";
		s += "</div>";  
	     return s;
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
	
	public String TableauClasse(Resultat res, int hero) {
		
		String s= "<div class=\"CSSTableGenerator\" >";
		 s += "<table>";
		 s += "<tr>";
		 s += "<td> Card </td>";
		 s += "<td> Hero </td>";
		 s += "<td> Number of games </td>";
		 s += "<td> Win rate </td>";
		 s += "<td> Win rate variation </td>";
		 s += "<td> Confidence Interval </td>";
		 s +="</tr>";
		
		 Heros herotab = res.getHeroTab();
	     CarteScore cartescore = res.getCarteScore();
	     List<Carte> classe = cartescore.listSelect(hero);
		 for(int i =0;i<classe.size();i++){
			{
				if(classe.get(i).getL() + classe.get(i).getW() >20)
				   s +="<tr>";
				   s += "<td> "+classe.get(i).getNomC()+ "</td>";
				   s += "<td> "+herotab.getClasseName(hero)+" </td>";
					 s += "<td> "+res.getnbgameCarte()+" </td>";
					 s += "<td>"+ res.getRatioCarte()+ "</td>";
					 if(res.getDeltaratioCarte()>0){
						   s+= "<td><font size=\"2\" color=\"green\">"+String.format("%.2f", res.getDeltaratioCarte())+"</font></td>";
					   }else{
						   s+= "<td><font size=\"2\" color=\"red\">"+String.format("%.2f", res.getDeltaratioCarte())+"</font></td>"+System.getProperty("line.separator");
					   }
					 s += "<td> ["+res.getDeltaplus() + ","+res.getDeltaplus()+"] </td>";
				   
				
				   s+= "</tr>";
			}
	}
	s += "</table>";
	s += "</div>";
	return s;	 
}
}
