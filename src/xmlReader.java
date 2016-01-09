//christopher hamm
//cosc 3011
//project 5

import java.io.File;  

import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  

import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  

/**
 * This class reads an xml file and extracts information from it.
 * It takes the extracted info and puts it into temporary card objects.
 * The card objects are then added to a newCardDeck object.
 * @author uwadminuser
 *
 */
public class xmlReader extends Main
{
	public static cardObject[] readXML(File inputSaveFile)
	{
		 cardObject[] newCardDeck= new cardObject[52];
		try
		{
			//File xmlFile = new File("saved_cardDeck.xml");  
			File xmlFile= inputSaveFile;
		   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
		     .newInstance();  
		   DocumentBuilder documentBuilder = documentFactory  
		     .newDocumentBuilder();  
		   Document doc = documentBuilder.parse(xmlFile);  
		  
		   doc.getDocumentElement().normalize();  
		   NodeList nodeList = doc.getElementsByTagName("card");
		   
		   System.out.println("Root element :"+ doc.getDocumentElement().getNodeName());
		  
		   int numOfShuffles =0;
		   int gameMode=0;
		   
		   for(int temp=0; temp < nodeList.getLength(); temp++)
		   {
			   Node node = nodeList.item(temp);
			   		//System.out.println("nodeList.getLength= " + nodeList.getLength());
				   System.out.println("\nElement type :" + node.getNodeName());
				
				   if(node.getNodeType() == Node.ELEMENT_NODE)
				   {
					   Element card = (Element) node;
					   //cardObject tempCard =null;
					   
					   
					   //get node name and value
					   System.out.println("Node name: " + node.getNodeName());
					   System.out.println("X: " + card.getElementsByTagName("X").item(0).getTextContent());
					   int tempX= Integer.parseInt(card.getElementsByTagName("X").item(0).getTextContent());
					   
					   System.out.println("Y: " + card.getElementsByTagName("Y").item(0).getTextContent());
					   int tempY= Integer.parseInt(card.getElementsByTagName("Y").item(0).getTextContent());
					   
					   System.out.println("prevX: " + card.getElementsByTagName("prevX").item(0).getTextContent());
					   int tempPrevX= Integer.parseInt(card.getElementsByTagName("prevX").item(0).getTextContent());
					   
					   System.out.println("prevY: " + card.getElementsByTagName("prevY").item(0).getTextContent());
					   int tempPrevY= Integer.parseInt(card.getElementsByTagName("prevY").item(0).getTextContent());
					   
					   System.out.println("index: " + card.getElementsByTagName("index").item(0).getTextContent());
					  int tempIndex= Integer.parseInt(card.getElementsByTagName("index").item(0).getTextContent());
					  
					  
					   System.out.println("rank: " + card.getElementsByTagName("rank").item(0).getTextContent());
					   int tempRank= Integer.parseInt(card.getElementsByTagName("rank").item(0).getTextContent());
					   
					   System.out.println("suit: " + card.getElementsByTagName("suit").item(0).getTextContent());
					   int tempSuit= Integer.parseInt(card.getElementsByTagName("suit").item(0).getTextContent());
					   
					   System.out.println("showCardBackside: " + card.getElementsByTagName("showCardBackside").item(0).getTextContent());
					   boolean tempShowCardBackside= Boolean.parseBoolean(card.getElementsByTagName("showCardBackside").item(0).getTextContent());
					  
					   
					   System.out.println("numShuffles: " + card.getElementsByTagName("numShuffles").item(0).getTextContent());
					   numOfShuffles= Integer.parseInt(card.getElementsByTagName("numShuffles").item(0).getTextContent());
					   
					   gameMode= Integer.parseInt(card.getElementsByTagName("gameMode").item(0).getTextContent());
					   //makes the new card 
					   newCardDeck[temp]= new cardObject(tempX,tempY,tempPrevX, tempPrevY, tempRank,tempSuit,tempIndex);
					   newCardDeck[temp].setShowCardBackside(tempShowCardBackside);
				   }//end of if statement
			
			   
		   }//end of for loop 
		   Main.setNumberOfShufflesPerformed(numOfShuffles);
		   Main.currentGameMode= gameMode;
		   
		}//end of try block
		catch(Exception e)
		{
			System.out.println("Error reading xml file, check to see if file is in folder");
			e.printStackTrace();
		}//end of catch block
		return newCardDeck;
	}//end of readXML
}
