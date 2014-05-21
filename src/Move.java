/* Move class as defined in project spec*/

public class Move{
	public int P; /*White or Black move*/
	public int Row; /*X coords*/
	public int Col; /*Y coords*/

	/*IsSwap can only be true for the first move
	by the second player*/
	public boolean IsSwap; 


	public Move(int Row, int Col, int P){
		this.Row = Row;
		this.Col = Col;
		this.P = P;
		/*IsSwap defaults to false unless otherwise specified*/
		this.IsSwap = FALSE;
		}
}