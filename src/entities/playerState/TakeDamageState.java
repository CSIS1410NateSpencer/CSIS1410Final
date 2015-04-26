package entities.playerState;

import entities.Player;
import finalProject.Game;
import graphics.AnimationSet;

public class TakeDamageState extends PlayerState {

	public TakeDamageState(Player p) {
		super(p);
		a = AnimationSet.loadAnimations("player/dmg", 4);
	}

	@Override
	public void begin() {
		super.begin();
		Game.audio.play("player_hurt.wav");
	}

	@Override
	void finish() {
		// TODO Auto-generated method stub

	}

}
