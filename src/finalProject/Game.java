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
import state.State;
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
	
	public static TileMap tileMap;
	public static List<Entity> entities = new CopyOnWriteArrayList<>();
	public static Player player;
	public static Point cameraPosition = new Point(0,0);
	
	public static AudioPlayer audio = new AudioPlayer();
	public HUD hud = new HUD();
	public MenuState menuState = new MenuState(this);
	public PlayState playState = new PlayState(this);
	public CreditsState creditsState = new CreditsState(this);
	public State state = creditsState;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
		
	}
	
	public Game(){
		setupWindow();
		setupInput();
		setupMap();
		setupEntities();
		frame.setVisible(true);
	}
	
	private void setupInput() {
		addKeyListener(input);
		addMouseListener(menuState);
		setFocusable(true);
		requestFocus();
	}
	private void setupMap() {
		tileMap = new TileMap("src/dungeon.txt", 96);
		tileMap.loadTiles("src/images/dungeon.png");
	}
	private void setupEntities() {
		for (int i = 0; i < 6; i++) {
			new Enemy();
		}
		player = new Player();
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
	
	@Override
	public void run() {
		Clock clock = new Clock();
		double desiredFPS = 60;
		double delay = Clock.NANOS_PER_SECOND / desiredFPS;
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
		state.update();
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

		state.render(g);
		// display the final product on the screen
		bs.show();

		// clean up after yourself
		g.dispose();
	}
	
	
	
	public void positionCamera() {
		cameraPosition.x = Maths.interpolate(cameraPosition.x, player.position.x - getWidth() / 2 + player.getSprite().getWidth() / 2,.05);
		cameraPosition.y = Maths.interpolate(cameraPosition.y, player.position.y - getHeight() / 2 + player.getSprite().getHeight() / 2,.05);
		cameraPosition.x = Maths.clamp(cameraPosition.x,0,tileMap.getTotalMapWidth() - getWidth());
		cameraPosition.y = Maths.clamp(cameraPosition.y,0,tileMap.getTotalMapHeight() - getHeight());
		tileMap.setx((int)-cameraPosition.x);
		tileMap.sety((int)-cameraPosition.y);
	}
	
	public static void remove(Entity e) {
		entities.remove(e);
	}
	public static void add(Entity e) {
		entities.add(e);
	}
	
	
}
