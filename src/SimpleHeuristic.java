import java.util.ArrayList;
import aiproj.fencemaster.*;

/**
 * Computer move based on simple table lookup of preferences
 */
public class SimpleHeuristic extends rkarina {

	// Moves {row, col} in order of preferences. {0, 0} at top-left corner
	//why a string lol? Not an array of cells?
	
	//private ArrayList<String> preferredMoves = new ArrayList<String>();
	private ArrayList<Cell> preferredMoves = new ArrayList<Cell>();
	private int preferredMoveSize = 0;
	
	/** Blank Constructor */
	public SimpleHeuristic() {
	}
	 
	/**
	 * Generates the preferred moves according to the current board state. 
	 * 
	 * Preferred moves?: As in all possible moves? Or highest value move?
	 * 
	 * Current version: generates ALL possible moves to be further examined
	*/
	public void GenerateAllMoves(Board b){
		for(int i = 0; i < board.boardCount; i++){
			if(board.boardCells.get(i).type.equals("-")){
				preferredMoves.add(board.boardCells.get(i));
				preferredMoveSize ++;
			}
		}
	}
	
	/*MaxHeuristic deals with finding the most ideal move for the player*/
	public int MaxHeuristic(Board b){
		for(int i = 0; i < board.boardCount; i++){
			
		}
		
		return -1;
	}
	   
	/** Search for the first empty cell, according to the preferences
	 *  Assume that next move is available, i.e., not gameover
	 *  @return int[2] of {row, col}
	 */
	    //@Override
	   // public int[] move() {
	     // for (int[] move : preferredMoves) {
	       //  if (cells[move[0]][move[1]].content == Seed.EMPTY) {
	         //   return move;
	         //}
	     // }
	      //assert false : "No empty cell?!";
	      //return null;
	   //}  
}