package finalProject;

import java.awt.event.KeyEvent;

public class Player extends Entity implements Fighter {
	double speed = 1;
	private double x;
	private double y;
	Point point = new Point();
	private int health;
	private Animation up = new Animation("src/images/walking_up.png",60,135,8);
	private Animation down = new Animation("src/images/walking_down.png",60,135,8);
	private Animation left = new Animation("src/images/walking_left.png",60,135,8);
	private Animation right = new Animation("src/images/walking_right_shaded.png",60,135,8);
	private Animation down_idle = new Animation("src/images/down_idle.png",60,135,8);
	private Animation up_idle = new Animation("src/images/idle_up.png",60,135,8);
	private Animation praise = new Animation("src/images/praise_the_sun.png",80,135,10);
	private Animation right_attack = new Animation("src/images/right_attack.png",142,136,9);
	private Animation left_attack = new Animation("src/images/left_attack.png",142,136,9);
	
	private Animation currentAnimation = right;
	
	public void setx(int i) { x = i; }
	public void sety(int i) { y = i; }
	
	@Override
	public void move() {
		currentAnimation = down_idle;
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
		
//		if(TestPanel.input.isPressed(KeyEvent.)){
//			
//		}
	}

	@Override
	public void attack() {
		//currentAnimation = praise;
		//currentAnimation = right_attack;
		if(TestPanel.input.isPressed(KeyEvent.VK_LEFT)){
			currentAnimation = left_attack;
		}
		if(TestPanel.input.isPressed(KeyEvent.VK_RIGHT)){
			currentAnimation = right_attack;
		}
		
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
		
	}

}
