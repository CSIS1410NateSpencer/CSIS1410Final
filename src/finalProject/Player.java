package finalProject;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Player extends Fighter {
	
	double speed = 2;//in pixels per update
	Map<Direction, Animation> walks = getAnimations("walk",60,135,8);
	Map<Direction, Animation> idles = getAnimations("idle",60,135,8);
	Map<Direction, Animation> attacks = getAnimations("attack",142,136,9);
	Map<Direction, Animation> injuries = getAnimations("attack",142,136,9);
	
	private Animation praise = new Animation("src/images/praise_the_sun.png",80,135,10);
	
	private Direction direction = Direction.Down;
	private Map<Direction, Animation> currentAnimationSet = idles;
	
	
	public Player(){
		walks.put(Direction.Right, new Animation("src/images/walk_right_shaded.png",60,135,8));
		sprite = currentAnimationSet.get(direction).currentSprite();
		health = 100;
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
		if(!Game.input.getPoint().equals(Point.zero())) {
			direction = Game.input.getDirection();
			currentAnimationSet = walks;
			Point velocity = Game.input.getPoint();
			position.x += velocity.x * speed;
			position.y += velocity.y * speed;
		}
		else
			currentAnimationSet = idles;
	}

	@Override
	public void attack() {
			currentAnimationSet = attacks;
			attacks.get(direction).play();
			//Entity attack = new AttackEntity();
	}

	@Override
	public void update() {
		sprite = currentAnimationSet.get(direction).currentSprite();
		if(currentAnimationSet != attacks)
			move();
		else if(currentAnimationSet.get(direction).finished == true)
			currentAnimationSet = idles;
		if(Game.input.isPressed(KeyEvent.VK_SPACE) && currentAnimationSet != attacks)
			attack();
		
	}

	@Override
	public void onCollide(Entity other) {
		if(other instanceof Enemy && currentAnimationSet != injuries)
			takeDamage(1);
	}
	
	@Override
	void die() {
		position = new Point(0,0);
		health = 100;
	}

}
