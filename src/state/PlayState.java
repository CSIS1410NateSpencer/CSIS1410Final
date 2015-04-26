package state;

import java.awt.Graphics;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import maths.Maths;
import maths.Point;
import entities.Enemy;
import entities.Entity;
import entities.Player;
import finalProject.CompareByDistanceToTarget;
import finalProject.Game;
import finalProject.TileMap;
import graphics.HUD;

public class PlayState extends State {

	
	public static TileMap tileMap;
	public static List<Entity> entities = new CopyOnWriteArrayList<>();
	public static Player player;
	public static Point cameraPosition = Point.zero();
	public HUD hud = new HUD();
	private Comparator<Entity> compareByDistanceToPlayer = new CompareByDistanceToTarget(player);
	
	public PlayState(Game game) {
		super(game);
		setupMap();
	}

	@Override
	public void begin() {
		setupEntities();
		cameraPosition = Point.zero();
	}
	
	@Override
	public void update() {
		if(!Game.audio.isPlaying())
			Game.audio.play("bgm.wav");
		for (Entity entity : entities) {
			entity.update();
		}
		
		entities.sort(compareByDistanceToPlayer);
		
		// check for intersection between entities
		for (int x = 0; x < entities.size(); x++) {
			for (int y = x; y < entities.size(); y++) {
				if (x != y)
					Entity.checkCollision(entities.get(x),
							entities.get(y));
			}
		}
		positionCamera();

		// sort entities by y position
		Collections.sort(entities);
	}

	@Override
	public void render(Graphics g) {
		//draw the map
		tileMap.draw(g);
		

		// draw objects in the level;
		for (Entity entity : entities) {
			entity.draw(g);
		}
		//draw the heads-up-display
		hud.render(g);
	}
	
	private void setupMap() {
		tileMap = new TileMap("src/dungeon.txt", 96);
		tileMap.loadTiles("src/images/dungeon.png");
	}
	private void setupEntities() {
		entities.clear();
		for (int i = 0; i < 15; i++) {
			new Enemy();
		}
		player = new Player();
		compareByDistanceToPlayer = new CompareByDistanceToTarget(player);
	}
	public void positionCamera() {
		cameraPosition.x = Maths.interpolate(cameraPosition.x, player.position.x - game.getWidth() / 2 + player.getSprite().getWidth() / 2,.05);
		cameraPosition.y = Maths.interpolate(cameraPosition.y, player.position.y - game.getHeight() / 2 + player.getSprite().getHeight() / 2,.05);
		cameraPosition.x = Maths.clamp(cameraPosition.x,0,tileMap.getTotalMapWidth() - game.getWidth());
		cameraPosition.y = Maths.clamp(cameraPosition.y,0,tileMap.getTotalMapHeight() - game.getHeight());
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
