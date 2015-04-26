package entities.playerState;

import entities.Player;
import graphics.AnimationSet;

public class IdleState extends PlayerState {

	public IdleState(Player p) {
		super(p);
		this.a = AnimationSet.loadAnimations("player/idle", 8);
	}

	@Override
	public void begin() {

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	void finish() {
		// TODO Auto-generated method stub

	}

}
