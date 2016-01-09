
//Christopher Hamm
//Project 5E revision 2

//COSC 3011


import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import java.util.Stack;

import javax.swing.*;



/**
 * This is the main class. This class controls everything.
 * 
 * @author Christopher Hamm
 * 
 */
public class Main
{
	static gameManager currentGameManager;
	static cardObject[] startingCardDeck;
	static cardObject[] cardDeckBeforeFirstShuffle;
	static cardObject[] cardDeckBeforeSecondShuffle;
	static cardObject[] cardDeckAfterFirstShuffle;
	static cardObject[] cardDeckAfterSecondShuffle;
	static clockCardSlot lastMovedCardInCardSlot;
	static int numberOfGamesPlayed=1;
	static int numberClockGamesPlayed=0;
	static int numberCLockGamesWon=0;
	static int numberOfGamesWon=0;
	static double percentageOfGamesWon= numberOfGamesWon/numberOfGamesPlayed;
	static int numberOfGamesCheatingWasEnabled=0;
	static int numberOfShufflesPerformed=0;
	static boolean allowIllegalCardPlacements= false;//default to false
	static Stack<undo_redo_object> UndoStack= new Stack<undo_redo_object>();
	static Stack<undo_redo_object> RedoStack= new Stack<undo_redo_object>();
	static boolean firstUndo = true; //default to true
	static boolean firstRedo= true; //default to true
	static boolean firstGameManagerLaunch= true; //default to true
	static boolean startOfClockSolitaire= true;
	static boolean firstLastMovedCardSlot= true;
	static int currentGameMode; //0=carpet solitaire 1=clock solitaire
	//master vector that holds all of the vectors of cardSlots
		static Vector<Vector<clockCardSlot>> clockCardDeckCardSlots= new Vector<Vector<clockCardSlot>>();
	// load the card image from the gif file.
				final static ImageIcon grayIcon = new ImageIcon(
						"src/cardImages/gray.gif");
				//card backside picture
				final static ImageIcon backIcon = new ImageIcon(	
						"src/cardImages/b.gif");
				final static ImageIcon goldIcon= new ImageIcon(
						"src/cardImages/gold.gif");
				
				// load the card image from the gif file.
				final static ImageIcon aceClubsIcon = new ImageIcon(
						"src/cardImages/aceClubs.gif");
				final static ImageIcon aceDiamondsIcon = new ImageIcon(
						"src/cardImages/aceDiamonds.gif");
				final   static ImageIcon aceHeartsIcon = new ImageIcon(
						"src/cardImages/aceHearts.gif");
				final   static ImageIcon aceSpadesIcon = new ImageIcon(
						"src/cardImages/aceSpades.gif");
				final  static  ImageIcon twoSpadesIcon = new ImageIcon(
						"src/cardImages/twoSpades.gif");
				final  static ImageIcon twoClubsIcon = new ImageIcon(
						"src/cardImages/twoClubs.gif");
				final  static ImageIcon twoDiamondsIcon = new ImageIcon(
						"src/cardImages/twoDiamonds.gif");
				final  static ImageIcon twoHeartsIcon = new ImageIcon(
						"src/cardImages/twoHearts.gif");
				final  static ImageIcon threeClubsIcon = new ImageIcon(
						"src/cardImages/threeClubs.gif");
				final   static ImageIcon threeDiamondsIcon = new ImageIcon(
						"src/cardImages/threeDiamonds.gif");
				final  static ImageIcon threeHeartsIcon = new ImageIcon(
						"src/cardImages/threeHearts.gif");
				final   static ImageIcon threeSpadesIcon = new ImageIcon(
						"src/cardImages/threeSpades.gif");

				final   static ImageIcon fourClubsIcon = new ImageIcon(
						"src/cardImages/fourClubs.gif");
				final static  ImageIcon fourDiamondsIcon = new ImageIcon(
						"src/cardImages/fourDiamonds.gif");
				final  static ImageIcon fourSpadesIcon = new ImageIcon(
						"src/cardImages/fourSpades.gif");
				final  static ImageIcon fourHeartsIcon = new ImageIcon(
						"src/cardImages/fourHearts.gif");

				final  static ImageIcon fiveClubsIcon = new ImageIcon(
						"src/cardImages/fiveClubs.gif");
				final  static ImageIcon fiveDiamondsIcon = new ImageIcon(
						"src/cardImages/fiveDiamonds.gif");
				final   static ImageIcon fiveSpadesIcon = new ImageIcon(
						"src/cardImages/fiveSpades.gif");
				final  static ImageIcon fiveHeartsIcon = new ImageIcon(
						"src/cardImages/fiveHearts.gif");
				final  static ImageIcon sixClubsIcon = new ImageIcon(
						"src/cardImages/sixClubs.gif");
				final  static ImageIcon sixDiamondsIcon = new ImageIcon(
						"src/cardImages/sixDiamonds.gif");
				final  static ImageIcon sixSpadesIcon = new ImageIcon(
						"src/cardImages/sixSpades.gif");
				final  static ImageIcon sixHeartsIcon = new ImageIcon(
						"src/cardImages/sixHearts.gif");

				final  static ImageIcon sevenClubsIcon = new ImageIcon(
						"src/cardImages/sevenClubs.gif");
				final  static ImageIcon sevenDiamondsIcon = new ImageIcon(
						"src/cardImages/sevenDiamonds.gif");
				final   static ImageIcon sevenSpadesIcon = new ImageIcon(
						"src/cardImages/sevenSpades.gif");
				final   static ImageIcon sevenHeartsIcon = new ImageIcon(
						"src/cardImages/sevenHearts.gif");

