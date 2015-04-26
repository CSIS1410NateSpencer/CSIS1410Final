package finalProject;

import java.util.Comparator;

import maths.Point;
import entities.Entity;

public final class CompareByDistanceToTarget implements Comparator<Entity> {
	
	Entity target;
	public CompareByDistanceToTarget(Entity target) {
		this.target = target;
	}
	@Override
	public int compare(Entity a, Entity b) {
		if(Point.distance(a.position, target.position) > Point.distance(b.position, target.position))
			return 1;
		else if(Point.distance(a.position, target.position) < Point.distance(b.position, target.position))
			return -1;
		else return 0;
	}
}
