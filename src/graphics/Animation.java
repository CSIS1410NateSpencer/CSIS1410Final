package graphics;

import java.awt.Graphics;
import java.util.ArrayList;

public class Animation {
	Sprite[] sprites;
	int currentIndex = 0;
	long startTime = System.currentTimeMillis();
	int delay = 75; //in milliseconds
	
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

	public Sprite currentSprite() {
		double elapsedMilliseconds = (System.currentTimeMillis() - startTime);
		currentIndex = (int)((elapsedMilliseconds / delay) % sprites.length);
		if (currentIndex == sprites.length - 1 && !looping)
			finished = true;
		return sprites[currentIndex];
	}
	
	private boolean finished = false;
	boolean looping = false;
	public void play(){
		finished = false;
		startTime = System.currentTimeMillis();
	}

	public boolean isFinished() {
		return finished;
	}

	public void setDelay(int i) {
		delay = i;
	}

	public void setXOffset(int xOffset) {
		for (Sprite sprite : sprites) {
			sprite.setxOffset(xOffset);
		}
	}
}
