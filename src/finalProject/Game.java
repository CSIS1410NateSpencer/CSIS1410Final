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
	
	private TileMap tileMap;
	private static CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<>();
	Player player = new Player();
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
		tileMap = new TileMap("1txt.txt", 32);
		tileMap.loadTiles("1tiles.png");
	}
	private void setupEntities() {
		for (int i = 0; i < 2; i++) {
			new Enemy();
		}
		
		player.position.x = getWidth() / 2 - player.getSprite().getWidth() / 2;
		player.position.y = getHeight() / 2 - player.getSprite().getHeight() / 2;
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
	
	double fps = 0;
	@Override
	public void run() {
		final long NANOS_PER_SECOND = 1000000000;
		double desiredFPS = 60;
		double delay = NANOS_PER_SECOND / desiredFPS;
		long startTime = System.nanoTime();
		long currentTime = startTime;
		long oldTime = startTime;
		double elapsed;
		int frames = 0;
		long totalElapsed = 0;
		
		while(true) {
			currentTime = System.nanoTime();
			elapsed = currentTime - oldTime;
			totalElapsed = currentTime - startTime;
			if(elapsed >= delay){
				update();
				render();
				oldTime = currentTime;
				frames++;
				if(totalElapsed > 0)
				fps = frames / (totalElapsed/(double)NANOS_PER_SECOND);
				
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
	}
	
	public static void remove(Entity e) {
		entities.remove(e);
	}
	public static void add(Entity e) {
		entities.add(e);
	}
	
	
}
