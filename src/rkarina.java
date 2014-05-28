/**
 * 
 * Class to represent a player agent for the FenceMaster board game session.
 * 
 * @author Rahmadhy Karina (585592) and Bradley Jackson (587163)
 * 
 */
import aiproj.fencemaster.*;  

import java.io.PrintStream;
import java.util.*;


public class rkarina implements Player, Piece {
	// Fields and initialized variables 
	int dim_num, p_piece, opp_piece, move_num = 0;
	String p_type, opp_type;
	
	Board board = new Board();
	NewGraph graph = new NewGraph();
	ArrayList<Cell> uncheckedCells = new ArrayList<Cell>();
	
	// Flag to check if the first move 
	Boolean isSwapped = false;
	
	// Constructor 
	public rkarina() {	
	}
	
	/**
	 * 
	 * Initialize the board game configurations, where the referee
	 * assigns this player and another agent a color and board dimension.
	 * 
	 * @param n | The board game configuration dimensions. 
	 * @param p | The player piece or color assigned for the current game. 
	 * 
	 * @return An integer value that returns -1 if the board game
	 * configuration does not initialize properly, otherwise returns 0. 
	 * 
	 */
	public int init(int n, int p){
		// Initialize variables and values
		int i, j, k = 0, o = 0;
		int boardCount = 0;
		Move nothing = new Move();
		
		// Assign this to the global variables for piece colour and board dimension 
		dim_num = n;
		p_piece = p;
		
<<<<<<< HEAD
		// Assign characters and piece for pplayer and  type for printing purposes 
		if(p_piece == 1){ opp_piece = 2; p_type = "W"; opp_type = "B"; }
		if(p_piece == 2){ opp_piece = 1; p_type = "B"; opp_type = "W"; }
=======

		// Assign characters for each player type for printing purposes 
		if(p_piece == 1){ p_type = "W"; opp_type = "B"; }
		if(p_piece == 2){ p_type = "B"; opp_type = "W"; }

		// Assign this to the global variables for player 
		this.dim_num = n;
		this.p_piece = p;
>>>>>>> 32a87a61b90a624e68eeb34a59bf59b763de0ab9
		
		// Make the an empty board configuration
		try {
			// Loop through each row of board configuration 
	        for(i=0; i < (2*n-1); i++){
				// Loop through each column of board configuration 
	        	for(j=0; j < n + k; j++){
					Cell current = new Cell(i, j+o, "-");
					board.addCell(boardCount,current);
					uncheckedCells.add(boardCount, current);
					boardCount++;
				}
				// Manage hex positioning 
				if (i < n-1){ k++; } else { k--; o++; }
			}
		}
		catch (NoSuchElementException e){
			System.err.println("Input lacks correct dimensions.");
			System.err.println("Caught NoSuchElementException:" + e.getMessage());	
			return -1;
		}
		return 0;
	}

	
	/**
	 * Selects a desired move against another player agent in thr board game. 
	 * Based on a MiniMax Adversarial Search Algorithm, the function 
	 * selects a move, based on the MAX value of the current board configuration.
	 * 
	 * @return A move class object derived from the Player interface. 
	 */
	public Move makeMove(){
		int randInt;
		// Variables and initialization of values  
		// Method/function name of algorithm
		// Block of Code 
		// Block of Code
		
		// Instantiate a new move object 
		Move move = new Move() ;
		// ArrayList of empty cells. 
		// Keep track of empty cells.
		
		// Random integer generator
		randInt = 0 + (int)(Math.random() * (uncheckedCells.size()));
		Cell randCell = uncheckedCells.get(randInt);
		
		// Initialize the move 
		move.Row = randCell.row;
		move.Col = randCell.col;
		move.P = p_piece;
		randCell.type = p_type;
		
		//Removes the random cell input from the Array List
		uncheckedCells.remove(randCell);
		
		// Update player board state 
		updateBoard(board, move);
		move_num++;
		return move;
	}
	
	/**
	 * This method is called by the referee to inform your player 
	 * about the opponent's most recent move, so that you can maintain 
	 * your board configuration. The input parameter is an object from 
	 * the class aiproj.fencemaster.Move, which includes the information 
	 * about the last move made by the opponent. Based on your board configuration
	 * if your player thinks this move is illegal you need to return -1 
	 * otherwise this function should return 0. 
	 * 
	 * @param m | A move class object from Player interface of the opponent. 
	 * 
	 * @return An integer value. Returns 0 if opponent move is legal, otherwise
	 * returns -1 if opponent move is illegal.
	 */

