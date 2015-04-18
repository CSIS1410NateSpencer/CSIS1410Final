package finalProject;

import java.util.Random;

public class Enemy extends Fighter {
	
	
	Animation walk_right = new Animation("src/images/walk_zombie_right.png",66,94,11);
	Animation walk_left = new Animation("src/images/walk_zombie_left.png",66,94,11);
	Animation currentAnimation = walk_left;
	double speed = .1;
	Random rand = new Random();
	
	public Enemy(){
		health = 500;
		setPosition(450,200);
	}
	public void setPosition(){
		position.x = 90;
		position.y = rand.nextInt(500);
	}
	public void setPosition(double x,double y){
		position.x = x;
		position.y=y;
	}
	
	@Override
	public void move() {
		// make enemies slower
		position.x-= speed / 2;
		
	}

	@Override
	public void attack() {
		
	}

	@Override
	public void update() {
		sprite = currentAnimation.currentSprite();
		move();
		
	}

	@Override
	public void onCollide(Entity other) {
		// TODO
		
	}

	@Override
	void die() {
		setPosition();
		health = 120;
	}

}
