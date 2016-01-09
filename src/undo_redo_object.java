//christopher hamm
//cosc 3011
//project 5

public class undo_redo_object
{
	int modifyType; //0= single cardChanged, 1= deckwas Shuffled, 2= undo to the startingDeck
	//int rankOfChangedCard;
	//int suitOfChangedCard; 
	//int xValue;
	//int yValue;
	cardObject[] theCardDeckObject; //used to hold a carddeck
	//the stack that the objects are in determines whether the object is a undo or a redo
	/*public undo_redo_object(int inputModifyType, int inputRankOfChangedCard, int inputSuitOfChangedCard, int inputXValue, int inputYValue)
	{
		//determine what kind of modifier it is
		if(inputModifyType == 0)//changed a single Card
		{
			modifyType= 0;
			rankOfChangedCard= inputRankOfChangedCard;
			suitOfChangedCard= inputSuitOfChangedCard;
			xValue= inputXValue;
			yValue= inputYValue;
		}
		else if(inputModifyType == 1)//shuffled the deck
		{
			modifyType= 1;
			rankOfChangedCard= 0;//graycard rank
			suitOfChangedCard= 5;//graycard suit
			xValue= inputXValue;
			yValue= inputYValue;
		}
		else
		{
			System.out.println("ERROR: invalid inputModifierType");
		}
	}//end of constructor */
	public undo_redo_object(int inputModifyType, cardObject[] inputCardDeck)//overloaded constructor
	{
		modifyType= inputModifyType;
		theCardDeckObject= Main.copyCardDeckArray(inputCardDeck);
	}//end of overloaded constructor 
	public cardObject[] getCardDeckObject()
	{
		return theCardDeckObject;
	}
	public void setModifyType(int inputValue)
	{
		if(inputValue==0 || inputValue==1 || inputValue==2)
		{
			modifyType= inputValue;
		}
		else
		{
			System.out.println("ERROR: invalid modifier value. must be 0 or 1");
		}
	}
	/*public void setRankOfChangedCard(int inputRank)
	{
		rankOfChangedCard= inputRank;
	}
	public void setSuitOfChangedCard(int inputSuit)
	{
		suitOfChangedCard= inputSuit;
	}
	public void setXValue(int inputX)
	{
		xValue= inputX;
	}
	public void setYValue(int inputY)
	{
		yValue= inputY;
	} */
	public int getModifyType()
	{
		return modifyType;
	}
	/*
	public int getRankOfChangedCard()
	{
		return rankOfChangedCard;
	}
	public int getSuitOfChangedCard()
	{
		return suitOfChangedCard;
	}
	public int getX()
	{
		return xValue;
	}
	public int getY()
	{
		return yValue;
	} */
}//end of class
