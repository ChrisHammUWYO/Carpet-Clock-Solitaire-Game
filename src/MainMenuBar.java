//christopher hamm
//project 5
//cosc 3011

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class MainMenuBar extends JPanel
{
	
	public MainMenuBar(JMenuBar inputMenuBar)
	{
		setLayout(new BorderLayout());
		
		inputMenuBar= setupMenuBar(inputMenuBar);
	}
	private JMenuBar setupMenuBar(JMenuBar inputMenuBar)
	{
		//add the File dropdown menu to the menubar*********************
		JMenu fileMenu= new JMenu("File");
		inputMenuBar.add(fileMenu);
		
		//add the dropdown new game option for File dropdown tab
		JMenuItem newGameOption= new JMenuItem("New Game");
		newGameOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileMenu.add(newGameOption);
		
		//define the action for this button
		newGameOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.allowIllegalCardPlacements= false;
				
				Main.replaceGameManager();
				Main.numberOfGamesPlayed++;
				gameManager.wasCheatingEnabled=false;
				Main.numberOfShufflesPerformed=0;
				gameManager.statusBarLabel.setText("Currently Cheating is Disabled");
				gameManager.statusBarLabel2.setText("					Number of Shuffles Performed: "+ Main.getNumberOfShufflesPerformed());
			}
		});
		
		//add the dropdown open option for File dropdown tab
		JMenuItem openOption= new JMenuItem("Load Game");
		openOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileMenu.add(openOption);
		
		//define action for this option
		openOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//creates the file chooser
				String userHomeFolder= System.getProperty("user.home");
				JFileChooser theFileChooser= new JFileChooser(userHomeFolder);
				FileNameExtensionFilter extensionFilter= new FileNameExtensionFilter("XML save file", "xml");
				theFileChooser.setFileFilter(extensionFilter);
				File inputSaveFile= null;
				
				//theFileChooser.setCurrentDirectory();
				int returnValue = theFileChooser.showOpenDialog(getParent());
				if(returnValue == JFileChooser.APPROVE_OPTION)
				{
					inputSaveFile= theFileChooser.getSelectedFile(); //holds the save file you selected
					System.out.println("You chose to open this file: "+ theFileChooser.getSelectedFile().getName());
				}
				//creates the parsor for xml files
				   cardObject[] loadedCardDeck= Main.copyCardDeckArray(xmlReader.readXML(inputSaveFile));
				   if(Main.currentGameMode==1)
				   {
					//   guiInterface.reloadCardSlotPiles();
				   }
				   Main.loadCardDeck(loadedCardDeck);
				   System.out.println("a new xmlReader has loaded the saved card deck");
				   
			}
		});

		//add the dropdown replay game option for File dropdown tab
		JMenuItem replayGameOption= new JMenuItem("Replay Game");
		replayGameOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileMenu.add(replayGameOption);
		
		//define the action for this option
		replayGameOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//insert action here
				if(Main.currentGameMode==0)
				{
					Main.replayGame();
					Main.numberOfShufflesPerformed=0;
					gameManager.statusBarLabel2.setText("					Number of Shuffles Performed: "+ Main.getNumberOfShufflesPerformed());
				}
				else //clock
				{
					guiInterface.tempSaveCardSlotPiles();
					//Main.replayGame();
					Main.loadCardDeck(guiInterface.getCardDeck());
					
					
				}
			}
		});
		
		//add the dropdown shuffle option for File dropdown tab
		JMenuItem shuffleOption= new JMenuItem("Shuffle");
		fileMenu.add(shuffleOption);
		if(Main.currentGameMode==1)
		{
			shuffleOption.setEnabled(false);
		}
		else
		{
			shuffleOption.setEnabled(true);
		}
		
		//define action for this option
		shuffleOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//insert action here
				if(Main.getNumberOfShufflesPerformed()==2)
				{
					JOptionPane.showMessageDialog(gameManager.currentJFrame, "You have reached your maximum allowed shuffles. \n"
							+"you cannot shuffle again.", "Exceeded Maximum Allowed Shuffles",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					cardObject[] gameManagersCurrentCardDeck= Main.copyCardDeckArray(guiInterface.getCardDeck());
					//undo_redo_object temp= new undo_redo_object(1,0,0,0,0);
					undo_redo_object temp= new undo_redo_object(1,Main.copyCardDeckArray(gameManagersCurrentCardDeck));
					System.out.println("new undo object made for the shuffle");
					if(Main.getNumberOfShufflesPerformed() ==0)
					{
						Main.setCardDeckBeforeFirstShuffle(Main.copyCardDeckArray(gameManagersCurrentCardDeck));
						System.out.println("^^^^^^^^^^^deck before first shuffle set^^^^^^^^^^^");
						
					}
					else//if #shuffles ==1
					{
						Main.setCardDeckBeforeSecondShuffle(Main.copyCardDeckArray(gameManagersCurrentCardDeck));
					}
					Main.UndoStack.push(temp);
					Main.shuffleExistingCardDeck(gameManagersCurrentCardDeck);
					if(Main.getNumberOfShufflesPerformed()==0)
					{
						Main.setCardDeckAfterFirstShuffle(Main.copyCardDeckArray(gameManagersCurrentCardDeck));	
					}
					else if(Main.getNumberOfShufflesPerformed()==1)
					{
						Main.setCardDeckAfterSecondShuffle(Main.copyCardDeckArray(gameManagersCurrentCardDeck));
					}
					else
					{
						System.out.println("ERROR: invalid number of shuffles in the shufle function");
					}
					Main.numberOfShufflesPerformed++;
					gameManager.statusBarLabel2.setText("					Number of Shuffles Performed: "+ Main.getNumberOfShufflesPerformed());
				}
				
			}
		});
		
		//add the dropdown Save as option for File dropdown tab
		JMenuItem saveAsOption= new JMenuItem("Save As...");
		saveAsOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileMenu.add(saveAsOption);
		
		//define action for this option
		saveAsOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//insert action here
				if(gameManager.wasCheatingEnabled == true)
				{
					JOptionPane.showMessageDialog(gameManager.currentJFrame, "Cheating was enabled during this game. \n" + "This game cannot be saved", "Saving Error: Cheating was enabled", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(Main.currentGameMode==1)
					{
						guiInterface.tempSaveCardSlotPiles();
					}
					
					//creates the writer for xml files
					xmlCreator createXml= new xmlCreator();
					cardObject[] inputCardDeck= new cardObject[52];
					inputCardDeck= Main.copyCardDeckArray(guiInterface.getCardDeck());
					createXml.createXML(inputCardDeck);
					System.out.println("a new xml craetor was made!");
				}
				
			}
		});
		
		
		//add the dropdown quiz option for File dropdown tab
		JMenuItem quitOption= new JMenuItem("Quit");
		fileMenu.add(quitOption);
		
		//define action for this option
		quitOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(JOptionPane.showConfirmDialog(null, "Do you want to save before you Quit the Game? \n", "Save Game?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
				{

					if(gameManager.wasCheatingEnabled == true)
					{
						JOptionPane.showMessageDialog(gameManager.currentJFrame, "Cheating was enabled during this game. \n" + "This game cannot be saved", "Saving Error: Cheating was enabled", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(Main.currentGameMode==1)
						{
							guiInterface.tempSaveCardSlotPiles();
						}
						
						//creates the writer for xml files
						xmlCreator createXml= new xmlCreator();
						cardObject[] inputCardDeck= new cardObject[52];
						inputCardDeck= Main.copyCardDeckArray(guiInterface.getCardDeck());
						createXml.createXML(inputCardDeck);
						System.out.println("a new xml craetor was made!");
					}
				}
				System.exit(0); //exits the program
			}
		});
		
		//add the Edit dropdown menu to the menubar*********************
		JMenu editMenu= new JMenu("Edit");
		inputMenuBar.add(editMenu);
		
		//add the dropdown undo option for Edit dropdown tab
		JMenuItem undoOption= new JMenuItem("Undo");
		undoOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		editMenu.add(undoOption);
		
		//define action for this option
		undoOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//is the stack empty??????
				if(!Main.UndoStack.isEmpty())//if not empty
				{
					if(Main.firstUndo == true)
					{
						//pop the empty value on the top os stack
						Main.UndoStack.pop();
						Main.firstUndo= false;
					}
					undo_redo_object poppedObject= Main.copyUndoRedoObject(Main.UndoStack.pop());
					System.out.println("undo_redo_object removed from undo stack");
					//add the undid object to the redo stack
					Main.RedoStack.push(poppedObject);//place undid object into the redo stack
					//what type of modifier is it?
					if(poppedObject.getModifyType() == 0) //single card changed
					{
						System.out.println("undo_redo_object removed from undo stack; TYPE: 0");
						if(poppedObject.getCardDeckObject() != null)
						{
							if(Main.currentGameMode==0)
							{
								cardObject[] currentCardDeck= Main.copyCardDeckArray(poppedObject.getCardDeckObject());
								Main.loadCardDeck(currentCardDeck);
							}
							else //if 1
							{
								cardObject[] currentCardDeck= Main.copyCardDeckArray(poppedObject.getCardDeckObject());
								Main.loadCardDeck(currentCardDeck);
								guiInterface.reloadCardSlotPiles();
								Main.currentGameManager.currentGuiInterface.repaint();
							}
							
						}
						else
						{
							System.out.println("No cardDeckObject is in the poppedObject");
						}
					}//end of if type ==0
					else if(poppedObject.getModifyType() == 1) //card deck was shuffled
					{
						//how many shuffles have been performed? 
						if(Main.getNumberOfShufflesPerformed()==0) //if no shuffles performed, set starting deck
						{
							cardObject[] theStartingCardDeck= Main.copyCardDeckArray(Main.getStartingCardDeck());
							Main.loadCardDeck(Main.copyCardDeckArray(theStartingCardDeck)); //load using starting card deck
						}//end of if #shufffles==0
						else if(Main.getNumberOfShufflesPerformed()==1)
						{
							cardObject[] deckB4FirstShuffle= Main.copyCardDeckArray(Main.getCardDeckBeforeFirstShuffle());
							Main.loadCardDeck(Main.copyCardDeckArray(deckB4FirstShuffle));
							Main.numberOfShufflesPerformed--;
							gameManager.statusBarLabel2.setText("					Number of Shuffles Performed: "+ Main.getNumberOfShufflesPerformed());
						}//end of else if #shuffles==1
						else //if 2 shuffles have been performed
						{
							cardObject[] deckB4SecondShuffle= Main.copyCardDeckArray(Main.getCardDeckBeforeSecondShuffle());
							Main.loadCardDeck(Main.copyCardDeckArray(deckB4SecondShuffle));
							Main.numberOfShufflesPerformed--;
							gameManager.statusBarLabel2.setText("					Number of Shuffles Performed: "+ Main.getNumberOfShufflesPerformed());
						}
					}//end of else for card deck was shuffled undo
					else if(poppedObject.getModifyType() == 2) //if undoing to startingDeck state
					{
						Main.replayGame();//restarts the game back to original positions
						System.out.println("type 2 modifyType was found and used");
					}//end of if modifytype==2
					else
					{
						System.out.println("ERROR: invalid modifyType, TYPE:" + poppedObject.getModifyType());
					}
				}//end if undoStack is not empty
				else //if the undo stack is empty
				{
					//do nothing
					System.out.println("NOTE: The undo stack is empty");
				}
			}
		});
		
		//add the dropdown redo option for Edit dropdown tab
		JMenuItem redoOption= new JMenuItem("Redo");
		redoOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		editMenu.add(redoOption);
		
		//define action for this option
		redoOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//insert action here
				if(!Main.RedoStack.isEmpty())
				{
					if(Main.firstRedo == true)
					{
						//remove the top blank item on the stack
						Main.RedoStack.pop();
						Main.firstRedo= false;
					}
					undo_redo_object poppedObject= Main.copyUndoRedoObject(Main.RedoStack.pop());
					//adding the redid object to the undo stack
					Main.UndoStack.push(poppedObject); //add the redid object to the undo stack
					//what type of modifier is it?
					if(poppedObject.getModifyType() == 0)//changed single card
					{
						cardObject[] poppedCardDeck= Main.copyCardDeckArray(poppedObject.getCardDeckObject());
						Main.loadCardDeck(poppedCardDeck);
					}//end if modifytype==0
					else if(poppedObject.getModifyType() == 1)//shuffled deck
					{
						if(Main.getNumberOfShufflesPerformed() == 0)
						{
							cardObject[] redoCardDeck= Main.copyCardDeckArray(Main.getCardDeckAfterFirstShuffle());
							Main.numberOfShufflesPerformed++;
							gameManager.statusBarLabel2.setText("					Number of Shuffles Performed: "+ Main.getNumberOfShufflesPerformed());
							//shuffle the card deck again
							Main.shuffleExistingCardDeck(Main.copyCardDeckArray(redoCardDeck));
							Main.loadCardDeck(Main.copyCardDeckArray(redoCardDeck));
						}
						else if(Main.getNumberOfShufflesPerformed()==1)
						{
							cardObject[] redoCardDeck= Main.copyCardDeckArray(Main.getCardDeckAfterSecondShuffle());
							Main.numberOfShufflesPerformed++;
							gameManager.statusBarLabel2.setText("					Number of Shuffles Performed: "+ Main.getNumberOfShufflesPerformed());
							//shuffle the card deck again
							Main.shuffleExistingCardDeck(Main.copyCardDeckArray(redoCardDeck));
							Main.loadCardDeck(Main.copyCardDeckArray(redoCardDeck));
						}
						else 
						{
							//should not be possible
							System.out.println("ERROR: invalid value for numberOfShufflesPerformed in redo");
						} 
					}//end of else if modifytype==1
					else if(poppedObject.getModifyType()==2)//redo the undo back to the startingDeck position
					{
						cardObject[] poppedCardDeck= Main.copyCardDeckArray(poppedObject.getCardDeckObject());
						Main.loadCardDeck(poppedCardDeck);
					}//end of if modifyType==2
					else
					{
						System.out.println("ERROR: Invalid modifyType in the redo function. Type: " + poppedObject.getModifyType());
					}
				}//end of if rdostack is not empty
				else
				{
					System.out.println("ERROR: the RedoStack is empty. ");
				}
			}
		});
		
		//add the dropdown statistics option for Edit dropdown tab
		JMenuItem statsOption= new JMenuItem("Statistics");
		editMenu.add(statsOption);
		
		//define action for this option
		statsOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//insert action here
				if(Main.currentGameMode==0)
				{
					int response= JOptionPane.showConfirmDialog(null, "Number of Games Played: " + Main.getNumberOfGamesPlayed() + "\n" 
							+ "Number of Games Won: " + Main.getNumberOfGamesWon() + "\n"
							+ "Number of Games were Cheating was Enabled: " + Main.getNumberOfGamesCheatingWasEnabled() + " \n"
							+ "Percentage of Games Won: " + (Main.getPercentageOfGamesWon())*100 + "% \n"
							+ "Do you want to clear these stats?", "Game Statistics", JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.YES_OPTION)
					{
						Main.clearGameStats();
					}
					else
					{
						//do nothing!
					}
				}
				else //if ==1
				{
					int response= JOptionPane.showConfirmDialog(null, "Number of Games Played: " + Main.getNumberofClockGamesPlayed() + "\n" 
							+ "Number of Clock Solitaire Games Won: " + Main.getNumberOfClockGamesWon() + "\n"
							+ "Do you want to clear these stats?", "Game Statistics", JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.YES_OPTION)
					{
						Main.clearGameStats();
					}
					else
					{
						//do nothing!
					}
				}
				
			}//end of action listener
		});
		
		//add the dropdown for illegalCardPlacements in edit menu
		JMenuItem allowCheatingOption= new JMenuItem("Allow/Disallow Illegal Card Placements");
		editMenu.add(allowCheatingOption);
		if(Main.currentGameMode==1)
		{
			allowCheatingOption.setEnabled(false);
		}
		else
		{
			allowCheatingOption.setEnabled(true);
		}
		//define action for this option
		allowCheatingOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//insert action here
				if(Main.getAllowIllegalCardPlacementValue() == false)
				{
					int response= JOptionPane.showConfirmDialog(null, "If you enable cheating, you will not be allowed to save the game. \n" 
							+ "Do you still want to enable cheating?", "Are You Sure You Want Enable Cheating?", JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.YES_OPTION)
					{
						Main.setAllowIllegalCardPlacementValue(true);
						if(gameManager.wasCheatingEnabled == false)
						{
							gameManager.wasCheatingEnabled= true;
							Main.numberOfGamesCheatingWasEnabled++;
						}
						gameManager.statusBarLabel.setText("Currently Cheating is Enabled");
					}
					else 
					{
						//do nothing
					}
					
				}
				else
				{
					Main.setAllowIllegalCardPlacementValue(false);
					gameManager.statusBarLabel.setText("Currently Cheating is Disabled");
				}
				
				
			}
		});
		//add the Game dropdown menu to the menubar*********************
				JMenu gameMenu= new JMenu("Game");
				inputMenuBar.add(gameMenu);
				
		//add the dropdown Carpet Solitaire
				JMenuItem carpetSolitaireOption= new JMenuItem("Carpet Solitaire");
				gameMenu.add(carpetSolitaireOption);
				
		//define action for this option
				carpetSolitaireOption.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//insert action here
						if(Main.firstGameManagerLaunch==true)//if first time
						{
							Main.replaceGameManager(0);
							Main.currentGameMode=0;
							Main.firstGameManagerLaunch=false; //set to false
						}
						else
						{
							//System.out.println("Not firstGameManagerLaunch, insert option to switch game here");
							if(Main.currentGameMode==0)
							{
								System.out.println("You are already in Carpet Solitaire Mode!");
							}
							else
							{
								if(JOptionPane.showConfirmDialog(null, "Are you sure you want to switch Game Modes? \n" +"(You can save on next window)" , "Start New Game?",
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
								{
									if(JOptionPane.showConfirmDialog(null, "Would you like to save your game before switching? \n" , "Save Game?",
											JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
									{
										if(gameManager.wasCheatingEnabled == true)
										{
											JOptionPane.showMessageDialog(gameManager.currentJFrame, "Cheating was enabled during this game. \n" + "This game cannot be saved", "Saving Error: Cheating was enabled", JOptionPane.ERROR_MESSAGE);
										}
										else
										{
											if(Main.currentGameMode==1)
											{
												guiInterface.tempSaveCardSlotPiles();
											}
											
											//creates the writer for xml files
											xmlCreator createXml= new xmlCreator();
											cardObject[] inputCardDeck= new cardObject[52];
											inputCardDeck= Main.copyCardDeckArray(guiInterface.getCardDeck());
											createXml.createXML(inputCardDeck);
											System.out.println("a new xml craetor was made!");
										}
									}//end of save operation
									System.out.println("Entering Carpet Solitaire Mode");
									Main.replaceGameManager(0);
									Main.currentGameMode=0;
								}
								
							}
						}
					}
				});
				
		//add the dropdown Clock Solitaire
				JMenuItem clockSolitaireOption= new JMenuItem("Clock Solitaire");
				gameMenu.add(clockSolitaireOption);
				
		//define action for this option
				clockSolitaireOption.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//insert action here
						if(Main.firstGameManagerLaunch==true)//if first time
						{
							Main.replaceGameManager(1);
							Main.currentGameMode=1;
							Main.firstGameManagerLaunch= false;
						}
						else
						{
							//System.out.println("Not firstGameManagerLaunch, insert option to switch game here");
							if(Main.currentGameMode==1)
							{
								System.out.println("You already are in Clock Solitaire Mode!");
							}
							else
							{
								if(JOptionPane.showConfirmDialog(null, "Are you sure you want to switch Game Modes? \n" +"(You can svae on next window)", "Start New Game?",
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
								{
									if(JOptionPane.showConfirmDialog(null, "Do you want to save before you switch Game Modes? \n", "Save Game?",
											JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
									{

										if(gameManager.wasCheatingEnabled == true)
										{
											JOptionPane.showMessageDialog(gameManager.currentJFrame, "Cheating was enabled during this game. \n" + "This game cannot be saved", "Saving Error: Cheating was enabled", JOptionPane.ERROR_MESSAGE);
										}
										else
										{
											if(Main.currentGameMode==1)
											{
												guiInterface.tempSaveCardSlotPiles();
											}
											
											//creates the writer for xml files
											xmlCreator createXml= new xmlCreator();
											cardObject[] inputCardDeck= new cardObject[52];
											inputCardDeck= Main.copyCardDeckArray(guiInterface.getCardDeck());
											createXml.createXML(inputCardDeck);
											System.out.println("a new xml craetor was made!");
										}
									}
									System.out.println("Entering Clock Solitaire Mode");
									Main.replaceGameManager(1);
									Main.currentGameMode=1;	
								}
								
								
							}//end of else
						}
					}
				});
		//add the Help dropdown menu to the menubar*********************
				JMenu helpMenu= new JMenu("Help");
				inputMenuBar.add(helpMenu);
				
		//add the dropdown rules option for help dropdown tab
				JMenuItem rulesOption= new JMenuItem("Game Rules");
				helpMenu.add(rulesOption);
				
		//define action for this option
		rulesOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//insert action here
				if(Main.currentGameMode==0)
				{
					JOptionPane.showMessageDialog(gameManager.currentJFrame, "1. You can only place an Ace in the leftmost column \n" 
							+ "2. No Card can be placed to the right of a King \n"
							+ "3. You only can place a card in an unoccupied spot \n"
							+ "4. You cannot place a card in a space that is to the right of an empty space \n"
							+ "5. You can only place a card in a space if the card to the left has the same suit as your selected card \n"
							+ "6. You can only place a card in a space if the card to the left has a card rank that is one lower than your card rank \n"
							+ "  \n" 
							+ "NOTE: If you enable cheating (Edit -> Allow Illegal Card Placements), you can violate these rules  \n"
							+ "CAUTION: If you enable cheating during a game, you will not be allowed to save the game \n"
							, "The Rules", JOptionPane.PLAIN_MESSAGE);
				}
				else //clock solitaire
				{
					JOptionPane.showMessageDialog(gameManager.currentJFrame, 
					"1. To start, click on the center pile of cards to flip the first card. \n" 
					+"2. Click on the card that flipped up in the middle to move it the appropriate pile. \n"
					+ "3. repeat step until you can no longer move any of the cards or you have all cards facing up (winning condition)", "The Rules",JOptionPane.PLAIN_MESSAGE);
				}
				
			
			}
		});
		
		//add the dropdown about option to the help tab
		JMenuItem aboutOption= new JMenuItem("About");
		helpMenu.add(aboutOption);
		
		//define action for this option
		aboutOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//insert action here
				JOptionPane.showMessageDialog(gameManager.currentJFrame, " Author: Christopher Hamm  \n"
						+ "Version: 5F \n" + "Contact info: email: catchumfishnx01@yahoo.com", "About this Game"
						, JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		return inputMenuBar;
	}
}
