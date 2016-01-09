//christopher hamm
//cosc 3011

public class clockCardSlot {
	private int X; //x value of upper left corner
	private int Y;//y value of upper left corner
	private int cardSlotPileRank; //rank of the card pile this cardSlot is located in (aces position has rank 1)
	private int cardSlotPilePosition; //position the cardSLot  in the card pile (pos 1 is the bottom and position 4 is the top)
																				//(pos 0 is temporary bottom card)
	private cardObject cardBeingHeld; //the card that is in this cardSlot
	
	
	public clockCardSlot(int inputX, int inputY, int inputCardSlotPileRank, int inputCardSlotPilePosition)
	{
		//set where the cardSLot is located
		X= inputX;
		Y= inputY;
		cardSlotPileRank= inputCardSlotPileRank;
		cardSlotPilePosition= inputCardSlotPilePosition;
		//set the cardBeing held to null
		cardBeingHeld= null;
	}//end of constructor
	
	public void setCardBeingHeld(cardObject inputCard)
	{
		cardBeingHeld= Main.copyCard(inputCard);
		cardBeingHeld.setX(X);
		cardBeingHeld.setY(Y);
	}
	
	public cardObject getCardBeingHeld()
	{
		return cardBeingHeld;
	}
	
	public int getCardSlotPileRank()
	{
		return cardSlotPileRank;
	}
	
	public int getCardSlotPilePosition()
	{
		return cardSlotPilePosition;
	}
}
