import java.util.*;

/*
 * Main.java
 * Entry point for the Moonfall Chronicle game.
 * Initializes all game objects, manages the main game loop, handles player input,
 * and coordinates interactions between maps, combat, inventory, and events.
 */

// Main game entry point and gameplay loop manager
public class Main {
	//Set base asset to 1000 gold
	public static int asset = 1000;
	
	public static void main(String[] args) throws Exception {
		//"throws Exception" is used for using Thread.sleep(); to wait a period of time
		
		try (Scanner sc = new Scanner(System.in)) {
			clearConsole();
			System.out.println("🌕🌖🌗🌘🌑 𝓜𝓸𝓸𝓷𝓯𝓪𝓵𝓵 𝓒𝓱𝓻𝓸𝓷𝓲𝓬𝓵𝓮 🌑🌒🌓🌔🌕\n\n\n");
			System.out.println("A demon king from the moon has taken over the world\nWarriors must rebel against him to bring peace to the world.\n\n\n");
			System.out.print("Press \'enter\' to play:");
			sc.nextLine();
			//Create objects
			//Create a map object
			WorldMap map = new WorldMap();

			//Create a gameplay data onject
			GameplayData gameplayData= new GameplayData();

			//Create other event object
			InventoryEvent inventoryEvent = new InventoryEvent(gameplayData);
			
			//Create a empty map
			LinkedList<LinkedList<String>> world = new LinkedList<>();
			
			//Create a string to store the current spot on the map
			String currentSpot = null;
			
			//Store the map level sequence
			Stack currentWorld = new Stack(10);
			currentWorld.push("overworld"); //push overworld to the stack
			
			//Player inventory
			Inventory inventory = new Inventory();
			
			//Setting object
			Menu menu = new Menu();
			
			//Create GoldEvent class
			GoldEvent goldEvent = new GoldEvent(inventory);
			
			//Create Combat class
			Combat combat = new Combat();
			
			//Create a character linked list
			LinkedList<Character> currentCharacter = new LinkedList<>();
			Character Souma = new Swordsman();
			Character Ren = new Priest();
			Character Yui = new WanderingMan();
			Character Mizuki = new Alchemist();
			currentCharacter.add(Souma);
			currentCharacter.add(Ren);
			currentCharacter.add(Yui);
			currentCharacter.add(Mizuki);
			
			//Main program
			while(true) {
				//Overworld
				clearConsole();
				world = map.generateWorld(currentWorld.top());
				//If enter a map
				while(map != null) {
					map.displayMap(world, currentWorld.top());
					
					//Control player's movement
					String move = sc.nextLine();
					if(move.toUpperCase().equals("W") || 
						move.toUpperCase().equals("A") ||
						move.toUpperCase().equals("S") ||
						move.toUpperCase().equals("D")){
						map.playerCoodinate(move, world);
						currentSpot = map.getSpotInfo(world);
					}
					//Interaction
					else if(move.toUpperCase().equals("E")) {
						//If current spot is combat
						if(combat.combatManager(currentSpot, currentCharacter, inventory) == 1) break;
						
						//If current spot is portal
						if(map.getSpotInfo(world).equals("portal")) {
							currentWorld.pop();
							System.out.println(currentWorld.top());
							break;
						}
						//Market
						else if(map.getSpotInfo(world).equals("market")) {
							clearConsole();
							goldEvent.market(inventory);
						}
						//If it lead to other world
						else if(!currentSpot.equals(map.getBackground(currentWorld.top()))) {
							currentWorld.push(currentSpot);
							break;
						}
					}
					
					//Settings
					else if(move.toUpperCase().equals("Q")) {
						String operation = menu.menuManager();
						
						if(operation.equals("display inventory")) {
							Main.clearConsole();
							inventory.displayInventory();
							//Display asset
							System.out.println("\n💰 Asset: " + Main.asset + " gold");
							System.out.println("\n-------------------\nPress \'Enter\' to continue:");
							sc.nextLine();
						}
						else if (operation.equals("character equipment")) {
							ModifyEquipment.modifyEquipment(currentCharacter, inventory, sc);
						}
						else if (operation.equals("cheat code")){
							System.out.print("Enter the cheat code: ");
							String cheatCode = sc.nextLine();
							clearConsole();
							if(cheatCode.equals("moonfall")) {
								asset += 1000000;
								inventory.cheatInventory();
								System.out.println("Cheat code accepted!\nYou have gained:\n◆ 1,000,000 gold!\n◆ Full inventory!");
							}
							else {
								System.out.println("Wrong cheat code!");
							}
							System.out.println("\n-------------------\nPress \'Enter\' to continue:");
							sc.nextLine();
						}
						else if (operation.equals("load a game")) {
							GameSave.loadGame(inventory);
						}
						else if (operation.equals("save the game")) {
							GameSave.saveGame(inventory);
						}
						else if (operation.equals("quit the game")) {
							endGame();
							return;
						}
					}
					//Invalid input
					else {
						System.out.println("Wrong input");
					}
					clearConsole();
				}
				clearConsole();
				
				//Inventory event (No map is needed)
				LinkedList<String> tempInventory = inventoryEvent.eventManager(currentSpot);
				//Add item gained in the event to the invnetory
				if(tempInventory != null) {
					inventory.setInventory(tempInventory);//Transfer the data into inventory
					currentWorld.pop();//End the event
				}	
			}
		}
	}
	
	public static void endGame() {
		clearConsole();
		System.out.println("🌸 𝓣𝓱𝓪𝓷𝓴𝓼 𝓯𝓸𝓻 𝓹𝓵𝓪𝔂𝓲𝓷𝓰 🌸\n\n\n");
	}
	
	public static void clearConsole() {
		for(int i = 0; i < 40; i++) {
		    System.out.println();
		}
    }
}