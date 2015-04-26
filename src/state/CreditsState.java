package state;

import java.awt.Color;
import java.awt.Graphics;

import finalProject.Game;

public class CreditsState extends State{

	double y = 900;
	String[] credits = {
			"                                                                                                                                                                          Credits                         ",
			"Spriter: Nate Ivy",
			"Coder: Nate Ivy",
			"Coder: Spencer Isaacson"
			};
	public CreditsState(Game game) {
		super(game);
	}

	@Override
	public void update() {
		y-= 1;	
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		for (int i = 0; i < credits.length; i++) {
			g.drawString(credits[i], 0, i * 32 + (int)y);
		}
		
	}

}
