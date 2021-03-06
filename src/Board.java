import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;

/**
 * Tha board class.
 * The board class represents an matix of chars, with 'Empty', 'Black' or 'White' in its cells
 * it makes calculates on the board and returns a new board when makeing a move.
 * actually, the board is a 'screen shot' of a the game in a certain moment.
 */
public class Board {

	private char[][] _actualBoard;
	private int _size;
	private int _numOfBlacks;
	private int _numOfWhites;
	private int _numBlacksOnBorder;
	private int _numWhitesOnBorder;




	/**
	 * constructor
	 * @param actualBoard the board (char[][])
	 * @param size size of board (size==width==height)
	 */
	public Board(char[][] actualBoard, int size){
		_actualBoard=actualBoard;
		_size=size;
	}

	/**
	 * calculates and updates members (num of black, whites, black on border and whites on border)
	 */
	public void CalcStates(){
		_numOfBlacks=0;
		_numOfWhites=0;
		_numBlacksOnBorder=0;
		_numWhitesOnBorder=0;
		for(int i=0;i<_size;i++){
			for(int j=0;j<_size;j++){
				if(_actualBoard[i][j]== Enum.BLACK){
					//found a black cell
					_numOfBlacks++;
				}
				if(_actualBoard[i][j]== Enum.WHITE){
					//found a white cell
					_numOfWhites++;
				}
				if(i==0 || j==0 || i==_size-1 || j==_size-1){
					//in border
					if(_actualBoard[i][j]== Enum.BLACK){
						//found a black cell in border
						_numBlacksOnBorder++;
					}
					if(_actualBoard[i][j]== Enum.WHITE){
						//found a white cell in border
						_numWhitesOnBorder++;
					}
				}

			}
		}

	}

	/**
	 * check whether a step is valid
	 * @param step step to do
	 * @return true if valid otherwise false
	 */
	private boolean IsValid(Step step){
		int i=step.GetLocation().GetI();
		int j=step.GetLocation().GetJ();
		if(i<0||i==_size||j<0||j==_size||_actualBoard[i][j]!= Enum.EMPTY)
			return false;
		if((i>0&&_actualBoard[i-1][j]!= Enum.EMPTY) //up != Empty
				   ||(i<(_size-1)&&_actualBoard[i+1][j]!= Enum.EMPTY) //down != Empty
				   ||(j>0&&_actualBoard[i][j-1]!= Enum.EMPTY) //left != Empty
				   ||(j<(_size-1)&&_actualBoard[i][j+1]!= Enum.EMPTY)//right != Empty
				   ||(i>0&&j>0&&_actualBoard[i-1][j-1]!= Enum.EMPTY) // upleft != Empty
				   ||(i>0&&j<(_size-1)&&_actualBoard[i-1][j+1]!= Enum.EMPTY)// upright != Empty
				   ||(i<(_size-1)&&j<(_size-1)&&_actualBoard[i+1][j+1]!= Enum.EMPTY)// downright != Empty
				   ||(i<(_size-1)&&j>0&&_actualBoard[i+1][j-1]!= Enum.EMPTY)) //downlefr != Empty
		{
			return true;
		}
		return false;
	}
	public List<Step> GetValidSteps(char color){
		List<Step> steps= new LinkedList<Step>();
		for(int i=0;i<_size;i++){
			for(int j=0; j<_size;j++){
				Step step= new Step(new Point(i,j),color);
				if(IsValid(step))
					steps.add(step);
			}
		}
		return steps;
	}
	public char GameEnded(){
		this.CalcStates();
		if(this._numOfWhites + this._numOfBlacks == this._size * this._size){
			if(this._numOfWhites > this._numOfBlacks){
				return Enum.WHITE;
			}
			if(this._numOfWhites < this._numOfBlacks){
				return Enum.BLACK;
			}
		}
		return Enum.NOT_ENDED;
	}

	public int Heuristic(){
		CalcStates();
		if(_numOfWhites == _numOfBlacks){
			return 0;
		}
		if(this.GameEnded() == Enum.BLACK ){
			return Integer.MAX_VALUE;
		}
		if(this.GameEnded() == Enum.WHITE ){
			return Integer.MIN_VALUE;
		}
		return (this._numOfBlacks - this._numOfWhites) + (this._numBlacksOnBorder - this._numWhitesOnBorder);
	}