	public int opponentMove(Move m){
		// Variables and init of values
<<<<<<< HEAD
		// Check if the opponent is able to swap or not
		if (opp_piece == 2 && move_num != 1 && m.IsSwap == true){ return -1; }
		if (opp_piece == 1 && m.IsSwap == true ){ return -1; }
		
		// Update the uncheckCells array list
    	for (Cell temp: uncheckedCells){
    		if (temp.row == m.Row && temp.col == m.Col) {
    			uncheckedCells.remove(temp);
    			break;
    		}
    	}
		
		updateBoard(board, m);
		move_num++;
		return 0;
=======
		// Function to check to see if the move is illegal, otherwise return 0
		// If it is illegal, return -1
		int i;
		int colourCount = 0;
		int swapIndex;
		int tempColourInt; 
		String tempColourStr;
		boolean possibleSwap = false;
		
		

		/*First check if move is within bounds of board rows*/
		if(m.Row < 0 || m.Row > (2*(this.dim_num) - 2)){ //max size is 2N-2
			return -1;
		}

		/*Check if move is within bounds of board columns. 
		The top half of the board and bottom half each follow
		different constraints

		e.g. top half is between 0 and N + row number
		bottom half is between N + 1 - row number and 2N-2
		*/


		if(m.Col < 0 || m.Col >= (this.dim_num + m.Row)){
			return -1;
		}
		if(m.Col < ((m.Row)-((this.dim_num)-1)) || m.Col > (2*(this.dim_num) - 2)){
			return -1;
		}


		/*Check if a Swap is used. Iterate over and make sure that the opponent
		has only used ONE move*/
		for(i = 0; i < board.boardCount; i++){
			tempColourInt = colourStringToInt(board.boardCells.get(i).type);
			if(tempColourInt == m.P){
				colourCount ++;
			}
		}
		if(colourCount == 1){
			possibleSwap = true;
		}

		
		/*Check if opponent is using this player's colour i.e. the wrong colour*/
		if(m.P == this.p_piece){
			return -1;
		}

		/*Check if piece isn't overlapping another piece on board*/
		for(i = 0; i < board.boardCount; i++){
			if((board.boardCells.get(i).row == m.Row) && 
				(board.boardCells.get(i).col == m.Col)){

				swapIndex = i;
				
				/*Detected overlap, check if they are overlapping your cell*/
				tempColourInt = colourStringToInt(board.boardCells.get(i).type);
				
				//System.out.println("board type: " + tempColourInt + " move type: " + m.P);
				if(tempColourInt != m.P && tempColourInt != 0){
					return -1;
				}
				/*Detected a match, now need to see if IsSwap was invoked*/
				else{
					/*Check if the swap was used, if true, update location colour
					and end*/
					if(possibleSwap == true && m.IsSwap == true){
						board.boardCells.get(i).row = m.Row;
						board.boardCells.get(i).col = m.Col;
						
						tempColourStr = colourIntToString(m.P);
						board.boardCells.get(i).type = tempColourStr;
						return 0;
					}


				}


			}
		}
		/*Move has passed checks, update opponent location*/
		/*construct new cell, and add to board*/
		tempColourStr = colourIntToString(m.P);
		Cell opponentCell = new Cell(m.Row, m.Col, tempColourStr);
		board.boardCells.add(opponentCell);
		
		/*
		board.boardCells.get(i).row = m.Row;
		board.boardCells.get(i).col = m.Col;
		board.boardCells.get(i).type = tempColourStr;
		*/
		
		return 0;
	}
	
	public int colourStringToInt(String type){
		/*converts colour defined strings (b,w,-) from the cell class
		 * and converts them into colour defined ints from the piece class
		White = 1, Black = 2, Empty = 0, Invalid = -1
		 */
		int colour;
		type = type.toUpperCase();
		
		if((type).equals("B")){
			colour = 2;
		}
		else if((type).equals("W")){
			colour = 1;
		}
		else if((type).equals("-")){
			colour = 0;
		}
		else{
			colour = -1;
		}
		
		
		return colour;
	}
	
