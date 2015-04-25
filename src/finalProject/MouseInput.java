package finalProject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		// start
		if (mx >= 400 && mx <= 600) {
			if (my >= 330 && my <= 400) {
				// pressed start button
				Game.State = Game.STATE.GAME;
			}
		}
		// quit
		if (mx >= 400 && mx <= 600) {
			if (my >= 415 && my <= 465) {
				// pressed exit button
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
