package entities;

import maths.Vector2;

public class Collider {
	
	Entity attachedTo;
	Vector2 offset = Vector2.zero();
	private int width;
	private int height;
	
	
	public Collider(Entity attachedTo, int x, int y) {
		this(attachedTo,x,y,0,0);
		if(attachedTo.sprite != null) {
			setWidth(attachedTo.sprite.getWidth());
			setHeight(attachedTo.getSprite().getWidth());
		}
	}
	public Collider(Entity attachedTo, int x, int y,int width, int height) {
		this.attachedTo = attachedTo;
		offset.x = x;
		offset.y = y;
		this.setWidth(width);
		this.setHeight(height);
	}
	
	//checks for intersection between entities
	public static boolean intersects(Collider a, Collider b) {
		Vector2 aPos = a.getPosition();
		Vector2 bPos = b.getPosition();
		if (aPos.x + a.getWidth() < bPos.x
				|| aPos.y + a.getHeight() < bPos.y
				|| bPos.x + b.getWidth() < aPos.x
				|| bPos.y + b.getHeight() < aPos.y)
			return false;
		return true;
	}
	
	public Vector2 getPosition() {
		return new Vector2(attachedTo.position.x + offset.x, attachedTo.position.y + offset.y);
	}
	public Vector2 getCenter() {
		return new Vector2(attachedTo.position.x + offset.x + (getWidth() / 2), attachedTo.position.y + offset.y + (getHeight() / 2));
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
