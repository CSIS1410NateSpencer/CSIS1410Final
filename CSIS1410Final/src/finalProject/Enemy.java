package finalProject;

public class Enemy extends Entity implements Fighter {
	Animation animation = new Animation("src/images/walking_right.png",60,135,8);
	double speed = 1;
	private int health;
	
	@Override
	public void move() {
		position.x+= speed;
		
	}

	@Override
	public void attack() {
		
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
		sprite = animation.currentSprite();
		move();
		
	}

	@Override
	public void onCollide(Entity other) {
		// TODO Auto-generated method stub
		
	}

}
