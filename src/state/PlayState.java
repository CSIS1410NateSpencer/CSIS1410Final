package state;

import java.awt.Graphics;
import java.util.Collections;

import entities.Entity;
import finalProject.Game;

public class PlayState extends State {

	public PlayState(Game game) {
		super(game);
	}

	@Override
	public void update() {
		// update entities
		for (Entity entity : Game.entities) {
			entity.update();
		}
		// check for intersection between entities
		for (int x = 0; x < Game.entities.size(); x++) {
			for (int y = x; y < Game.entities.size(); y++) {
				if (x != y)
					Entity.checkCollision(Game.entities.get(x),
							Game.entities.get(y));
			}
		}
		game.positionCamera();

		// sort entities by y position
		Collections.sort(Game.entities);
	}

	@Override
	public void render(Graphics g) {
		Game.tileMap.draw(g);
		game.hud.render(g);

		// draw objects in the level;
		for (Entity entity : Game.entities) {
			entity.draw(g);
		}
	}

}
