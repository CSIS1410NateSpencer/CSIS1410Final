package entities.playerState;

import entities.Player;
import graphics.AnimationSet;

public class WalkState extends PlayerState {

	public WalkState(Player p) {
		super(p);
		a = AnimationSet.loadAnimations("player/walking", 8);
	}

	@Override
	void finish() {
	}

}
