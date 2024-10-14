Interactive Fiction Game documentation:


Github Link:
https://github.com/JackT-C/InteractiveJavaGame



Overview
-	This interactive fiction game is a text-based exploration game where players can interact with a variety of rooms, all containing items, NPCs (non-playable characters) and enemies.
-	The main requirements of this task are to convey a story to the user, give the user freedom to explore the map and to demonstrate interesting techniques/methods.
Changes Made
-	Introduction of GUI:






![image](https://github.com/user-attachments/assets/52995d2f-4ca2-4dee-a0e7-1ae06779b1df)
o	As seen above instead of having user inputs which could lead to many errors, there are now clean buttons at the bottom which allow quicker and more responsive gameplay
o	Room Description and exits are displayed, along with items available, NPCs, and enemies
-	Dark Mode Feature:






![image](https://github.com/user-attachments/assets/536c5075-df1c-4866-ba93-a24e2e4e458b)
-	Save and Load Game Functionality:
o	As seen above when dark mode is pressed the theme switches to a dark grey and black theme
o	Allows the user to switch back to light mode, this improves accessibility
-	Map Visualisation using ASCII art:






![image](https://github.com/user-attachments/assets/c2316a1d-9b95-4993-8ddd-3b2a1bfe447f)
-	Implementation of Friendly NPCs, the choice to add friendly NPCs, as well as enemies was because this will make each room feel more unique and improve the story telling aspect of the game.
-	Implementation of Enemies, this mechanic introduces combat to the game, it is a simple turn-based system where the player can attack the enemy then the enemy attacks the player until one is defeated.
-	Inventory System, this mechanic allows the player to keep items collected from each room, also allowing them to save and load this the next time they play.
-	Save/Load Functionality: the users current room, health, attack, and inventory are saved into the savegame.txt file, when loading this data is loaded from the file to allow users to save their progress.
Conclusion
The transition from a console-based to GUI-based adventure game enhances the player experience through improved accessibility, interactivity, and visual engagement. My other changes help implement features to keep the user engaged to convey more of a story to the user. Some potential changes that could be made to improve this game could be to finish the implementation of the XP and level system, potentially implement a quest or story mechanic increasing player engagement, and to implement a database connection with login functionality and also stores user progress.

