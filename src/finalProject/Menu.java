package finalProject;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Menu extends Component {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580699642985275388L;

	public void render(Graphics g) {
		Image img1 = Toolkit.getDefaultToolkit().getImage("src/images/dungeon_menu.gif");
		g.drawImage(img1, 0, 0, this);
		g.finalize();
	}
}
