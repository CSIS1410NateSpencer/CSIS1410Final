package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import entities.Enemy;
import finalProject.Game;

public class HUD extends Component{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2378444046339263101L;

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Image img1 = Toolkit.getDefaultToolkit().getImage("src/images/empty_HUD.png");
		g2d.drawImage(img1, 0, 710, this);
		
		g2d.setColor(Color.RED);
		for(int i = 0; i < Game.player.getHealth(); i++){
			g2d.fillRect(i * 8+ 62,760,6,8);
		}
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("arial", Font.BOLD, 14));
		g2d.drawString(Integer.toString(Enemy.enemies), 188, 796);
		//g2d.finalize();
	}
}
