import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * GameSave.java
 * Handles game save/load functionality by reading and writing inventory and gold data to a file.
 * Allows players to persist their progress between game sessions.
 */

// Handles game save and load functionality
public class GameSave {
	static String fileName = "game_save.txt";//Store the file name we want to access
	static Scanner sc = new Scanner(System.in);
	
	public static void saveGame(Inventory inventory) {
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            
            // Save all inventory items and quantities
			for(InventoryUnit item : inventory.content) {
				writer.write(item.itemName + "," + item.amount + "\n");
			}
			
			// Save player's gold amount
			writer.write("gold," + Main.asset);
			
			writer.close();
			
			System.out.println("Gave has been saved");
			System.out.println("Press \'Enter\' to continue:");
			sc.nextLine();
		}
		catch (IOException e){
			System.out.println("Game saving failed");
			System.out.println("Press \'Enter\' to continue:");
			sc.nextLine();
			e.printStackTrace();
		}
	}
	
	public static void loadGame(Inventory inventory) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			
			// Clear previous inventory data
			inventory.content.clear();
			
			LinkedList<String> tempInventory = new LinkedList<>();
			// Read inventory items from save file
			while((line = reader.readLine()) != null) {
				if(line.trim().isEmpty()) continue;

			    String[] parts = line.split(",");

			    String itemName = parts[0];
			    int amount = Integer.parseInt(parts[1]);

			    if(itemName.equals("gold")) {
			    	Main.asset = amount;
			    }
			    else {
			    	tempInventory.add(itemName);
			    }	
			}
			
			inventory.setInventory(tempInventory);
			
			reader.close(); 
			
			System.out.println("Gave has been loaded");
			System.out.println("Press \'Enter\' to continue:");
			sc.nextLine();
		}
		//If file is not exist
		catch (IOException e) {
			System.out.println("Game loading failed");
			System.out.println("Press \'Enter\' to continue:");
			sc.nextLine();
			e.printStackTrace();
		}
	}
}
