package Library;

public class Player {
    //player class with player attributes
    public static int level;
    private static int experience;
    public static int health;
    private static int maxHealth;
    public static int playerAttack;

    public Player() {
        level = 1;
        experience = 0;
        maxHealth = 100;
        health = maxHealth;
        playerAttack = 20;
    }

    public static int getLevel() {
        return level;
    }

    public static int getHealth() {
        return health;
    }

    public static int getPlayerAttack(){
        return playerAttack;
    }


    // Adds XP, checks for level up
    public static void addExperience(int xp) {
        experience += xp;
        System.out.println("You have gained " + xp + " XP.");
        while (experience >= getXpThreshold()) {
            levelUp();
        }
    }

    private static int getXpThreshold() {
        // XP required increases with each level to make each level harder
        return level * 100;
    }


    // Level Up method
    private static void levelUp() {
        level++;
        experience -= getXpThreshold();

        // Increase health and max health on leveling up
        maxHealth += 20;
        health = maxHealth;


        Game.gameText.append("\n Congratulations! you leveled up to level " + level + "!");
    }

    // Method to deal damage to the player
    public static void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }


}
