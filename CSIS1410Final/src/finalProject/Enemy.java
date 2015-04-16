package finalProject;

public class Enemy extends Entity implements Fighter {
	Animation animation = new Animation("src/images/walk_zombie_left.png",66,94,8);
	double speed = .1;
	private int health;
	public Enemy(){
		position.x = 300;
		position.y = 300;
		animation.delay = 200;
	}
	@Override
	public void move() {
		position.x-= speed;
		
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
