package finalProject;

public enum Direction {
	Up,Down,Left,Right;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
	
	public Point getPoint() {
		switch (this) {
		case Up:
			return new Point(0, -1);
		case Down:
			return new Point(0, 1);
		case Left:
			return new Point(-1, 0);
		case Right:
			return new Point(1, 0);
		default:
			return null;
		}
	}
	
	public Point getInversePoint() {
		switch (this) {
		case Up:
			return new Point(0, 1);
		case Down:
			return new Point(0, -1);
		case Left:
			return new Point(1, 0);
		case Right:
			return new Point(-1, 0);
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