				final static  ImageIcon eightClubsIcon = new ImageIcon(
						"src/cardImages/eightClubs.gif");
				final   static ImageIcon eightDiamondsIcon = new ImageIcon(
						"src/cardImages/eightDiamonds.gif");
				final static  ImageIcon eightSpadesIcon = new ImageIcon(
						"src/cardImages/eightSpades.gif");
				final static  ImageIcon eightHeartsIcon = new ImageIcon(
						"src/cardImages/eightHearts.gif");

				final  static ImageIcon nineClubsIcon = new ImageIcon(
						"src/cardImages/nineClubs.gif");
				final static  ImageIcon nineDiamondsIcon = new ImageIcon(
						"src/cardImages/nineDiamonds.gif");
				final  static ImageIcon nineSpadesIcon = new ImageIcon(
						"src/cardImages/nineSpades.gif");
				final static  ImageIcon nineHeartsIcon = new ImageIcon(
						"src/cardImages/nineHearts.gif");
				final   static ImageIcon tenClubsIcon = new ImageIcon(
						"src/cardImages/tenClubs.gif");
				final  static  ImageIcon tenDiamondsIcon = new ImageIcon(
						"src/cardImages/tenDiamonds.gif");
				final  static ImageIcon tenSpadesIcon = new ImageIcon(
						"src/cardImages/tenSpades.gif");
				final  static ImageIcon tenHeartsIcon = new ImageIcon(
						"src/cardImages/tenHearts.gif");

				final static  ImageIcon jackClubsIcon = new ImageIcon(
						"src/cardImages/jackClubs.gif");
				final static  ImageIcon jackDiamondsIcon = new ImageIcon(
						"src/cardImages/jackDiamonds.gif");
				final  static ImageIcon jackSpadesIcon = new ImageIcon(
						"src/cardImages/jackSpades.gif");
				final  static ImageIcon jackHeartsIcon = new ImageIcon(
						"src/cardImages/jackHearts.gif");

				final  static ImageIcon queenClubsIcon = new ImageIcon(
						"src/cardImages/queenClubs.gif");
				final  static ImageIcon queenDiamondsIcon = new ImageIcon(
						"src/cardImages/queenDiamonds.gif");
				final  static ImageIcon queenSpadesIcon = new ImageIcon(
						"src/cardImages/queenSpades.gif");
				final  static ImageIcon queenHeartsIcon = new ImageIcon(
						"src/cardImages/queenHearts.gif");

				final  static ImageIcon kingClubsIcon = new ImageIcon(
						"src/cardImages/kingClubs.gif");
				final static  ImageIcon kingDiamondsIcon = new ImageIcon(
						"src/cardImages/kingDiamonds.gif");
				final  static ImageIcon kingSpadesIcon = new ImageIcon(
						"src/cardImages/kingSpades.gif");
				final  static ImageIcon kingHeartsIcon = new ImageIcon(
						"src/cardImages/kingHearts.gif");
				
				//arrays of ImageIcons
				static ImageIcon[] spadesImageIcons= {aceSpadesIcon, twoSpadesIcon, threeSpadesIcon, fourSpadesIcon, fiveSpadesIcon, sixSpadesIcon, sevenSpadesIcon, eightSpadesIcon, nineSpadesIcon, tenSpadesIcon, jackSpadesIcon, queenSpadesIcon, kingSpadesIcon};
				static ImageIcon[] heartsImageIcons= {aceHeartsIcon, twoHeartsIcon,threeHeartsIcon,fourHeartsIcon,fiveHeartsIcon,sixHeartsIcon,sevenHeartsIcon,eightHeartsIcon,nineHeartsIcon,tenHeartsIcon,jackHeartsIcon,queenHeartsIcon,kingHeartsIcon};
				static ImageIcon[] diamondsImageIcons= {aceDiamondsIcon, twoDiamondsIcon,threeDiamondsIcon,fourDiamondsIcon,fiveDiamondsIcon,sixDiamondsIcon,sevenDiamondsIcon,eightDiamondsIcon,nineDiamondsIcon,tenDiamondsIcon,jackDiamondsIcon,queenDiamondsIcon,kingDiamondsIcon};
				static ImageIcon[] clubsImageIcons= {aceClubsIcon, twoClubsIcon,threeClubsIcon,fourClubsIcon,fiveClubsIcon,sixClubsIcon,sevenClubsIcon,eightClubsIcon,nineClubsIcon,tenClubsIcon,jackClubsIcon,queenClubsIcon,kingClubsIcon};
				//making the gray cards
				final static cardObject grayCard = new cardObject(10,10,0,4,0);
				final static cardObject grayCard2 = new cardObject(10,113,0,4,1);
				final static cardObject grayCard3 = new cardObject(10,216,0,4,2);
				final static cardObject grayCard4 = new cardObject(10,319,0,4,3);
				
				static cardObject[] grayCardDeck= { grayCard, grayCard2, grayCard3, grayCard4};
				
