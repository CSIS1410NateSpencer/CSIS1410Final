package finalProject;

public abstract class Fighter extends Entity {
	protected int health;
	abstract void move();
	abstract void attack();
	void takeDamage(int amount) {
		health-=amount;
		if(health <=0)
			die();
	}
	abstract void die();
}
