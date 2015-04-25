package maths;

public class Maths {
	public static double interpolate(double a, double b, double t){
		return a + (b-a) * t;
	}
	public static double clamp(double base, double lower, double upper){
		if(base < lower)
			return lower;
		if(base > upper)
			return upper;
		return base;
	}
}
