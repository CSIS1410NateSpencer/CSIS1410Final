package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import finalProject.Game;

public class HUD extends Component{
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Image img1 = Toolkit.getDefaultToolkit().getImage("src/images/empty_HUD.png");
		g2d.drawImage(img1, 0, 710, this);
		
		g2d.setColor(Color.RED);
		for(int i = 0; i < Game.player.getHealth(); i++){
			g2d.fillRect(i * 8+ 62,760,6,8);
		}
		
		
		System.out.println();
		//g2d.finalize();
	}
}
