/* 
 * The main class to handle standard input and validate board for
 * terminal states.
 *
 *	Date modified:  
 */
import java.io.PrintStream;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.lang.*;

public class TestWin {
	public static void main(String args[]){  
		// Initialize all relevant variables and class objects
		Scanner scan = new Scanner(System.in);  
    	NewGraph graph = new NewGraph();
    	rkarina player = new rkarina();
    	
    	// Read in top line value, which determines board dimensions
        int n = scan.nextInt(), k=0, j, o=0;
        int boardCount = 0, c=0;
        // Set the board size and the edge node ranges
        Board board = new Board(n);
        Board b = new Board();
        board.generateEdgeNodes(n);
        
        // For printing and testing purposes 
        for(int i=0; i < (2*n-1); i++){
			// Loop through each column of board configuration 
        	c=0;
        	for(j=0; j < n + k; j++){
				Cell current = new Cell(i, j+o, "-");
					while(c<(n-k)){
						System.out.print(" ");
						c++;
					}
					board.addCell(boardCount,current);
					System.out.print(board.boardCells.get(boardCount).type + " ");
					boardCount++;
			}
			System.out.println();
			// Manage hex positioning 
			if (i < n-1){k++;} else {k--;o++;}
		}
        
        int moves = 0;
        int x, y, index=0;
        Scanner inp = new Scanner(System.in);
        while(moves<7){
        	Cell temp = new Cell();
        	System.out.println("Make a move by typing a row: ");
        	x = inp.nextInt();
        	System.out.println("Make a move by typing a column: ");
        	y = inp.nextInt();
        	while(index<board.boardCells.size()){
        		temp = board.boardCells.get(index);
        		if ((temp.row == x) && (temp.col == y)){
        			temp.type = "B";
        		}
        		index++;
        	}
        	index=0;
        	b.printBoard(board, n);
        	moves++;
        }
       
        // Validation of integer input dimension
        if(n < 5){
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

	private static void printBoard(Board board, int n) {
		// Reset value for printing
     	int count=0;
     	int d = 0;
     	int k=0;
     	// For printing and testing purposes 
     	for(int i=0; i < (2*n-1); i++){
			// Loop through each column of board configuration 
			for(int j=0; j < n + k; j++){
					while(d<(n-k)){
						System.out.print(" ");
						d++;
					}
					System.out.print(board.boardCells.get(count).type + " ");
					count++;
			}
			d=0;
			while(d<(n-k)){
				System.out.print(" ");
				d++;
			}
			System.out.println();
			d=0;
			// Manage hex positioning 
			if (i < n-1){k++;} else {k--;}
		}
		
	}
}