import java.io.PrintStream;
import java.util.*;

/**
 * The Board class contains a collection of cell objects in an array and 
 * contains the suitable algorithms to check for tripods and loops in the 
 * current board configuration.
 */

public class Board {
	// Initialize variables for board configuration
	public int boardSize;
	public int cellCount = 0;
	public int boardCount = 0;
	public int num_B = 0;
	public int num_W = 0;
	ArrayList<Cell> boardCells = new ArrayList<Cell>();
	String colourWon;
	public final static int NEIGHBOR_MAX = 6;
	
	
	// Initialize edge nodes for tripod checking
	public ArrayList<String> edges = new ArrayList<String>(),
	top = new ArrayList<String>(), bot = new ArrayList<String>(), 
	top_r = new ArrayList<String>(), top_l = new ArrayList<String>(), 
	bot_l = new ArrayList<String>(), bot_r = new ArrayList<String>();
	
	// Empty Constructor 
	Board(){}
	
	// Initializes a board dimension from the first integer input
	Board(int n){
		this.boardSize = n;
		this.boardCount = 0;
	}
	
	public void addCell(int index, Cell curr){
		this.boardCells.add(index, curr);
		this.boardCount = boardCells.size();
	}
	
	/**
	 * Function to find a cell in an array list. 
	 * @param x | Row integer of cell position. 
	 * @param y | Column integer of cell position.
	 * @param list | An array list of cells. 
	 * @return
	 */
	public Cell findCell(int x, int y, ArrayList<Cell> list){
		// Assigns a new cell object which all attributes have null values  
		Cell temp = new Cell();
		for (Cell c : list) {
			// Assigns the cell object to the searched cell object in the list
			if (c.row == x && c.col == y){ temp = c; }
		}
		// Returns a cell object with null values if not found in the list
		return temp;
	}
	
	public static void adjacencySweep(Board board, Cell current){
		int i = 0;
		//while(i<board.boardCount){
			// Only wants to compare row above and below current
			//if((board.boardCells[i].x >= current.x-1) && (board.boardCells[i].x <= current.x+1)){
				//if((isAdjacent(current, board.boardCells[i])==true) && 
				//(current.type.equals(board.boardCells[i].type))
				//){
					//current.neighborList.add(board.boardCells[i]);
				//	current.neighborCount += 1;
			//	}
		//	}
		//	i++;
	//	}
			
	}
	
	//public static void addNeighborList(Board board, Cell curr){
		//int i = 0;
		//int x = curr.row;
		//int y = curr.col;
		//String t = curr.type;
		//while(i<NEIGHBOR_MAX){
		// Only compare nodes surrounding the current cell in the board
		//	Cell temp = new Cell();
			//temp.cellCopy(board.boardCells.get(i));
			// Is it the same cell node?
		//	if(!curr.equals(temp) && isSurround(curr,temp)==true){ 
			//	curr.addNeighbor(temp);
		//	}
	//	}
	//}
	
	/**
	 * Function to add neighbor cells into the current cell's neighbor list which are 
	 * adjacent to the current cell. 
	 * @param board | Board object of the current board state/configuration. 
	 * @param curr | Cell object in the board configuration. 
	 */
	public void addNeighborList(Cell curr){
		ArrayList<int[]> neighborCoords = getNeighbors(curr);
		
		// Adds the cell into the current cells neighbor list
		for (int[] coord: neighborCoords){	
			curr.addNeighbor(findCell(coord[0], coord[1], this.boardCells));
		}
	}
	
	/**
	 * Function to get a list of all the coordinates of the current cells coordinates. 
	 * @param c | A cell object which is called to get it's neighbor coordinates. 
	 * @return An array list of integer arrays which is the list of neighbor coordinates. 
	 */
	public ArrayList<int[]> getNeighbors(Cell c){
		ArrayList<int[]> neighbors = new ArrayList<int[]>();
		
		// Add all the coordinates of the current cell's neighbors in integer arrays
		int[] temp1 = {c.row-1,c.col-1}, temp2 = {c.row-1, c.col}, temp3 = {c.row, c.col-1}, 
		temp4 = {c.row, c.col+1}, temp5 = {c.row+1, c.col}, temp6 = {c.row+1, c.col+1};
		
		// Add all the coordinates of the current cell's neighbors in a list 
		neighbors.add(temp1); neighbors.add(temp2); neighbors.add(temp3);
		neighbors.add(temp4); neighbors.add(temp5); neighbors.add(temp6);
		return neighbors;
	}
	
