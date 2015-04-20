package entities;

import finalProject.Game;
import finalProject.Point;
import graphics.Sprite;

import java.awt.Graphics;
import java.util.Random;

public abstract class Entity implements Comparable<Entity>{
	public Point position = new Point();
	protected Sprite sprite = null;	
	
	protected Collider collider;
	
	public Entity() {
		collider = new Collider(this, 0, 0, 0, 0);
		Game.add(this);
	}
	
	public Entity(Point position, int width, int height) {
		
	}
	public abstract void update();
	public final void draw(Graphics g) {
		
		if(sprite != null) {
			sprite.draw(g, (int)(position.x - Game.cameraPosition.x), (int)(position.y - Game.cameraPosition.y));
		}
		g.drawRect((int)(position.x - Game.cameraPosition.x), (int)(position.y - Game.cameraPosition.y),(int)collider.width, (int)collider.height);
	}

	public static void checkCollision(Entity a, Entity b) {
		if(Collider.intersects(a.collider,b.collider)) {
			a.onCollide(b);
			b.onCollide(a);
		}
	}
	public abstract void onCollide(Entity other);
	
	@Override
	public int compareTo(Entity o) {
		int spriteHeightA = 0;
		int spriteHeightB = 0;
		if(sprite != null)
			spriteHeightA = sprite.getHeight();
		if(o.sprite != null)
			spriteHeightB = o.sprite.getHeight();
		
		double ay = position.y + spriteHeightA;
		double by = o.position.y + spriteHeightB;
		if(ay > by)
			return 1;
		if(ay < by)
			return -1;
		return 0;
	}
	public Sprite getSprite() {
		return sprite;
	}
	void destroy() {
		Game.remove(this);
	}
}
