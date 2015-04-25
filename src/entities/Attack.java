package entities;

import maths.Point;


public class Attack extends Entity{

	Entity sender;
	double timeRemaining = 25;
	boolean temporary = true;
	
	public Attack(Entity sender, Point point, int width, int height){
		collider.width = width;
		collider.height = height;
		this.sender = sender;
		position = point;
		
	}
	@Override
	public void update() {
		if(temporary)
			timeRemaining -= 1;
		if(timeRemaining == 0)
			destroy();
	}

	@Override
	public void onCollide(Entity other) {
		if(temporary && other != sender && other instanceof Fighter){
			destroy();
		}
	}

}
