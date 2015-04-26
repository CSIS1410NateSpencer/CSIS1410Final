package finalProject;

import java.util.Comparator;

import maths.Vector2;
import entities.Entity;

public final class CompareByDistanceToTarget implements Comparator<Entity> {
	
	Entity target;
	public CompareByDistanceToTarget(Entity target) {
		this.target = target;
	}
	@Override
	public int compare(Entity a, Entity b) {
		if(Vector2.distance(a.position, target.position) > Vector2.distance(b.position, target.position))
			return 1;
		else if(Vector2.distance(a.position, target.position) < Vector2.distance(b.position, target.position))
			return -1;
		else return 0;
	}
}
