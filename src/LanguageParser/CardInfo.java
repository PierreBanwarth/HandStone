package LanguageParser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CardInfo {
		public String CardID;
		public String Name;
		public String CardSet;
		public String Rarity;
		public String Type;
		public int Attack;
		public int Health;
		public int Cost;
		public int Durability;
		public String Class;
		public String Faction;
		public String Text;
		public String Mechanics;
		public String Language;
		
		public CardInfo(Node n, String language) {
			n.normalize();
			Element e = (Element) n;
			Node e2;
			//CardID = info.item(1).getTextContent();
			e2 = e.getElementsByTagName("CardId").item(0);
			if (e2 != null)
				CardID = e2.getTextContent(); //.item(1).getNodeValue();
			else
				CardID = "";
			//System.out.println("[CardInfo] CardID: " + CardID);
			e2 = e.getElementsByTagName("Name").item(0);
			if (e2 != null)
				Name = e2.getTextContent();
			else
				Name = "";
			//System.out.println("[CardInfo] Name: " + Name);
			e2 = e.getElementsByTagName("CardSet").item(0);
			if (e2 != null)
				CardSet = e2.getTextContent();
			else
				CardSet = "";
			//System.out.println("[CardInfo] CardSet: " + CardSet);
			e2 = e.getElementsByTagName("Rarity").item(0);
			if (e2 != null)
				Rarity = e2.getTextContent();
			else
				Rarity = "";
			//System.out.println("[CardInfo] Rarity: " + Rarity);
			e2 = e.getElementsByTagName("Type").item(0);
			if (e2 != null)
				Type = e2.getTextContent();
			else
				Type = "";
			//System.out.println("[CardInfo] Type: " + Type);
			e2 = e.getElementsByTagName("Attack").item(0);
			if (e2 != null)
				Attack = Integer.parseInt(e2.getTextContent());
			else
				Attack = 999;
			e2 = e.getElementsByTagName("Health").item(0);
			if (e2 != null)
				Health = Integer.parseInt(e2.getTextContent());
			else
				Health = 999;
			e2 = e.getElementsByTagName("Cost").item(0);
			if (e2 != null)
				Cost = Integer.parseInt(e2.getTextContent());
			else
				Cost = 999;
			e2 = e.getElementsByTagName("Durability").item(0);
			if (e2 != null)
				Durability = Integer.parseInt(e2.getTextContent());
			else
				Durability = 999;
			e2 = e.getElementsByTagName("Class").item(0);
			if (e2 != null)
				Class = e2.getTextContent();
			else
				Class = "";
			e2 = e.getElementsByTagName("Faction").item(0);
			if (e2 != null)
				Faction = e2.getTextContent();
			else
				Faction = "";
			e2 = e.getElementsByTagName("Text").item(0);
			if (e2 != null)
				Text = e2.getTextContent();
			else
				Text = "";
			e2 = e.getElementsByTagName("Mechanics").item(0);
			if (e2 != null)
				Mechanics = e2.getTextContent();
			else
				Mechanics = "";
			Language = language;
		}
		
		public void exportToDB (DBConnexion db) {
			String query = "";
			if(db.isConnected()) {
				query = "";
				query += "INSERT INTO cardInfo ";
				query += "(cardID, name, cardSet, rarity, type, attack, health, cost, durability, class, faction, text, mechanics, language)";
				query += " values ('";
				query += CardID.replace("\'", "&#039");
				query += "', '";
				query += Name.replace("\'", "&#039");
				query += "', '";
				query += CardSet.replace("\'", "&#039");
				query += "', '";
				query += Rarity.replace("\'", "&#039");
				query += "', '";
				query += Type.replace("\'", "&#039");
				query += "', '";
				query += Attack;
				query += "', '";
				query += Health;
				query += "', '";
				query += Cost;
				query += "', '";
				query += Durability;
				query += "', '";
				query += Class.replace("\'", "&#039");
				query += "', '";
				query += Faction.replace("\'", "&#039");
				query += "', '";
				query += Text.replace("\'", "&#039");
				query += "', '";
				query += Mechanics.replace("\'", "&#039");
				query += "', '";
				query += Language.replace("\'", "&#039");
				query += "');";
				
				System.out.println(query);
				db.modify(query);
			}
		}
		
		/*
		 * <CardId>CS1h_001</CardId>
		 * <Name>Soins inférieurs</Name>
		 * <CardSet>Basic</CardSet>
		 * <Rarity>Free</Rarity>
		 * <Type>Hero Power</Type>
		 * <Attack>0</Attack>
		 * <Health>0</Health>
		 * <Cost>2</Cost>
		 * <Durability>0</Durability>
		 * <Class>Priest</Class>
		 * <Faction>Neutral</Faction>
		 * <Text><b>Pouvoir héroïque</b>Rend 2 PV.</Text>
		 * <Mechanics/>
		*/
	}
