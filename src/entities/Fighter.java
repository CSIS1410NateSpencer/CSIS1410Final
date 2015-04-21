package entities;


public abstract class Fighter extends Entity {
	private int health;
	protected int starterHealth;
	
	protected void initializeHealth(int health) {
		setHealth(health);
		starterHealth = getHealth();
	}
	
	abstract void move();
	abstract void attack();
	
	void takeDamage(int amount) {
		setHealth(getHealth() - amount);
		//System.out.println(this.getClass().getSimpleName() + " Health: " + health);
		if(getHealth() <=0)
			die();
	}
	
	@Override
	public final void onCollide(Entity other) {
		if(other instanceof Attack && ((Attack)other).sender.getClass() != this.getClass())
			takeDamage(1);
	}
	abstract void die();

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
