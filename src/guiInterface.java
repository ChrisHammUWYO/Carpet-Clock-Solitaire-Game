//christopher hamm
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JOptionPane;
/**
 * This class declares  the arrays that hold the card objects. 
 * This class also overrides the paintComponent function.
 * This class declares a mouseAdpter object, and overrides mousePressed, mouseDragged, and mouseReleased functions.
 * @author Christopher Hamm
 *
 */
public class guiInterface extends JPanel 
{
		
	static cardObject[] cardDeck= new cardObject[52];
	int[] xGridSnapValues = {10,87,164,241,318,395,472,549,626,703,780,857,934,1011};
	int[] yGridSnapValues = {10,113,216,319};
	moveMouse myMovingMouse= new moveMouse(); //creates a new moveMouse object
	boolean firstPaint; 
	cardObject selectedCard= null;
	int gameSelectValue; 
	 //make vectors that hold the cardSLots and ad them to the clockCardDeckCardSLot vector and add slots to vectors
	static Vector<clockCardSlot> acesPile= new Vector<clockCardSlot>(); //holds the cardSlots in the aces position
	static Vector<clockCardSlot> twosPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> threesPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> foursPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> fivesPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> sixesPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> sevensPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> ninesPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> eightsPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> tensPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> jacksPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> queensPile= new Vector<clockCardSlot>();
	static Vector<clockCardSlot> kingsPile= new Vector<clockCardSlot>();
	
