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
	int dim_num, p_piece, move_num = 0;
	String p_type, opp_type;
	
	Board board = new Board();
	NewGraph graph = new NewGraph();
	ArrayList<Cell> uncheckedCells = new ArrayList<Cell>();
	
	// Flag to check if the first move 
	Boolean isSwapped = false;
	// Constructor 
	rkarina() {	
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
		
		// Assign this to the global variables for piece colore and board dimension 
		dim_num = n;
		p_piece = p;
		
		// Assign characters for each player type for printing purposes 
		if(p_piece == 1){ p_type = "W"; opp_type = "B"; }
		if(p_piece == 2){ p_type = "B"; opp_type = "W"; }
		
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
		// Function to check to see if the move is illegal, otherwise return 0
		if (m.IsSwap == true && move_num != 1)
			return -1;
		updateBoard(board, m);
		move_num++;
		return 0;
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
		// board.checkTripod(Newgraph g);
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
	 * Checks the board for  
	 * @param b A board configuration. 
	 */
	public void checkBoard(Board b){
		int i=0;
		while(i<b.boardCells.size()){
			Cell temp = b.boardCells.get(i);
			if(temp.type=="-"){ uncheckedCells.add(temp); } 
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
	
	/**
	 * Evaluation function to assign weights to possible moves from the current
	 * board configurations. 
	 */
	public void evaluationWeights(){
		
	}

	
	
	
}
