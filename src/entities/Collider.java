package entities;

import finalProject.Point;

public class Collider {
	
	Entity attachedTo;
	Point position = Point.zero();
	int width;
	int height;
	
	
	public Collider(Entity attachedTo, int x, int y) {
		this(attachedTo,x,y,0,0);
		if(attachedTo.sprite != null) {
			width = attachedTo.sprite.getWidth();
			height = attachedTo.getSprite().getWidth();
		}
	}
	public Collider(Entity attachedTo, int x, int y,int width, int height) {
		this.attachedTo = attachedTo;
		position.x = x;
		position.y = y;
		this.width = width;
		this.height = height;
	}
	
	public static boolean intersects(Collider a, Collider b) {
		if (a.position.x + a.attachedTo.position.x + a.width < b.position.x + b.attachedTo.position.x
				|| a.position.y + a.attachedTo.position.y + a.height < b.position.y + b.attachedTo.position.y
				|| b.position.x + b.attachedTo.position.x + b.width < a.position.x + a.attachedTo.position.x
				|| b.position.y + b.attachedTo.position.y + b.height < a.position.y + a.attachedTo.position.y)
			return false;
		return true;
	}
}