	/**
	 * This is the constructor for a guiInterface.
	 * This creates a MotionListener and a mouseListener.
	 * Then shuffles the cardDeck array.
	 * This takes no parameters.
	 */
	public guiInterface(cardObject[] theCardDeck, boolean theFirstPaintValue)
	{
		addMouseMotionListener(myMovingMouse); //creates the motion listeners for the mouse
		addMouseListener(myMovingMouse); //creates the listeners for the mouse
		firstPaint= theFirstPaintValue;
		gameSelectValue= Main.currentGameMode;
		System.out.println("TEST: GuiInterface gameSelectValue: " + gameSelectValue);
		//add loaded cards to cardDeck
		cardDeck= Main.copyCardDeckArray(theCardDeck);
		//if clock solitaire mode, set show backside== true
		if(Main.currentGameMode==1 && Main.startOfClockSolitaire==true)
		{
			for(int i=0; i<52; i++)
			{
				cardDeck[i].setShowCardBackside(true);
			}
			
		}
		
	}//end of guiInterface constructor
	/**
	 * This method shifts all the elements in the cardDeck array to the left by one. Starting at the startingIndex and goes up to the end of the cardDeck array.
	 * @param startIndex The position in the cardDeck array where you want to start the left shift.
	 */
	public void shiftCardDeckLeft(int startIndex)//shifts all cards inthe array to the previous array position
	{
		cardObject tempCard= cardDeck[startIndex];
		for(int i=startIndex; i < 51; i++)
		{
			cardDeck[i+1].setCardIndex(cardDeck[i].getCardIndex());
			cardDeck[i]= cardDeck[i+1];//shift card left one 
		}
		//set array 51 equal to card zero
		cardDeck[51]= tempCard;
		cardDeck[51].setCardIndex(51); //set card index to 51
	}
	/**
	 * This method overrides the existing predefined paintComponent function.
	 * This takes a graphics object as a parameter. 
	 * The first time this function is called, it initially draws all of the gray cards and the cards in cardDeck to a predefined location on the screen.
	 * After the first time this function is called, it redraws all card objects to the screen but it doesn't change the card object's coordinates
	 * @param g A graphics object.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(gameSelectValue==0)//carpet solitaire mode
		{
			if(firstPaint == true) //if it is the first time being called
			{
				int X= 0; //the x coordinate variable
				int initialX = 10; //initial X value
				int horizontalSpacing= 77; 
				int Y= 0; //the y coordinate variable
				int initialY= 10;  //initial y value
				int verticalSpacing= 103; //amount of pixels between the top of two rows of cards
				
				Y= initialY; //set y to 10 for first card on first row
				X= initialX; 
				
				//draw gray space for row 1
				Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g, X, Y);
				X= X + horizontalSpacing;
				//draw all of the spades
				
				for(int i=0; i < 13; i++)
				{
					//draw gray card
					Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g,  X, Y);
					//draw card ontop of gray card
					Main.getCardPicture(cardDeck[i].getCardRank(), cardDeck[i].getCardSuit()).paintIcon(this, g, X, Y);
					cardDeck[i].setX(X);
					cardDeck[i].setY(Y);
					cardDeck[i].setPrevX(X);
					cardDeck[i].setPrevY(Y);
					X= X + horizontalSpacing;
				}
				
				Y= Y + verticalSpacing; //reset Y for hearts
				X= initialX; //reset the X for the next suit of cards
				
				//draw gray space for row 2
				Main.getCardPicture(Main.grayCardDeck[1].getCardRank(), Main.grayCardDeck[1].getCardSuit()).paintIcon(this, g, X, Y);
				X= X + horizontalSpacing;
				//draw all hearts
				for(int i=13; i < 26; i++)
				{
					//draw gray card
					Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g,  X, Y);
					//draw card ontop of gray card
					Main.getCardPicture(cardDeck[i].getCardRank(), cardDeck[i].getCardSuit()).paintIcon(this, g, X, Y);
					cardDeck[i].setX(X);
					cardDeck[i].setY(Y);
					cardDeck[i].setPrevX(X);
					cardDeck[i].setPrevY(Y);
					X= X + horizontalSpacing;
				}
				
				Y= Y + verticalSpacing; //reset Y for Diamonds
				X= initialX; //reset for the next suit

				//draw gray space for row 3
				Main.getCardPicture(Main.grayCardDeck[2].getCardRank(), Main.grayCardDeck[2].getCardSuit()).paintIcon(this, g, X, Y);
				X= X + horizontalSpacing;
				//draw all diamonds
				for(int i=26; i < 39; i++)
				{
					//draw gray card
					Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g,  X, Y);
					//draw card ontop of gray card
					Main.getCardPicture(cardDeck[i].getCardRank(), cardDeck[i].getCardSuit()).paintIcon(this, g, X, Y);
					cardDeck[i].setX(X);
					cardDeck[i].setY(Y);
					cardDeck[i].setPrevX(X);
					cardDeck[i].setPrevY(Y);
					X= X + horizontalSpacing;
				}
				
				Y= Y + verticalSpacing; //reset Y for clubs
				X= initialX; //reset X for next suit of cards
				
				//draw gray space for row 4
				Main.getCardPicture(Main.grayCardDeck[3].getCardRank(), Main.grayCardDeck[3].getCardSuit()).paintIcon(this, g, X, Y);
				X= X + horizontalSpacing;
				//draw all clubs
				for(int i=39; i < 52; i++)
				{
					//draw gray card
					Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g,  X, Y);
					//draw card ontop of gray card
					Main.getCardPicture(cardDeck[i].getCardRank(), cardDeck[i].getCardSuit()).paintIcon(this, g, X, Y);
					cardDeck[i].setX(X);
					cardDeck[i].setPrevX(X);
					cardDeck[i].setPrevY(Y);
					cardDeck[i].setY(Y);
					X= X + horizontalSpacing;
				}
				firstPaint= false; 
			}//end of if firstPaint==true
			else //Does NOT change the card x and y values as they are drawn!!
			{
				//draw gray space for row 1
				Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g, Main.grayCardDeck[0].getX(), Main.grayCardDeck[0].getY());
				int X= 0; //the x coordinate variable
				int initialX = 10; //initial X value
				int horizontalSpacing= 77; 
				int Y= 0; //the y coordinate variable
				int initialY= 10;  //initial y value
				int verticalSpacing= 103; //amount of pixels between the top of two rows of cards
				
				Y= initialY; //set y to 10 for first card on first row
				X= initialX; 
				
				//draw gray space for row 1
				Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g, X, Y);
				X= X + horizontalSpacing;
				//draw row 1
				for(int i=0; i < 13; i++)
				{
					//draw gray card
					Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g,  X, Y);
					X= X + horizontalSpacing;
				}
				
				Y= Y + verticalSpacing; //reset Y for hearts
				X= initialX; //reset the X for the next suit of cards
				
				//draw gray space for row 2
				Main.getCardPicture(Main.grayCardDeck[1].getCardRank(), Main.grayCardDeck[1].getCardSuit()).paintIcon(this, g, X, Y);
				X= X + horizontalSpacing;
				//draw row 2
				for(int i=13; i < 26; i++)
				{
					//draw gray card
					Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g,  X, Y);
					X= X + horizontalSpacing;
				}
				
				Y= Y + verticalSpacing; //reset Y for Diamonds
				X= initialX; //reset for the next suit

				//draw gray space for row 3
				Main.getCardPicture(Main.grayCardDeck[2].getCardRank(), Main.grayCardDeck[2].getCardSuit()).paintIcon(this, g, X, Y);
				X= X + horizontalSpacing;
				//draw row 3
				for(int i=26; i < 39; i++)
				{
					//draw gray card
					Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g,  X, Y);
					X= X + horizontalSpacing;
				}
				
				Y= Y + verticalSpacing; //reset Y for clubs
				X= initialX; //reset X for next suit of cards
				
				//draw gray space for row 4
				Main.getCardPicture(Main.grayCardDeck[3].getCardRank(), Main.grayCardDeck[3].getCardSuit()).paintIcon(this, g, X, Y);
				X= X + horizontalSpacing;
				//draw row 4
				for(int i=39; i < 52; i++)
				{
					//draw gray card
					Main.getCardPicture(Main.grayCardDeck[0].getCardRank(), Main.grayCardDeck[0].getCardSuit()).paintIcon(this, g,  X, Y);
					X= X + horizontalSpacing;
				}
				
				//draw the cards on top of grey cards
				for(int i=0; i < 52; i++)
				{
					if(cardDeck[i] != null)
					{
						//draw card ontop of gray card
						Main.getCardPicture(cardDeck[i].getCardRank(), cardDeck[i].getCardSuit()).paintIcon(this, g, cardDeck[i].getX(), cardDeck[i].getY());
				
					}
					else
					{
						System.out.println("ERROR: getCardPicture is null in paintComponent at i: " + i);
					}
				}
				
			}//end of else condition for if(firstPaint==true)
		}//end of carpet solitaire paint
		else if(gameSelectValue==1)//clock solitaire mode
		{
			//INSERT CLOCK SOLITAIRE PAINT FUNCTION
			if(Main.startOfClockSolitaire==true)
			{
				int cardDeckPosition=0; //position index of the card I am looking at
				
				Main.clockCardDeckCardSlots.add(acesPile);
				//paint the gray spot underneath the bottom position
				Main.getCardPicture(0,4).paintIcon(this, g, 636, 117);
				for(int i=0; i<5; i++ )
				{
					int xValue= 636 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 117,1,i);
					acesPile.add(temp);
					if(i<4 )
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 117);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 117);
						}
						cardDeck[cardDeckPosition].setX(xValue);
						cardDeck[cardDeckPosition].setY(117);
						//System.out.println("cardDeck[cardDeckPosition] newX:" + cardDeck[cardDeckPosition].getX()+
							//	" newY:" + cardDeck[cardDeckPosition].getY());
						acesPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						//acesPile.elementAt(i).getCardBeingHeld().setX(xValue);
						//acesPile.elementAt(i).getCardBeingHeld().setY(117);
						cardDeckPosition++;
					}
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 734, 224);
				
				Main.clockCardDeckCardSlots.add(twosPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 734 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 224,1,i);
					twosPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 224);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 224);
							
						}
						twosPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 832, 331);
				
				Main.clockCardDeckCardSlots.add(threesPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 832 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 331,1,i);
					threesPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 331);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 331);
						}
						threesPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 734, 438);
				
				Main.clockCardDeckCardSlots.add(foursPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 734 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 438,1,i);
					foursPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 438);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 438);
						}
						foursPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 636, 545);
				
				Main.clockCardDeckCardSlots.add(fivesPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 636 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 545,1,i);
					fivesPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 545);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 545);
						}
						fivesPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 538, 652);
				
				Main.clockCardDeckCardSlots.add(sixesPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 538 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 652,1,i);
					sixesPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 652);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 652);
						}
						sixesPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
					
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 440, 545);
				
				Main.clockCardDeckCardSlots.add(sevensPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 440 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 545,1,i);
					sevensPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 545);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 545);
						}
						sevensPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
					
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 342, 438);
				
				Main.clockCardDeckCardSlots.add(eightsPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 342 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 438,1,i);
					eightsPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 438);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 438);
						}
						eightsPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
					
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 244, 331);
				
				Main.clockCardDeckCardSlots.add(ninesPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 244 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 331,1,i);
					ninesPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 331);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue,331);
						}
						ninesPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
					
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 342, 224);
				
				Main.clockCardDeckCardSlots.add(tensPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 342 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 224,1,i);
					tensPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 224);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 224);
						}
						tensPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
					
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 440, 117);
				
				Main.clockCardDeckCardSlots.add(jacksPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 440 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 117,1,i);
					jacksPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 117);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 117);
						}
						jacksPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
					
				}
				Main.getCardPicture(0,4).paintIcon(this, g,538, 10);
				
				Main.clockCardDeckCardSlots.add(queensPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 538 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 10,1,i);
					queensPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 10);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 10);
						}
						queensPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
					
				}
				Main.getCardPicture(0,4).paintIcon(this, g, 538, 331);
				
				Main.clockCardDeckCardSlots.add(kingsPile);
				for(int i=0; i<5; i++ )
				{
					int xValue= 538 + (12*i);
					clockCardSlot temp= new clockCardSlot(xValue, 331,1,i);
					kingsPile.add(temp);
					if(i<4)
					{
						if(cardDeck[cardDeckPosition].getShowCardBacksideValue()==true)
						{
							Main.getCardBacksidePicture().paintIcon(this,g,xValue, 331);
						}
						else
						{
							Main.getCardPicture(cardDeck[cardDeckPosition].getCardRank(),cardDeck[cardDeckPosition].getCardSuit()).paintIcon(this, g, xValue, 331);
						}
						kingsPile.elementAt(i).setCardBeingHeld(Main.copyCard(cardDeck[cardDeckPosition]));
						cardDeckPosition++;
					}
					
				} 
			}//end of if main startOfCLockSolitaire==true
			else //if Main startOfClockSolitaire==false
			{
				//aces
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(acesPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==1)
				{
					cardObject temp= Main.copyCard(acesPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
					System.out.println("Click on the Aces Pile");
				}
				//twos
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(twosPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==2)
				{
					cardObject temp= Main.copyCard(twosPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//threes
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(threesPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==3)
				{
					cardObject temp= Main.copyCard(threesPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//fours
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(foursPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==4)
				{
					cardObject temp= Main.copyCard(foursPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//fives
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(fivesPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==5)
				{
					cardObject temp= Main.copyCard(fivesPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//sixes
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(sixesPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==6)
				{
					cardObject temp= Main.copyCard(sixesPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//sevens
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(sevensPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==7)
				{
					cardObject temp= Main.copyCard(sevensPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//eight
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(eightsPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==8)
				{
					cardObject temp= Main.copyCard(eightsPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//nine
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(ninesPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==9)
				{
					cardObject temp= Main.copyCard(ninesPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//tens
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(tensPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==10)
				{
					cardObject temp= Main.copyCard(tensPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//jacks
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(jacksPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==11)
				{
					cardObject temp= Main.copyCard(jacksPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//queens
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(queensPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==12)
				{
					cardObject temp= Main.copyCard(queensPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
				//kings
				for(int i=0 ; i<4; i++)
				{
					cardObject temp= Main.copyCard(kingsPile.elementAt(i).getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + i);
					}
				}
				if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank()==13)
				{
					cardObject temp= Main.copyCard(kingsPile.lastElement().getCardBeingHeld());
					if(temp.getShowCardBacksideValue()==true)
					{
						Main.getCardBacksidePicture().paintIcon(this, g, temp.getX(), temp.getY());
					}
					else
					{
						Main.getCardPicture(temp.getCardRank(), temp.getCardSuit()).paintIcon(this,g,temp.getX(), temp.getY());
						System.out.println("temp X:" + temp.getX() +" Y:" + temp.getY()+" i:" + "4");
					}
				}
			}//end of else (main startingOfclocksolitaire==false
			
		}//end of clock solitaire paint
		
	}//end of Overridden paintCompnent
	
	public static cardObject[] getCardDeck()
	{
		cardObject[] outBoundCardDeck= new cardObject[52];
		outBoundCardDeck= Main.copyCardDeckArray(cardDeck);
		
		return outBoundCardDeck;
	}
	 public static cardObject flipCardOver(cardObject inputCard) //flips a card over
	 {
		cardObject outboundCard= Main.copyCard(inputCard); 
		 if(outboundCard.getShowCardBacksideValue()==true)
		 {
			 outboundCard.setShowCardBackside(false);
		 }
		 return outboundCard;
	 }
	 
	 public static void tempSaveCardSlotPiles()//used to temporarily save the cardSLotPiles into the master vector in main
	 {
		 int cardDeckPosition=0; //record the position in the cardDeck i am looking at
		
			 for(int j=0; j<4; j++)
			 {
				 cardDeck[cardDeckPosition]= Main.copyCard(acesPile.elementAt(j).getCardBeingHeld());
				// System.out.println("cardDeck["+i+"] X:" + cardDeck[i].getX()+ " Y:"+ cardDeck[i].getY());
				// System.out.println("acesPile.elementAt("+j+")");
				 //System.out.println("cardDeckPosition:" +cardDeckPosition);
				 cardDeckPosition++;
			 }
			
	
		 //twos
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(twosPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //threes
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(threesPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //fours
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(foursPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //fives
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(fivesPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //sixes
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(sixesPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //sevens
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(sevensPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //eights
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(eightsPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
			 //nines
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(ninesPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //tens
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(tensPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //jacks
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(jacksPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //queens
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(queensPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		 //kings
	
			 for(int j=0; j<4; j++)
			 {
			 cardDeck[cardDeckPosition]= Main.copyCard(kingsPile.elementAt(j).getCardBeingHeld());
			 cardDeckPosition++;
			 }
	
		
	 }//end of tempsave
	 public boolean winningConditionsMetClock()
	 {
		 boolean didYouWin= true; //default to true
		 if(Main.startOfClockSolitaire==false)
		 {
			 for(int i=0; i< cardDeck.length; i++)
			 {
				 if(cardDeck[i].getShowCardBacksideValue()==true)
				 {
					 didYouWin= false;
					 break;
				 }
			 }
		 }
		 
		 
		 return didYouWin;
	 }//end of winning conditions met clock
	 
	 
	 public boolean allCardsFaceUpInPile(Vector<clockCardSlot> inputVector)
	 {
		 boolean youLose=false;
		 int numCardsFaceUp=0;
		 for(int i=0; i<4; i++)
		 {
			 if(inputVector.elementAt(i).getCardBeingHeld().getShowCardBacksideValue()==false)
			 {
				 numCardsFaceUp++;
			 }
		 }
		 if(numCardsFaceUp==inputVector.size())
		 {
			 youLose= true;
		 }
		 return youLose;
	 }
	 
	 public static void reloadCardSlotPiles()
	 {
		//aces
		 acesPile.clear();
		 for(int i=0; i < 4; i++)
		 {
			cardObject tempCard= Main.copyCard(cardDeck[i]);
			clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),1,i);
			temp.setCardBeingHeld(tempCard);
			 acesPile.add(temp);
		 }
		 //twos
		 twosPile.clear();
		 for(int i=4; i < 8; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),2,i);
				temp.setCardBeingHeld(tempCard);
				 twosPile.add(temp);
		 }
		 //threes
		 threesPile.clear();
		 for(int i=8; i < 12; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),3,i);
				temp.setCardBeingHeld(tempCard);
				 threesPile.add(temp);
		 }
		 //fours
		 foursPile.clear();
		 for(int i=12; i < 16; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),4,i);
				temp.setCardBeingHeld(tempCard);
				 foursPile.add(temp);
		 }
		 //fives
		 fivesPile.clear();
		 for(int i=16; i < 20; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),5,i);
				temp.setCardBeingHeld(tempCard);
				 fivesPile.add(temp);
		 }
		 //sixes
		 sixesPile.clear();
		 for(int i=20; i < 24; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),6,i);
				temp.setCardBeingHeld(tempCard);
				 sixesPile.add(temp);
		 }
		 //sevens
		 sevensPile.clear();
		 for(int i=24; i < 28; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),7,i);
				temp.setCardBeingHeld(tempCard);
				 sevensPile.add(temp);
		 }
		 //eights
		 eightsPile.clear();
		 for(int i=28; i < 32; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),8,i);
				temp.setCardBeingHeld(tempCard);
				 eightsPile.add(temp);
		 }
		 //nines
		 ninesPile.clear();
		 for(int i=32; i < 36; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),9,i);
				temp.setCardBeingHeld(tempCard);
				 ninesPile.add(temp);
		 }
		 //tens
		 tensPile.clear();
		 for(int i=36; i < 40; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),10,i);
				temp.setCardBeingHeld(tempCard);
				 tensPile.add(temp);
		 }
		 //jacks
		 jacksPile.clear();
		 for(int i=40; i < 44; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),11,i);
				temp.setCardBeingHeld(tempCard);
				 jacksPile.add(temp);
		 }
		 //queens
		 queensPile.clear();
		 for(int i=44; i < 48; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),12,i);
				temp.setCardBeingHeld(tempCard);
				 queensPile.add(temp);
		 }
		 //kings
		 kingsPile.clear();
		 for(int i=48; i < 52; i++)
		 {
			 cardObject tempCard= Main.copyCard(cardDeck[i]);
				clockCardSlot temp= new clockCardSlot(tempCard.getX(), tempCard.getY(),13,i);
				temp.setCardBeingHeld(tempCard);
				 kingsPile.add(temp);
		 }
		 System.out.println("kings pile reloaded sucsessfully");
	 }
	
	/**
	 * This method snaps a card object the grid that is drawn on the screen.
	 * @param theSelectedCard The card object that is to be snapped to the grid.
	 */
	public void snapCardToGrid(cardObject theSelectedCard)
	{
		//SNAPPING TO THE X COORDINATE
		//look for x value that is closest to the card's X value
		int closestXGridIndex=0; //index of the x value closest to the card's x value
		int smallestXDistance= 99999999; //value representing the current closest Distance
		for(int i=0; i< xGridSnapValues.length; i++)
		{
			int tempDistanceValue= xGridSnapValues[i] - theSelectedCard.getX();
			if(tempDistanceValue < 0)//if negative, take absolute value
			{
				tempDistanceValue= tempDistanceValue*(-1);
			}
			if(tempDistanceValue < smallestXDistance)
			{
				closestXGridIndex=i; //record the index
				smallestXDistance= tempDistanceValue; //save new smallest distance value
			}
		}//end of for loop
		theSelectedCard.setX(xGridSnapValues[closestXGridIndex]);//snap the x value
		//SNAPPING TO THE Y COORDINATE
		int closestYGridIndex=0; //index of the y value closest to the card's y value;
		int smallestYDistance= 999999999; //value representing the current closest distance 
		for(int i=0; i < yGridSnapValues.length; i++)
		{
			int tempDistanceValue= yGridSnapValues[i] - theSelectedCard.getY();
			if(tempDistanceValue < 0)//if negative, take absolute value
			{
				tempDistanceValue= tempDistanceValue*(-1);
			}
			if(tempDistanceValue < smallestYDistance)
			{
				closestYGridIndex= i;//record the i
				smallestYDistance= tempDistanceValue;//save new smallest distance value
			}
		}//end of for loop
		theSelectedCard.setY(yGridSnapValues[closestYGridIndex]);//snap to y value
	}
	/**
	 * This method determines whether or not theSelectedCard is being placed in a position that violates any of the game rules.
	 * If the position is valid, then the method resets the card's prevX and prevY values to the current values.
	 * If the position is not valid, then the method sets the card back to its last valid position (prevX and PrevY)
	 * @param theSelectedCard the card being placed
	 * @return true if no rules are violated, false if rules have been violated
	 */
	public boolean validCardPlacement(cardObject theSelectedCard)
	{
		if(theRules(theSelectedCard)==true)
		{
			System.out.println("ok placement");
			theSelectedCard.setPrevX(theSelectedCard.getX());
			theSelectedCard.setPrevY(theSelectedCard.getY());
			//undo_redo_object temp= new undo_redo_object(0,theSelectedCard.getCardRank(),theSelectedCard.getCardSuit(),theSelectedCard.getX(),theSelectedCard.getY(),0,0);
			//Main.UndoStack.push(temp);
			//System.out.print("temp X: " + temp.getPrevXValue() + " Y: " + temp.getPrevYValue());
			return true;
		}
		else
		{
			System.out.println("not a valid cardPlacement");
			//set card to previous position
			theSelectedCard.setX(theSelectedCard.getPrevX());
			theSelectedCard.setY(theSelectedCard.getPrevY());
			Toolkit.getDefaultToolkit().beep();
			return false;
		}
	}//end of validCardPlacement
	/**
	 * This method checks every rule method, to see if the card placement will violate any of the rules.
	 * @param theSelectedCard card that is being placed
	 * @return a boolean value, true if no rules are violated
	 */
	public boolean theRules(cardObject theSelectedCard)
	{
		//check to see if any rule is violated
		if(rule_IsCardSlotVacant(theSelectedCard)==false)
		{
			System.out.println("Violated the card slot is vacant rule");
			return false;
		}
		else if(rule_IsCardSlottotheLeftVacant(theSelectedCard)==false)
		{
			System.out.println("Violated the card slot to left of this slot is vacant and this slot is vacant rule");
			return false;
		}
		else if(rule_AceInTheLeftmostColumn(theSelectedCard)==false)
		{
			System.out.println("violated the only an ace can be in the left column rule");
			return false;
		}
		else if(rule_NoCardCanBePlaceToTheRightofaKing(theSelectedCard)==false)
		{
			System.out.println("Violated the No card can be placed to the right of a king rule");
			return false;
		}
		else if(rule_SameSuitAsLeftCard_andNextRankfromLeftCard(theSelectedCard)==false)
		{
			System.out.println("Violated the Same suit as left card rule and Next rank form left card rule");
			return false;
		}
		else
			return true;
	}
	/**
	 * This method is a rule method. This rule method checks to see if you are placing a card in the leftmost column, if so, then it checks if it is an ace or not.
	 * @param theSelectedCard card being placed
	 * @return true if not violated, false if violated
	 */
	public boolean rule_AceInTheLeftmostColumn(cardObject theSelectedCard)
	{
		//RULE:an ace can go in any of the four rows in the leftmost column
		if(theSelectedCard.getX()== 10) //if x = 10, leftmost column
		{
			//System.out.println("cardRank: " + theSelectedCard.getCardRank());
			if(theSelectedCard.getCardRank()==1)//is it an Ace?
			{
				//make sure that there is no card currently there,if there is return false
				for(int i=0; i<cardDeck.length; i++)
				{
					if(cardDeck[i].getX() == 10)
						return true;
				}
			}//end of if card is an ace
			return false;
		}//end of if selectedcardx==10
		else
		{
			return true;
		}
	}
	/**
	 * This method is a rule method. This rule method makes sure the card is not directly to the right of a King, if the slot is empty.
	 * @param theSelectedCard the card being placed
	 * @return true if not violated, false if violated
	 */
	public boolean rule_NoCardCanBePlaceToTheRightofaKing(cardObject theSelectedCard)
	{
		//Is there a king to the left of this position?
		int leftCardX= theSelectedCard.getX() - 77;//getting the leftcard's x value
		if(leftCardX < 0)
		{
			return true;
		}
		//System.out.println("leftCardX: "+ leftCardX + " theSelectedCard Y: " + theSelectedCard.getY());
		//find the card with the same Y and the leftCardX value;
		for(int i=0; i<cardDeck.length; i++)
		{
			//System.out.println("card from deck: X: " + cardDeck[i].getX() + " Y: " + cardDeck[i].getY());
			if(cardDeck[i].getX()==leftCardX)
			{
				//System.out.println("found card with same X value as leftcard X");
				if( cardDeck[i].getY()==theSelectedCard.getY())
				{
					//System.out.println("found card with same X as leftX and same Y as card Y");
					//does this card have the rank of a king? if not, then fail, else pass
					if(cardDeck[i].getCardRank() == 13)
					{
						System.out.println("A king is to the left of this position");
						return false;
					}
				}
				
			}
		}
		return true;
	}
	/**
	 * This is a rule method. This rule method checks to make sure that the place you are trying to put the card is vacant.
	 * @param theSelectedCard the card to be placed
	 * @return true if spot is vacant, false if not vacant
	 */
	public boolean rule_IsCardSlotVacant(cardObject theSelectedCard)//if no, then fails the test
	{
		//is the spot currently vacant? if no, then fail the rule
				for(int i=0; i < cardDeck.length; i++)
				{
					
					if(theSelectedCard.getCardIndex() != cardDeck[i].getCardIndex())//make sure you are not looking at the selected card
					{
						if(cardDeck[i].getX() == theSelectedCard.getX())//if x values match
						{
							if(cardDeck[i].getY() == theSelectedCard.getY())//if the y values match
							{
								return false; //card is in that spot, cant put a card there
							}
						}
					}
					
					
				}
				return true;
	}
	/**
	 * This method is a rule method. This rule method checks to see if the card slot to the left of theselectedCards card slot is vacant.
	 * If it is vacant, then placing the card here would violate this rule.
	 * @param theSelectedCard the card object being placed
	 * @return true if the left spot is not vacant, and false if it is vacant
	 */
	public boolean rule_IsCardSlottotheLeftVacant(cardObject theSelectedCard) //if slot to the left is vacant and this slot is vacant, you cant place card here
	{
		if((rule_IsCardSlotVacant(theSelectedCard)==true)&&(theSelectedCard.getCardRank()== 1))
		{
			return true;
		}
		else if(rule_IsCardSlotVacant(theSelectedCard)==true)//is this slot vacant?
		{
			int leftCardSlotX= theSelectedCard.getX() - 77;//x value of left slot
			int leftCardSlotY= theSelectedCard.getY();//y value of the left card slot
			//check to see if leftcard is out of bounds
			if(leftCardSlotX < 0)
			{
				return true;
			}
			//look to see if there is a card in the left slot
			for(int i=0; i< cardDeck.length; i++)
			{
				if(cardDeck[i].getX()==leftCardSlotX)//does the x values match
				{
					if(cardDeck[i].getY()==leftCardSlotY)//does the y values match
					{
						return true;
					}
				}
			}
		} 
		return false;
	}
	/**
	 * This method is a rule method. This rule method checks to see if the card to the left of theSelectedCard is the same suit as the selected card.
	 * Then, the method checks to see if theSelectedCard is the next rank up from the card on the left.
	 * @param theSelectedCard the card being placed
	 * @return true if thSelectedCard is the same suit an is the next rank up from the card on the left
	 */
	public boolean rule_SameSuitAsLeftCard_andNextRankfromLeftCard(cardObject theSelectedCard)
	{
		int leftCardSlotX= theSelectedCard.getX() - 77; //x value of the left slot
		int leftCardSlotY= theSelectedCard.getY();
		//check to see if leftcardx is out of bounds
		if(leftCardSlotX < 0)
		{
			return true;
		}
		//find card in left slot
		for(int i=0; i < cardDeck.length; i++)
		{
			if(cardDeck[i].getX()==leftCardSlotX)//if x values match left slot
			{
				if(cardDeck[i].getY()==leftCardSlotY)//if y values match left slot
				{
					//do the suits match? if yes, proceed, if no then fail
					if(cardDeck[i].getCardSuit()==theSelectedCard.getCardSuit())
					{
						//is the selected ccard the next rank up from the left card
						int leftCardRank= cardDeck[i].getCardRank();
						if(theSelectedCard.getCardRank()== (leftCardRank +1))
						{
							return true;
						}
						else
						{
							return false;
						}
					}
					else
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	/**
	 * This method determines whether you have won the game or not. It checks to make sure all of the cards are in the correct positions.
	 * @return a boolean value stating if you won or not. True if you won
	 */
	public boolean winTheGame()
	{
		boolean winningConditionsMet = true; //default to true, if any violations found set to false
		
		//find the row1, slot 13 card
		cardObject currentCard= Main.grayCard; 
		for(int i=0; i < cardDeck.length; i++)
		{
			if(cardDeck[i].getX()==934)//see if x is in 13 slot
			{
				if(cardDeck[i].getY()==10)//see if y is row 1
				{
					currentCard= cardDeck[i];
				}
			}
		}
		if(currentCard==Main.grayCard)
		{
			//System.out.println("ERROR: did not find a card in the 13 position of row1");
			winningConditionsMet= false;
		}
		//check predesosor cards in row 1
		for(int i=0; i < xGridSnapValues.length -1; i++) //start from slot 13
		{
			if(rule_SameSuitAsLeftCard_andNextRankfromLeftCard(currentCard) != true)
			{
				//System.out.println("At index: " + i + "in row 1, violation of rank rule");
				winningConditionsMet= false;
			}
			currentCard= getCardToTheLeft(currentCard);
		}
		currentCard=Main.grayCard; //reset currentCard 
		//check to see if row 1 slot 14 is empty
		for(int i=0; i < cardDeck.length; i++)
		{
			if(cardDeck[i].getY()==10)//check to see if in first row
			{
				if(cardDeck[i].getX()==1011) //set if in slot 14
				{
					winningConditionsMet= false;
				}
			}
		}
		//find the row2, slot 13 card
		for(int i=0; i < cardDeck.length; i++)
		{
			if(cardDeck[i].getX()==934)//see if x is in 13 slot
			{
				if(cardDeck[i].getY()==113)//see if y is row 2
				{
					currentCard= cardDeck[i];
				}
			}
		}
		if(currentCard==Main.grayCard)
		{
			//System.out.println("ERROR: did not find a card in the 13 position of row2");
			winningConditionsMet= false;
		}
		//check predessecor cards in row 2
		for(int i=0; i < xGridSnapValues.length -1; i++) //start from slot 13
		{
			if(rule_SameSuitAsLeftCard_andNextRankfromLeftCard(currentCard) != true)
			{
				//System.out.println("At index: " + i + "in row 2, violation of rank rule");
				winningConditionsMet= false;
			}
			currentCard= getCardToTheLeft(currentCard);
		}
		currentCard=Main.grayCard; //reset currentCard 
		//check to see if row 2 slot 14 is empty
				for(int i=0; i < cardDeck.length; i++)
				{
					if(cardDeck[i].getY()==113)//check to see if in 2nd row
					{
						if(cardDeck[i].getX()==1011) //set if in slot 14
						{
							winningConditionsMet= false;
						}
					}
				}
		//find the row 3 , slot 13  card
		for(int i=0; i < cardDeck.length; i++)
		{
			if(cardDeck[i].getX()==934)//see if x is in 13 slot
			{
				if(cardDeck[i].getY()==216)//see if y is row 3
				{
					currentCard= cardDeck[i];
				}
			}
		}
		if(currentCard==Main.grayCard)
		{
			//System.out.println("ERROR: did not find a card in the 13 position of row3");
			winningConditionsMet= false;
		}
		//check predessecor cards in row 3
		for(int i=0; i < xGridSnapValues.length -1; i++) //start from slot 13
		{
			if(rule_SameSuitAsLeftCard_andNextRankfromLeftCard(currentCard) != true)
			{
				//System.out.println("At index: " + i + "in row 3, violation of rank rule");
				winningConditionsMet= false;
			}
			currentCard= getCardToTheLeft(currentCard);
		}
		currentCard=Main.grayCard; //reset currentCard
		//check to see if row 3 slot 14 is empty
				for(int i=0; i < cardDeck.length; i++)
				{
					if(cardDeck[i].getY()==216)//check to see if in 3rd row
					{
						if(cardDeck[i].getX()==1011) //set if in slot 14
						{
							winningConditionsMet= false;
						}
					}
				}
		//find the row4 , slot 13 card
		for(int i=0; i < cardDeck.length; i++)
		{
			if(cardDeck[i].getX()==934)//see if x is in 13 slot
			{
				if(cardDeck[i].getY()==319)//see if y is row 4
				{
					currentCard= cardDeck[i];
				}
			}
		}
		if(currentCard==Main.grayCard)
		{
			//System.out.println("ERROR: did not find a card in the 13 position of row4");
			winningConditionsMet= false;
		}
		//check predessesor cards in row 4
		for(int i=0; i < xGridSnapValues.length -1; i++) //start from slot 13
		{
			if(rule_SameSuitAsLeftCard_andNextRankfromLeftCard(currentCard) != true)
			{
				//System.out.println("At index: " + i + "in row 4, violation of rank rule");
				winningConditionsMet= false;
			}
			currentCard= getCardToTheLeft(currentCard);
		}
		currentCard= Main.grayCard;//reset currentCard
		//check to see if row 4 slot 14 is empty
				for(int i=0; i < cardDeck.length; i++)
				{
					if(cardDeck[i].getY()==319)//check to see if in 4th row
					{
						if(cardDeck[i].getX()==1011) //set if in slot 14
						{
							winningConditionsMet= false;
						}
					}
				}
		
		
		return winningConditionsMet;
	}
	/**
	 * This method rturns the card object that is to the left of the current card.
	 * @param currentCard the currently selected card
	 * @return cardObject to the left of the selected card
	 */
	public cardObject getCardToTheLeft(cardObject currentCard)
	{
		//find card to the left of current card
		cardObject leftCard= currentCard;
		for(int i=0; i < cardDeck.length; i++)
		{
			if(cardDeck[i].getY()==currentCard.getY())//find matching Y
			{
				if(cardDeck[i].getX()== currentCard.getX()-77)//find x to the left
				{
					leftCard= cardDeck[i];
				}
			}
		}
		if((leftCard==currentCard)&&(currentCard.getCardRank() != 1))
		{
			//System.out.println("ERROR: leftcard was not found");
		}
		return leftCard;
	}
	/**
	 * This method displays the victory message when you have won the game.
	 * @param winningConditionsMet boolean value for wether or not you won
	 */
	public void displayVictoryMessage(boolean winningConditionsMet)
	{
		if(winningConditionsMet==true)
		{
			JOptionPane.showMessageDialog(null,"You are Victorious");
		}
	}
	public  Vector<clockCardSlot> shiftCardSlotCardValues(Vector<clockCardSlot> inputVector)
	{
		Vector<clockCardSlot> outboundVector= new Vector<clockCardSlot>();
		if(Main.startOfClockSolitaire==true)
		{
			for(int i=0; i<3;i++)
			{
				int newX= inputVector.elementAt(i+1).getCardBeingHeld().getX();
				
				int newY= inputVector.elementAt(i+1).getCardBeingHeld().getY();
				System.out.println("inputVector["+(i+1)+"] X:" + newX+ " Y:"+ newY);
				//clockCardSlot temp= new clockCardSlot(newX,newY,inputVector.elementAt(i).getCardBeingHeld().getCardRank(),inputVector.elementAt(i).getCardSlotPilePosition() +1 );
				inputVector.elementAt(i).getCardBeingHeld().setX(newX);
				inputVector.elementAt(i).getCardBeingHeld().setY(newY);
				System.out.println("inputVector["+(i)+"] X:"+ inputVector.elementAt(i).getCardBeingHeld().getX()+ " Y:"+inputVector.elementAt(i).getCardBeingHeld().getY());
			}
			
		}
		else
		{
			for(int i=0; i<4;i++)
			{
				int newX= inputVector.elementAt(i+1).getCardBeingHeld().getX();
				
				int newY= inputVector.elementAt(i+1).getCardBeingHeld().getY();
				System.out.println("inputVector["+(i+1)+"] X:" + newX+ " Y:"+ newY);
				//clockCardSlot temp= new clockCardSlot(newX,newY,inputVector.elementAt(i).getCardBeingHeld().getCardRank(),inputVector.elementAt(i).getCardSlotPilePosition() +1 );
				inputVector.elementAt(i).getCardBeingHeld().setX(newX);
				inputVector.elementAt(i).getCardBeingHeld().setY(newY);
				System.out.println("inputVector["+(i)+"] X:"+ inputVector.elementAt(i).getCardBeingHeld().getX()+ " Y:"+inputVector.elementAt(i).getCardBeingHeld().getY());
			}
		}
		
		return inputVector; 
		
	}
	/**
	 * This is the moveMouse class that extends the MouseAdapter class.
	 * @author Christopher Hamm
	 *
	 */
	class moveMouse extends MouseAdapter
	{
		private int x; //coordinates of the mouse
		private int y;
		
		/**
		 * This method overrides the predefined mousePressed function.
		 * It takes a MouseEvent as a parameter.
		 * This method gets the x and y values of the mouse and sets x and y of the moveMouse class to those values.
		 * Then, it runs through the cardDeck array to see if the mouse coordinates are inside any of the card object's drawn picture. 
		 * If it finds a card that contains that coordinate, is sets the selectedCard object equal to the card object in the cardDeck array.
		 * It then shifts the cardDeck array to the left, by calling shiftCardDeckLeft function.
		 * Then it breaks the for loop.
		 * This does not return anything.
		 * 
		 * @param e A MouseEvent 
		 */
		@Override
		public void mousePressed(MouseEvent e)
		{
			x= e.getX(); //get x coordinates of where the press occurred 
			y= e.getY(); //get y coordinates of when the mouse was pressed
			if(Main.currentGameMode==1)
			{
				if(Main.startOfClockSolitaire ==true)//look at kingsPile
				{
					if((kingsPile.elementAt(3)).getCardBeingHeld().contains(x,y))
					{
						kingsPile.elementAt(3).getCardBeingHeld().setShowCardBackside(false);
						Main.setLastMovedCardInCardSlot((kingsPile.elementAt(3)));
						kingsPile= (shiftCardSlotCardValues(kingsPile));
						tempSaveCardSlotPiles();
						undo_redo_object temp2= new undo_redo_object(0,cardDeck);
						Main.UndoStack.push(temp2);
					} 
					Main.startOfClockSolitaire= false;
					tempSaveCardSlotPiles();
					//Main.loadCardDeck(cardDeck);
					reloadCardSlotPiles();
					repaint();
				}
				else //if not start of game
				{
					if(Main.getLastMOvedCardInCardSlot().getCardBeingHeld().contains(x,y))
					{
						if(Main.firstLastMovedCardSlot==true)
						{
							Main.firstLastMovedCardSlot=false;
							kingsPile.lastElement().getCardBeingHeld().setShowCardBackside(true);
							//kingsPile.remove(kingsPile.lastElement());
						}
						int pileRank= Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank();
						Main.getLastMOvedCardInCardSlot().getCardBeingHeld().setShowCardBackside(false);
						if(pileRank==1)
						{
							Collections.reverse(acesPile);
							acesPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(acesPile);
							Main.setLastMovedCardInCardSlot(acesPile.lastElement());
							acesPile= (shiftCardSlotCardValues(acesPile));
							acesPile.remove(acesPile.lastElement());
							
						}
						else if(pileRank==2)
						{
							Collections.reverse(twosPile);
							twosPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(twosPile);
							Main.setLastMovedCardInCardSlot(twosPile.lastElement());
							twosPile= (shiftCardSlotCardValues(twosPile));
							twosPile.remove(twosPile.lastElement());
						}
						else if(pileRank==3)
						{
							Collections.reverse(threesPile);
							threesPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(threesPile);
							Main.setLastMovedCardInCardSlot(threesPile.lastElement());
							threesPile= (shiftCardSlotCardValues(threesPile));
							threesPile.remove(threesPile.lastElement());
						}
						else if(pileRank==4)
						{
							Collections.reverse(foursPile);
							foursPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(foursPile);
							Main.setLastMovedCardInCardSlot(foursPile.lastElement());
							foursPile= (shiftCardSlotCardValues(foursPile));
							foursPile.remove(foursPile.lastElement());
						}
						else if(pileRank==5)
						{
							Collections.reverse(fivesPile);
							fivesPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(fivesPile);
							Main.setLastMovedCardInCardSlot(fivesPile.lastElement());
							fivesPile= (shiftCardSlotCardValues(fivesPile));
							fivesPile.remove(fivesPile.lastElement());
						}
						else if(pileRank==6)
						{
							Collections.reverse(sixesPile);
							sixesPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(sixesPile);
							Main.setLastMovedCardInCardSlot(sixesPile.lastElement());
							sixesPile= (shiftCardSlotCardValues(sixesPile));
							sixesPile.remove(sixesPile.lastElement());
						}
						else if(pileRank==7)
						{
							Collections.reverse(sevensPile);
							sevensPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(sevensPile);
							Main.setLastMovedCardInCardSlot(sevensPile.lastElement());
							sevensPile= (shiftCardSlotCardValues(sevensPile));
							sevensPile.remove(sevensPile.lastElement());
						}
						else if(pileRank==8)
						{
							Collections.reverse(eightsPile);
							eightsPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(eightsPile);
							Main.setLastMovedCardInCardSlot(eightsPile.lastElement());
							eightsPile= (shiftCardSlotCardValues(eightsPile));
							eightsPile.remove(eightsPile.lastElement());
						}
						else if(pileRank==9)
						{
							Collections.reverse(ninesPile);
							ninesPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(ninesPile);
							Main.setLastMovedCardInCardSlot(ninesPile.lastElement());
							ninesPile= (shiftCardSlotCardValues(ninesPile));
							ninesPile.remove(ninesPile.lastElement());
						}
						else if(pileRank==10)
						{
							Collections.reverse(tensPile);
							tensPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(tensPile);
							Main.setLastMovedCardInCardSlot(tensPile.lastElement());
							tensPile= (shiftCardSlotCardValues(tensPile));
							tensPile.remove(tensPile.lastElement());
						}
						else if(pileRank==11)
						{
							Collections.reverse(jacksPile);
							jacksPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(jacksPile);
							Main.setLastMovedCardInCardSlot(jacksPile.lastElement());
							jacksPile= (shiftCardSlotCardValues(jacksPile));
							jacksPile.remove(jacksPile.lastElement());
						}
						else if(pileRank==12)
						{
							Collections.reverse(queensPile);
							queensPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(queensPile);
							Main.setLastMovedCardInCardSlot(queensPile.lastElement());
							queensPile= (shiftCardSlotCardValues(queensPile));
							queensPile.remove(queensPile.lastElement());
						}
						else if(pileRank==13)
						{
							Collections.reverse(kingsPile);
							kingsPile.add(Main.getLastMOvedCardInCardSlot());
							Collections.reverse(kingsPile);
							Main.setLastMovedCardInCardSlot(kingsPile.lastElement());
							kingsPile= (shiftCardSlotCardValues(kingsPile));
							kingsPile.remove(kingsPile.lastElement());
						}
						else
						{
							System.out.println("ERROR: pileRank is invalid");
						}
						
						tempSaveCardSlotPiles();
						//Main.loadCardDeck(cardDeck);
						reloadCardSlotPiles();
						repaint();
					}
					//System.out.println("main's lastMOvedCardslot ,card rank:" +(Main.getLastMOvedCardInCardSlot()).getCardBeingHeld().getCardRank()+" suit:"+ Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardSuit());
					if(winningConditionsMetClock()==true)
					{
						JOptionPane.showMessageDialog(gameManager.currentJFrame, "You Have WOn the Game!");
						Main.numberCLockGamesWon++;
					}
					else
					{
						System.out.println("you have not won yet");
					}
				}
			}
			else //if carpet solitaire
			{
				//find if cursor is inside a card, if so which card
				for(int i=51; i > -1; i--)
				{
					if(cardDeck[i].contains(x,y))
					{
						selectedCard= cardDeck[i];//set which card is the currently selected card
						System.out.println("selectedCard is origiannly at cardDeck["+i+"]");
						shiftCardDeckLeft(i);
						System.out.println("selectedCards rank:" + selectedCard.getCardRank());
						System.out.println("selectedCards suit:" + selectedCard.getCardSuit());
						break;
					}//end of if contains
				}
			}
			
		}
		
		
		/**
		 * This method overrides the mouseDragged function.
		 * It takes a MouseEvent as a parameter.
		 * This method calculates the change in the x and y values.
		 * It then adds the selectedCard's x and y values to the change in x and y. Then sets the resulting values to the selected card object's x and y.
		 * After that, it calls the repaint function.
		 * Then changes the x and y of the moveMouse object to accomodate from the change in the x and y values of the mouse.
		 * 
		 * @param e A MouseEvent 
		 */
		@Override
		public void mouseDragged(MouseEvent e)
		{
			if(selectedCard != null)
			{
				int dx = e.getX() -x; //get the change in x coordinate
				int dy = e.getY() - y; //get the change in y coordinate
				selectedCard.setX(selectedCard.getX() + dx);//change the elected card coords
				selectedCard.setY(selectedCard.getY()+ dy);
				repaint();
				x += dx; //set the new x value
				y += dy; //set the new y value
			}
		}//end of mouseDragged
		/**
		 * This method sets the selectedCard object to null;
		 * It takes a MouseEvent as a parameter.
		 *  
		 * @param e A MouseEvent
		 */
		public void mouseReleased(MouseEvent e)
		{
			if(selectedCard != null)
			{
				if(Main.currentGameMode==1)
				{
					int rankOfLastMovedCard= Main.getLastMOvedCardInCardSlot().getCardBeingHeld().getCardRank();
					if(rankOfLastMovedCard==1)
					{
						if(allCardsFaceUpInPile(acesPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==2)
					{
						if(allCardsFaceUpInPile(twosPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==3)
					{
						if(allCardsFaceUpInPile(threesPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==4)
					{
						if(allCardsFaceUpInPile(foursPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==5)
					{
						if(allCardsFaceUpInPile(fivesPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==6)
					{
						if(allCardsFaceUpInPile(sixesPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==7)
					{
						if(allCardsFaceUpInPile(sevensPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==8)
					{
						if(allCardsFaceUpInPile(eightsPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==9)
					{
						if(allCardsFaceUpInPile(ninesPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==10)
					{
						if(allCardsFaceUpInPile(tensPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==11)
					{
						if(allCardsFaceUpInPile(jacksPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==12)
					{
						if(allCardsFaceUpInPile(queensPile)==true)
						{
							System.out.println("You have lost");
						}
					}
					else if(rankOfLastMovedCard==13)
					{
						if(allCardsFaceUpInPile(kingsPile)==true)
						{
							System.out.println("You have lost");
						}
					}
				}
				snapCardToGrid(selectedCard);
				//if cheating is disabled, check this, if not, then ignore
				if(Main.getAllowIllegalCardPlacementValue()==false)
				{
					validCardPlacement(selectedCard);
				}
				else
				{
					//ignore the rule
				}
				undo_redo_object temp2= new undo_redo_object(0,cardDeck);
				Main.UndoStack.push(temp2);
				System.out.println("new undo_redo_object was added to the undo stack"); 
				//undo_redo_object temp= new undo_redo_object(0,selectedCard.getCardRank(),selectedCard.getCardSuit(),selectedCard.getX(),selectedCard.getY());
				//Main.UndoStack.push(temp);
				//System.out.print("temp X: " + temp.getX() + " Y: " + temp.getY());
				
				selectedCard.setOriginalPositionValue(false);
				repaint();
				selectedCard= null; //reset the value	
				if(winTheGame()==true)
				{
					System.out.println("You are victorious!");
					displayVictoryMessage(true);
				}
				else
				{
					System.out.println("winning conditions not met");
				}
			}
			
		}
		
	}
}
