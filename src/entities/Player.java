package entities;

import finalProject.Direction;
import finalProject.Game;
import graphics.Animation;
import graphics.AnimationSet;

import java.awt.event.KeyEvent;

import maths.Vector2;

public class Player extends Fighter {
	
	private double speed = 2;//in pixels per update
	
	private AnimationSet idles;
	private Animation praise = new Animation("src/images/player/praise_the_sun.png",10);
	
	
	Vector2 startingPoint = new Vector2(2175,360);
	public Player(){
		initializeHealth(16);
		direction = Direction.Down;
		walks = AnimationSet.loadAnimations("player/walking",8);
		idles = AnimationSet.loadAnimations("player/idle",8);
		setAttacks(AnimationSet.loadAnimations("player/attack",9));
		damage = AnimationSet.loadAnimations("player/dmg",4);
		die = AnimationSet.loadAnimations("player/die",14);
		setCurrentAnimationSet(idles);
		sprite = getCurrentAnimationSet().get(direction).currentSprite();
		getCollider().setWidth(sprite.getWidth());
		getCollider().setHeight(sprite.getHeight());
		position = new Vector2(startingPoint.x,startingPoint.y);
	}
	
	@Override
	public void move() {
		velocity = Game.input.getPoint().multiply(speed);
		if(!velocity.equals(Vector2.zero())) {
			direction = Game.input.getDirection();
			setCurrentAnimationSet(walks);
		}
		else
			setCurrentAnimationSet(idles);

		moveAdjustingForTileCollision();
	}

	

	@Override
	public void attack() {
			setCurrentAnimationSet(getAttacks());
			getAttacks().get(direction).play();
			Game.audio.play("player_swing_sword.wav");
	}

	@Override
	public void update() {
		sprite = getCurrentAnimationSet().get(direction).currentSprite();
		
		if(getCurrentAnimationSet().get(direction).isFinished() == true) {
			if(getCurrentAnimationSet() == getAttacks())
				createAttackEntity(75,95,85);
			if(getCurrentAnimationSet() == die) {
				Game.stateManager.next();
			}
			setCurrentAnimationSet(idles);
		}
		
		if(getCurrentAnimationSet() == idles || getCurrentAnimationSet() == walks){
			if(Game.input.isPressed(KeyEvent.VK_SPACE))
					attack();
			else
				move();
		}
		if(Enemy.enemies == 0) {
			sprite = praise.currentSprite();
			if(praise.isFinished()) {
				System.out.println("YIPPEE!");
				Game.stateManager.next();
			}
		}
		
		
	}

	protected void createAttackEntity(int distanceOut, int wide, int tall) {
		Vector2 directionPoint = direction.getPoint();
		Vector2 attackPosition = position.add(directionPoint.multiply(distanceOut));
		attackPosition.x += getCollider().getWidth() / 2;
		attackPosition.y += getCollider().getHeight() / 2;
		
		int width = (int)(Math.abs(directionPoint.x * tall) + Math.abs(directionPoint.y * wide));
		int height = (int)(Math.abs(directionPoint.y * tall) + Math.abs(directionPoint.x * wide));
		attackPosition.x -= width / 2;
		attackPosition.y -= height / 2;
		
		new Attack(this, attackPosition,width,height);
	}

	@Override
	void takeDamage(int amount) {
		if(getCurrentAnimationSet() != damage) {
			super.takeDamage(amount);
			damage.get(direction).play();
			Game.audio.play("player_hurt.wav");
		}
	}
	
	@Override
	void die() {
		super.die();
		Game.audio.play("player_die.wav");
		
	}
}
