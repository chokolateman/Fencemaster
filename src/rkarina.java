/******************************************************************************
 * 
 * Class to represent a player agent for the FenceMaster board game session.
 * 
 * @author Rahmadhy Karina (585592) and Bradley Jackson (587163)
 * 
******************************************************************************/

import aiproj.fencemaster.*;  

import java.io.PrintStream;
import java.util.*;


public class rkarina implements Player, Piece {
	// Fields and initialized variables 
	int dim_num, dim_height, p_piece, opp_piece, move_num = 0;
	String p_type, opp_type;
	Cell prevMove;
	
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
		
		// Assign global variables for player piece and board dimension 
		dim_num = n;
		dim_height = (2*n)-2;
		p_piece = p;
		
		// Assign characters and pieces 
		if(p_piece == 1){ opp_piece = 2; p_type = "W"; opp_type = "B"; }
		if(p_piece == 2){ opp_piece = 1; p_type = "B"; opp_type = "W"; }
		
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
			System.err.println("Caught NoSuchElementException:" 
			+ e.getMessage());	
			return -1;
		}
		
		board.generateEdgeNodes(dim_num);
		return 0;
	}

	
	/**
	 * Selects a desired move against another player agent in the board game. 
	 * Based on a MiniMax Adversarial Search Algorithm, the function 
	 * selects a move, based on the MAX value of the current 
	 * board configuration.
	 * 
	 * @return A move class object derived from the Player interface. 
	 */
	public Move makeMove(){
		int randInt;
		int minimaxReturn[];
		Cell nextCell = new Cell();

		
		// Instantiate a new move object 
		Move move = new Move() ;
		// ArrayList of empty cells. 
		// Keep track of empty cells.
		
		/*Randomly place first piece*/
		if(move_num < 2){
			// Random integer generator
			randInt = 0 + (int)(Math.random() * (uncheckedCells.size()));
			nextCell = uncheckedCells.get(randInt);
			
			// Initialize the move 
			move.Row = nextCell.row;
			move.Col = nextCell.col;
			move.P = p_piece;
			nextCell.type = p_type;
			//Removes the random cell input from the Array List
			uncheckedCells.remove(nextCell);
			
		}
		/*otherwise if not first move, commence Minimax*/
		else{
			nextCell = uncheckedCells.get(0);
			minimaxReturn = minimax(nextCell, 2, p_piece);
			move.Row =  minimaxReturn[1];
			move.Col =  minimaxReturn[2];
			move.P = p_piece;
			
			nextCell.row = move.Row;
			nextCell.col = move.Col;
			nextCell.type = p_type;
			
			//Removes the returned minimax position from the Array List
			uncheckedCells.remove(nextCell);
			

		}
		
		// Update player board state 
		updateBoard(board, move);
		move_num++;
		return move;
	}
	
	/**
	 * Function to inform current player about the opponent's most recent move, 
	 * so that you can maintain your board configuration. Based on board 
	 * configuration, if move is illegal return -1, otherwise this function 
	 * returns 0. 
	 * 
	 * @param m | A move class object from Player interface of the opponent. 
	 * 
	 * @return An integer value. Returns 0 if opponent move is legal, otherwise
	 * returns -1 if opponent move is illegal.
	 */
	public int opponentMove(Move m){
		/*First check if move is within bounds of board rows*/
		if(m.Row < 0 || m.Row > dim_height){ //max size is 2N-2
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
		if(m.Col < ((m.Row)-((dim_num)-1)) || m.Col > dim_height){
			return -1;
		}

		
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
    	
		// Once the opponents last move is a legal move, update current board 
    	// state and add the number of moves played
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
	
	/** 
	 * Recursive minimax at level of depth for either maximizing or minimizing player.
     */
	private int[] minimax(Cell node, int depth, int player) {
		// Assigns the piece type, whether a minimizer or maximizer
		String player_piece;
	    
		// Check if the current minimax call is a maximizer(p_piece) or a minimizer (opp_piece)
	    if (player == p_piece){ player_piece = p_type; } else { player_piece = opp_type; }
		
		// Generate possible next moves in an Array List (these are empty cells)
	    // if not working, try .clone()
		ArrayList<Cell> nextMoves = uncheckedCells;
	
		// p_piece is maximizing; while opp_piece is minimizing
		int bestScore = (player == p_piece)? Integer.MAX_VALUE : Integer.MIN_VALUE;
		int currentScore;
		int bestRow = -1;
		int bestCol = -1;
	
		if (nextMoves.isEmpty() || depth == 0) {
			// Gameover or depth reached, evaluate score
			bestScore = evaluate(node, board, player_piece);
		} else {
			for (Cell move : nextMoves) {
	        // Try this move for the current "player" piece
			move.type = player_piece;	
	        if (player == p_piece) {  // p_piece is maximizing player
	        	// Grabs the existing score and updates it 
	            currentScore = minimax(move, depth - 1, opp_piece)[0];
	            if (currentScore > bestScore) {
	                bestScore = currentScore;
	                bestRow = move.row;
	                bestCol = move.col;
	            }
	        } else {  // opp_piece is minimizing player
	        	// Grabs the existing score and updates it 
	            currentScore = minimax(move, depth - 1, p_piece)[0];
	            if (currentScore < bestScore) {
	               bestScore = currentScore;
	               bestRow = move.row;
	               bestCol = move.col;
	            }
	         }
	         // Reset move
	         move.type = "-";
	      }
	   	}
	   	// Returns the evaluation/weight of the cell node and its row and column
		return new int[] {bestScore, bestRow, bestCol};
	}
	
	/**
	 * Evaluation function to assign weights to possible moves from the current
	 * board configurations. 
	 */
	private int evaluate(Cell c, Board b, String player_piece){
		int weight=0;
		/* Checks what player_piece currently is 
		 * Simple evaluation V1
		 * Ranks edge nodes higher, ranks adjacent to existing pieces higher
		 * bridges nodes towards closes untouched edge
		 * a chain of nodes with more pieces ranked as a higher priority to join
		 */
		int x,y, currPieces;
		double meanDistance, closestEdgeDistance, evaluation;
		Cell closeCorner;
		
		meanDistance = currPieces = 0;
		closeCorner = findClosestEdge(c);
		
		/*Calculate the average distance between current cell, and all of the 
		 * other cells of the player already on the board
		 */
		for(int i = 0; i < b.boardCount; i++){
			if(player_piece.equals(p_type)){
				if(b.boardCells.get(i).type.equals(player_piece)){
					meanDistance += FindDistance(c, b.boardCells.get(i));
					currPieces ++;
				}
			}
			else if(player_piece.equals(opp_type)){
				if(b.boardCells.get(i).type.equals(player_piece)){
					meanDistance += FindDistance(c, b.boardCells.get(i));
					currPieces ++;
				}
			}		
		}
		/*Add the edge and mean distances, the smaller the piece is, the more ideal it is*/
		meanDistance = (double)meanDistance/currPieces;
		closestEdgeDistance = FindDistance(c, closeCorner);
		evaluation = meanDistance + closestEdgeDistance;
		
		weight = getWeight(evaluation);
		
		/*corner pieces are worthless
		 *contrastingly, cells 1 space diagonal from the corner are highly valued
		 *starting locations for tripod victories.  
		 */

		/*if(isCornerPiece(c) == true){
			weight = 0;
		}*/
		//System.out.println("weight: " + weight);
		return weight;
	}
	
	/**
	 * The smaller the evaluation is, the more ideal it is.
	 * Therefore, return greater weight.
	 */
	int getWeight(double evaluation){
		int weight = 0;
		
		if(evaluation < 6.0){
			weight = 1;
		}
		if(evaluation < 4.0){
			weight = 2;
		}
		if(evaluation < 3.0){
			weight = 3;
		}
		if(evaluation < 2.5){
			weight = 4;
		}
		if(evaluation < 2.0){
			weight = 5;
		}
		if(evaluation < 1.5){
			weight = 6;
		}
		
		return weight;
	}
	
	private double FindDistance(Cell c, Cell d){
		double distance = -1;
		int x1,x2,y1,y2;
		x1 = c.row;
		y1 = c.col;
		x2 = d.row;
		y2 = d.col;
		
		distance = ((Math.abs(x1-x2) + Math.abs(y1-y2) + Math.abs((x1-y1)-(x2-y2)))/2);
		
		
		return distance;
	}
	
	/**
	 * Used to detect if current piece is a corner
	 */
	boolean isCornerPiece(Cell cell){
		/*Blocks the 6 corner coordinates:
		 * (0,0), (0, n-1), (n-1, 0), (n-1, 2n-2), (2n-2, n-1), (2n-2, 2n-2)
		 */
		if((cell.row == 0)&&(cell.col == 0)){
			return true;
		}
		else if((cell.row == 0)&&(cell.col == dim_num-1)){
			return true;
		}
		else if((cell.row == dim_num-1)&&(cell.col == 0)){
			return true;
		}
		else if((cell.row == dim_num-1)&&(cell.col == (2*dim_num)-2)){
			return true;
		}
		else if((cell.row == (2*dim_num)-2)&&(cell.col == dim_num-1)){
			return true;
		}
		else if((cell.row == (2*dim_num)-2)&&(cell.col == (2*dim_num)-2)){
			return true;
		}
		
		return false;
	}
	
	/**
	 * finds closest edge to current piece
	 * 
	 */
	Cell findClosestEdge(Cell cell){
		int i = 0;
		//corners of the board
		int corners[][] = { {0, 0} , { dim_num-1, 0}, {0, dim_num-1}, {dim_num-1, dim_height}, 
				{dim_height, dim_num-1}, {dim_height, dim_height} };
		
		double distance = 0.0, temp;
		double x,y;
		double cellrow = cell.row;
		double cellcol = cell.col;
		
		int closestCorner = 0;
		
		//set initial distance
		x = corners[i][0];
		y = corners[i][1];
		distance = ((Math.abs(x-cellrow) + Math.abs(y-cellcol) + Math.abs((x-y)-(cellrow-cellcol)))/2);
		
		for(i = 0; i < 6; i++ ){
			x = corners[i][0];
			y = corners[i][1];
			temp = ((Math.abs(x-cellrow) + Math.abs(y-cellcol) + Math.abs((x-y)-(cellrow-cellcol)))/2);
			if(temp>distance){
				distance = temp;
				closestCorner = i;
			}
		}
		
		cell.row = corners[closestCorner][0];
		cell.col = corners[closestCorner][1];
		
		return cell;
	}
}
