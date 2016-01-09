//christopher hamm
//cosc 3011
//project 5

  
import java.io.File;  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerException;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  
import org.w3c.dom.Attr;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  


public class xmlCreator
{
	public void createXML(cardObject saving_cardDeck[])
	{
		try
		{
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();  
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();  
			
			//define the root elements
			Document document= documentBuilder.newDocument();
			Element rootElement = document.createElement("cardDeck");
			document.appendChild(rootElement);
			
			
			
			for(int i=0; i < saving_cardDeck.length; i++)
			{
				
				//define the cardDeck elements
				Element cardDeck= document.createElement("card");
				rootElement.appendChild(cardDeck);
				//add x elements
				Element X= document.createElement("X");
				X.appendChild(document.createTextNode("" + saving_cardDeck[i].getX()));
				cardDeck.appendChild(X);
				
				//add y elements
				Element Y= document.createElement("Y");
				Y.appendChild(document.createTextNode("" + saving_cardDeck[i].getY()));
				cardDeck.appendChild(Y);
				
				//add prevX elements
				Element prevX= document.createElement("prevX");
				prevX.appendChild(document.createTextNode("" + saving_cardDeck[i].getPrevX()));
				cardDeck.appendChild(prevX);
				
				//add prevY elements
				Element prevY= document.createElement("prevY");
				prevY.appendChild(document.createTextNode("" + saving_cardDeck[i].getPrevY()));
				cardDeck.appendChild(prevY);
				
				//add index elements
				Element index= document.createElement("index");
				index.appendChild(document.createTextNode("" + saving_cardDeck[i].getCardIndex()));
				cardDeck.appendChild(index);
				
				//add rank elements
				Element rank= document.createElement("rank");
				rank.appendChild(document.createTextNode("" + saving_cardDeck[i].getCardRank()));
				cardDeck.appendChild(rank);
				
				//add suit elements
				Element suit= document.createElement("suit");
				suit.appendChild(document.createTextNode("" + saving_cardDeck[i].getCardSuit()));
				cardDeck.appendChild(suit);
				
				//showCardbackside
				Element showCardBackside= document.createElement("showCardBackside");
				showCardBackside.appendChild(document.createTextNode("" + saving_cardDeck[i].getShowCardBacksideValue()));
				cardDeck.appendChild(showCardBackside);
				
				//add# of shuffles
				Element numShuffles= document.createElement("numShuffles");
				numShuffles.appendChild(document.createTextNode(""+ Main.getNumberOfShufflesPerformed()));
				cardDeck.appendChild(numShuffles);
				//gameMode
				Element gameMode= document.createElement("gameMode");
				gameMode.appendChild(document.createTextNode(""+Main.currentGameMode));
				cardDeck.appendChild(gameMode);
				
			}//end of for loop
			
			
			
			
			//creating and writing to xml file
			TransformerFactory transformerFactory = TransformerFactory  
				     .newInstance();  
				   Transformer transformer = transformerFactory.newTransformer();  
				   DOMSource domSource = new DOMSource(document);  
				   StreamResult streamResult = new StreamResult(new File(  
				     "saved_cardDeck.xml"));  
				   transformer.transform(domSource, streamResult);  
				   
				   System.out.println("File saved to specified path!");  
		}//end of try block
		catch(ParserConfigurationException pce)
		{
			pce.printStackTrace(); 
		}//end of catch block
		catch (TransformerException tfe) 
		{  
			tfe.printStackTrace();  
		}  
	}//end of createXML
	
}//end of xmlCreator class
