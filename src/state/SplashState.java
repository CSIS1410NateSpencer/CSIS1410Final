package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import maths.Maths;
import finalProject.Clock;
import finalProject.Game;

public class SplashState extends State {

	Clock clock = new Clock();
	double opacity = 1;
	Image img = null;
	public SplashState(Game game) {
		super(game);
		
		try {
			img = ImageIO.read(new File("src/images/splash_screen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() {
		opacity = Maths.interpolate(1, 0, clock.getElapsedAsSeconds() / 3 - 1);
		opacity = Maths.clamp(opacity, 0, 1);
		clock.tick();
		if(clock.getElapsedAsSeconds() > 10)
			game.state = game.menuState;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, 0, 0, game.getWidth(),game.getHeight(), null);
		g.setColor(new Color(0,0,0,(float) opacity));
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
	}

}
