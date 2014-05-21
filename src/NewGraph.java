import java.util.ArrayList;
import java.util.LinkedList;

/**
 * NewGraph Class
 * A collection of root cells signifying where adjacent nodes exist.
 * Size indicates the number of root nodes of either color on the board. 
 * @author Rahmadhy Karina and Bradley Jackson
 *
 */

public class NewGraph {
	LinkedList<Cell> rootList;
	public int size;

	
	public NewGraph() {
		this.rootList= new LinkedList<Cell>();
		this.size = 1;
	}
	
	public Cell getRoot(int i){
		return this.rootList.get(i);
	}
	
	public void toString(LinkedList<Cell> inp){
		for(int i = 0; i < inp.size(); i++){
            inp.get(i).cellData();
        }
	}
	
	
}

