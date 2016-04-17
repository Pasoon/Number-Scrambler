import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.Collections;
import java.util.ArrayList;
public class PuzzleGame extends JFrame implements ActionListener {

	private int movesCounter = 0;//Keeps track of how many moves user made.
	private JButton[] buttons = new JButton[16];//JButton array
	private JLabel movesDisplay;//What will display the amount of moves
	private JButton resetButton, menuButton;//the corresponding menu and reset buttons
    public String x = "";//What will be used to store the text to compare strings

	public PuzzleGame()
	{
		super("ICS4U - Pasoon Azimi");//Creation of JFrame
		setSize(425,425);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Font f = new Font("IMPACT", Font.PLAIN, 26);
		Font g = new Font("IMPACT", Font.PLAIN, 16);
		
		resetButton = new JButton("Reset Game");//Creation and Characteristics of resetButton
		resetButton.setFont(g);
		resetButton.addActionListener(this);
		resetButton.setBackground(Color.WHITE);
		resetButton.setForeground(Color.BLACK);
		
		menuButton = new JButton("Menu");//Creation and characterisitcs of MenuButton
		menuButton.setFont(g);
		menuButton.addActionListener(this);
		menuButton.setBackground(Color.WHITE);
		menuButton.setForeground(Color.BLACK);
		
		movesDisplay = new JLabel("Moves: " + movesCounter, JLabel.CENTER);//Creation and characteristics of Moves Label
		movesDisplay.setFont(g);
		movesDisplay.setForeground(Color.WHITE);
		
		JPanel PuzzleGamePanel = new JPanel(); //JPanel that will store the buttons
		PuzzleGamePanel.setBorder(new EmptyBorder(5,5,5,5));
		PuzzleGamePanel.setLayout(new GridLayout(4,4,5,5));;
		PuzzleGamePanel.setBackground(Color.BLACK);

		JPanel scorePanel = new JPanel();//This panel which will be at the bottom of the gameboard will have the menuButton, resetButton and the moves counter.
		scorePanel.setLayout(new GridLayout(1,3, 5, 5));
		scorePanel.add(movesDisplay);
        scorePanel.add(menuButton);
		scorePanel.add(resetButton);
		scorePanel.setBackground(Color.BLACK);
		
		JPanel panel = new JPanel(); //This panel will hold the scorePanel and PuzzleGamePanel, making up the entire JFrame
		panel.setLayout(new BorderLayout());
		panel.add(PuzzleGamePanel, BorderLayout.CENTER);
		panel.add(scorePanel, BorderLayout.PAGE_END);
		panel.setBackground(Color.BLACK);
		
		for (int i = 0; i < buttons.length; i++)//For loop used to initialize the JButton array
		{		
			
			buttons[i] = new JButton("" + (i));//Characteristics of each button
			buttons[i].setForeground(Color.BLACK);
			buttons[i].setFont(f);
			buttons[i].setBackground(Color.WHITE);
			
			buttons[i].addActionListener(this);
			PuzzleGamePanel.add(buttons[i]);//This is where the buttons are added to the puzzlegamepanel
		}
		
		
		buttons = RandomizeBoard(buttons);//Sends the button array to the RandomizeBoard Class where the JButtons will be randomized and returned
	    
		setVisible(true);//Sets the JFrame to visible
		setLocationRelativeTo(null);//Middle of the Screen
		add(panel);//adds the panel
        
	}
	
    public static void main(String[] args) {
		  new PuzzleGame();
		} 
    
    public JButton[] RandomizeBoard(JButton[] buttons){//Method used to Randomize the Puzzle board
    	
    	int rarr[] = new int [16];
   
        ArrayList<Integer> arr = new ArrayList<Integer>();//using an arraylist we shuffle an array of 16 integers
		 for (int i=0; i<16; i++) {//for loop used to initialize the array
			     arr.add(new Integer(i+1));
			 }
			   Collections.shuffle(arr);//shuffle the array
		for (int i=0; i<16; i++) {//This loop is used to store the values of the shuffled array into another integer array
				  rarr[i] = arr.get(i);
				}
		 
		for(int i = 0; i<rarr.length; i++){
			   if(rarr[i]==16){//This is used to set the number 16 to x and setting the 15th index to this number. This makes sure that the button that is not visible is the button in the bottom right corner everytime.
				int x = rarr[i];
				rarr[i] = rarr[15];
				rarr[15] = x;
				 }
			   
		   }
			 
		for(int i = 0; i < buttons.length; i++){//this loop is used to set the Text of the button array to the previously shuffled array
			 
			 buttons[i].setText("" + (rarr[i]));
		 }
		    buttons[15].setText("X");//Sets the text of the button at the 15th index to X
			buttons[15].setVisible(false);//Also sets its visibility to False
	      return buttons; //return the new buttons array.
		}
	
