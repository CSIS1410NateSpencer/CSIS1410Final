package entities;

import finalProject.Direction;
import finalProject.Game;
import finalProject.Point;
import graphics.Animation;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Player extends Fighter {
	
	double speed = 1;//in pixels per update
	int starterHealth;
	Point velocity;
	
	Map<Direction, Animation> walks = getAnimations("walk",60,135,8);
	Map<Direction, Animation> idles = getAnimations("idle",60,135,8);
	Map<Direction, Animation> attacks = getAnimations("attack",142,136,9);
	Map<Direction, Animation> injuries = getAnimations("injury",60,135,2);
	
	private Animation praise = new Animation("src/images/praise_the_sun.png",80,135,10);
	
	private Direction direction = Direction.Down;
	private Map<Direction, Animation> currentAnimationSet = idles;
	
	
	public Player(){
		walks.put(Direction.Right, new Animation("src/images/walk_right_shaded.png",60,135,8));
		sprite = currentAnimationSet.get(direction).currentSprite();
		health = 100;
		starterHealth = health;
		for (Direction direction : Direction.values()) {
			injuries.get(direction).setDelay(1000);
		}
	}
	
	private Map<Direction,Animation> getAnimations(String type,int width, int height, int numberOfSprites){
		Map<Direction,Animation> animations = new HashMap<>();
		for (int i = 0; i < Direction.values().length; i++) {
			Direction d = Direction.values()[i];
			animations.put(d, new Animation("src/images/" + type + "_" + d + ".png",width,height,numberOfSprites));
		}
		return animations;
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
			//Entity attack = new AttackEntity(this);
	}

	@Override
	public void update() {
		sprite = currentAnimationSet.get(direction).currentSprite();
		if(currentAnimationSet != attacks && currentAnimationSet != injuries)
			move();
		else if(currentAnimationSet.get(direction).isFinished() == true)
			currentAnimationSet = idles;
		if(Game.input.isPressed(KeyEvent.VK_SPACE) && currentAnimationSet != attacks && currentAnimationSet != injuries)
			attack();
		
	}

	@Override
	public void onCollide(Entity other) {
		if(other instanceof Enemy && currentAnimationSet != injuries) {
			takeDamage(1);
		}
	}
	
	@Override
	void takeDamage(int amount) {
		super.takeDamage(amount);
		currentAnimationSet = injuries;
		injuries.get(direction).play();
		position.x -= direction.getValue(17);
	}
	
	@Override
	void die() {
		position = Point.zero();
		health = starterHealth;
	}

}
