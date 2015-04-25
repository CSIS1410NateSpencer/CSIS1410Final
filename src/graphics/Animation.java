package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Animation {
	BufferedImage[] sprites;
	int currentIndex = 0;
	long startTime = System.currentTimeMillis();
	int delay = 75; //in milliseconds
	
	public Animation(String path, int numberOfSprites) {
		sprites = load(path, numberOfSprites);
		startTime = System.currentTimeMillis();
		
	}
	
	private static BufferedImage[] load(String path, int numberOfSprites){
		ArrayList<BufferedImage> sprites = new ArrayList<>();
		
		for (int x = 0; x < numberOfSprites; x++) {
			try {
				BufferedImage rawImage = ImageIO.read(new File(path));
				int width = rawImage.getWidth() / numberOfSprites;
				int height = rawImage.getHeight();
				BufferedImage img = rawImage.getSubimage(x * width, 0, width, height);
				sprites.add(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sprites.toArray(new BufferedImage[]{});
	}

	public BufferedImage currentSprite() {
		double elapsedMilliseconds = (System.currentTimeMillis() - startTime);
				currentIndex = (int)((elapsedMilliseconds / delay) % sprites.length);
				if (currentIndex == sprites.length - 1 && !looping)
					finished = true;
				return sprites[currentIndex];
//		if (!finished) {
//			double elapsedMilliseconds = (System.currentTimeMillis() - startTime);
//			currentIndex = (int) ((elapsedMilliseconds / delay) % sprites.length);
//			if (currentIndex == sprites.length - 1 && !looping)
//				finished = true;
//			return sprites[currentIndex];
//		}
//		else return sprites[sprites.length - 1];
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
}