	public boolean isValidMove(int i){//This method is used to check if the button clicked by the user is a valid move.
		boolean move = false;
		
		if(i==0||i==4||i==8||i==12){// CHECK BUTTONS ON FAR LEFT COLUMN
			if(i==0){//CHECK BUTTON IN TOP LEFT CORNER
				if((buttons[i+4].getText()=="X")){//Check button below
					move = true;
				}
				
				if((buttons[i+1].getText()=="X")){//Check button to the right
					move = true;
				}
				
			}
			
		if(i==12){//CHECK BUTTON IN BOTTOM LEFT CORNER
			if((buttons[i-4].getText()=="X")){//Check button above
				move = true;
			}
			
			if((buttons[i+1].getText()=="X")){//Check button to the right
				move = true;
			}
			
		}
		
		if(i==8||i==4){//CHECK OTHER BUTTONS
			
			if((buttons[i+1].getText()=="X")){//Check button to the right
				move = true;
			}
			
			if((buttons[i+4].getText()=="X")){//Check button below
				move = true;
			}
			
			if((buttons[i-4].getText()=="X")){//Check button above
				move = true;
			}
		}
		}
		////////////////////////////////////////////////////////////////////////////////////////////
		//CHECK BUTTONS ON FAR RIGHT COLUMN
		if(i==3||i==7||i==11||i==15){
			if(i==3){//CHECK BUTTON IN TOP RIGHT CORNER
				if((buttons[i-1].getText()=="X")){//Check button to the left
					move = true;
			}
				if((buttons[i+4].getText()=="X")){//Check button below
					move = true;
				}
			}
			
			if(i==15){//CHECK BUTTON IN BOTTOM RIGHT CORNER
				if((buttons[i-1].getText()=="X")){//Check button to the left
					move = true;
				}
				if((buttons[i-4].getText()=="X")){//Check button above
					move = true;
				}
			}
			if(i==7||i==11){//CHECK REST OF BUTTONS
			if((buttons[i-1].getText()=="X")){//Check button to the left
				move = true;
		    }
			
			if((buttons[i-4].getText()=="X")){//Check button above
				move = true;
			}
			
			if((buttons[i+4].getText()=="X")){//Check button below
				move = true;
			}
			}
		}
		////////////////////////////////////////////////////////////////////////////////////
		//CHECK BUTTONS ON BOTTOM ROW
		if(i>11&&i<15){
		if((buttons[i+1].getText()=="X")){//Check button to the right
			move = true;
		}	
		if((buttons[i-1].getText()=="X")){//Check button to the left
			move = true;
		}		
		if((buttons[i-4].getText()=="X")){//Check button above
			move = true;
		}
		}
		////////////////////////////////////////////////////////////////////////////////////////////
		//CHECK BUTTONS ON TOP ROW
		if(i>0&&i<3){
			if((buttons[i+1].getText()=="X")){//Check button to the right
				move = true;
			}	
			if((buttons[i-1].getText()=="X")){//Check button to the left
				move = true;
			}		
			if((buttons[i+4].getText()=="X")){//Check button below
				move = true;
				
			}
			}
   ////////////////////////////////////////////////////////////////////
	if(i==5||i==6||i==9||i==10){//CHECK ANY OTHER BUTTON
	
		if((buttons[i+1].getText()=="X")){//Check button to the right
			move = true;
		}
		
		if((buttons[i-1].getText()=="X")){//Check button to the left
			move = true;
		}
		
		if((buttons[i+4].getText()=="X")){//Check button below
			move = true;
		}
		
		if((buttons[i-4].getText()=="X")){//Check button above
			move = true;
		}
		
      }
		return move;//return the true or false value of move
	}
	
