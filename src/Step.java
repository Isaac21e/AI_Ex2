/**
 * Created by Isaac on 1/5/2018.
 */
public class Step {

	private Point _location;
	private CellState _state;

	public Step(Point point,CellState state){
		_location=point;
		_state=state;
	}

	public Point GetLocation(){
		return _location;
	}
	public CellState GetState(){
		return _state;
	}


}
