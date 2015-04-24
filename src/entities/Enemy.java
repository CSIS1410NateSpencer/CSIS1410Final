package entities;

import finalProject.Direction;
import graphics.Animation;
import graphics.AnimationSet;

import java.util.Random;

public class Enemy extends Fighter {
	
	public static int enemies = 0;
	
	double speed = .15;
	Random rand = new Random();
	Attack attack;
	
	public Enemy(){
		setDirection(Direction.Left);
		setPosition();
		initializeHealth(5);
		walks = AnimationSet.loadAnimations("walk_zombie", 11);
		damage = AnimationSet.loadAnimations("damaged", 4);
		die = AnimationSet.loadAnimations("die", 14);
		setCurrentAnimationSet(walks);
		sprite = getCurrentAnimationSet().get(getDirection()).currentSprite();
		collider.width = sprite.getWidth();
		collider.height = sprite.getHeight();
		 attack = new Attack(this,position, sprite.getWidth(), sprite.getHeight());
		 attack.temporary = false;
		 
		 enemies++;
	}
	public void setPosition(){
		position.x = rand.nextInt(150) + 800;
		position.y = rand.nextInt(500) + 300;
	}
	public void setPosition(double x,double y){
		position.x = x;
		position.y = y;
	}
	
	@Override
	public void move() {
		position.x += getDirection().getSign(speed);
		if(rand.nextInt(1000) == 999)
			if(getDirection() == Direction.Right)
				setDirection(Direction.Left);
			else
				setDirection(Direction.Right);
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
		
	}

	@Override
	void die() {
		super.die();
		attack.destroy();
		enemies--;
		
		
	}

}
