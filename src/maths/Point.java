package maths;

public class Point {
	public double x;
	public double y;
	
	public Point(){
		this(0.0,0.0);
	}
	public Point(double x, double y){
		this.x = x; this.y = y;
	}
	
	public static Point zero(){
		return new Point(0.0,0.0);
	}
	public Point multiply(double speed) {
		return new Point(x * speed, y * speed);
	}
	public Point add(Point v) {
		return new Point(x + v.x, y + v.y);
	}

	public Point subtract(Point v) {
		return new Point(x - v.x, y - v.y);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("[%s, %s]",x,y);
	}
	public Point normalized() {
		return new Point(x / magnitude(),y / magnitude());
	}
	
	public double magnitude(){
		return Math.sqrt(x * x + y * y);
	}
}
