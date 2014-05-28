import java.util.ArrayList;

/**
 * Computer move based on simple table lookup of preferences
 */
public class SimpleHeuristic extends rkarina {

	// Moves {row, col} in order of preferences. {0, 0} at top-left corner
	private ArrayList<String> preferredMoves = new ArrayList<String>();
	
	/** Blank Constructor */
	public SimpleHeuristic() {
	}
	 
	/**
	 * Generates the preferred moves according to the current board state. 
	*/
	public void generatePreferredMoves(Board b){
		preferredMoves = b.edges;
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