				//making the cards that are spades
				final  static cardObject aceSpadesCard = new cardObject(10,10,1,0,0);
				final   static cardObject twoSpadesCard = new cardObject(10,10,2,0,1);
				final  static cardObject threeSpadesCard = new cardObject(10,10, 3,0,2);
				final  static cardObject fourSpadesCard = new cardObject(10,10,4,0,3);
				final   static cardObject fiveSpadesCard = new cardObject(10,10, 5,0,4);
				final   static cardObject sixSpadesCard = new cardObject(10,10,6,0,5);
				final  static cardObject sevenSpadesCard = new cardObject(10,10,7,0,6);
				final  static cardObject eightSpadesCard = new cardObject(10,10,8,0,7);
				final   static cardObject nineSpadesCard = new cardObject(10,10, 9,0,8);
				final  static cardObject tenSpadesCard = new cardObject(10,10,10,0,9);
				final   static cardObject jackSpadesCard = new cardObject(10,10,11,0,10);
				final  static cardObject queenSpadesCard = new cardObject(10,10,12,0,11);
				final  static cardObject kingSpadesCard = new cardObject(10,10, 13,0,12);
				//making the cards that are Hearts
				final  static cardObject aceHeartsCard = new cardObject(10,10,1,1,13);
				final   static cardObject twoHeartsCard = new cardObject(10,10, 2,1,14);
				final  static cardObject threeHeartsCard = new cardObject(10,10, 3,1,15);
				final  static cardObject fourHeartsCard = new cardObject(10,10,4,1,16);
				final  static cardObject fiveHeartsCard = new cardObject(10,10, 5,1,17);
				final   static cardObject sixHeartsCard = new cardObject(10,10, 6,1,18);
				final   static cardObject sevenHeartsCard = new cardObject(10,10, 7,1,19);
				final   static cardObject eightHeartsCard = new cardObject(10,10,8,1,20);
				final   static cardObject nineHeartsCard = new cardObject(10,10, 9,1,21);
				final  static cardObject tenHeartsCard = new cardObject(10,10,10,1,22);
				final static  cardObject jackHeartsCard = new cardObject(10,10, 11,1,23);
				final   static cardObject queenHeartsCard = new cardObject(10,10,12,1,24);
				final   static cardObject kingHeartsCard = new cardObject(10,10, 13,1,25);
				//making cards that are diamonds
				final  static cardObject aceDiamondsCard = new cardObject(10,10, 1,2,26);
				final  static cardObject twoDiamondsCard = new cardObject(10,10, 2,2,27);
				final  static cardObject threeDiamondsCard = new cardObject(10,10, 3,2,28);
				final   static cardObject fourDiamondsCard = new cardObject(10,10,4,2,29);
				final  static cardObject fiveDiamondsCard = new cardObject(10,10, 5,2,30);
				final  static cardObject sixDiamondsCard = new cardObject(10,10, 6,2,31);
				final  static cardObject sevenDiamondsCard = new cardObject(10,10, 7,2,32);
				final   static cardObject eightDiamondsCard = new cardObject(10,10, 8,2,33);
				final  static cardObject nineDiamondsCard = new cardObject(10,10, 9,2,34);
				final  static cardObject tenDiamondsCard = new cardObject(10,10, 10,2,35);
				final  static cardObject jackDiamondsCard = new cardObject(10,10,11,2,36);
				final  static cardObject queenDiamondsCard = new cardObject(10,10,12,2,37);
				final  static cardObject kingDiamondsCard = new cardObject(10,10,13,2,38);
				//making cards that are clubs
				final  static cardObject aceClubsCard = new cardObject(10,10,1,3,39);
				final  static cardObject twoClubsCard = new cardObject(10,10, 2,3,40);
				final   static cardObject threeClubsCard = new cardObject(10,10,3,3,41);
				final  static cardObject fourClubsCard = new cardObject(10,10, 4,3,42);
				final  static cardObject fiveClubsCard = new cardObject(10,10, 5,3,43);
				final  static cardObject sixClubsCard = new cardObject(10,10, 6,3,44);
				final  static cardObject sevenClubsCard = new cardObject(10,10,7,3,45);
				final  static cardObject eightClubsCard = new cardObject(10,10,8,3,46);
				final  static cardObject nineClubsCard = new cardObject(10,10, 9,3,47);
				final   static cardObject tenClubsCard = new cardObject(10,10, 10,3,48);
				final  static cardObject jackClubsCard = new cardObject(10,10, 11,3,49);
				final  static cardObject queenClubsCard = new cardObject(10,10, 12,3,50);
				final static cardObject kingClubsCard = new cardObject(10,10, 13,3,51);
				
