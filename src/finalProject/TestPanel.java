package finalProject;


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import static finalProject.Tile.TILE_SIZE;

public class TestPanel extends JPanel implements Runnable{

	static final int TILES_WIDE = 30;
	static final int TILES_TALL = 25;
	public static final int SCALE = 1;
	static Input input = new Input();
	private TileMap tm;
	ArrayList<Entity> entities = new ArrayList<>();
	
	public TestPanel() {
		ensureSize();
		addKeyListener(input);
		setFocusable(true);
		
		
		
		tm = new TileMap("1txt.txt", 32);
		tm.loadTiles("1tiles.png");
		//dirt = new Tile(Sprite.load("src/images/1tiles.png",0,0,32,32));
		entities.add(new Player());
		entities.add(new Enemy());
		
		Thread thread = new Thread(this);
		thread.start();
	}
	protected void ensureSize() {
		Dimension dim = new Dimension(TILES_WIDE * TILE_SIZE * SCALE,TILES_TALL * TILE_SIZE * SCALE);
		setMinimumSize(dim);
		setPreferredSize(dim);
		setMaximumSize(dim);
	}
	

	double t = 0;
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
			tm.draw(g);
			for (Entity entity : entities) {
				entity.draw(g);
			}
		repaint();
	}
	

	private void update() {
		for (Entity entity : entities) {
			entity.update();
		}
	}
	@Override
	public void run() {
		final long NANOS_PER_SECOND = 1000000000;
		double desiredFPS = 60;
		double delay = NANOS_PER_SECOND / desiredFPS;
		long currentTime = System.nanoTime();
		long oldTime = currentTime;
		double elapsed;
		
		while(true){
			currentTime = System.nanoTime();
			elapsed = currentTime - oldTime;
			if(elapsed >= delay){
				update();
				oldTime = currentTime;
			}
		}
	}
}
