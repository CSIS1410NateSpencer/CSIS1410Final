package entities;

import finalProject.Direction;
import finalProject.Game;
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
	protected Direction direction = Direction.Right;
	protected Point velocity = Point.zero();
	private boolean topLeft;
	private boolean topRight;
	private boolean bottomLeft;
	private boolean bottomRight;
	
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
				&& getCurrentAnimationSet() != damage){
			Point difference = other.collider.center().subtract(collider.center());
			position = position.subtract(difference.normalized().multiply(10));
			takeDamage(1);
		}
	}
	
	void die() {
		alive = false;
		setCurrentAnimationSet(die);
		die.get(getDirection()).play();
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
	
	private void calculateCorners(double x, double y) {
		int leftTile = Game.tileMap.getColTile((int) (x - collider.width / 2));
		int rightTile = Game.tileMap.getColTile((int) (x + collider.width / 2) - 1);
		int topTile = Game.tileMap.getRowTile((int) (y - collider.height / 2));
		int bottomTile = Game.tileMap.getRowTile((int) (y + collider.height / 2) - 1);
		topLeft = Game.tileMap.isBlocked(topTile, leftTile);
		topRight = Game.tileMap.isBlocked(topTile, rightTile);
		bottomLeft = Game.tileMap.isBlocked(bottomTile, leftTile);
		bottomRight = Game.tileMap.isBlocked(bottomTile, rightTile);
	}
	
	protected void adjustForCollision() {
		int currCol = Game.tileMap.getColTile((int) position.x);
		int currRow = Game.tileMap.getRowTile((int) position.y);

		double tox = position.x + velocity.x;
		double toy = position.y + velocity.y;

		double tempx = position.x;
		double tempy = position.y;

		calculateCorners(position.x, toy);
		if (velocity.y < 0) {
			if (topLeft || topRight) {
				velocity.y = 0;
				tempy = currRow * Game.tileMap.getTileSize() + collider.height / 2;
			} else {
				tempy += velocity.y;
			}
		}
		if (velocity.y > 0) {
			if (bottomLeft || bottomRight) {
				velocity.y = 0;
				tempy = (currRow + 1) * Game.tileMap.getTileSize() - collider.height / 2;
			} else {
				tempy += velocity.y;
			}
		}

		calculateCorners(tox, position.y);
		if (velocity.x < 0) {
			if (topLeft || bottomLeft) {
				velocity.x = 0;
				tempx = currCol * Game.tileMap.getTileSize() + collider.width / 2;
			} else {
				tempx += velocity.x;
			}
		}
		if (velocity.x > 0) {
			if (topRight || bottomRight) {
				velocity.x = 0;
				tempx = (currCol + 1) * Game.tileMap.getTileSize() - collider.width / 2;
			} else {
				tempx += velocity.x;
			}
		}

		position.x = tempx;
		position.y = tempy;
	}
	
	
}
