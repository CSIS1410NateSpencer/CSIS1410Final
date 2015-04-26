package finalProject;

import maths.Vector2;

public enum Direction {
	Up,Down,Left,Right;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
	
	public Vector2 getPoint() {
		switch (this) {
		case Up:
			return new Vector2(0, -1);
		case Down:
			return new Vector2(0, 1);
		case Left:
			return new Vector2(-1, 0);
		case Right:
			return new Vector2(1, 0);
		default:
			return null;
		}
	}
	
	public Vector2 getInversePoint() {
		switch (this) {
		case Up:
			return new Vector2(0, 1);
		case Down:
			return new Vector2(0, -1);
		case Left:
			return new Vector2(1, 0);
		case Right:
			return new Vector2(-1, 0);
		default:
			return null;
		}
	}
	
	public double getSign(double i) {
		if(this == Up || this == Left)
			return -i;
		else return i;
	}
}
