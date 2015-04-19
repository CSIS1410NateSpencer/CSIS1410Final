package entities;

import graphics.Animation;

import java.util.Random;

public class Enemy extends Fighter {
	
	
	Animation walk_right = new Animation("src/images/walk_zombie_right.png",66,94,11);
	Animation walk_left = new Animation("src/images/walk_zombie_left.png",66,94,11);
	Animation currentAnimation = walk_left;
	double speed = .05;
	Random rand = new Random();
	
	public Enemy(){
		health = 500;
		setPosition(450,200);
	}
	public void setPosition(){
		position.x = 450;
		position.y = rand.nextInt(500);
	}
	public void setPosition(double x,double y){
		position.x = x;
		position.y=y;
	}
	
	@Override
	public void move() {
		// make enemies slower
		position.x-= speed;
		
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
		if(other instanceof AttackEntity && ((AttackEntity)other).sender != this)
			takeDamage(1);
	}

	@Override
	void die() {
		setPosition();
		health = 120;
	}

}
