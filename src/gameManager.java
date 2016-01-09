//christopher hamm
//project 5
//cosc 3011
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class gameManager 
{
	static cardObject[] currentCardDeck= null; //the current cardDeck being used
	static JFrame currentJFrame= null; //the current JFrame the game is using
	static JPanel currentJPanel= null; //the current JPanel the game is using
	static JPanel statusBarPanel= null;
	static JLabel statusBarLabel= new JLabel("Currently Cheating is Disabled");
	static JLabel statusBarLabel2= new JLabel("					Number of Shuffles Performed: 0");
	static JLabel statusBarLabel3= new JLabel("   Game Mode: None");
	static xmlReader currentXMLReader= null; //the current xmlReader object
	static xmlCreator currentXMLCreator= null; //the current xmlCreator object
	static guiInterface currentGuiInterface= null; //the current guiInterface object being used
	static guiInterface currentGuiInterface2 = null; //used for clock solitaire
	static JMenuBar currentJMenuBar= null; //the current menuBar being used
	static MainMenuBar currentMainMenuBar= null; //the current Main menu bar being used
	static boolean wasCheatingEnabled = false;
	static int gameSelectValue;
//gameSelect -1=first launch, await input 0= carpet solitaire, 1= clock solitaire
	
	public gameManager(cardObject[] tempDeck, boolean theFirstPaintValue, int gameSelect)
	{
		//set gameSelectValue
		gameSelectValue= gameSelect;
		Main.currentGameMode= gameSelectValue;
		//generate new cardDeck
		currentCardDeck= Main.copyCardDeckArray(tempDeck);
		//make new guiInterface
		currentGuiInterface= new guiInterface(currentCardDeck, theFirstPaintValue);
		//make new guiInterface2
		currentGuiInterface2= new guiInterface(currentCardDeck, theFirstPaintValue);
		//make new JFrame
		currentJFrame= new JFrame();
		//make new JPanel
		currentJPanel= new JPanel();
		//make new statusbar
		statusBarPanel= new JPanel();
		statusBarPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusBarPanel.setMinimumSize(new Dimension(currentJFrame.getWidth(), 16));
		statusBarPanel.setLayout(new BoxLayout(statusBarPanel, BoxLayout.X_AXIS));
		statusBarLabel.setHorizontalAlignment(SwingConstants.LEFT);
		//if in carpet Solitaire mode
		if(gameSelectValue==0)//if carpet solitaire
		{
			statusBarPanel.add(statusBarLabel);
			statusBarPanel.add(statusBarLabel2);
			statusBarPanel.add(statusBarLabel3);
			statusBarLabel3.setText("   Game Mode: Carpet Solitaire");
		}
		else if(gameSelectValue==1)
		{
			statusBarPanel.add(statusBarLabel3);
			statusBarLabel3.setText("   Game Mode: Clock Solitaire");
		}
		else
		{
			statusBarPanel.add(statusBarLabel3);
		}
		//make new JMenuBar
		currentJMenuBar= new JMenuBar();
		//make new MainMenuBar
		currentMainMenuBar= new MainMenuBar(currentJMenuBar);
		//set JMenuBar
		currentJFrame.setJMenuBar(currentJMenuBar);
		//set MainMenuBar
		currentJFrame.setContentPane(currentMainMenuBar);
		/*//check to see if it is the first launch
		if(gameSelectValue== -1)//if first launch
		{
			JOptionPane.showMessageDialog(currentJFrame,"Please select a Game Mode  \n" + " from the MenuBar Above");
		} */
		//add JPanel to JFrame---------
		if(gameSelectValue==0)//carpet solitaire
		{
			currentJFrame.add(currentJPanel);
		}
		else if(gameSelectValue==1)//clock solitaire
		{
			//insert clock solitaire JFrame adding JPanel here
			currentJFrame.add(currentJPanel);
			Main.numberClockGamesPlayed++;
		}
		else if(gameSelectValue==-1)//if first launch
		{
			System.out.println("First time gameManager launch value used");
		}
		else
		{
			System.out.println("ERROR: invalid gameSelectValue!");
		}
		currentJFrame.add(statusBarPanel, BorderLayout.SOUTH);
		//add guiInterface to JFrame---------------
		if(gameSelectValue==0)//carpet solitaire
		{
			currentJFrame.add(currentGuiInterface);
		}
		else if(gameSelectValue==1)//clock solitaire
		{
			//insert clock solitaire piece here
			currentJFrame.add(currentGuiInterface2);
		}
		else if(gameSelectValue==-1)
		{
			System.out.println("First time gameManager launch value used");
		}
		else
		{
			System.out.println("ERROR: invalid gameSelectValue !!!!");
		}
		wasCheatingEnabled= false;
		//set background color for guiInterface
		currentGuiInterface.setBackground(new Color(0x64, 0xc8, 0x66)); 
		currentGuiInterface2.setBackground(new Color(0x64, 0xc8, 0x66)); 
		//set default resolution for JFrame
		if(gameSelectValue==0)//carpet solitaire
		{
			currentJFrame.setMinimumSize(new Dimension(1150, 500));
		}
		else if(gameSelectValue==1)//clock solitaire
		{
			currentJFrame.setMinimumSize(new Dimension(1150, 820));
		}
		else
		{
			currentJFrame.setMinimumSize(new Dimension(1150, 500));
		}
		currentJFrame.pack();
		currentJFrame.setVisible(true);
		currentJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets the x
																		// button to
																		// application
																		// when
																		// pressed
	}//end of gameManager constructor
	

	
	public void clearJFrame()
	{
		currentCardDeck= null;
		currentJFrame.getContentPane().removeAll();
		currentJPanel.remove(currentGuiInterface);
		currentJPanel.remove(currentGuiInterface2);
		currentJFrame.remove(currentJPanel);
		currentJFrame.setVisible(false);
		currentJFrame.removeAll();
	}
	
	
	
}//end of gameManager class


