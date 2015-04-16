package finalProject;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import static finalProject.Tile.TILE_SIZE;

public class TestPanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7819835970931575504L;
	static final int TILES_WIDE = 20;
	static final int TILES_TALL = 15;
	public static final int SCALE = 1;
	static Input input = new Input();
	private Sprite grass;
	Entity player = new Player();
	ArrayList<Entity> entities = new ArrayList<>();
	
	public TestPanel() {
		ensureSize();
		addKeyListener(input);
		setFocusable(true);
		grass = Sprite.load("src/images/grass.png",0,0,16,16);
		entities.add(player);
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
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setDoubleBuffered(false);
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(getWidth() / 2, getHeight() / 2);
			for (int x = 0; x < TILES_WIDE; x++) {
				for (int y = 0; y < TILES_TALL; y++) {
					grass.draw(g, (int)(x * TILE_SIZE - (int)cam.x), (int)(y * TILE_SIZE - (int)cam.y),TILE_SIZE,TILE_SIZE);
				}
			}
			for (Entity entity : entities) {
				entity.draw(g);
			}
	}
	
	public static Point cam = new Point();
	private void update() {
		if(player.sprite != null){
		cam.x = lerp(cam.x,player.position.x + (player.sprite.width / 2),.1);
		cam.y = getHeight() / 2;
		//cam.y = player.position.y + (player.sprite.height / 2);
		}
		for (Entity entity : entities) {
			entity.update();
		}
	}
	
	double lerp(double a, double b, double t) {
		return a + (b-a) * t;
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
				repaint();
				oldTime = currentTime;
			}
		}
	}
}
