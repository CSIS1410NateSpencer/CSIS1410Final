package finalProject;

import java.awt.event.KeyEvent;

public class Player extends Entity implements Fighter {
	double speed = 1;
	private int health;
	private Animation up = new Animation("src/images/walking_up.png",60,135,8);
	private Animation down = new Animation("src/images/walking_down.png",60,135,8);
	private Animation left = new Animation("src/images/walking_left.png",60,135,8);
	private Animation right = new Animation("src/images/walking_right.png",60,135,8);
	private Animation praise = new Animation("src/images/praise_the_sun.png",80,135,8);
	
	private Animation currentAnimation = right;
	
	@Override
	public void move() {
		if(TestPanel.input.isPressed(KeyEvent.VK_LEFT)) {
			currentAnimation = left;
			position.x-= speed;
		}
		if(TestPanel.input.isPressed(KeyEvent.VK_RIGHT)) {
			currentAnimation = right;
			position.x+= speed;
		}
		if(TestPanel.input.isPressed(KeyEvent.VK_UP)) {
			currentAnimation = up;
			position.y-=speed;
		}
		if(TestPanel.input.isPressed(KeyEvent.VK_DOWN)) {
			currentAnimation = down;
			position.y+=speed;
		}
	}

	@Override
	public void attack() {
		currentAnimation = praise;
	}

	@Override
	public void takeDamage(int amount) {
		health-=amount;
		if(amount <=0)
			die();
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		sprite = currentAnimation.currentSprite();
		move();
		if(TestPanel.input.isPressed(KeyEvent.VK_SPACE))
			attack();
		
	}

	@Override
	public void onCollide(Entity other) {
		// TODO Auto-generated method stub
		
	}

}
