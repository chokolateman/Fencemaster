/* 
 * The main class to handle standard input and validate board for
 * terminal states.
 *
 *	Date modified:  
 */
import java.util.Scanner;
import java.util.NoSuchElementException;


public class TestWin {
	public static void main(String args[]){  
		// Initialize all relevant variables and class objects
		Scanner scan = new Scanner(System.in);  
    	NewGraph graph = new NewGraph() ;
    	rkarina player = new rkarina();
    	
    	// Read in top line value, which determines board dimensions
        int inp_num = scan.nextInt();
        
        // Set the board size and the edge node ranges
        Board board = new Board(inp_num);
        board.generateEdgeNodes(inp_num);
        
        // Validation of integer input dimension
        if(inp_num < 5){
    		System.out.print("Please enter dimensions greater than 5");
    		System.exit(0);
        }
        

 
		
		// Check if there is the wrong number of coloured pieces in the board 
		if (board.num_W - board.num_B < -1 || board.num_B - board.num_W > 1){
			System.err.println("Invalid number of coloured positions.");
		}
		
		// Is it a win?
		boolean win = board.checkTripod(graph);
		
		
		// Check if there are any tripods on the board
		if (win && (board.colourWon.compareTo("B") == 0)){
			System.out.println("Black");
			System.out.println("Tripod");
		}
		if (win && (board.colourWon.compareTo("W") == 0)){
			System.out.println("White");
			System.out.println("Tripod");
		}
		
		// Check if there are more cells to update
		if (!win && (board.boardCount < board.cellCount)){
			System.out.println("None");
			System.out.println("Nil");
		}
	}
}