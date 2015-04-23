package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	final BufferedImage image;
	private final int width;
	private final int height;
	private int xOffset;
	private int yOffset;
	
	public Sprite(BufferedImage image) {
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	void draw(Graphics g, int x, int y, int width, int height) {
		if(image != null)
			g.drawImage(image, x + xOffset, y + yOffset, width , height , null);
	}
	
	public void draw(Graphics g, int x, int y) {
		draw(g, x, y, getWidth(), getHeight());
	}
	
	public static Sprite load(String path, int x, int y, int width, int height) {
		try {
			BufferedImage rawImage = ImageIO.read(new File(path));
			
			int[] pixels = new int[width * height];
			rawImage.getRGB(x * width, y * height, width, height, pixels, 0, width);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			image.setRGB(0, 0, width, height, pixels, 0, width);
			return new Sprite(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Sprite(null);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
}
