package entities;

import maths.Point;

public class Collider {
	
	Entity attachedTo;
	Point offset = Point.zero();
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
		offset.x = x;
		offset.y = y;
		this.width = width;
		this.height = height;
	}
	
	//checks for intersection between entities
	public static boolean intersects(Collider a, Collider b) {
		if (a.offset.x + a.attachedTo.position.x + a.width < b.offset.x + b.attachedTo.position.x
				|| a.offset.y + a.attachedTo.position.y + a.height < b.offset.y + b.attachedTo.position.y
				|| b.offset.x + b.attachedTo.position.x + b.width < a.offset.x + a.attachedTo.position.x
				|| b.offset.y + b.attachedTo.position.y + b.height < a.offset.y + a.attachedTo.position.y)
			return false;
		return true;
	}
	
	public Point getPosition() {
		return new Point(attachedTo.position.x + offset.x, attachedTo.position.y + offset.y);
	}
	public Point getCenter() {
		return new Point(attachedTo.position.x + offset.x + (width / 2), attachedTo.position.y + offset.y + (height / 2));
	}
}
