package Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RoomType {
    //Enum to store all info on rooms
    MYSTICAL_GROVE("Mystical Grove",
            "You stand in a serene grove surrounded by ancient trees with glowing leaves.",
            new ArrayList<>(List.of(
                    new Item("Silver Dagger", "A finely crafted dagger with a silver blade."),
                    new Item("Healing Herb", "A rare herb with restorative properties."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Elf", "Welcome to the mystical grove, traveler."),
                    new FriendlyNPC("Forest Dweller", "The trees whisper to those who listen."))),
            new ArrayList<>(List.of(new Enemy("Witch", 15, 7))),
            new HashMap<>()),

    ENCHANTED_CAVERN("Enchanted Cavern",
            "Sparkling crystals illuminate the walls of this mystical cavern.",
            new ArrayList<>(List.of(
                    new Item("Crystal Amulet", "A radiant amulet with mystical energy."),
                    new Item("Ancient Coin", "A coin from an era long forgotten, embedded with crystal shards."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Dwarf", "Ho there, friend! Care for a pint of ale?"),
                    new FriendlyNPC("Crystal Gnome", "Mind the crystals; they’re precious!"))),
            new ArrayList<>(List.of(new Enemy("Crystal King", 20, 20))),
            new HashMap<>()),

    GOBLIN_CAMP("Goblin Camp",
            "A makeshift campsite of goblins, scattered with crude weapons and discarded bones.",
            new ArrayList<>(List.of(
                    new Item("Goblin Club", "A crude weapon used by goblins."),
                    new Item("Rusty Helmet", "A dented, rusty helmet offering minimal protection."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Goblin Scout", "What do you want, human?"),
                    new FriendlyNPC("Goblin Trader", "Got any shinies to trade?"))),
            new ArrayList<>(List.of(new Enemy("Goblin", 10, 5))),
            new HashMap<>()),

    DRAGON_LAIR("Dragon Lair",
            "A massive cavern with molten lava flows and the echoes of a distant dragon's roar.",
            new ArrayList<>(List.of(
                    new Item("Dragon Scale", "A shimmering scale shed by a mighty dragon."),
                    new Item("Molten Gem", "A gem radiating intense heat, said to be born of dragon fire."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Ancient Dragon Spirit", "I am but a whisper of the dragon's might."))),
            new ArrayList<>(List.of(new Enemy("Dragon", 250, 1000))),
            new HashMap<>()),

    FORGOTTEN_TEMPLE("Forgotten Temple",
            "A crumbling temple filled with ancient relics.",
            new ArrayList<>(List.of(
                    new Item("Ancient Tome", "A dusty tome containing forgotten knowledge."),
                    new Item("Golden Idol", "A small idol, heavy and valuable, depicting an unknown deity."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Temple Guardian", "I protect the ancient knowledge here."))),
            new ArrayList<>(List.of(new Enemy("Mummy", 18, 8))),
            new HashMap<>()),

    HAUNTED_FOREST("Haunted Forest",
            "Twisted trees and eerie whispers fill this dark and ominous forest.",
            new ArrayList<>(List.of(
                    new Item("Cursed Pendant", "A pendant said to carry the curse of the forest."),
                    new Item("Ghostly Cloak", "A cloak that shimmers, almost ethereal in appearance."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Forest Guardian", "Tread carefully; this forest is alive."))),
            new ArrayList<>(List.of(new Enemy("Ghost of the Forest", 15, 100))),
            new HashMap<>()),

    DARK_CAVE("Dark Cave",
            "You find yourself in a dimly lit cave, the air thick with an unsettling presence.",
            new ArrayList<>(List.of(
                    new Item("Shadow Cloak", "A cloak woven from the darkness of the cave."),
                    new Item("Obsidian Dagger", "A dagger with a blade as dark as the cave."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Hermit", "Who ventures here, in the darkness?"))),
            new ArrayList<>(List.of(new Enemy("Shadow Beast", 18, 8))),
            new HashMap<>()),

    CRYSTAL_GROTTO("Crystal Grotto",
            "Glowing crystals line the walls of this underground chamber, casting vibrant hues of light.",
            new ArrayList<>(List.of(
                    new Item("Crystal Shard", "A shard of a larger crystal, pulsating with magical energy."),
                    new Item("Enchanted Gemstone", "A gemstone with a faint inner glow."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Crystal Sage", "The crystals speak to those who listen."))),
            new ArrayList<>(List.of(new Enemy("Crystal Spider", 12, 5))),
            new HashMap<>()),

    WITCHES_HUT("Witches Hut",
            "A crooked hut sits nestled in a clearing, emanating an aura of magic.",
            new ArrayList<>(List.of(
                    new Item("Potion Ingredients", "Various herbs and ingredients used in potion-making."),
                    new Item("Magic Candle", "A candle with a faint green glow and a strange aroma."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Witch", "I sure hope I don't get corrupted after doing all this magic..."))),
            new ArrayList<>(List.of(new Enemy("Corrupted Witch", 16, 6))),
            new HashMap<>()),

    FROZEN_TUNDRA("Frozen Tundra",
            "Snow-covered plains stretch as far as the eye can see, the bitter cold biting at your skin.",
            new ArrayList<>(List.of(
                    new Item("Frostbite Dagger", "A dagger that deals extra cold damage."),
                    new Item("Ice Crystal", "A rare, translucent crystal that never melts."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Yeti Hunter", "Bring me the beast of the ice, I need a challenge."))),
            new ArrayList<>(List.of(new Enemy("YETI", 200, 101))),
            new HashMap<>()),

    UNDERWATER_CAVERN("Underwater Cavern",
            "Submerged beneath the ocean's surface, this cavern teems with bioluminescent creatures.",
            new ArrayList<>(List.of(
                    new Item("Sea Serpent Scale", "A scale from a legendary sea serpent."),
                    new Item("Coral Necklace", "A necklace crafted from colorful ocean coral."))),
            new ArrayList<>(List.of(
                    new FriendlyNPC("Mermaid", "Who are you? It's been years since anyone came here."))),
            new ArrayList<>(List.of(new Enemy("Kraken King", 200, 10))),
            new HashMap<>());


    private final String title;
    private final String description;
    private final List<Item> items;
    private final List<FriendlyNPC> friendlyNPCs;
    private final List<Enemy> enemies;
    private Map<String, RoomType> exits;

    RoomType(String title, String description, List<Item> items, List<FriendlyNPC> friendlyNPCs, List<Enemy> enemies, Map<String, RoomType> exits) {
        this.title = title;
        this.description = description;
        this.items = items;
        this.friendlyNPCs = friendlyNPCs;
        this.enemies = enemies;
        this.exits = exits;
    }

    public void setExits(Map<String, RoomType> exits) {
        this.exits = exits;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<FriendlyNPC> getFriendlyNPCs() {
        return friendlyNPCs;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Map<String, RoomType> getExits() {
        return exits;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public static RoomType getByTitle(String title) {
        for (RoomType room : RoomType.values()) {
            if (room.getTitle().equalsIgnoreCase(title)) {
                return room;
            }
        }
        return null;
    }

    //Exits:
    public static void initializeExits() {
        MYSTICAL_GROVE.setExits(Map.of(
                "north", ENCHANTED_CAVERN,
                "east", GOBLIN_CAMP,
                "south", DRAGON_LAIR,
                "west", FORGOTTEN_TEMPLE));

        ENCHANTED_CAVERN.setExits(Map.of(
                "south", MYSTICAL_GROVE,
                "east", CRYSTAL_GROTTO,
                "west", WITCHES_HUT));

        GOBLIN_CAMP.setExits(Map.of(
                "west", MYSTICAL_GROVE,
                "north", CRYSTAL_GROTTO,
                "south", FROZEN_TUNDRA));

        DRAGON_LAIR.setExits(Map.of(
                "east", FROZEN_TUNDRA,
                "north", MYSTICAL_GROVE));

        FORGOTTEN_TEMPLE.setExits(Map.of(
                "north", WITCHES_HUT,
                "east", MYSTICAL_GROVE,
                "west", HAUNTED_FOREST));

        HAUNTED_FOREST.setExits(Map.of(
                "east", FORGOTTEN_TEMPLE));

        DARK_CAVE.setExits(Map.of(
                "west", CRYSTAL_GROTTO,
                "east", UNDERWATER_CAVERN));

        CRYSTAL_GROTTO.setExits(Map.of(
                "east", DARK_CAVE,
                "south", GOBLIN_CAMP,
                "west", ENCHANTED_CAVERN));

        WITCHES_HUT.setExits(Map.of(
                "east", ENCHANTED_CAVERN,
                "south", FORGOTTEN_TEMPLE));

        FROZEN_TUNDRA.setExits(Map.of(
                "west", DRAGON_LAIR,
                "north", GOBLIN_CAMP));

        UNDERWATER_CAVERN.setExits(Map.of(
                "west", DARK_CAVE));
    }
}


