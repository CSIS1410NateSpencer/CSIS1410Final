package finalProject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Input extends KeyAdapter{
	public static List<Integer> pressedKeys = new ArrayList<>();
	
	Direction  getDirection(){
		Map<Integer, Direction> directions = new HashMap<>();
		if(isPressed(KeyEvent.VK_UP)) {
			directions.put(pressedKeys.indexOf(KeyEvent.VK_UP), Direction.Up);
		}
		if(isPressed(KeyEvent.VK_DOWN)) {
			directions.put(pressedKeys.indexOf(KeyEvent.VK_DOWN), Direction.Down);
		}
		
		if(isPressed(KeyEvent.VK_LEFT)) {
			directions.put(pressedKeys.indexOf(KeyEvent.VK_LEFT), Direction.Left);
		}
		if(isPressed(KeyEvent.VK_RIGHT)) {
			directions.put(pressedKeys.indexOf(KeyEvent.VK_RIGHT), Direction.Right);
		}

		int latest = -1;
		for (Integer integer : directions.keySet()) {
			if(integer > latest)
				latest = integer;
		}
		return directions.get(latest);
	}
	
	Point getPoint(){
		double x = 0, y = 0;
		if(isPressed(KeyEvent.VK_UP)) {
			y = -1;
		}
		if(isPressed(KeyEvent.VK_DOWN)) {
			y = 1;
		}
		if(isPressed(KeyEvent.VK_LEFT)) {
			x = -1;
		}
		if(isPressed(KeyEvent.VK_RIGHT)) {
			x = 1;
		}
		return new Point(x,y);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(!pressedKeys.contains(e.getKeyCode()))
			pressedKeys.add(e.getKeyCode());
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		if(pressedKeys.contains(e.getKeyCode()))
			pressedKeys.remove((Integer)e.getKeyCode());
	}
	
	public boolean isPressed(int key) {
		return pressedKeys.contains(key);
	}
}
