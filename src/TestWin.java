/* 
 * The main class to handle standard input and validate board for
 * terminal states.
 *
 *	Date modified:  
 */
import java.util.Scanner;

import aiproj.fencemaster.Move;

public class TestWin {
	public static void main(String args[]){  
		// Initialize all relevant variables and class objects
		Scanner scan = new Scanner(System.in);  
    	NewGraph graph = new NewGraph();
    	rkarina player = new rkarina();
    	int x, y, index=0;
    	
    	// Read in top line value, which determines board dimensions
        int n = scan.nextInt(), k=0, j, o=0, d=0, i;
        int boardCount = 0, c=0;
        
        // Set the board size and the edge node ranges
        Board board = new Board(n);
        Board b = new Board();
        board.generateEdgeNodes(n);
        
        // Validation of integer input dimension
        if(n < 5){
    		System.out.print("Please enter dimensions greater than 5");
    		System.exit(0);
        }
        int maxDim = 2*n-2;
        // Loop through each row of board configuration 
        for(i=0; i < (2*n-1); i++){
			// Loop through each column of board configuration 
			for(j=0; j < n + k; j++){
				while(d<(n-k)){
					//System.out.print(" ");
					d++;
				}
				//System.out.print("- ");
			}
			d=0;
			while(d<(n-k)){
				//System.out.print(" ");
				d++;
			}
			//System.out.println();
			d=0;
			// Manage hex positioning 
			if (i < n-1){k++;} else {k--;}
        }
        
        player.init(n,1);    
        int count = player.board.boardCount;
        
        Scanner inp = new Scanner(System.in);
        
        while(count > 0){
        	// Player goes first in the game
        	System.out.println("COMPUTER MOVE");
        	player.makeMove();
        	player.printBoard(System.out);
        	int validMove = 0;
        	while (validMove == 0){
	        	Move tempMove = new Move();
	        	System.out.println("Make a move by typing a row: ");
	        	x = inp.nextInt();
	        	if (x > maxDim || x < 0) { System.out.println("Invalid row input!"); }
	        	tempMove.Row = x;
	        	System.out.println("Make a move by typing a column: ");
	        	y = inp.nextInt();
	        	if (y > maxDim || y < 0) { System.out.println("Invalid column input!"); }
	        	tempMove.Col = y;
	        	
	        	// Check if you've swapped with computer
	        	for (Cell temp: player.board.boardCells){
	        		if (temp.row == x && temp.col == y && temp.type.equals("W")) {
	        			tempMove.IsSwap = true;
	        			break;
	        		} else { tempMove.IsSwap = false; }
	        	}
	        	tempMove.P = 2;
	        	if (player.opponentMove(tempMove)==0) { 
	        		break;
	        	} else { System.out.println("Invalid move, please input another move."); }
	        	
	        	while(index<board.boardCells.size()){
	        		Cell temp = board.boardCells.get(index);
	        		if ((temp.row == x) && (temp.col == y)){
	        			temp.type = "B";
	        		}
	        		index++;
	        	}
	        	index=0;
	        	count--;
        	}
        	
        	System.out.println("HUMAN PLAYER MOVE");
        	player.printBoard(System.out);
        	System.out.print(player.getWinner());
        	System.out.println();
        	
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
		
		// Check if there is the wrong number of coloured pieces in the board 
		if (board.num_W - board.num_B < -1 || board.num_B - board.num_W > 1){
			System.err.println("Invalid number of coloured positions.");
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