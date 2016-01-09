

/**
 * This is the cardObject class. This class extends Main. 
 * The cardObject class is used to create card objects.
 * @author Christopher Hamm
 *
 */
public class cardObject extends Main
{
	private int X;
	private int Y;
	private int prevX; //previous valid X coord
	private int prevY; //previous valid Y coord
	private int index;
	private int rank; //card rank; graycard=0,  ace =1 , king =13
	private int suit; //spades=0 hearts=1 diamonds=2 clubs=3 graycard=4
	private String rank_Suit_ID; //ID code, first two chars are rank, third char is suit
	private boolean originalPosition; //is the card in the original place that it was placed when a new game started
	private boolean showCardBackside= false;//for clock solitaire only
	
	/**
	 * This is the constructor for a cardObject. 
	 * @param x This is the x value that specifies where the upper left corner of the card is currently located.
	 * @param y This is the y value that specifies where the upper left corner of the card is currently located.
	 * @param cardPicture This is the picture that will be drawn when the card is drawn to the screen.
	 * @param cardIndex This value represents where the card is in the cardDeck[] array, which is declared in guiInterface.
	 */
	public cardObject(int x, int y,int cardRank,int cardSuit, int cardIndex)
	{
		setX(x);
		setY(y);
		setPrevX(x);
		setPrevY(y);
		setRankSuitID(cardRank, cardSuit);
		setCardRank(cardRank);
		setCardSuit(cardSuit);
		setCardIndex(cardIndex);
		setOriginalPositionValue(true);
		setShowCardBackside(false);
	}
	/**
	 * Overloaded version of default constructor. This is for the xmlReader to use to load the card Data
	 * @param x
	 * @param y
	 * @param newPrevX
	 * @param newPrevY
	 * @param cardPicture
	 * @param cardRank
	 * @param cardSuit
	 * @param cardIndex
	 */
	public cardObject(int x, int y, int newPrevX, int newPrevY,int cardRank,int cardSuit, int cardIndex)
	{
		setX(x);
		setY(y);
		setPrevX(newPrevX);
		setPrevY(newPrevY);
		setRankSuitID(cardRank,cardSuit);
		setCardRank(cardRank);
		setCardSuit(cardSuit);
		setCardIndex(cardIndex);
		setOriginalPositionValue(true);
		setShowCardBackside(false);
	}
	
	/**
	 * This method is used to set a new x value to the card.
	 * @param newX The new x value that specifies where the upper left corner of the card is
	 */
	public void setX(int newX)
	{
		X = newX;
	}
	/**
	 * This method is used to set a new y value to the card.
	 * @param newY The new y value that specifies where the upper left corner of the card is
	 */
	public void setY(int newY)
	{
		Y = newY;
	}
	/**
	 * This method sets the previous x value of a card object. This is the last valid card coordinate
	 * @param x the coordinate X
	 */
	public void setPrevX(int x)
	{
		prevX=x;
	}
	/**
	 * This method sets the previous y value for a card object. This is the last valid card coordinate
	 * @param y The coordinate Y
	 */
	public void setPrevY(int y)
	{
		prevY= y;
	}
	public String setRankSuitID(int inputRank, int inputSuit)
	{
		String ID;
		if(rank < 10)
		{
			ID= "0" + inputRank + inputSuit;
		}
		else //if 10 or more
		{
			ID= "" + inputRank + inputSuit;
		}
		return ID;
	}
	/**
	 * This method sets the card rank to a card object.
	 * @param x The rank of the card
	 */
	public void setCardRank(int x)
	{
		rank= x;
	}
	/**
	 * This method sets the card suit to a card object.
	 * @param x The suit of the card; Spades=0, hearts=1, diamonds=2, clubs=3, greycard=4
	 */
	public void setCardSuit(int x)
	{
		suit= x;
	}
	/**
	 * This method is used to set the value of cardIndex for a card.
	 * @param cardIndex The new index value that is to be set to the card. If this value is a negative number, the system will output to the command line an error message.
	 */
	public void setCardIndex(int cardIndex)
	{
		if(cardIndex < 0)
		{
			System.out.println("Error: negative card index");
			System.out.println("Current index: " + getCardIndex());
			System.out.println("Attempted to set index to: " + cardIndex);
		}
		else
			index= cardIndex;
	}
	
	public void setOriginalPositionValue(boolean inputValue)
	{
		originalPosition= inputValue;
	}
	
	public void setShowCardBackside(boolean input)
	{
		showCardBackside= input;
	}
	/**
	 * This method retrieves the x value from the card object.
	 * @return        The X value of the card object, that specifies where the upper left corner of the card is located.
	 */
	public int getX()
	{
		return X;
	}
	/**
	 * This method retrieves the y value from the card object.
	 * @return         The Y value of the card object, that specifies where the upper left corner of the card is located.
	 */
	public int getY()
	{
		return Y;
	}
	/**
	 * This method returns the prevX value of the card object.
	 * @return prevX 
	 */
	public int getPrevX()
	{
		return prevX;
	}
	/**
	 * This method returns the prevY value of a card object.
	 * @return prevY
	 */
	public int getPrevY()
	{
		return prevY;
	}
	
	
	public String getRankSuitID()
	{
		return rank_Suit_ID;
	}
	/**
	 * This method returns a card objects rank.
	 * 
	 * @return rank The rank of the card object
	 */
	public int getCardRank()
	{
		return rank;
	}
	/**
	 * This method returns a card objects suit
	 * @return suit The suit of the card object
	 */
	public int getCardSuit()
	{
		return suit;
	}
	/**
	 * This method retrieves the card index value for the card object.
	 * @return		The cardIndex value of the card object.
	 */
	public int getCardIndex()
	{
		return index;
	}
	
	public boolean getOriginalPositionValue()
	{
		return originalPosition;
	}
	
	public boolean getShowCardBacksideValue()
	{
		return showCardBackside;
	}
	/**
	 * This method determines if an x and a y coordinate are inside the card object's drawn picture.
	 * If the coordinates are inside the card object's drawn picture, it will return true.
	 * If the coordinates are not inside the card object's drawn picture, it will return false.
	 * @param x The x coordinate to be checked
	 * @param y The y coordinate to be checked
	 * @return	true or false, depending on whether or not the coordinates provided are in the card object's picture or not.
	 */
	public boolean contains(int x, int y)//is the coordinates inside the area of this card
	{
		if((x > getX()) && x < getX()+73)//if within x bounds
		{
			if(y > getY() && y < getY()+97)
			{
				System.out.println("point is contained in this card " + getCardIndex());
				return true;	
			}
			else
				return false;
		}
			
		else
			return false;
	}
	
}

