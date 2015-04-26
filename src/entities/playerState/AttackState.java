package entities.playerState;

import maths.Vector2;
import entities.Attack;
import entities.Player;
import finalProject.Game;
import graphics.AnimationSet;

public class AttackState extends PlayerState {

	public AttackState(Player p) {
		super(p);
	}

	@Override
	public void begin() {
		super.begin();
		Game.audio.play("player_swing_sword.wav");
	}

	@Override
	void finish() {
		createAttackEntity(75, 95, 85);
	}

	private void createAttackEntity(int distanceOut, int wide, int tall) {
		Vector2 directionPoint = p.getDirection().getPoint();
		Vector2 attackPosition = p.position.add(directionPoint
				.multiply(distanceOut));
		attackPosition.x += p.getCollider().getWidth() / 2;
		attackPosition.y += p.getCollider().getHeight() / 2;

		int width = (int) (Math.abs(directionPoint.x * tall) + Math
				.abs(directionPoint.y * wide));
		int height = (int) (Math.abs(directionPoint.y * tall) + Math
				.abs(directionPoint.x * wide));
		attackPosition.x -= width / 2;
		attackPosition.y -= height / 2;

		new Attack(p, attackPosition, width, height);
	}

}
