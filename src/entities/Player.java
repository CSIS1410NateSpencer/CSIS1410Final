package entities;

import finalProject.Direction;
import finalProject.Game;
import finalProject.Point;
import graphics.Animation;
import graphics.AnimationSet;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Player extends Fighter {
	
	double speed = 1;//in pixels per update
	Point velocity;
	
	AnimationSet walks = AnimationSet.getAnimations("walk",60,135,8);
	AnimationSet idles = AnimationSet.getAnimations("idle",60,135,8);
	AnimationSet attacks = AnimationSet.getAnimations("attack",142,136,9);
	AnimationSet injuries = AnimationSet.getAnimations("injury",60,135,2);
	
	private AnimationSet currentAnimationSet = idles;
	
	private Animation praise = new Animation("src/images/praise_the_sun.png",80,135,10);
	
	private Direction direction = Direction.Right;
	
	
	public Player(){
		sprite = currentAnimationSet.get(direction).currentSprite();
		initializeHealth(10);
		collider.width = sprite.getWidth();
		collider.height = sprite.getHeight();
	}
	
	@Override
	public void move() {
		velocity = Game.input.getPoint();
		if(!velocity.equals(Point.zero())) {
			direction = Game.input.getDirection();
			currentAnimationSet = walks;
			position = position.add(velocity.multiply(speed));
		}
		else
			currentAnimationSet = idles;
	}

	@Override
	public void attack() {
			currentAnimationSet = attacks;
			attacks.get(direction).play();
	}

	@Override
	public void update() {
		sprite = currentAnimationSet.get(direction).currentSprite();
		
		if(currentAnimationSet.get(direction).isFinished() == true) {
			if(currentAnimationSet == attacks)
				new Attack(this, new Point(position.x + direction.getValue(collider.width),position.y),collider.width,collider.height);
			currentAnimationSet = idles;
		}
		
		if(currentAnimationSet != attacks && currentAnimationSet != injuries){
			if(Game.input.isPressed(KeyEvent.VK_SPACE))
					attack();
			else
				move();
		}
		if(Enemy.enemies == 0)
			sprite = praise.currentSprite();
	}

	
	@Override
	void takeDamage(int amount) {
		if(currentAnimationSet != injuries) {
			super.takeDamage(amount);
			currentAnimationSet = injuries;
			injuries.get(direction).play();
			position.x -= direction.getValue(10);
		}
	}
	
	@Override
	void die() {
		position = Point.zero();
		setHealth(starterHealth);
	}

}