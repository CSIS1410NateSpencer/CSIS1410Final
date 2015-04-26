package entities.playerState;

import entities.Player;
import finalProject.Game;
import graphics.AnimationSet;

public abstract class PlayerState {
	
	Player p;
	AnimationSet a;
	PlayerState next;
	public PlayerState(Player p) {
		this.p = p;
	}
	
	public void begin() {
		p.setCurrentAnimationSet(a);
		p.getCurrentAnimationSet().get(p.getDirection()).play();
	}
	
	public void update() {
		if(a != null && a.get(p.getDirection()).isFinished() == true)
			finish();
	}
	
	abstract void finish();
	
	void next() {
		p.setPlayerState(next);
	}
	
	PlayerState getNext() {
		return next;
	}
	
	void setNext(PlayerState next) {
		this.next = next;
	}
}
