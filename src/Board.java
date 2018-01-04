/**
 * Created by Isaac on 1/5/2018.
 */
public class Board {

	CellState[][] _actualBoard;

	public Board(CellState[][] actualBoard){
		_actualBoard=actualBoard;
	}

	public boolean isValid(Step step){
		if(_actualBoard[step.GetI()][step.GetJ()]!= CellState.EMPTY)
			return false;


	}
}
