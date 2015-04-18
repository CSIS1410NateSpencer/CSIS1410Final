package finalProject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	final BufferedImage image;
	final int width, height;
	
	private Sprite(BufferedImage image) {
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	void draw(Graphics g, int x, int y, int width, int height) {
		g.drawImage(image, x , y , width , height , null);
	}
	void draw(Graphics g, int x, int y) {
		g.drawImage(image, x , y , width , height , null);
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
}
