package entities;


public class AttackEntity extends Entity{

	Entity sender;
	
	public AttackEntity(Entity sender){
		this.sender = sender;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollide(Entity other) {
		if(other != sender && other instanceof Fighter){
			destroy();
		}
	}
	private void destroy() {
		
	}

}
