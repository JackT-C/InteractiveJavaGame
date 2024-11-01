package Library;

public class Enemy {
    //Enemy Class with enemy attributes
    private final String name;
    private int health;
    private final int attackDamage;

    public Enemy(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public int getAttackPower() {
        return attackDamage;
    }


}
