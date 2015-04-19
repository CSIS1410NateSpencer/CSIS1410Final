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

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Canvas implements Runnable{
	JFrame frame;
	BufferStrategy bs;
	Graphics g;
	public static Input input = new Input();
	
	private TileMap tileMap;
	static ArrayList<Entity> entities = new ArrayList<>();
	static ArrayList<Entity> newEntities = new ArrayList<>();
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
		new Enemy();
		player.position.x = getWidth() / 2 - player.sprite.width / 2;
		player.position.y = getHeight() / 2 - player.sprite.height / 2;
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
		frame.setVisible(true);
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
		//sort entities by y position
		Collections.sort(entities);
		
		//update entity state
		for (Entity entity : entities) {
			entity.update();	
		}
		//check for intersection between entities
		for (int x = 0; x < entities.size(); x++) {
			for (int y = 0; y < entities.size(); y++) {
				if(x!=y)
					Entity.checkCollision(entities.get(x),entities.get(y));
			}
		}
		entities.addAll(newEntities);
		newEntities.clear();
		positionCamera();
	}
	private void render() {
		bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//clear the screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//draw the level
		tileMap.draw(g);
		
		//draw objects in the level;
		for (Entity entity : entities) {
			entity.draw(g);
		}
		
		//display the final product on the screen
		bs.show();
		
		//clean up after yourself
		g.dispose();
	}
	
	
	private double interpolate(double a, double b, double t){
		return a + (b-a) * t;
	}
	private void positionCamera() {
		cameraPosition.x = interpolate(cameraPosition.x, player.position.x - getWidth() / 2 + player.sprite.width / 2,.05);
		cameraPosition.y = interpolate(cameraPosition.y, player.position.y - getHeight() / 2 + player.sprite.height / 2,.05);
	}

}
