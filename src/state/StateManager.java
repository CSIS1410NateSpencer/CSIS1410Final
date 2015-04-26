package state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateManager {
	List<State> states = new ArrayList<>();
	Iterator<State> i;
	private State current;
	
	public void add(State state) {
		states.add(state);
	}
	
	
	public void begin() {
		i = states.iterator();
		next();
	}
	public void next(){
		if(i == null)
			System.out.println("Must call \"begin\" before using \"next\"");
		else if(i.hasNext()) {
			if(current != null)
				current.end();
			current = i.next();
			current.begin();
		}
		else
			begin();
	}
	
	public State getCurrent(){
		return current;
	}
}