	public static boolean isAdjacent(Cell previous, Cell current){
	       
        if(current.row == previous.row && current.col == previous.col){
        	return false;
        } else if(
                (current.row - previous.row <= 1) && (current.row - previous.row >= 0)
                &&
                (current.col - previous.col <= 1) && (current.col - previous.col >= 0)
        ){
        	return true;
        } else if(
                (current.row - previous.row <= 0) && (current.row - previous.row >= -1)
                &&
                (current.col - previous.col <= 0) && (current.col - previous.col >= -1)
        ){
        	return true;
        } else if((current.row - previous.row <= 0) && (current.row - previous.row >= -1)
                        &&
                        (current.col - previous.col <= 1) && (current.col - previous.col >= 0)
        ){
        	return true;
        } else if((current.row - previous.row <= 1) && (current.row - previous.row >= 0)
                        &&
                        (current.col - previous.col <= 0) && (current.col - previous.col >= -1)
        ){
        	return true;      
        } else {                  
            return false;
        }
	}
	
	public static boolean isNeighbour(String currentColour, Board board, Cell current){
		int i = 0;
		while(i<board.boardCount){
			//Only wants to compare row above and below current
			//if((board.boardCells[i].x >= current.x-1) && (board.boardCells[i].x <= current.x+1)){
				
			//	if((isAdjacent(current, board.boardCells[i])==true)){
					
				//	if(currentColour.equals(board.boardCells[i].type)){
						return true;
					}	
			//		else{
						return false;
					//}
			//	}
		//	}
			//i++;
	//	}
		//return false;
	//	}
	}
	
	public static boolean isSurround(Cell c, Cell t){
		if 
			// Check if top left cell
			(((c.row-1 == t.row) && (c.col-1 == t.col)) ||
			// Check if top right cell 	
			((c.row-1 == t.row) && (c.col+1 == t.col)) ||
			// Check if left cell	
			((c.row == t.row) && (c.col-1 == t.col)) ||
			// Check if right cell
			((c.row == t.row) && (c.col+1 == t.col)) ||
			// Check if bottom left cell
			((c.row+1 == t.row) && (c.col == t.col)) ||
			// Check if bottom right cell
			((c.row+1 == t.row) && (c.col+1 == t.col)))
			{return true;}
		else {return false;}
	}
	
	/**
	 * Function to check if node is a corner cell.  
	 */
	public boolean isCorner(int x, int y){
		if (x == 0 && y == 0 || x == 0 && y == boardSize-1 ||
			x == boardSize-1 && y == 0 ||
			x == boardSize-1 && y == 2*boardSize-2 ||
			x == 2*boardSize-2 && y == boardSize-1 || 
			x == 2*boardSize-2 && y == boardSize-1){ return true; 
		} else { return false; }
	}
	

	/**
	 * Function to generate board edge nodes. There will be
	 * six ranges of edge nodes: Top, top left, top right, bottom left
	 * bottom right and bottom.   
	 */
	public void generateEdgeNodes(int n){
		int i, j=n;
		
		// Populate top and top left range
		for(i=1;i<n-1;i++){ 
			String temp1 = "0,"+i;
			String temp2 = i+",0";
			edges.add(temp1);edges.add(temp2);			
			top.add(temp1);
			top_l.add(temp2);
		}
		
		// Populate bottom left and top right range
		for(i=1;i<n-1;i++){ 
			String temp1 = j+","+i;
			String temp2 = i+","+j;
			edges.add(temp1);edges.add(temp2);	
			bot_l.add(temp1);
			top_r.add(temp2);
			j++;
		}
		
		// Change value
		j = (2*n-2);
		// Populate bottom and bottom right range
		for(i=n;i<j;i++){
			String temp1 = i+","+j;
			String temp2 = j+","+i;
			edges.add(temp1);edges.add(temp2);	
			bot_r.add(temp1);
			bot.add(temp2);
		}
	}
	
	public void toString(ArrayList<String> inp){
		for(int i = 0; i < inp.size(); i++){
            System.out.println(inp.get(i));
        }
	}
	
