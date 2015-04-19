package finalProject;

public enum Direction {
	Up,Down,Left,Right;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
	
	public int getValue(int i) {
		if(this == Up || this == Left)
			return -i;
		else return i;
	}
}
