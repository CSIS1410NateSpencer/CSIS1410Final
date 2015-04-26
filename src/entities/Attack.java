package entities;

import maths.Vector2;


public class Attack extends Entity{

	Entity sender;
	double timeRemaining = 25;
	boolean temporary = true;
	
	public Attack(Entity sender, Vector2 vector2, int width, int height){
		getCollider().setWidth(width);
		getCollider().setHeight(height);
		this.sender = sender;
		position = vector2;
		
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
