package Library;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class Game extends JFrame {
    //main game class, initialise buttons and gui
    private final Map<RoomType, Room> rooms = new HashMap<>();
    public static JTextArea gameText;
    private final JButton lookButton;
    private final JButton moveButton;
    private final JButton interactButton;
    private final JButton takeItemButton;
    private final JButton attackButton;
    private final JButton mapButton;
    private final JButton inventoryButton;
    private final JButton saveButton;
    private final JButton loadButton;
    private final JButton darkmodeButton;
    private final JPanel buttonPanel;
    private RoomType currentRoom;
    //set starting room
    private RoomType startingRoom = RoomType.MYSTICAL_GROVE;
    private final List<Item> inventory;
    private boolean isDarkMode = false;


    public Game() {
        setTitle("Adventure Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //gui info
        gameText = new JTextArea();
        gameText.setEditable(false);
        gameText.setLineWrap(true);
        gameText.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(gameText);
        add(scrollPane, BorderLayout.CENTER);

        //all buttons, using a grid setup
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

        //initialise inventory, rooms and exits
        inventory = new ArrayList<>();
        RoomType.initializeExits();
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
        //dark mode functionality
        if (isDarkMode) {
            gameText.setBackground(Color.WHITE);
            gameText.setBackground(Color.WHITE);
            gameText.setForeground(Color.BLACK);
            buttonPanel.setBackground(Color.LIGHT_GRAY);
            //change colour scheme
            for (Component c : buttonPanel.getComponents()) {
                if (c instanceof JButton) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
            }
            darkmodeButton.setText("Dark Mode");
        } else {
            //if already in dark mode have an option to switch back
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
        //loop through all set up rooms in enum and enter values into the rooms
        for (RoomType type : RoomType.values()) {
            rooms.put(type, new Room(type));
        }
    }

    private void displayCurrentRoom() {
        //display title and description
        gameText.setText(currentRoom.getTitle() + "\n" + currentRoom.getDescription());
        //loop through all exits and display for the user
        StringBuilder exits = new StringBuilder("Exits: ");
        for (String exit : currentRoom.getExits().keySet()) {
            exits.append(exit).append(" ");
        }
        gameText.append("\n" + exits);
        //display all items in the room
        List<Item> items = currentRoom.getItems();
        if (!items.isEmpty()) {
            gameText.append("\nItems in the room:");
            for (Item item : items) {
                gameText.append("\n- " + item.getName() + ": " + item.getDescription());
            }
        }
        else{
            gameText.append("\nThere are no more items in the room.");
        }
        //display all friendly NPCs in the room
        List<FriendlyNPC> npcs = currentRoom.getFriendlyNPCs();
        if (!npcs.isEmpty()) {
            gameText.append("\nNPCs in the room:");
            for (FriendlyNPC npc : npcs) {
                gameText.append("\n- " + npc.getName());
            }
        }
        //display all enemies in the room
        List<Enemy> enemies = currentRoom.getEnemies();
        if (!enemies.isEmpty()) {
            gameText.append("\nEnemies in the room:");
            for (Enemy enemy : enemies) {
                gameText.append("\n- " + enemy.getName() + " (Health: " + enemy.getHealth() + ")");
            }
        }
        else{
            gameText.append("\nThere are no enemies in this room.");
        }
    }

    private void displayMap() {
        //display the map accurate to all the exits
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

    private void move() {
        //method handling switching rooms
        String direction = JOptionPane.showInputDialog(this, "Which direction?");
        RoomType nextRoom = currentRoom.getExits().get(direction);

        if (nextRoom != null) {
            currentRoom = nextRoom;
            displayCurrentRoom();
        } else {
            gameText.setText("You can't move in that direction.");
        }
    }

    private void interactWithNPC() {
        //NPC says greeting upon interaction may implement more later
        List<FriendlyNPC> npcs = currentRoom.getFriendlyNPCs();
        if (!npcs.isEmpty()) {
            FriendlyNPC npc = npcs.get(0);
            gameText.setText(npc.getName() + ": " + npc.getGreeting());
            Player.addExperience(20);
        } else {
            gameText.setText("There is no one to interact with.");
        }
    }

    private void takeItem() {
        //method for picking up items
        List<Item> items = currentRoom.getItems();
        if (!items.isEmpty()) {
            String[] itemNames = items.stream().map(Item::getName).toArray(String[]::new);
            String selectedItemName = (String) JOptionPane.showInputDialog(this, "Select an item to take:", "Take Item", JOptionPane.QUESTION_MESSAGE, null, itemNames, itemNames[0]);
            if (selectedItemName != null) {
                Item selectedItem = items.stream().filter(i -> i.getName().equals(selectedItemName)).findFirst().orElse(null);
                if (selectedItem != null) {
                    inventory.add(selectedItem);
                    currentRoom.removeItem(selectedItem);
                    gameText.setText("You took the " + selectedItem.getName() + ".");
                    Player.addExperience(30);
                }
            }
        } else {
            gameText.setText("There are no items to take.");
        }
    }

    private void displayInventory() {
        //displaying inventory
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
        //save game to txt file
        try (FileWriter writer = new FileWriter("savegame.txt")) {
            writer.write("Player Health: " + Player.getHealth() + "\n");
            writer.write("Player Attack: " + Player.getPlayerAttack() + "\n");
            writer.write("Player Level: " + Player.getLevel() + "\n");

            writer.write("Inventory: \n");
            for (Item item : inventory) {
                writer.write(item.getName() + " | " + item.getDescription() + "\n");  // Save name and description
            }

            writer.write("Current Room Title: " + currentRoom.getTitle() + "\n");
            writer.write("Current Room Description: " + currentRoom.getDescription() + "\n");
            JOptionPane.showMessageDialog(this, "Game saved successfully!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving game: " + e.getMessage());
        }
    }

    private void loadGame() {
        //load game from txt file
        createRooms();
        String roomTitle = null;
        String roomDescription = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("savegame.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //loop through txt file looking for keywords
                if (line.startsWith("Player Health:")) {
                    Player.health = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Player Attack:")) {
                    Player.playerAttack = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Player Level:")) {
                    Player.level = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Inventory:")) {
                    loadInventory(reader);
                } else if (line.startsWith("Current Room Title:")) {
                    roomTitle = line.split(":")[1].trim();
                } else if (line.startsWith("Current Room Description:")) {
                    roomDescription = line.split(":")[1].trim();
                }
            }
            //update room
            if (roomTitle != null && roomDescription != null) {
                currentRoom = RoomType.getByTitle(roomTitle);
                displayCurrentRoom();
            }
        } catch (IOException e) {
            //error handling
            gameText.setText("Failed to load the game.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            gameText.setText("Error parsing numeric values in save file.");
            e.printStackTrace();
        }
        gameText.setText("Game Loaded!");
    }

    private void loadInventory(BufferedReader reader) throws IOException {
        //reset inventory then add all from txt file
        inventory.clear();
        String line;
        reader.mark(1000);
        while ((line = reader.readLine()) != null) {
            if (line.contains(":")) {
                reader.reset();
                break;
            }
            if (!line.trim().isEmpty()) {
                String[] parts = line.split("\\|");
                String name = parts[0].trim();
                String description = parts.length > 1 ? parts[1].trim() : "";
                Item item = new Item(name, description);
                inventory.add(item);
            }
            reader.mark(1000);
        }
    }



    private void attack() {
        //heals player to full and sets base attack before attacking enemy
        Player.health = 100;
        Player.playerAttack = 20;
        List<Enemy> enemies = currentRoom.getEnemies();
        if (!enemies.isEmpty()) {
            Enemy enemy = enemies.get(0);
            enemy.takeDamage(Player.playerAttack);
            gameText.setText("You attacked the " + enemy.getName() + " for " + Player.playerAttack + " damage!");
            if (enemy.isDefeated()) {
                gameText.append("\nThe " + enemy.getName() + " is defeated!");
                Player.addExperience(50);
                currentRoom.removeEnemy(enemy);
            } else {
                Player.takeDamage(enemy.getAttackPower());
                gameText.append("\nThe " + enemy.getName() + " attacked you back!");
                if (Player.getHealth() <= 0) {
                    gameText.append("\nYou have been defeated!");
                    // Prompt user for action
                    int choice = JOptionPane.showOptionDialog(null,
                            "You have been defeated! What would you like to do?",
                            "Game Over",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[]{"Respawn", "Exit"},
                            null);

                    if (choice == 0) {
                        respawn();
                    } else {
                        System.exit(0);
                    }
                }
            }
        } else {
            gameText.setText("There are no enemies to attack.");
        }
    }

    private void respawn(){
        //respawn method
        inventory.clear();
        Player.health = 100;
        Player.level = 0;
        currentRoom = startingRoom;
        displayCurrentRoom();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game().setVisible(true));
    }
}
