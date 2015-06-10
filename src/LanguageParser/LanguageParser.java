package LanguageParser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class LanguageParser {
	
	/*
	 * args[0] DataBase user
	 * args[1] Database password
	 * args[2] input file
	 */
	
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);

		String FileName = args[2];
		System.out.println("[Language Parser] input file: \"" + FileName + "\"");
		String url = "jdbc:mysql://serveur-du-placard.tk:3306/HandStoneDB";
		DBConnexion db = new DBConnexion(args[0], args[1], url);
		db.connect();
		System.out.println("[Language Parser] db is connected: " + db.isConnected());
		
		
        if (FileName != null) {
	    	DocumentBuilder builder;
			try {
				builder = factory.newDocumentBuilder();
			    Document document= builder.parse(new File(FileName));
				
			    System.out.println("[Language Parser] Reading file: \"" + FileName + "\"");
			    
			    //File properties display
			    System.out.println("[Language Parser] Version : " + document.getXmlVersion());
			    System.out.println("[Language Parser] Encoding : " + document.getXmlEncoding());		
		        System.out.println("[Language Parser] Standalone : " + document.getXmlStandalone());
							
			    //Root element
			    Element racine = document.getDocumentElement();		
		        System.out.println("[Language Parser] racine : " + racine.getNodeName());
			    
			    
			    // Cards registration
			    System.out.println("[Language] Reading Cards");
			    NodeList CardList = racine.getElementsByTagName("Card");
			    CardInfo cur;
			    for (int i = 0; i < CardList.getLength(); i++) {
				//for (int i = 0; i < CardList.getLength(); i++) {
			    	//System.out.println("[Language Parser][Card] " + CardList.item(i).getNodeName());
			    	cur = new CardInfo(CardList.item(i), "pt");
			    	cur.exportToDB(db);
			    }
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
        	 System.out.println("[Language Parser][Error] FileName is null");
        }
	}

}