	/**
	 * return Board after making step
	 * @param step step to make
	 * @return returns board after making step. if invalid returns null.
	 */
	public Board MakeStep(Step step){
		if(!IsValid(step)){
			return null;
		}
		char newBoard[][]=this.CloneBoard();
		//change cell's state
		newBoard[step.GetLocation().GetI()][step.GetLocation().GetJ()]=step.GetState();
		CheckUP(newBoard,step);
		CheckDown(newBoard,_size,step);
		CheckLeft(newBoard,step);
		CheckRight(newBoard,_size,step);
		CheckUpLeft(newBoard,_size,step);
		CheckUpRIght(newBoard,_size,step);
		CheckDownRIght(newBoard,_size,step);
		CheckDownLeft(newBoard,_size,step);
		return new Board(newBoard,_size);
	}
	public void Print(){
		for(int i=0;i<_size;i++){
			for(int j=0;j<_size;j++){
				System.out.print(_actualBoard[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	private void CheckUP(char[][] board,Step step){
		int i=step.GetLocation().GetI()-1;
		int j= step.GetLocation().GetJ();
		List<Point> toChange= new LinkedList<>();
		boolean doChange=false;
		while(i>=0){
			toChange.add(new Point(i,j));
			if(board[i][j]==step.GetState()){
				doChange=true;
				break;
			}
			i--;
		}
		if(doChange){
			for (Point p:toChange){
				board[p.GetI()][p.GetJ()]=step.GetState();
			}
		}
	}
	private void CheckDown(char[][] board, int size,Step step){
		int i=step.GetLocation().GetI()+1;
		int j= step.GetLocation().GetJ();
		List<Point> toChange= new LinkedList<>();
		boolean doChange=false;
		while(i<size){
			toChange.add(new Point(i,j));
			if(board[i][j]==step.GetState()){
				doChange=true;
				break;
			}
			i++;
		}
		if(doChange){
			for (Point p:toChange){
				board[p.GetI()][p.GetJ()]=step.GetState();
			}
		}
	}
	private void CheckRight(char[][] board,int size,Step step){
		int i=step.GetLocation().GetI();
		int j= step.GetLocation().GetJ()+1;
		List<Point> toChange= new LinkedList<>();
		boolean doChange=false;
		while(j<size){
			toChange.add(new Point(i,j));
			if(board[i][j]==step.GetState()){
				doChange=true;
				break;
			}
			j++;
		}
		if(doChange){
			for (Point p:toChange){
				board[p.GetI()][p.GetJ()]=step.GetState();
			}
		}
	}
	private void CheckLeft(char[][] board,Step step){
		int i=step.GetLocation().GetI();
		int j= step.GetLocation().GetJ()-1;
		List<Point> toChange= new LinkedList<>();
		boolean doChange=false;
		while(j>=0){
			toChange.add(new Point(i,j));
			if(board[i][j]==step.GetState()){
				doChange=true;
				break;
			}
			j--;
		}
		if(doChange){
			for (Point p:toChange){
				board[p.GetI()][p.GetJ()]=step.GetState();
			}
		}
	}
	private void CheckUpRIght(char[][] board,int size,Step step){
		int i=step.GetLocation().GetI()-1;
		int j= step.GetLocation().GetJ()+1;
		List<Point> toChange= new LinkedList<>();
		boolean doChange=false;
		while(j<size && i>=0){
			toChange.add(new Point(i,j));
			if(board[i][j]==step.GetState()){
				doChange=true;
				break;
			}
			j++;
			i--;
		}
		if(doChange){
			for (Point p:toChange){
				board[p.GetI()][p.GetJ()]=step.GetState();
			}
		}
	}
	private void CheckUpLeft(char[][] board,int size,Step step){
		int i=step.GetLocation().GetI()-1;
		int j= step.GetLocation().GetJ()-1;
		List<Point> toChange= new LinkedList<>();
		boolean doChange=false;
		while(j>=0 && i>=0){
			toChange.add(new Point(i,j));
			if(board[i][j]==step.GetState()){
				doChange=true;
				break;
			}
			i--;
			j--;
		}
		if(doChange){
			for (Point p:toChange){
				board[p.GetI()][p.GetJ()]=step.GetState();
			}
		}
	}
	private void CheckDownRIght(char[][] board,int size,Step step){
		int i=step.GetLocation().GetI()+1;
		int j= step.GetLocation().GetJ()+1;
		List<Point> toChange= new LinkedList<>();
		boolean doChange=false;
		while(j<size && i<size){
			toChange.add(new Point(i,j));
			if(board[i][j]==step.GetState()){
				doChange=true;
				break;
			}
			j++;
			i++;
		}
		if(doChange){
			for (Point p:toChange){
				board[p.GetI()][p.GetJ()]=step.GetState();
			}
		}
	}
	private void CheckDownLeft(char[][] board,int size,Step step){
		int i=step.GetLocation().GetI()+1;
		int j= step.GetLocation().GetJ()-1;
		List<Point> toChange= new LinkedList<>();
		boolean doChange=false;
		while(j>=0 && i<size){
			toChange.add(new Point(i,j));
			if(board[i][j]==step.GetState()){
				doChange=true;
				break;
			}
			i++;
			j--;
		}
		if(doChange){
			for (Point p:toChange){
				board[p.GetI()][p.GetJ()]=step.GetState();
			}
		}
	}
	private char[][] CloneBoard(){
		char newBoard[][]= new char[_size][_size];
		for(int i=0;i<_size;i++){
			for(int j=0;j<_size;j++){
				newBoard[i][j]=_actualBoard[i][j];
			}
		}
		return newBoard;

	}



	public void SetCell(Point p, char state){
		this._actualBoard[p.GetI()][p.GetJ()]=state;
	}
	public char GetCell(int i, int j){
		return this._actualBoard[i][j];
	}
	public int GetSize() {
		return _size;
	}

}

