package finalProject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maths.Vector2;

public class Input extends KeyAdapter{
	public static List<Integer> pressedKeys = new ArrayList<>();
	
	public Direction getDirection() {
		Map<Integer, Direction> directions = new HashMap<>();

		int upIndex = pressedKeys.indexOf(KeyEvent.VK_UP);
		directions.put(upIndex, Direction.Up);
		int downIndex = pressedKeys.indexOf(KeyEvent.VK_DOWN);
		directions.put(downIndex, Direction.Down);
		int leftIndex = pressedKeys.indexOf(KeyEvent.VK_LEFT);
		directions.put(leftIndex, Direction.Left);
		int rightIndex = pressedKeys.indexOf(KeyEvent.VK_RIGHT);
		directions.put(rightIndex, Direction.Right);

		int latestVertical = Math.max(upIndex, downIndex);
		if (latestVertical == -1)
			latestVertical = 100;
		int latestHorizontal = Math.max(leftIndex, rightIndex);
		if (latestHorizontal == -1)
			latestHorizontal = 100;

		int earliest = Math.min(latestHorizontal, latestVertical);

		if (earliest == 100)
			return null;
		return directions.get(earliest);

	}
	
	public Vector2 getPoint() {
		Map<Integer, Double> directions = new HashMap<>();
		
		int upIndex = pressedKeys.indexOf(KeyEvent.VK_UP);
		directions.put(upIndex, -1.0);
		
		int downIndex = pressedKeys.indexOf(KeyEvent.VK_DOWN);
		directions.put(downIndex, 1.0);
		
		int leftIndex = pressedKeys.indexOf(KeyEvent.VK_LEFT);
		directions.put(leftIndex, -1.0);
		
		int rightIndex = pressedKeys.indexOf(KeyEvent.VK_RIGHT);
		directions.put(rightIndex, 1.0);
		
		double x, y;
		
		int latestHorizontal = Math.max(leftIndex, rightIndex);
		if (latestHorizontal == -1)
			x = 0;
		else
			x = directions.get(latestHorizontal);
		
		int latestVertical = Math.max(upIndex, downIndex);
		if (latestVertical == -1)
			y = 0;
		else
			y = directions.get(latestVertical);
		
		return new Vector2(x, y);
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
