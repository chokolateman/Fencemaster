/**
 * 
 * Class to represent a player agent for the FenceMaster board game session.
 * 
 * @author Rahmadhy Karina (585592) and Bradley Jackson ()
 * 
 */
//import aiproject.fencemaster.player  
import java.util.NoSuchElementException;
import java.util.*;


public class rkarina {//implements Player, Piece {
	// Fields and initialized variables 
	int inp_num, p_piece;
	
	Board board = new Board(inp_num);
	//String ;
	
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
		// Init variables and values 
		int i, j, k=0;
		Cell cell = new Cell();
		
		// Validate the board configuration dimensions before populating  
		// checking correct values for n and p
		if (n < 5){ //|| (p!=("Int for W" || "Int for B"))){
			return -1;
		}
		
		// Assign this to the global variables for player 
		this.inp_num = n;
		this.p_piece=p;
		
		// Make the an empty board configuration
		try {
			// Loop through each row of board configuration
			for(i=0; i < (2*n-1); i++){
				// Loop through each column of board configuration 
				for(j=0; j < n + k; j++){
					Cell current = new Cell(i, j, "-");
						board.addCell(current);
				
				} 
			}
				// Manage hex positioning 
			if (i < n-1){k++;} else {k--;}
		}
		catch (NoSuchElementException e){
			System.err.println("Input lacks correct dimensions.");
			System.err.println("Caught NoSuchElementException:" + e.getMessage());	
		}
		return 0;
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
	 * 
	 * Evaluation function to assign weights to possible moves from the current
	 * board configurations. 
	 * 
	 */
	public void evaluationWeights(){
		
	}
	
	/**
	 * 
	 * Selects a desired move against another player agent in thr board game. 
	 * Based on a MiniMax Adversarial Search Algorithm, the function 
	 * selects a move, based on the MAX value of the current board configuration.
	 * 
	 * @return A move class object derived from the Player interface. 
	 * 
	 */
	//public Move makeMove(){
		// Variables and initialization of values  
		// Method/function name of algorithm
		// Block of Code 
		// Block of Code
		//
	//}
	
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
	 *  
	 */
	//public int opponentMove(Move m){
		// Variables and init of values
		// Function to check to see if the move is illegal, otherwise return 0
		// If it is illegal, return -1
	//}
	     
	/**
	 * This method should check the board configuration for a 
	 * possible winner and return the winner as an integer value
	 * according to the Piece interface (-1=INVALID, 0=EMPTY, 
	 * 1=WHITE, 2=BLACK). Note that EMPTY corresponds to a draw 
	 * and INVALID corresponds to a non-terminal state of the game.
	 * 
	 * @return int Value 
	 */
	//public int getWinner(){
		
	//}
	
	     
	/**
	* 
	* @param output
	*/
	//public void printBoard(PrintStream output){
		//int i=0;
		//while(i<board.boardCells.size()){
			
		//}
	//}

	
	
	
}
