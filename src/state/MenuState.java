package state;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import finalProject.Game;

public class MenuState extends State implements MouseListener{

	public MenuState(Game game) {
		super(game);
	}

	boolean exit = false;
	boolean next = false;
	public void render(Graphics g) {
		Image img1 = Toolkit.getDefaultToolkit().getImage("src/images/dungeon_menu.gif");
		g.drawImage(img1, 0, 0, null);
		g.finalize();
	}

	
	@Override
	public void begin() {
		exit = false;
		next = false;
	}
	
	@Override
	public
	void update() {
		if(exit)
			System.exit(1);
		if(next)
			Game.manager.next();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		// start
		if (mx >= 400 && mx <= 600) {
			if (my >= 330 && my <= 400) {
				// pressed start button
				next = true;
			}
		}
		// quit
		if (mx >= 400 && mx <= 600) {
			if (my >= 415 && my <= 465) {
				// pressed exit button
				exit = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	
}