	/**
	 * Tripod rule checking function using board edge nodes
	 * and recursive traversing through the connecting neighbor
	 * nodes.
	 */
	public boolean checkTripod(NewGraph g){
		// Flags to check if an edge has been reached
		boolean top = false;
		boolean bot = false;
		boolean top_left = false;
		boolean top_right = false;	
		boolean bot_left = false;
		boolean bot_right = false;
		int i, counter = 0;
		Stack<String> checkedNodes = new Stack<String>();
		
		// Check parent nodes for connecting graphs 
		for (Cell c: g.rootList){
			String temp = c.row+","+c.col;
			// Push the parent node into the stack 
			checkedNodes.push(temp);
			if (this.isCorner(c.row,c.col)){
				 // Do nothing 
			} else if (this.top.contains(temp) && top == false){ 
				top = true;
				counter++;
			} else if(this.bot.contains(temp) && bot == false){ 
				bot = true;
				counter++;
			} else if (this.top_l.contains(temp) && top_left == false){
				top_left = true;
				counter++;
			} else if(this.top_r.contains(temp) && top_right == false){
				top_right = true;
				counter++;
			} else if(this.bot_l.contains(temp) && bot_left == false){
				bot_left = true;
				counter++; 
			} else if(this.bot_r.contains(temp) && bot_right == false){
				bot_right = true;
				counter++; }
			if (counter == 3){
				this.colourWon = c.type;
				return true;
			}
			
			// Traverse through the neighbors and get their board positions
			for (i=0;i<c.neighborList.size();i++){   
				// Get the first neighbor found in the list  
				temp = c.neighborList.get(i).row+","+c.neighborList.get(i).col;
				// Push the neighboring node into the stack  
				checkedNodes.push(temp);
				// Check if three edges/sides has already been touched
				if (this.top.contains(temp) && top == false){ 
					top = true;
					counter++;
				} else if(this.bot.contains(temp) && bot == false){ 
					bot = true;
					counter++;
				} else if (this.top_l.contains(temp) && top_left == false){
					top_left = true;
					counter++;
				} else if(this.top_r.contains(temp) && top_right == false){
					top_right = true;
					counter++;
				} else if(this.bot_l.contains(temp) && bot_left == false){
					bot_left = true;
					counter++; 
				} else if(this.bot_r.contains(temp) && bot_right == false){
					bot_right = true;
					counter++; }
			}
		}
		return false;
	}
	
	 public void printBoard(Board board, int n){
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
	
	/**
	* The printboard we tried to implement today (20/5/14). 
	* @param output
	*/
	//public void printBoard(PrintStream output){
		//int i=0;
		//while(i<this.boardCells.size()){
		//	System.out.println(output.toString());
		//	i++
		//}
//	}
	
	/**
	 * simpleLoopCheck - A primitive, and currently malfunctioning way to check for loops. With each iteration, intended to run from the start of 
	 * board and find the next adjacent cell to current.
	 * Was designed to be recursive, to handle each detached configuration of cells and their respective root nodes.
	 * Prints out colour and loop if confirmed.
	 * Currently having issues running correctly alongside the Board class
	 */
	//public static boolean simpleLoopCheck(Board adjBoard){
		//find loop, last minute rush, abandoning graph idea
		//Cell root = new Cell();
		//Cell previous = new Cell();
		//Cell current = new Cell();
		//LinkedList<Cell> cellList = new LinkedList<Cell>();
		//LinkedList<Cell> exclusionList = new LinkedList<Cell>();
		//int cellListCount = 0;
		//int exclusionListCount = 0;
		
		// String currentColour = adjBoard.boardCells[0].type;
		
		//for(int i = 0; i < adjBoard.boardCount; i++){
			//if(adjBoard.boardCells[i].type.equals(currentColour)){
				
				//if(cellList.isEmpty()==true){
				//	current.cellCopy(adjBoard.boardCells[i]);
					//if(isUnchecked( current, exclusionList, exclusionListCount)==true){

						//previous.cellCopy(current);
						//root.cellCopy(current);
					
					//	cellList.add(root);
						//exclusionList.add(root);
						
						//cellListCount += 1;
						//exclusionListCount += 1;
					//}
					//else{
					//	return false;
					//}
				//}
				//else{
				//	current.cellCopy(adjBoard.boardCells[i]);
					
					//if(isAdjacent(previous,current)==true && 
						//isUnchecked( current, exclusionList, exclusionListCount)==true)
					//{
						
				//		previous.cellCopy(current);
						
						//cellList.addLast(current);
						//exclusionList.add(current);
						
						//cellListCount += 1;
					//	exclusionListCount += 1;
						
					//	i = 0;
				//	}
				//}
			//}
		//}
		
		//if(isAdjacent(previous,root) == true){
			//if(currentColour.equals("B")){
				//System.out.println("BLACK");
			//return true;	
		//}
			//else{
				//System.out.println("WHITE");
			//}
			//System.out.println("LOOP");
			//return true;
		//}
		//else{
		//	cellListCount = 0;
		 //	cellList.clear();
		//	simpleLoopCheck(adjBoard);
		//	return false;
		//}
		
	//}

	/**
	 * isUnchecked - Checks if the cell belongs to exclusion list. It's a primitive function intended for use with simpleLoopCheck
	 */
	public static boolean isUnchecked(Cell current, LinkedList<Cell> cellExclusionList, int cellExclusionListSize){
		int i = 0;
		while(i < cellExclusionListSize){
			if((cellExclusionList.get(i).row == current.row) && (cellExclusionList.get(i).col == current.col)){
				//slot has already been checked
				return false;
			}
			i += 1;
		}
		
		return true;
	}
}