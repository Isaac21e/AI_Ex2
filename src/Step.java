/**
 * Created by Isaac on 1/5/2018.
 */
public class Step {

	private int _i;
	private int _j;
	private CellState _state;

	public Step(int i, int j,CellState state){
		_i=i;
		_j=j;
		_state=state;
	}

	public int GetI(){
		return _i;
	}
	public int GetJ(){
		return _j;
	}
	public CellState GetState(){
		return _state;
	}


}
