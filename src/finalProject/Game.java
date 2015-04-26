package finalProject;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

import state.CreditsState;
import state.MenuState;
import state.PlayState;
import state.SplashState;
import state.State;
import state.StateManager;
import maths.Maths;
import maths.Point;
import audio.AudioPlayer;
import entities.Enemy;
import entities.Entity;
import entities.Player;
import graphics.HUD;

public class Game extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8294422427114133940L;
	JFrame frame;
	BufferStrategy bs;
	Graphics g;
	public static Input input = new Input();
	
	public static StateManager manager = new StateManager();
	public SplashState splashState = new SplashState(this);
	public MenuState menuState = new MenuState(this);
	public PlayState playState = new PlayState(this);
	public CreditsState creditsState = new CreditsState(this);
	public static AudioPlayer audio = new AudioPlayer();
	private static Game game;
	
	public static void main(String[] args) {
		Game.getInstance().run();
	}
	
	public static Game getInstance() {
		if(game == null)
			game = new Game();
		return game;
	}
	
	private Game(){
		setupInput();
		setupWindow();
		setupStates();
		frame.setVisible(true);
	}

	
	private void setupInput() {
		addKeyListener(input);
		addMouseListener(menuState);
		setFocusable(true);
		requestFocus();
	}
	
	private void ensureSize(Dimension size) {
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}
	private void setupWindow() {
		ensureSize(new Dimension(1000,800));
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	private void setupStates() {
		manager.add(splashState);
		manager.add(menuState);
		manager.add(playState);
		manager.add(creditsState);
	}
	@Override
	public void run() {
		Clock clock = new Clock();
		double desiredFPS = 60;
		double delay = Clock.NANOS_PER_SECOND / desiredFPS;
		
		manager.begin();
		while (true) {
			clock.tick();
			if (clock.getElapsed() >= delay) {
				update();
				render();
				clock.reset();
			}
		}
	}
	
	private void update() {
		manager.getCurrent().update();
	}

	private void render() {

		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		// clear the screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		manager.getCurrent().render(g);
		// display the final product on the screen
		bs.show();

		// clean up after yourself
		g.dispose();
	}
	
	
	
	
	
	
}
