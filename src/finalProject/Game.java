package finalProject;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Enemy;
import entities.Entity;
import entities.Player;

public class Game extends Canvas implements Runnable{
	JFrame frame;
	BufferStrategy bs;
	Graphics g;
	public static Input input = new Input();
	
	public static TileMap tileMap;
	private static List<Entity> entities = new CopyOnWriteArrayList<>();
	Player player;
	public static Point cameraPosition = new Point(0,0);
	
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
		setFocusable(true);
		requestFocus();
	}
	private void setupMap() {
		tileMap = new TileMap("src/LVL1.txt", 96);
		tileMap.loadTiles("src/images/LVL1.png");
	}
	private void setupEntities() {
		for (int i = 0; i < 2; i++) {
			new Enemy();
		}
		player = new Player();
		player.position = new Point(800,600);
	}
	private void ensureSize(Dimension size) {
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}
	private void setupWindow() {
		ensureSize(new Dimension(640,480));
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
		while(true) {
			clock.tick();
			if(clock.getElapsed() >= delay){
				update();
				render();
				clock.reset();
			}
		}
	}
	
	private void update() {
		
		
		//update entity state
		for (Entity entity : entities) {
			entity.update();	
		}
		//check for intersection between entities
		for (int x = 0; x < entities.size(); x++) {
			for (int y = x; y < entities.size(); y++) {
				if(x!=y)
					Entity.checkCollision(entities.get(x),entities.get(y));
			}
		}
		positionCamera();
		
		//sort entities by y position
		Collections.sort(entities);
	}
	private void render() {
		bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//clear the screen
		g.setColor(new Color(0.35f, 0.20f, 0.0f, 1.0f));
		//g.setColor(new Color(0.0f,0.0f,0.0f,0.1f));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//draw the level
		tileMap.draw(g);
		
		//draw objects in the level;
		for (Entity entity : entities) {
			entity.draw(g);
		}
		g.setColor(Color.BLACK);
		g.drawString("Health: " + player.getHealth(), 0, 17);
		//display the final product on the screen
		bs.show();
		
		//clean up after yourself
		g.dispose();
	}
	
	
	private double interpolate(double a, double b, double t){
		return a + (b-a) * t;
	}
	private void positionCamera() {
		cameraPosition.x = interpolate(cameraPosition.x, player.position.x - getWidth() / 2 + player.getSprite().getWidth() / 2,.05);
		cameraPosition.y = interpolate(cameraPosition.y, player.position.y - getHeight() / 2 + player.getSprite().getHeight() / 2,.05);
		cameraPosition.x = clamp(cameraPosition.x,0,tileMap.getTotalMapWidth() - getWidth());
		cameraPosition.y = clamp(cameraPosition.y,0,tileMap.getTotalMapHeight() - getHeight());
		tileMap.setx((int)-cameraPosition.x);
		tileMap.sety((int)-cameraPosition.y);
	}
	
	double clamp(double base, double lower, double upper){
		if(base < lower)
			return lower;
		if(base > upper)
			return upper;
		return base;
	}
	
	public static void remove(Entity e) {
		entities.remove(e);
	}
	public static void add(Entity e) {
		entities.add(e);
	}
	
	
}
