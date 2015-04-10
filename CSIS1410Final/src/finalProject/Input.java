package finalProject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Input extends KeyAdapter{
	private static Set<Integer> pressedKeys = new HashSet<>();
	
	
	@Override
	public void keyPressed(KeyEvent e) {
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
