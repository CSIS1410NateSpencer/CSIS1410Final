package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import finalProject.Game;

public class CreditsState extends State{

	int starterYOffset = 900;
	int yOffset = starterYOffset;
	int minYOffset = -1400;
	String[] credits = {
			"",
			"     --------------------------------------------------------------------------------------",
			"                                                    Credits",
			"     --------------------------------------------------------------------------------------",
			"",			
			"                                   Programmer : Nate Ivy",
			"                                   Programmer : Spencer Isaacson",			
			"",
			"",
			"     --------------------------------------------------------------------------------------",
			"                                                    AUDIO",
			"     --------------------------------------------------------------------------------------",
			"",
			"        Sound Effects Recorded By : Spencer Isaacson",
			"                        Background Music : sounddogs.com",
			"",
			"",
			"     --------------------------------------------------------------------------------------",
			"                                                GRAPHICS",
			"     --------------------------------------------------------------------------------------",
			"",
			"                Main Character Sprites : Nate Ivy",
			"        Additional Sprites and Tiles : spriters-resource.com",
			"",
			"",
			"     --------------------------------------------------------------------------------------",
			"                                          SPECIAL THANKS",
			"     --------------------------------------------------------------------------------------",
			"",
			"                                                        Eitr",
			"                                                  Dark Souls",
			"                                                Video Games",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			
			"                                                    THE END",
			
			};
	public CreditsState(Game game) {
		super(game);
	}

	@Override
	public void begin() {
		yOffset = starterYOffset;
		
	}
	@Override
	public void update() {
		if(yOffset > minYOffset)
			yOffset--;
		else if (Game.input.isPressed(KeyEvent.VK_SPACE)){
			Game.stateManager.next();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 30));
		for (int i = 0; i < credits.length; i++) {
			g.drawString(credits[i], 25, i * 32 + (int)yOffset);
		}
		
		if(yOffset <= minYOffset)
			
			g.drawString("Press the spacebar to restart", 301, 430);
	}

	@Override
	public void end() {
	}
}