	public String colourIntToString(int type){
		/*converts colour defined ints from the piece class 	 
		* and converts them into colour defined strings (B,W,-) from the cell class
		White = 1, Black = 2, Empty = 0, Invalid = -1
		 */
		String colour;
		if(type == 1){
			colour = "W";
		}
		else if(type == 2){
			colour = "B";
		}
		else if(type == 0){
			colour = "-";
		}
		else{
			colour = "-";
		}
		
		
		return colour;
>>>>>>> 32a87a61b90a624e68eeb34a59bf59b763de0ab9
	}
	     
	/**
	 * This method should check the board configuration for a 
	 * possible winner and return the winner as an integer value
	 * according to the Piece interface (-1=INVALID, 0=EMPTY, 
	 * 1=WHITE, 2=BLACK). Note that EMPTY corresponds to a draw 
	 * and INVALID corresponds to a non-terminal state of the game.
	 * 
	 * @return int Value that represents the current board state, either 
	 * a non-terminal or terminal state is presented in the board game state.  
	 */
	public int getWinner(){
		//board.checkTripod(Newgraph g);
		if (this.uncheckedCells.size() > 0){
			return -1;
		}
		return 0;
	}
	
	/**
	 * Function to check through the whole board and all cells in the board. 
	 * Updates when an opponent move is given from opponentMove method and when
	 * the player itself makes a move.
	 */
	public void updateBoard(Board b, Move move){
		int i=0;
		while(i<b.boardCells.size()){
			Cell temp = b.boardCells.get(i);
			if ((temp.row == move.Row) && (temp.col == move.Col)){
				if (move.P == 1){ temp.type = "W"; }
				if (move.P == 2){ temp.type = "B"; }
				break;
			}
			i++;
		}
	}
	
	/**
	* 
	* @param output
	*/
	public void printBoard(PrintStream output){
		PrintStream out = output;
		// Reset value for printing
     	int count=0;
     	int d = 0;
     	int k = 0;
     	// For printing and testing purposes 
     	for(int i=0; i < (2*dim_num-1); i++){
			// Loop through each column of board configuration 
			for(int j=0; j < dim_num + k; j++){
					while(d<(dim_num-k)){
						out.print(" ");
						d++;
					}
					out.print(board.boardCells.get(count).type + " ");
					count++;
			}
			d=0;
			while(d<(dim_num-k)){
				out.print(" ");
				d++;
			}
			out.println();
			d=0;
			// Manage hex positioning 
			if (i < dim_num-1){k++;} else {k--;}
		}
     	// Show the PrintStream to the player 
     	//out.flush();
	}
	
	/**
	* 
	* Alpha Beta Pruning function to speed up the MiniMax searching processes. 
	* 
	*/
	public String alphaBeta(){
		return ""; 
	}
	
	/** Recursive minimax at level of depth for either maximizing or minimizing player.
     */
	private void minimax(int depth, Seed player) {
   
	// Generate possible next moves in a List of int[2] of {row, col}.
	ArrayList<Cell> nextMoves = uncheckedCells;

   // mySeed is maximizing; while oppSeed is minimizing
   int bestScore = (player == p_piece) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
   int currentScore;
   int bestRow = -1;
   int bestCol = -1;

   if (nextMoves.isEmpty() || depth == 0) {
      // Gameover or depth reached, evaluate score
      bestScore = evaluate();
   } else {
      for (int[] move : nextMoves) {
         // Try this move for the current "player"
         cells[move[0]][move[1]].content = player;
         if (player == mySeed) {  // mySeed (computer) is maximizing player
            currentScore = minimax(depth - 1, oppSeed)[0];
            if (currentScore > bestScore) {
               bestScore = currentScore;
               bestRow = move[0];
               bestCol = move[1];
            }
         } else {  // oppSeed is minimizing player
            currentScore = minimax(depth - 1, mySeed)[0];
            if (currentScore < bestScore) {
               bestScore = currentScore;
               bestRow = move[0];
               bestCol = move[1];
            }
         }
         // Undo move
         cells[move[0]][move[1]].content = Seed.EMPTY;
      }
   }
   return new int[] {bestScore, bestRow, bestCol};
}
	
	/**
	 * Evaluation function to assign weights to possible moves from the current
	 * board configurations. 
	 */
	public int evaluate(){
		
	}

	
	
	
}
