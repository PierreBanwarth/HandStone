
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
/**
 * @author Crunchify.com
 */
 
public class Translate {

	Map<String , String> carteTranslation = new HashMap<String, String>();
    @SuppressWarnings("unchecked")
    public  Translate() {
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader("C:\\Users\\tagadatsointsoin\\Desktop\\data HS bordel\\AllSets.json"));
            List<String> cartes = new ArrayList<String>(); 
            cartes.add("Basic");
            cartes.add("Curse of Naxxramas");
            cartes.add("Goblins vs Gnomes");
            
            cartes.add("Blackrock Mountain");
            cartes.add("Credits");
            cartes.add("Promotion");
            cartes.add("Debug");
            
            cartes.add("System");
            cartes.add("Missions");
            cartes.add("Promotion");
            cartes.add("Classic");
            Iterator iterator;
            for (int i = 0;i<cartes.size();i++){
            	JSONObject jsonObject = (JSONObject) obj;
                JSONArray lang = (JSONArray) jsonObject.get(cartes.get(i));
                iterator = lang.iterator();
                while (iterator.hasNext()) {
                	    JSONObject innerObj = (JSONObject) iterator.next();
                	    //"id":"GAME_004","name":"AFK","type":"Enchantment","text":"Your turns are shorter."
                	    String s = (String) innerObj.get("id");
                	   String s2 = (String) innerObj.get("name");
                	    carteTranslation.put(s,s2);
                }
            }
            
  
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String ToEnglish(String s){
    	if(carteTranslation.get(s) == null){return s;}
    	return carteTranslation.get(s);
    	
    }
    
    
}