package entities;

import finalProject.Game;
import finalProject.Point;
import graphics.Sprite;

import java.awt.Graphics;
import java.util.Random;

public abstract class Entity implements Comparable<Entity>{
	public Point position = new Point();
	protected Sprite sprite = null;	
	
	public Entity() {
		Game.newEntities.add(this);
	}
	public abstract void update();
	public final void draw(Graphics g) {
		
		if(getSprite() != null) {
			getSprite().draw(g, (int)(position.x - Game.cameraPosition.x), (int)(position.y - Game.cameraPosition.y));
			//g.drawRect((int)(position.x - Game.cameraPosition.x), (int)(position.y - Game.cameraPosition.y),sprite.width, sprite.height);
		}
	}

	public static boolean checkCollision(Entity a, Entity b) {
		if (a.position.x + a.getSprite().getWidth() < b.position.x
				|| a.position.y + a.getSprite().getHeight() < b.position.y
				|| b.position.x + b.getSprite().getWidth() < a.position.x
				|| b.position.y + b.getSprite().getHeight() < a.position.y)
			return false;
		a.onCollide(b);
		b.onCollide(a);
		return true;
	}
	public abstract void onCollide(Entity other);
	
	@Override
	public int compareTo(Entity o) {
		int spriteHeightA = 0;
		int spriteHeightB = 0;
		if(getSprite() != null)
			spriteHeightA = getSprite().getHeight();
		if(o.getSprite() != null)
			spriteHeightB = o.getSprite().getHeight();
		
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
}
