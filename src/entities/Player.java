package entities;

import finalProject.Direction;
import finalProject.Game;
import finalProject.Point;
import graphics.Animation;
import graphics.AnimationSet;

import java.awt.event.KeyEvent;

public class Player extends Fighter {
	
	double speed = 2;//in pixels per update
	
	AnimationSet idles;
	private Animation praise = new Animation("src/images/praise_the_sun.png",10);
	
	
	Point startingPoint = new Point(2175,360);
	public Player(){
		initializeHealth(16);
		
		direction = Direction.Down;
		walks = AnimationSet.loadAnimations("walking",8);
		idles = AnimationSet.loadAnimations("idle",8);
		attacks = AnimationSet.loadAnimations("attack",9);
		damage = AnimationSet.loadAnimations("dmg",4);
		die = AnimationSet.loadAnimations("die",14);
		setCurrentAnimationSet(idles);
		sprite = getCurrentAnimationSet().get(direction).currentSprite();
		collider.width = sprite.getWidth();
		collider.height = sprite.getHeight();
		position = new Point(startingPoint.x,startingPoint.y);
	}
	
	@Override
	public void move() {
		velocity = Game.input.getPoint().multiply(speed);
		if(!velocity.equals(Point.zero())) {
			direction = Game.input.getDirection();
			setCurrentAnimationSet(walks);
		}
		else
			setCurrentAnimationSet(idles);

		adjustForCollision();
	}

	

	@Override
	public void attack() {
			setCurrentAnimationSet(attacks);
			attacks.get(direction).play();
	}

	@Override
	public void update() {
		sprite = getCurrentAnimationSet().get(direction).currentSprite();
		
		if(getCurrentAnimationSet().get(direction).isFinished() == true) {
			if(getCurrentAnimationSet() == attacks)
				new Attack(this, position.add(direction.getPoint().multiply(100)),collider.width,collider.height);
			if(getCurrentAnimationSet() == die)
				respawn();
			setCurrentAnimationSet(idles);
		}
		
		if(getCurrentAnimationSet() == idles || getCurrentAnimationSet() == walks){
			if(Game.input.isPressed(KeyEvent.VK_SPACE))
					attack();
			else
				move();
		}
		if(Enemy.enemies == 0)
			sprite = praise.currentSprite();
	}

	private void respawn() {
		position = new Point(startingPoint.x,startingPoint.y);
		setHealth(starterHealth);
		alive = true;
	}

	@Override
	void takeDamage(int amount) {
		if(getCurrentAnimationSet() != damage) {
			super.takeDamage(amount);
			damage.get(direction).play();
		}
	}
	
	@Override
	void die() {
		super.die();
		
	}
}
