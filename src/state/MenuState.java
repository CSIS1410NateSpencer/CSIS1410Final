package state;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import finalProject.Game;

public class MenuState extends State implements MouseListener, MouseMotionListener{

	private int x = 322;
	private int y = 329;
	private int gap = 105;
	private int width = 353;
	private int height = 80;
	
	public MenuState(Game game) {
		super(game);
	}

	boolean exit = false;
	boolean next = false;
	private boolean overNext = false;
	private boolean overExit = false;
	public void render(Graphics g) {
		Image img1 = Toolkit.getDefaultToolkit().getImage("src/images/dungeon_menu.gif");
		g.drawImage(img1, 0, 0, null);
		g.setColor(Color.RED);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(10));
		if(overNext)
			g.drawRect(x, y, width, height);
		if(overExit)
			g.drawRect(x, y + gap, width, height);
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
			System.exit(0);
		if(next)
			Game.stateManager.next();
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
		if (overNext) {
				// pressed start button
				next = true;
		}
		// quit
		if (overExit) {
				exit = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		if(p.x > x && p.x < x + width &&
				p.y > y && p.y < y + height)
			overNext = true;
		else
			overNext = false;
		if(p.x > x && p.x < x + width &&
				p.y > y + gap && p.y < y + gap + height)
			overExit = true;
		else overExit = false;
	}


	@Override
	public void end() {
	}

	
}
