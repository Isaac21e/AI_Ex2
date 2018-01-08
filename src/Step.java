/**
 * Created by Isaac on 1/5/2018.
 */
public class Step {

	private Point _location;
	private char _state;

	public Step(Point point,char state){
		_location=point;
		_state=state;
	}

	public Point GetLocation(){
		return _location;
	}
	public char GetState(){
		return _state;
	}


}
