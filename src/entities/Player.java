package entities;

import finalProject.Direction;
import finalProject.Game;
import finalProject.Point;
import graphics.Animation;
import graphics.AnimationSet;

import java.awt.event.KeyEvent;

public class Player extends Fighter {
	
	double speed = 2;//in pixels per update
	Point velocity;
	
	AnimationSet idles;
	private boolean topLeft;
	private boolean topRight;
	private boolean bottomLeft;
	private boolean bottomRight;
	
	private Animation praise = new Animation("src/images/praise_the_sun.png",10);
	
	private Direction direction = Direction.Right;
	
	
	public Player(){
		initializeHealth(9);
		
		
		walks = AnimationSet.loadAnimations("walking",8);
		idles = AnimationSet.loadAnimations("idle",8);
		attacks = AnimationSet.loadAnimations("attack",9);
		damage = AnimationSet.loadAnimations("dmg",4);
		die = AnimationSet.loadAnimations("die",14);
		setCurrentAnimationSet(idles);
		sprite = getCurrentAnimationSet().get(direction).currentSprite();
		collider.width = sprite.getWidth();
		collider.height = sprite.getHeight();
	}
	
	@Override
	public void move() {
		velocity = Game.input.getPoint();
		if(!velocity.equals(Point.zero())) {
			direction = Game.input.getDirection();
			setCurrentAnimationSet(walks);
		}
		else
			setCurrentAnimationSet(idles);

		adjustForCollision();
	}

	

	@Override
	public void attack() {
			setCurrentAnimationSet(attacks);
			attacks.get(direction).play();
	}

	@Override
	public void update() {
		sprite = getCurrentAnimationSet().get(direction).currentSprite();
		
		if(getCurrentAnimationSet().get(direction).isFinished() == true) {
			if(getCurrentAnimationSet() == attacks)
				new Attack(this, new Point(position.x + direction.getSign(collider.width),position.y),collider.width,collider.height);
			if(getCurrentAnimationSet() == die)
				respawn();
			setCurrentAnimationSet(idles);
		}
		
		if(getCurrentAnimationSet() == idles || getCurrentAnimationSet() == walks){
			if(Game.input.isPressed(KeyEvent.VK_SPACE))
					attack();
			else
				move();
		}
		if(Enemy.enemies == 0)
			sprite = praise.currentSprite();
		
		
	}

	private void respawn() {
		position = new Point(800,800);
		setHealth(starterHealth);
		alive = true;
	}

	@Override
	void takeDamage(int amount) {
		if(getCurrentAnimationSet() != damage) {
			super.takeDamage(amount);
			damage.get(direction).play();
			position.x -= direction.getSign(10);
		}
	}
	
	@Override
	void die() {
		super.die();
		System.out.println("dont leave this magic number here");
		
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
