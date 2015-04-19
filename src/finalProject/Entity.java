package finalProject;

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
		
		if(sprite != null) {
			sprite.draw(g, (int)(position.x - Game.cameraPosition.x), (int)(position.y - Game.cameraPosition.y));
			//g.drawRect((int)(position.x - Game.cameraPosition.x), (int)(position.y - Game.cameraPosition.y),sprite.width, sprite.height);
		}
	}

	public static boolean checkCollision(Entity a, Entity b) {
		if (a.position.x + a.sprite.width < b.position.x
				|| a.position.y + a.sprite.height < b.position.y
				|| b.position.x + b.sprite.width < a.position.x
				|| b.position.y + b.sprite.height < a.position.y)
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
		if(sprite != null)
			spriteHeightA = sprite.height;
		if(o.sprite != null)
			spriteHeightB = o.sprite.height;
		
		double ay = position.y + spriteHeightA;
		double by = o.position.y + spriteHeightB;
		if(ay > by)
			return 1;
		if(ay < by)
			return -1;
		return 0;
	}
}
