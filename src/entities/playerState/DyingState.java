package entities.playerState;

import entities.Player;
import finalProject.Game;
import graphics.AnimationSet;

public class DyingState extends PlayerState
{

	public DyingState(Player p) {
		super(p);
		a = AnimationSet.loadAnimations("player/die",14);
	}

	@Override
	public void begin() {
		super.begin();
		Game.audio.play("player_die.wav");
	}


	@Override
	void finish() {
		Game.stateManager.next();
	}

}
