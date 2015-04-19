package finalProject;
import graphics.Tile;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

public class TileMap {
	
	private int x;
	private int y;
	
	private int tileSize;
	private int[][] map;
	private int mapWidth;
	private int mapHeight;
	
	private BufferedImage tileset;
	private Tile[][] tile;
	
	private int minx;
	private int miny;
	private int maxx = 0;
	private int maxy = 0;
	
	public TileMap(String s, int tileSize) {
		
		this.tileSize = tileSize;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(s));
			
			mapWidth = Integer.parseInt(br.readLine());
			mapHeight = Integer.parseInt(br.readLine());
			map = new int[mapHeight][mapWidth];
			
			minx = Game.WIDTH - mapWidth * tileSize;
			miny = Game.HEIGHT - mapHeight * tileSize;
			
			String delimiters = ",";
			for(int row = 0; row < mapHeight; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for(int col = 0; col < mapWidth; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e) {}
		
	}
	
	public void loadTiles(String s) {
		
		try {
			
			tileset = ImageIO.read(new File(s));
			int numTilesAcross = (tileset.getWidth()) / (tileSize);
			tile = new Tile[2][numTilesAcross];
			
			BufferedImage subimage;
			for(int col = 1; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(
					col * tileSize - 32,
					0,
					tileSize,
					tileSize
				);
				tile[0][col] = new Tile(subimage);
				subimage = tileset.getSubimage(
					col * tileSize - 32,
					tileSize,
					tileSize,
					tileSize
				);
				tile[1][col] = new Tile(subimage);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getx() { return x; }
	public int gety() { return y; }
	
	public int getColTile(int x) {
		return x / tileSize;
	}
	public int getRowTile(int y) {
		return y / tileSize;
	}
	public int getTile(int row, int col) {
		return map[row][col];
	}
	public int getTileSize() {
		return tileSize;
	}
	
//	public boolean isBlocked(int row, int col) {
//		int rc = map[row][col];
//		int r = rc / tiles[0].length;
//		int c = rc % tiles[0].length;
//		return tiles[r][c].isBlocked();
//	}
//	
	public void setx(int i) {
		x = i;
		if(x < minx) {
			x = minx;
		}
		if(x > maxx) {
			x = maxx;
		}
	}
	public void sety(int i) {
		y = i;
		if(y < miny) {
			y = miny;
		}
		if(y > maxy) {
			y = maxy;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		
		for(int row = 0; row < mapHeight; row++) {
			for(int col = 0; col < mapWidth; col++) {
				
				int rc = map[row][col];
				
				int r = rc / tile[0].length;
				int c = rc % tile[0].length;
				
				g.drawImage(
					tile[r][c].getImage(),
					x + col * tileSize - (int)Game.cameraPosition.x,
					y + row * tileSize - (int)Game.cameraPosition.y,
					null
				);
				
			}
		}
		
	}
	
}
