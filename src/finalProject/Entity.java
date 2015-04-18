package finalProject;

import java.awt.Graphics;
import java.util.Random;

public abstract class Entity {
	public Point position = new Point();
	protected Sprite sprite = null;	
	
	public abstract void update();
	public void draw(Graphics g) {
		
		if(sprite != null) {
			sprite.draw(g, (int)(position.x - Game.cameraPosition.x), (int)(position.y - Game.cameraPosition.y));
			g.drawRect((int)(position.x - Game.cameraPosition.x), (int)(position.y - Game.cameraPosition.y),sprite.width, sprite.height);
		}
	}
	public boolean checkCollision(Entity other) {
		
		return false;
	}
	public abstract void onCollide(Entity other);
}
