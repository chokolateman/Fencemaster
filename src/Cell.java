/***************************************************************************
 * 
 * Class to represent a board piece for the FenceMaster board game session.
 * 
 * @author Rahmadhy Karina (585592) and Bradley Jackson (587163)
 * 
***************************************************************************/

import java.util.LinkedList;
// Class to represent each unique board slots/cells

public class Cell {
	//Variables for cell dimensions, colour type and 
	//a flag to check if it is an already visited node
	public int row;
	public int col;
	public String type;
	private boolean visited = false; 
	
	public int neighborCount = 0;
	LinkedList<Cell> neighborList;
	
	/*This variable will be used later for minimax search*/
	private int value;
	
	// Constructor to create a new cell object
	public Cell(int x, int y, String type){
		this.row = x;
		this.col = y;
		this.type = type;
		this.neighborList = new LinkedList<Cell>();
	}
	
	// Constructor 
	public Cell(){
		
	}
	
	/**
	 * Copies all the data of a cell into the current cell object.  
	 * @param source The cell that needs copying.
	 */
	public void cellCopy(Cell source){
		this.row = source.row;
		this.col = source.col;
		this.type = source.type;
		this.neighborList = source.neighborList;
	}
	
	/**
	 * Prints all the cell neighbors.  
	 */
	public void printNeighbours(){
		System.out.println(this.neighborCount + " neighbours");
		if(this.neighborCount>0){
			for(int i = 0; i < this.neighborCount; i++){
				this.neighborList.get(i).cellData();
			}
		}	
	}
	
	/**
	 * Add neighboring cells into a list. 
	 * @param c | A cell object that needs to be added to the neighbor list.
	 */
	public void addNeighbor(Cell c){
		neighborList.add(c);
		this.neighborCount++;
	}
	
	/**
	 * Prints the cell data.  
	 */
	public void cellData(){
		System.out.println(row + "th row, " + col + "th col, " + type);
	}
	
	/**
	 * Checks to see if the cell is already visited or not. 
	 * @return True or False
	 */
	public boolean isChecked(){
		if (this.visited==true)
		{return true;}
		else{return false;}
	}
	
	/**
	 * Gets the type of the cell. 
	 * @return Cell type. 
	 */
	public String getType(){
		return this.type;
	}
}
