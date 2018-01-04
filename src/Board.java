/**
 * Created by Isaac on 1/5/2018.
 */
public class Board {

	CellState[][] _actualBoard;
	int _size;

	public Board(CellState[][] actualBoard, int size){
		_actualBoard=actualBoard;
		_size=size;
	}

	public boolean IsValid(Step step){
		int i=step.GetI();
		int j=step.GetJ();
		if(i<0||i==_size||j<0||j==_size||_actualBoard[i][j]!= CellState.EMPTY)
			return false;
		if((i>0&&_actualBoard[i-1][j]!=CellState.EMPTY) //up != Empty
				   ||(i<(_size-1)&&_actualBoard[i+1][j]!=CellState.EMPTY) //down != Empty
				   ||(j>0&&_actualBoard[i][j-1]!=CellState.EMPTY) //left != Empty
				   ||(j<(_size-1)&&_actualBoard[i][j+1]!=CellState.EMPTY)//right != Empty
				   ||(i>0&&j>0&&_actualBoard[i-1][j-1]!=CellState.EMPTY) // upleft != Empty
				   ||(i>0&&j<(_size-1)&&_actualBoard[i-1][j+1]!=CellState.EMPTY)// upright != Empty
				   ||(i<(_size-1)&&j<(_size-1)&&_actualBoard[i+1][j+1]!=CellState.EMPTY)// downright != Empty
				   ||(i<(_size-1)&&j>0&&_actualBoard[i+1][j-1]!=CellState.EMPTY)) //downlefr != Empty
		{
			return true;
		}
		return false;
	}
	public Board MakeStep(Step step){

	}
}
