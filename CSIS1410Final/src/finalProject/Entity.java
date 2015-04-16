package finalProject;

import java.awt.Graphics;

public abstract class Entity {
	public Point position = new Point();
	protected Sprite sprite = null;
	public abstract void update();
	public void draw(Graphics g) {
		if(sprite != null) {
			sprite.draw(g, (int)(position.x - TestPanel.cam.x), (int)(position.y - TestPanel.cam.y));
			
			//g.drawRect((int)position.x, (int)position.y, sprite.width, sprite.height);
		}
	}
	public boolean checkCollision(Entity other) {
		return false;
	}
	public abstract void onCollide(Entity other);
}
