package finalProject;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Menu extends Component {
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Image img1 = Toolkit.getDefaultToolkit().getImage("src/images/dungeon_menu.gif");
		g2d.drawImage(img1, 0, 0, this);
		g2d.finalize();
	}
}
