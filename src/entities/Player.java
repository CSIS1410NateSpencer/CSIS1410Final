package entities;

import entities.playerState.PlayerState;
import finalProject.Direction;
import finalProject.Game;
import graphics.Animation;
import graphics.AnimationSet;

import java.awt.event.KeyEvent;

import maths.Vector2;

public class Player extends Fighter {
	
	private double speed = 2;//in pixels per update
	
	private AnimationSet idles;
	private Animation praise = new Animation("src/images/player/praise_the_sun.png",10);
	
	private PlayerState currentPlayerState;
	Vector2 startingPoint = new Vector2(2175,360);
	public Player(){
		initializeHealth(16);
		direction = Direction.Down;
		
		
		setAttacks(AnimationSet.loadAnimations("player/attack",9));
		
		setCurrentAnimationSet(idles);
		sprite = getCurrentAnimationSet().get(direction).currentSprite();
		getCollider().setWidth(sprite.getWidth());
		getCollider().setHeight(sprite.getHeight());
		position = new Vector2(startingPoint.x,startingPoint.y);
	}
	
	@Override
	public void move() {
		velocity = Game.input.getPoint().multiply(speed);
		if(!velocity.equals(Vector2.zero())) {
			direction = Game.input.getDirection();
			setCurrentAnimationSet(walks);
		}
		else
			setCurrentAnimationSet(idles);

		moveAdjustingForTileCollision();
	}

	

	@Override
	public void attack() {
			setCurrentAnimationSet(getAttacks());
			getAttacks().get(direction).play();
			Game.audio.play("player_swing_sword.wav");
	}

	@Override
	public void update() {
		getPlayerState().update();
		sprite = getCurrentAnimationSet().get(direction).currentSprite();
	}



	@Override
	void takeDamage(int amount) {
		if(getCurrentAnimationSet() != damage) {
			setPlayerState(takeDamageState);
		}
	}
	
	@Override
	void die() {
		super.die();
		
		
	}

	public PlayerState getPlayerState() {
		return currentPlayerState;
	}

	public void setPlayerState(PlayerState playerState) {
		this.currentPlayerState = playerState;
		playerState.begin();
	}
}