				static cardObject[] cardDeck = {aceSpadesCard,twoSpadesCard,threeSpadesCard,fourSpadesCard, fiveSpadesCard, sixSpadesCard, sevenSpadesCard, eightSpadesCard, nineSpadesCard, tenSpadesCard, jackSpadesCard, queenSpadesCard, kingSpadesCard,aceHeartsCard, twoHeartsCard,threeHeartsCard, fourHeartsCard,fiveHeartsCard,sixHeartsCard,sevenHeartsCard, eightHeartsCard, nineHeartsCard, tenHeartsCard, jackHeartsCard, queenHeartsCard, kingHeartsCard,aceDiamondsCard, twoDiamondsCard,threeDiamondsCard, fourDiamondsCard,fiveDiamondsCard, sixDiamondsCard,sevenDiamondsCard, eightDiamondsCard, nineDiamondsCard, tenDiamondsCard, jackDiamondsCard, queenDiamondsCard, kingDiamondsCard,aceClubsCard, twoClubsCard,threeClubsCard,fourClubsCard,fiveClubsCard,sixClubsCard,sevenClubsCard,eightClubsCard,nineClubsCard,tenClubsCard,jackClubsCard,queenClubsCard,kingClubsCard};
				
				
	/**
	 * This is the main function. This function creates the JPanel and the JFrame window. It also creates a guiInterface.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		cardObject[] tempDeck= copyCardDeckArray(cardDeck);
		tempDeck= shuffleCardDeck(tempDeck);
		setStartingCardDeck(copyCardDeckArray(tempDeck));
		undo_redo_object initialUndo= new undo_redo_object(2,getStartingCardDeck());
		UndoStack.push(initialUndo);
		startOfClockSolitaire= true;
		firstLastMovedCardSlot=true;
		currentGameManager= new gameManager(tempDeck,true,-1);
		
	}
	public static int getNumberOfClockGamesWon()
	{
		return  numberCLockGamesWon;
	}
	public static int getNumberofClockGamesPlayed()
	{
		return numberClockGamesPlayed;
	}
	public static int getNumberOfShufflesPerformed()
	{
		return numberOfShufflesPerformed;
	}
	public static double getPercentageOfGamesWon()
	{
		percentageOfGamesWon= getNumberOfGamesWon()/getNumberOfGamesPlayed();
		return percentageOfGamesWon;
	}
	public static int getNumberOfGamesCheatingWasEnabled()
	{
		return numberOfGamesCheatingWasEnabled;
	}
	public static int getNumberOfGamesPlayed()
	{
		return numberOfGamesPlayed;
	}
	public static int getNumberOfGamesWon()
	{
		return numberOfGamesWon;
	}
	public static void clearGameStats()
	{
		numberOfGamesPlayed=1;
		numberOfGamesWon=0;
		percentageOfGamesWon= 0.0;
		numberOfGamesCheatingWasEnabled=0;
		numberClockGamesPlayed=1;
		numberCLockGamesWon=0;
	}
	public static boolean getAllowIllegalCardPlacementValue()
	{
		return allowIllegalCardPlacements;
	}
	public static void setAllowIllegalCardPlacementValue(boolean temp)
	{
		allowIllegalCardPlacements = temp;
	}
	public void displayVictoryMessage(boolean winningConditionsMet)
	{
		if(winningConditionsMet==true)
		{
			JOptionPane.showInternalMessageDialog(null,"You are Victorious","You Win", JOptionPane.INFORMATION_MESSAGE);
			Main.numberOfGamesWon++;
		}
	}
	public static void setLastMovedCardInCardSlot(clockCardSlot inputCardSlot)
	{
		 lastMovedCardInCardSlot = inputCardSlot;
	}
	public static clockCardSlot getLastMOvedCardInCardSlot()
	{
		return lastMovedCardInCardSlot;
	}
	public static ImageIcon getCardBacksidePicture()
	{
		return backIcon;
	}
	public static ImageIcon getCardPicture(int inputRank, int inputSuit)
	{
		
		int inputCardRank= inputRank -1;//minus one because array starts at zero
		int inputCardSuit= inputSuit ;
		
		 ImageIcon tempImageIcon= null;
		if(inputCardSuit == 0) //spades
		{
			tempImageIcon= spadesImageIcons[inputCardRank];
		}
		else if(inputCardSuit == 1)
		{
			tempImageIcon= heartsImageIcons[inputCardRank];
		}
		else if(inputCardSuit == 2)
		{
			tempImageIcon= diamondsImageIcons[inputCardRank];
		}
		else if(inputCardSuit == 3)
		{
			tempImageIcon= clubsImageIcons[inputCardRank];
		}
		else if(inputCardSuit == 4)
		{
			tempImageIcon= grayIcon;
		}
		else
		{
			System.out.println("ERROR: inputCardSuit did not match valid suit");
			System.out.println("ERROR: inputCardSuit: " + inputCardSuit);
		}
		return tempImageIcon;
	}
	public static void setNumberOfShufflesPerformed(int input)
	{
		numberOfShufflesPerformed= input;
		gameManager.statusBarLabel2.setText("					Number of Shuffles Performed: "+ input);
	}
	
	public static void setStartingCardDeck(cardObject[] theStartingCardDeck)
	{
		startingCardDeck= copyCardDeckArray(theStartingCardDeck);
	}
	public static void setCardDeckBeforeFirstShuffle(cardObject[] inputCardDeck)
	{
		cardDeckBeforeFirstShuffle= copyCardDeckArray(inputCardDeck);
	}
	public static void setCardDeckBeforeSecondShuffle(cardObject[] inputCardDeck)
	{
		cardDeckBeforeSecondShuffle= copyCardDeckArray(inputCardDeck);
	}
	public static void setCardDeckAfterFirstShuffle(cardObject[] inputCardDeck)
	{
		cardDeckAfterFirstShuffle= copyCardDeckArray(inputCardDeck);
	}
	public static void setCardDeckAfterSecondShuffle(cardObject[] inputCardDeck)
	{
		cardDeckAfterSecondShuffle= copyCardDeckArray(inputCardDeck);
	}
	public static cardObject[] getStartingCardDeck()
	{
		return startingCardDeck;
	}
	public static cardObject[] getCardDeckBeforeFirstShuffle()
	{
		return cardDeckBeforeFirstShuffle;
	}
	public static cardObject[] getCardDeckBeforeSecondShuffle()
	{
		return cardDeckBeforeSecondShuffle;
	}
	public static cardObject[] getCardDeckAfterFirstShuffle()
	{
		return cardDeckAfterFirstShuffle;
	}
	public static cardObject[] getCardDeckAfterSecondShuffle()
	{
		return cardDeckAfterSecondShuffle;
	}
	public static void replaceGameManager()
	{
		currentGameManager.clearJFrame();
		currentGameManager= null;
		startOfClockSolitaire= true;
		cardObject[] tempDeck= Main.copyCardDeckArray(cardDeck);
		tempDeck= shuffleCardDeck(tempDeck);
		setStartingCardDeck(tempDeck);
	
		currentGameManager= new gameManager(tempDeck, true,currentGameMode);
	}
	public static void replaceGameManager(int inputGameSelector)
	{
		currentGameManager.clearJFrame();
		currentGameManager= null;
		cardObject[] tempDeck= Main.copyCardDeckArray(cardDeck);
		tempDeck= shuffleCardDeck(tempDeck);
		setStartingCardDeck(tempDeck);
		currentGameManager= new gameManager(tempDeck, true,inputGameSelector);
	}
	public static void loadCardDeck(cardObject[] inputCardDeck)
	{
		currentGameManager.clearJFrame();
		currentGameManager= null;
		cardObject[] loadedCardDeck=  copyCardDeckArray(inputCardDeck);
		setStartingCardDeck(loadedCardDeck);
		currentGameManager= new gameManager(loadedCardDeck, false,currentGameMode);
		if(currentGameMode==1)
		{
			System.out.println("line prior to calling reload the card slot piles");
			guiInterface.reloadCardSlotPiles();
			System.out.println("line after to calling reload the card slot piles");
		}
	}
	public static void replayGame()
	{
		currentGameManager.clearJFrame();
		currentGameManager= null;
		cardObject[] tempDeck= copyCardDeckArray(getStartingCardDeck());
		
		
			currentGameManager= new gameManager(tempDeck, true,currentGameMode);
		
		
	}
	
	public static void shuffleExistingCardDeck(cardObject[] existingCardDeck)
	{
		currentGameManager.clearJFrame();
		currentGameManager= null;
		//do not reset startingCardDeck
		cardObject[] theShuffledCardDeck= Main.shuffleInvalidlyPlacedCards(Main.copyCardDeckArray(existingCardDeck));
		
		currentGameManager= new gameManager(theShuffledCardDeck, false,0);
	}
	public static cardObject[] shuffleCardDeck(cardObject[] inputCardDeck)
	{
		//randomize cardDeck array
		Collections.shuffle(Arrays.asList(inputCardDeck));	
		return inputCardDeck;
	}//end of shuffleCardDeck
	
	public static cardObject[] shuffleInvalidlyPlacedCards(cardObject[] inputCardDeck)
	{
		
		//copy inputCardDeck to temporaryDeck
		cardObject[] inboundCardDeck= Main.copyCardDeckArray(inputCardDeck);
		System.out.println("beginning of shuffle invalid");
		Main.validateCardDeck(inboundCardDeck);
		cardObject[] finalOutboundCardDeck= new cardObject[52]; //the final card deck to be returned
		Vector<cardObject> outboundCardDeck= new Vector<cardObject>();
		//make a copy of invalidly placed cards in a vector
		Vector<cardObject> invalidlyPlacedCards = new Vector<cardObject>();
		//loop through card deck
		for(int i=0; i < 52; i++)
		{
			//check to see if card is validly placed, if not add a copy to vector, if are copy to outBoundCardDeck
			boolean validlyPlaced= gameManager.currentGuiInterface.validCardPlacement(Main.copyCard(inboundCardDeck[i]));
			if(validlyPlaced== false)
			{
				invalidlyPlacedCards.add(Main.copyCard(inboundCardDeck[i]));
				System.out.println("card with suit: " + inboundCardDeck[i].getCardSuit() + " rank: " + inboundCardDeck[i].getCardRank()+ "is invalidly placed");
			}
			else
			{
				outboundCardDeck.add(Main.copyCard(inboundCardDeck[i]));
			}
		}//end of for loop
		System.out.println(" ");
		System.out.println("#####outboundCardDeck.size():" + outboundCardDeck.size());
		System.out.println("#####invalidlyPlacedCards.szie():" + invalidlyPlacedCards.size());
		//make list of all spaces
		int[] allXValues = {87,164,241,318,395,472,549,626,703,780,857,934,1011};
		int[] allYValues = {10,113,216,319};
		Vector<Integer> remainingXValues= new Vector<Integer>();
		Vector<Integer> remainingYValues= new Vector<Integer>();
		//generatethe remaining X and Y Values
		for(int i=0; i < allXValues.length; i++)
		{
			for(int j=0; j < allYValues.length; j++)
			{
				remainingXValues.add(allXValues[i]);
				remainingYValues.add(allYValues[j]);
			}
		}//end of for loop
		
		//looking for vacant slots
		for(int i=0; i < inboundCardDeck.length; i++) //check each card
		{
			
			int cardX= inboundCardDeck[i].getX();
			int cardY= inboundCardDeck[i].getY();
			//find and remove coords
			for(int j=0; j < remainingXValues.size(); j++)
			{
				if(remainingXValues.elementAt(j)== cardX)
				{
					//coords must be dealt with in pairs
					remainingXValues.remove(j);
					remainingYValues.remove(j);
					break;
				}
			}
		}//end of removing uses slot coords
		
		//make a list of the x and y values of the invalid cards
		int[] invalidXValues= new int[52];
		int[] invalidYValues= new int[52];
		int[][] invalidCoordValues= {invalidXValues, invalidYValues}; //also holds unsused coord values
		for(int i=0; i < invalidlyPlacedCards.size(); i++)
		{
				invalidCoordValues[0][i]= invalidlyPlacedCards.elementAt(i).getX();//setting the X values
				invalidCoordValues[1][i]= invalidlyPlacedCards.elementAt(i).getY();//setting the Y values	
		}
		int numberOfUnusedCoords= remainingXValues.size();
		for(int i=invalidlyPlacedCards.size(); i < invalidlyPlacedCards.size() + numberOfUnusedCoords; i++)
		{
			invalidCoordValues[0][i]= remainingXValues.elementAt(numberOfUnusedCoords -1);
			invalidCoordValues[1][i]= remainingYValues.elementAt(numberOfUnusedCoords -1);
			numberOfUnusedCoords--;
		}
			checkForAll52RightCoords(invalidCoordValues);
		//sort list of coords in decending order of x
		invalidCoordValues= Main.deepCopyDoubleArray(sortDescendingOrder(invalidCoordValues));
		Collections.shuffle(invalidlyPlacedCards);
		//set the coresponding x and y coords from list to the matching card in same position in the vector
		for(int i=0; i < invalidlyPlacedCards.size(); i++)
		{
				invalidlyPlacedCards.elementAt(i).setX(invalidCoordValues[0][i]); //set the x values
				invalidlyPlacedCards.elementAt(i).setY(invalidCoordValues[1][i]);//set the y values
		}
		System.out.println("invalidlyPlacedCards.size():" + invalidlyPlacedCards.size());
		System.out.println("invalidCoordsValues[0].size():" + invalidCoordValues[0].length);
		//copy cards from vector into first available space in the outboundCardDeck
			for(int i=0; i < invalidlyPlacedCards.size(); i++) 
			{
				outboundCardDeck.add(Main.copyCard(invalidlyPlacedCards.elementAt(i)));
			}
			//System.out.println("number of Null outbound cards: " + numberOfNullOutBoundCards);
			System.out.println("outBoundCardDeck.size():" + outboundCardDeck.size());
			//copy vector to outbound array
			for(int i=0; i < outboundCardDeck.size(); i++) 
			{
				finalOutboundCardDeck[i]= Main.copyCard(outboundCardDeck.elementAt(i));
				///System.out.println("finalOutboundCardDeck["+i+"] X:" + finalOutboundCardDeck[i].getX()+ " Y:" + finalOutboundCardDeck[i].getY());
			}
			Main.validateCardDeck(Main.copyCardDeckArray(finalOutboundCardDeck));
		return finalOutboundCardDeck;
	}
	public static int[][] deepCopyDoubleArray(int[][] inputDoubleArray)
	{
		int[] outboundXvalues= new int[inputDoubleArray[0].length];
		int[] outboundYvalues= new int[inputDoubleArray[1].length];
		int[][] outboundArray= {outboundXvalues, outboundYvalues};
		for(int i=0; i < inputDoubleArray[0].length; i++)
		{
			outboundArray[0][i]= inputDoubleArray[0][i];
			outboundArray[1][i]= inputDoubleArray[1][i];
		}
		return outboundArray;
	}
	
	public static int[][] sortDescendingOrder(int[][] inputDoubleArray)
	{
		
		Vector<Integer> theXValues= new Vector<Integer>();
		Vector<Integer> theYValues= new Vector<Integer>();
		Vector<Vector<Integer>> theInputCoords= new Vector<Vector<Integer>>();
		theInputCoords.add(theXValues);
		theInputCoords.add(theYValues);
		int numberOfSorted=0;//count how many coords have been sorted
		//copy xValue array and yvalue array
		for(int i=0; i < inputDoubleArray[0].length; i++)
		{
			theXValues.add(inputDoubleArray[0][i]);
			theYValues.add(inputDoubleArray[1][i]);
		}
		
		//make new array to hold the sort portion of the array
		int[] sortedXvalues= new int[inputDoubleArray[0].length];
		int[] sortedYvalues= new int[inputDoubleArray[1].length];
		int[][] sortedCoords= {sortedXvalues, sortedYvalues};
		
		int currentLargestX=0;
		int currentLargestXindex= 99999;
		int currentSmallestY=99999;
		while(numberOfSorted != inputDoubleArray[0].length )
		{
			currentLargestX= 0;
			currentLargestXindex= 99999;
			currentSmallestY= 99999;
			
			
			//find largest x coordinate in the array (that has not been sorted)
			for(int i=0; i < theXValues.size() ; i++)
			{
				if(theInputCoords.elementAt(0).elementAt(i) > currentLargestX)
				{
					currentLargestX= theInputCoords.elementAt(0).elementAt(i);
					currentSmallestY= theInputCoords.elementAt(1).elementAt(i);
					currentLargestXindex= i;
					//theInputCoords.elementAt(0).remove(i);
					//theInputCoords.elementAt(1).remove(i);
					//killafter for loop
					break;
				} 
				else if(theInputCoords.elementAt(0).elementAt(i) == currentLargestX)
				{
					//check y coord
					if(theInputCoords.elementAt(1).elementAt(i) < currentSmallestY)
					{
						currentLargestX= theInputCoords.elementAt(0).elementAt(i);
						currentSmallestY= theInputCoords.elementAt(1).elementAt(i);
						currentLargestXindex= i;
						//remove this theInputCoords.elementAt(0).remove(i);
						//remove this , kill after for looptheInputCoords.elementAt(1).remove(i);
						break;
					}
					
				}
				else
				{
					System.out.println("sortingCoordsArrays[" + i+"] else condition");
					System.out.println("theXValues.size():" + theXValues.size());
					System.out.println("numberOfSortedCoords:" + numberOfSorted);
				}
				
			}//end of for loop
			//System.out.println("theXValues.size() after for loop:" + theXValues.size());
			//add that set of coordinates to array
			sortedCoords[0][numberOfSorted]= currentLargestX;
			sortedCoords[1][numberOfSorted]= currentSmallestY;
			if(currentLargestXindex < theInputCoords.elementAt(0).size())
			{
				theInputCoords.elementAt(0).remove(currentLargestXindex);
				theInputCoords.elementAt(1).remove(currentLargestXindex);
				//insert remove statements
			}
			
			//System.out.println("sortedValuesAdded: X:" + currentLargestX+ " Y:"+ currentSmallestY);
			
			numberOfSorted++;
		}//end of while loop
		
		//when finished return the sorted array
		return sortedCoords;
	}
	public static boolean checkToSeeIfCardIsInSlot(int checkX, int checkY, Vector<Integer> listOfUsedXValues, Vector<Integer> listOfUsedYValues)
	{
		boolean cardSlotIsVacant= true;
		//check xValues
		for(int i=0; i< listOfUsedXValues.size(); i++)
		{
			if(listOfUsedXValues.elementAt(i) == checkX)
			{
				//check y value
				for(int j=0; j < listOfUsedYValues.size(); j++)
				{
					if(listOfUsedYValues.elementAt(j)== checkY)
					{
						cardSlotIsVacant= false;
					}
				}//end of for loop checking y values
			}//end of if
		}//end of for loop checking x values
		//System.out.println("checkingCardSLotVacancy, #nullXValues:" + numberOfNullXValues);
		//System.out.println("checkingCardSLotVacancy, #nullYValues:" + numberOfNullYValues);
		return cardSlotIsVacant;
	}
	
	public static undo_redo_object copyUndoRedoObject(undo_redo_object inputObject)
	{
		int tempModifierType= inputObject.getModifyType();
		//int tempCardRank= inputObject.getRankOfChangedCard();
		//int tempCardSuit= inputObject.getSuitOfChangedCard();
		//int tempCardX= inputObject.getX();
		//int tempCardY= inputObject.getY();
		cardObject[] tempCardDeck= Main.copyCardDeckArray(inputObject.getCardDeckObject());
		//if(inputObject.getCardDeckObject() != null)
		//{
			tempCardDeck= inputObject.getCardDeckObject();
			undo_redo_object outboundObject2= new undo_redo_object(tempModifierType, tempCardDeck);
			return outboundObject2;
		//}
		/*else
		{
			undo_redo_object outboundObject= new undo_redo_object(tempModifierType, tempCardRank, tempCardSuit, tempCardX, tempCardY);
			return outboundObject;
		} */	
	}
	
	public static cardObject[] copyCardDeckArray(cardObject[] inputCardDeck)
	{
		//new array to copy to 
		cardObject[] outBoundCardDeck = new cardObject[52];
		for(int i=0; i < inputCardDeck.length; i++)
		{
			if(inputCardDeck[i] != null)
			{
				int X= inputCardDeck[i].getX();
				int Y= inputCardDeck[i].getY();
				int prevX= inputCardDeck[i].getPrevX(); //previous valid X coord
				int prevY= inputCardDeck[i].getPrevY(); //previous valid Y coord
				int index= inputCardDeck[i].getCardIndex();
				int rank= inputCardDeck[i].getCardRank(); //card rank; graycard=0,  ace =1 , king =13
				int suit= inputCardDeck[i].getCardSuit(); //spades=0 hearts=1 diamonds=2 clubs=3 graycard=4
				cardObject tempCard= new cardObject(X,Y,prevX,prevY,rank,suit,index);
				if(inputCardDeck[i].getShowCardBacksideValue()==true)
				{
					tempCard.setShowCardBackside(true);
				}
				outBoundCardDeck[i]= tempCard;
			}
			else
			{
				outBoundCardDeck[i]= null;
				System.out.println("ERROR: trying to copy a null card in copyCardDeckArray at i: " + i);
			}
		}
		return outBoundCardDeck;
	}//end of copyCardDeckArray
	
	public static cardObject copyCard(cardObject inputCard)
	{
		if(inputCard != null)
		{
			int X= inputCard.getX();
			int Y= inputCard.getY();
			int prevX= inputCard.getPrevX();
			int prevY= inputCard.getPrevY();
			int index= inputCard.getCardIndex();
			int rank= inputCard.getCardRank();
			int suit= inputCard.getCardSuit();
			cardObject outBoundCard= new cardObject(X,Y,prevX,prevY,rank,suit,index);
			if(currentGameMode==1)
			{
				boolean tempShowCardBackside= inputCard.getShowCardBacksideValue();
				outBoundCard.setShowCardBackside(tempShowCardBackside);
			}
			return outBoundCard;
		}
		else
		{
			System.out.println("ERROR: trying to copy a null card");
			return inputCard;
		}
	}//end of copyCard
	
	public static void checkForAll52RightCoords(Vector<Vector<Integer>> inputCoordinateVector)
	{
		//this function makes sure that a vector contains all of the coordinates for the rightmost 52 cards (no ace column)
		//inputCoordinateVector[0] is the x values
		//inputCoordinateVector[1] is the y values
		int[] xCoords= {87,164,241,318,395,472,549,626,703,780,857,934,1011};
		int[] yCoords= {10,113,216,319};
		boolean coordinateMatchFound= false;
		System.out.println("===========CoordinateMatching[VECTOR VERSION]===========");
		if(inputCoordinateVector.elementAt(0).size() < 52)
		{
			System.out.println("NOTICE: inputCoordinateVector[0] has less than 52 coords!");
			System.out.println("NOTICE: #coords found in inputCoordinateVector[0]="+ inputCoordinateVector.elementAt(0).size());
		}
		if(inputCoordinateVector.elementAt(1).size() < 52)
		{
			System.out.println("NOTICE: inputCoordinateVector[1] has less than 52 coords!");
			System.out.println("NOTICE: #coords found in inputCoordinateVector[1]="+ inputCoordinateVector.elementAt(1).size());
		}
		//look for a matching x coord in xCoords
		for(int i=0; i < xCoords.length; i++)
		{
			coordinateMatchFound= false;//reset value
			//search vector for matching x value
			for(int vi=0; vi < inputCoordinateVector.elementAt(0).size(); vi++)
			{
				//if x coord match is found, look for cooresponding y value
				if(xCoords[i]== inputCoordinateVector.elementAt(0).elementAt(vi))
				{
					//look for matching y coords in yCoords
					for(int j=0; j < yCoords.length; j++)
					{
						//if y coords match, then state that the coordinateMatch was found
						if(yCoords[j] == inputCoordinateVector.elementAt(1).elementAt(vi))
						{
							coordinateMatchFound= true;
							System.out.println("coordinateMatch found X:" +xCoords[i]+ " Y:"+yCoords[j]);
							break;
						}
					}//end of for loop looking for matching y value in yCoords
					if(coordinateMatchFound==false)
					{
						System.out.println("NOTICE: no matching Y coord found, Y:" + yCoords[i]);
					}
				}//end of if x coordinate match found
			}//end of for loop that is looking for matching x value in vector
			if(coordinateMatchFound==false)//if value was not found
			{
				System.out.println("NOTICE: no matching X coord found, X:" + xCoords[i]);
			}
			//
		}//end of loop looking for matching x's
		System.out.println("=============endOfCoordCheck[VECTOR VERSION]=========");
	}//end of checkfor all 52 rightCoords
	
	public static void checkForAll52RightCoords(int[][] inputCoordinateDoubleArray)
	{
		//this function makes sure that an array contains all of the coordinates for the rightmost 52 cards (no ace column)
		//inputCoordinateDoubleArray[0] is the x values
		//inputCoordinateDoubleArray[1] is the y values
		boolean[] coordinateAlreadyMatched= new boolean[inputCoordinateDoubleArray[0].length]; //used to indicate coordinate has been matched already, dont use this one
		for(int i=0; i<coordinateAlreadyMatched.length; i++)
		{
			coordinateAlreadyMatched[i]= false;
		}
		int[] xCoords= {87,164,241,318,395,472,549,626,703,780,857,934,1011};
		int[] yCoords= {10,113,216,319};
		boolean coordinateMatchFound= false;
		System.out.println("===========CoordinateMatching[ARRAY VERSION]===========");
		if(inputCoordinateDoubleArray[0].length < 52)
		{
			System.out.println("NOTICE: inputCoordinateDoubleArray[0] has less than 52 coords!");
			System.out.println("NOTICE: #coords found in inputCoordinateDoubleArray[0]="+ inputCoordinateDoubleArray[0].length);
		}
		if(inputCoordinateDoubleArray[1].length < 52)
		{
			System.out.println("NOTICE: inputCoordinateDoubleArray[1] has less than 52 coords!");
			System.out.println("NOTICE: #coords found in inputCoordinateDoubleArray[1]="+ inputCoordinateDoubleArray[1].length);
		}
		//look for a matching x coord in xCoords
		for(int i=0; i < xCoords.length; i++)
		{
			coordinateMatchFound= false;//reset value
			//search vector for matching x value
			for(int vi=0; vi < inputCoordinateDoubleArray[0].length; vi++)
			{
				//if x coord match is found, look for cooresponding y value
				if((xCoords[i]== inputCoordinateDoubleArray[0][vi]) && coordinateAlreadyMatched[vi]==false)
				{
					//look for matching y coords in yCoords
					for(int j=0; j < yCoords.length; j++)
					{
						
						//if y coords match, then state that the coordinateMatch was found
						if((yCoords[j] == inputCoordinateDoubleArray[1][vi]) && coordinateAlreadyMatched[vi]==false)
						{
							coordinateMatchFound= true;
							coordinateAlreadyMatched[vi] = true;
							System.out.println("coordinateMatch found X:" +xCoords[i]+ " Y:"+yCoords[j]);
							break;
						}
						else
						{
							coordinateMatchFound= false;
						}
					}//end of for loop looking for matching y value in yCoords
					if(coordinateMatchFound==false)
					{
						System.out.println("NOTICE: no matching Y coord found, Y:" + yCoords[i]);
					}
				}//end of if x coordinate match found
			}//end of for loop that is looking for matching x value in vector
			if(coordinateMatchFound==false)//if value was not found
			{
				System.out.println("NOTICE: no matching X coord found, X:" + xCoords[i]);
			}
			//
		}//end of loop looking for matching x's
		System.out.println("=============endOfCoordCheck[ARRAY VERSION]=========");
	}//end of checkfor all 52 rightCoords array version
	public static void validateCardDeck(cardObject[] inputCardDeck)
	{
		Vector<cardObject> cardsNotFound= new Vector<cardObject>();
		//fill vector with new card deck
		cardObject[] newDeck= Main.copyCardDeckArray(cardDeck);
		for(int i=0; i < newDeck.length; i++)
		{
			cardsNotFound.add(Main.copyCard(newDeck[i]));
		}
		
		//find one of every card
		for(int index=0; index< inputCardDeck.length; index++)
		{
			//find this rank in the vector
			for(int i=0 ; i<cardsNotFound.size(); i++)
			{
				if(inputCardDeck[index].getCardRank()== cardsNotFound.elementAt(i).getCardRank())//if ranks match
				{
					//check if suit matches
					if(inputCardDeck[index].getCardSuit()== cardsNotFound.elementAt(i).getCardSuit())//if suits match
					{
						//remove the card from vector
						cardsNotFound.remove(i);
					}
				}
				//find this suit
			}
		}//end of top for loop
		
		//check to see if vector is empty
		if(cardsNotFound.isEmpty())
		{
			System.out.println("All cards where found");
		}
		else
		{
			for(int i=0; i < cardsNotFound.size(); i++)
			{
				System.out.println("card not found; rank:" + cardsNotFound.elementAt(i).getCardRank() + " suit:" + cardsNotFound.elementAt(i).getCardSuit());
			}
		}
	}
	
}
