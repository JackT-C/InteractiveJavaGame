package Library;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Game extends JFrame {
    private JTextArea gameText;
    private JButton lookButton, moveButton, interactButton, takeItemButton, attackButton, mapButton, inventoryButton, saveButton, loadButton, darkmodeButton;
    private JPanel buttonPanel;
    private Room currentRoom;
    private Room startingRoom;
    private List<Item> inventory;
    private boolean isDarkMode = false;
    private int playerHealth, playerAttack, playerXP, playerLevel;
    private int playerMaxXP;

    public Game() {
        setTitle("Adventure Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameText = new JTextArea();
        gameText.setEditable(false);
        gameText.setLineWrap(true);
        gameText.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(gameText);
        add(scrollPane, BorderLayout.CENTER);

        lookButton = new JButton("Look");
        moveButton = new JButton("Move");
        interactButton = new JButton("Interact");
        takeItemButton = new JButton("Take Item");
        attackButton = new JButton("Attack");
        mapButton = new JButton("Map");
        inventoryButton = new JButton("Inventory");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        darkmodeButton = new JButton("Dark Mode");

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 5));
        buttonPanel.add(lookButton);
        buttonPanel.add(moveButton);
        buttonPanel.add(interactButton);
        buttonPanel.add(takeItemButton);
        buttonPanel.add(attackButton);
        buttonPanel.add(mapButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(darkmodeButton);
        add(buttonPanel, BorderLayout.SOUTH);


        inventory = new ArrayList<>();
        playerHealth = 100;
        playerAttack = 10;
        playerXP = 0;
        playerLevel = 1;
        playerMaxXP = 100;

        createRooms();
        currentRoom = startingRoom;
        displayCurrentRoom();


        lookButton.addActionListener(e -> displayCurrentRoom());
        moveButton.addActionListener(e -> move());
        interactButton.addActionListener(e -> interactWithNPC());
        takeItemButton.addActionListener(e -> takeItem());
        attackButton.addActionListener(e -> attack());
        mapButton.addActionListener(e -> displayMap());
        inventoryButton.addActionListener(e -> displayInventory());
        saveButton.addActionListener(e -> saveGame());
        loadButton.addActionListener(e -> loadGame());
        darkmodeButton.addActionListener(e -> darkMode());
    }

    private void darkMode() {
        if (isDarkMode) {
            gameText.setBackground(Color.WHITE);
            gameText.setBackground(Color.WHITE);
            gameText.setForeground(Color.BLACK);
            buttonPanel.setBackground(Color.LIGHT_GRAY);

            for (Component c : buttonPanel.getComponents()) {
                if (c instanceof JButton) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
            }
            darkmodeButton.setText("Dark Mode");
        } else {
            gameText.setBackground(Color.DARK_GRAY);
            gameText.setBackground(Color.DARK_GRAY);
            gameText.setForeground(Color.WHITE);
            buttonPanel.setBackground(Color.BLACK);

            for (Component c : buttonPanel.getComponents()) {
                if (c instanceof JButton) {
                    c.setBackground(Color.BLACK);
                    c.setForeground(Color.WHITE);
                }
            }
            darkmodeButton.setText("Light Mode");
        }
        isDarkMode = !isDarkMode;
    }

    private void createRooms() {
        Room mysticalGrove = new Room("Mystical Grove", "You stand in a serene grove surrounded by ancient trees with glowing leaves.");
        Room enchantedCavern = new Room("Enchanted Cavern", "Sparkling crystals illuminate the walls of this mystical cavern.");
        Room dragonLair = new Room("Dragon Lair", "A massive cavern with molten lava flows and the echoes of a distant dragon's roar.");
        Room forgottenTemple = new Room("Forgotten Temple", "A crumbling temple filled with ancient relics and a sense of foreboding.");
        Room hauntedForest = new Room("Haunted Forest", "Twisted trees and eerie whispers fill this dark and ominous forest.");
        Room darkCave = new Room("Dark Cave", "You find yourself in a dimly lit cave, the air thick with an unsettling presence.");
        Room crystalGrotto = new Room("Crystal Grotto", "Glowing crystals line the walls of this underground chamber, casting vibrant hues of light.");
        Room witchesHut = new Room("Witches Hut", "A crooked hut sits nestled in a clearing, emanating an aura of magic.");
        Room goblinCamp = new Room("Goblin Camp", "A makeshift campsite of goblins, scattered with crude weapons and discarded bones.");
        Room frozenTundra = new Room("Frozen Tundra", "Snow-covered plains stretch as far as the eye can see, the bitter cold biting at your skin.");
        Room underwaterCavern = new Room("Underwater Cavern", "Submerged beneath the ocean's surface, this cavern teems with bioluminescent creatures.");

        mysticalGrove.addExit("north", enchantedCavern);
        mysticalGrove.addExit("east", goblinCamp);
        mysticalGrove.addExit("south", dragonLair);
        mysticalGrove.addExit("west", forgottenTemple);

        enchantedCavern.addExit("west", witchesHut);
        enchantedCavern.addExit("south", mysticalGrove);
        enchantedCavern.addExit("east", crystalGrotto);

        dragonLair.addExit("north", mysticalGrove);
        dragonLair.addExit("east", frozenTundra);

        forgottenTemple.addExit("north", witchesHut);
        forgottenTemple.addExit("east", mysticalGrove);
        forgottenTemple.addExit("west", hauntedForest);

        hauntedForest.addExit("east", forgottenTemple);

        darkCave.addExit("west", crystalGrotto);
        darkCave.addExit("east", underwaterCavern);

        crystalGrotto.addExit("east", darkCave);
        crystalGrotto.addExit("south", goblinCamp);
        crystalGrotto.addExit("west", enchantedCavern);

        witchesHut.addExit("east", enchantedCavern);
        witchesHut.addExit("south", forgottenTemple);

        goblinCamp.addExit("north", crystalGrotto);
        goblinCamp.addExit("south", frozenTundra);
        goblinCamp.addExit("west", mysticalGrove);

        frozenTundra.addExit("north", goblinCamp);
        frozenTundra.addExit("west", dragonLair);

        underwaterCavern.addExit("west", darkCave);

        startingRoom = mysticalGrove;

        mysticalGrove.addItem(new Item("Silver Dagger", "A finely crafted dagger with a silver blade."));
        mysticalGrove.addItem(new Item("Elven Bow", "A bow of exquisite craftsmanship, said to be favored by elven archers."));
        enchantedCavern.addItem(new Item("Crystal Amulet", "A radiant amulet with mystical energy."));
        enchantedCavern.addItem(new Item("Glowing Mushrooms", "Bioluminescent fungi that emit a soft, soothing light."));
        dragonLair.addItem(new Item("Dragon Scale", "A shimmering scale shed by a mighty dragon."));
        dragonLair.addItem(new Item("Fiery Sword", "A sword imbued with the power of dragonfire."));
        forgottenTemple.addItem(new Item("Ancient Tome", "A dusty tome containing forgotten knowledge."));
        forgottenTemple.addItem(new Item("Ornate Staff", "A staff adorned with intricate carvings, once wielded by a powerful mage."));
        hauntedForest.addItem(new Item("Spectral Lantern", "A lantern that shines with an otherworldly light, capable of revealing hidden truths."));
        hauntedForest.addItem(new Item("Cursed Pendant", "A pendant said to carry the curse of the forest, bringing misfortune to its bearer."));
        darkCave.addItem(new Item("Shadow Cloak", "A cloak woven from the darkness of the cave, granting its wearer the ability to blend into the shadows."));
        darkCave.addItem(new Item("Cave Crystals", "Rare crystals that emit a faint, eerie glow in the darkness."));
        crystalGrotto.addItem(new Item("Crystal Shard", "A shard of a larger crystal, pulsating with magical energy."));
        crystalGrotto.addItem(new Item("Luminous Coral", "Coral formations that emit a soft, ethereal glow."));
        witchesHut.addItem(new Item("Potion Ingredients", "Various herbs and ingredients used in potion-making."));
        witchesHut.addItem(new Item("Spellbook", "A weathered tome filled with arcane incantations and rituals."));
        goblinCamp.addItem(new Item("Stolen Loot", "Various trinkets and treasures pilfered by goblin raiders."));
        goblinCamp.addItem(new Item("Goblin Ale", "A foul-smelling brew favored by goblins, rumored to grant temporary strength."));
        frozenTundra.addItem(new Item("Ice Shard", "A sharp shard of ice, imbued with the biting cold of the tundra."));
        frozenTundra.addItem(new Item("Frostbitten Relics", "Ancient artifacts preserved in ice, waiting to be unearthed."));
        underwaterCavern.addItem(new Item("Seashell Necklace", "A necklace crafted from colorful seashells, prized by merfolk and sailors alike."));
        underwaterCavern.addItem(new Item("Sunken Treasure", "A chest filled with gold coins and jewels, lost to the depths of the ocean."));

        if (Math.random() * 100 > 25) {
            mysticalGrove.addEnemy(new Enemy("Witch", 15, 7));
        }
        if (Math.random() * 100 > 50) {
            mysticalGrove.addEnemy(new Enemy("Ent", 20, 10));
        }

        if (Math.random() * 100 > 25) {
            enchantedCavern.addEnemy(new Enemy("Crystal King", 20, 20));
        }
        if (Math.random() * 100 > 50) {
            enchantedCavern.addEnemy(new Enemy("Golem", 25, 15));
        }

        if (Math.random() * 100 > 50) {
            dragonLair.addEnemy(new Enemy("Dragon", 250, 10));
        }
        if (Math.random() * 100 > 75) {
            dragonLair.addEnemy(new Enemy("Fire Elemental", 30, 12));
        }

        if (Math.random() * 100 > 30) {
            forgottenTemple.addEnemy(new Enemy("Dark Wizard", 20, 5));
        }
        if (Math.random() * 100 > 60) {
            forgottenTemple.addEnemy(new Enemy("Mummy", 18, 8));
        }

        if (Math.random() * 100 > 40) {
            hauntedForest.addEnemy(new Enemy("Ghost of the Forest", 15, 6));
        }
        if (Math.random() * 100 > 70) {
            hauntedForest.addEnemy(new Enemy("Werewolf", 22, 12));
        }

        if (Math.random() * 100 > 35) {
            darkCave.addEnemy(new Enemy("Shadow Beast", 18, 8));
        }
        if (Math.random() * 100 > 65) {
            darkCave.addEnemy(new Enemy("Bat Swarm", 10, 4));
        }

        if (Math.random() * 100 > 30) {
            crystalGrotto.addEnemy(new Enemy("Crystal Spider", 12, 5));
        }
        if (Math.random() * 100 > 60) {
            crystalGrotto.addEnemy(new Enemy("Rock Elemental", 20, 10));
        }

        if (Math.random() * 100 > 20) {
            witchesHut.addEnemy(new Enemy("Coven Witch", 16, 6));
        }
        if (Math.random() * 100 > 50) {
            witchesHut.addEnemy(new Enemy("Animated Broom", 8, 3));
        }

        if (Math.random() * 100 > 30) {
            goblinCamp.addEnemy(new Enemy("Goblin Warrior", 14, 5));
        }
        if (Math.random() * 100 > 65) {
            goblinCamp.addEnemy(new Enemy("Orc Berserker", 18, 8));
        }

        if (Math.random() * 100 > 40) {
            frozenTundra.addEnemy(new Enemy("Ice Elemental", 16, 7));
        }
        if (Math.random() * 100 > 70) {
            frozenTundra.addEnemy(new Enemy("Frost Troll", 20, 10));
        }
        if (Math.random() * 100 > 5) {
            frozenTundra.addEnemy(new Enemy("YETI", 200, 10));
        }

        if (Math.random() * 100 > 35) {
            underwaterCavern.addEnemy(new Enemy("Siren", 14, 6));
        }
        if (Math.random() * 100 > 65) {
            underwaterCavern.addEnemy(new Enemy("Giant Crab", 18, 8));
        }

        // Add NPCs to Rooms
        mysticalGrove.addFriendlyNPC(new FriendlyNPC("Elf", "Welcome to the mystical grove, traveler."));
        mysticalGrove.addFriendlyNPC(new FriendlyNPC("Druid", "The ancient spirits watch over this grove, guiding those who seek harmony with nature."));
        enchantedCavern.addFriendlyNPC(new FriendlyNPC("Dwarf", "Ho there, friend! Care for a pint of ale?"));
        forgottenTemple.addFriendlyNPC(new FriendlyNPC("Wizard", "Greetings, seeker of knowledge..."));
        forgottenTemple.addFriendlyNPC(new FriendlyNPC("Monk", "Peace be upon you, traveler. Seek solace and enlightenment within these ancient walls."));
        dragonLair.addFriendlyNPC(new FriendlyNPC("Dragonkin", "Hail, brave adventurer! I am a humble servant of the dragon, guardian of these fiery halls."));
        hauntedForest.addFriendlyNPC(new FriendlyNPC("Spirit", "Traveler, beware! These woods are haunted by the restless spirits of those who once dwelled here."));
        darkCave.addFriendlyNPC(new FriendlyNPC("Cave Dweller", "Welcome to the depths, traveler. Few venture into the darkness willingly..."));
        crystalGrotto.addFriendlyNPC(new FriendlyNPC("Crystal Miner", "Welcome, friend! I seek rare gems and crystals to mine from these caverns."));
        witchesHut.addFriendlyNPC(new FriendlyNPC("Familiar", "Meow! I am the witch's familiar, guardian of her secrets and companion in the night."));
        frozenTundra.addFriendlyNPC(new FriendlyNPC("Yeti Hunter", "Greetings, fellow adventurer! I seek the legendary yeti of the tundra..."));
        underwaterCavern.addFriendlyNPC(new FriendlyNPC("Mermaid", "Welcome, surface dweller! Are you here to explore the wonders of the deep? Beware, for the ocean holds many secrets."));
    }

    // Method to display the current room's details
    private void displayCurrentRoom() {
        gameText.setText(currentRoom.getTitle() + "\n" + currentRoom.getDescription());

        // Show available exits
        StringBuilder exits = new StringBuilder("Exits: ");
        for (String exit : currentRoom.getExits().keySet()) {
            exits.append(exit).append(" ");
        }
        gameText.append("\n" + exits.toString());

        // Show available items
        List<Item> items = currentRoom.getItems();
        if (!items.isEmpty()) {
            gameText.append("\nItems in the room:");
            for (Item item : items) {
                gameText.append("\n- " + item.getName() + ": " + item.getDescription());
            }
        }

        // Show available NPCs
        List<FriendlyNPC> npcs = currentRoom.getFriendlyNPCs();
        if (!npcs.isEmpty()) {
            gameText.append("\nNPCs in the room:");
            for (FriendlyNPC npc : npcs) {
                gameText.append("\n- " + npc.getName());
            }
        }

        // Show enemies
        List<Enemy> enemies = currentRoom.getEnemies();
        if (!enemies.isEmpty()) {
            gameText.append("\nEnemies in the room:");
            for (Enemy enemy : enemies) {
                gameText.append("\n- " + enemy.getName() + " (Health: " + enemy.getHealth() + ")");
            }
        }
    }

    private void displayMap() {
        String map =
                "\n" +
                        "                                       Witches Hut  -  Enchanted Cavern - Crystal Grotto - Dark Cave - Underwater Cave\n" +
                        "                                                      |                        |                                 |  \n" +
                        "Haunted Forest - Forgotten Temple - Mystical Grove - Goblin Camp\n" +
                        "                                                                                 |                              |   \n" +
                        "                                                                        Dragons Lair  -  Frozen Tundra\n" +
                        "\n";

        JOptionPane.showMessageDialog(this, map, "Game Map", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to handle movement between rooms
    private void move() {
        String direction = JOptionPane.showInputDialog(this, "Which direction?");
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            displayCurrentRoom();
        } else {
            gameText.setText("You can't move in that direction.");
        }
    }

    // Method to interact with NPCs in the current room
    private void interactWithNPC() {
        List<FriendlyNPC> npcs = currentRoom.getFriendlyNPCs();
        if (!npcs.isEmpty()) {
            FriendlyNPC npc = npcs.get(0);  // For simplicity, interact with the first NPC
            gameText.setText(npc.getName() + ": " + npc.getGreeting());
        } else {
            gameText.setText("There is no one to interact with.");
        }
    }

    // Method to handle item pickup
    private void takeItem() {
        List<Item> items = currentRoom.getItems();
        if (!items.isEmpty()) {
            Item item = items.get(0);  // For simplicity, take the first item
            inventory.add(item);
            currentRoom.removeItem(item); // Remove the item from the room
            gameText.setText("You took the " + item.getName() + ".");
            playerXP += 10; // Example XP gain for taking an item
            checkLevelUp(); // Check for level up after gaining XP
        } else {
            gameText.setText("There are no items to take.");
        }
    }

    // Method to check for level up
    private void checkLevelUp() {
        if (playerXP >= playerMaxXP) {
            playerLevel++;
            playerXP -= playerMaxXP; // Reset XP for the next level
            playerMaxXP += 50; // Increase max XP for the next level
            gameText.append("\nCongratulations! You've leveled up to Level " + playerLevel + "!");
        }
    }


    private void displayInventory() {
        StringBuilder inventoryDisplay = new StringBuilder("Your Inventory:\n");
        if (inventory.isEmpty()) {
            inventoryDisplay.append("Your inventory is empty.");
        } else {
            for (Item item : inventory) {
                inventoryDisplay.append("- ").append(item.getName()).append(": ").append(item.getDescription()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, inventoryDisplay.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
    }


    private void saveGame() {
        try (FileWriter writer = new FileWriter("savegame.txt")) {
            writer.write("Player Health: " + playerHealth + "\n");
            writer.write("Player Attack: " + playerAttack + "\n");
            writer.write("Player XP: " + playerXP + "\n");
            writer.write("Player Level: " + playerLevel + "\n");

            writer.write("Inventory: \n");
            for (Item item : inventory) {
                writer.write(item.getName() + "\n");
            }

            writer.write("Current Room: " + currentRoom.getTitle() + "\n");
            JOptionPane.showMessageDialog(this, "Game saved successfully!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving game: " + e.getMessage());
        }
    }




    private void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader("savefile.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                switch (parts[0]) {
                    case "playerHealth" -> playerHealth = Integer.parseInt(parts[1]);
                    case "playerXP" -> playerXP = Integer.parseInt(parts[1]);
                    case "playerLevel" -> playerLevel = Integer.parseInt(parts[1]);
                    case "playerMaxXP" -> playerMaxXP = Integer.parseInt(parts[1]);
                    case "inventory" -> inventory = new ArrayList(List.of(parts[1].split(",")));
                }
            }
            gameText.setText("Game Loaded!");
            displayCurrentRoom();
        } catch (IOException e) {
            gameText.setText("Failed to load the game.");
            e.printStackTrace();
        }
    }




    private void attack() {
        List<Enemy> enemies = currentRoom.getEnemies();
        if (!enemies.isEmpty()) {
            Enemy enemy = enemies.get(0);
            enemy.takeDamage(playerAttack);
            gameText.setText("You attacked the " + enemy.getName() + " for " + playerAttack + " damage!");
            if (enemy.isDefeated()) {
                gameText.append("\nThe " + enemy.getName() + " is defeated!");
            }
        } else {
            gameText.setText("There are no enemies to attack.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game().setVisible(true));
    }
}
