package finalProject;

public class Clock {
	public static final long NANOS_PER_SECOND = 10000000;
	long startTime = System.nanoTime();
	long currentTime = startTime;
	double elapsed;
	
	public void tick(){
		currentTime = System.nanoTime();
		elapsed = currentTime - startTime;
	}
	
	public void reset(){
		startTime = System.nanoTime();
	}
	public double getElapsed() {
		return elapsed;
	}
}