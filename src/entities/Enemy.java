package entities;

import graphics.Animation;

import java.util.Random;

public class Enemy extends Fighter {
	
	
	Animation walk_right = new Animation("src/images/walk_zombie_right.png",66,94,11);
	Animation walk_left = new Animation("src/images/walk_zombie_left.png",66,94,11);
	
	Animation currentAnimation = walk_left;
	double speed = .05;
	Random rand = new Random();
	Attack attack;
	public Enemy(){
		setPosition(450,200);
		sprite = walk_left.currentSprite();
		collider.width = sprite.getWidth();
		collider.height = sprite.getHeight();
		 attack = new Attack(this,position, sprite.getWidth(), sprite.getHeight());
		 attack.temporary = false;
	}
	public void setPosition(){
		position.x = 450;
		position.y = rand.nextInt(500);
	}
	public void setPosition(double x,double y){
		position.x = x;
		position.y = y;
	}
	
	@Override
	public void move() {
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
	void die() {
		setPosition();
		health = starterHealth;
	}

}
