import java.util.*;

/*
 * WorldMap.java
 * Generates and manages the game world maps for different locations.
 * Handles player movement, structure placement, and location information display.
 */

// Generates and manages the game world map and player movement
public class WorldMap {
	int playerX, playerY;
	// World size for each map level
	int[][] worldSize = {
			{7,7},//Overworld
			{3,3},//Treasure room
			{7,7},//Castle
			{5,5},//Temple
			{7,7},//Stronghold
	};
	
	String[] background = {
			/*Overworld*/"grass",
			/*Treasure*/"debris",
			/*Castle*/"debris",
			/*Temple*/"debris",
			/*Stronghold*/"debris",
	};
	
	// Number of each structure type in each map level
	int[][] structureAmount = {
			//Overworld, treasure, castle, temple, stronghold
			/*Grass*/{0, 0, 0, 0, 0},
			/*Castle*/{1, 0, 0, 0, 0},
			/*Stronghold*/{1, 0, 0, 0, 0},
			/*Temple*/{1, 0, 0, 0, 0},
			/*Mob*/{1, 0, 10, 0, 0},
			/*Elite mob*/{1, 0, 5, 0, 20},
			/*Treasure*/{1, 0, 0, 3, 0},
			/*Wall*/{0, 0, 0, 0, 0},
			/*Portal*/{0, 1, 1, 1, 1},
			/*ghost*/{0, 0, 1, 0, 0},
			/*bat*/{0, 0, 0, 0, 1},
			/*Python*/{0, 0, 0, 1, 0},
			/*Empty*/{0, 0, 0, 0, 0},
			/*Treasure chest*/{0, 1, 1, 1, 1},
			/*demon king*/{1, 0, 0, 0, 0},
			/*doctor*/{1, 0, 0, 0, 0},
			/*weapons dealer*/{1, 0, 0, 0, 0},
			/*armor merchant*/{1, 0, 0, 0, 0},
			/*market*/{1, 0, 0, 0, 0}
	};
	
	String[] structureName = {
			"grass",
			"castle",
			"stronghold",
			"temple",
			"mob",
			"elite mob",
			"treasure",
			"debris",
			"portal",
			"ghost",
			"bat",
			"python",
			"empty",
			"treasure chest",
			"demon king",
			"doctor",
			"weapon dealer",
			"armor merchant",
			"market",
	};
	
	String[] structureSymbol = {
			"🌿",//Grass
			"🏰",//Castle
			"🏯‍",//Stronghold
			"🏛",//Temple
			"👾",//Mob
			"👹",//Elite mob
			"✨",//Treasure
			"🧱",//wall
			"🌀",//portal
			"️👻",//ghost
			"🦇",//Bat
			"🐍",//Python
			"  ",//empty
			"🎁",//Treasure chest
			"👿",//Demon king
			"💊",//doctor
			"🗡️",//weapon dealer
			"🛡️",//armor merchant
			"🏪",//market
	};

	// Initialize player at map starting position
	public void initializePlayerPosition() {
		playerX = 0;
		playerY = 0;
	}
	
	// Gets player's X coordinate
	public int getPlayerX() {
		return playerX;
	}
	
	public int getPlayerY() {
		return playerY;
	}
	
	public String getBackground(String worldType) {
		return background[worldToIndex(worldType)];
	}
	
	public String getSpotInfo(LinkedList<LinkedList<String>> map) {
		return map.get(getPlayerY()).get(getPlayerX());
	}
	
	public int worldToIndex(String worldType) {
		int worldIndex = -1;
		if(worldType.equals("overworld")) worldIndex = 0;
		else if (worldType.equals("treasure")) worldIndex = 1;
		else if (worldType.equals("castle")) worldIndex = 2;
		else if (worldType.equals("temple")) worldIndex = 3;
		else if (worldType.equals("stronghold")) worldIndex = 4;
		
		return worldIndex;
	}
	
	public LinkedList<LinkedList<String>> generateWorld(String worldType) {
		Random rand = new Random();
		
		//Get type of world
		int worldIndex = worldToIndex(worldType);
		if(worldIndex == -1) return null; //This interaction does not support world generation
		
		//Get length of world
		int horizontalL = worldSize[worldIndex][1];
		int verticalL = worldSize[worldIndex][0];
		
		initializePlayerPosition(); 
		
		//Create a map
		LinkedList<LinkedList<String>> map = new LinkedList<>();
		for(int i = 0; i < verticalL; i++) {
			LinkedList<String> row = new LinkedList<>();
			for(int j = 0; j < horizontalL; j++) {
				row.add(background[worldIndex]);
			}
			map.add(row);
		}
		
		//Randomize coordinate
		int x = rand.nextInt(verticalL), y = rand.nextInt(horizontalL);
		
		//Iterate the map
		//Iterate every structures
		for(int i = 0, l = structureAmount.length; i < l; i++) {
			//Loop for generate certain amount of structure
			for(int j = 0, l2 = structureAmount[i][worldIndex]; j < l2; j++) {
				while(!map.get(x).get(y).equals(background[worldIndex])) {
					x = rand.nextInt(verticalL);
					y = rand.nextInt(horizontalL);
				}
				map.get(x).set(y, structureName[i]);
			}
		}
		return map;
	}
	
	public void playerCoodinate(String move, LinkedList<LinkedList<String>> map) {
		//Get world size
		int horizontalL = map.get(0).size();
		int verticalL = map.size();
		
		if(move.toUpperCase().equals("W") && playerY > 0) playerY--;
		else if(move.toUpperCase().equals("A") && playerX > 0) playerX--;
		else if(move.toUpperCase().equals("S") && playerY < verticalL - 1) playerY++;
		else if(move.toUpperCase().equals("D") && playerX < horizontalL - 1) playerX++;
	}
	
	public void displayMap(LinkedList<LinkedList<String>> map, String worldType) {
		if(map == null) return;
		
		//Get world size
		int horizontalL = map.get(0).size();
		int verticalL = map.size();
		
		//print world type
		System.out.println(worldType);
		
		//Display the whole map
		//Horizontal scale
		System.out.print("\t");
		for(int i = 0; i < horizontalL; i++) {
			System.out.print("|" + (char)('A' + i) + "  ");
		}
		System.out.println();
		
		for(int i = 0; i < verticalL; i++) {
			System.out.print((i+1) + "-\t");//Vertical scale
			
			//map content
			for(int j = 0; j < horizontalL; j++) {
				//Display structure
				for(int k = 0, l = structureName.length; k < l; k++) {
					if(map.get(i).get(j).equals(structureName[k])) {
						System.out.print(structureSymbol[k]);
						break;
					}
				}
				//Display player
				if(playerX == j && playerY == i) System.out.print("🙎‍♂️");
				else System.out.print("  ");
			}
			
			System.out.println();
		}
		System.out.println("\nYour current position🙎‍♂️: " + (char)('A' + getPlayerX()) + (getPlayerY() + 1) + " " + map.get(getPlayerY()).get(getPlayerX()));
		System.out.println();
		System.out.println("Press \'Q\' to open the menu ⚙️");
		if(map.get(getPlayerY()).get(getPlayerX()).equals("grass")) System.out.print("Use \'W\',\'A\',\'S\',\'D\' to move 👟:");
		else System.out.print("Use \'W\',\'A\',\'S\',\'D\' to move 👟 (Press \'E\' to interact with " + map.get(getPlayerY()).get(getPlayerX()) + " 💬):");
	}
}
