package maths;

public class Vector2 {
	public double x;
	public double y;
	
	public Vector2(){
		this(0.0,0.0);
	}
	public Vector2(double x, double y){
		this.x = x; this.y = y;
	}
	
	public static Vector2 zero(){
		return new Vector2(0.0,0.0);
	}
	public Vector2 multiply(double speed) {
		return new Vector2(x * speed, y * speed);
	}
	public Vector2 add(Vector2 v) {
		return new Vector2(x + v.x, y + v.y);
	}

	public Vector2 subtract(Vector2 v) {
		return new Vector2(x - v.x, y - v.y);
	}
	
	public Vector2 normalized() {
		return new Vector2(x / magnitude(),y / magnitude());
	}
	
	public double magnitude(){
		return Math.sqrt(x * x + y * y);
	}
	
	public static double distance(Vector2 a, Vector2 b){
		return a.subtract(b).magnitude();
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
		if (!(obj instanceof Vector2))
			return false;
		Vector2 other = (Vector2) obj;
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
}
