/**
 * An Answer class.
 * This class represents am answer of the minmax algorithm. it contains a board and its heuristic.
 */
public class Answer {
	/**
	 * constuctor of answer
	 * @param board the board
	 * @param heuristics the heuristic of the board
	 */
	public Answer(Board board, int heuristics){
		this.board=board;
		this.heuristics=heuristics;
	}

	public Board board;
	public int heuristics;
}
