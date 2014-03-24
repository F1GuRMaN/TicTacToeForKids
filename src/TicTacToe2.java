import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	public class TicTacToe2 extends Applet implements ActionListener{
	
		Button squares[][];
		Button newGameButton;
		Label score;
		Label playerWin;
		Label playerLost;
		int emptySquaresLeft=9;
		int i;
		int j;
		
		
		/**
		* init method is the applet's constructor
		*/
		public void init() {
			//Set the applet's layout manager, font and color
			this.setLayout(new BorderLayout());
			this.setBackground(Color.CYAN);
			
			// Change the applet's font to be bold
			// of size 20 points
			Font appletFont=new Font("Monospased",Font.BOLD, 20);
			this.setFont(appletFont);
			// Create the button New Game and register it
			// with the action listener
			newGameButton = new Button ("New Game");
			newGameButton.addActionListener(this);
			playerWin = new Label ("");
			playerLost = new Label ("");
	//		playerWin.setText("You won" + i + "games");
			Panel topPanel=new Panel();
			topPanel.setLayout(new GridLayout(3,1));
			topPanel.add(newGameButton, "North");
			topPanel.add(playerWin, "North");
			topPanel.add(playerLost, "South");
			
					
			this.add("North", topPanel);
			
			Panel centerPanel=new Panel();
			centerPanel.setLayout(new GridLayout(3,3));
			this.add(centerPanel,"Center");
			score=new Label("Your turn!");
			this.add(score,"South");
			
			// create an array to hold references to 9 buttons
			squares=new Button[3][3];
			// Instantiate the buttons, store the references
			// to them in the array, register them with the
			// listeners, paint them in orange and add to panel
			
			for (int i = 0; i<3; i++) {
				for (j=0; j<3; j++) {
				squares[i][j] = new Button();
				squares[i][j].addActionListener(this);
				squares[i][j].setBackground(Color.ORANGE);
				centerPanel.add(squares[i][j]);
			}
			}
			
		}
		/**
		* This method will process all action events
		* @param ActionEvent object
		*/
		public void actionPerformed(ActionEvent e) {
			Button theButton = (Button) e.getSource();
			// Is this a New Game button?
			if(theButton == newGameButton) {
				for(int i=0; i<3; i++) {
					for (j=0; j<3; j++) {
					squares[i][j].setEnabled(true);
					squares[i][j].setLabel("");
					squares[i][j].setBackground(Color.ORANGE);
				}
				}
				emptySquaresLeft=9;
				score.setText("Your turn!");
				newGameButton.setEnabled(false);
				return; // exit the method here
			}
			String winner ="";
			// Is this one of the squares?
			for ( int i=0; i<3; i++ ) {
				for (j=0; j<3; j++) {
				if(theButton == squares[i][j] && squares[i][j].getLabel().equals("")) {
					squares[i][j].setLabel("X");
					//(squares[selectedSquare].getLabel().equals(""))
					winner = lookForWinner();
				
		
					if(!"".equals(winner)){
						endTheGame();
					}
					
						
						 else {
						computerMove();
						winner = lookForWinner();
						if ( !"".equals(winner)){
						endTheGame();
						}
						}
						break;
						}
			} // end for
			}
			if ( winner.equals("X") ) {
				++i;
				score.setText("You won!");
				playerWin.setText(Integer.toString(i));	
				} else if (winner.equals("O")){
					++j;
				score.setText("You lost!");
				playerLost.setText(Integer.toString(j));		
					} else if (winner.equals("T")){
				score.setText("It's a tie!");
				}
			}
			 // end actionPerformed
		
		/**
		* This method is called after every move to see
		* if we have a winner. It checks every row, column
		* and diagonal to find out three squares with the
		* same label (other than blank)
		* @return "X", "O", "T" for tie or "" for no winner
		*/
		
		String lookForWinner() {
			String theWinner = "";
			emptySquaresLeft--;
			if (emptySquaresLeft==0){
			return "T"; // it's a tie
			}
			
			// Check the row 1 - array elements 0,1,2
			if (!squares[0][0].getLabel().equals("") &&
			squares[0][0].getLabel().equals(squares[1][0].getLabel()) &&
			squares[0][0].getLabel().equals(squares[2][0].getLabel())) {
			theWinner = squares[0][0].getLabel();
	//		highlightWinner(0,1,2);
			// Check the row 2 - array elements 3,4,5
			} else if (!squares[1][0].getLabel().equals("") &&
			squares[1][0].getLabel().equals(squares[1][1].getLabel()) &&
			squares[1][0].getLabel().equals(squares[1][2].getLabel())) {
			theWinner = squares[1][0].getLabel();
	//		highlightWinner(3,4,5);
			// Check the row 3 - - array elements 6,7,8
			} else if ( ! squares[2][0].getLabel().equals("") &&
			squares[2][0].getLabel().equals(squares[2][1].getLabel()) &&
			squares[2][0].getLabel().equals(squares[2][2].getLabel())) {
			theWinner = squares[2][0].getLabel();
	//		highlightWinner(6,7,8);
			// Check the column 1 - array elements 0,3,6
			} else if ( ! squares[0][0].getLabel().equals("") &&
			squares[0][0].getLabel().equals(squares[1][0].getLabel()) &&
			squares[0][0].getLabel().equals(squares[2][0].getLabel())) {
			theWinner = squares[0][0].getLabel();
	//		highlightWinner(0,3,6);
			// Check the column 2 - array elements 1,4,7
			} else if ( ! squares[0][1].getLabel().equals("") &&
			squares[0][1].getLabel().equals(squares[1][1].getLabel()) &&
			squares[0][1].getLabel().equals(squares[2][1].getLabel())) {
			theWinner = squares[0][1].getLabel();
	//		highlightWinner(1,4,7);
			// Check the column 3 - array elements 2,5,8
			} else if ( !squares[0][2].getLabel().equals("") &&
			squares[0][2].getLabel().equals(squares[1][2].getLabel()) &&
			squares[0][2].getLabel().equals(squares[2][2].getLabel())) {
			theWinner = squares[0][2].getLabel();
	//		highlightWinner(2,5,8);
			
			// Check the first diagonal - array elements 0,4,8
			} else if ( ! squares[0][0].getLabel().equals("") &&
			squares[0][0].getLabel().equals(squares[1][1].getLabel()) &&
			squares[0][0].getLabel().equals(squares[2][2].getLabel())) {
			theWinner = squares[0][0].getLabel();
	//		highlightWinner(0,4,8);
			// Check the second diagonal - array elements 2,4,6
			} else if ( ! squares[0][2].getLabel().equals("") &&
			squares[0][2].getLabel().equals(squares[1][1].getLabel()) &&
			squares[0][2].getLabel().equals(squares[2][0].getLabel())) {
			theWinner = squares[0][2].getLabel();
	//		highlightWinner(2,4,6);
			}
			return theWinner;
			}
		
		/**
		* This method applies a set of rules to find
		* the best computer's move. If a good move
		* can't be found, it picks a random square.
		*/
		
		void computerMove() {
			int selectedSquare;
			
			// Computer first tries to find an empty
			// square next the two squares with "O" to win
			selectedSquare = findEmptySquare("O");
			// if can't find two "O", at least try to stop the
			// opponent from making 3 in a row by placing
			// "O" next to 2 "X".
			if ( selectedSquare == -1 ) {
			selectedSquare = findEmptySquare("X");
			}
			// if the selectedSquare is still -1, at least
			// try to occupy the central square
			if ( (selectedSquare == -1)
			&&(squares[1][1].getLabel().equals("")) ){
				selectedSquare=4;
			}
			// no luck with the central either...
			// just get a random square
			if ( selectedSquare == -1 ){
			selectedSquare = getRandomSquare();
			}
			squares[selectedSquare/3][selectedSquare%3].setLabel("O");
			}
	/**
	*This method checks every row, column and diagonal
	* to see if there are two squares with the same label
	* and an empty square.
	* @param give X - for user, and O for computer
	* @return the number of the empty square to use,
	* or the negative 1 could not find 2 square
	* with the same label
	*/
	int findEmptySquare(String player) {
	int weight[][] = new int[3][3];
	for ( int i = 0; i < 3; i++ ) {
		for (int j=0; j<3; j++) {
	if ( squares[i][j].getLabel().equals("O") )
	weight[i][j] = -1;
	else if ( squares[i][j].getLabel().equals("X") )
	weight[i][j] = 1;
	else
	weight[i][j] = 0;
	}
	int twoWeights = player.equals("O") ? -2 : 2;
	// See if row 1 has the same 2 squares and a blank
	if ( weight[0][0] + weight[0][1] + weight[0][2] == twoWeights
	) {
	if ( weight[0][0] == 0 )
	return 0;
	else if ( weight[0][1] == 0 )
	return 1;
	else
	return 2;
	}
	// See if row 2 has the same 2 squares and a blank
	if (weight[1][0] +weight[1][1] + weight[1][2] == twoWeights) {
	if ( weight[1][0] == 0 )
	return 3;
	else if ( weight[1][1] == 0 )
	return 4;
	else
	return 5;
	}
	
	// See if row 3 has the same 2 squares and a blank
	if (weight[2][0] + weight[2][1] +weight[2][2] == twoWeights ) {
	if ( weight[2][0] == 0 )
	return 6;
	else if ( weight[2][1] == 0 )
	return 7;
	else
	return 8;
	}
	// See if column 1 has the same 2 squares and a blank
	if (weight[0][0] + weight[1][0] + weight[2][0] == twoWeights) {
	if ( weight[0][0] == 0 )
	return 0;
	else if ( weight[1][0] == 0 )
	return 3;
	else
	return 6;
	}
	// See if column 2 has the same 2 squares and a blank
	if (weight[0][1] +weight[1][1] + weight[2][1] == twoWeights ) {
	if ( weight[0][1] == 0 )
	return 1;
	else if ( weight[1][1] == 0 )
	return 4;
	else
	return 7;
	}
	// See if column 3 has the same 2 squares and a blank
	if (weight[0][2] + weight[1][2] + weight[2][2] == twoWeights ){
	if ( weight[0][2] == 0 )
	return 2;
	else if ( weight[1][2] == 0 )
	return 5;
	else
	return 8;
	}
	//See if diagonal 1 has the same 2 squares and a blank
	if (weight[0][0] + weight[1][1] + weight[2][2] == twoWeights ){
	if ( weight[0][0] == 0 )
	return 0;
	else if ( weight[1][1] == 0 )
	return 4;
	else
	return 8;
	}
	
	// See if diagonal has the same 2 squares and a blank
	if (weight[0][2] + weight[1][1] + weight[2][0] == twoWeights ){
	if ( weight[0][2] == 0 )
	return 2;
	else if ( weight[1][1] == 0 )
	return 4;
	else
	return 6;
	}
	// There are no two neighbors that are the same
	}
	return -1;
	
	} // end of findEmptySquare()
	/**
	* This method selects any empty square
	 * @return 
	* @return a randomly selected square number
	*/
		 int getRandomSquare() {
	boolean gotEmptySquare = false;
	int selectedSquare = -1;
		
	do {
	selectedSquare = (int) (Math.random() * 9 );

	if (squares[selectedSquare/3][selectedSquare%3].getLabel().equals("")){
	gotEmptySquare = true; // to end the loop
	}
	} while (!gotEmptySquare );
	return selectedSquare;
	} // end getRandomSquare()
	/**
	* This method highlights the winning line.
	* @param first,second and third squares to highlight
	*/
		 
		/* 
		void highlightWinner(int win1, int win2, int win3) {
	squares[win1].setBackground(Color.CYAN);
	squares[win2].setBackground(Color.CYAN);
	squares[win3].setBackground(Color.CYAN);
	}
		*/
		
		
	// Disables squares and enable New Game button
	
		void endTheGame(){
	newGameButton.setEnabled(true);
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
	squares[i][j].setEnabled(false);
	}
	}
		}
	} // end of class