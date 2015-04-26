package entities;

import finalProject.Direction;
import finalProject.Game;
import graphics.AnimationSet;

import java.util.Random;

public class Enemy extends Fighter {
	
	private static final int RIGHT_OFFSET = 35;
	private static final int DOWN_OFFSET = 20;

	public static int enemies = 0;
	
	double speed = .15;
	Random rand = new Random();
	Attack attack;
	
	public Enemy(){
		setDirection(Direction.Left);
		setPosition();
		initializeHealth(5);
		walks = AnimationSet.loadAnimations("enemy/walk_zombie", 11);
		damage = AnimationSet.loadAnimations("enemy/damaged", 4);
		die = AnimationSet.loadAnimations("enemy/die_enemy", 11);
		setCurrentAnimationSet(walks);
		sprite = getCurrentAnimationSet().get(getDirection()).currentSprite();
		collider.width = sprite.getWidth() - 30;
		collider.height = sprite.getHeight() - 20;
		
		 attack = new Attack(this,position, collider.width,collider.height);
		 attack.temporary = false;
		 collider.offset.y = DOWN_OFFSET;
		 attack.collider.offset.y = DOWN_OFFSET;
		 enemies++;
	}
	public void setPosition(){
		position.x = rand.nextInt(700) + 1600;
		position.y = rand.nextInt(2200) + 800;
	}
	public void setPosition(double x,double y){
		position.x = x;
		position.y = y;
	}
	
	@Override
	public void move() {
		velocity = direction.getPoint().multiply(speed);
		moveAdjustingForTileCollision();
		if(rand.nextInt(1000) == 999)
			if(getDirection() == Direction.Right) {
				setDirection(Direction.Left);
				collider.offset.x = 0;
				attack.collider.offset.x = 0;
			}
			else {
				setDirection(Direction.Right);
				collider.offset.x = RIGHT_OFFSET;
				attack.collider.offset.x = RIGHT_OFFSET;
			}
	}

	@Override
	public void attack() {
		
	}

	@Override
	public void update() {
		sprite = getCurrentAnimationSet().get(getDirection()).currentSprite();
		if(currentAnimationSet == damage && damage.get(getDirection()).isFinished())
			currentAnimationSet = walks;
		
		if(currentAnimationSet != die)
			move();
		else if (die.get(getDirection()).isFinished()) {
			
			destroy();
		}
		attack.position = position;
	}

	@Override
	void die() {
		super.die();
		attack.destroy();
		enemies--;
		
		
	}

	@Override
	void takeDamage(int amount) {
		super.takeDamage(amount);
		Game.audio.play("enemy_hurt.wav");
	}
}