	public void SwitchButtons(int i){//This method is used to do the action of switching buttons.
		if(i==0||i==4||i==8||i==12){
			if(i==0){//CHECK BUTTON IN TOP LEFT CORNER
				if((buttons[i+4].getText()=="X")){//Check button below
					x = buttons[i].getText();//Store the text of the button[i] in String value x
					buttons[i].setText("X");//Set button[i] to X
					buttons[i].setVisible(false);//Also set its visibility to false
					buttons[i+4].setVisible(true);//Set button thats being switched to true visiblity
					buttons[i+4].setText(x);//Set the text of button[i] to that of the new button
					//THIS IS DONE FOR ALL POSSIBLE MOVES, TO AVOID REDUNDANT COMMENTS I ONLY DID FOR THE ABOVE SET^.
				//Main purpose is to compare two buttons and then switch the text of the two buttons and set the one you clicked on to invisible while setting its text equal to X. 
					//The X value is what the program checks in order to see if its a valid move. 
				}
				
				
				if((buttons[i+1].getText()=="X")){//Check button to the right
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i+1].setVisible(true);
					buttons[i+1].setText(x);
				}
				
			}
			
			if(i==4){
				if((buttons[i+4].getText()=="X")){//Check button below
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i+4].setVisible(true);
					buttons[i+4].setText(x);
				}
				
				if((buttons[i+1].getText()=="X")){//Check button to the right
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i+1].setVisible(true);
					buttons[i+1].setText(x);
				}
				
