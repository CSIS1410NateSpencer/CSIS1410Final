package entities;

import finalProject.Direction;
import finalProject.Point;
import graphics.AnimationSet;


public abstract class Fighter extends Entity {
	
	AnimationSet walks;
	AnimationSet attacks;
	AnimationSet damage;
	AnimationSet die;
	
	boolean alive = true;
	protected AnimationSet currentAnimationSet;
	private int health;
	protected int starterHealth;
	private Direction direction = Direction.Right;
	
	protected void initializeHealth(int health) {
		setHealth(health);
		starterHealth = getHealth();
	}
	
	@Override
	public void update() {
			move();
			sprite = getCurrentAnimationSet().get(getDirection()).currentSprite();
	}
	
	abstract void move();
	abstract void attack();
	
	void takeDamage(int amount) {
		setHealth(getHealth() - amount);
		setCurrentAnimationSet(damage);
		damage.get(direction).play();
		if(getHealth() <=0)
			die();
	}
	
	@Override
	public final void onCollide(Entity other) {
		if(alive 
				&& other instanceof Attack 
				&& ((Attack)other).sender.getClass() != this.getClass() 
				&& getCurrentAnimationSet() != damage)
			takeDamage(1);
	}
	
	void die() {
		alive = false;
		setCurrentAnimationSet(die);
		die.get(getDirection()).play();
		System.out.println("for some reason when he dies facing left the animation doesn't play.");
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public AnimationSet getCurrentAnimationSet() {
		return currentAnimationSet;
	}

	protected void setCurrentAnimationSet(AnimationSet currentAnimationSet) {
		this.currentAnimationSet = currentAnimationSet;
	}

	public Direction getDirection() {
		return direction;
	}

	protected void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
}
