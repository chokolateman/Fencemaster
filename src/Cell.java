import java.util.LinkedList;
// Class to represent each unique board slots/cells

public class Cell {
	//Variables for cell dimensions, colour type and 
	//a flag to check if it is an already visited node
	public int x;
	public int y;
	public String type;
	private boolean visited = false; 
	
	public int neighborCount = 0;
	LinkedList<Cell> neighborList;
	
	// Constructor to create a new cell object
	public Cell(int x, int y, String type){
		this.x = x;
		this.y = y;
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
		this.x = source.x;
		this.y = source.y;
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
	 * @param c A cell object that needs to be added to the neighbor list.  
	 */
	public void addNeighbor(Cell c){
		neighborList.add(c);
		this.neighborCount++;
	}
	
	/**
	 * Prints the cell data.  
	 */
	public void cellData(){
		System.out.println(x + "th row, " + y + "th col, " + type);
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
