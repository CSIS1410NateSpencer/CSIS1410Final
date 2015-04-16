package finalProject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	Sprite sprite;
	static final int TILE_SIZE = 32;

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	void draw(Graphics g, int x, int y) {
		sprite.draw(g, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE,TILE_SIZE);
	}
	
	
}