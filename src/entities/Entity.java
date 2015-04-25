package entities;

import state.PlayState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import state.PlayState;
import maths.Point;

public abstract class Entity implements Comparable<Entity>{
	public Point position = new Point();
	protected BufferedImage sprite = null;	
	
	protected Collider collider;
	
	public Entity() {
		collider = new Collider(this, 0, 0, 0, 0);
		PlayState.add(this);
	}
	
	public Entity(Point position, int width, int height) {
		
	}
	public abstract void update();
	public final void draw(Graphics g) {
		int tx = PlayState.tileMap.getx();
		int ty = PlayState.tileMap.gety();
		if(sprite != null) {
			g.drawImage(sprite, (int)(tx + position.x - sprite.getWidth() / 2), (int)(ty + position.y - sprite.getHeight() / 2),null);
		}
		g.drawRect((int)(tx + collider.getPosition().x - collider.width / 2), (int)(ty + collider.getPosition().y - collider.height / 2),(int)collider.width, (int)collider.height);
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
	public BufferedImage getSprite() {
		return sprite;
	}
	void destroy() {
		PlayState.remove(this);
	}
	
	
}
