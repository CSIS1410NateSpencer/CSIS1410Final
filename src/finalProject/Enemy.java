package finalProject;

import java.util.Random;

public class Enemy extends Entity implements Fighter {
	//public Point position = new Point();
	Animation animation = new Animation("src/images/walk_zombie_right.png",66,94,11);
	double speed = 1;
	private int health;
	Random rand = new Random();
	
	public void setPosition(){
		position.x = rand.nextInt(10)+ 300;
	}
	
	@Override
	public void move() {
		// make enemies slower
		position.x+= speed / 2;
		
	}

	@Override
	public void attack() {
		
	}

	@Override
	public void takeDamage(int amount) {
		health-=amount;
		if(amount <=0)
			die();
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		sprite = animation.currentSprite();
		move();
		
	}

	@Override
	public void onCollide(Entity other) {
		// TODO
		
	}

}
