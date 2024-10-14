package Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String title;
    private String description;
    private Map<String, Room> exits;
    private List<Item> items;
    private List<Enemy> enemies;
    private List<FriendlyNPC> friendlyNPCs;

    public Room(String title, String description) {
        this.title = title;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.friendlyNPCs = new ArrayList<>();
    }

    public void addExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Map<String, Room> getExits() {
        return exits;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void addFriendlyNPC(FriendlyNPC npc) {
        friendlyNPCs.add(npc);
    }

    public List<FriendlyNPC> getFriendlyNPCs() {
        return friendlyNPCs;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
    }
}
