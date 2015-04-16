package finalProject;

import java.awt.Graphics;
import java.util.ArrayList;

import static finalProject.Tile.TILE_SIZE;

public class Animation {
	Sprite[] sprites;
	int currentIndex = 0;
	long startTime = System.currentTimeMillis();
	int delay = 100; //in milliseconds
	
	public Animation(String path, int width, int height, int numberOfSprites) {
		sprites = load(path,width,height,numberOfSprites);
		startTime = System.currentTimeMillis();
	}
	
	private static Sprite[] load(String path, int width, int height, int numberOfSprites){
		ArrayList<Sprite> sprites = new ArrayList<>();
		for (int x = 0; x < numberOfSprites; x++) {
			for (int y = 0; y < 1; y++) {
				sprites.add(Sprite.load(path,x,y,width,height));
			}
		}
		return sprites.toArray(new Sprite[]{});
	}

	Sprite currentSprite(){
		double elapsedMilliseconds = (System.currentTimeMillis() - startTime);
		currentIndex = (int)((elapsedMilliseconds / delay) % sprites.length);
		return sprites[currentIndex];
	}
}
