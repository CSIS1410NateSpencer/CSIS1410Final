package entities;


public abstract class Fighter extends Entity {
	protected int health;
	protected int starterHealth;
	
	public Fighter() {
		health = 10;
		starterHealth = health;
	}
	
	abstract void move();
	abstract void attack();
	
	void takeDamage(int amount) {
		health-=amount;
		System.out.println(this.getClass().getSimpleName() + " Health: " + health);
		if(health <=0)
			die();
	}
	
	@Override
	public final void onCollide(Entity other) {
		if(other instanceof Attack && ((Attack)other).sender != this)
			takeDamage(1);
	}
	abstract void die();
}