				if((buttons[i-4].getText()=="X")){//Check button above
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i-4].setVisible(true);
					buttons[i-4].setText(x);
				}
				
			}
			
			if(i==8){
				if((buttons[i+4].getText()=="X")){//Check button below
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i+4].setVisible(true);
					buttons[i+4].setText(x);
				}
				
				if((buttons[i+1].getText()=="X")){//Check button to the right
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i+1].setVisible(true);
					buttons[i+1].setText(x);
				}
				
				if((buttons[i-4].getText()=="X")){//Check button above
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i-4].setVisible(true);
					buttons[i-4].setText(x);
				}
				
			}
			
		if(i==12){//CHECK BUTTON IN BOTTOM LEFT CORNER
			if((buttons[i-4].getText()=="X")){//Check button above
				x = buttons[i].getText();
				buttons[i].setText("X");
				buttons[i].setVisible(false);
				buttons[i-4].setVisible(true);
				buttons[i-4].setText(x);
			}
			
			if((buttons[i+1].getText()=="X")){//Check button to the right
				x = buttons[i].getText();
				buttons[i].setText("X");
				buttons[i].setVisible(false);
				buttons[i+1].setVisible(true);
				buttons[i+1].setText(x);
			}
			
		}
		
		}
		////////////////////////////////////////////////////////////////////////////////////////////
		//CHECK BUTTONS ON FAR RIGHT COLUMN
		if(i==3||i==7||i==11||i==15){
			if(i==3){//CHECK BUTTON IN TOP RIGHT CORNER
				if((buttons[i-1].getText()=="X")){//Check button to the left
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i-1].setVisible(true);
					buttons[i-1].setText(x);
			}
				if((buttons[i+4].getText()=="X")){//Check button below
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i+4].setVisible(true);
					buttons[i+4].setText(x);
				}
			}
			
			if(i==15){//CHECK BUTTON IN BOTTOM RIGHT CORNER
				if((buttons[i-1].getText()=="X")){//Check button to the left
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i-1].setVisible(true);
					buttons[i-1].setText(x);
			}
				if((buttons[i-4].getText()=="X")){//Check button above
					x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i-4].setVisible(true);
					buttons[i-4].setText(x);
				}
			}
			if(i==7||i==11){//CHECK REST OF BUTTONS
			if((buttons[i-1].getText()=="X")){//Check button to the left
				x = buttons[i].getText();
				buttons[i].setText("X");
				buttons[i].setVisible(false);
				buttons[i-1].setVisible(true);
				buttons[i-1].setText(x);
		    }
			
			if((buttons[i-4].getText()=="X")){//Check button above
				x = buttons[i].getText();
				buttons[i].setText("X");
				buttons[i].setVisible(false);
				buttons[i-4].setVisible(true);
				buttons[i-4].setText(x);
			}
			
			if((buttons[i+4].getText()=="X")){//Check button below
				x = buttons[i].getText();
				buttons[i].setText("X");
				buttons[i].setVisible(false);
				buttons[i+4].setVisible(true);
				buttons[i+4].setText(x);
			}
			}
		}
		////////////////////////////////////////////////////////////////////////////////////
		//CHECK BUTTONS ON BOTTOM ROW
		if(i>11&&i<15){
		if((buttons[i+1].getText()=="X")){//Check button to the right
			    x = buttons[i].getText();
			    buttons[i].setText("X");
				buttons[i].setVisible(false);
				buttons[i+1].setVisible(true);
				buttons[i+1].setText(x);
		}	
		if((buttons[i-1].getText()=="X")){//Check button to the left
			    x = buttons[i].getText();
				buttons[i].setText("X");
				buttons[i].setVisible(false);
				buttons[i-1].setVisible(true);
				buttons[i-1].setText(x);
		}		
		if((buttons[i-4].getText()=="X")){//Check button above
			    x = buttons[i].getText();
				buttons[i].setText("X");
				buttons[i].setVisible(false);
				buttons[i-4].setVisible(true);
				buttons[i-4].setText(x);
			
		}
		}
		////////////////////////////////////////////////////////////////////////////////////////////
		//CHECK BUTTONS ON TOP ROW
		if(i>0&&i<3){
			if((buttons[i+1].getText()=="X")){//Check button to the right
				    x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i+1].setVisible(true);
					buttons[i+1].setText(x);
			}	
			if((buttons[i-1].getText()=="X")){//Check button to the left
				    x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i-1].setVisible(true);
					buttons[i-1].setText(x);
			}		
			if((buttons[i+4].getText()=="X")){//Check button below
				    x = buttons[i].getText();
					buttons[i].setText("X");
					buttons[i].setVisible(false);
					buttons[i+4].setVisible(true);
					buttons[i+4].setText(x);
				
			}
			}
   ////////////////////////////////////////////////////////////////////
	if(i==5||i==6||i==9||i==10){//CHECK ANY OTHER BUTTON
	
		if((buttons[i+1].getText()=="X")){//Check button to the right
			x = buttons[i].getText();
			buttons[i].setText("X");
			buttons[i].setVisible(false);
			buttons[i+1].setVisible(true);
			buttons[i+1].setText(x);
		}
		
		if((buttons[i-1].getText()=="X")){//Check button to the left
			x = buttons[i].getText();
			buttons[i].setText("X");
			buttons[i].setVisible(false);
			buttons[i-1].setVisible(true);
			buttons[i-1].setText(x);
		}
		
		if((buttons[i+4].getText()=="X")){//Check button below
			x = buttons[i].getText();
			buttons[i].setText("X");
			buttons[i].setVisible(false);
			buttons[i+4].setVisible(true);
			buttons[i+4].setText(x);
		}
		
		if((buttons[i-4].getText()=="X")){//Check button above
			x = buttons[i].getText();
			buttons[i].setText("X");
			buttons[i].setVisible(false);
			buttons[i-4].setVisible(true);
			buttons[i-4].setText(x);
		}
		
      }
	}
	
	  public void GameOver(JButton[] buttons){//This method is used to Check if the user one and ends the game
		  String gameover[] = new String[15];//Create a string array that will store the text of buttons array
		  String check[] = new String[15];//This string array will be used to compare and check if you won.
		  int count = 0;
		  
		  for(int i = 0; i<check.length; i++){//Initialize the String array setting it equal to 1-15. This represents the order needed in order to win the game. 
			  check[i] = ""+ (i+1);
		  }
		  
		  for(int i = 0; i<buttons.length-1; i++){//Array that will take the text from the buttons array and store it into the gameover string
			  String x = buttons[i].getText();
			  gameover[i] = x;
		  }
		  
		  for(int i = 0; i < gameover.length; i++){//This loop is used to compare the two strings and see if the user has put the numbers in order (1-15)
			 if( gameover[i].equals(check[i])){
				 count++;//Every time the gameover and check string arrays are equal increment count each time.
			   }
			}
		     if(count==15){//If count is incremented to 15.(representing the 15 numbers) user has completed the game.
		    	String Message = "You completed the Puzzle in " + movesCounter + " moves";
		 		String titleBar = "Congratulations!";
		 		JOptionPane.showMessageDialog(null, Message, "" + titleBar, JOptionPane.INFORMATION_MESSAGE);
			   
		  }
		  
	  }
	
	
	public void actionPerformed(ActionEvent e) {//
		
		
		for (int i = 0; i < buttons.length; i++)//Go through the buttons array
		{
			if (e.getSource() == buttons[i])
			{
				boolean valid = isValidMove(i);//Every time a button is clicked check if it is a possible move, return the boolean value (true or false)
				if(valid==true){//If the move is possible
				SwitchButtons(i);//Switch the buttons
				movesCounter++;//increment movesCounter which counts how many moves the user has made. 
				movesDisplay.setText("Moves: " + movesCounter);//Print out the new number of moves. 
				GameOver(buttons);//Check if user won.
			}
		}
		}
		
		if(e.getSource() == menuButton){//If menuButton is clicked, open the menu and set the current window to invisible.
			setVisible(false);
			new PuzzleGameMenu();
			
		}
		
		if(e.getSource() == resetButton){//If the resetButton is clicked, open a new PuzzleGame that has a new set of random numbers. while setting the current window to invisible.
			new PuzzleGame();
			setVisible(false);
			
			
		}
	}
	    
	
	

}

