package state;

import java.awt.Graphics;

import finalProject.Game;

public abstract class State {
	protected Game game;
	
	public State(Game game) {
		this.game = game;
	}
	public abstract void update();
	public abstract void render(Graphics g);
}
